package com.ajiani.maidahui.bean.chat;

public class SystemNumBean {

    /**
     * code : 1
     * info : success
     * data : {"messages":0,"last_message":"","last_message_time":"","order":0,"shops":0,"acetex":19,"message":0,"fans":0}
     * user : {"id":100003,"nickname":"大风起兮龙飞翔","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"10000.00","votes_income":"0.00","votes_incomes":"10000.00","lock_votes":"0.00","integral":0,"user_auth":1,"true_name":"王耀","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg"}
     * timestamp : 1578304894
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
         * messages : 0
         * last_message :
         * last_message_time :
         * order : 0
         * shops : 0
         * acetex : 19
         * message : 0
         * fans : 0
         */

        private int messages;
        private String last_message;
        private String last_message_time;
        private int order;
        private int shops;
        private int acetex;
        private int message;
        private int fans;

        public int getMessages() {
            return messages;
        }

        public void setMessages(int messages) {
            this.messages = messages;
        }

        public String getLast_message() {
            return last_message;
        }

        public void setLast_message(String last_message) {
            this.last_message = last_message;
        }

        public String getLast_message_time() {
            return last_message_time;
        }

        public void setLast_message_time(String last_message_time) {
            this.last_message_time = last_message_time;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getShops() {
            return shops;
        }

        public void setShops(int shops) {
            this.shops = shops;
        }

        public int getAcetex() {
            return acetex;
        }

        public void setAcetex(int acetex) {
            this.acetex = acetex;
        }

        public int getMessage() {
            return message;
        }

        public void setMessage(int message) {
            this.message = message;
        }

        public int getFans() {
            return fans;
        }

        public void setFans(int fans) {
            this.fans = fans;
        }
    }

    public static class UserBean {
        /**
         * id : 100003
         * nickname : 大风起兮龙飞翔
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 10000.00
         * votes_income : 0.00
         * votes_incomes : 10000.00
         * lock_votes : 0.00
         * integral : 0
         * user_auth : 1
         * true_name : 王耀
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg
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
}
