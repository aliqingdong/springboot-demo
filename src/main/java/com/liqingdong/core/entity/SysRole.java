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
 * 系统用户组 实体
 *
 * @author auto generate
 * @since 2019-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("T_SYS_ROLE")
public class SysRole extends BaseEntity<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    private String roleName;

    /**
     * 角色代码
     */
    @TableField("ROLE_CODE")
    private String roleCode;

    /**
     * 角色描述
     */
    @TableField("ROLE_DESC")
    private String roleDesc;

    /**
     * 角色是否可删除 0:不可删除 1:可删除
     */
    @TableField("CAN_DELETE")
    private String canDelete;

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