package com.ajiani.maidahui.http;

/**
 * 项目名：MyViewDemo2
 * 包名：  com.jiyun.firstnavigation.http
 * 文件名：ApiException
 * 创建者：liangxq
 * 创建时间：2019/1/18  9:02
 * 描述：TODO
 */
public class ApiException extends Throwable {
    private int code;
    private String info;

    public ApiException(int code, String message) {
        this.code = code;
        this.info = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return info;
    }

    public void setMessage(String message) {
        this.info = message;
    }
}
