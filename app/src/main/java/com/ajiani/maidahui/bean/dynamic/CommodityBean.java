package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class CommodityBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"aid":18,"title":"团购商品测试","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","shop_price":"100.00","market_price":"400.00","stock":9,"sales":1,"share_price":"0.10","extra_table":"goods_group","extra_data":{"number":2,"duration":86400,"period_count":0,"is_original":1,"current":0},"comment_good":0,"rebate_price":"0.10","rebate_integral":2,"share_count":0},{"aid":16,"title":"抽奖产品测试","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","shop_price":"1.00","market_price":"10.00","stock":10,"sales":0,"share_price":"0.00","extra_table":"goods_duobao","extra_data":{"single_price":1,"price":200,"period_count":0,"number":200,"duration":172800,"current":0},"comment_good":0,"rebate_price":"0.00","rebate_integral":0,"share_count":0},{"aid":14,"title":"秒杀产品测试","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","shop_price":"20.00","market_price":"100.00","stock":44,"sales":0,"share_price":"0.10","extra_table":"goods_seckill","extra_data":{"times":"8,10,12,14,16,18","is_original":1,"is_kill":0},"comment_good":0,"rebate_price":"0.10","rebate_integral":1,"share_count":0},{"aid":6,"title":"换购商品测试","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","shop_price":"4.00","market_price":"100.00","stock":45,"sales":0,"share_price":"0.10","extra_table":"goods_exchange","extra_data":{"integral":10,"is_original":1},"comment_good":0,"rebate_price":"0.10","rebate_integral":1,"share_count":0},{"aid":2,"title":"Youngor/雅戈尔【DP免烫】 2019夏季新品 衬衫男短袖 商务休闲 蓝色 40","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","shop_price":"399.00","market_price":"499.00","stock":200,"sales":2,"share_price":"1.50","extra_table":"goods","extra_data":[],"comment_good":0,"rebate_price":"2.00","rebate_integral":20,"share_count":0},{"aid":4,"title":"代理商品测试","thumb":"https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png","shop_price":"20.00","market_price":"40.00","stock":29,"sales":0,"share_price":"0.00","extra_table":"goods_agent","extra_data":[],"comment_good":0,"rebate_price":"0.00","rebate_integral":0,"share_count":0}]
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"2.00","money_consume":"0.00","money_consumes":"0.00","votes":"0.00","votes_income":"0.00","votes_incomes":"0.00","lock_votes":"0.00","integral":20,"user_type":""}
     * timestamp : 1567656055
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
         * nickname : 手机用户4
         * sex : 0
         * money : 2.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 0.00
         * votes_income : 0.00
         * votes_incomes : 0.00
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

    public static class DataBean {
        /**
         * aid : 18
         * title : 团购商品测试
         * thumb : https://www.maidahui.com/uploads/image/81/23e326c146de116720b703ed3775d4.png
         * shop_price : 100.00
         * market_price : 400.00
         * stock : 9
         * sales : 1
         * share_price : 0.10
         * extra_table : goods_group
         * extra_data : {"number":2,"duration":86400,"period_count":0,"is_original":1,"current":0}
         * comment_good : 0
         * rebate_price : 0.10
         * rebate_integral : 2
         * share_count : 0
         */

        private int aid;
        private String title;
        private String thumb;
        private String shop_price;
        private String market_price;
        private int stock;
        private int sales;
        private String share_price;
        private String extra_table;
        private Object extra_data;
        private int comment_good;
        private String rebate_price;
        private int rebate_integral;
        private int share_count;

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

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
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

        public String getShare_price() {
            return share_price;
        }

        public void setShare_price(String share_price) {
            this.share_price = share_price;
        }

        public String getExtra_table() {
            return extra_table;
        }

        public void setExtra_table(String extra_table) {
            this.extra_table = extra_table;
        }

        public Object getExtra_data() {
            return extra_data;
        }

        public void setExtra_data(Object extra_data) {
            this.extra_data = extra_data;
        }

        public int getComment_good() {
            return comment_good;
        }

        public void setComment_good(int comment_good) {
            this.comment_good = comment_good;
        }

        public String getRebate_price() {
            return rebate_price;
        }

        public void setRebate_price(String rebate_price) {
            this.rebate_price = rebate_price;
        }

        public int getRebate_integral() {
            return rebate_integral;
        }

        public void setRebate_integral(int rebate_integral) {
            this.rebate_integral = rebate_integral;
        }

        public int getShare_count() {
            return share_count;
        }

        public void setShare_count(int share_count) {
            this.share_count = share_count;
        }

        public static class ExtraDataBean {
            /**
             * number : 2
             * duration : 86400
             * period_count : 0
             * is_original : 1
             * current : 0
             */

            private int number;
            private int duration;
            private int period_count;
            private int is_original;
            private int current;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getPeriod_count() {
                return period_count;
            }

            public void setPeriod_count(int period_count) {
                this.period_count = period_count;
            }

            public int getIs_original() {
                return is_original;
            }

            public void setIs_original(int is_original) {
                this.is_original = is_original;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }
        }
    }
}
