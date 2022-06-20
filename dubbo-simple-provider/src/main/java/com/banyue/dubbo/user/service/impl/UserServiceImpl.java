package com.banyue.dubbo.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.banyue.dubbo.user.service.UserDao;
import com.banyue.dubbo.common.user.dto.User;
import com.banyue.dubbo.common.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/27 9:07
 * @Version 1.0
 */
@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        try {
            User user = userDao.findById(id).get();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
