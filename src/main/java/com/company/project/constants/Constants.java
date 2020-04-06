package com.company.project.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dewey.du
 * @create 2020/4/6 0006
 */

public interface Constants {
    public static String IMG_DOMAIN = "";

    /**
     * 接口url
     */
    public static Map<String,String> URL_MAPPING_MAP = new HashMap<>();

    /**
     *  获取项目根目录
     */
    public static String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * SHA-256加密盐值
     */
    public static String SALT = "zhengqing";

    /**
     * 请求头 - token  【注：ShiroConfig中放行】
     */
    public static final String REQUEST_HEADER = "X-Token";
}
