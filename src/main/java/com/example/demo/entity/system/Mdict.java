package com.example.demo.entity.system;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 多级字典表
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Data
@Accessors(chain = true)
@TableName("sys_mdict")
public class Mdict extends Model<Mdict> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;
    /**
     * 父级编号
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 所有父级编号
     */
    @TableField("parent_ids")
    private String parentIds;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private BigDecimal sort;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_date")
    private Date updateDate;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 删除标记
     */
    @TableField("del_flag")
    private String delFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
