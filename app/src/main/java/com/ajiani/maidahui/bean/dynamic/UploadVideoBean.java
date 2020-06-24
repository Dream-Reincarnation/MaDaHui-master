package com.ajiani.maidahui.bean.dynamic;

public class UploadVideoBean {
    /**
     * code : 200
     * info : 操作成功
     * data : {"host":"https://ajiani.oss-cn-beijing.aliyuncs.com","aliyunData":{"key":"uploads/video/eb/b238b58d14e750e9074e61b2ae24c6.mp4","policy":"eyJleHBpcmF0aW9uIjoiMjAxOS0wOS0xMlQxMDo0MTowOVoiLCJjb25kaXRpb25zIjpbWyJjb250ZW50LWxlbmd0aC1yYW5nZSIsMCwxMDQ4NTc2MDAwXSxbInN0YXJ0cy13aXRoIiwiJGtleSIsInVwbG9hZHNcL3ZpZGVvXC8iXV19","OSSAccessKeyId":"LTAIXDJuLoyB4jK8","success_action_status":200,"signature":"gsvx7J5xXlj/WcRdCnouQWr0kYw=","host":"https://ajiani.oss-cn-beijing.aliyuncs.com","callback":"eyJjYWxsYmFja1VybCI6Imh0dHBzOlwvXC93d3cubWFpZGFodWkuY29tXC9hcGlcLzViNmQzYzMxYjBhMjI/YWxpeXVuPTEmc2F2ZVRva2VuPWViYjIzOGI1OGQxNGU3NTBlOTA3NGU2MWIyYWUyNGM2IiwiY2FsbGJhY2tCb2R5IjoidGVzdD0xIn0="},"saveToken":"ebb238b58d14e750e9074e61b2ae24c6"}
     * user : {"id":100003,"nickname":"手机用户4","sex":0,"money":"3.00","money_consume":"0.00","money_consumes":"0.00","votes":"98.85","votes_income":"0.00","votes_incomes":"100.23","lock_votes":"0.00","integral":30,"user_type":""}
     * timestamp : 1568256039
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
         * host : https://ajiani.oss-cn-beijing.aliyuncs.com
         * aliyunData : {"key":"uploads/video/eb/b238b58d14e750e9074e61b2ae24c6.mp4","policy":"eyJleHBpcmF0aW9uIjoiMjAxOS0wOS0xMlQxMDo0MTowOVoiLCJjb25kaXRpb25zIjpbWyJjb250ZW50LWxlbmd0aC1yYW5nZSIsMCwxMDQ4NTc2MDAwXSxbInN0YXJ0cy13aXRoIiwiJGtleSIsInVwbG9hZHNcL3ZpZGVvXC8iXV19","OSSAccessKeyId":"LTAIXDJuLoyB4jK8","success_action_status":200,"signature":"gsvx7J5xXlj/WcRdCnouQWr0kYw=","host":"https://ajiani.oss-cn-beijing.aliyuncs.com","callback":"eyJjYWxsYmFja1VybCI6Imh0dHBzOlwvXC93d3cubWFpZGFodWkuY29tXC9hcGlcLzViNmQzYzMxYjBhMjI/YWxpeXVuPTEmc2F2ZVRva2VuPWViYjIzOGI1OGQxNGU3NTBlOTA3NGU2MWIyYWUyNGM2IiwiY2FsbGJhY2tCb2R5IjoidGVzdD0xIn0="}
         * saveToken : ebb238b58d14e750e9074e61b2ae24c6
         */

        private String host;
        private AliyunDataBean aliyunData;
        private String saveToken;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public AliyunDataBean getAliyunData() {
            return aliyunData;
        }

        public void setAliyunData(AliyunDataBean aliyunData) {
            this.aliyunData = aliyunData;
        }

        public String getSaveToken() {
            return saveToken;
        }

        public void setSaveToken(String saveToken) {
            this.saveToken = saveToken;
        }

        public static class AliyunDataBean {
            /**
             * key : uploads/video/eb/b238b58d14e750e9074e61b2ae24c6.mp4
             * policy : eyJleHBpcmF0aW9uIjoiMjAxOS0wOS0xMlQxMDo0MTowOVoiLCJjb25kaXRpb25zIjpbWyJjb250ZW50LWxlbmd0aC1yYW5nZSIsMCwxMDQ4NTc2MDAwXSxbInN0YXJ0cy13aXRoIiwiJGtleSIsInVwbG9hZHNcL3ZpZGVvXC8iXV19
             * OSSAccessKeyId : LTAIXDJuLoyB4jK8
             * success_action_status : 200
             * signature : gsvx7J5xXlj/WcRdCnouQWr0kYw=
             * host : https://ajiani.oss-cn-beijing.aliyuncs.com
             * callback : eyJjYWxsYmFja1VybCI6Imh0dHBzOlwvXC93d3cubWFpZGFodWkuY29tXC9hcGlcLzViNmQzYzMxYjBhMjI/YWxpeXVuPTEmc2F2ZVRva2VuPWViYjIzOGI1OGQxNGU3NTBlOTA3NGU2MWIyYWUyNGM2IiwiY2FsbGJhY2tCb2R5IjoidGVzdD0xIn0=
             */

            private String key;
            private String policy;
            private String OSSAccessKeyId;
            private int success_action_status;
            private String signature;
            private String host;
            private String callback;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getPolicy() {
                return policy;
            }

            public void setPolicy(String policy) {
                this.policy = policy;
            }

            public String getOSSAccessKeyId() {
                return OSSAccessKeyId;
            }

            public void setOSSAccessKeyId(String OSSAccessKeyId) {
                this.OSSAccessKeyId = OSSAccessKeyId;
            }

            public int getSuccess_action_status() {
                return success_action_status;
            }

            public void setSuccess_action_status(int success_action_status) {
                this.success_action_status = success_action_status;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public String getCallback() {
                return callback;
            }

            public void setCallback(String callback) {
                this.callback = callback;
            }
        }
    }



    /**
     * code : 200
     * info : 操作成功
     * data : {"host":"https://ajiani.oss-cn-beijing.aliyuncs.com","aliyunData":{"key":"uploads/mp4/b7/a8874bac274c3721ac6a11897a055a.mp4","policy":"eyJleHBpcmF0aW9uIjoiMjAxOS0wOS0wMlQxMjoxMzo0OFoiLCJjb25kaXRpb25zIjpbWyJjb250ZW50LWxlbmd0aC1yYW5nZSIsMCwxMDQ4NTc2MDAwXSxbInN0YXJ0cy13aXRoIiwiJGtleSIsInVwbG9hZHNcL21wNFwvIl1dfQ==","OSSAccessKeyId":"LTAIXDJuLoyB4jK8","success_action_status":200,"signature":"s33BWmSQhrpFHMGW5qHK6+tqNFg=","host":"https://ajiani.oss-cn-beijing.aliyuncs.com","callback":"eyJjYWxsYmFja1VybCI6Imh0dHA6XC9cL21kaC53ZWZzbi5jb21cL2FwaVwvNWI2ZDNjMzFiMGEyMj9hbGl5dW49MSZzYXZlVG9rZW49YjdhODg3NGJhYzI3NGMzNzIxYWM2YTExODk3YTA1NWEiLCJjYWxsYmFja0JvZHkiOiJ0ZXN0PTEifQ=="},"saveToken":"b7a8874bac274c3721ac6a11897a055a"}
     * user :
     * timestamp : 1567397598
     */


}
