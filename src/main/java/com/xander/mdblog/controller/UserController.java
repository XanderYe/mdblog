package com.xander.mdblog.controller;

import com.xander.mdblog.VO.OwnerVO;
import com.xander.mdblog.VO.UserVO;
import com.xander.mdblog.base.RequestContextHolder;
import com.xander.mdblog.base.ResultBean;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.User;
import com.xander.mdblog.service.UserService;
import com.xander.mdblog.util.ShortUUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 叶振东
 * @date 2018-11-20
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${upload.root}")
    private String uploadRoot;

    @Value("${user.avatar}")
    private String avatarPath;

    @PostMapping("login")
    public ResultBean login(User user) {
        UserVO userVO = userService.login(user);
        return new ResultBean<>(userVO);
    }

    @PostMapping("register")
    public ResultBean register(HttpSession session, User user, String verCode) {
        String code = (String) session.getAttribute("captcha");
        this.userService.register(user, code, verCode);
        return new ResultBean<>();
    }

    @GetMapping("logout")
    public ResultBean logout(HttpSession session) {
        session.invalidate();
        return new ResultBean();
    }

    /**
     * 设置主题
     *
     * @param primary
     * @param accent
     * @param layout
     * @param session
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @PostMapping("theme")
    public ResultBean changeTheme(String primary, String accent, String layout, HttpSession session) {
        User user = (User) session.getAttribute("user");
        OwnerVO ownerVO = (OwnerVO) session.getAttribute("owner");
        if (!("").equals(primary)) {
            user.setThemePrimary(primary);
            ownerVO.setThemePrimary(primary);
        }
        if (!("").equals(accent)) {
            user.setThemeAccent(accent);
            ownerVO.setThemeAccent(accent);
        }
        if (!("").equals(layout)) {
            user.setThemeLayout(layout);
            ownerVO.setThemeLayout(layout);
        }
        userService.update(user);
        session.removeAttribute("user");
        session.removeAttribute("theme");
        session.setAttribute("user", user);
        session.setAttribute("owner", ownerVO);
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
    @PostMapping("uploadAvatar")
    public ResultBean uploadAvatar(@RequestParam("avatar") MultipartFile file) throws Exception {
        User user = RequestContextHolder.get();
        String originalFilename = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
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
     * @param
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019/8/27
     */
    @GetMapping("getOwner")
    public ResultBean getOwner() {
        User user = userService.getUserByPermission(Constants.BLOG_OWNER);
        OwnerVO owner = new OwnerVO();
        owner.setOwner(user.getNickname());
        owner.setDescription(user.getDescription());
        owner.setEmail(user.getEmail());
        owner.setGithub(user.getGithub());
        owner.setAvatar(user.getAvatar());
        owner.setThemePrimary(user.getThemePrimary() == null ? "teal" : user.getThemePrimary());
        owner.setThemeAccent(user.getThemeAccent() == null ? "teal" : user.getThemeAccent());
        owner.setThemeLayout(user.getThemeLayout() == null ? "light" : user.getThemeLayout());
        return new ResultBean<>(owner);
    }
}
