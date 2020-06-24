package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class FriendsBean {


    /**
     * code : 1
     * info : 成功
     * data : [{"id":100015,"nickname":"恰同学少年","headimgurl":"https://www.maidahui.com/images/none.png","level":1,"level_anchor":1,"is_follow":1,"is_follow_me":1},{"id":100016,"nickname":"璇儿","headimgurl":"https://www.maidahui.com/images/none.png","level":1,"level_anchor":1,"is_follow":1,"is_follow_me":1}]
     * user : {"id":100007,"nickname":"尔等休要放肆","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"863.00","votes_income":"0.00","votes_incomes":"2300.00","lock_votes":"0.00","integral":1427,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg"}
     * timestamp : 1575973511
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
         * nickname : 尔等休要放肆
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 863.00
         * votes_income : 0.00
         * votes_incomes : 2300.00
         * lock_votes : 0.00
         * integral : 1427
         * user_auth : 0
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg
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

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }
    }

    public static class DataBean {
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
}
