package com.example.demo.common.contants;

import lombok.Getter;

/**
 * @author banyue
 * date 2020-06-01
 */
@Getter
public enum ResultCode {

    /**
     * 操作成功
     */
    OK(200, "操作成功"),

    /**
     * 未知异常
     */
    SERVER_ERROR(500, "服务器出错啦");
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
