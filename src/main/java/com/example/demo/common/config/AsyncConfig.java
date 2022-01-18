package com.example.demo.common.config;

import org.springframework.aop.interceptor.AsyncExecutionAspectSupport;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.*;

/**
 * @author banyue
 * date 2020-09-02
 */
@Configuration
public class AsyncConfig {

    /**
     * 默认最大并发数<br>
     */
    private static final int DEFAULT_MAX_CONCURRENT = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 线程池名称格式
     */
    private static final String THREAD_POOL_NAME = "ExternalConvertProcessPool-%d";

    /**
     * 默认队列大小
     */
    private static final int DEFAULT_SIZE = 30;

    /**
     * 默认线程存活时间
     */
    private static final int DEFAULT_KEEP_ALIVE = 60;


    @Bean(name = AsyncExecutionAspectSupport.DEFAULT_TASK_EXECUTOR_BEAN_NAME )
    public Executor getAsyncExecutor(){
        System.out.println("------------------------------->");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(DEFAULT_MAX_CONCURRENT);
        executor.setQueueCapacity(DEFAULT_SIZE);
        executor.setKeepAliveSeconds(DEFAULT_KEEP_ALIVE);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }




    /**
     * 线程池工具类
     *
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @param queueSize       队列容量
     * @return
     */
    public static ExecutorService getExecutorService(int corePoolSize, int maximumPoolSize, int queueSize) {
        ExecutorService pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueSize),new NamedThreadFactory(THREAD_POOL_NAME), new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }

    class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            System.out.println("class#method: " + method.getDeclaringClass().getName() + "#" + method.getName());
            System.out.println("type        : " + ex.getClass().getName());
            System.out.println("exception   : " + ex.getMessage());
        }
    }
}
