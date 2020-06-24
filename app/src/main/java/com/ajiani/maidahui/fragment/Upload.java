package com.ajiani.maidahui.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.dynamic.OSSVideobean;
import com.ajiani.maidahui.bean.dynamic.UploadVideoBean;
import com.ajiani.maidahui.bean.dynamic.VideoAleary;
import com.ajiani.maidahui.mInterface.dynamic.ReleaseMin;
import com.ajiani.maidahui.presenters.dynamic.ReleasePresenter;
import com.ajiani.maidahui.services.MusicService3;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Upload extends BaseActivity<ReleaseMin.releaseView, ReleasePresenter> implements ReleaseMin.releaseView {

    private int duration;
    private File file;

    @Override
    protected ReleasePresenter preparePresenter() {
        return new ReleasePresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String   mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    mFilePath += "/AudioRecord";
    ArrayList<String> strings = listFiles(new File(mFilePath));
    file = new File(strings.get(1));
    String md5 = Md5Utils.getMD5(file);
    //得到时间
    long length = file.length();
    String name = file.getName();
        Log.i("WXY", "onActivityResult: "+md5+"==="+md5.length());
        Log.i("WXY", "onActivityResult: "+length+"==="+name);
    MediaPlayer mediaPlayer = new MediaPlayer();
        try {
        mediaPlayer.setDataSource(strings.get(1));
        mediaPlayer.prepare();
        duration = mediaPlayer.getDuration()/1000;
    } catch (IOException e) {
        e.printStackTrace();
    }
    HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("filemd5",md5);
        hashMap.put("filesize",length+"");
        hashMap.put("filename",name);
        hashMap.put("filetype","audio");
        hashMap.put("filetime",duration+"");
       mPresenter.upload(hashMap);
}
    public  ArrayList<String> listFiles(File dir) {
        ArrayList<String> strings = new ArrayList<>();
        if (!dir.exists() || !dir.isDirectory())
            return strings;
        String[] files = dir.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(dir, files[i]);
            if (file.isFile()) {
                String absolutePath = file.getAbsolutePath();
                strings.add(absolutePath);

            } else {


                listFiles(file);
            }
        }
        return strings;
    }
    @Override
    protected int createLayout() {
        return R.layout.activity_up;
    }

    @Override
    public void success(String string) {

    }

    @Override
    public void sendSuccess(String success) {

    }

    @Override
    public void uploadSuccess(String success) {
        JSONObject jsonObject = JSON.parseObject(success);
        String code = jsonObject.getString("code");
        if(code.equals("304")){
            VideoAleary videoAleary = new Gson().fromJson(success, VideoAleary.class);
            int id = videoAleary.getData().getId();
            String path = videoAleary.getData().getPath();

            Intent intent = new Intent(this, MusicService3.class);
            intent.putExtra("name",path);
            startService(intent);
        }else{
            UploadVideoBean uploadVideoBean = new Gson().fromJson(success, UploadVideoBean.class);
            UploadVideoBean.DataBean data = uploadVideoBean.getData();
            UploadVideoBean.DataBean.AliyunDataBean aliyunData = data.getAliyunData();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("key",aliyunData.getKey());
            hashMap.put("policy",aliyunData.getPolicy());
            hashMap.put("OSSAccessKeyId",aliyunData.getOSSAccessKeyId());
            hashMap.put("success_action_status",aliyunData.getSuccess_action_status()+"");
            hashMap.put("signature",aliyunData.getSignature());
            hashMap.put("callback",aliyunData.getCallback());
            mPresenter.ossVideo(hashMap,file);
        }


        //进行上传

    }

    @Override
    public void ossSuccess(String success) {
        OSSVideobean ossVideobean = new Gson().fromJson(success, OSSVideobean.class);
        String path = ossVideobean.getData().getPath();
        Log.i("WXY", "ossSuccess: "+path);
        String id = ossVideobean.getData().getId();

    }
}
