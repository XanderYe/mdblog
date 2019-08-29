package com.xander.mdblog.util;

/**
 * @author hbxz
 * @date 2018/12/21 0021
 */
public class EmailUtils {
    /**
     * 校验EMAIL格式，真为正确
     *
     * @author
     * @date 2017-7-19
     * @param email
     * @return true 为格式正确 false 为格式错误
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            tag = false;
        }
        return tag;
    }

}
