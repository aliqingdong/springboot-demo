package com.liqingdong.web.security;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.entity.SysUser;
import com.liqingdong.core.service.SysUserRoleService;
import com.liqingdong.core.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author liqingdong
 * @since 2019/01/29 10:05
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService userRoleService;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = userService.findUserByUsername(userName);
        if (null == user)
            throw new UsernameNotFoundException("用户不存在！");

        if ("0".equals(user.getStatus()))
            return new CustomUserDetails(userName, user.getPassword(), false, true,
                    true, true, Collections.emptyList());

        List<SysRole> roles = userRoleService.queryRolesByUsername(userName);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = null;
        for (SysRole role : roles) {
            authority = new SimpleGrantedAuthority(role.getRoleCode());
            authorities.add(authority);
        }

        return new CustomUserDetails(userName, user.getPassword(), true, true,
                true, true, authorities, user);
    }
}
