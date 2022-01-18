package com.example.demo.mapper.system;

import com.example.demo.entity.system.RoleMenu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色-菜单 Mapper 接口
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
public interface RoleMenuDao extends BaseMapper<RoleMenu> {

    /**
     * 查询所有的角色菜单列表，返回角色id与menu直接匹配关系
     * @return
     */
    @Select("SELECT r.enname AS roleId,t.menu_id as menuId from sys_role r left join ( SELECT rm.role_id , m.href as menu_id  FROM sys_role_menu rm RIGHT JOIN sys_menu m on rm.menu_id = m.id where TRIM(m.href) != '') t on r.id = t.role_id where TRIM(t.menu_id) != ''")
    List<RoleMenu> selectMenuRoleListForGlobal();
}
