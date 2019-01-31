package com.liqingdong.core.mapper;

import com.liqingdong.core.entity.SysRole;
import com.liqingdong.core.entity.SysUserRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户与角色的关联关系表 Mapper 接口
 *
 * @author auto generate
 * @since 2019-01-29
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Select("select r.* from T_SYS_ROLE r where r.ID in " +
            "(select ur.ROLE_ID from T_SYS_USER_ROLE ur where ur.USER_ID = " +
            "(select u.ID from T_SYS_USER u where u.USERNAME = #{userName}))")
    List<SysRole> queryRolesByUsername(String userName);
}
