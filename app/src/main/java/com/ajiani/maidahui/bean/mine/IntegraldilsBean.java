package com.ajiani.maidahui.bean.mine;

import java.util.List;

public class IntegraldilsBean {

    /**
     * code : 1
     * info : 操作成功
     * data : [{"before_integral":15960,"change_integral":-1160,"after_integral":14800,"change_type":"兑换道具","create_time":"2019-09-26 10:56:35","remark":"兑换道具[肥料] x 58","order_no":"TP_F9265355397742061"},{"before_integral":19000,"change_integral":-3040,"after_integral":15960,"change_type":"兑换道具","create_time":"2019-09-26 10:55:54","remark":"兑换道具[肥料] x 152","order_no":"TP_J9264642643836988"},{"before_integral":19020,"change_integral":-20,"after_integral":19000,"change_type":"兑换道具","create_time":"2019-09-26 10:54:41","remark":"兑换道具[肥料] x 1","order_no":"TP_C9263826412776932"},{"before_integral":19040,"change_integral":-20,"after_integral":19020,"change_type":"兑换道具","create_time":"2019-09-26 10:54:40","remark":"兑换道具[肥料] x 1","order_no":"TP_H9263897614449172"},{"before_integral":19060,"change_integral":-20,"after_integral":19040,"change_type":"兑换道具","create_time":"2019-09-26 10:54:39","remark":"兑换道具[肥料] x 1","order_no":"TP_B9269837120026376"},{"before_integral":19080,"change_integral":-20,"after_integral":19060,"change_type":"兑换道具","create_time":"2019-09-26 10:54:38","remark":"兑换道具[肥料] x 1","order_no":"TP_E9261809611988592"},{"before_integral":19100,"change_integral":-20,"after_integral":19080,"change_type":"兑换道具","create_time":"2019-09-26 10:54:37","remark":"兑换道具[肥料] x 1","order_no":"TP_D9263423421673014"},{"before_integral":19120,"change_integral":-20,"after_integral":19100,"change_type":"兑换道具","create_time":"2019-09-26 10:54:36","remark":"兑换道具[肥料] x 1","order_no":"TP_I9262713758962455"},{"before_integral":19140,"change_integral":-20,"after_integral":19120,"change_type":"兑换道具","create_time":"2019-09-26 10:54:35","remark":"兑换道具[肥料] x 1","order_no":"TP_E9265447690489309"},{"before_integral":19160,"change_integral":-20,"after_integral":19140,"change_type":"兑换道具","create_time":"2019-09-26 10:54:35","remark":"兑换道具[肥料] x 1","order_no":"TP_D9268168193813573"},{"before_integral":19180,"change_integral":-20,"after_integral":19160,"change_type":"兑换道具","create_time":"2019-09-26 10:54:34","remark":"兑换道具[肥料] x 1","order_no":"TP_F9263248707848107"},{"before_integral":19200,"change_integral":-20,"after_integral":19180,"change_type":"兑换道具","create_time":"2019-09-26 10:54:33","remark":"兑换道具[肥料] x 1","order_no":"TP_B9264190987903431"},{"before_integral":19220,"change_integral":-20,"after_integral":19200,"change_type":"兑换道具","create_time":"2019-09-26 10:54:33","remark":"兑换道具[肥料] x 1","order_no":"TP_B9262257396174762"},{"before_integral":19240,"change_integral":-20,"after_integral":19220,"change_type":"兑换道具","create_time":"2019-09-26 10:54:32","remark":"兑换道具[肥料] x 1","order_no":"TP_B9262878588897823"},{"before_integral":19260,"change_integral":-20,"after_integral":19240,"change_type":"兑换道具","create_time":"2019-09-26 10:54:31","remark":"兑换道具[肥料] x 1","order_no":"TP_F9267217944646647"}]
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"2.00","money_consume":"0.00","money_consumes":"0.00","votes":"900.00","votes_income":"0.00","votes_incomes":"1000.00","lock_votes":"0.00","integral":14800,"user_type":""}
     * timestamp : 1569470896
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
         * id : 100003
         * nickname : 手机用户4
         * sex : 0
         * money : 2.00
         * money_consume : 0.00
         * money_consumes : 0.00
         * votes : 900.00
         * votes_income : 0.00
         * votes_incomes : 1000.00
         * lock_votes : 0.00
         * integral : 14800
         * user_type :
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
        private String user_type;

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

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }
    }

    public static class DataBean {
        /**
         * before_integral : 15960
         * change_integral : -1160
         * after_integral : 14800
         * change_type : 兑换道具
         * create_time : 2019-09-26 10:56:35
         * remark : 兑换道具[肥料] x 58
         * order_no : TP_F9265355397742061
         */

        private int before_integral;
        private int change_integral;
        private int after_integral;
        private String change_type;
        private String create_time;
        private String remark;
        private String order_no;

        public int getBefore_integral() {
            return before_integral;
        }

        public void setBefore_integral(int before_integral) {
            this.before_integral = before_integral;
        }

        public int getChange_integral() {
            return change_integral;
        }

        public void setChange_integral(int change_integral) {
            this.change_integral = change_integral;
        }

        public int getAfter_integral() {
            return after_integral;
        }

        public void setAfter_integral(int after_integral) {
            this.after_integral = after_integral;
        }

        public String getChange_type() {
            return change_type;
        }

        public void setChange_type(String change_type) {
            this.change_type = change_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }
    }
}
