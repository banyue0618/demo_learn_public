package com.example.demo.service.impl.system;

import com.example.demo.entity.system.UserRole;
import com.example.demo.mapper.system.UserRoleDao;
import com.example.demo.service.system.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色 服务实现类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
