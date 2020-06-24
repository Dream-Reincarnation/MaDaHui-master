package com.ajiani.maidahui.bean;

public class UpLoadBean {
    /**
     * code : 1
     * info : 操作成功
     * data : {"id":112,"path":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/82/261407959353984f72a0e0df0620ff.jpg"}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"3.00","money_consume":"0.00","money_consumes":"0.00","votes":"98.85","votes_income":"0.00","votes_incomes":"100.23","lock_votes":"0.00","integral":30,"user_type":""}
     * timestamp : 1568254357
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
         * id : 112
         * path : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/82/261407959353984f72a0e0df0620ff.jpg
         */

        private int id;
        private String path;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }



    /**
     * code : 1
     * info : 操作成功
     * data : {"id":21,"path":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/1e/368721eb71296a94c59da77472abf3.jpg"}
     * user :
     * timestamp : 1567047878
     */

  /*  private String code;
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
        *//**
         * id : 21
         * path : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/1e/368721eb71296a94c59da77472abf3.jpg
         *//*

        private int id;
        private String path;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }*/
}
