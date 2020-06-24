package com.ajiani.maidahui.bean.chat;

import java.util.List;

public class ServiceBean {

    /**
     * code : 1
     * info : success
     * data : [{"shop_id":5,"name":"阿佳妮旗舰店","thumb":"https://www.maidahui.com/images/none.png","noread2":47,"content":"hello,你好呀,衣服多少钱","timestamp":1570538053,"format_create_time":"14分钟前"},{"shop_id":6,"name":"01","thumb":"https://www.maidahui.com/images/none.png","noread2":60,"content":"发个","timestamp":1570531224,"format_create_time":"2小时前"}]
     * user : {"id":100003,"nickname":"大风起兮龙飞翔","sex":0,"money":"9900.00","money_consume":"0.00","money_consumes":"102.00","votes":"763.08","votes_income":"0.00","votes_incomes":"1583.22","lock_votes":"0.00","integral":33775,"user_type":""}
     * timestamp : 1570538919
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
         * nickname : 大风起兮龙飞翔
         * sex : 0
         * money : 9900.00
         * money_consume : 0.00
         * money_consumes : 102.00
         * votes : 763.08
         * votes_income : 0.00
         * votes_incomes : 1583.22
         * lock_votes : 0.00
         * integral : 33775
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
         * shop_id : 5
         * name : 阿佳妮旗舰店
         * thumb : https://www.maidahui.com/images/none.png
         * noread2 : 47
         * content : hello,你好呀,衣服多少钱
         * timestamp : 1570538053
         * format_create_time : 14分钟前
         */

        private int shop_id;
        private String name;
        private String thumb;
        private String noread2;
        private String content;
        private String timestamp;
        private String format_create_time;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }



        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getNoread2() {
            return noread2;
        }

        public void setNoread2(String noread2) {
            this.noread2 = noread2;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getFormat_create_time() {
            return format_create_time;
        }

        public void setFormat_create_time(String format_create_time) {
            this.format_create_time = format_create_time;
        }
    }
}
