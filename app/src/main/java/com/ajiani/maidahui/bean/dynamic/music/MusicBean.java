package com.ajiani.maidahui.bean.dynamic.music;

import java.util.List;

public class MusicBean {

    /**
     * code : 1
     * info : 获取成功
     * data : [{"music_id":1490,"name":"要你健康","author":"你过来呀～","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/06/f1c82df44f02f8b98af86efa317f5b.mp3","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/8b/789aec9ee365e4f832e75f388057c7.png","size":818062,"duration":51,"nickname":"桂","star":0,"is_star":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","count":0},{"music_id":1489,"name":"jami soul she","author":"小拽","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/e8/9e47bd80254e50226b692416430a27.mp3","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/9e/394bd187cdaa68d55b9adcbcc12ffc.png","size":886160,"duration":221,"nickname":"麦穗","star":0,"is_star":0,"headimgurl":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/82/fcda46ec941db2fdcf2be758f22fbd.png","count":0},{"music_id":1488,"name":"焱?专属定制音乐6562017323970988813","author":"你过来呀～","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/32/992e39bdf75301d0ee70f1f2177b49.mp3","thumb":"https://www.maidahui.com/images/none.png","size":171741,"duration":11,"nickname":"桂","star":0,"is_star":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","count":0},{"music_id":1487,"name":"狒狒偷水6561236803555379976","author":"你过来呀～","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/14/5daa5c60ef4bd3d9e15edafb6132ca.mp3","thumb":"https://www.maidahui.com/images/none.png","size":242795,"duration":15,"nickname":"桂","star":0,"is_star":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","count":0},{"music_id":1486,"name":"最新的猪叫6531873427916065549","author":"你过来呀～","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/aa/5cf4dfcf2c71aafb4b5e1df4df8699.mp3","thumb":"https://www.maidahui.com/images/none.png","size":243565,"duration":15,"nickname":"桂","star":0,"is_star":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","count":0},{"music_id":1485,"name":"最后的莫西干人6526329972700515076","author":"你过来呀～","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/3d/6325381ffd0939af0aa09d2e507480.mp3","thumb":"https://www.maidahui.com/images/none.png","size":242421,"duration":15,"nickname":"桂","star":0,"is_star":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","count":0},{"music_id":1484,"name":"走马观花原创右下角首发6572833604982803208","author":"你过来呀～","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/81/9db2a76e7ad48e784e623f1a296825.mp3","thumb":"https://www.maidahui.com/images/none.png","size":242376,"duration":15,"nickname":"桂","star":0,"is_star":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","count":0},{"music_id":1483,"name":"专属80后的游戏6596145006501694221","author":"你过来呀～","url":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/f7/d30a39819388ba59d1046a6c8165f7.mp3","thumb":"https://www.maidahui.com/images/none.png","size":204988,"duration":13,"nickname":"桂","star":0,"is_star":0,"headimgurl":"http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132","count":0}]
     */

    private String code;
    private String info;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * music_id : 1490
         * name : 要你健康
         * author : 你过来呀～
         * url : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/audio/06/f1c82df44f02f8b98af86efa317f5b.mp3
         * thumb : https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/8b/789aec9ee365e4f832e75f388057c7.png
         * size : 818062
         * duration : 51
         * nickname : 桂
         * star : 0
         * is_star : 0
         * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/soDBQeQT3P8gb7VwYskMDZfmmP3ya62zGMlYhAr4VnoNVeVHoF7TewWgctEoTTfhljVna05Bw4N6RPIvmAcKKw/132
         * count : 0
         */

        private int music_id;
        private String name;
        private String author;
        private String url;
        private String thumb;
        private int size;
        private int duration;
        private String nickname;
        private int star;
        private int is_star;
        private String headimgurl;
        private int count;
        boolean isPlay;
        public int getMusic_id() {
            return music_id;
        }

        public void setMusic_id(int music_id) {
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public int getIs_star() {
            return is_star;
        }

        public void setIs_star(int is_star) {
            this.is_star = is_star;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public boolean isPlay() {
            return isPlay;
        }

        public void setPlay(boolean play) {
            isPlay = play;
        }
    }
}
