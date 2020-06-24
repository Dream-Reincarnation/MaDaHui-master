package com.ajiani.maidahui.bean.mine;

import java.util.List;

public class CommentListBeans {


    /**
     * code : 1
     * info : 获取成功
     * data : [{"aid":12,"user_id":100012,"nickname":"桂","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","star":1,"is_star":"0","content":"钢刚分","create_time":"2019-11-11 09:42:10","format_create_time":"8天前","score":5,"replay":[{"aid":18,"user_id":100003,"nickname":"大风起兮龙飞翔","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg","star":0,"is_star":"0","content":"还是睡觉觉","create_time":"2019-11-19 11:57:08","format_create_time":"6分钟前","score":5}]}]
     * user : {"id":100003,"nickname":"大风起兮龙飞翔","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"0.00","votes_income":"0.00","votes_incomes":"0.00","lock_votes":"0.00","integral":0,"user_auth":1,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg"}
     * timestamp : 1574136238
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
         * nickname : 大风起兮龙飞翔
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 0.00
         * votes_income : 0.00
         * votes_incomes : 0.00
         * lock_votes : 0.00
         * integral : 0
         * user_auth : 1
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
         * aid : 12
         * user_id : 100012
         * nickname : 桂
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132
         * star : 1
         * is_star : 0
         * content : 钢刚分
         * create_time : 2019-11-11 09:42:10
         * format_create_time : 8天前
         * score : 5
         * replay : [{"aid":18,"user_id":100003,"nickname":"大风起兮龙飞翔","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg","star":0,"is_star":"0","content":"还是睡觉觉","create_time":"2019-11-19 11:57:08","format_create_time":"6分钟前","score":5}]
         */

        private int aid;
        private int user_id;
        private String nickname;
        private String headimgurl;
        private int star;
        private String is_star;
        private String content;
        private String create_time;
        private String format_create_time;
        private int score;
        private List<ReplayBean> replay;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getIs_star() {
            return is_star;
        }

        public void setIs_star(String is_star) {
            this.is_star = is_star;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getFormat_create_time() {
            return format_create_time;
        }

        public void setFormat_create_time(String format_create_time) {
            this.format_create_time = format_create_time;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<ReplayBean> getReplay() {
            return replay;
        }

        public void setReplay(List<ReplayBean> replay) {
            this.replay = replay;
        }

        public static class ReplayBean {
            /**
             * aid : 18
             * user_id : 100003
             * nickname : 大风起兮龙飞翔
             * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg
             * star : 0
             * is_star : 0
             * content : 还是睡觉觉
             * create_time : 2019-11-19 11:57:08
             * format_create_time : 6分钟前
             * score : 5
             */

            private int aid;
            private int user_id;
            private String nickname;
            private String headimgurl;
            private int star;
            private String is_star;
            private String content;
            private String create_time;
            private String format_create_time;
            private int score;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
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

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public String getIs_star() {
                return is_star;
            }

            public void setIs_star(String is_star) {
                this.is_star = is_star;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getFormat_create_time() {
                return format_create_time;
            }

            public void setFormat_create_time(String format_create_time) {
                this.format_create_time = format_create_time;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }
    }
}
