package com.ajiani.maidahui.bean.sockets;

import android.text.SpannableString;

public class MsgBean {
    public MsgBean() {
    }
    public long aId=0;
    public boolean isLeft;
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
    private SpannableString spannableString;
    private Object extra;
    private int timestamp;

    public SpannableString getSpannableString() {
        return spannableString;
    }

    public void setSpannableString(SpannableString spannableString) {
        this.spannableString = spannableString;
    }

    @Override
    public String toString() {
        return "MsgBean{" +
                "isLeft=" + isLeft +
                ", _method_='" + _method_ + '\'' +
                ", action='" + action + '\'' +
                ", msgid='" + msgid + '\'' +
                ", userType=" + userType +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", uid='" + uid + '\'' +
                ", toid='" + toid + '\'' +
                ", shopid=" + shopid +
                ", ct='" + ct + '\'' +
                ", extra='" + extra + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }


    public long getaId() {
        return aId;
    }

    public void setaId(long aId) {
        this.aId = aId;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

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

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
