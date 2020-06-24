package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class SMusicBean {

    /**
     * code : 1
     * info : 获取成功
     * data : [{"music_id":"257525194","name":"南山南","author":"赵小臭"},{"music_id":"256426576","name":"南山","author":"涵昱"},{"music_id":"289727508","name":"南山南","author":"牟茗"},{"music_id":"267640851","name":"南山观世音","author":"新韵传音"},{"music_id":"664504115","name":"终南山","author":"梦苇"}]
     * user :
     * timestamp : 1566437980
     */

    private String code;
    private String info;
    private String user;
    private int timestamp;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /*
         * music_id : 257525194
         * name : 南山南
         * author : 赵小臭
         */

        private String music_id;
        private String name;
        private String author;
        private boolean isshow;

        public boolean isIsshow() {
            return isshow;
        }

        public void setIsshow(boolean isshow) {
            this.isshow = isshow;
        }

        public String getMusic_id() {
            return music_id;
        }

        public void setMusic_id(String music_id) {
            this.music_id = music_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
