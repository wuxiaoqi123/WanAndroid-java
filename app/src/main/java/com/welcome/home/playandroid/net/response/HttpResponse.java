package com.welcome.home.playandroid.net.response;

/**
 * Created by wuxiaoqi on 2018/4/23.
 * 公共json
 */

public class HttpResponse<T> {

    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
