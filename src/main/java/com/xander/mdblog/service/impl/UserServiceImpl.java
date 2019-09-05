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
        check(StringUtils.isNoneEmpty(user.getPassword()), ErrorCodeEnum.PARAMETER_EMPTY, "user={}", user);
        User findUser = userMapper.selectOne(user);
        check(null != findUser, ErrorCodeEnum.ACCOUNT_NOTEXIST, "user={}", user);
        check(findUser.getPassword().equals(MD5Utils.encryptPwd(user.getPassword(), Constants.SALT)), ErrorCodeEnum.ACCOUNT_OR_PASSWORD_ERROR, "user={}", user);
        UserVO userVO = new UserVO();
        userVO.setId(findUser.getId());
        userVO.setUsername(findUser.getUsername());
        userVO.setToken(findUser.getToken());
        return userVO;
    }

    @Override
    public void register(User user, String code, String verCode) {
        check(StringUtils.isNotEmpty(verCode) && code.toLowerCase().equals(verCode.toLowerCase()), ErrorCodeEnum.CAPTCHA_ERROR, "code={},verCode={}", code, verCode);
        check(StringUtils.isNotEmpty(user.getUsername()) || StringUtils.isNotEmpty(user.getNickname()) || StringUtils.isNotEmpty(user.getPassword()), ErrorCodeEnum.PARAMETER_EMPTY, "user={}", user);
        check(user.getPassword().length() > 6, ErrorCodeEnum.UNSAFE_PASSWORD, "user={}", user);
        User findUser = new User();
        findUser.setUsername(user.getUsername());
        User tmp = userMapper.selectOne(findUser);
        check(tmp == null, ErrorCodeEnum.ACCOUNT_EXIST, "user={}", user);
        // 设置密码
        user.setPassword(MD5Utils.encryptPwd(user.getPassword(), Constants.SALT));
        // 设置token
        user.setToken(SecureRandomUtil.nextHex());
        // 生成头像
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sdf.format(new Date()) + "_" + ShortUUIDUtil.getShortUUID();
        try {
            NamePictureUtil.generateImg(user.getNickname(), uploadRoot + avatarPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setAvatar("avatarPath" + File.separator + fileName + ".jpg");
        userMapper.insert(user);
    }

    @Override
    public User getUserByPermission(int permission) {
        User user = new User();
        user.setPermission(permission);
        return userMapper.selectOne(user);
    }

    @Override
    public User selectByToken(String token) {
        User user = new User();
        user.setToken(token);
        return userMapper.selectOne(user);
    }

}
