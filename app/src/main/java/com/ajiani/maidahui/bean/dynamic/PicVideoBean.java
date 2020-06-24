package com.ajiani.maidahui.bean.dynamic;

public class PicVideoBean {
    String path;
    boolean isSel;
    String time;
    int num;
    public String getPath() {
        return path;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSel() {
        return isSel;
    }

    public void setSel(boolean sel) {
        isSel = sel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
