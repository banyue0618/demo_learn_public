package com.example.demo.controller.system;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.vo.ResultVo;
import com.example.demo.entity.request.system.UserDto;
import com.example.demo.entity.system.User;
import com.example.demo.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author banyue
 * @since 2020-03-31
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResultVo listUser(@Validated UserDto userDto, BindingResult result){

        List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> collect = fieldErrors.stream().map(o->o.getDefaultMessage()).collect(Collectors.toList());
        if(collect.size() > 0){
            return error("参数有误", collect);
        }

        //分页
        Page page = new Page(pageNo,pageSize);
        //条件构造器
        EntityWrapper entityWrapper = new EntityWrapper<User>();
        Page users = userService.selectMapsPage(page, entityWrapper.isNotNull("user_Type"));
        return ok(users);
    }

}

