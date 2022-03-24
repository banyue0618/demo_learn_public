package com.example.demo.learn.design.dProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName DynamicProxy
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/22 21:27
 * @Version 1.0
 */
public class DynamicProxy implements InvocationHandler {

    private Class <?>[] clazz;

    private ClassLoader loader;

    // 被代理的实例
    private Object object;

    public Object newProxy(Object object) {
        this.object = object;
        this.clazz = object.getClass().getInterfaces();
        this.loader = object.getClass().getClassLoader();
        return Proxy.newProxyInstance(loader, clazz, this);
    }

    /**
     *
     * @param proxy 这个参数是jdk返回的，是当前被代理类的真实代理，注意，不能调用该参数的toString等方法，否则会造成栈溢出，
     *              因为该真实代理的底层是通过调用当前DynamicProxy的代理方法，造成循环调用
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object, args);
        return result;
    }
}
