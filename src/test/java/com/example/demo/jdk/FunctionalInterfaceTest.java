package com.example.demo.jdk;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author banyue
 * date 2020-09-07
 */
public class FunctionalInterfaceTest {

    @Test
    public void printTest(){
        printMsg((s)-> System.out.println(s),"听朋友说「烟雨星空」公众号不仅文章好看，还免费送程序员福利，我心动了");
    }



    public void printMsg(Consumer<String> consumer, String msg){
        //消费型，只有传入参数，没有返回值
        consumer.accept(msg);
    }



    @Test
    public void test3(){
        //返回一个 0~99 的随机数
        Integer content = getContent(() -> new Random().nextInt(100));
        System.out.println(content);
    }

    public Integer getContent(Supplier<Integer> supplier){
        //供给型，传入参数为空，带返回值
        return supplier.get();
    }




    @Test
    public void test4(){
        //传入一个字符串，然后把它都转换成大写字母。
        System.out.println(transfer((str) -> str.toUpperCase(), "My wechat : mistyskys"));
    }

    public String transfer(Function<String,String> func, String str){
        // 函数型，传入一个参数，对其进行处理之后，返回一个结果
        return func.apply(str);
    }


    @Test
    public void test5(){
        //定义一个list，用来做筛选
        ArrayList<String> list = new ArrayList<>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("jerry");
        list.add("tom");
        //筛选出集合中，字符串长度大于 3 的，并加入到结果集。
        List<String> filterResult = filter((str) -> str.length() > 3, list);
        System.out.println(filterResult.toString());
    }

    public List<String> filter(Predicate<String> predicate, List<String> list){
        List<String> result = new ArrayList<>();
        for (String str : list) {
            //断言型，传入一个参数，并返回true或者false。
            //这里的逻辑是，若断言为真，则把当前的字符串加入到结果集中
            if(predicate.test(str)){
                result.add(str);
            }
        }
        return result;
    }

    @Test
    public void Test6(){
        LocalDate localDate1 = LocalDate.now();
        System.out.println(localDate1);  //2020-09-05
        LocalDate localDate2 = LocalDate.of(2020, 9, 5);
        System.out.println(localDate2); //2020-09-05
        LocalDate localDate3 = LocalDate.parse("2020-09-05");
        System.out.println(localDate3); //2020-09-05
    }


    @Test
    public void Test7(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
// 1. 日期时间转化为字符串。有两种方式
        String format = dtf.format(LocalDateTime.now());
        System.out.println(format); // 2020-09-05 23:02:02
    }

}
