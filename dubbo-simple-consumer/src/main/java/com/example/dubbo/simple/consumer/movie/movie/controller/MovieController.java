package com.example.dubbo.simple.consumer.movie.movie.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.banyue.dubbo.common.user.dto.User;
import com.banyue.dubbo.common.user.service.UserService;
import com.example.dubbo.simple.consumer.movie.movie.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName MovieController
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/27 9:44
 * @Version 1.0
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserConfig userConfig;

    @Reference
    private UserService userService;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        User userDO = restTemplate.getForObject(userConfig.getUserServiceURL() + id, User.class);
        return userDO;
    }


    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id){
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }



}
