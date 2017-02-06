package com.crawler.pbccrc.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtil {

    public static String sha256Salt(String plainPassword,String salt){
        return DigestUtils.sha256Hex(plainPassword + salt);
    }
}
