package com.ajiani.maidahui.bean;

public class ParamsBean {

    /**
     * code : 1
     * info : success
     * data : {"cash_poundage_pro":"5","cash_min_money":"100","socket_message_server":"http://139.129.102.90:19965","socket_live_server":"http://139.129.102.90:19967","wechat_open_appid":"wxe52595c56f1bfd66","wechat_open_appsecret":"dbcb43708beb61abc0b5ddfea869bc9d","umeng_ios_appkey":"5d8d68543fc1959d10000a2b","umeng_ios_message_secret":"","umeng_android_appkey":"5d8d67ec0cafb2d543000d20","umeng_android_message_secret":"","ios_veson":"1","ios_check_veson":"0","ios_veson_content":"","ios_url":"","android_veson":"","android_veson_content":"","android_url":""}
     * user :
     * timestamp : 1570324632
     */

    private String code;
    private String info;
    private DataBean data;
    private String user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
         * cash_poundage_pro : 5
         * cash_min_money : 100
         * socket_message_server : http://139.129.102.90:19965
         * socket_live_server : http://139.129.102.90:19967
         * wechat_open_appid : wxe52595c56f1bfd66
         * wechat_open_appsecret : dbcb43708beb61abc0b5ddfea869bc9d
         * umeng_ios_appkey : 5d8d68543fc1959d10000a2b
         * umeng_ios_message_secret :
         * umeng_android_appkey : 5d8d67ec0cafb2d543000d20
         * umeng_android_message_secret :
         * ios_veson : 1
         * ios_check_veson : 0
         * ios_veson_content :
         * ios_url :
         * android_veson :
         * android_veson_content :
         * android_url :
         */

        private String cash_poundage_pro;
        private String cash_min_money;
        private String socket_message_server;
        private String socket_live_server;
        private String wechat_open_appid;
        private String wechat_open_appsecret;
        private String umeng_ios_appkey;
        private String umeng_ios_message_secret;
        private String umeng_android_appkey;
        private String umeng_android_message_secret;
        private String ios_veson;
        private String ios_check_veson;
        private String ios_veson_content;
        private String ios_url;
        private String android_veson;
        private String android_veson_content;
        private String android_url;

        public String getCash_poundage_pro() {
            return cash_poundage_pro;
        }

        public void setCash_poundage_pro(String cash_poundage_pro) {
            this.cash_poundage_pro = cash_poundage_pro;
        }

        public String getCash_min_money() {
            return cash_min_money;
        }

        public void setCash_min_money(String cash_min_money) {
            this.cash_min_money = cash_min_money;
        }

        public String getSocket_message_server() {
            return socket_message_server;
        }

        public void setSocket_message_server(String socket_message_server) {
            this.socket_message_server = socket_message_server;
        }

        public String getSocket_live_server() {
            return socket_live_server;
        }

        public void setSocket_live_server(String socket_live_server) {
            this.socket_live_server = socket_live_server;
        }

        public String getWechat_open_appid() {
            return wechat_open_appid;
        }

        public void setWechat_open_appid(String wechat_open_appid) {
            this.wechat_open_appid = wechat_open_appid;
        }

        public String getWechat_open_appsecret() {
            return wechat_open_appsecret;
        }

        public void setWechat_open_appsecret(String wechat_open_appsecret) {
            this.wechat_open_appsecret = wechat_open_appsecret;
        }

        public String getUmeng_ios_appkey() {
            return umeng_ios_appkey;
        }

        public void setUmeng_ios_appkey(String umeng_ios_appkey) {
            this.umeng_ios_appkey = umeng_ios_appkey;
        }

        public String getUmeng_ios_message_secret() {
            return umeng_ios_message_secret;
        }

        public void setUmeng_ios_message_secret(String umeng_ios_message_secret) {
            this.umeng_ios_message_secret = umeng_ios_message_secret;
        }

        public String getUmeng_android_appkey() {
            return umeng_android_appkey;
        }

        public void setUmeng_android_appkey(String umeng_android_appkey) {
            this.umeng_android_appkey = umeng_android_appkey;
        }

        public String getUmeng_android_message_secret() {
            return umeng_android_message_secret;
        }

        public void setUmeng_android_message_secret(String umeng_android_message_secret) {
            this.umeng_android_message_secret = umeng_android_message_secret;
        }

        public String getIos_veson() {
            return ios_veson;
        }

        public void setIos_veson(String ios_veson) {
            this.ios_veson = ios_veson;
        }

        public String getIos_check_veson() {
            return ios_check_veson;
        }

        public void setIos_check_veson(String ios_check_veson) {
            this.ios_check_veson = ios_check_veson;
        }

        public String getIos_veson_content() {
            return ios_veson_content;
        }

        public void setIos_veson_content(String ios_veson_content) {
            this.ios_veson_content = ios_veson_content;
        }

        public String getIos_url() {
            return ios_url;
        }

        public void setIos_url(String ios_url) {
            this.ios_url = ios_url;
        }

        public String getAndroid_veson() {
            return android_veson;
        }

        public void setAndroid_veson(String android_veson) {
            this.android_veson = android_veson;
        }

        public String getAndroid_veson_content() {
            return android_veson_content;
        }

        public void setAndroid_veson_content(String android_veson_content) {
            this.android_veson_content = android_veson_content;
        }

        public String getAndroid_url() {
            return android_url;
        }

        public void setAndroid_url(String android_url) {
            this.android_url = android_url;
        }
    }
}
