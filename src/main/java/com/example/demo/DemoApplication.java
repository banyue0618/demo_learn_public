package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})  改成无需连接数据库

/**
 * SpringBoot的主配置类
 * @author banyue
 * date 2020-03-26
 */

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {




        System.out.println("----------------CommandLineRunner 钩子----------------");
    }
}
