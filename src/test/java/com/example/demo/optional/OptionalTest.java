package com.example.demo.optional;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author banyue
 * date 2020-08-25
 */
public class OptionalTest {

    public static List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API", "2", "3");
    public static List num = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API", "1", "2");

    public static void main(String[] args) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = Duration.between(simpleDateFormat.parse("2021-05-17 10:16:31").toInstant(), simpleDateFormat.parse("2021-05-28 14:08:31").toInstant()).toMillis();
        long l2 = Duration.between(simpleDateFormat.parse("2021-04-27 17:37:12").toInstant(), simpleDateFormat.parse("2021-05-07 14:33:14").toInstant()).toMillis();
        System.out.println(l);
        System.out.println(l2);
        System.out.println(8*7.5);

        long l3 = 60*60*60*1000 + 330*60*1000 + ((60+43)*60 + 31)*1000 + 511*1000;
        System.out.println(l3);



//        Long i = 1L;
//        System.out.println(++i);

//        double i = Double.valueOf(null);
//        Math.round(i);
//        String itemCode="11440605MB2D102559444011310600201202106241096";
//        System.out.println(itemCode = itemCode.substring(0, itemCode.length() - 12));

//        Instant now = Instant.now();
//        System.out.println(features.stream().anyMatch(e-> num.stream().noneMatch(e::equals)));
//        System.out.println("time:"+Duration.between(now, Instant.now()).toMillis());
//        System.out.println(String.format("%03d%n", 7));
//
//        String value = "";
//
//        String value2 = "111";
//
//        String value3 = null;
//
//        List list = null;

//        System.out.println(Optional.ofNullable(list).filter((str)->str.toString().length() > 4).orElse(features).get(2).toString());
//        Optional.ofNullable(list).filter((str)->str.toString().length() > 4).orElse(features).forEach(e-> System.out.println("--->"+e));
//        Optional.ofNullable(list).filter((str)->str.toString().length() > 4).orElse(features).get(2).forEach(e-> System.out.println("--->"+e));




    }

    @Test
    public void test(){
        System.out.println(String.format("%03d%n", 7));
    }

}
