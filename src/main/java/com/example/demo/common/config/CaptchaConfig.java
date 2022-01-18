package com.example.demo.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author banyue
 * date 2020-06-07
 */
@Configuration
@ConfigurationProperties(prefix = "kaptcha")
@Getter
@Setter
public class CaptchaConfig {

    private String border;
    private String borderColor;
    private String textProducerFontColor;
    private String textProducerFontSize;
    private String textProducerFontNames;
    private String textProducerCharLength;
    private String imageWidth;
    private String imageHeight;
    private String noiseColor;
    private String noiseImpl;
    private String obscurificatorImpl;
    private String sessionKey;
    private String sessionDate;

}
