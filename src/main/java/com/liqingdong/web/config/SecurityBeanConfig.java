package com.liqingdong.web.config;

import com.liqingdong.web.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author liqingdong
 * @since 2019/01/30 17:54
 */
@Configuration
public class SecurityBeanConfig {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages_zh_CN");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    @Autowired
    public CustomAuthenticationProvider customAuthenticationProvider(
            Md5PasswordEncoder md5PasswordEncoder,
            ResourceBundleMessageSource messageSource,
            CustomUserDetailsService userDetailsService) {
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(md5PasswordEncoder);
        authenticationProvider.setMessageSource(messageSource);
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Bean
    public CustomFilterSecurityInterceptor customFilterSecurityInterceptor(
            CustomInvocationSecurityMetadataSource securityMetadataSource,
            CustomAccessDecisionManager accessDecisionManager) {
        CustomFilterSecurityInterceptor filterSecurityInterceptor = new CustomFilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        return filterSecurityInterceptor;
    }

    @Bean
    public CustomLoginFilter customLoginFilter(
            CustomAuthenticationSuccessHandler successHandler,
            CustomAuthenticationFailureHandler failureHandler) {
        CustomLoginFilter customLoginFilter = new CustomLoginFilter();
        customLoginFilter.setAuthenticationSuccessHandler(successHandler);
        customLoginFilter.setAuthenticationFailureHandler(failureHandler);
        return customLoginFilter;
    }

    @Bean
    public CustomAjaxSessionStrategy customAjaxSessionStrategy() {
        CustomAjaxSessionStrategy customAjaxSessionStrategy = new CustomAjaxSessionStrategy();
        customAjaxSessionStrategy.setInvalidSessionUrl("/login");
        return customAjaxSessionStrategy;
    }
}
