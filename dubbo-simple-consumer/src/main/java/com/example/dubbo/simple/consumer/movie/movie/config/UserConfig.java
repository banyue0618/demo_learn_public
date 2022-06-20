package com.example.dubbo.simple.consumer.movie.movie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UserConfig
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/27 10:10
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "user")
@Data
public class UserConfig {

    private String userServiceURL;
}
