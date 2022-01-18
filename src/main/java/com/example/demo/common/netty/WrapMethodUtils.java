package com.example.demo.common.netty;

import java.lang.reflect.Method;

/**
 * @author banyue
 * date 2020-08-13
 */
public class WrapMethodUtils {

    /**
     * 获取 method的元数据信息
     *
     * @param interfaceClass
     * @param method
     * @param args
     * @return
     */
    public static MethodInvokeMeta readMethod(Class interfaceClass, Method method, Object[] args) {
        MethodInvokeMeta mim = new MethodInvokeMeta();
        mim.setInterfaceClass(interfaceClass);
        mim.setArgs(args);
        mim.setMethodName(method.getName());
        mim.setReturnType(method.getReturnType());
        Class<?>[] parameterTypes = method.getParameterTypes();
        mim.setParameterTypes(parameterTypes);
        return mim;
    }

}
