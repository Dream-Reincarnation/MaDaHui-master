package com.ajiani.maidahui.bean.login;

public class LoginBean {

    /**
     * code : 1
     * info : 登录成功
     * data : {"user_id":"100003","user_login":"18831094384","token":"a5e95db2c62e97b584753cc852deef1d","expire_time":1567072622,"nickname":"手机用户4","mobile":"18831094384","sex":0,"user_auth":"","money":"","headimgurl":"http://mdh.wefsn.com/images/face.png"}
     * user :
     * timestamp : 1566467822
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
         * user_id : 100003
         * user_login : 18831094384
         * token : a5e95db2c62e97b584753cc852deef1d
         * expire_time : 1567072622
         * nickname : 手机用户4
         * mobile : 18831094384
         * sex : 0
         * user_auth :
         * money :
         * headimgurl : http://mdh.wefsn.com/images/face.png
         */

        private String user_id;
        private String user_login;
        private String token;
        private int expire_time;
        private String nickname;
        private String mobile;
        private int sex;
        private String user_auth;
        private String money;
        private String headimgurl;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getExpire_time() {
            return expire_time;
        }

        public void setExpire_time(int expire_time) {
            this.expire_time = expire_time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUser_auth() {
            return user_auth;
        }

        public void setUser_auth(String user_auth) {
            this.user_auth = user_auth;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }
    }
}
