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
     * 日期格式
     */
    public static final String DATE_FORMAT = "yyyyMMddhhmmss";

    /**
     * 验证码长度
     */
    public static int IMAGE_WIDTH = 100;

    /**
     * 验证码宽度
     */
    public static int IMAGE_HEIGHT = 30;

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

    /**
     * 默认redis用户过期时间
     */
    public static final long REDIS_USER_EXPIRE = 3600;

    /**
     * 默认redis验证码过期时间
     */
    public static final long REDIS_CAPTCHA_EXPIRE = 600;
}
