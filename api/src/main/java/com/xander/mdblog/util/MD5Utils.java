package com.xander.mdblog.util;

import org.springframework.util.Base64Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author 叶振东
 * @date 2018-11-05
 */
public class MD5Utils {
    private static final String[] strDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 使用MD5加密算法
     * @param str
     * @return
     * @throws Exception
     */
    public static String encry(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byteToString(md.digest(str.getBytes()));
    }

    /**
     * MD5校检密码
     * @param pwd
     * @param loginPwd
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean validate(String pwd, String loginPwd) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        if (!pwd.equals(byteToString(md5.digest(loginPwd.getBytes())))) {
            return false;
        }
        return true;
    }

    /**
     * 使用MD5+Base64混合加密算法
     * @param pwd
     * @param salt
     * @return
     */
    public static String encryptPwd(String pwd, String salt) {
        String assemblyPwd = salt + pwd + salt;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
        }
        return Base64Utils.encodeToString(md5.digest(assemblyPwd.getBytes()));
    }

    /**
     * MD5+Base64校验密码
     * @param pwd
     * @param salt
     * @param loginPwd
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean validatePwd(String pwd, String salt, String loginPwd) throws NoSuchAlgorithmException {
        String assemblyPwd = salt + loginPwd + salt;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        if (!pwd.equals(Base64Utils.encodeToString(md5.digest(assemblyPwd.getBytes())))) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encryptPwd("admin","xander"));
        System.out.println(validate("ae31671a1e2baf69a6ca60c0c4121979","adminadmin1498184354000"));
        System.out.println(validatePwd("tEPoVmRvHYE9YoA9COToEQ==","xander","123456"));
        System.out.println(encry("adminadmin1498184354000"));
        System.out.println(encryptPwd("adminadmin", "11223311"));
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }


}