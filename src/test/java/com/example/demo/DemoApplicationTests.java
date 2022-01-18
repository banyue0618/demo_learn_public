package com.example.demo;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.entity.system.User;
import com.example.demo.service.system.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Junit4运行环境
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {


    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {

        //分页
        Page page = new Page(1,15);
        //条件构造器
        EntityWrapper entityWrapper = new EntityWrapper<User>();
        Page users = userService.selectMapsPage(page, entityWrapper.isNotNull("user_Type"));
        System.out.println("hello world");
        System.out.println(users.toString());
    }

}
