package com.ajiani.maidahui.Utils.http;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.web.WebUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.activity.chat.ChatActivity;
import com.ajiani.maidahui.activity.dynamic.ReportVideoActivity;
import com.ajiani.maidahui.bean.UpLoadBean;
import com.ajiani.maidahui.bean.dynamic.FollowUserBean;
import com.ajiani.maidahui.bean.dynamic.OSSVideobean;
import com.ajiani.maidahui.bean.dynamic.UploadVideoBean;
import com.ajiani.maidahui.bean.dynamic.VideoAleary;
import com.ajiani.maidahui.bean.login.VeriftyBean;
import com.ajiani.maidahui.chat.ChatAdapter;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class HttpUtils  {
    public static HttpUtils mHttpManager;
    private OSSVideobean ossVideobean;
    public static String url;
    public static  boolean isSuccess;
    private int duration;
    private static String path;
    public static String code_id;


    public static  void setUrl(String s){
       path=s;
    }
    public static  String getUrl(){
        return path;
    }


    //差UN观察UN多张图片
     public void uploadMoreImg(ArrayList<File> list,Activity activity){
         for (int i = 0; i < list.size(); i++) {
             File file = list.get(i);
             //上传图片
             HashMap<String, String> hashMap = Params.setParams();
             HashMap<String, String> map = Params.getSign(hashMap);
             RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
             MultipartBody.Builder file1 = new MultipartBody.Builder()
                     .setType(MultipartBody.FORM)
                     .addFormDataPart("file", file.getName(), requestBody);
             //遍历 hashMAp
             for (String o : map.keySet()) {
                 file1.addFormDataPart(o, map.get(o));
             }
             MultipartBody build = file1.build();
             int finalI = i;
             HttpManager.instance().getServer(MyServer.class).upLoadImg(build)
                     .compose(RxUtils.rxScheduleThread())
                     .subscribe(new BaseObserver() {
                                    @Override
                                    protected void onSuccess(String string) {
                                        if(finalI==list.size()-1){
                                            ReportVideoActivity reportVideoActivity = (ReportVideoActivity) activity;
                                            reportVideoActivity.success();
                                        }

                                    }

                                    @Override
                                    protected void onError(String string) {
                                        Toast.makeText(MyApp.getApp(), "上传失败", Toast.LENGTH_SHORT).show();

                                    }

                                }


                     );
         }

     }

    //发布接口


    //上传图片
    public void uoLoadImg(File file){
        //上传图片
        HashMap<String, String> hashMap = Params.setParams();
        HashMap<String, String> map = Params.getSign(hashMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Builder file1 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), requestBody);
        //遍历 hashMAp
        for (String o : map.keySet()) {
            file1.addFormDataPart(o, map.get(o));
        }
        MultipartBody build = file1.build();
        HttpManager.instance().getServer(MyServer.class).upLoadImg(build)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                               @Override
                               protected void onSuccess(String string) {
                                   Log.i("wxy", "onError: "+string+"==="+ChatActivity.isShop);
                                   UpLoadBean upLoadBean = new Gson().fromJson(string, UpLoadBean.class);
                                   String path = upLoadBean.getData().getPath();
                                   setUrl(path);
                                    if(ChatActivity.isShop){
                                        ChatActivity.getPhotoPath(path);
                                    }else{
                                        Chat2Activity.getPhotoPath(path);
                                    }

                               }

                               @Override
                               protected void onError(String string) {
                                   Log.i("wxy", "onError: "+string);
                               }

                           }


                );

    }


     //收藏接口
    public static void videostar(String videoId){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("video_id",videoId);
        HashMap<String, String> hashMap2 = Params.setParams2();
        hashMap2.putAll(hashMap);
        HashMap<String, String> sign = Params.getSign(hashMap2);
        HttpManager.instance().getServer(MineServer.class)
                .videoStart(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {

                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }



    public static HttpUtils instance() {
        if (mHttpManager == null) {
            synchronized (HttpUtils.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpUtils();
                }
            }
        }
        return mHttpManager;
    }
    //关注接口
    public String followUser(String userid){
        StringBuffer stringBuffer = new StringBuffer();
        HashMap<String, String> map = new HashMap<>();
        map.put("anchor_id",userid);
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class)
                .followUser(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        FollowUserBean followUserBean = new Gson().fromJson(string, FollowUserBean.class);
                        FollowUserBean.DataBean data = followUserBean.getData();
                        int follow = data.getFollow();
                        stringBuffer.append(follow);
                    }

                    @Override
                    protected void onError(String string) {
                        Toast.makeText(MyApp.getApp(), string, Toast.LENGTH_SHORT).show();
                    }
                });

        return stringBuffer.toString();
    }

    //上传音视频
    public void upLoad(String path){
        duration = 0;
        File file = new File(path);
        //得到文件的MD5
        String md5 = Md5Utils.getMD5(file);
        long length = file.length();
        String name = file.getName();
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            duration = mediaPlayer.getDuration()/1000;
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("filemd5",md5);
        hashMap.put("filesize",length+"");
        hashMap.put("filename",name);
        hashMap.put("filetype",getMimeType(path));
        hashMap.put("filetime", duration +"");
        HashMap<String, String> map = Params.setParams();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class)
                .uploadVideo(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        //上传成功
                        upLoadSuccess(s, path);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //得到文件的类型
    public static String getMimeType(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        String mime = "text/plain";
        if (filePath != null) {
            try {
                mmr.setDataSource(filePath);
                mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            } catch (IllegalStateException e) {
                return mime;
            } catch (IllegalArgumentException e) {
                return mime;
            } catch (RuntimeException e) {
                return mime;
            }
        }
        return mime;
    }

    public void upLoadSuccess(String string,String path){
        StringBuffer stringBuffer = new StringBuffer();
        JSONObject jsonObject = JSON.parseObject(string);
        String code = jsonObject.getString("code");
        if(code.equals("304")){
            VideoAleary videoAleary = new Gson().fromJson(string, VideoAleary.class);
            String path1 = videoAleary.getData().getPath();

        }else{

            UploadVideoBean uploadVideoBean = new Gson().fromJson(string, UploadVideoBean.class);
            UploadVideoBean.DataBean data = uploadVideoBean.getData();
            UploadVideoBean.DataBean.AliyunDataBean aliyunData = data.getAliyunData();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("key",aliyunData.getKey());
            hashMap.put("policy",aliyunData.getPolicy());
            hashMap.put("OSSAccessKeyId",aliyunData.getOSSAccessKeyId());
            hashMap.put("success_action_status",aliyunData.getSuccess_action_status()+"");
            hashMap.put("signature",aliyunData.getSignature());
            hashMap.put("callback",aliyunData.getCallback());
            MediaType mediaType = MediaType.parse("application/octet-stream");
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(mediaType,file);
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (String o : hashMap.keySet()) {
                builder.addFormDataPart(o, hashMap.get(o));
            }
            MultipartBody file1 = builder.addFormDataPart("file", file.getName(), requestBody).build();
            HttpManager.instance().getServer().ossVideo(file1)
                    .compose(RxUtils.rxScheduleThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        protected void onSuccess(String string) {
                            ossVideobean = new Gson().fromJson(string, OSSVideobean.class);
                            url = ossVideobean.getData().getPath();
                            isSuccess=true;
                            stringBuffer.append(url);
                          if(ChatActivity.isShop){
                              ChatActivity.getUrl(url,duration+"");
                          }else{
                              Chat2Activity.getUrl(url,duration+"");
                          }
                        //     ChatActivity.getUrl(url,duration+"");
                        }

                        @Override
                        protected void onError(String string) {
                            Log.i("wxy", "onError: "+string);

                        }
                    });

        }
    }
    public String getPath(String url){

        return url;
    }

    //微信参数
   public void getWechat(HashMap<String, String> map){
            HashMap<String, String> hashMap = Params.setParams();
            hashMap.putAll(map);
             HashMap<String, String> sign = Params.getSign(hashMap);
             HttpManager.instance().getServer(MyServer.class).getWeChatPay(sign)
                     .compose(RxUtils.rxScheduleThread())
                     .subscribe(new BaseObserver() {
                         @Override
                         protected void onSuccess(String string) {
                             WebUtils.wetChatSuccess(string);

                         }

                         @Override
                         protected void onError(String string) {

                         }
                     });

           }
    //支付宝参数
    public void getAli(HashMap<String, String> map){
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).getAliparameter(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        WebUtils.AliPay(string);

                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    //发送验证码

    public static void sendVertify(String phone){
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.put("mobile", phone);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).getVerifty(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        Gson gson = new Gson();
                        VeriftyBean veriftyBean = gson.fromJson(string, VeriftyBean.class);
                        code_id = veriftyBean.getData().getCode_id();
                    }
                    @Override
                    protected void onError(String string) {

                    }

                });

    }
}
