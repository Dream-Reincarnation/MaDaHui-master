package com.ajiani.maidahui.bean.sockets;

public class ShopMsg {
    String shopid;
    String msgid;
    String msg;
    String extra;

    public ShopMsg(String shopid, String msgid, String msg, String extra) {
        this.shopid = shopid;
        this.msgid = msgid;
        this.msg = msg;
        this.extra = extra;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
