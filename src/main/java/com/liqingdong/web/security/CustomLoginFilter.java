package com.liqingdong.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义认证过滤器
 * 可在这里做登陆的其他校验
 * 需要配置authenticationManager
 *
 * @author liqingdong
 * @since 2019/01/31 11:27
 */
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

//    @Autowired
//    @Override
//    public void setRememberMeServices(RememberMeServices rememberMeServices) {
//        super.setRememberMeServices(rememberMeServices);
//    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }
}
