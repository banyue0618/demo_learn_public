package com.example.demo.service.impl.system;

import com.example.demo.entity.system.AccountEntity;
import com.example.demo.entity.system.User;
import com.example.demo.mapper.system.UserDao;
import com.example.demo.service.system.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author banyue
 * @since 2020-03-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    /**
     * 根据用户名查找用户
     * left join出所属角色
     *
     * @param userName
     * @return
     */
    @Override
    public AccountEntity loadUserByUsername(String userName) {
        return this.baseMapper.loadUserByUsername(userName);
    }
}
