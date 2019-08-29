package com.xander.mdblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author hbxz
 * @date 2018/12/20 0020
 */
@Data
@JsonIgnoreProperties({"handler", "javassistProxyFactory"})
public class Comment {
    private Long id;

    private String content;

    private String image;

    private Integer floor;

    private Long authorId;

    private Long toId;

    private Long articleId;

    private String creator;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private String updator;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    private Integer delFlag;

    private String nickname;

    private String email;
}
