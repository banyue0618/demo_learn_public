package com.banyue.dubbo.user.controller;

import com.banyue.dubbo.common.user.dto.User;
import com.banyue.dubbo.user.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/27 9:03
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        User userDO = userService.getOne(id);
        return userDO;
    }

}
