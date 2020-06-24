package com.ajiani.maidahui.bean.dynamic.shop;

public class CommodityInfo {

    String goods_id;
    String name ;
    String thumb;
    String goods_type;
    String top;
    String left;
    String link;
    String star;
    String sort ;
    String direction;
    String price;

    public CommodityInfo() {
    }

    public CommodityInfo(String goods_id, String name, String thumb, String goods_type, String top, String left, String link, String star, String sort, String direction, String price) {
        this.goods_id = goods_id;
        this.name = name;
        this.thumb = thumb;
        this.goods_type = goods_type;
        this.top = top;
        this.left = left;
        this.link = link;
        this.star = star;
        this.sort = sort;
        this.direction = direction;
        this.price = price;
    }


    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "{" +
                "goods_id='" + goods_id + '\'' +
                ", name='" + name + '\'' +
                ", thumb='" + thumb + '\'' +
                ", goods_type='" + goods_type + '\'' +
                ", top='" + top + '\'' +
                ", left='" + left + '\'' +
                ", link='" + link + '\'' +
                ", sort='" + sort + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
