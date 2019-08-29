package com.xander.mdblog.base;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author 叶振东
 * @date 2019-01-10
 */
@Data
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String msg = "success";

    private int code = 0;
    private T data;

    public ResultBean() {
    }

    public ResultBean(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    public ResultBean(T data) {
        this.data = data;
    }

}