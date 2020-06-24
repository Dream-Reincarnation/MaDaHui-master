package com.ajiani.maidahui.bean.mine;

import java.io.Serializable;
import java.util.List;

public class VideoInfoBean  implements Serializable{


    /**
     * code : 1
     * info : 操作成功
     * data : {"aid":221,"title":"哈喽","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/eb/4bc201012261f260ce01ff5ac8e1a5.jpg","video_type":"video","star":5,"is_star":"0","comment":0,"share":0,"count":761,"fire":0,"create_time":"2019-11-14 17:13:04","format_create_time":"11月14日","landmark":"","topic":"","user_id":100012,"nickname":"桂花","headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","leval":1,"level_anchor":1,"distance":6952.515415103507,"format_distance":"6.95km","longitude":"113.6886900","latitude":"34.7879750","video":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/95/55ab890c506a1bbd4a0ca8b8a3dfaa.mp4","audio":"https://www.maidahui.com/images/none.png","music":{"music_id":"","name":"","url":"","thumb":"","duration":"","nickname":"","headimgurl":""},"pictrue":[],"goods":[{"aid":156,"model":0,"video_id":221,"sort":0,"goods_id":933,"name":"黑色皮鞋男士潮流软底商务正装青年圆头休闲鞋英伦韩版内增高男鞋","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/88/972c0442b038a6fa22a0e95625066b.jpg","link":"","goods_type":0,"top":0,"left":0,"start":1,"end":3,"shop_id":9,"direction":0,"price":"138.00","stock":5271,"sales":0},{"aid":157,"model":0,"video_id":221,"sort":0,"goods_id":929,"name":"送香槟杯2个星空酒起泡酒鸡尾酒火焰气泡网红酒整箱抖音甜葡萄酒.","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7e/e776d225c66785d5a5d1f2d2d16d85.jpg","link":"","goods_type":0,"top":0,"left":0,"start":1,"end":3,"shop_id":9,"direction":0,"price":"128.00","stock":900,"sales":0},{"aid":158,"model":0,"video_id":221,"sort":0,"goods_id":926,"name":"褚铁匠锌合金剃须刀手动刮胡刀男5层刀片头刮脸刀《套装可定制》","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/08/615bc3e578bc98abfa538f4cab4b4e.jpg","link":"","goods_type":0,"top":0,"left":0,"start":1,"end":3,"shop_id":9,"direction":0,"price":"108.00","stock":5292,"sales":2}],"live_status":0,"role":0,"status":1,"gift":0,"is_follow":"0","is_top":0,"is_download":1,"is_comment":0,"poi_id":"","tags":[]}
     * user : {"id":100007,"nickname":"小荷才露尖尖角","sex":1,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"96628.40","votes_income":"0.00","votes_incomes":"104020.50","lock_votes":"0.00","integral":4975,"user_auth":0,"true_name":"","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg"}
     * page :
     * timestamp : 1587009727
     */

    private int code;
    private String info;
    private DataBean data;
    private UserBean user;
    private String page;
    private int timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class DataBean implements Serializable {
        /**
         * aid : 221
         * title : 哈喽
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/eb/4bc201012261f260ce01ff5ac8e1a5.jpg
         * video_type : video
         * star : 5
         * is_star : 0
         * comment : 0
         * share : 0
         * count : 761
         * fire : 0
         * create_time : 2019-11-14 17:13:04
         * format_create_time : 11月14日
         * landmark :
         * topic :
         * user_id : 100012
         * nickname : 桂花
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132
         * leval : 1
         * level_anchor : 1
         * distance : 6952.515415103507
         * format_distance : 6.95km
         * longitude : 113.6886900
         * latitude : 34.7879750
         * video : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/95/55ab890c506a1bbd4a0ca8b8a3dfaa.mp4
         * audio : https://www.maidahui.com/images/none.png
         * music : {"music_id":"","name":"","url":"","thumb":"","duration":"","nickname":"","headimgurl":""}
         * pictrue : []
         * goods : [{"aid":156,"model":0,"video_id":221,"sort":0,"goods_id":933,"name":"黑色皮鞋男士潮流软底商务正装青年圆头休闲鞋英伦韩版内增高男鞋","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/88/972c0442b038a6fa22a0e95625066b.jpg","link":"","goods_type":0,"top":0,"left":0,"start":1,"end":3,"shop_id":9,"direction":0,"price":"138.00","stock":5271,"sales":0},{"aid":157,"model":0,"video_id":221,"sort":0,"goods_id":929,"name":"送香槟杯2个星空酒起泡酒鸡尾酒火焰气泡网红酒整箱抖音甜葡萄酒.","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7e/e776d225c66785d5a5d1f2d2d16d85.jpg","link":"","goods_type":0,"top":0,"left":0,"start":1,"end":3,"shop_id":9,"direction":0,"price":"128.00","stock":900,"sales":0},{"aid":158,"model":0,"video_id":221,"sort":0,"goods_id":926,"name":"褚铁匠锌合金剃须刀手动刮胡刀男5层刀片头刮脸刀《套装可定制》","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/08/615bc3e578bc98abfa538f4cab4b4e.jpg","link":"","goods_type":0,"top":0,"left":0,"start":1,"end":3,"shop_id":9,"direction":0,"price":"108.00","stock":5292,"sales":2}]
         * live_status : 0
         * role : 0
         * status : 1
         * gift : 0
         * is_follow : 0
         * is_top : 0
         * is_download : 1
         * is_comment : 0
         * poi_id :
         * tags : []
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
        private String fire;
        private String create_time;
        private String format_create_time;
        private String landmark;
        private String topic;
        private String user_id;
        private String nickname;
        private String headimgurl;
        private String leval;
        private String level_anchor;
        private String distance;
        private String format_distance;
        private String longitude;
        private String latitude;
        private String video;
        private String audio;
        private MusicBean music;
        private String live_status;
        private String role;
        private String status;
        private String gift;
        private String is_follow="0";
        private String is_top;
        private String is_download;
        private String is_comment;
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

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getFire() {
            return fire;
        }

        public void setFire(String fire) {
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
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

        public String getLeval() {
            return leval;
        }

        public void setLeval(String leval) {
            this.leval = leval;
        }

        public String getLevel_anchor() {
            return level_anchor;
        }

        public void setLevel_anchor(String level_anchor) {
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



        public String getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(String is_follow) {
            this.is_follow = is_follow;
        }

        public String getIs_top() {
            return is_top;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getIs_download() {
            return is_download;
        }

        public void setIs_download(String is_download) {
            this.is_download = is_download;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getLive_status() {
            return live_status;
        }

        public void setLive_status(String live_status) {
            this.live_status = live_status;
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
            private String model;
            private int video_id;
            private String sort;
            private int goods_id;
            private String name;
            private String thumb;
            private String link;
            private String goods_type;
            private int top;
            private String left;
            private String start;
            private String end;
            private String shop_id;
            private String direction;
            private String price;
            private int stock;
            private int sales;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }


            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
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


            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getLeft() {
                return left;
            }

            public void setLeft(String left) {
                this.left = left;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            public String getEnd() {
                return end;
            }

            public void setEnd(String end) {
                this.end = end;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
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

    public static class UserBean {
        /**
         * id : 100007
         * nickname : 小荷才露尖尖角
         * sex : 1
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 96628.40
         * votes_income : 0.00
         * votes_incomes : 104020.50
         * lock_votes : 0.00
         * integral : 4975
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
}
