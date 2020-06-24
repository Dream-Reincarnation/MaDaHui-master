package com.ajiani.maidahui.bean.sockets;

public class ShopMsg2 {
    String shopid;
    String msgid;
    String msg;
    Object extra;

    public ShopMsg2(String shopid, String msgid, String msg, Object extra) {
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

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
