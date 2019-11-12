package com.xander.mdblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * @author 叶振东
 * @date 2018-11-16
 */
@Data
@JsonIgnoreProperties({"handler", "javassistProxyFactory"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String token;

    private String nickname;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    private String avatar;

    private Integer gender;

    private String phone;

    private String occupation;

    private String description;

    private String github;

    private String themePrimary;

    private String themeAccent;

    private String themeLayout;

    private Integer status;

    private Integer permission;
}
