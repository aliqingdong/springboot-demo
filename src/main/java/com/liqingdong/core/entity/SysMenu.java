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
 * 系统菜单 实体
 *
 * @author auto generate
 * @since 2019-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("T_SYS_MENU")
public class SysMenu extends BaseEntity<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("MENU_NAME")
    private String menuName;

    /**
     * 菜单编码
     */
    @TableField("MENU_CODE")
    private String menuCode;

    /**
     * 菜单图标
     */
    @TableField("MENU_ICON")
    private String menuIcon;

    /**
     * 菜单描述
     */
    @TableField("MENU_DESC")
    private String menuDesc;

    /**
     * 是否叶子节点0:非叶子节点 1:叶子节点
     */
    @TableField("IS_LEAF")
    private String isLeaf;

    /**
     * 菜单级别
     */
    @TableField("MENU_LEVEL")
    private Integer menuLevel;

    /**
     * 父菜单id
     */
    @TableField("PARENT_MENU_ID")
    private Long parentMenuId;

    /**
     * 菜单排序
     */
    @TableField("SORT_NO")
    private Integer sortNo;

    /**
     * 菜单访问的url
     */
    @TableField("URL")
    private String url;

    /**
     * 菜单是否可删除 0:不可删除 1:可删除
     */
    @TableField("CAN_DELETE")
    private String canDelete;

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