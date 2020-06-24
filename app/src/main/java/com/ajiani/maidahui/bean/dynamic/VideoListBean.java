package com.ajiani.maidahui.bean.dynamic;

import com.ajiani.maidahui.bean.mine.VideoInfoBean;

import java.io.Serializable;
import java.util.List;

public class VideoListBean {
    /**
     * code : 1
     * info : 操作成功
     * data : [{"aid":210,"title":"你看这个歌 真白","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/bb/647f1486f9f10d9c938022f8b49b84.png","video_type":"video","star":14,"is_star":"0","comment":6,"share":0,"count":526,"fire":0,"create_time":"2019-11-06 17:22:30","format_create_time":"19天前","landmark":"","topic":"","user_id":100001,"nickname":"上天吗","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/99/5994615890200a119013d3a4ca1d9f.png","leval":1,"level_anchor":1,"distance":1.2163435590158707E7,"format_distance":"12163.43km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/68/b3682aaf1c69b37738cdd26f9222c7.mp4","goods":[]},{"aid":218,"title":"啦啦啦","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/1e/9d3f4f3fbfe125f877e6133d0440c6.jpg","video_type":"video","star":6,"is_star":"0","comment":14,"share":0,"count":550,"fire":0,"create_time":"2019-11-07 20:38:09","format_create_time":"17天前","landmark":"","topic":"","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg","leval":1,"level_anchor":1,"distance":31.436359998275993,"format_distance":"31.4m","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/be/3dc7db3032806774006b846621a59f.mp4","goods":[]},{"aid":219,"title":"最近 你的每个黄昏是否都有人陪","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/d7/8501fb8f2b15566d4268cfa03dde01.png","video_type":"video","star":6,"is_star":"1","comment":1,"share":0,"count":429,"fire":0,"create_time":"2019-11-08 10:07:37","format_create_time":"17天前","landmark":"","topic":"","user_id":100001,"nickname":"上天吗","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/99/5994615890200a119013d3a4ca1d9f.png","leval":1,"level_anchor":1,"distance":1.2163435590158707E7,"format_distance":"12163.43km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/f8/a9c2436f9864eb746958f7a2c1ccd0.mp4","goods":[]},{"aid":217,"title":"你看这是谁\n","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/fe/2fc50c4773fc3934126ac470145211.png","video_type":"video","star":5,"is_star":"0","comment":0,"share":0,"count":289,"fire":0,"create_time":"2019-11-07 14:44:18","format_create_time":"18天前","landmark":"","topic":"","user_id":100015,"nickname":"恰同学少年","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/5a/f1d37a4156f3fe571f0d46391fb354.png","leval":1,"level_anchor":1,"distance":1.2163435590158707E7,"format_distance":"12163.43km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/b0/38e84f49a69d9afdf13484ba56f846.mp4","goods":[]},{"aid":216,"title":"外婆和第八集哦给您嘻嘻嘻","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/1a/c1fc2cb7c6f727c8a950dfa4752a86.jpg","video_type":"video","star":7,"is_star":"0","comment":1,"share":0,"count":275,"fire":0,"create_time":"2019-11-07 10:51:43","format_create_time":"18天前","landmark":"","topic":"","user_id":100009,"nickname":"小草","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/21/4b7d915b69f23ded3f8f5088201c79.png","leval":1,"level_anchor":1,"distance":100.30715419085857,"format_distance":"0.10km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/2b/5446b0b1eb611a0b04feebd0c5defe.mp4","goods":[]},{"aid":224,"title":"手机看的","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ba/142a19e294fc96ad553ae8e540fd4f.jpg","video_type":"video","star":3,"is_star":"1","comment":0,"share":0,"count":96,"fire":0,"create_time":"2019-11-20 18:20:19","format_create_time":"5天前","landmark":"","topic":"","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg","leval":1,"level_anchor":1,"distance":39.727101833773645,"format_distance":"39.7m","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/8f/6f77f63c860c600c29e47a8bd9b193.mp4","goods":[]},{"aid":222,"title":"哦随口说说","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/17/ed52c260d4a865026027147d931a5e.jpg","video_type":"video","star":3,"is_star":"1","comment":0,"share":0,"count":96,"fire":0,"create_time":"2019-11-20 11:47:25","format_create_time":"5天前","landmark":"","topic":"","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg","leval":1,"level_anchor":1,"distance":169.4415116362132,"format_distance":"0.16km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/bd/0db889ba344b1c5a2072098f58642f.mp4","goods":[]},{"aid":226,"title":"就是就是几十块","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ba/142a19e294fc96ad553ae8e540fd4f.jpg","video_type":"video","star":2,"is_star":"1","comment":0,"share":0,"count":92,"fire":0,"create_time":"2019-11-21 16:04:24","format_create_time":"4天前","landmark":"","topic":"#傲慢与偏见","user_id":100007,"nickname":"尔等休要放肆","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg","leval":1,"level_anchor":1,"distance":39.727101833773645,"format_distance":"39.7m","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/dc/71bca2b9483d06245072dd7d6da5a5.mp4","goods":[{"name":"送香槟杯2个星空酒起泡酒鸡尾酒火焰气泡网红酒整箱抖音甜葡萄酒","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7e/e776d225c66785d5a5d1f2d2d16d85.jpg","price":"128.00"},{"name":"网红水晶绒四件套冬季加厚双面珊瑚绒韩版被套床单床上1.8m法兰绒","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/18/79c78cf461c1209de3b9c507c64708.jpg","price":"318.00"},{"name":"男士手表2019新款虫洞概念高中生手表男学生青少年潮流防水机械表","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/0a/e5c2e125f280f52be16b8726c54b5d.jpg","price":"95.00"}]},{"aid":227,"title":"天下有情人-横屏测试","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/8a/48109d3150951d950a39db4d78754b.png","video_type":"video","star":3,"is_star":"1","comment":0,"share":0,"count":57,"fire":0,"create_time":"2019-11-23 13:03:25","format_create_time":"2天前","landmark":"","topic":"今日份微笑","user_id":100001,"nickname":"上天吗","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/99/5994615890200a119013d3a4ca1d9f.png","leval":1,"level_anchor":1,"distance":1.2163435590158707E7,"format_distance":"12163.43km","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/1b/9f8bbf5dcf1e7a278aa3e1ad0b6913.mp4","goods":[]}]
     * user : {"id":100007,"nickname":"尔等休要放肆","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"1.00","votes_income":"0.00","votes_incomes":"1300.00","lock_votes":"0.00","integral":1419,"user_auth":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b7/cc0a620aeb6839f51f752eb8eb419e.jpg"}
     * timestamp : 1574683023
     */

    private String code;
    private String info;
    private UserBean user;
    private int timestamp;
    private List<VideoInfoBean.DataBean> data;

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

    public List<VideoInfoBean.DataBean> getData() {
        return data;
    }

    public void setData(List<VideoInfoBean.DataBean> data) {
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

    public static class DataBean implements Serializable {

        private int aid;
        private String title;
        private String thumb;
        private String video_type;
        private String star;
        private String is_star;
        private String comment;
        private String share;
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
        private String is_top;
        private int is_download;
        private int is_comment;
        private String poi_id;
        private List<?> pictrue;
        private List<GoodsBean> goods;
        private List<?> tags;

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

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
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

        public String getIs_top() {
            return is_top;
        }

        public int getIs_download() {
            return is_download;
        }

        public void setIs_download(int is_download) {
            this.is_download = is_download;
        }

        public int getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(int is_comment) {
            this.is_comment = is_comment;
        }

        public String getPoi_id() {
            return poi_id;
        }

        public void setPoi_id(String poi_id) {
            this.poi_id = poi_id;
        }

        public List<?> getPictrue() {
            return pictrue;
        }

        public void setPictrue(List<?> pictrue) {
            this.pictrue = pictrue;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }




        public static class MusicBean {
            /**
             * music_id :
             * name :
             * url :
             * thumb :
             * duration :
             * nickname :
             * headimgurl :
             */

            private String music_id;
            private String name;
            private String url;
            private String thumb;
            private String duration;
            private String nickname;
            private String headimgurl;

            public String getMusic_id() {
                return music_id;
            }

            public void setMusic_id(String music_id) {
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

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
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
        }

        public static class GoodsBean {
            /**
             * aid : 156
             * model : 0
             * video_id : 221
             * sort : 0
             * goods_id : 933
             * name : 黑色皮鞋男士潮流软底商务正装青年圆头休闲鞋英伦韩版内增高男鞋
             * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/88/972c0442b038a6fa22a0e95625066b.jpg
             * link :
             * goods_type : 0
             * top : 0
             * left : 0
             * start : 1
             * end : 3
             * shop_id : 9
             * direction : 0
             * price : 138.00
             * stock : 5271
             * sales : 0
             */

            private int aid;
            private int model;
            private int video_id;
            private int sort;
            private int goods_id;
            private String name;
            private String thumb;
            private String link;
            private int goods_type;
            private int top;
            private int left;
            private int start;
            private int end;
            private int shop_id;
            private int direction;
            private String price;
            private int stock;
            private int sales;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getModel() {
                return model;
            }

            public void setModel(int model) {
                this.model = model;
            }

            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(int goods_type) {
                this.goods_type = goods_type;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getStart() {
                return start;
            }

            public void setStart(int start) {
                this.start = start;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public int getShop_id() {
                return shop_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public int getDirection() {
                return direction;
            }

            public void setDirection(int direction) {
                this.direction = direction;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getSales() {
                return sales;
            }

            public void setSales(int sales) {
                this.sales = sales;
            }
        }

    }


}
