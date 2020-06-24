package com.ajiani.maidahui.bean.dynamic;

public class VideoCommdity {
    String goods_id;
    String name ;
    String thumb;
    String goods_type;
    String top="0";
    String left="0";
    String link;
    String start;
    String end;
    String sort ;
    String direction;
    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public VideoCommdity(String goods_id, String name, String thumb, String goods_type, String link, String start, String end, String sort,String price) {
        this.goods_id = goods_id;
        this.name = name;
        this.thumb = thumb;
        this.goods_type = goods_type;
        this.link = link;
        this.start = start;
        this.end = end;
        this.sort = sort;
        this.direction=price;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStart() {
        return start;
    }

    public void setStsrt(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
