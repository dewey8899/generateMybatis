package com.company.project.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author dewey.du
 * @create 2020/4/6 0006
 * <p> 根据传过来的密码进行加密 - 注意：加密算法必须一致(加密算法类型，加密次数，盐值  ex：MD5 10 zhengqing)</p>
 */

public class MD5Utils {
    //    public static final String SALT = "deweydu";
    public static final int HASHITERATIONS = 10;

    public static String createMD5Str(String password, String salt){
        SimpleHash hash = new SimpleHash("MD5", password, salt, HASHITERATIONS);
        return hash.toString();
    }
}
