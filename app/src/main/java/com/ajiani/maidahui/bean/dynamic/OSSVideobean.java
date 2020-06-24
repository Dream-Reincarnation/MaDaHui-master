package com.ajiani.maidahui.bean.dynamic;

public class OSSVideobean {

    /**
     * code : 1
     * info : 操作成功
     * data : {"id":"49","name":"1567417228505_8000.wav","path":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/a1/325a0be075601c12af5e86dc3a68f4.wav"}
     * user :
     * timestamp : 1567418418
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
         * id : 49
         * name : 1567417228505_8000.wav
         * path : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/a1/325a0be075601c12af5e86dc3a68f4.wav
         */

        private String id;
        private String name;
        private String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
