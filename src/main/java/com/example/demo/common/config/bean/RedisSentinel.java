package com.example.demo.common.config.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author banyue
 * date 2020-05-27
 */
@Component
@ConfigurationProperties(prefix = "redis")
@PropertySource("redis.properties")
public class RedisSentinel {
}
