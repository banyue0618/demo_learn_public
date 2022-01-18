package com.example.demo.common.config.security.handler;

import com.example.demo.common.contants.ResponseBean;
import com.example.demo.common.vo.ResultVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author banyue
 * date 2020-05-20
 * 认证失败处理器
 */
@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise
     * returns a 401 error code.
     * <p>
     * If redirecting or forwarding, {@code saveException} will be called to cache the
     * exception for use in the target view.
     *
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        super.onAuthenticationFailure(request, response, exception);
        response.setContentType("application/json;charset=utf-8");
        ResultVo respBean;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            respBean = ResponseBean.error(HttpServletResponse.SC_UNAUTHORIZED, "账户名或者密码输入错误!");
        } else if (exception instanceof LockedException) {
            respBean = ResponseBean.error(HttpServletResponse.SC_UNAUTHORIZED,"账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            respBean = ResponseBean.error(HttpServletResponse.SC_UNAUTHORIZED,"密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            respBean = ResponseBean.error(HttpServletResponse.SC_UNAUTHORIZED,"账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            respBean = ResponseBean.error(HttpServletResponse.SC_UNAUTHORIZED,"账户被禁用，请联系管理员!");
        } else {
            respBean = ResponseBean.error(HttpServletResponse.SC_UNAUTHORIZED,"登录失败!");
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(objectMapper.writeValueAsString(respBean));
    }
}
