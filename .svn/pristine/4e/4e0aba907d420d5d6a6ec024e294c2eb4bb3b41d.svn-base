package com.rabbit.fitness.user.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.rabbit.fitness.user.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

////    //ShiroFilter
////    @Bean
////    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
////        ShiroFilterFactoryBean filterFactoryBean= new ShiroFilterFactoryBean();
////        filterFactoryBean.setSecurityManager(securityManager);
////        Map<String,String> map = new LinkedHashMap<>();
////        map.put("/login.html","anon");
////        map.put("/register.html","anon");
////        map.put("/doLogin","anon");
////        map.put("/css/*","anon");
////        map.put("/js/*","anon");
////        map.put("/**","authc");
////        filterFactoryBean.setFilterChainDefinitionMap(map);
////        filterFactoryBean.setLoginUrl("login.html");
////        filterFactoryBean.setUnauthorizedUrl("403.html");
////        return filterFactoryBean;
////    }
//
//    //自定义域
//    @Bean
//    public Realm customRealm(HashedCredentialsMatcher matcher, MemoryConstrainedCacheManager memoryConstrainedCacheManager){
//        CustomRealm realm = new CustomRealm();
//        realm.setCredentialsMatcher(matcher);
//        realm.setCacheManager(memoryConstrainedCacheManager);
//        return realm;
//    }
//    //密码匹配器
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(2);
//        return matcher;
//    }
//    //方言，需导入依赖，使用themleaf
//    @Bean
//    public ShiroDialect shiroDialect(){
//        ShiroDialect dialect = new ShiroDialect();
//        return dialect;
//    }
//    //使注解生效的通知
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager){
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(manager);
//        return advisor;
//    }
//    //针对类进行aop代理，因为shiro在使用注解的方式来实现对 请求进行验证
//    @Bean
//    public DefaultAdvisorAutoProxyCreator proxyCreator(){
//        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
    //缓存管理器
//    @Bean
//    public MemoryConstrainedCacheManager memoryConstrainedCacheManager(){
//        MemoryConstrainedCacheManager constrainedCacheManager= new MemoryConstrainedCacheManager();
//        return constrainedCacheManager;
//    }
//
//
  /*  @Bean
    public ShiroFilterChainDefinition chainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/**","anon");

        return definition;
    }*/
@Bean
public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
    ShiroFilterFactoryBean filterFactoryBean= new ShiroFilterFactoryBean();
    filterFactoryBean.setSecurityManager(securityManager);
    Map map = new LinkedHashMap();
 /*   map.put("/**","anon");*/
    map.put("/index.html","anon");
    map.put("/","anon");
    map.put("/1.jpg","anon");
    map.put("/2.jpg","anon");
    map.put("/static-login.html","anon");
    map.put("/users/login","anon");
    map.put("/users/register","anon");
    map.put("/users/sendSms","anon");
    map.put("/users/addMessage","anon");
    map.put("/users/retrieve","anon");
    map.put("/css/**","anon");
    map.put("/js/**","anon");
    map.put("/equip/**","anon");
    map.put("/fonts/**","anon");
    map.put("/images/**","anon");
    map.put("/img/**","anon");
    map.put("/gym/listPrevious","anon");
    map.put("/gym/gymInfo","anon");
    map.put("/coach/listPrevious","anon");
    map.put("/coach/info","anon");
    map.put("/knowledge/listPrevious","anon");
    map.put("/knowledge/info","anon");
    map.put("/product/listPrevious","anon");
    map.put("/product/info","anon");
    map.put("/equip/listPrevious","anon");
    map.put("/equip/info","anon");
    map.put("/course/listPrevious","anon");
    map.put("/course/info","anon");
    map.put("/static-register.html","anon");
    map.put("/fitness_gyms.html","anon");
    map.put("/fitness_coach.html","anon");
    map.put("/fitness_course.html","anon");
    map.put("/fitness_knowledge.html","anon");
    map.put("/fitness_qicai.html","anon");
    map.put("/fitness_product.html","anon");
    map.put("/coach_list.html","anon");
    map.put("/gym_details.html","anon");
    map.put("/knowledge_details.html","anon");
    map.put("/product_details.html","anon");
    map.put("/course_details.html","anon");
    map.put("/equip_details.html","anon");
    map.put("/coach_details.html","anon");
    map.put("/coaches.html","anon");
    map.put("/upload/**","anon");
    map.put("/materialize/**","anon");



    map.put("/about.html","anon");
    map.put("/services.html","anon");
    map.put("/contact.html","anon");
    map.put("/gymes.html","anon");
    map.put("/gym_list.html","anon");
    map.put("/opt/**","anon");


    map.put("/coach_aaaa.html","anon");


    map.put("/logout", "logout");
    map.put("/**","authc");
    filterFactoryBean.setFilterChainDefinitionMap(map);
    filterFactoryBean.setLoginUrl("/static-login.html");
    filterFactoryBean.setSuccessUrl("/index.html");
    /*授权失败地址*/
    filterFactoryBean.setUnauthorizedUrl("/403.html");
    return filterFactoryBean;
}
    //安全管理器
    @Bean
    public SecurityManager securityManager(CustomRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }
    //自定义域
    //缓存器 MemoryConstrainedCacheManager
    //密码匹配器
    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher matcher,MemoryConstrainedCacheManager memoryConstrainedCacheManager){
        CustomRealm realm = new CustomRealm();
        realm.setCredentialsMatcher(matcher);
        realm.setCacheManager(memoryConstrainedCacheManager);
        return realm;
    }
    //  密码匹配器，Shiro 提供了用于加密密码和验证密码服务的 CredentialsMatcher 接口，而 HashedCredentialsMatcher
    // 正是 CredentialsMatcher 的一个实现类。写项目的话，总归会用到用户密码的非对称加密，目前主流的非对称加密方式是 MD5 ，
    // 以及在 MD5 上的加盐处理，而 HashedCredentialsMatcher 也允许我们指定自己的算法和盐
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(2);
        return matcher;
    }
    //方言，需导入依赖，使用themleaf
    @Bean
    public ShiroDialect shiroDialect(){
        ShiroDialect dialect = new ShiroDialect();
        return dialect;
    }

    //针对类进行aop代理，因为shiro在使用注解的方式来实现对 请求进行验证
    @Bean
    public DefaultAdvisorAutoProxyCreator proxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    //使注解生效的通知
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
    //缓存管理器
    @Bean
    public MemoryConstrainedCacheManager memoryConstrainedCacheManager(){
        MemoryConstrainedCacheManager constrainedCacheManager= new MemoryConstrainedCacheManager();
        return constrainedCacheManager;
    }
}
