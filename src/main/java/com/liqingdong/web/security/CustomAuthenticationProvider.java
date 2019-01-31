package com.liqingdong.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 自定义认证供应商
 *
 * @author liqingdong
 * @since 2019/01/29 10:04
 */
@Slf4j
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    /**
     * 1、父类已经对账号和密码进行了校验
     * 2、此处可添加一些额外校验 比如图片验证码、手机动态验证码
     * 3、校验不通过直接抛出异常即可，一般使用 {@link BadCredentialsException}
     * 4、也可以通过自定义过滤器链来实现额外的校验，过滤器链执行顺序
     * {@link org.springframework.security.config.annotation.web.builders.FilterComparator#FilterComparator()}
     *
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        logger.info("additionalAuthenticationChecks");
        // TODO 添加额外校验
        super.additionalAuthenticationChecks(userDetails, authentication);
    }

//    @PostConstruct
//    public void Construct() {
//        super.setHideUserNotFoundExceptions(false);
//        super.setMessageSource(messageSource);
//        super.setPasswordEncoder(md5PasswordEncoder);
//        super.setUserDetailsService(userDetailsService);
//    }

}
