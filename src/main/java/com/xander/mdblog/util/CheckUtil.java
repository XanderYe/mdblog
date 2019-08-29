package com.xander.mdblog.util;

import com.xander.mdblog.enums.ErrorCodeEnum;
import com.xander.mdblog.exception.BusinessException;

/**
 *
 * @author 叶振东
 * @date 2018-11-21
 */
public class CheckUtil {
    /**
     * 校验方法
     * @param condition
     * @param errorCode
     * @param snapshotFormat
     * @param argArray
     */
    public static void check(boolean condition, ErrorCodeEnum errorCode, String snapshotFormat, Object... argArray){
        if(!condition){
            throw new BusinessException(errorCode,snapshotFormat,argArray);
        }
    }
}
