package com.ajiani.maidahui.bean;

import com.ajiani.maidahui.bean.dynamic.shop.CommodityInfo;

import java.util.ArrayList;

public class ListBean {
    int id;
    ArrayList<CommodityInfo> list;

    public ListBean(int id, ArrayList<CommodityInfo> list) {

        this.id = id;
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<CommodityInfo> getList() {
        return list;
    }

    public void setList(ArrayList<CommodityInfo> list) {
        this.list = list;
    }
}
