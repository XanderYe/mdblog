package com.xander.mdblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author hbxz
 * @date 2018-12-19
 */
@Data
@JsonIgnoreProperties({"handler", "javassistProxyFactory"})
public class Topic {
    private Long id;

    private String name;

    private String description;

    private String creator;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private String updator;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    private Integer delFlag;
}