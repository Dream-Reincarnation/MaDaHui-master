package com.ajiani.maidahui.bean.chat;

import com.google.gson.annotations.SerializedName;

public class WeXinBean {

    /**
     * appid : wxe52595c56f1bfd66
     * partnerid : 1516783291
     * prepayid : wx281437227809984058f1cdb81737697800
     * package : Sign=WXPay
     * noncestr : j9p63kythnkoa6dxeuyuj4pp7kwnpe7n
     * timestamp : 1572244642
     * sign : D9F98A9DEBB083886A70414D0F010768
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
