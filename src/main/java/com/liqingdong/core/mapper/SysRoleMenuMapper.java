package com.liqingdong.core.mapper;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.entity.SysRoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色菜单关联关系表 Mapper 接口
 *
 * @author auto generate
 * @since 2019-01-29
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    @Select("select r.* from T_SYS_ROLE r where r.ID in " +
            "(select rm.ROLE_ID from T_SYS_ROLE_MENU rm where rm.MENU_ID in " +
            "(select m.ID from T_SYS_MENU m where m.URL like concat(#{root},'%')))")
    List<SysRole> queryRolesLikeMenuUrl(String root);
}
