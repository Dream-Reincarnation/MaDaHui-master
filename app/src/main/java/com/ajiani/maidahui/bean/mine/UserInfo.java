package com.ajiani.maidahui.bean.mine;

public class UserInfo {


    /**
     * code : 1
     * info : 登录成功
     * data : {"user_id":100003,"headimgurl":"https://www.maidahui.com/images/face.png","nickname":"手机用户4","sign":"","user_auth":0,"level":1,"level_progress":0,"level_anchor":1,"level_anchor_progress":0,"follows":"0","fans":"0","money":"975.00","votes":"700.00","integral":50,"alipay":"","wechat":0,"mobile":"18831094384","is_paypass":0,"is_pass":0,"sex":0,"city":"郑州市","longitude":"113.6885180","latitude":"34.7875390","collection":0,"collected":0,"agent_level":0,"agent_name":"","area_level":0,"area_name":"","birthday":"0000-00-00","height":0,"weight":0,"pay_pass":""}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"975.00","money_consume":"0.00","money_consumes":"30.00","votes":"700.00","votes_income":"0.00","votes_incomes":"1000.00","lock_votes":"0.00","integral":50,"user_type":""}
     * timestamp : 1568637712
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
         * user_id : 100003
         * headimgurl : https://www.maidahui.com/images/face.png
         * nickname : 手机用户4
         * sign :
         * user_auth : 0
         * level : 1
         * level_progress : 0
         * level_anchor : 1
         * level_anchor_progress : 0
         * follows : 0
         * fans : 0
         * money : 975.00
         * votes : 700.00
         * integral : 50
         * alipay :
         * wechat : 0
         * mobile : 18831094384
         * is_paypass : 0
         * is_pass : 0
         * sex : 0
         * city : 郑州市
         * longitude : 113.6885180
         * latitude : 34.7875390
         * collection : 0
         * collected : 0
         * agent_level : 0
         * agent_name :
         * area_level : 0
         * area_name :
         * birthday : 0000-00-00
         * height : 0
         * weight : 0
         * pay_pass :
         */

        private int user_id;
        private String headimgurl;
        private String nickname;
        private String sign;
        private int user_auth;
        private int level;
        private int level_progress;
        private int level_anchor;
        private int level_anchor_progress;
        private String follows;
        private String fans;
        private String money;
        private String votes;
        private int integral;
        private String alipay;
        private int wechat;
        private String mobile;
        private int is_paypass;
        private int is_pass;
        private int sex;
        private String city;
        private String longitude;
        private String latitude;
        private int collection;
        private int collected;
        private int agent_level;
        private String agent_name;
        private int area_level;
        private String area_name;
        private String birthday;
        private int height;
        private int weight;
        private String pay_pass;
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public int getUser_auth() {
            return user_auth;
        }

        public void setUser_auth(int user_auth) {
            this.user_auth = user_auth;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getLevel_progress() {
            return level_progress;
        }

        public void setLevel_progress(int level_progress) {
            this.level_progress = level_progress;
        }

        public int getLevel_anchor() {
            return level_anchor;
        }

        public void setLevel_anchor(int level_anchor) {
            this.level_anchor = level_anchor;
        }

        public int getLevel_anchor_progress() {
            return level_anchor_progress;
        }

        public void setLevel_anchor_progress(int level_anchor_progress) {
            this.level_anchor_progress = level_anchor_progress;
        }

        public String getFollows() {
            return follows;
        }

        public void setFollows(String follows) {
            this.follows = follows;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getAlipay() {
            return alipay;
        }

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public int getWechat() {
            return wechat;
        }

        public void setWechat(int wechat) {
            this.wechat = wechat;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_paypass() {
            return is_paypass;
        }

        public void setIs_paypass(int is_paypass) {
            this.is_paypass = is_paypass;
        }

        public int getIs_pass() {
            return is_pass;
        }

        public void setIs_pass(int is_pass) {
            this.is_pass = is_pass;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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

        public int getAgent_level() {
            return agent_level;
        }

        public void setAgent_level(int agent_level) {
            this.agent_level = agent_level;
        }

        public String getAgent_name() {
            return agent_name;
        }

        public void setAgent_name(String agent_name) {
            this.agent_name = agent_name;
        }

        public int getArea_level() {
            return area_level;
        }

        public void setArea_level(int area_level) {
            this.area_level = area_level;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getPay_pass() {
            return pay_pass;
        }

        public void setPay_pass(String pay_pass) {
            this.pay_pass = pay_pass;
        }
    }

    public static class UserBean {
        /**
         * id : 100003
         * nickname : 手机用户4
         * sex : 0
         * money : 975.00
         * money_consume : 0.00
         * money_consumes : 30.00
         * votes : 700.00
         * votes_income : 0.00
         * votes_incomes : 1000.00
         * lock_votes : 0.00
         * integral : 50
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
