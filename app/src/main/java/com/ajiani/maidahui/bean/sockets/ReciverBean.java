package com.ajiani.maidahui.bean.sockets;

public class ReciverBean {

    /**
     * _method_ : SendMsg
     * action : 1
     * msgid : 10000301000031569643113596110
     * userType : 0
     * nickname : 大风起兮龙飞翔
     * avatar : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg
     * uid : 100003
     * toid : 100003
     * shopid : 0
     * ct : hello,你好呀
     * extra :
     * timestamp : 1569643113
     */

    private String _method_;
    private String action;
    private String msgid;
    private int userType;
    private String nickname;
    private String avatar;
    private String uid;
    private String toid;
    private int shopid;
    private String ct;
    private String extra;
    private int timestamp;

    public String get_method_() {
        return _method_;
    }

    public void set_method_(String _method_) {
        this._method_ = _method_;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
