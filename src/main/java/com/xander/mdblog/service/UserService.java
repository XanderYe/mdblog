package com.xander.mdblog.service;

import com.xander.mdblog.vo.UserVO;
import com.xander.mdblog.base.BaseService;
import com.xander.mdblog.entity.User;

/**
 *
 * @author Xander
 * @date 2018-11-20
 */
public interface UserService extends BaseService<User> {
    /**
     * 登录
     * @param user
     * @return
     */
    UserVO login(User user);
    /**
     * 注册
     * @param user
     * @return
     */
    public void register(User user, String code, String verCode);

    /**
     * 根据permission获取博客拥有者
     * @param permission
     * @return
     */
    User getUserByPermission(int permission);

    /**
     * 根据token查询用户
     * @param token
     * @return com.xander.mdblog.entity.User
     * @author yezhendong
     * @date 2019-07-10
     */
    User selectByToken(String token);
}
