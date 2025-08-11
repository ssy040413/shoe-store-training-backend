package com.xys.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("通用API数据模型")
public class ResponseResult<T> {
    public static final int SUCCESS = 200;
    public static final int FAILED = 201;

    public static final int WRONG = 500;
    public static final int ERROR = -1;

    @ApiModelProperty("状态码: 200操作成功, 201操作失败, 500操作错误, -1后台错误")
    private int code;

    @ApiModelProperty("状态消息")
    private String msg;

    @ApiModelProperty("结果集数据")
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T data) {
        return success("success", data);
    }

    public static <T> ResponseResult<T> successmsg(String msg) {
        return success(msg, null);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(ResponseResult.SUCCESS, msg, data);
    }

    public static <T> ResponseResult<T> success(int code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

    public static <T> ResponseResult<T> failure(String msg) {
        return failure(ResponseResult.ERROR, msg);
    }

    public static <T> ResponseResult<T> failure(int code, String msg) {
        return new ResponseResult<>(code, msg, null);
    }

    public boolean isOk() {
        return code == SUCCESS;
    }

    public boolean isError() {
        return !isOk();
    }
}

