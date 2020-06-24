package com.ajiani.maidahui.bean.chat;

import java.util.List;

public class PlatformBean {

    /**
     * code : 1
     * info : success
     * data : []
     * user : {"id":100007,"nickname":"尔等休要放肆","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"516.00","votes_income":"0.00","votes_incomes":"1011.00","lock_votes":"0.00","integral":0,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/fb/789a932445c6bc68f8f659b46713da.png"}
     * timestamp : 1573630153
     */

    private String code;
    private String info;
    private UserBean user;
    private int timestamp;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
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
         * votes : 516.00
         * votes_income : 0.00
         * votes_incomes : 1011.00
         * lock_votes : 0.00
         * integral : 0
         * user_auth : 0
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/fb/789a932445c6bc68f8f659b46713da.png
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
}
