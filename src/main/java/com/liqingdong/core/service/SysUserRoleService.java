package com.liqingdong.core.service;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 用户与角色的关联关系表 服务类
 *
 * @author auto generate
 * @since 2019-01-29
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    List<SysRole> queryRolesByUsername(String userName);
}
