package com.ajiani.maidahui.http;

import com.ajiani.maidahui.bean.BaseBean;
import com.ajiani.maidahui.bean.RecommedBean;
import com.ajiani.maidahui.bean.login.VeriftyBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface MyServer {

    // 验证码
    @POST("5b32f164ba07c")
    @FormUrlEncoded
    Observable<String> getVerifty(@FieldMap HashMap<String, String> hashMap);
    //  Observable<String> get(@Body RequestBody requestBody);

    //登录接口
    @POST("5d5625dac4b31")
    @FormUrlEncoded
    Observable<String> phoneLogin(@FieldMap HashMap<String, String> hashMap);

    //上传图片
    @POST("5d57dc30d76fe")
    Observable<String> upLoadImg(@Body MultipartBody body);

    //找回密码第一步 】
    @POST("5d562bab37ecf")
    @FormUrlEncoded
    Observable<String> findPass(@FieldMap HashMap<String, String> map);

    //获取充值规则
    @POST("5d562d29989af")
    @FormUrlEncoded
    Observable<String> getRecharge(@FieldMap HashMap<String, String> map);

    //创建充值订单
    @POST("5d5e2bde717a2 ")
    @FormUrlEncoded
    Observable<String> createRecharge(@FieldMap HashMap<String, String> map);

    //得到支付宝的参数
    @POST("5d5e2a7ddaffd")
    @FormUrlEncoded
    Observable<String> getAliparameter(@FieldMap HashMap<String, String> map);

    //得到微信的支付参数
    @POST("5d5e299398fa5")
    @FormUrlEncoded
    Observable<String> getWeChatPay(@FieldMap HashMap<String, String> map);
    @POST("5d56271a250d5")
    @FormUrlEncoded
    Observable<String> weChatLogin(@FieldMap HashMap<String, String> map);

    //上传音视频

    @POST("5b35f848ee47f")
    @FormUrlEncoded
    Observable<String> uploadVideo(@FieldMap HashMap<String, String> map);

    //上传音视频
    @POST("/")
    Observable<String> ossVideo(@Body MultipartBody body);

    //百度AI 识别图片
    @POST("5d707dcbc6649")
    Observable<String> baiduAI(@Body MultipartBody body);

    //余额支付
    @POST("5d5e293c42660")
    @FormUrlEncoded
    Observable<String> votesPay(@FieldMap HashMap<String,String> map);

    //获取公共参数
    @POST("5d5625bd4ba74")
    @FormUrlEncoded
    Observable<String> getParams(@FieldMap HashMap<String,String> map);

    //微信登录时认证
    @POST("5d56271a250d5")
    @FormUrlEncoded
    Observable<String> getweChat(@FieldMap HashMap<String,String> map);

}
