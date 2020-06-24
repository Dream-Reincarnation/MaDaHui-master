package com.ajiani.maidahui.bean.mine;

import java.util.List;

public class PictureInfoBean2 {


    /**
     * code : 1
     * info : 操作成功
     * data : {"aid":205,"title":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ec/d6a73ad65060a714820c934a0edd0c.jpg","video_type":"image","star":0,"is_star":"0","comment":0,"share":0,"count":16,"fire":0,"create_time":"2019-11-02 17:11:03","format_create_time":"30分钟前","landmark":"","topic":"#傲慢与偏见","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7c/20790c203e5a3682cd66ae2ca2f3f7.jpg","leval":1,"level_anchor":1,"distance":"","format_distance":"","longitude":"0.0000000","latitude":"0.0000000","video":"https://www.maidahui.com/images/none.png","audio":"https://www.maidahui.com/images/none.png","music":{"music_id":568460363,"name":"无问","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/1f/66cb18d96395f6e22924db070e7fc2.mp3","thumb":"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_2,w_500,h_500","duration":258},"pictrue":["https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ec/d6a73ad65060a714820c934a0edd0c.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/5d/e3e2cf710273282402283173eecd47.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/cf/feb741510c9ef9e4c3a941124d7407.jpg"],"goods":[],"live_status":0,"role":0,"status":0,"gift":0,"is_follow":"0"}
     * user : {"id":100007,"nickname":"尔等休要放肆","sex":1,"money":"8992.00","money_consume":"0.00","money_consumes":"1010.00","votes":"591.60","votes_income":"0.00","votes_incomes":"1622.00","lock_votes":"0.00","integral":91754,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7c/20790c203e5a3682cd66ae2ca2f3f7.jpg"}
     * timestamp : 1572687698
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
         * aid : 205
         * title : 哈哈哈哈哈哈哈哈哈哈哈哈哈哈
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ec/d6a73ad65060a714820c934a0edd0c.jpg
         * video_type : image
         * star : 0
         * is_star : 0
         * comment : 0
         * share : 0
         * count : 16
         * fire : 0
         * create_time : 2019-11-02 17:11:03
         * format_create_time : 30分钟前
         * landmark :
         * topic : #傲慢与偏见
         * user_id : 100007
         * nickname : 尔等休要放肆
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7c/20790c203e5a3682cd66ae2ca2f3f7.jpg
         * leval : 1
         * level_anchor : 1
         * distance :
         * format_distance :
         * longitude : 0.0000000
         * latitude : 0.0000000
         * video : https://www.maidahui.com/images/none.png
         * audio : https://www.maidahui.com/images/none.png
         * music : {"music_id":568460363,"name":"无问","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/1f/66cb18d96395f6e22924db070e7fc2.mp3","thumb":"http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_2,w_500,h_500","duration":258}
         * pictrue : ["https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ec/d6a73ad65060a714820c934a0edd0c.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/5d/e3e2cf710273282402283173eecd47.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/cf/feb741510c9ef9e4c3a941124d7407.jpg"]
         * goods : []
         * live_status : 0
         * role : 0
         * status : 0
         * gift : 0
         * is_follow : 0
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
        private String distance;
        private String format_distance;
        private String longitude;
        private String latitude;
        private String video;
        private String audio;
        private MusicBean music;
        private int live_status;
        private int role;
        private int status;
        private int gift;
        private String is_follow;
        private List<String> pictrue;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
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

        public MusicBean getMusic() {
            return music;
        }

        public void setMusic(MusicBean music) {
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

        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public List<String> getPictrue() {
            return pictrue;
        }

        public void setPictrue(List<String> pictrue) {
            this.pictrue = pictrue;
        }

        public List<?> getGoods() {
            return goods;
        }

        public void setGoods(List<?> goods) {
            this.goods = goods;
        }

        public static class MusicBean {
            /**
             * music_id : 568460363
             * name : 无问
             * url : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/1f/66cb18d96395f6e22924db070e7fc2.mp3
             * thumb : http://qukufile2.qianqian.com/data2/pic/6c0e494d5c69133b874d3a29edfd50a8/568459104/568459104.jpg@s_2,w_500,h_500
             * duration : 258
             */

            private int music_id;
            private String name;
            private String url;
            private String thumb;
            private int duration;

            public int getMusic_id() {
                return music_id;
            }

            public void setMusic_id(int music_id) {
                this.music_id = music_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }
        }
    }

    public static class UserBean {
        /**
         * id : 100007
         * nickname : 尔等休要放肆
         * sex : 1
         * money : 8992.00
         * money_consume : 0.00
         * money_consumes : 1010.00
         * votes : 591.60
         * votes_income : 0.00
         * votes_incomes : 1622.00
         * lock_votes : 0.00
         * integral : 91754
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
