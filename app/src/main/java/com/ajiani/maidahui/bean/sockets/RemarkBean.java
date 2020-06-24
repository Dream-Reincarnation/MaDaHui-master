package com.ajiani.maidahui.bean.sockets;

public class RemarkBean {

    /**
     * _method_ : SendMsg
     * action : 5
     * msgid : 10000151000031570502600886734
     * userType : 1
     * nickname : U_5d5173640262e
     * avatar : https://www.maidahui.com/images/face.png
     * uid : 100001
     * toid : 100003
     * shopid : 5
     * ct : 请对本次服务进行点评
     * extra : {"stream":"100001_100003_1570502597"}
     * timestamp : 1570502600
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
         * stream : 100001_100003_1570502597
         */

        private String stream;

        public String getStream() {
            return stream;
        }

        public void setStream(String stream) {
            this.stream = stream;
        }
    }
}
