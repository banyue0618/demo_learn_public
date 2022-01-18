package com.example.demo.service.impl.system;

import com.example.demo.entity.system.RoleMenu;
import com.example.demo.mapper.system.RoleMenuDao;
import com.example.demo.service.system.RoleMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色-菜单 服务实现类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

    /**
     * 查询所有的角色菜单列表，返回角色id与menu直接匹配关系
     *
     * @return
     */
    @Override
    public List<RoleMenu> selectMenuRoleListForGlobal() {
        return this.baseMapper.selectMenuRoleListForGlobal();
    }
}
