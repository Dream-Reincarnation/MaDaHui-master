package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class FriendsListBean {

    /**
     * code : 1
     * info : 成功
     * data : [{"id":100022,"nickname":"黄远航","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/IgpNh2icImnWsvicXLEL9qbqn8mEolxBAy5qKSLrIeVxFlrIpIAEXW5y0WSotNcG8kicSHBDWGCPUDEibRhh2oTc1Q/132","level":1,"level_anchor":1,"is_follow":"1","is_follow_me":"1","pinyin":"H"},{"id":100005,"nickname":"金角大王","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/8b/8422e6081c0b42d7a675647ced16f2.png","level":1,"level_anchor":1,"is_follow":"1","is_follow_me":"0","pinyin":"J"},{"id":100021,"nickname":"李雪","headimgurl":"https://thirdwx.qlogo.cn/mmopen/vi_32/zbibeib7BWMkZFHiaArvTNHEb995p1rkGb17ww1RpUibR07U7EFs10GI4Yorib0yKRQGRwxU0hKjQhh6y1m2HBDib3ibg/132","level":1,"level_anchor":1,"is_follow":"0","is_follow_me":"1","pinyin":"L"},{"id":100006,"nickname":"没披风的平头哥","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/82/fcda46ec941db2fdcf2be758f22fbd.png","level":1,"level_anchor":1,"is_follow":"1","is_follow_me":"1","pinyin":"M"},{"id":100015,"nickname":"恰同学少年","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/5a/f1d37a4156f3fe571f0d46391fb354.png","level":1,"level_anchor":1,"is_follow":"1","is_follow_me":"1","pinyin":"Q"},{"id":100009,"nickname":"小草","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/21/4b7d915b69f23ded3f8f5088201c79.png","level":1,"level_anchor":1,"is_follow":"1","is_follow_me":"0","pinyin":"X"},{"id":100016,"nickname":"璇儿","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/86/cbe4b037777519f25da15c98680b01.png","level":1,"level_anchor":1,"is_follow":"1","is_follow_me":"1","pinyin":"X"}]
     * user : {"id":100007,"nickname":"@我有一壶酒","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"96628.40","votes_income":"0.00","votes_incomes":"104020.50","lock_votes":"0.00","integral":2411,"user_auth":0,"true_name":"","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg"}
     * timestamp : 1584322975
     */

    private String code;
    private String info;
    private UserBean user;
    private int timestamp;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class UserBean {
        /**
         * id : 100007
         * nickname : @我有一壶酒
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 96628.40
         * votes_income : 0.00
         * votes_incomes : 104020.50
         * lock_votes : 0.00
         * integral : 2411
         * user_auth : 0
         * true_name :
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg
         */

        private int id;
        private String nickname;
        private int sex;
        private String money;
        private String money_consume;
        private String money_consumes;
        private String votes;
        private String votes_income;
        private String votes_incomes;
        private String lock_votes;
        private int integral;
        private int user_auth;
        private String true_name;
        private String headimgurl;

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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoney_consume() {
            return money_consume;
        }

        public void setMoney_consume(String money_consume) {
            this.money_consume = money_consume;
        }

        public String getMoney_consumes() {
            return money_consumes;
        }

        public void setMoney_consumes(String money_consumes) {
            this.money_consumes = money_consumes;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getVotes_income() {
            return votes_income;
        }

        public void setVotes_income(String votes_income) {
            this.votes_income = votes_income;
        }

        public String getVotes_incomes() {
            return votes_incomes;
        }

        public void setVotes_incomes(String votes_incomes) {
            this.votes_incomes = votes_incomes;
        }

        public String getLock_votes() {
            return lock_votes;
        }

        public void setLock_votes(String lock_votes) {
            this.lock_votes = lock_votes;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public int getUser_auth() {
            return user_auth;
        }

        public void setUser_auth(int user_auth) {
            this.user_auth = user_auth;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }
    }

    public static class DataBean {
        /**
         * id : 100022
         * nickname : 黄远航
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/IgpNh2icImnWsvicXLEL9qbqn8mEolxBAy5qKSLrIeVxFlrIpIAEXW5y0WSotNcG8kicSHBDWGCPUDEibRhh2oTc1Q/132
         * level : 1
         * level_anchor : 1
         * is_follow : 1
         * is_follow_me : 1
         * pinyin : H
         */

        private int id;
        private String nickname;
        private String headimgurl;
        private int level;
        private int level_anchor;
        private String is_follow;
        private String is_follow_me;
        private String pinyin;

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

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getIs_follow_me() {
            return is_follow_me;
        }

        public void setIs_follow_me(String is_follow_me) {
            this.is_follow_me = is_follow_me;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }
    }
}
