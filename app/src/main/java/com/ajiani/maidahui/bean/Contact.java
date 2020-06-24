package com.ajiani.maidahui.bean;


import com.ajiani.maidahui.cn.CN;

/**
 * Created by you on 2017/9/11.
 */

public class Contact implements CN {

    public  String name;

    public  String imgUrl;
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contact(String name, String imgUrl, String id) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.id = id;
    }

    @Override
    public String chinese() {
        return name;
    }
}
