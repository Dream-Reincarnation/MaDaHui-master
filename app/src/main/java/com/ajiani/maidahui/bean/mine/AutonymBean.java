package com.ajiani.maidahui.bean.mine;

public class AutonymBean {

    /**
     * code : 1
     * info : 获取成功
     * data : {"true_name":"*耀","id_card":"13***************9","id_card_a":"","id_card_a_path":"","id_card_b":"","id_card_b_path":"","status":2,"reason":"身份证号码检测与输入的不相符"}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"2.00","money_consume":"0.00","money_consumes":"0.00","votes":"899.77","votes_income":"0.00","votes_incomes":"1000.00","lock_votes":"0.00","integral":20,"user_type":""}
     * timestamp : 1568865255
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
         * true_name : *耀
         * id_card : 13***************9
         * id_card_a :
         * id_card_a_path :
         * id_card_b :
         * id_card_b_path :
         * status : 2
         * reason : 身份证号码检测与输入的不相符
         */

        private String true_name;
        private String id_card;
        private String id_card_a;
        private String id_card_a_path;
        private String id_card_b;
        private String id_card_b_path;
        private int status;
        private String reason;
        private String id_card_exp;

        public String getId_card_exp() {
            return id_card_exp;
        }

        public void setId_card_exp(String id_card_exp) {
            this.id_card_exp = id_card_exp;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }

        public String getId_card_a() {
            return id_card_a;
        }

        public void setId_card_a(String id_card_a) {
            this.id_card_a = id_card_a;
        }

        public String getId_card_a_path() {
            return id_card_a_path;
        }

        public void setId_card_a_path(String id_card_a_path) {
            this.id_card_a_path = id_card_a_path;
        }

        public String getId_card_b() {
            return id_card_b;
        }

        public void setId_card_b(String id_card_b) {
            this.id_card_b = id_card_b;
        }

        public String getId_card_b_path() {
            return id_card_b_path;
        }

        public void setId_card_b_path(String id_card_b_path) {
            this.id_card_b_path = id_card_b_path;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    public static class UserBean {
        /**
         * id : 100003
         * nickname : 手机用户4
         * sex : 0
         * money : 2.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 899.77
         * votes_income : 0.00
         * votes_incomes : 1000.00
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
}
