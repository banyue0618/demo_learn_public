package com.example.demo.common.exception;

import com.example.demo.common.contants.ResultCode;
import com.example.demo.common.vo.ResultVo;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

/**
 * @ClassName ExceptionControllerAdvice
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/1/18 10:34
 * @Version 1.0
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo <String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        // 从异常对象中拿到错误信息
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        // 参数的Class对象，等下好通过字段名称获取Field对象
        Class<?> parameterType = e.getParameter().getParameterType();

        // 拿到错误的字段名称
        String fieldName = e.getBindingResult().getFieldError().getField();

        Field field = parameterType.getDeclaredField(fieldName);

        // 获取Field对象上的自定义注解
        ExceptionCode annotation = field.getAnnotation(ExceptionCode.class);

        // 有注解的话就返回注解的响应信息
        if (annotation != null) {
            return new ResultVo <>(annotation.value(),annotation.message(),defaultMessage);
        }

        // 没有注解就提取错误提示信息进行返回统一错误码
        return new ResultVo <>(ResultCode.SERVER_ERROR.getCode(), defaultMessage);
    }
}
