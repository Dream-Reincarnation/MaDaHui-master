package com.ajiani.maidahui.bean.chat;

import java.util.List;

public class SystemBean {


    /**
     * code : 1
     * info : success
     * data : [{"form_user_id":100007,"nickname":"@我有一壶酒","avatar":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg","action":"40","extra":{"video_id":"239","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ce/647de96f471f854d9188fd049c7a59.jpg"},"content":"@我有一壶酒发布了大作《啦啦啦啦》,邀请您看看","msgid":"10000710000715786242124937","timestamp":1578624212,"msg_type":"AcetexVideo","msg_type_name":"短视频@我"}]
     * user : {"id":100007,"nickname":"@我有一壶酒","sex":0,"money":"0.00","money_consume":"0.00","money_consumes":"0.00","votes":"100337.00","votes_income":"0.00","votes_incomes":"103478.10","lock_votes":"0.00","integral":2411,"user_auth":0,"true_name":"","headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg"}
     * timestamp : 1578649142
     */

    private String code;
    private String info;
    private UserBean user;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class UserBean {
        /**
         * id : 100007
         * nickname : @我有一壶酒
         * sex : 0
         * money : 0.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 100337.00
         * votes_income : 0.00
         * votes_incomes : 103478.10
         * lock_votes : 0.00
         * integral : 2411
         * user_auth : 0
         * true_name :
         * headimgurl : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg
         */

        private int id;
        private String nickname;
        private int sex;
        private String money;
        private String money_consume;
        private String money_consumes;
        private String votes;
        private String votes_income;
        private String votes_incomes;
        private String lock_votes;
        private int integral;
        private int user_auth;
        private String true_name;
        private String headimgurl;

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

        public String getMoney_consume() {
            return money_consume;
        }

        public void setMoney_consume(String money_consume) {
            this.money_consume = money_consume;
        }

        public String getMoney_consumes() {
            return money_consumes;
        }

        public void setMoney_consumes(String money_consumes) {
            this.money_consumes = money_consumes;
        }

        public String getVotes() {
            return votes;
        }

        public void setVotes(String votes) {
            this.votes = votes;
        }

        public String getVotes_income() {
            return votes_income;
        }

        public void setVotes_income(String votes_income) {
            this.votes_income = votes_income;
        }

        public String getVotes_incomes() {
            return votes_incomes;
        }

        public void setVotes_incomes(String votes_incomes) {
            this.votes_incomes = votes_incomes;
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

        public int getUser_auth() {
            return user_auth;
        }

        public void setUser_auth(int user_auth) {
            this.user_auth = user_auth;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }
    }

    public static class DataBean {
        /**
         * form_user_id : 100007
         * nickname : @我有一壶酒
         * avatar : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/58/4beab89f32a6d38e6525731d0123eb.jpg
         * action : 40
         * extra : {"video_id":"239","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ce/647de96f471f854d9188fd049c7a59.jpg"}
         * content : @我有一壶酒发布了大作《啦啦啦啦》,邀请您看看
         * msgid : 10000710000715786242124937
         * timestamp : 1578624212
         * msg_type : AcetexVideo
         * msg_type_name : 短视频@我
         */

        private int form_user_id;
        private String nickname;
        private String avatar;
        private String action;
        private Object extra;
        private String content;
        private String msgid;
        private int timestamp;
        private String msg_type;
        private String msg_type_name;

        public int getForm_user_id() {
            return form_user_id;
        }

        public void setForm_user_id(int form_user_id) {
            this.form_user_id = form_user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public Object getExtra() {
            return extra;
        }

        public void setExtra(Object extra) {
            this.extra = extra;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMsgid() {
            return msgid;
        }

        public void setMsgid(String msgid) {
            this.msgid = msgid;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public String getMsg_type_name() {
            return msg_type_name;
        }

        public void setMsg_type_name(String msg_type_name) {
            this.msg_type_name = msg_type_name;
        }

        public static class ExtraBean {
            /**
             * video_id : 239
             * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ce/647de96f471f854d9188fd049c7a59.jpg
             */

            private String video_id;
            private String thumb;

            public String getVideo_id() {
                return video_id;
            }

            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }
    }
}
