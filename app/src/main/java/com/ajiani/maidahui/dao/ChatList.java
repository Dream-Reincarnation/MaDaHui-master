package com.ajiani.maidahui.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity( nameInDb = "chatList")
public class ChatList {
    @Id(autoincrement = true) // id自增长
    Long id;
    String shopId;
    String userId;

    //头像
    String headImg;

    //最新一条消息
    String content;
    //时间
    String timemap;
    //是否置顶
    Boolean isTop;
    //名字
    String nickName;
    //未读消息数量
    String unread;
    //格式化时间
    String formatTime;
    @Generated(hash = 1061314701)
    public ChatList(Long id, String shopId, String userId, String headImg,
            String content, String timemap, Boolean isTop, String nickName,
            String unread, String formatTime) {
        this.id = id;
        this.shopId = shopId;
        this.userId = userId;
        this.headImg = headImg;
        this.content = content;
        this.timemap = timemap;
        this.isTop = isTop;
        this.nickName = nickName;
        this.unread = unread;
        this.formatTime = formatTime;
    }
    @Generated(hash = 406825685)
    public ChatList() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getShopId() {
        return this.shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTimemap() {
        return this.timemap;
    }
    public void setTimemap(String timemap) {
        this.timemap = timemap;
    }
    public Boolean getIsTop() {
        return this.isTop;
    }
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getUnread() {
        return this.unread;
    }
    public void setUnread(String unread) {
        this.unread = unread;
    }
    public String getFormatTime() {
        return this.formatTime;
    }
    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }
    public String getHeadImg() {
        return this.headImg;
    }
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


}
