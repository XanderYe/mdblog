package com.xander.mdblog.VO;

import lombok.Data;

/**
 * @author 叶振东
 * @date 2018-12-19
 */
@Data
public class OwnerVO {
    private String owner;

    private String avatar;

    private String email;

    private String description;

    private String github;

    private String themePrimary;

    private String themeAccent;

    private String themeLayout;
}
