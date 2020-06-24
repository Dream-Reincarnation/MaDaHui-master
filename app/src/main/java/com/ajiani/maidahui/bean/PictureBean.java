package com.ajiani.maidahui.bean;

import java.util.List;

public class PictureBean {

    /**
     * pictureArr : ["https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/23/3b2c6be0ad469068abf1aa4f717cd6.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/3e/b6967283b9c2da51dd7814e720520d.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/05/50add8f317ee0343e903b193821001.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/41/ae1d9f71880c691724690f29991de8.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/a3/d72f9dc0a64e7def824d81856b8005.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/40/5a8b4472d2bc9bca06adf559737640.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/cd/461d02a4e4debc069b35f46c5364bd.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/99/b387d5f7600ef6f110e0b9fbaeeb4b.jpg","https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/74/315db28ac2b86414d3e7a4f492942f.jpg"]
     * index : 3
     */

    private int index;
    private List<String> pictureArr;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getPictureArr() {
        return pictureArr;
    }

    public void setPictureArr(List<String> pictureArr) {
        this.pictureArr = pictureArr;
    }
}
