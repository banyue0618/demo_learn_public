package com.example.demo.jdk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author banyue
 * date 2020-07-07
 */
public class LamdbaTest {
    public static void main(String[] args) {

//        foreachPrint();

        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();

        foreachPrintWithCondition();

        System.out.println(new LamdbaTest().getCheckResultMuti("订单", 2));

    }

    public static void foreachPrint(){

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

//        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    public static void foreachPrintWithCondition(){

        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.toString().length() > 4);

    }


    public static void filter(List f, Predicate condition){
        f.stream().filter(e -> condition.test(e)).forEach(e -> System.out.println(e + " "));
    }




    private static Map<String, Function<String, String>> checkResultDispatcherMuti = new HashMap<>();

    /**
     * 初始化 业务逻辑分派Map 其中value 存放的是 lambda表达式
     */
    static {
        checkResultDispatcherMuti.put("key_订单1", order -> String.format("对%s执行业务逻辑1", order));
        checkResultDispatcherMuti.put("key_订单1_订单2", order -> String.format("对%s执行业务逻辑2", order));
        checkResultDispatcherMuti.put("key_订单1_订单2_订单3", order -> String.format("对%s执行业务逻辑3", order));
    }

    public String getCheckResultMuti(String order, int level) {
        //写一段生成key的逻辑：
        String ley = getDispatcherKey(order, level);

        Function<String, String> result = checkResultDispatcherMuti.get(ley);
        if (result != null) {
            //执行这段表达式获得String类型的结果
            return result.apply(order);
        }
        return "不在处理的逻辑中返回业务错误";
    }

    /**
     * 判断条件方法
     */
    private String getDispatcherKey(String order, int level) {
        StringBuilder key = new StringBuilder("key");
        for (int i = 1; i <= level; i++) {
            key.append("_" + order + i);
        }
        return key.toString();
    }

}
