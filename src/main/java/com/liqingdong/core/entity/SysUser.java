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
 * 系统用户 实体
 *
 * @author auto generate
 * @since 2019-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("T_SYS_USER")
public class SysUser extends BaseEntity<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 用户密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 密码长度
     */
    @TableField("PASSWORD_LENGTH")
    private Integer passwordLength;

    /**
     * 用户姓名
     */
    @TableField("NAME")
    private String name;

    /**
     * 用户性别1-男2-女
     */
    @TableField("SEX")
    private String sex;

    /**
     * 联系电话
     */
    @TableField("TEL")
    private String tel;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 用户是否可删除 0:不可删除 1:可删除
     */
    @TableField("CAN_DELETE")
    private String canDelete;

    /**
     * 用户状态0-无效1-有效
     */
    @TableField("STATUS")
    private String status;

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