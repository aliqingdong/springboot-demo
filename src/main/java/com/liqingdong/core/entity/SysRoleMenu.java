package com.liqingdong.core.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色菜单关联关系表 实体
 *
 * @author auto generate
 * @since 2019-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("T_SYS_ROLE_MENU")
public class SysRoleMenu extends BaseEntity<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField("MENU_ID")
    private Long menuId;

    @Override
    protected void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Long getId(){
        return this.id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}