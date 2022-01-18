package com.example.demo.common.controller;

import com.example.demo.common.contants.CommonConstant;
import com.example.demo.common.support.StrKit;
import com.example.demo.common.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author banyue
 * date 2020-03-31
 */
@Slf4j
public class BaseController {

    /**
     * 用于分页页码
     */
    protected int pageNo;

    /**
     * 用于分页单页数量
     */
    protected int pageSize;

    /**
     * 请求
     */
    protected HttpServletRequest request;

    /**
     * 响应
     */
    protected HttpServletResponse response;

    @ModelAttribute
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.pageSize = getParaToInt("pageSize", 15);
        this.pageNo = getParaToInt("pageNo", 1);
    }

    public ResultVo ok(String message, Object obj) {
        return result(CommonConstant.RESULT_SUCCESS_CODE, message, obj);
    }

    public ResultVo ok(Object obj) {
        return result(CommonConstant.RESULT_SUCCESS_CODE, CommonConstant.RESULT_SUCCESS_MESSAGE, obj);
    }

    public ResultVo ok() {
        return result(CommonConstant.RESULT_SUCCESS_CODE, CommonConstant.RESULT_SUCCESS_MESSAGE, null);
    }

    public ResultVo error(String message, Object obj) {
        return result(CommonConstant.RESULT_ERROR_CODE, message, obj);
    }

    public ResultVo error(String message) {
        return result(CommonConstant.RESULT_SUCCESS_CODE, message, null);
    }

    public ResultVo error() {
        return result(CommonConstant.RESULT_SUCCESS_CODE, CommonConstant.RESULT_ERROR_MESSAGE, null);
    }

    public ResultVo result(int statusCode, String message, Object obj) {
        return new ResultVo(statusCode, message, obj);
    }

    public String getPara(String name) {
        return request.getParameter(name);
    }

    public String getPara(String name, String defaultValue) {
        String result = request.getParameter(name);
        return result != null && !"".equals(result) ? result : defaultValue;
    }

    public Integer getParaToInt(String name) {
        return toInt(request.getParameter(name), null);
    }

    public Integer getParaToInt(String name, Integer defaultValue) {
        return toInt(request.getParameter(name), defaultValue);
    }

    private Integer toInt(String value, Integer defaultValue) {
        try {
            if (StrKit.isBlank(value)){
                return defaultValue;
            }
            value = value.trim();
            if (value.startsWith("N") || value.startsWith("n")){
                return -Integer.parseInt(value.substring(1));
            }
            return Integer.parseInt(value);
        }
        catch (Exception e) {
            throw new RuntimeException("Can not parse the parameter \"" + value + "\" to Integer value.");
        }
    }
}
