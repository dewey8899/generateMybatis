//package com.company.project.shiro;
//
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.cache.MemoryConstrainedCacheManager;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.crazycake.shiro.RedisManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Async;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @author dewey.du
// * @create 2020/4/3 0003
// */
//@Configuration
//public class ShiroConfiguration {
//
//    @Bean("credentialMatcher")
//    public CredentialMatcher credentialMatcher(){
//        return new CredentialMatcher();
//    }
//    @Bean("authRealm")
//    public AuthRealm authRealm(@Qualifier("credentialMatcher")CredentialMatcher credentialMatcher){
//        AuthRealm authRealm = new AuthRealm();
//        authRealm.setCredentialsMatcher(credentialMatcher);
//        //认证会缓存到内存中
//        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
//        return authRealm;
//    }
//    @Bean("securityManager")
//    public SecurityManager securityManager(@Qualifier("authRealm")AuthRealm authRealm){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(authRealm);
//        return securityManager;
//    }
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager securityManager){
//        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//        factoryBean.setSecurityManager(securityManager);
//
//        factoryBean.setLoginUrl("/login");
//        factoryBean.setSuccessUrl("/index");
//        factoryBean.setUnauthorizedUrl("/unauthorized");
//
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/index", "authc");
//        filterChainDefinitionMap.put("/login", "anon");
//        filterChainDefinitionMap.put("/loginUser", "anon");
//        filterChainDefinitionMap.put("/admin", "roles[admin]");
//        filterChainDefinitionMap.put("/edit", "perms[edit]");
//        //数据库监控
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/**", "user");
//
//        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return factoryBean;
//    }
//
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
//        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
//
//    /**
//     * 身份验证器
//     */
//    @Bean
//    public AuthRealm shiroRealm() {
//        AuthRealm shiroRealm = new AuthRealm();
//        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return shiroRealm;
//    }
//
//    /**
//     *  自定义Realm的加密规则 -> 凭证匹配器：将密码校验交给Shiro的SimpleAuthenticationInfo进行处理,在这里做匹配配置
//     */
//    @Bean
//    @Async
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
//        // 散列算法:这里使用SHA256算法;
////        shaCredentialsMatcher.setHashAlgorithmName(Sha256.HASH_ALGORITHM_NAME);
////        // 散列的次数，比如散列两次，相当于 md5(md5(""));
////        shaCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
//        return shaCredentialsMatcher;
//    }
//
//    /**
//     * 配置Redis管理器：使用的是shiro-redis开源插件
//     */
////    @Bean
////    public RedisManager redisManager() {
////        RedisManager redisManager = new RedisManager();
////        redisManager.setHost(host);
////        redisManager.setPort(port);
////        redisManager.setTimeout(timeout);
//////        redisManager.setPassword(password);
////        return redisManager;
////    }
//
//    /**
//     * 配置Cache管理器：用于往Redis存储权限和角色标识  (使用的是shiro-redis开源插件)
//     */
////    @Bean
////    public RedisCacheManager cacheManager() {
////        RedisCacheManager redisCacheManager = new RedisCacheManager();
////        redisCacheManager.setRedisManager(redisManager());
////        redisCacheManager.setKeyPrefix(CACHE_KEY);
////        // 配置缓存的话要求放在session里面的实体类必须有个id标识 注：这里id为用户表中的主键，否-> 报：User must has getter for field: xx
////        redisCacheManager.setPrincipalIdFieldName("id");
////        return redisCacheManager;
////    }
//
//    /**
//     * SessionID生成器
//     */
////    @Bean
////    public ShiroSessionIdGenerator sessionIdGenerator(){
////        return new ShiroSessionIdGenerator();
////    }
//
//    /**
//     * 配置RedisSessionDAO (使用的是shiro-redis开源插件)
//     */
////    @Bean
////    public RedisSessionDAO redisSessionDAO() {
////        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
////        redisSessionDAO.setRedisManager(redisManager());
////        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
////        redisSessionDAO.setKeyPrefix(SESSION_KEY);
////        redisSessionDAO.setExpire(EXPIRE);
////        return redisSessionDAO;
////    }
//
//    /**
//     * 配置Session管理器
//     */
////    @Bean
////    public SessionManager sessionManager() {
////        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
////        shiroSessionManager.setSessionDAO(redisSessionDAO());
////        return shiroSessionManager;
////    }
//}
