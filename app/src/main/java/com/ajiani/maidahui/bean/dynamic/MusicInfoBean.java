package com.ajiani.maidahui.bean.dynamic;

public class MusicInfoBean {

    /**
     * code : 1
     * info : 获取成功
     * data : {"music_id":"121377633","name":"山水之间","author":"许嵩","thumb":"http://qukufile2.qianqian.com/data2/pic/7b437f7ffee6717ed017db37504487ca/579301812/579301812.jpg@s_2,w_500,h_500","url":"http://audio01.dmhmusic.com/71_53_T10038957304_128_4_1_0_sdk-cpm/0209/M00/11/D0/ChR461n07saAStuzAENy7y9P8_Q221.mp3?xcode=73f9f4588d0fb8154f7750821174765e8de6087","duration":276,"size":4420335}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"0.00","consumption":"","consumptions":"","votes":"0.00","votes_total":"","votes_totals":"","lock_votes":"0.00","integral":0,"user_type":"","vip_level":""}
     * timestamp : 1566524458
     */

    private String code;
    private String info;
    private DataBean data;
    private UserBean user;
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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
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
         * music_id : 121377633
         * name : 山水之间
         * author : 许嵩
         * thumb : http://qukufile2.qianqian.com/data2/pic/7b437f7ffee6717ed017db37504487ca/579301812/579301812.jpg@s_2,w_500,h_500
         * url : http://audio01.dmhmusic.com/71_53_T10038957304_128_4_1_0_sdk-cpm/0209/M00/11/D0/ChR461n07saAStuzAENy7y9P8_Q221.mp3?xcode=73f9f4588d0fb8154f7750821174765e8de6087
         * duration : 276
         * size : 4420335
         */

        private String music_id;
        private String name;
        private String author;
        private String thumb;
        private String url;
        private int duration;
        private int size;

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

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static class UserBean {
        /**
         * id : 100003
         * nickname : 手机用户4
         * sex : 0
         * money : 0.00
         * consumption :
         * consumptions :
         * votes : 0.00
         * votes_total :
         * votes_totals :
         * lock_votes : 0.00
         * integral : 0
         * user_type :
         * vip_level :
         */

        private int id;
        private String nickname;
        private int sex;
        private String money;
        private String consumption;
        private String consumptions;
        private String votes;
        private String votes_total;
        private String votes_totals;
        private String lock_votes;
        private int integral;
        private String user_type;
        private String vip_level;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getConsumption() {
            return consumption;
        }

        public void setConsumption(String consumption) {
            this.consumption = consumption;
        }

        public String getConsumptions() {
            return consumptions;
        }

        public void setConsumptions(String consumptions) {
            this.consumptions = consumptions;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getVotes_total() {
            return votes_total;
        }

        public void setVotes_total(String votes_total) {
            this.votes_total = votes_total;
        }

        public String getVotes_totals() {
            return votes_totals;
        }

        public void setVotes_totals(String votes_totals) {
            this.votes_totals = votes_totals;
        }

        public String getLock_votes() {
            return lock_votes;
        }

        public void setLock_votes(String lock_votes) {
            this.lock_votes = lock_votes;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getVip_level() {
            return vip_level;
        }

        public void setVip_level(String vip_level) {
            this.vip_level = vip_level;
        }
    }
}
