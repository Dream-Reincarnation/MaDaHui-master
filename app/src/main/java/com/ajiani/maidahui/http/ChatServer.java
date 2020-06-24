package com.ajiani.maidahui.http;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ChatServer {

    //获取聊天列表
    @POST("5d8f42886000e")
    @FormUrlEncoded
    Observable<String> getUserList(@FieldMap HashMap<String, String> map);

    //获取私信列表
    @POST("5d8f43773940b")
    @FormUrlEncoded
    Observable<String> getMessageList(@FieldMap HashMap<String, String> map);

    //获取广播消息
    @POST("5d8f43a5b6d20")
    @FormUrlEncoded
    Observable<String> getBoardList(@FieldMap HashMap<String, String> map);

    //客服打分
    @POST("5d9beae8d2ccd")
    @FormUrlEncoded
     Observable<String> serviceComment(@FieldMap HashMap<String,String> map);

    //获取客服私信列表
    @POST("5d8f44322797d")
    @FormUrlEncoded
    Observable<String> getServicesList(@FieldMap HashMap<String,String> map);

    //客服聊天记录
    @POST("5d8f4453a8d3e")
    @FormUrlEncoded
    Observable<String> getServiceMessage(@FieldMap HashMap<String,String> map);

    //获取商城消息记录
    @POST("5d9b05d60a1cb")
    @FormUrlEncoded
    Observable<String> getShopMessage(@FieldMap HashMap<String,String> map);

    //获取我的粉丝
    @POST("5d562ffdd8e44")
    @FormUrlEncoded
    Observable<String> getMineFans(@FieldMap HashMap<String,String> map);

    //小额转账
    @POST("5d9fd6f527e24")
    @FormUrlEncoded
    Observable<String> toVotes(@FieldMap HashMap<String,String> map);

    //获取平台的消息
    @POST("5da1420dd0bc2")
    @FormUrlEncoded
    Observable<String> getSystem(@FieldMap HashMap<String,String> map);

    //消息统计分组
    @POST("5d8f4544b37d5")
    @FormUrlEncoded
    Observable<String> getSystemNum(@FieldMap HashMap<String,String> map);
    //删除商城广播消息

    @POST("5e152e06928f3")
    @FormUrlEncoded
    Observable<String> delShopMessage(@FieldMap HashMap<String,String> map);

    //删除广播消息
    @POST("5e15328fbaca9")
    @FormUrlEncoded
    Observable<String> delMessage(@FieldMap HashMap<String,String> map);

    //删除私聊对象
    @POST("5e183906cb011")
    @FormUrlEncoded
    Observable<String> delUserMesage(@FieldMap HashMap<String,String> map);
    //删除客服聊天
    @POST("5e1bc94930bc8")
    @FormUrlEncoded
    Observable<String> delShopMesage(@FieldMap HashMap<String,String> map);

    //删除聊天记录
    @POST("5e1bcb2cb1a53")
    @FormUrlEncoded
    Observable<String> delMsgId(@FieldMap HashMap<String,String> map);

}
