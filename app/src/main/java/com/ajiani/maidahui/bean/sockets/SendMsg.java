package com.ajiani.maidahui.bean.sockets;

import org.json.JSONObject;

public class SendMsg {
    String uid;
    String msgid;
    String msg;
    String action;
    JSONObject extra;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public JSONObject getExtra() {
        return extra;
    }

    public void setExtra(JSONObject extra) {
        this.extra = extra;
    }

    public SendMsg(String uid, String msgid, String msg, String action, JSONObject extra) {
        this.uid = uid;
        this.msgid = msgid;
        this.msg = msg;
        this.action = action;
        this.extra = extra;
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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


}
