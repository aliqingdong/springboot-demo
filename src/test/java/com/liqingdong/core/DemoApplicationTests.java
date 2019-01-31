package com.liqingdong.core;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.service.SysRoleMenuService;
import com.liqingdong.core.service.SysUserRoleService;
import com.liqingdong.core.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Test
    public void test() {
        sysUserService.findUserByUsername("root");
    }

    @Test
    public void test1() {
        List<SysRole> roles = userRoleService.queryRolesByUsername("root");
        System.out.println(roles.size());
    }

    @Test
    public void test2() {
        List<SysRole> roles = roleMenuService.queryRolesLikeMenuUrl("/user/index");
        System.out.println(roles.size());
    }
}

