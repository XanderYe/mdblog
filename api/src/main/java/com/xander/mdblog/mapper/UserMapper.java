package com.xander.mdblog.mapper;

import com.xander.mdblog.base.BaseMapper;
import com.xander.mdblog.entity.User;

/**
 *
 * @author 叶振东
 * @date 2018-11-19
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return com.xander.mdblog.entity.User
     * @author yezhendong
     * @date 2019/9/10
     */
    User findUserByUsername(String username);
}
