package com.xander.mdblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 *
 * @author 叶振东
 * @date 2018-11-16
 */
@Data
@JsonIgnoreProperties({"handler", "javassistProxyFactory"})
public class User {

    private Long id;

    private String username;

    private String password;

    private String token;

    private String nickname;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    private String avatar;

    private String gender;

    private String phone;

    private String description;

    private String github;

    private String themePrimary;

    private String themeAccent;

    private String themeLayout;

    private Integer status;

    private Integer permission;
}
