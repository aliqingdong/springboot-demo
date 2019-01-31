package com.liqingdong.core.service.impl;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.entity.SysRoleMenu;
import com.liqingdong.core.mapper.SysRoleMenuMapper;
import com.liqingdong.core.service.SysRoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色菜单关联关系表 服务实现类
 *
 * @author auto generate
 * @since 2019-01-29
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public List<SysRole> queryRolesLikeMenuUrl(String root) {
        return baseMapper.queryRolesLikeMenuUrl(root);
    }
}
