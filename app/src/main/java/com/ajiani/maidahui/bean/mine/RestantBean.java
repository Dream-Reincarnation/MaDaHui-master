package com.ajiani.maidahui.bean.mine;

import java.util.List;

public class RestantBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"before_votes":"1000.00","change_votes":"-100.00","after_votes":"900.00","change_type":"购买商品","create_time":"2019-09-15 20:04:22","remark":"团购商品测试","order_no":"SP_C9154512134468318"},{"before_votes":"0.00","change_votes":"1000.00","after_votes":"1000.00","change_type":"系统冲正","create_time":"2019-09-15 19:55:04","remark":"系统冲正 操作管理员工号:1","order_no":"CZ_I9158492041106543"}]
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"2.00","money_consume":"0.00","money_consumes":"0.00","votes":"900.00","votes_income":"0.00","votes_incomes":"1000.00","lock_votes":"0.00","integral":14800,"user_type":""}
     * timestamp : 1569486435
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
         * votes : 900.00
         * votes_income : 0.00
         * votes_incomes : 1000.00
         * lock_votes : 0.00
         * integral : 14800
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
         * before_votes : 1000.00
         * change_votes : -100.00
         * after_votes : 900.00
         * change_type : 购买商品
         * create_time : 2019-09-15 20:04:22
         * remark : 团购商品测试
         * order_no : SP_C9154512134468318
         */

        private String before_votes;
        private String change_votes;
        private String after_votes;
        private String change_type;
        private String create_time;
        private String remark;
        private String order_no;

        public String getBefore_votes() {
            return before_votes;
        }

        public void setBefore_votes(String before_votes) {
            this.before_votes = before_votes;
        }

        public String getChange_votes() {
            return change_votes;
        }

        public void setChange_votes(String change_votes) {
            this.change_votes = change_votes;
        }

        public String getAfter_votes() {
            return after_votes;
        }

        public void setAfter_votes(String after_votes) {
            this.after_votes = after_votes;
        }

        public String getChange_type() {
            return change_type;
        }

        public void setChange_type(String change_type) {
            this.change_type = change_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }
    }
}
