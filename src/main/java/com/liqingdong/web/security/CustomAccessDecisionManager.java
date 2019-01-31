package com.liqingdong.web.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 访问决策管理器
 *
 * @author liqingdong
 * @since 2019/01/30 14:32
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        // 允许访问没有设置权限的资源
        if (null == configAttributes) return;

        // 一个资源可以由多个权限访问，用户拥有其中一个权限即可访问该资源
        for (ConfigAttribute configAttribute : configAttributes) {
            String needPermission = configAttribute.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needPermission.equals(ga.getAuthority())) return;
            }
        }

        throw new AccessDeniedException("没有权限访问");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
