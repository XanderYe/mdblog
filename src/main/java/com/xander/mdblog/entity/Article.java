package com.xander.mdblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author hbxz
 * @date 2018-12-19
 */
@Data
@JsonIgnoreProperties({"handler", "javassistProxyFactory"})
public class Article {
    private Long id;

    private String title;

    private String content;

    private String creator;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private String updator;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    private Long authorId;

    private Long topicId;

    private Integer delFlag;

    @Transient
    private List<Comment> comments;
}
