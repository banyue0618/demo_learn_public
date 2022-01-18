package com.example.demo.entity.system;

import com.baomidou.mybatisplus.annotations.TableId;
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
 * 角色-机构
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_office")
public class RoleOffice extends Model<RoleOffice> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    @TableId("role_id")
    private String roleId;
    /**
     * 机构编号
     */
    @TableField("office_id")
    private String officeId;


    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
