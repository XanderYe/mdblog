package com.xander.mdblog.constant;

/**
 *
 * @author 叶振东
 * @date 2018-11-20
 */
public final class Constants {
    /**
     * 盐
     */
    public static String SALT = "xander";

    /**
     * 昵称长度
     */
    public static Integer MAX_LENGTH = 32;

    /**
     * 未删除状态
     */
    public static final Integer IS_NOT_DELETED = 1;

    /**
     * 删除状态
     */
    public static final Integer IS_DELETED = 0;

    /**
     * 博客拥有者
     */
    public static final int BLOG_OWNER = 1;

    /**
     * session存储博客拥有者标识
     */
    public static final String OWNER_STRING = "owner";
}
