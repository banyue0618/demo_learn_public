package com.example.demo.service.system;

import com.example.demo.entity.system.AccountEntity;
import com.example.demo.entity.system.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author banyue
 * @since 2020-03-31
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查找用户
     * left join出所拥有角色 角色id 已逗号拼接形式返回
     * @param userName
     * @return
     */
    AccountEntity loadUserByUsername(String userName);
}
