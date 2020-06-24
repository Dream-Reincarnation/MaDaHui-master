package com.ajiani.maidahui.bean.mine;

public class CountBean {

      /* user : {"id":100007,"nickname":"尔等休要放肆","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"1.00","votes_income":"0.00","votes_incomes":"1300.00","lock_votes":"0.00","integral":1419,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg"}
     * timestamp : 1574334308
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
         * goods_collection : 4
         * shops_star : 2
         * videos_star : 7
         * topic_star : 0
         * audio_star : 0
         * collection : 13
         * collected : 12
         * history : 23
         * order_pay : 3
         * order_group : 0
         * order_shipping : 2
         * order_success : 0
         * order_comment : 1
         */

        private int goods_collection;
        private int shops_star;
        private int videos_star;
        private int topic_star;
        private int audio_star;
        private int collection;
        private int collected;
        private int history;
        private int order_pay;
        private int order_group;
        private int order_shipping;
        private int order_success;
        private int order_comment;

        public int getGoods_collection() {
            return goods_collection;
        }

        public void setGoods_collection(int goods_collection) {
            this.goods_collection = goods_collection;
        }

        public int getShops_star() {
            return shops_star;
        }

        public void setShops_star(int shops_star) {
            this.shops_star = shops_star;
        }

        public int getVideos_star() {
            return videos_star;
        }

        public void setVideos_star(int videos_star) {
            this.videos_star = videos_star;
        }

        public int getTopic_star() {
            return topic_star;
        }

        public void setTopic_star(int topic_star) {
            this.topic_star = topic_star;
        }

        public int getAudio_star() {
            return audio_star;
        }

        public void setAudio_star(int audio_star) {
            this.audio_star = audio_star;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getCollected() {
            return collected;
        }

        public void setCollected(int collected) {
            this.collected = collected;
        }

        public int getHistory() {
            return history;
        }

        public void setHistory(int history) {
            this.history = history;
        }

        public int getOrder_pay() {
            return order_pay;
        }

        public void setOrder_pay(int order_pay) {
            this.order_pay = order_pay;
        }

        public int getOrder_group() {
            return order_group;
        }

        public void setOrder_group(int order_group) {
            this.order_group = order_group;
        }

        public int getOrder_shipping() {
            return order_shipping;
        }

        public void setOrder_shipping(int order_shipping) {
            this.order_shipping = order_shipping;
        }

        public int getOrder_success() {
            return order_success;
        }

        public void setOrder_success(int order_success) {
            this.order_success = order_success;
        }

        public int getOrder_comment() {
            return order_comment;
        }

        public void setOrder_comment(int order_comment) {
            this.order_comment = order_comment;
        }
    }

    public static class UserBean {
        /**
         * id : 100007
         * nickname : 尔等休要放肆
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 1.00
         * votes_income : 0.00
         * votes_incomes : 1300.00
         * lock_votes : 0.00
         * integral : 1419
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
}
