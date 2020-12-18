package com.company.project.redis;

import io.lettuce.core.ReadFrom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author dewey.du
 * @DAte 2020/12/7 20:37
 */
@Configuration
public class RedisRWConfig {
    //读写分离
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        System.out.println("使用读写分离版本");
//        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .readFrom(ReadFrom.SLAVE_PREFERRED)
//                .build();
//        // 此处
//        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration("192.168.232.128", 6379);
//        return new LettuceConnectionFactory(serverConfig, clientConfig);
//    }

    //集群 高可用 哨兵模式
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        System.out.println("使用哨兵版本");
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master("mymaster")
                // 哨兵地址
                .sentinel("192.168.232.128", 26380)
                .sentinel("192.168.232.128", 26381)
                .sentinel("192.168.232.128", 26382);
        return new LettuceConnectionFactory(sentinelConfig);
    }
}
