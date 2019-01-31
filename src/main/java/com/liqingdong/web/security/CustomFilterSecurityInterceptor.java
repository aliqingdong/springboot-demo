package com.liqingdong.web.security;

import lombok.Setter;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;

/**
 * 根据访问url决策访问权限
 * {@link CustomInvocationSecurityMetadataSource} 根据请求的url的查询出所需的权限
 * {@link CustomAccessDecisionManager} 通过自定义决策管理器进行校验操作
 *
 * @author liqingdong
 * @since 2019/01/30 16:59
 */
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Setter
    private CustomInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        invoke(filterInvocation);
    }

    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
