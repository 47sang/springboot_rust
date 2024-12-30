package com.example.demo.utils;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ApiResult<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ApiResult<T> success() {
        return success(null);
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(200);
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> error(int code, String msg) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        return apiResult;
    }
    public static <T> ApiResult<T> error( String msg) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(500);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
