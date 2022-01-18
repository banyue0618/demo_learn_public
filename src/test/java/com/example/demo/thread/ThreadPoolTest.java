package com.example.demo.thread;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * @author banyue
 * date 2020-07-20
 */
public class ThreadPoolTest {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Set<String> dates = new HashSet<>();


        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Future<?> submit = executorService.submit(() -> {
                new TicketTest().run();
//                String date = new ThreadPoolTest().date2(finalI);
//                dates.add(date);
//                System.out.println(dates.size());
            });
        }
        // 关闭线程池，此种关闭方式不再接受新的任务提交，等待现有队列中的任务全部执行完毕之后关闭
        executorService.shutdown();
    }

    private String date(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        return ThreadSafeDateFormatter.dateFormatThreadLocal.get().format(date);
    }
    private String date2(int seconds) {
        // 参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        return DATE_FORMAT.format(date);
    }

}
class ThreadSafeDateFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


}
