package com.xander.mdblog.service.impl;

import com.xander.mdblog.vo.UserVO;
import com.xander.mdblog.base.BaseServiceImpl;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.entity.User;
import com.xander.mdblog.enums.ErrorCodeEnum;
import com.xander.mdblog.mapper.UserMapper;
import com.xander.mdblog.service.UserService;
import com.xander.mdblog.util.MD5Utils;
import com.xander.mdblog.util.NamePictureUtil;
import com.xander.mdblog.util.SecureRandomUtil;
import com.xander.mdblog.util.ShortUUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.xander.mdblog.util.CheckUtil.check;

/**
 * @author Xander
 * @date 2018-11-20
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Value("${upload.root}")
    private String uploadRoot;

    @Value("${user.avatar}")
    private String avatarPath;

    @Override
    public UserVO login(User user) {
        // 校验参数是否为空
        check(StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getPassword()), ErrorCodeEnum.PARAMETER_EMPTY, "user={}", user);
        // 查询用户是否为空
        User findUser = userMapper.findUserByUsername(user.getUsername());
        check(null != findUser, ErrorCodeEnum.ACCOUNT_NOTEXIST, "user={}", user);
        // 校验密码是否相同
        check(findUser.getPassword().equals(MD5Utils.encryptPwd(user.getPassword(), Constants.SALT)), ErrorCodeEnum.ACCOUNT_OR_PASSWORD_ERROR, "user={}", user);
        UserVO userVO = new UserVO();
        userVO.setId(findUser.getId());
        userVO.setUsername(findUser.getUsername());
        userVO.setToken(findUser.getToken());
        userVO.setAvatar(findUser.getAvatar());
        return userVO;
    }

    @Override
    public void register(User user, String code, String verCode) {
        // 校验验证码是否相同
        check(StringUtils.isNotEmpty(verCode) && code.toLowerCase().equals(verCode.toLowerCase()), ErrorCodeEnum.CAPTCHA_ERROR, "code={},verCode={}", code, verCode);
        // 校验参数是否为空
        check(StringUtils.isNotEmpty(user.getUsername()) && StringUtils.isNotEmpty(user.getNickname()) && StringUtils.isNotEmpty(user.getPassword()), ErrorCodeEnum.PARAMETER_EMPTY, "user={}", user);
        // 查询用户是否存在
        User tmp = userMapper.findUserByUsername(user.getUsername());
        check(null == tmp, ErrorCodeEnum.ACCOUNT_EXIST, "user={}", user);
        //校验密码安全性
        check(user.getPassword().length() >= 6, ErrorCodeEnum.UNSAFE_PASSWORD, "user={}", user);
        // 设置密码
        user.setPassword(MD5Utils.encryptPwd(user.getPassword(), Constants.SALT));
        // 设置token
        user.setToken(SecureRandomUtil.nextHex());
        // 性别
        user.setGender(user.getGender() == null ? 0 : user.getGender());
        // 账号状态
        user.setStatus(0);
        // 权限
        user.setPermission(0);
        // 生成头像
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String fileName = sdf.format(new Date()) + "_" + ShortUUIDUtil.getShortUUID();
        try {
            NamePictureUtil.generateImg(user.getNickname(), uploadRoot + avatarPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setAvatar(avatarPath + File.separator + fileName + ".jpg");
        userMapper.insert(user);
    }

    @Override
    public User findUserByPermission(Integer permission) {
        User user = new User();
        user.setPermission(permission);
        return userMapper.selectOne(user);
    }

    @Override
    public User findByToken(String token) {
        User user = new User();
        user.setToken(token);
        return userMapper.selectOne(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

}
