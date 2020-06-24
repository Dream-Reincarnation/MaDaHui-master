package com.ajiani.maidahui.bean.mine;

import com.google.gson.annotations.SerializedName;

public class WeChatBean {

    /**
     * code : 1
     * info : 请按照此配置调起支付
     * data : {"appid":"wxe52595c56f1bfd66","partnerid":"1516783291","prepayid":"wx11151843512086f2b9d42f3f1378736500","package":"Sign=WXPay","noncestr":"xplxneso6cstg71f7gk842olgw9x230y","timestamp":1568186323,"sign":"DDAC6061B4C80D4A2F919D81879A2F23"}
     * user :
     * timestamp : 1568186323
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
         * appid : wxe52595c56f1bfd66
         * partnerid : 1516783291
         * prepayid : wx11151843512086f2b9d42f3f1378736500
         * package : Sign=WXPay
         * noncestr : xplxneso6cstg71f7gk842olgw9x230y
         * timestamp : 1568186323
         * sign : DDAC6061B4C80D4A2F919D81879A2F23
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
