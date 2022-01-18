package com.example.demo.learn.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.ref.SoftReference;
import java.text.DecimalFormat;
import java.util.HashSet;

public class SoftReferenceTest {

    public static void main(String[] args) {
//        showInitialMemoryInfo();
//        softReference();
        softReferenceOverHeadLimit();
    }


    static class SmallSoftObject2 {
        byte[] data = new byte[1024];
    }

    private static void softReferenceOverHeadLimit() {
        int capacity = 1024 * 10;
        HashSet<SoftReference<SmallSoftObject2>> set = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            set.add(new SoftReference<>(new SmallSoftObject2()));
        }
        System.out.println("End");
    }

    private static void softReference() {
        printSplitLine();

        //创建软引用对象
        SoftReference<SoftObject> reference = new SoftReference<>(new SoftObject());
        System.out.println(getCurrentMemoryInfo() + ",当前软引用对象:" + reference.get());

        printSplitLine();

        //创建强引用对象
        SoftObject object = new SoftObject();
        printSplitLine();
        System.out.println(getCurrentMemoryInfo() + ",当前软引用对象:" + reference.get());

        //创建强引用对象
        SoftObject object2 = new SoftObject();
        printSplitLine();
        System.out.println(getCurrentMemoryInfo() + ",当前软引用对象:" + reference.get());
    }



    private static void showInitialMemoryInfo() {
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        System.out.println("最大可用内存:" + toMB(mbean.getHeapMemoryUsage().getMax()));
        for (MemoryPoolMXBean mxBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println("Name:" + mxBean.getName()
                    + ",Type:" + mxBean.getType()
                    + ",Size:" + toMB(mxBean.getUsage().getMax()));
        }
    }

    public static String toMB(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        }
        else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }

    /**
     * 获取当前内存信息
     *
     * @return
     */
    private static String getCurrentMemoryInfo() {
        return "当前最大可用内存:" + toMB(Runtime.getRuntime().maxMemory()) + ",当前空闲内存:" + toMB(Runtime.getRuntime().freeMemory());
    }

    /**
     * 打印分隔行
     */
    private static void printSplitLine() {
        System.out.println("========================================================================");
    }

    static class SoftObject{
        byte[] data = new byte[50*1024*1024];
    }

    static class SmallSoftObject {
        byte[] data = new byte[1024];
    }

}
