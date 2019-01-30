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
 * 用户与角色的关联关系表 实体
 *
 * @author auto generate
 * @since 2019-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("T_SYS_USER_ROLE")
public class SysUserRole extends BaseEntity<SysUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private Long roleId;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId(){
        return this.id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}