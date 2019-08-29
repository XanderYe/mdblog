package com.xander.mdblog.enums;

import lombok.Getter;

/**
 *
 * @author 叶振东
 * @date 2018-11-05
 */
@Getter
public enum ErrorCodeEnum {
    /*错误码*/
    ACCOUNT_OR_PASSWORD_ERROR(10001, "账号或密码错误！"),
    ACCOUNT_NOTEXIST(10002, "账号不存在！"),
    ACCOUNT_AUTH_ERROR(10003, "账号认证异常！"),
    ACCOUNT_DISABLED(10004, "账号被禁用！"),
    ACCOUNT_EXIST(10005, "用户名已存在！"),
    UNSAFE_PASSWORD(10006, "密码不安全！"),
    PARAMETER_ERROR(10007, "参数错误！"),
    PARAMETER_EMPTY(10008, "参数不为空！"),
    CAPTCHA_ERROR(10009, "验证码错误！"),
    NICKNAME_TOOLONG(10010, "昵称过长！"),
    EMAIL_ERROR(10011, "邮箱格式错误！");
    private int code;
    private String message;

    private ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}