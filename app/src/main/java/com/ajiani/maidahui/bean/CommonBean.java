package com.ajiani.maidahui.bean;

public class CommonBean<T> {
    private  int code;
    private  T data;

    public CommonBean(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public CommonBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
