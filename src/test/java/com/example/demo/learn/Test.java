package com.example.demo.learn;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * @author banyue
 * date 2020-04-09
 */
public class Test {

    //构造代码块比构造器优先执行
    {
        System.out.println("我是构造代码块");
    }

//    public Test() {
//        System.out.println("我是构造方法");
//    }

    static {
        System.out.println("我是静态代码块。无论new多少实例，我仅执行一次");
    }

    final static int i = 6;

    final static String language=  "Java";

    //类加载委托机制作用：避免重复加载类（节省内存），保护核心类
    public static void main(String[] args) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue("{\"x-tif-paasid\":\"fsgj\",\"x-tif-passToken\":\"1131236be90d4c10971163af1faab035\",\"remote-url\":\"http://19.15.75.180:8581/GatewayMsg/http/api/proxy/invoke\"}", Map.class);

        System.out.println("----" + (124>>2));
        System.out.println("----" + (400>>2));

        int i = -1;
        int k = 0;
        k = Integer.toBinaryString(i).length();
        System.out.println(Integer.toBinaryString(i));
        System.out.println(k);
//        i >>>=10;
//        System.out.println(Integer.toBinaryString(i));
        i<<=10;
        System.out.println(Integer.toBinaryString(i));
        System.out.println("--------------------------------");
        long l = -1;
        k = Long.toBinaryString(l).length();
        System.out.println(k);
        System.out.println(Long.toBinaryString(l));

//        Singleton.play();
//        Singleton.getInstance();

//        float num= (float)0/10;
//        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
//        String s = df.format(num*100);//返回的是String类型
//        System.out.println(s+"%");

//        StringBuffer stringBuffer = null;
//        Map map = new HashMap();
//        System.out.println(s+"%");
//
//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        System.out.println(int.class.isPrimitive());
//        boolean result = RegularKit.isChinaPhoneLegal("14688556699");
//        new Test();
//        System.out.println(result);

    }

    private String calculator(int a, int b){
        float num= (float)a/b;
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num * 100) + "%";
    }


}
