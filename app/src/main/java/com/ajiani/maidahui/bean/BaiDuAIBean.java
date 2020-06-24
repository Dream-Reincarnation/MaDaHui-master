package com.ajiani.maidahui.bean;

import java.util.List;

public class BaiDuAIBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"score":0.856934,"root":"商品-瓶饮","keyword":"瓶装饮料"},{"score":0.775569,"root":"Logo","keyword":"娃哈哈"},{"score":0.431154,"root":"商品-家具","keyword":"桌布"},{"score":0.231724,"root":"商品-容器","keyword":"瓶子"},{"score":0.030679,"root":"商品-瓶饮","keyword":"娃哈哈矿泉水"}]
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"2.00","money_consume":"0.00","money_consumes":"0.00","votes":"0.00","votes_income":"0.00","votes_incomes":"0.00","lock_votes":"0.00","integral":20,"user_type":""}
     * timestamp : 1567666180
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
         * id : 100003
         * nickname : 手机用户4
         * sex : 0
         * money : 2.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 0.00
         * votes_income : 0.00
         * votes_incomes : 0.00
         * lock_votes : 0.00
         * integral : 20
         * user_type :
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
        private String user_type;

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

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }
    }

    public static class DataBean {
        /**
         * score : 0.856934
         * root : 商品-瓶饮
         * keyword : 瓶装饮料
         */

        private double score;
        private String root;
        private String keyword;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getRoot() {
            return root;
        }

        public void setRoot(String root) {
            this.root = root;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
