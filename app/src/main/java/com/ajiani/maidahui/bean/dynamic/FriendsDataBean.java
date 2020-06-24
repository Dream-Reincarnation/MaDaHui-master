package com.ajiani.maidahui.bean.dynamic;

public class FriendsDataBean {
    /**
     * id : 100015
     * nickname : 恰同学少年
     * headimgurl : https://www.maidahui.com/images/none.png
     * level : 1
     * level_anchor : 1
     * is_follow : 1
     * is_follow_me : 1
     */

    private int id;
    private String nickname;
    private String headimgurl;
    private int level;
    private int level_anchor;
    private int is_follow;
    private int is_follow_me;
    private boolean isSel;

    public boolean isSel() {
        return isSel;
    }

    public void setSel(boolean sel) {
        isSel = sel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel_anchor() {
        return level_anchor;
    }

    public void setLevel_anchor(int level_anchor) {
        this.level_anchor = level_anchor;
    }

    public int getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(int is_follow) {
        this.is_follow = is_follow;
    }

    public int getIs_follow_me() {
        return is_follow_me;
    }

    public void setIs_follow_me(int is_follow_me) {
        this.is_follow_me = is_follow_me;
    }
}
