package com.almond.common.exception;


/**
 * 异常状态码如下
 * 通用类型 10开头 001代表校验异常
 * 11: 商品异常
 * 12: 订单模块
 * 13: 购物车模块
 * 14: 仓储
 */

public enum ErrorCodeEnum {
    UNKNOWN_EXCEPTION(10000, "未知异常"),
    VALID_EXCEPTION(10001, "参数格式异常");

    private Integer code;
    private String message;

    private ErrorCodeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return this.code;
    }
    public String getMessage(){
        return this.message;
    }
}
