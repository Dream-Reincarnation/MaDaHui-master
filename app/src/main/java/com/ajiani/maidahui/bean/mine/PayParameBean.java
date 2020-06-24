package com.ajiani.maidahui.bean.mine;

public class PayParameBean {

    /**
     * code : 1
     * info : 请按照此配置调起支付
     * data : {"orderString":"alipay_sdk=alipay-sdk-php-20161101&app_id=2019061765629232&biz_content=%7B%22body%22%3A%22100003-%5Cu5145%5Cu503c100%5Cu94bb%5Cu77f3%5Cu9001100%5Cu79ef%5Cu5206%22%2C%22subject%22%3A%22100003-%5Cu5145%5Cu503c100%5Cu94bb%5Cu77f3%5Cu9001100%5Cu79ef%5Cu5206%22%2C%22out_trade_no%22%3A%22RC_I8302113408391766%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22100.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmdh.wefsn.com%2Fnotify%2Fpay%2Faliapp_notify&sign_type=RSA2&timestamp=2019-08-30+16%3A02%3A24&version=1.0&sign=rCfhHAi2QhrJZ9Dmpr0m1SgOPhErVPIPP3%2Fs0Cp5i%2Bnn4UNRWdlejlG8Ntq%2Bi8ttsBEHZWPGSZ0axjcDMWZzWNMapi%2BXqUOcytGHZgHXNrd155kPyf8C3rwhMFzSZ5wBmpMHq84xFYKw%2BbtyKqchhID9lJN4duneexzOuRrhp6fuYYEVwchLHyNWJhPZ4F50Lqnhvsvf7NAVVYNuN0TufNQ%2BhLN1ZS0TzLPMVFYdYulo1SKIhNQ34Mmqby8LVgvBGG3whxyx7CVlm4%2Bs7evZesuR1XEJ8H86x%2FwLntAjnLoIBKFsTlfr9sHoRGAADpdYO43CseffhvOurgi8dKpc6Q%3D%3D"}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"0.00","votes_income":"0.00","votes_incomes":"0.00","lock_votes":"0.00","integral":0,"user_type":""}
     * timestamp : 1567152144
     */

    private String code;
    private String info;
    private DataBean data;
    private UserBean user;
    private int timestamp;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * orderString : alipay_sdk=alipay-sdk-php-20161101&app_id=2019061765629232&biz_content=%7B%22body%22%3A%22100003-%5Cu5145%5Cu503c100%5Cu94bb%5Cu77f3%5Cu9001100%5Cu79ef%5Cu5206%22%2C%22subject%22%3A%22100003-%5Cu5145%5Cu503c100%5Cu94bb%5Cu77f3%5Cu9001100%5Cu79ef%5Cu5206%22%2C%22out_trade_no%22%3A%22RC_I8302113408391766%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22100.00%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmdh.wefsn.com%2Fnotify%2Fpay%2Faliapp_notify&sign_type=RSA2&timestamp=2019-08-30+16%3A02%3A24&version=1.0&sign=rCfhHAi2QhrJZ9Dmpr0m1SgOPhErVPIPP3%2Fs0Cp5i%2Bnn4UNRWdlejlG8Ntq%2Bi8ttsBEHZWPGSZ0axjcDMWZzWNMapi%2BXqUOcytGHZgHXNrd155kPyf8C3rwhMFzSZ5wBmpMHq84xFYKw%2BbtyKqchhID9lJN4duneexzOuRrhp6fuYYEVwchLHyNWJhPZ4F50Lqnhvsvf7NAVVYNuN0TufNQ%2BhLN1ZS0TzLPMVFYdYulo1SKIhNQ34Mmqby8LVgvBGG3whxyx7CVlm4%2Bs7evZesuR1XEJ8H86x%2FwLntAjnLoIBKFsTlfr9sHoRGAADpdYO43CseffhvOurgi8dKpc6Q%3D%3D
         */

        private String orderString;

        public String getOrderString() {
            return orderString;
        }

        public void setOrderString(String orderString) {
            this.orderString = orderString;
        }
    }

    public static class UserBean {
        /**
         * id : 100003
         * nickname : 手机用户4
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 0.00
         * votes_income : 0.00
         * votes_incomes : 0.00
         * lock_votes : 0.00
         * integral : 0
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
}
