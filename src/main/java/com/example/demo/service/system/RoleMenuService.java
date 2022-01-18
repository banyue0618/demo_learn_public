package com.example.demo.service.system;

import com.example.demo.entity.system.Menu;
import com.example.demo.entity.system.RoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色-菜单 服务类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 查询所有的角色菜单列表，返回角色id与menu直接匹配关系
     * @return
     */
    List<RoleMenu> selectMenuRoleListForGlobal();
}
