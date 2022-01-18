package com.example.demo.common.config;

import com.example.demo.common.component.NotResponseBody;
import com.example.demo.common.vo.ResultVo;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName ResponseControllerAdvice
 * @Description 数据返回统一包装按段
 * @Author: zhangsp
 * @date 2022/1/18 10:52
 * @Version 1.0
 */
@RestControllerAdvice(basePackages = {"com.example.demo.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice <Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class <? extends HttpMessageConverter <?>> converterType) {
        // 如果接口返回的类型本身就是ResultVO那就没有必要进行额外的操作，返回false
        // 如果方法上加了我们的自定义注解也没有必要进行额外的操作
        return !(returnType.getParameterType().equals(ResultVo.class) || returnType.hasMethodAnnotation(NotResponseBody.class));

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class <? extends HttpMessageConverter <?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return null;
    }
}
