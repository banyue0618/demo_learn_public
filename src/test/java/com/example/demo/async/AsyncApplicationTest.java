package com.example.demo.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * @author banyue
 * date 2020-09-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class AsyncApplicationTest {


    @Test
    public void asyncTest() throws Exception{
        testAsync();
        testAsync1();
        testAsync2();
        testAsync3();

    }

    @Async
    public void testAsync(){
        System.out.println("------>Async");
    }

    @Async
    public void testAsync1(){
        System.out.println("------>Async1");
    }

    @Async
    public void testAsync2(){
        System.out.println("------>Async2");
    }

    @Async
    public void testAsync3(){
        System.out.println("------>Async3");
    }

}
