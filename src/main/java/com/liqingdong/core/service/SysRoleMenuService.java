package com.liqingdong.core.service;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.entity.SysRoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 角色菜单关联关系表 服务类
 *
 * @author auto generate
 * @since 2019-01-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    List<SysRole> queryRolesLikeMenuUrl(String root);
}
