package com.ajiani.maidahui.bean.mine;

import java.util.List;

public class WithDrawDetailsBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"order_no":"CS_BA301858786852807","create_time":"2019-10-30 18:49:07","true_name":"","status":2,"start_time":"2019-10-30 18:49:12","rason":"","end_time":"2019-10-30 18:49:44","account_type":"alipay","cash_fee":"1.00","poundage":"0.05","pay_fee":"0.95","order_id":"20191030110070001506960019826485","account_type_name":"支付宝","remark":"","status_name":"已完成"},{"order_no":"CS_BA307642692092025","create_time":"2019-10-30 18:42:58","true_name":"","status":2,"start_time":"2019-10-30 18:43:15","rason":"","end_time":"2019-10-30 18:44:11","account_type":"alipay","cash_fee":"1.00","poundage":"0.05","pay_fee":"0.95","order_id":"20191030110070001506960019877148","account_type_name":"支付宝","remark":"支付宝","status_name":"已完成"},{"order_no":"CS_BA308453902367128","create_time":"2019-10-30 18:32:36","true_name":"","status":3,"start_time":"2019-10-30 18:34:40","rason":"账户不正确","end_time":"2019-10-30 18:34:40","account_type":"alipay","cash_fee":"100.00","poundage":"5.00","pay_fee":"95.00","order_id":"","account_type_name":"支付宝","remark":"","status_name":"已拒绝"}]
     * user : {"id":100007,"nickname":"尔等休要放肆","sex":1,"money":"8992.00","money_consume":"0.00","money_consumes":"1010.00","votes":"1050.60","votes_income":"0.00","votes_incomes":"1612.00","lock_votes":"0.00","integral":91654,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7c/20790c203e5a3682cd66ae2ca2f3f7.jpg"}
     * timestamp : 1572483518
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
         * sex : 1
         * money : 8992.00
         * money_consume : 0.00
         * money_consumes : 1010.00
         * votes : 1050.60
         * votes_income : 0.00
         * votes_incomes : 1612.00
         * lock_votes : 0.00
         * integral : 91654
         * user_auth : 0
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7c/20790c203e5a3682cd66ae2ca2f3f7.jpg
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
         * order_no : CS_BA301858786852807
         * create_time : 2019-10-30 18:49:07
         * true_name :
         * status : 2
         * start_time : 2019-10-30 18:49:12
         * rason :
         * end_time : 2019-10-30 18:49:44
         * account_type : alipay
         * cash_fee : 1.00
         * poundage : 0.05
         * pay_fee : 0.95
         * order_id : 20191030110070001506960019826485
         * account_type_name : 支付宝
         * remark :
         * status_name : 已完成
         */

        private String order_no;
        private String create_time;
        private String true_name;
        private int status;
        private String start_time;
        private String rason;
        private String end_time;
        private String account_type;
        private String cash_fee;
        private String poundage;
        private String pay_fee;
        private String order_id;
        private String account_type_name;
        private String remark;
        private String status_name;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getRason() {
            return rason;
        }

        public void setRason(String rason) {
            this.rason = rason;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getAccount_type() {
            return account_type;
        }

        public void setAccount_type(String account_type) {
            this.account_type = account_type;
        }

        public String getCash_fee() {
            return cash_fee;
        }

        public void setCash_fee(String cash_fee) {
            this.cash_fee = cash_fee;
        }

        public String getPoundage() {
            return poundage;
        }

        public void setPoundage(String poundage) {
            this.poundage = poundage;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getAccount_type_name() {
            return account_type_name;
        }

        public void setAccount_type_name(String account_type_name) {
            this.account_type_name = account_type_name;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
    }
}
