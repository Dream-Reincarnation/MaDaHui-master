package com.ajiani.maidahui.bean.login;

public class VerfitySuBean {

    /**
     * code : 1
     * info : 验证成功，请使用该TOKEN设置密码
     * data : {"token":"d55d427553c24b9e522dd3b9623a2524"}
     * user :
     * timestamp : 1566784049
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
         * token : d55d427553c24b9e522dd3b9623a2524
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
