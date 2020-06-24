package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class StoreBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"aid":6,"name":"金大勇的服装店","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/31/fad9e5539b519f81a32620ffccb592.png","content":"","type_name":"旗舰店","type_icon":"","type_icon2":""}]
     * user : {"id":100007,"nickname":"@我有一壶酒","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"96628.40","votes_income":"0.00","votes_incomes":"104020.50","lock_votes":"0.00","integral":2411,"user_auth":0,"true_name":"","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg"}
     * timestamp : 1584522298
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
         * nickname : @我有一壶酒
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 96628.40
         * votes_income : 0.00
         * votes_incomes : 104020.50
         * lock_votes : 0.00
         * integral : 2411
         * user_auth : 0
         * true_name :
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg
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

    public static class DataBean {
        /**
         * aid : 6
         * name : 金大勇的服装店
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/31/fad9e5539b519f81a32620ffccb592.png
         * content :
         * type_name : 旗舰店
         * type_icon :
         * type_icon2 :
         */

        private int aid;
        private String name;
        private String thumb;
        private String content;
        private String type_name;
        private String type_icon;
        private String type_icon2;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
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

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getType_icon() {
            return type_icon;
        }

        public void setType_icon(String type_icon) {
            this.type_icon = type_icon;
        }

        public String getType_icon2() {
            return type_icon2;
        }

        public void setType_icon2(String type_icon2) {
            this.type_icon2 = type_icon2;
        }
    }
}
