package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.support.excel.ExcelKit;
import com.example.demo.common.vo.ResultVo;
import com.example.demo.entity.request.system.UserDto;
import com.example.demo.entity.system.User;
import com.example.demo.service.system.UserService;
import com.example.demo.service.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author banyue
 * date 2020-03-26
 */
@RestController
public class HelloController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(Thread.currentThread().getName());
        testAsync();
        testAsync1();
        testAsync2();
        testAsync3();
        return "Hello Spring Boot!";
    }


    @Async(value = "asyncTaskExecutor")
    public void testAsync(){
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName());
        System.out.println("------>Async");
    }

    @Async(value = "asyncTaskExecutor")
    public void testAsync1(){
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName());
        System.out.println("------>Async1");
    }

    @Async(value = "asyncTaskExecutor")
    public void testAsync2(){
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName());
        System.out.println("------>Async2");
    }

    @Async(value = "asyncTaskExecutor")
    public void testAsync3(){
        System.out.println(System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName());
        System.out.println("------>Async3");
    }

//    @ModelAttribute
    public void setHttpServletResponse(HttpServletResponse response){
        try {
            response.addHeader("Content-Disposition", "attachment;filename="+new String( "用户信息".getBytes("utf-8"), "ISO8859-1" )+ExcelKit.xls);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping( value = "/downLoadUser", method = RequestMethod.GET, produces = "application/json")
    public ResultVo downLoadUser(HttpServletRequest request, HttpServletResponse response){
        ExcelKit excelKit = new ExcelKit(UserDto.class);
        try {
            excelKit.exportExcel(userService.selectList(new EntityWrapper<User>().orderBy("no")), "用户信息", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok();
    }


    @RequestMapping( value = "/importData", method = RequestMethod.GET, produces = "application/json")
    public ResultVo importData(HttpServletRequest request, HttpServletResponse response){
        ExcelKit excelKit = new ExcelKit(Test.class);

        List <Test> data = excelKit.importExcel("sheet21", "");


        return ok();
    }


}
