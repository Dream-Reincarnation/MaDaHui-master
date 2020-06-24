package com.ajiani.maidahui.bean.sockets;

public class OfferBean {

    /**
     * _method_ : SendMsg
     * action : 7
     * msgid : 10000151000031570707732993425
     * userType : 1
     * nickname : U_5d5173640262e
     * avatar : https://www.maidahui.com/images/face.png
     * uid : 100001
     * toid : 100003
     * shopid : 5
     * ct : 定制报价单已生成,请付款
     * extra : {"goods_id":14,"title":"秒杀产品测试","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/4e/73c0acf1524bd4c565fb6be5548240.png","price":"300.00","sku":"123","sku_path":"f80e261eb75d0f0e7b6b63713afa4669","remark":"好啊"}
     * timestamp : 1570707732
     */

    private String _method_;
    private int action;
    private String msgid;
    private int userType;
    private String nickname;
    private String avatar;
    private int uid;
    private String toid;
    private int shopid;
    private String ct;
    private ExtraBean extra;
    private int timestamp;

    public String get_method_() {
        return _method_;
    }

    public void set_method_(String _method_) {
        this._method_ = _method_;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class ExtraBean {
        /**
         * goods_id : 14
         * title : 秒杀产品测试
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/4e/73c0acf1524bd4c565fb6be5548240.png
         * price : 300.00
         * sku : 123
         * sku_path : f80e261eb75d0f0e7b6b63713afa4669
         * remark : 好啊
         */

        private int goods_id;
        private String title;
        private String thumb;
        private String price;
        private String sku;
        private String goods_price;
        private String sku_path;
        private String remark;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(String goods_price) {
            this.goods_price = goods_price;
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

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getSku_path() {
            return sku_path;
        }

        public void setSku_path(String sku_path) {
            this.sku_path = sku_path;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
