package com.ajiani.maidahui.bean.dynamic;

import java.util.List;

public class AddressBean {

    /**
     * status : 0
     * message : query ok
     * count : 100
     * data : [{"id":"16278717918011783335","title":"省人行二号家属院","address":"河南省郑州市金水区郑花路29号","category":"房产小区:住宅区:住宅小区","type":0,"_distance":1909,"location":{"lat":34.80114,"lng":113.67614},"adcode":410105,"province":"河南省","city":"郑州市","district":"金水区"},{"id":"10615717378633840915","title":"河南省脑血管病医院","address":"河南省郑州市金水区纬五路7号","category":"医疗保健:专科医院:其它专科医院","type":0,"_distance":1751,"location":{"lat":34.772102142,"lng":113.693772107},"adcode":410105,"province":"河南省","city":"郑州市","district":"金水区"},{"id":"6985695192287914263","title":"河南省人民医院家属院","address":"河南省郑州市金水区黄河路经三路交叉口东","category":"房产小区:住宅区:住宅小区","type":0,"_distance":1417,"location":{"lat":34.77545,"lng":113.69431},"adcode":410105,"province":"河南省","city":"郑州市","district":"金水区"},{"id":"15520147077000107846","title":"省汇中心","address":"河南省郑州市金水区农业路东16号","category":"房产小区:商务楼宇","type":0,"_distance":48,"location":{"lat":34.78675,"lng":113.68834},"adcode":410105,"province":"河南省","city":"郑州市","district":"金水区"},{"id":"16337808034462359110","title":"省汇中心A座","address":"河南省郑州市金水区农业路省汇中心","category":"房产小区:商务楼宇","type":0,"_distance":48,"location":{"lat":34.78675,"lng":113.68834},"adcode":410105,"province":"河南省","city":"郑州市","district":"金水区"}]
     * request_id : 17513200586794316957
     */

    private int status;
    private String message;
    private int count;
    private String request_id;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 16278717918011783335
         * title : 省人行二号家属院
         * address : 河南省郑州市金水区郑花路29号
         * category : 房产小区:住宅区:住宅小区
         * type : 0
         * _distance : 1909
         * location : {"lat":34.80114,"lng":113.67614}
         * adcode : 410105
         * province : 河南省
         * city : 郑州市
         * district : 金水区
         */

        private String id;
        private String title;
        private String address;
        private String category;
        private int type;
        private int _distance;
        private LocationBean location;
        private int adcode;
        private String province;
        private String city;
        private String district;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int get_distance() {
            return _distance;
        }

        public void set_distance(int _distance) {
            this._distance = _distance;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getAdcode() {
            return adcode;
        }

        public void setAdcode(int adcode) {
            this.adcode = adcode;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public static class LocationBean {
            /**
             * lat : 34.80114
             * lng : 113.67614
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }
}
