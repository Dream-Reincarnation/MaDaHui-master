package com.ajiani.maidahui.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class ShopMessage {

    @Id
    private Long id;
    //谁的聊天记录
    public String room;
    //店铺ID
    private String shopID;
    //头像
    private String avatar;
    //姓名
    private String nickName;
    //消息action
    private String action;
    //发送人ID
    private String userID;
    //接收人ID
    private String toUserID;
    //消息ID
    private String msgId;
    private String userType;
    //消息内容
    private String content;
    //透传
    private String extra;
    //是否是我发出的
    boolean isme;
    //发送时间\
    private long time;

    @Generated(hash = 72016997)
    public ShopMessage(Long id, String room, String shopID, String avatar,
            String nickName, String action, String userID, String toUserID,
            String msgId, String userType, String content, String extra,
            boolean isme, long time) {
        this.id = id;
        this.room = room;
        this.shopID = shopID;
        this.avatar = avatar;
        this.nickName = nickName;
        this.action = action;
        this.userID = userID;
        this.toUserID = toUserID;
        this.msgId = msgId;
        this.userType = userType;
        this.content = content;
        this.extra = extra;
        this.isme = isme;
        this.time = time;
    }
    @Generated(hash = 691502139)
    public ShopMessage() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRoom() {
        return this.room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getShopID() {
        return this.shopID;
    }
    public void setShopID(String shopID) {
        this.shopID = shopID;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getAction() {
        return this.action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getUserID() {
        return this.userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getToUserID() {
        return this.toUserID;
    }
    public void setToUserID(String toUserID) {
        this.toUserID = toUserID;
    }
    public String getMsgId() {
        return this.msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public String getUserType() {
        return this.userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getExtra() {
        return this.extra;
    }
    public void setExtra(String extra) {
        this.extra = extra;
    }
    public boolean getIsme() {
        return this.isme;
    }
    public void setIsme(boolean isme) {
        this.isme = isme;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
   
}
