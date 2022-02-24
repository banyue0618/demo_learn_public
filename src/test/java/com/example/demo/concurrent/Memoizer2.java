package com.example.demo.concurrent;

import java.util.concurrent.*;

/**
 * @ClassName Memoizer2
 * @Description 优雅的高效并发查询设计（单机应用）
 * @Author: zhangsp
 * @date 2022/2/24 10:44
 * @Version 1.0
 */
public class Memoizer2<A, V> implements Query<A, V> {

    private final ConcurrentMap <A, Future <V>> cache = new ConcurrentHashMap <>();

    private final Query<A, V> c;

    public Memoizer2(Query<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws Exception {
        while (true){
            Future<V> f = cache.get(arg);
            if(null == f){
                Callable <V> callable = () -> c.compute(arg);

                FutureTask<V> ft = new FutureTask <>(callable);

                f = cache.putIfAbsent(arg, ft); // 此处解决了并发问题

                if(f == null){
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();// get操作 并发任务查询条件一致时，所有的线程排除执行了run方法的那个线程将会陷入阻塞，等待获取执行run方法这个线程的结果
            } catch (CancellationException e) {
                cache.remove(arg, f);
                // 添加remove()操作的原因：
                // 在调用get方法，可能会抛出CancellationException异常（原因是任务可能被取消，为什么呢），
                // FutureTask是存在生命周期的，当状态为cancel（任务被取消会变成这个状态）时，会在调用run方法时会直接抛异常（查看FutureTask的run方法），
                // 造成代码在while(true)中将会陷入死循环
                // 为了避免死循环这个问题，还有一种解决办法，cache.putIfAbsent(arg, ft); 换成 cache.computeIfAbsent(arg, ft)
                // putIfAbsent，如果 Key 不存在或者对应的值是 null，则将 Value 设置进去，然后返回 null；否则只返回 Map 当中对应的值，而不做其他操作。
                // computeIfAbsent，首先它也是一个线程安全的方法，如果发现 Key 不存在或者对应的值是 null，则调用 Function 来产生一个值，然后将其放入 Map，最后返回这个值；否则的话返回 Map 已经存在的值。
                // 不过还是 putIfAbsent更优雅
            } catch (ExecutionException | InterruptedException e) {
                throw e;
            }
        }

        // 问题，以上操作并没有解决缓存的过期问题、缓存的淘汰问题。这里采用spring-cache
        /**
         * <dependency>
         *     <groupId>org.springframework.boot</groupId>
         *     <artifactId>spring-boot-starter-cache</artifactId>
         * </dependency>
         */




    }
}
