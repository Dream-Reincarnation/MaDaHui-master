package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class CompleteBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"aid":943,"title":"钻戒 31克拉钻戒 你值得拥有HSOHV","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/06/b84e006835718d7314a5679409e62b.jpg","shop_price":"10.00","market_price":"10000.00","sales":43,"share_price":"0.10","extra_table":"goods","comment_good":14,"rebate_price":"0.10","rebate_integral":5,"share_count":0},{"aid":942,"title":"4D环绕音箱HRGBLSN环视","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/1d/2203cd4a4b0b42697c92e9f9635690.png","shop_price":"12.00","market_price":"300.00","sales":24,"share_price":"0.10","extra_table":"goods_group","comment_good":7,"rebate_price":"0.10","rebate_integral":18,"share_count":0},{"aid":950,"title":"茄果瓜类茄果瓜类茄果瓜类茄果瓜类茄果瓜类","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/64/86ac16808f4269f664886038dfc4b0.jpg","shop_price":"1.00","market_price":"10000.00","sales":7,"share_price":"0.10","extra_table":"goods_group","comment_good":1,"rebate_price":"0.10","rebate_integral":1,"share_count":0},{"aid":946,"title":"半加工蔬菜半加工蔬菜半加工蔬菜","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/96/a0f2e0c347c2770fc2476faa5e59b8.jpg","shop_price":"10000.00","market_price":"100000.00","sales":1,"share_price":"0.10","extra_table":"goods","comment_good":0,"rebate_price":"0.10","rebate_integral":15500,"share_count":0},{"aid":952,"title":"宠物犬玩具HAYCJUVUKHKBL","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/59/90c0a1c1388f438f0c3eba55bbc907.jpg","shop_price":"5.00","market_price":"100.00","sales":14,"share_price":"0.10","extra_table":"goods","comment_good":0,"rebate_price":"0.10","rebate_integral":5,"share_count":0},{"aid":951,"title":"叶菜类叶菜类叶菜类叶菜类叶菜类","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/72/f61138abf3bca7aec815bf5fbced12.jpg","shop_price":"10.00","market_price":"10000.00","sales":0,"share_price":"0.10","extra_table":"goods_customs","comment_good":0,"rebate_price":"0.10","rebate_integral":6,"share_count":0},{"aid":903,"title":"LADES蓝蒂丝碧海潮生初学者化妆刷套装散粉刷眼影刷全套美妆工具","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/f3/dcfde49ce5d166970b2b90e995ff06.jpg","shop_price":"98.00","market_price":"176.40","sales":6,"share_price":"4.90","extra_table":"goods","comment_good":2,"rebate_price":"4.90","rebate_integral":57,"share_count":0},{"aid":949,"title":"根茎类根茎类根茎类根茎类根茎类","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/56/3df275466c5dba340cb968aeb12bd4.jpg","shop_price":"1.00","market_price":"1.00","sales":0,"share_price":"0.00","extra_table":"goods_duobao","comment_good":0,"rebate_price":"0.00","rebate_integral":0,"share_count":0},{"aid":947,"title":"葱姜蒜椒葱姜蒜椒葱姜蒜椒葱姜蒜椒","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/9e/1fff9bd973840acc26ce6d09d8b35e.jpg","shop_price":"100000.00","market_price":"100000.00","sales":0,"share_price":"0.00","extra_table":"goods_exchange","comment_good":0,"rebate_price":"0.10","rebate_integral":0,"share_count":0},{"aid":953,"title":"达芬奇原作 星空HOIHBIAYNCKO","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/48/fda37a995b13712085e3eec3cc10fd.jpg","shop_price":"15.00","market_price":"11111.00","sales":1,"share_price":"0.10","extra_table":"goods_customs","comment_good":0,"rebate_price":"0.50","rebate_integral":20,"share_count":0},{"aid":948,"title":"鲜菌菇鲜菌菇鲜菌菇鲜菌菇鲜菌菇","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/e1/103f441c9ba3585d2a261c88049baa.jpg","shop_price":"10000.00","market_price":"1000000.00","sales":0,"share_price":"0.10","extra_table":"goods_seckill","comment_good":0,"rebate_price":"0.10","rebate_integral":18700,"share_count":0}]
     */

    private String code;
    private String info;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * aid : 943
         * title : 钻戒 31克拉钻戒 你值得拥有HSOHV
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/06/b84e006835718d7314a5679409e62b.jpg
         * shop_price : 10.00
         * market_price : 10000.00
         * sales : 43
         * share_price : 0.10
         * extra_table : goods
         * comment_good : 14
         * rebate_price : 0.10
         * rebate_integral : 5
         * share_count : 0
         */

        private int aid;
        private String title;
        private String thumb;
        private String shop_price;
        private String market_price;
        private int sales;
        private String share_price;
        private String extra_table;
        private int comment_good;
        private String rebate_price;
        private int rebate_integral;
        private int share_count;
        private boolean isSel;


        public boolean isSel() {
            return isSel;
        }

        public void setSel(boolean sel) {
            isSel = sel;
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
    }
}
