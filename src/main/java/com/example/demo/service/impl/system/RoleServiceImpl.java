package com.example.demo.service.impl.system;

import com.example.demo.entity.system.Role;
import com.example.demo.mapper.system.RoleDao;
import com.example.demo.service.system.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}
