package com.liqingdong.core.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity
 *
 * @author liqingdong
 * @since 2019/01/29 11:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
abstract class BaseEntity<T extends Model> extends Model<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("CREATE_BY")
    private String createBy;
    @TableField("CREATE_DATE")
    private Date createDate;
    @TableField("UPDATE_BY")
    private String updateBy;
    @TableField("UPDATE_DATE")
    private Date updateDate;

    protected abstract Long getId();

    protected abstract void setId(Long id);
}
