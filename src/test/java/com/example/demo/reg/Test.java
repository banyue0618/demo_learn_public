package com.example.demo.reg;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String a = "4406001";
        System.out.println(a.substring(0, 6));

//        String a = "202111111111";
//        String b = "20211111111";
//        String regEx = "^(\\d{12})$";
//        String regEx2 = "^[0-9_]+$";//纯数字
//        Pattern pattern = Pattern.compile(regEx);
//        Matcher matcher = pattern.matcher(a);
//        boolean rs = matcher.matches();
//        System.out.println(rs);

//        System.out.println(BigDecimal.valueOf(630).multiply(BigDecimal.valueOf(60*60*1000)).divideAndRemainder(BigDecimal.valueOf(25200000))[0]);
//        System.out.println(BigDecimal.valueOf(630).multiply(BigDecimal.valueOf(60*60*1000)).divide(BigDecimal.valueOf(25200000), BigDecimal.ROUND_CEILING));
//        String.valueOf(596 * 60 * 60 * 1000)
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        LocalDateTime plus = localDateTime.plus(90, ChronoUnit.DAYS);

        System.out.println(plus);




    }

}
