package com.xander.mdblog.controller;

import com.xander.mdblog.config.VerCodeCache;
import com.xander.mdblog.enums.ErrorCodeEnum;
import com.xander.mdblog.vo.OwnerVO;
import com.xander.mdblog.vo.UserVO;
import com.xander.mdblog.base.RequestContextHolder;
import com.xander.mdblog.base.ResultBean;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.User;
import com.xander.mdblog.service.UserService;
import com.xander.mdblog.util.ShortUUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.xander.mdblog.util.CheckUtil.check;

/**
 * @author 叶振东
 * @date 2018-11-20
 */
@Api(value = "用户controller", tags = {"用户操作接口"})
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private VerCodeCache verCodeCache;
    @Autowired
    private UserService userService;

    @Value("${upload.root}")
    private String uploadRoot;

    @Value("${user.avatar}")
    private String avatarPath;

    @ApiOperation(value = "登录", notes = "")
    @PostMapping("login")
    public ResultBean login(User user) {
        UserVO userVO = userService.login(user);
        return new ResultBean<>(userVO);
    }

    @ApiOperation(value = "注册", notes = "")
    @PostMapping("register")
    public ResultBean register(User user, String uuid, String verCode) {
        String code = verCodeCache.get(uuid);
        this.userService.register(user, code, verCode);
        return new ResultBean<>();
    }

    @ApiOperation(value = "检查用户名", notes = "")
    @GetMapping("check")
    public ResultBean checkUser(String username) {
        check(StringUtils.isNotEmpty(username), ErrorCodeEnum.PARAMETER_ERROR, "");
        User findUser = userService.findUserByUsername(username);
        check(findUser == null, ErrorCodeEnum.ACCOUNT_EXIST, "");
        return new ResultBean<>();
    }

    /**
     * 设置主题
     *
     * @param primary
     * @param accent
     * @param layout
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value = "设置主题", notes = "")
    @PostMapping("theme")
    public ResultBean changeTheme(String primary, String accent, String layout) {
        User user = RequestContextHolder.get();
        if (StringUtils.isNotEmpty(primary)) {
            user.setThemePrimary(primary);
        }
        if (StringUtils.isNotEmpty(accent)) {
            user.setThemeAccent(accent);
        }
        if (StringUtils.isNotEmpty(layout)) {
            user.setThemeLayout(layout);
        }
        return new ResultBean();
    }

    /**
     * 上传头像
     *
     * @param file
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value = "上传头像", notes = "")
    @PostMapping("uploadAvatar")
    public ResultBean uploadAvatar(@RequestParam("avatar") MultipartFile file) throws Exception {
        User user = RequestContextHolder.get();
        String originalFilename = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = sdf.format(new Date()) + "_" + ShortUUIDUtil.getShortUUID() + "." + suffix;
        String destFileName = uploadRoot + avatarPath + File.separator + fileName;
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();
        file.transferTo(destFile);
        String avatar = avatarPath + File.separator + fileName;
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setAvatar(avatar);
        userService.update(updateUser);
        user.setAvatar(avatar);
        return new ResultBean<>(avatar);
    }

    /**
     * 获取博客拥有者主题
     *
     * @param
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019/8/27
     */
    @ApiOperation(value = "获取博客信息", notes = "")
    @GetMapping("getOwner")
    public ResultBean getOwner() {
        User user = userService.findUserByPermission(Constants.BLOG_OWNER);
        OwnerVO owner = new OwnerVO();
        owner.setId(user.getId());
        owner.setOwner(user.getNickname());
        owner.setDescription(user.getDescription());
        owner.setOccupation(user.getOccupation());
        owner.setEmail(user.getEmail());
        owner.setGithub(user.getGithub());
        owner.setAvatar(user.getAvatar());
        owner.setThemePrimary(user.getThemePrimary() == null ? "teal" : user.getThemePrimary());
        owner.setThemeAccent(user.getThemeAccent() == null ? "teal" : user.getThemeAccent());
        owner.setThemeLayout(user.getThemeLayout() == null ? "light" : user.getThemeLayout());
        return new ResultBean<>(owner);
    }

    /**
     * 获取登录信息
     * @param
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019/11/20
     */
    @ApiOperation(value = "获取登录信息", notes = "")
    @GetMapping("info")
    public ResultBean info() {
        User user = RequestContextHolder.get();
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setNickname(user.getNickname());
        userVO.setAvatar(user.getAvatar());
        return new ResultBean<>(userVO);
    }
}
