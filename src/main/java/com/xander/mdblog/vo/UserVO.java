package com.xander.mdblog.vo;

import lombok.Data;

/**
 * @author 叶振东
 * @date 2019-07-10
 */
@Data
public class UserVO {
    private Long id;

    private String username;

    private String nickname;

    private String avatar;

    private String token;
}
