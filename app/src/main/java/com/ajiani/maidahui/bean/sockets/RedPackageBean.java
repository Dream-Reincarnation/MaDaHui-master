package com.ajiani.maidahui.bean.sockets;

public class RedPackageBean  {


    /**
     * _method_ : SendMsg
     * action : 8
     * msgid : 10000861000031570527417407182
     * userType : 1
     * nickname : 手机用户5063
     * avatar : https://www.maidahui.com/images/face.png
     * uid : 100008
     * toid : 100003
     * shopid : 6
     * ct : 退运费
     * extra : {"money":"1.00","order_no":"TM_IA082669223802602"}
     * timestamp : 1570527417
     */

    private String _method_;
    private int action;
    private String msgid;
    private int userType;
    private String nickname;
    private String avatar;
    private int uid;
    private String toid;
    private int shopid;
    private String ct;
    private ExtraBean extra;
    private int timestamp;

    public String get_method_() {
        return _method_;
    }

    public void set_method_(String _method_) {
        this._method_ = _method_;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class ExtraBean {
        /**
         * money : 1.00
         * order_no : TM_IA082669223802602
         */

        private String money;
        private String order_no;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }
    }
}
