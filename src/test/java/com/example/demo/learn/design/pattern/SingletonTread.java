package com.example.demo.learn.design.pattern;

/**
 * 多线程下线程安全的的懒汉单例设计模式
 *
 *
 * @author banyue
 * date 2020-04-12
 */
public class SingletonTread {

    private static SingletonTread singletonTread;

    private SingletonTread(){}

    //代码根据指令重排序
    public SingletonTread getInstance(){
        if(singletonTread == null){
            synchronized (SingletonTread.class){
                if(singletonTread == null){
                    singletonTread = new SingletonTread();
                }
            }
        }
        return singletonTread;

    }

    /**
     * 当多线程并发的时候，假如第一个线程成功获取锁并进入if块执行singleton=new Singleton()，
     *
     * 这句代码我们可以看成三步操作：
     *
     *     在堆内存中划分一个Singleton对象实体的空间
     *     初始化堆内存中对象实例的数据（字段等）
     *     将singleton变量通过指针指向生成的对象实体
     *
     * 这个时候因为指令重排序，可能在步骤2还没有执行完的时候，步骤3已经执行完了，
     *
     * 这时候singleton变量已经不为null，此时如果有并发的线程执行getInstance()方法，将获取到一个没有初始化完成的Singleton对象从而引发错误。
     *
     *
     * 我们给singleton变量添加关键字volatile得到懒汉模式V3.0:
     *
     *private static volatile SingletonTread singletonTread;
     *
     *
     * 这里用volatile修饰singleton并不是用了volatile的可见性，而是用了java内存模型的“先行发生”(happens-before)原则的其中一条：
     *
     * Volatile变量规则:对一个volatile变量的写操作先行发生于后面对这个变量的读操作，这里的“后面”指时间上的先后顺序。
     *
     * 这样一来就能禁止指令重排序，确保singleton对象是在初始化完成后才能被读到。
     *
     *
     * jvm规范规定有且只有以下7种情况下会进行类加载的初始化阶段：
     *
     *     使用new关键字实例化对象的时候
     *     设置或读取一个类的静态字段（被final修饰，已在编译器把结果放入常量池的静态字段除外）的时候
     *     调用一个类的静态方法的时候
     *     使用java.lang.reflect包的方法对类进行反射调用的时候
     *     初始化一个类的子类（会首先初始化父类）
     *     当虚拟机启动的时候，初始化包含main方法的主类
     *     当使用jdk1.7的动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化
     *
     */


}
