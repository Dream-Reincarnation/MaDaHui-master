package com.ajiani.maidahui.bean;

public class AddShopBean {
    int code;
    int shopId;
    String shopName;
    String shopPrice;
    String shopLint;
    String shopTheml;

    public AddShopBean() {

    }

    public AddShopBean(int code, int shopId, String shopName, String shopPrice, String shopLint, String shopTheml) {
        this.code = code;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopPrice = shopPrice;
        this.shopLint = shopLint;
        this.shopTheml = shopTheml;
    }

    public String getShopTheml() {
        return shopTheml;
    }

    public void setShopTheml(String shopTheml) {
        this.shopTheml = shopTheml;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getShopLint() {
        return shopLint;
    }

    public void setShopLint(String shopLint) {
        this.shopLint = shopLint;
    }
}
