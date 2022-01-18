package com.example.demo.common.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author banyue
 * date 2020-05-13
 */
@Component
public class SpringKit implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringKit.applicationContext = applicationContext;
    }

    /**
     * 通过名称获取指定bean
     * @param id
     * @param <T>
     * @return
     */
    public static <T> T getBean(String id) throws Exception{
        try {
            return applicationContext.containsBean(id) ? (T)applicationContext.getBean(id) : null;
        } catch (BeansException e) {
            throw new Exception("not found bean id: " + id);
        }
    }

    /**
     * 根据类型获取bean
     * @param baseType
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> baseType){
        return applicationContext.getBeansOfType(baseType);
    }

}
