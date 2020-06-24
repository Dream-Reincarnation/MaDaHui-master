package com.ajiani.maidahui.bean.dynamic;

public class PersonBean {

    /**
     * code : 1
     * info : 操作成功
     * data : {"id":100005,"nickname":"铁扇公主","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/48/2eaba1a477208486c025bf724845b0.png","sign":"❤️❤️❤️❤️","fans":"1","follows":"0","is_follow":"1","level":1,"level_anchor":1,"collection":"","collected":"","city":"郑州市","longitude":"113.6883070","latitude":"34.7871610","distance":"","sex":0,"user_auth":0}
     * user : {"id":100007,"nickname":"尔等休要放肆","sex":0,"money":"8990.00","money_consume":"0.00","money_consumes":"1010.00","votes":"63.60","votes_income":"0.00","votes_incomes":"512.00","lock_votes":"0.00","integral":91634,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7c/20790c203e5a3682cd66ae2ca2f3f7.jpg"}
     * timestamp : 1572337366
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
         * id : 100005
         * nickname : 铁扇公主
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/48/2eaba1a477208486c025bf724845b0.png
         * sign : ❤️❤️❤️❤️
         * fans : 1
         * follows : 0
         * is_follow : 1
         * level : 1
         * level_anchor : 1
         * collection :
         * collected :
         * city : 郑州市
         * longitude : 113.6883070
         * latitude : 34.7871610
         * distance :
         * sex : 0
         * user_auth : 0
         */

        private int id;
        private String nickname;
        private String headimgurl;
        private String sign;
        private String fans;
        private String follows;
        private String is_follow;
        private int level;
        private int level_anchor;
        private String collection;
        private String collected;
        private String city;
        private String longitude;
        private String latitude;
        private String distance;
        private int sex;
        private int user_auth;

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

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getFollows() {
            return follows;
        }

        public void setFollows(String follows) {
            this.follows = follows;
        }

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
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

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getCollected() {
            return collected;
        }

        public void setCollected(String collected) {
            this.collected = collected;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUser_auth() {
            return user_auth;
        }

        public void setUser_auth(int user_auth) {
            this.user_auth = user_auth;
        }
    }

    public static class UserBean {
        /**
         * id : 100007
         * nickname : 尔等休要放肆
         * sex : 0
         * money : 8990.00
         * money_consume : 0.00
         * money_consumes : 1010.00
         * votes : 63.60
         * votes_income : 0.00
         * votes_incomes : 512.00
         * lock_votes : 0.00
         * integral : 91634
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
}
