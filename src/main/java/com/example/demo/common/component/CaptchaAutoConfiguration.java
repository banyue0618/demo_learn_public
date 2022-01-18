package com.example.demo.common.component;

import com.example.demo.common.config.CaptchaConfig;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author banyue
 * date 2020-06-07
 */
@Configuration
@EnableConfigurationProperties(CaptchaConfig.class)
public class CaptchaAutoConfiguration {

    @Autowired
    private CaptchaConfig captchaConfig;

    @Bean(name = "captchaProducer")
    public DefaultKaptcha getCaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_BORDER, captchaConfig.getBorder());
        properties.setProperty(Constants.KAPTCHA_BORDER_COLOR, captchaConfig.getBorderColor());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, captchaConfig.getTextProducerFontColor());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, captchaConfig.getTextProducerFontSize());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, captchaConfig.getTextProducerFontNames());
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, captchaConfig.getTextProducerCharLength());
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, captchaConfig.getImageWidth());
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, captchaConfig.getImageHeight());
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, captchaConfig.getNoiseColor());
        //干扰线
        properties.setProperty(Constants.KAPTCHA_NOISE_IMPL, captchaConfig.getNoiseImpl());
        //阴影渲染效果
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, captchaConfig.getObscurificatorImpl());
        properties.setProperty(Constants.KAPTCHA_SESSION_KEY, captchaConfig.getSessionKey());
        properties.setProperty(Constants.KAPTCHA_SESSION_DATE, captchaConfig.getSessionDate());
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
