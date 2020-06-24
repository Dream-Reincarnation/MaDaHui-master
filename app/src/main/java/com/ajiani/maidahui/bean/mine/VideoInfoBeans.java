package com.ajiani.maidahui.bean.mine;

import java.util.List;

public class VideoInfoBeans {

    /**
     * code : 1
     * info : 操作成功
     * data : {"aid":17,"title":"啦啦啦啦啦啦啦啦","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/55/9c616e05efcd13227c7d693751d046.jpg","video_type":"video","star":0,"is_star":"0","comment":0,"share":0,"count":0,"fire":0,"create_time":"2019-09-16 10:39:16","format_create_time":"刚刚","landmark":"","topic":"","user_id":100003,"nickname":"手机用户4","headimgurl":"https://www.maidahui.com/images/face.png","leval":1,"level_anchor":1,"distance":1.216345169284051E7,"format_distance":"12163.45KM","longitude":"0.0000000","latitude":"0.0000000","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/e4/f5c565d14bd63b61997b2ba7ba9644.mp4","audio":"https://www.maidahui.com/images/none.png","music":"","pictrue":[],"goods":[],"live_status":0,"role":0,"status":0,"gift":0}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"2.00","money_consume":"0.00","money_consumes":"0.00","votes":"900.00","votes_income":"0.00","votes_incomes":"1000.00","lock_votes":"0.00","integral":20,"user_type":""}
     * timestamp : 1568601575
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
         * aid : 17
         * title : 啦啦啦啦啦啦啦啦
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/55/9c616e05efcd13227c7d693751d046.jpg
         * video_type : video
         * star : 0
         * is_star : 0
         * comment : 0
         * share : 0
         * count : 0
         * fire : 0
         * create_time : 2019-09-16 10:39:16
         * format_create_time : 刚刚
         * landmark :
         * topic :
         * user_id : 100003
         * nickname : 手机用户4
         * headimgurl : https://www.maidahui.com/images/face.png
         * leval : 1
         * level_anchor : 1
         * distance : 1.216345169284051E7
         * format_distance : 12163.45KM
         * longitude : 0.0000000
         * latitude : 0.0000000
         * video : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/e4/f5c565d14bd63b61997b2ba7ba9644.mp4
         * audio : https://www.maidahui.com/images/none.png
         * music :
         * pictrue : []
         * goods : []
         * live_status : 0
         * role : 0
         * status : 0
         * gift : 0
         */

        private int aid;
        private String title;
        private String thumb;
        private String video_type;
        private String star;
        private String is_star;
        private String comment;
        private String share;
        private String count;
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
        private String longitude;
        private String latitude;
        private String video;
        private String audio;
        private String music;
        private int live_status;
        private int role;
        private int status;
        private int gift;
        private List<?> pictrue;
        private List<?> goods;

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


        public String getIs_star() {
            return is_star;
        }

        public void setIs_star(String is_star) {
            this.is_star = is_star;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
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

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }

        public int getLive_status() {
            return live_status;
        }

        public void setLive_status(int live_status) {
            this.live_status = live_status;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getGift() {
            return gift;
        }

        public void setGift(int gift) {
            this.gift = gift;
        }

        public List<?> getPictrue() {
            return pictrue;
        }

        public void setPictrue(List<?> pictrue) {
            this.pictrue = pictrue;
        }

        public List<?> getGoods() {
            return goods;
        }

        public void setGoods(List<?> goods) {
            this.goods = goods;
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
         * votes : 900.00
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
