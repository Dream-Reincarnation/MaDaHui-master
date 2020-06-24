package com.ajiani.maidahui.bean;

/**
 * Created by asd on 2019/1/17.
 */

public class BaseBean<T> {
    public int code;
    public String info;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
