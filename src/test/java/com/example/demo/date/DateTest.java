package com.example.demo.date;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @ClassName DateTest
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/2/25 9:49
 * @Version 1.0
 */
public class DateTest {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    public static void main(String[] args) {
        Instant now = Instant.now();
        Date n = Date.from(now);

        System.out.println(dateFormatThreadLocal.get().format(n));
        Instant instant = now.plusSeconds(3);

        Date from = Date.from(instant);
        System.out.println(dateFormatThreadLocal.get().format(from));
    }
}
