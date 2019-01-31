package com.liqingdong.web.security;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author liqingdong
 * @since 2019/01/30 13:59
 */
@Component
public class CustomInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

//    // 此处排除配置可在框架入口进行控制，在这里排除效果是一样，前者不经过框架，后者经过但是无需任何权限。因此这里去掉排除功能
//    private static Set<String> excludeUrls = new HashSet<>();
//
//    static {
//        excludeUrls.add("/index");
//        excludeUrls.add("/login");
//        // ...
//    }

    @Autowired
    private SysRoleMenuService roleMenuService;

    /**
     * 根据请求url获取所需权限
     * 判断逻辑 根据 "/模块/子模块%" 数据库like查询所需的role
     * eg:
     * /sys-menu/index.shtml
     * /sys-menu/index/save.do
     * like "/sys-menu/index%"
     *
     * @param object FilterInvocation
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getRequestUrl();
        if (url.contains("?")) url = url.substring(0, url.indexOf('?'));
//        if (excludeUrls.contains(url)) return null;
        String[] arr = url.split("/");
        int endIndex = arr.length < 4 ? url.lastIndexOf(".") : url.lastIndexOf("/");
        String root = url.substring(0, endIndex);// 截取后的路径 "/sys-menu/index"
        List<SysRole> roles = roleMenuService.queryRolesLikeMenuUrl(root);
        Collection<ConfigAttribute> configAttributes = new ArrayList<>(roles.size());
        for (SysRole role : roles) {
            configAttributes.add(new SecurityConfig(role.getRoleCode()));
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
