package com.ajiani.maidahui.http;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface DynamicServer {

    //获取收藏音乐
    @POST("5ddb3428a586f")
    @FormUrlEncoded
    Observable<String> getCollectionMusic(@FieldMap HashMap<String,String> map);

    //收藏音乐
    @POST("5dd904f2f0c28")
    @FormUrlEncoded
    Observable<String> collectionMusic(@FieldMap HashMap<String,String> map);

    //搜索音乐
    @POST("5d5b77dd8543e")
    @FormUrlEncoded
    Observable<String> searchMusic(@FieldMap HashMap<String,String> map);

    //音乐详情、
    @POST("5d5b781ac754e")
    @FormUrlEncoded
    Observable<String>  musicInfo(@FieldMap HashMap<String,String> map);

    //获得话题
    @POST("5d5dfb80342ca")
    @FormUrlEncoded
    Observable<String> getTalke(@FieldMap HashMap<String,String> map);

    //发表短视频
    @POST("5d5a5d09767b7")
    @FormUrlEncoded
    Observable<String> sendVideo(@FieldMap HashMap<String,String> map);

    //商品列表
    @POST("5d5d1508006dd")
    @FormUrlEncoded
    Observable<String> getCommodityList(@FieldMap HashMap<String,String> map);

    //发布短视频带商品的
    @POST("5d5a5d09767b7")
    @FormUrlEncoded
    Observable<String> sendVideo2(@FieldMap HashMap<String,String> map);

    //公共视频列表

    @POST("5d5b46fd4cce9")
    @FormUrlEncoded
    Observable<String> getVideoList(@FieldMap HashMap<String,String> map);

    //关注用户
    @POST("5d900b21d6a1d")
    @FormUrlEncoded
    Observable<String> followUser(@FieldMap HashMap<String,String> map);

    //关注列表
    @POST("5d56303515488")
    @FormUrlEncoded
    Observable<String> followList(@FieldMap HashMap<String,String> map);
    //关注视频列表
    @POST("5d5b6cb5da293")
    @FormUrlEncoded
    Observable<String> followVideoList(@FieldMap HashMap<String,String> map);

    //获取他人的详情
    @POST("5d562b703aa2d")
    @FormUrlEncoded
    Observable<String> getUserInfo(@FieldMap HashMap<String,String> map);

    //粉丝列表、
    @POST("5d562ffdd8e44")
    @FormUrlEncoded
    Observable<String> getFansList(@FieldMap HashMap<String,String> map);

    //获取私信的列表
    @POST("5def3f71032ea")
    @FormUrlEncoded
    Observable<String> getFriends(@FieldMap HashMap<String,String> map);

    //获取私信的列表
    @POST("5db94e77bec1a")
    @FormUrlEncoded
    Observable<String> report(@FieldMap HashMap<String,String> map);

    //设置置顶
    @POST("5df0ab88ea1fc")
    @FormUrlEncoded
    Observable<String> isStick(@FieldMap HashMap<String,String> map);

    //好友列表
    @POST("5df0c51c34481")
    @FormUrlEncoded
    Observable<String> friendsList(@FieldMap HashMap<String,String> map);

    @GET("suggestion")
    Observable<String> searchAddress(@QueryMap HashMap<String,String> map);

    //多店铺商品列表
    @POST("5e6f22d81f5e6")
    @FormUrlEncoded
    Observable<String> shopList(@FieldMap HashMap<String,String> map);

    //获取收藏的店铺
    @POST("5db25e7f7acba")
    @FormUrlEncoded
    Observable<String> collectionStore(@FieldMap HashMap<String,String> map);

    //收藏的商品
    @POST("5db25c546e32e")
    @FormUrlEncoded
    Observable<String> collectionShip(@FieldMap HashMap<String,String> map);

    //新的关注列表
    @POST("5e8697d89211a")
    @FormUrlEncoded
    Observable<String> followNewList(@FieldMap HashMap<String,String> map);

}
