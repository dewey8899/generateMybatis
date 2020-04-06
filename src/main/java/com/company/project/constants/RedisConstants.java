package com.company.project.constants;

/**
 * @author dewey.du
 * @create 2020/4/6 0006
 * <p> redis常量类 </p>
 */

public interface RedisConstants {
    /**
     * TOKEN前缀
     */
    String REDIS_PREFIX_LOGIN = "code-generator_token_%s";
    /**
     * 过期时间2小时
     */
    Integer REDIS_EXPIRE_TWO = 7200;
    /**
     * 过期时间15分
     */
    Integer REDIS_EXPIRE_EMAIL = 900;
    /**
     * 过期时间5分钟
     */
    Integer REDIS_EXPIRE_KAPTCHA = 300;
    /**
     * 暂无过期时间
     */
    Integer REDIS_EXPIRE_NULL = -1;
}
