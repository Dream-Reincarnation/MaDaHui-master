package com.ajiani.maidahui.bean.dynamic;

public class VideoAleary  {

    /**
     * code : 304
     * info : 操作成功
     * data : {"id":49,"path":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/a1/325a0be075601c12af5e86dc3a68f4.wav","name":"1567417228505_8000.wav","md5":"a1325a0be075601c12af5e86dc3a68f4","duration":30}
     * user :
     * timestamp : 1567473841
     */

    private int code;
    private String info;
    private DataBean data;
    private String user;
    private int timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * id : 49
         * path : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/a1/325a0be075601c12af5e86dc3a68f4.wav
         * name : 1567417228505_8000.wav
         * md5 : a1325a0be075601c12af5e86dc3a68f4
         * duration : 30
         */

        private int id;
        private String path;
        private String name;
        private String md5;
        private int duration;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }
}
