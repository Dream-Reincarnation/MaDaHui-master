package com.ajiani.maidahui.bean.dynamic;

import com.ajiani.maidahui.cn.CN;

import java.util.List;

public class FollowListBean {
   public static  String name;
    /**
     * code : 1
     * info : 成功
     * data : [{"id":100010,"nickname":"中国好小伙儿","headimgurl":"https://www.maidahui.com/images/face.png","level":1,"level_anchor":1,"is_follow":1,"is_follow_me":0},{"id":100011,"nickname":"二郎神","headimgurl":"https://www.maidahui.com/images/face.png","level":1,"level_anchor":1,"is_follow":1,"is_follow_me":0}]
     * user : {"id":100003,"nickname":"大风起兮龙飞翔","sex":0,"money":"9955.00","money_consume":"0.00","money_consumes":"47.00","votes":"899.86","votes_income":"0.00","votes_incomes":"1000.00","lock_votes":"0.00","integral":390,"user_type":""}
     * timestamp : 1569742744
     */

    private String code;
    private String info;
    private UserBean user;
    private int timestamp;
    public List<DataBean> data;

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
         * money : 9955.00
         * money_consume : 0.00
         * money_consumes : 47.00
         * votes : 899.86
         * votes_income : 0.00
         * votes_incomes : 1000.00
         * lock_votes : 0.00
         * integral : 390
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

    public  class DataBean implements CN  {
        /**
         * id : 100010
         * nickname : 中国好小伙儿
         * headimgurl : https://www.maidahui.com/images/face.png
         * level : 1
         * level_anchor : 1
         * is_follow : 1
         * is_follow_me : 0
         */

        private int id;
        public String nickname;
        private String headimgurl;
        private int level;
        private int level_anchor;
        private int is_follow;
        private int is_follow_me;

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

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }

        public int getIs_follow_me() {
            return is_follow_me;
        }

        public void setIs_follow_me(int is_follow_me) {
            this.is_follow_me = is_follow_me;
        }

        @Override
        public String chinese() {
            return nickname;
        }
    }
}
