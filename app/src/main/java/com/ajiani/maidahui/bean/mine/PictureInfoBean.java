package com.ajiani.maidahui.bean.mine;

import java.util.List;

public class PictureInfoBean {


    /**
     * code : 1
     * info : 操作成功
     * data : {"aid":19,"title":"拉卡拉卡拉","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/a1/066426bb0d1cebb19af9034ac2d39c.jpg","video_type":"image","star":0,"is_star":"0","comment":0,"share":0,"count":0,"fire":0,"create_time":"2019-09-16 11:21:47","format_create_time":"1小时前","landmark":"","topic":"","user_id":100003,"nickname":"手机用户4","headimgurl":"https://www.maidahui.com/images/face.png","leval":1,"level_anchor":1,"distance":1.216345169284051E7,"format_distance":"12163.45KM","longitude":"0.0000000","latitude":"0.0000000","video":"https://www.maidahui.com/images/none.png","audio":"https://www.maidahui.com/images/none.png","music":"","pictrue":["https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/a1/066426bb0d1cebb19af9034ac2d39c.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/94/852895dd7bd1c4ca12efbee8a707ed.png","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/94/852895dd7bd1c4ca12efbee8a707ed.png"],"goods":[{"sort":1,"lists":[{"model":0,"video_id":19,"sort":1,"goods_id":2,"name":"Youngor/雅戈尔【DP免烫】 2019夏季新品 衬衫男短袖 商务休闲","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","link":"","goods_type":0,"top":518,"left":757,"start":0,"end":0,"shop_id":5,"direction":1,"price":"499.00"},{"model":0,"video_id":19,"sort":1,"goods_id":4,"name":"代理商品测试","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","link":"","goods_type":0,"top":318,"left":289,"start":0,"end":0,"shop_id":5,"direction":1,"price":"40.00"}]}],"live_status":0,"role":0,"status":0,"gift":0}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"2.00","money_consume":"0.00","money_consumes":"0.00","votes":"900.00","votes_income":"0.00","votes_incomes":"1000.00","lock_votes":"0.00","integral":20,"user_type":""}
     * timestamp : 1568607936
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
         * aid : 191
         * title : 啦啦啦啦啦
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/e6/35ff14972dc499a69477fd99e94442.jpg
         * video_type : image
         * star : 0
         * is_star : 0
         * comment : 0
         * share : 0
         * count : 4
         * fire : 0
         * create_time : 2019-10-29 10:42:49
         * format_create_time : 8分钟前
         * landmark :
         * topic :
         * user_id : 100007
         * nickname : 尔等休要放肆
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/7c/20790c203e5a3682cd66ae2ca2f3f7.jpg
         * leval : 1
         * level_anchor : 1
         * distance : 1.216345169284051E7
         * format_distance : 12163.45KM
         * longitude : 0.0000000
         * latitude : 0.0000000
         * video : https://www.maidahui.com/images/none.png
         * audio : https://www.maidahui.com/images/none.png
         * music : {"music_id":601427388,"name":"卡路里（电影《西虹市首富》插曲）","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/30/7201d965416d4fa41f33892b580f64.mp3","thumb":"http://qukufile2.qianqian.com/data2/pic/8d356491f24692ff802cc49c80f51fee/612356223/612356223.jpg@s_2,w_500,h_500"}
         * pictrue : ["https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/e6/35ff14972dc499a69477fd99e94442.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/99/5a08a07ed5cfcf17c6c6210c76c5fa.png","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/80/4c3c21298f674a00256da1d647644a.jpg"]
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
        private String star;
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
        private Object music;
        private int live_status;
        private int role;
        private int status;
        private int gift;
        private String is_follow;
        private List<String> pictrue;
        private List<GoodsBean> goods;

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


        public String getStar() {
            return star;
        }

        public void setStar(String star) {
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

        public Object getMusic() {
            return music;
        }

        public void setMusic(Object music) {
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

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * sort : 1
             * lists : [{"model":0,"video_id":202,"sort":1,"goods_id":53,"name":"骨鸡爪920g 口水鸡零食鸡腿肉棒棒鸡 脱骨麻辣凤爪爪","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b1/c445891dcd15a50e301d6b92c44e82.jpg","link":"","goods_type":0,"top":600,"left":500,"start":0,"end":0,"shop_id":8,"direction":1,"price":"255.00"},{"model":0,"video_id":202,"sort":1,"goods_id":54,"name":"烤小鸡腿16只336g香辣奥尔良味卤鸡腿蜜汁卤味肉类零食整箱","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/88/e5b4a3d82dc49749c2c80b3d1f0c96.jpg","link":"","goods_type":0,"top":317,"left":449,"start":0,"end":0,"shop_id":8,"direction":1,"price":"199.00"}]
             */

            private int sort;
            private List<PictureInfoBean.DataBean.GoodsBean.ListsBean> lists;

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ListsBean> getLists() {
                return lists;
            }

            public void setLists(List<ListsBean> lists) {
                this.lists = lists;
            }

            public static class ListsBean {
                /**
                 * model : 0
                 * video_id : 202
                 * sort : 1
                 * goods_id : 53
                 * name : 骨鸡爪920g 口水鸡零食鸡腿肉棒棒鸡 脱骨麻辣凤爪爪
                 * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/b1/c445891dcd15a50e301d6b92c44e82.jpg
                 * link :
                 * goods_type : 0
                 * top : 600
                 * left : 500
                 * start : 0
                 * end : 0
                 * shop_id : 8
                 * direction : 1
                 * price : 255.00
                 */

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
            }
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
