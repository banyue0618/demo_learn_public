package com.example.demo.classLoader;

import com.example.demo.entity.system.User;

/**
 * @ClassName ClassLoaderTest
 * @Description TODO 双亲委派机制测试
 * @Author: zhangsp
 * @date 2022/1/25 15:55
 * @Version 1.0
 */
public class ClassLoaderTest extends ClassLoader{

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.getClass().getClassLoader());

        System.out.println(user.getClass().getClassLoader().getParent());

        System.out.println(user.getClass().getClassLoader().getParent().getParent());

    }


    @Override
    protected Class <?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
