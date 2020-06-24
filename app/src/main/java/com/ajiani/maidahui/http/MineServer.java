package com.ajiani.maidahui.http;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MineServer {

    //获取我的全部视频
    @POST("5d5b6af5f16a2")
    @FormUrlEncoded
    Observable<String> getmineVideo(@FieldMap HashMap<String, String> map);

    //个人信息的页面

    @POST("5d562b61536bd")
    @FormUrlEncoded
    Observable<String> getUserInfo(@FieldMap HashMap<String, String> map);

    //钻石明细
    @POST("5d562d402f113")
    @FormUrlEncoded
    Observable<String> getEraDetails(@FieldMap HashMap<String,String> map);

    //修改个人信息
    @POST("5d562c53103d3")
    @FormUrlEncoded
    Observable<String> setInfo(@FieldMap HashMap<String,String> map);

    //麦穗明细 界面
    @POST("5d562d9327a09")
    @FormUrlEncoded
    Observable<String> getIntegral(@FieldMap HashMap<String,String> map);

    //获取视频的详情
    @POST("5d5b754d3b05c")
    @FormUrlEncoded
    Observable<String> getVideoInfo(@FieldMap HashMap<String,String> map);

    //获取短视频的视频
    @POST("5d5b7601c23f4")
    @FormUrlEncoded
    Observable<String>  getCommentList(@FieldMap HashMap<String,String> map);

    //收藏视频
    @POST("5d5b7571205dd")
    @FormUrlEncoded
    Observable<String> videoStart(@FieldMap HashMap<String,String> map);

    //视频评论列表
    @POST("5d5b7601c23f4")
    @FormUrlEncoded
    Observable<String> CommentList(@FieldMap HashMap<String,String> map);

    //发布评论
    @POST("5d5b763540325")
    @FormUrlEncoded
    Observable<String> addComment(@FieldMap HashMap<String,String> map);

    //收藏评论
    @POST("5d5b7669bcb23")
    @FormUrlEncoded
    Observable<String> starComment(@FieldMap HashMap<String,String> map);

    //实名认证
    @POST("5d71b4b1cefdc")
    @FormUrlEncoded
    Observable<String> autonym(@FieldMap HashMap<String,String> map);

    //验证实名认证
    @POST("5d71b48293b42")
    @FormUrlEncoded
    Observable<String> autonymInfo(@FieldMap HashMap<String,String> map);

    //余额详情
    @POST("5d562d63d955b")
    @FormUrlEncoded
    Observable<String> mineRestant(@FieldMap HashMap<String,String> map);

    //获得 我或他喜欢的视频

    @POST("5d5b67aea5900")
    @FormUrlEncoded
    Observable<String> getLoveList(@FieldMap HashMap<String,String> map);

    //申请提现
    @POST("5d562dde23f0f")
    @FormUrlEncoded
    Observable<String> castList(@FieldMap HashMap<String,String> map);

    //提现记录
    @POST("5d562dbc7d4e3")
    @FormUrlEncoded
    Observable<String> castDetails(@FieldMap HashMap<String,String> map);

    //统计数据、
    @POST("5dd4af67ef251")
    @FormUrlEncoded
    Observable<String> getCount(@FieldMap HashMap<String,String> map);

    //更换手机号
    @POST("5d562c72ef932 ")
    @FormUrlEncoded
    Observable<String> ChangePhone(@FieldMap HashMap<String,String> map);

    //邮箱发送验证码
    @POST("5e0061e3bbbc0")
    @FormUrlEncoded
    Observable<String> sendMailVertify(@FieldMap HashMap<String,String> map);

    //绑定邮箱
    @POST("5e007ab375f89")
    @FormUrlEncoded
    Observable<String> bindMailBox(@FieldMap HashMap<String,String> map);
}
