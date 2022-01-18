package com.example.demo.common.contants;

import com.example.demo.common.vo.ResultVo;

/**
 * @author banyue
 * date 2020-06-02
 */
public class ResponseBean {

    public static ResultVo ok(String message, Object obj) {
        return result(CommonConstant.RESULT_SUCCESS_CODE, message, obj);
    }

    public static ResultVo ok(Object obj) {
        return result(CommonConstant.RESULT_SUCCESS_CODE, CommonConstant.RESULT_SUCCESS_MESSAGE, obj);
    }

    public static ResultVo ok() {
        return result(CommonConstant.RESULT_SUCCESS_CODE, CommonConstant.RESULT_SUCCESS_MESSAGE, null);
    }

    public static ResultVo error(String message, Object obj) {
        return result(CommonConstant.RESULT_ERROR_CODE, message, obj);
    }

    public static ResultVo error(String message) {
        return result(CommonConstant.RESULT_ERROR_CODE, message, null);
    }

    public static ResultVo error() {
        return result(CommonConstant.RESULT_ERROR_CODE, CommonConstant.RESULT_ERROR_MESSAGE, null);
    }

    public static ResultVo error(int statusCode, String message) {
        return result(statusCode, message, null);
    }


    public static ResultVo result(int statusCode, String message, Object obj) {
        return new ResultVo(statusCode, message, obj);
    }

}
