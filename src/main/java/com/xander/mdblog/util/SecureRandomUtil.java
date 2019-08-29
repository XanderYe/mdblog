package com.xander.mdblog.util;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

/**
 * Created by Xander on 2018-11-05.
 */
public class SecureRandomUtil {
    private static final int DEFAULT_HEX_LENGTH = 32;

    public static String nextHex(int length) {
        SecureRandom secureRandom = new SecureRandom();
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length + 1 >> 1];
        random.nextBytes(bytes);
        String hex = DatatypeConverter.printHexBinary(bytes);
        hex = hex.substring(0, length);
        return hex;
    }

    public static String nextHex() {
        return nextHex(32);
    }

    public static void main(String[] args) {
        System.out.println(nextHex(64));
    }
}