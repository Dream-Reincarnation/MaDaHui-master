package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class PersonVideoBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"aid":218,"title":"啦啦啦","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/1e/9d3f4f3fbfe125f877e6133d0440c6.jpg","video_type":"video","star":7,"is_star":"1","comment":14,"share":0,"count":524,"fire":0,"create_time":"2019-11-07 20:38:09","format_create_time":"14天前","landmark":"","topic":"","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg","leval":1,"level_anchor":1,"distance":131.92417242047733,"format_distance":"0.13km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/be/3dc7db3032806774006b846621a59f.mp4","goods":[]},{"aid":222,"title":"哦随口说说","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/17/ed52c260d4a865026027147d931a5e.jpg","video_type":"video","star":2,"is_star":"1","comment":0,"share":0,"count":56,"fire":0,"create_time":"2019-11-20 11:47:25","format_create_time":"2天前","landmark":"","topic":"","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg","leval":1,"level_anchor":1,"distance":36.8836638375643,"format_distance":"36.8m","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/bd/0db889ba344b1c5a2072098f58642f.mp4","goods":[]},{"aid":224,"title":"手机看的","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ba/142a19e294fc96ad553ae8e540fd4f.jpg","video_type":"video","star":2,"is_star":"1","comment":0,"share":0,"count":50,"fire":0,"create_time":"2019-11-20 18:20:19","format_create_time":"1天前","landmark":"","topic":"","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg","leval":1,"level_anchor":1,"distance":119.5936746048778,"format_distance":"0.11km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/8f/6f77f63c860c600c29e47a8bd9b193.mp4","goods":[]}]
     * user : {"id":100007,"nickname":"尔等休要放肆","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"1.00","votes_income":"0.00","votes_incomes":"1300.00","lock_votes":"0.00","integral":1419,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg"}
     * timestamp : 1574417376
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

    public static class DataBean {
        /**
         * aid : 218
         * title : 啦啦啦
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/1e/9d3f4f3fbfe125f877e6133d0440c6.jpg
         * video_type : video
         * star : 7
         * is_star : 1
         * comment : 14
         * share : 0
         * count : 524
         * fire : 0
         * create_time : 2019-11-07 20:38:09
         * format_create_time : 14天前
         * landmark :
         * topic :
         * user_id : 100007
         * nickname : 尔等休要放肆
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg
         * leval : 1
         * level_anchor : 1
         * distance : 131.92417242047733
         * format_distance : 0.13km
         * video : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/be/3dc7db3032806774006b846621a59f.mp4
         * goods : []
         */

        private int aid;
        private String title;
        private String thumb;
        private String video_type;
        private int star;
        private String is_star;
        private int comment;
        private int share;
        private int count;
        private int fire;
        private String create_time;
        private String format_create_time;
        private String landmark;
        private String topic;
        private int user_id;
        private String nickname;
        private String headimgurl;
        private int leval;
        private int level_anchor;
        private double distance;
        private String format_distance;
        private String video;
        private List<GoodsBean> goods;
        public static class GoodsBean {
            /**
             * name : 黑色皮鞋男士潮流软底商务正装青年圆头休闲鞋英伦韩版内增高男鞋
             * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/88/972c0442b038a6fa22a0e95625066b.jpg
             * price : 138.00
             */
            private String name;
            private String thumb;
            private String price;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getVideo_type() {
            return video_type;
        }

        public void setVideo_type(String video_type) {
            this.video_type = video_type;
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

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFire() {
            return fire;
        }

        public void setFire(int fire) {
            this.fire = fire;
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

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
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

        public int getLeval() {
            return leval;
        }

        public void setLeval(int leval) {
            this.leval = leval;
        }

        public int getLevel_anchor() {
            return level_anchor;
        }

        public void setLevel_anchor(int level_anchor) {
            this.level_anchor = level_anchor;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getFormat_distance() {
            return format_distance;
        }

        public void setFormat_distance(String format_distance) {
            this.format_distance = format_distance;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }
    }

}
