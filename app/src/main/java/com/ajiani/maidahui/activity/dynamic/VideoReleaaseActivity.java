package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.Contact;
import com.ajiani.maidahui.bean.Even;
import com.ajiani.maidahui.bean.UpLoadBean;
import com.ajiani.maidahui.bean.dynamic.CommodityBean;
import com.ajiani.maidahui.bean.dynamic.CompleteBean;
import com.ajiani.maidahui.bean.dynamic.OSSVideobean;
import com.ajiani.maidahui.bean.dynamic.SendSuccBean;
import com.ajiani.maidahui.bean.dynamic.UploadVideoBean;
import com.ajiani.maidahui.bean.dynamic.VideoAleary;
import com.ajiani.maidahui.bean.dynamic.VideoCommdity;
import com.ajiani.maidahui.bean.dynamic.shop.CommodityInfo;
import com.ajiani.maidahui.bean.dynamic.topic.TopObject;
import com.ajiani.maidahui.mInterface.dynamic.ReleaseMin;
import com.ajiani.maidahui.presenters.dynamic.ReleasePresenter;
import com.ajiani.maidahui.weight.ReleaseEditText;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoReleaaseActivity extends BaseActivity<ReleaseMin.releaseView, ReleasePresenter> implements ReleaseMin.releaseView, TencentLocationListener {

    @BindView(R.id.vrelease_video)
    ImageView video;
    @BindView(R.id.spin_kit)
    FrameLayout spinKit;
    @BindView(R.id.videorelease_more)
    ImageView addresAdd;
    @BindView(R.id.videorelease_address_del)
    ImageView addressDel;
    @BindView(R.id.vrellase_title)
    TextView releaseTitle;
    @BindView(R.id.vrellase_text)
    ReleaseEditText releaseText;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.vrelease_shop)
    TextView vreleaseShop;
    @BindView(R.id.vrelease_num)
    TextView vreleaseNum;
    @BindView(R.id.friends)
    TextView friends;
    @BindView(R.id.vrellase_location)
    TextView releaseLocation;
    @BindView(R.id.vrellase_lin1)
    LinearLayout releaseLin1;
    @BindView(R.id.vrellase_mode)
    TextView releaseMode;
    @BindView(R.id.vrellase_lin2)
    LinearLayout releaseLin2;
    @BindView(R.id.vrellase_savelin)
    LinearLayout releaseSavelin;
    @BindView(R.id.vrellase_btsend)
    Button releaseBtsend;
    StringBuffer mText = new StringBuffer();
    StringBuffer mTopic = new StringBuffer();
    @BindView(R.id.vrelease_shop1)
    ImageView vreleaseShop1;
    @BindView(R.id.vrelease_farme1)
    FrameLayout vreleaseFarme1;
    @BindView(R.id.vrelease_shop2)
    ImageView vreleaseShop2;
    @BindView(R.id.vrelease_farme2)
    FrameLayout vreleaseFarme2;
    @BindView(R.id.vrelease_shop3)
    ImageView vreleaseShop3;
    @BindView(R.id.vrelease_farme3)
    FrameLayout vreleaseFarme3;
    @BindView(R.id.vrellase_lin3)
    LinearLayout vrellaseLin3;
    private int duration;
    private File file;
    private int id;
    private String topic;
    private String musicid;
    public ArrayList<String> list = new ArrayList<String>();
    private String path;
    private boolean b;
    private String mimeType;
    private String lat;
    private String lon;
    private String thumb;
    public static ArrayList<CompleteBean.DataBean> dataBeans;
    public  ArrayList<CompleteBean.DataBean> shopBeans;
    public  ArrayList<String> topics;
    private StringBuffer stringBuffer;
    private StringBuffer stringName;
    private String address;
    private int musicId;
    private String TAG="wxy";
    private Bitmap frameAtTime;


    @Override
    protected void onResume() {
        super.onResume();
        address = (String) SPUtils.get(this, "address", "");
        if (address.length() > 5) {
            String[] split = address.split(",");
            releaseLocation.setText(split[0]);
            addressDel.setVisibility(View.VISIBLE);
            addresAdd.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {

        releaseText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

              if(String.valueOf(s).equals("@")){
                  JumpUtils.gotoActivity(VideoReleaaseActivity.this,FriendsActivity.class);
              }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        title.setText("发布");
        SPUtils.remove(this, "address");
        if (EventBus.getDefault().isRegistered(this)) {

        } else {
            EventBus.getDefault().register(this);
        }



    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(Even text) {
        switch (text.getCode()) {
            case 1:
                switch (Integer.parseInt(text.getText())) {
                    case 0:
                        releaseMode.setText("公开");
                        break;
                    case 1:
                        releaseMode.setText("关注");
                        break;
                    case 2:
                        releaseMode.setText("私密");
                        break;
                }
                break;
            case 2:
                Log.i("wxy", "getMessage: "+topics.size());
                releaseText.setData(new TopObject("#",text.getText()));

                String s = releaseText.getText().toString();
                mText = new StringBuffer();
                topic = text.getText();
                mText.append(s);
                if (mText.length() + topic.length() + 2 > 60) {
                    Toast.makeText(this, "最多60个字", Toast.LENGTH_SHORT).show();
                } else {
                    mText.append("#" + topic + " ");
                    SpannableString spannableString = new SpannableString(mText.toString());
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#f55056"));
                    for (int i = 0; i < topics.size(); i++) {
                        String s1 = topics.get(i);
                        int i1 = mText.toString().indexOf(s1);
                        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#f55056")),i1, i1+s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                        spannableString.setSpan(foregroundColorSpan,s.length(), mText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


                    releaseText.setText(spannableString);
                    releaseText.setSelection(spannableString.length());
                    topics.add("#"+ topic+ " ");
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage2(Contact contact) {

        String name = contact.getName();
        String id = contact.getId();
        releaseText.setData(new TopObject(id,contact.getName()));
        String s = releaseText.getText().toString();
        mText = new StringBuffer();
        mText.append(s);
        mText.append("@" + name+" ");
        SpannableString spannableString = new SpannableString(mText.toString());
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#f55056"));
        for (int i = 0; i < topics.size(); i++) {
            String s1 = topics.get(i);
            int i1 = mText.toString().indexOf(s1);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#f55056")),i1, i1+s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        spannableString.setSpan(foregroundColorSpan,s.length(), mText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        releaseText.setText(spannableString);
        releaseText.setSelection(spannableString.length());

        topics.add("@"+name+" ");
        list.add(id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage3(ArrayList<CompleteBean.DataBean> list) {
        dataBeans.clear();
        dataBeans.addAll(list);
        if(dataBeans.size()>2){
            vreleaseFarme1.setVisibility(View.VISIBLE);
            vreleaseFarme2.setVisibility(View.VISIBLE);
            vreleaseFarme3.setVisibility(View.VISIBLE);
            Glide.with(this).load(dataBeans.get(0).getThumb()).into(vreleaseShop1);
            Glide.with(this).load(dataBeans.get(1).getThumb()).into(vreleaseShop2);
            Glide.with(this).load(dataBeans.get(2).getThumb()).into(vreleaseShop3);
            vreleaseShop.setText("我的购物袋");
            vreleaseNum.setText(dataBeans.size()+"件");
        }else if(dataBeans.size()>1){
            vreleaseFarme1.setVisibility(View.VISIBLE);
            vreleaseFarme2.setVisibility(View.VISIBLE);
            vreleaseFarme3.setVisibility(View.GONE);
            Glide.with(this).load(dataBeans.get(0).getThumb()).into(vreleaseShop1);
            Glide.with(this).load(dataBeans.get(1).getThumb()).into(vreleaseShop2);
        //    Glide.with(this).load(list.get(2).getThumb()).into(vreleaseShop3);
            vreleaseShop.setText("我的购物袋");
            vreleaseNum.setText(dataBeans.size()+"件");
        }else if(dataBeans.size()>0){
            vreleaseFarme1.setVisibility(View.VISIBLE);
            vreleaseFarme2.setVisibility(View.GONE);
            vreleaseFarme3.setVisibility(View.GONE);
            Glide.with(this).load(dataBeans.get(0).getThumb()).into(vreleaseShop1);
           /* Glide.with(this).load(list.get(1).getThumb()).into(vreleaseShop2);
            Glide.with(this).load(list.get(2).getThumb()).into(vreleaseShop3);*/
            vreleaseShop.setText("我的购物袋");
            vreleaseNum.setText(dataBeans.size()+"件");
        }else{
            vreleaseFarme1.setVisibility(View.GONE);
            vreleaseFarme2.setVisibility(View.GONE);
            vreleaseFarme3.setVisibility(View.GONE);
            vreleaseNum.setText("添加");
            vreleaseShop.setText("商品货架");
            
        }

    }

    @Override
    protected void initView() {

        topics = new ArrayList<>();
        shopBeans=new ArrayList<>();
        dataBeans = new ArrayList<>();

        if(HomeActivity.lat==null||HomeActivity.lat.equals("")){

            //注册地图地位
            TencentLocationRequest tencentLocationRequest = TencentLocationRequest.create();
            tencentLocationRequest.setInterval(30000).setRequestLevel(3).setAllowCache(true);
            TencentLocationManager locationManager = TencentLocationManager.getInstance(this);
            int error = locationManager.requestLocationUpdates(tencentLocationRequest, this);

        }



        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        musicId = bundle.getInt("music");
        path = bundle.getString("file");
        thumb = bundle.getString("thumb");

        releaseMode.setText("公开");
        Glide.with(this).load(new File(thumb)).into(video);

        releaseText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (releaseText.getText().toString().length() == 60) {
                    Toast.makeText(VideoReleaaseActivity.this, "最多只能写60个字", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (releaseText.getText().toString().length() == 60) {
                    Toast.makeText(VideoReleaaseActivity.this, "最多只能写60个字", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_videorelease;
    }

    @OnClick({R.id.back, R.id.vrelease_video, R.id.vrellase_lin3, R.id.videorelease_address_del, R.id.vrellase_text, R.id.vrellase_title, R.id.friends, R.id.vrellase_lin1, R.id.vrellase_lin2, R.id.vrellase_savelin, R.id.vrellase_btsend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vrellase_lin3:
                //添加商品
                JumpUtils.gotoActivity(this, AddShopActivity.class);
                break;
            //删除地址
            case R.id.videorelease_address_del:
                releaseLocation.setText("添加位置");
                addresAdd.setVisibility(View.VISIBLE);
                addressDel.setVisibility(View.GONE);
                SPUtils.remove(this, "address");
                break;

            case R.id.back:
                finish();
                break;
            case R.id.vrelease_video:
                //跳转播放视频
                Bundle bundle = new Bundle();
                bundle.putString("file", path);
                JumpUtils.gotoActivity(this, VideoActivity.class, bundle);
                break;
            case R.id.vrellase_text:
                break;
            case R.id.vrellase_title:
                //添加标题
                JumpUtils.gotoActivity(this, SelectTalk.class);
                break;
            case R.id.friends:
                //@好友
                JumpUtils.gotoActivity(this, FriendsActivity.class);
                break;
            case R.id.vrellase_lin1:
                //添加位置
                JumpUtils.gotoActivity(this, AddressActivity.class);
                break;
            case R.id.vrellase_lin2:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("mode",getMode(releaseMode.getText().toString()));

                //谁可以看
                JumpUtils.gotoActivity(this,PrivateActivity.class,bundle1);

                /*Intent intent = new Intent(this, PrivateActivity.class,bundle1);
                startActivityForResult(intent, 1000);*/
                break;
            case R.id.vrellase_savelin:
                //保存 图片，标题，地址
                break;
            case R.id.vrellase_btsend:
                stringBuffer = new StringBuffer();
                stringName = new StringBuffer();
                List<TopObject> objects = releaseText.getObjects();
                for (int i = 0; i <objects.size() ; i++) {
                    TopObject topObject = objects.get(i);
                    String rule = topObject.getRule();
                    String text = topObject.getText();
                    if(rule.equals("#")){
                        stringBuffer.append(text+",");
                    }else{
                        stringName.append(rule+",");
                    }
                }
                Log.i(TAG, "onViewClicked: "+stringBuffer.toString());
                if(stringBuffer.length()>0){
                    stringBuffer.replace(stringBuffer.length()-2, stringBuffer.length()-1,"");
                }
                if(stringName.length()>0){
                    stringName.replace(stringName.length()-2, stringName.length()-1,"");
                }
                Log.i(TAG, "onViewClicked: sssss"+stringBuffer.toString());
                releaseBtsend.setEnabled(false);
                //网络请求
                spinKit.setVisibility(View.VISIBLE);
                //先上传第一帧的图片
                File file = new File(thumb);
                mPresenter.setData(file);
                spinKit.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected ReleasePresenter preparePresenter() {
        return new ReleasePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBeans.clear();
        EventBus.getDefault().unregister(this);
    }


    public  String getMimeType(String filePath) {
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

    @Override
    public void success(String string) {
        UpLoadBean upLoadBean = new Gson().fromJson(string, UpLoadBean.class);
        id = upLoadBean.getData().getId();
        //上传视频
        this.file = new File(path);
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            duration = mediaPlayer.getDuration() / 1000;
        } catch (IOException e) {
            e.printStackTrace();
        }
        mimeType = getMimeType(path);

        String md5 = Md5Utils.getMD5(this.file);
        //得到时间

        long length = this.file.length();
        String name = this.file.getName();


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("filemd5", md5);
        hashMap.put("filesize", length + "");
        hashMap.put("filename", name);
        hashMap.put("filetype", mimeType);
        hashMap.put("filetime", duration + "");

        mPresenter.upload(hashMap);
    }

    @Override
    public void sendSuccess(String success) {
        SendSuccBean sendSuccBean = new Gson().fromJson(success, SendSuccBean.class);



        releaseBtsend.setEnabled(false);
        JumpUtils.gotoActivity(this, HomeActivity.class);
    }

    private int getMode(String mode) {
        if (mode.equals("公开")) {
            return 0;
        } else if (mode.equals("关注")) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void uploadSuccess(String success) {
        //
        JSONObject jsonObject = JSON.parseObject(success);
        String code = jsonObject.getString("code");
        if (code.equals("304")) {
            VideoAleary videoAleary = new Gson().fromJson(success, VideoAleary.class);
            int mId = videoAleary.getData().getId();
            String path = videoAleary.getData().getPath();
            //就拿到了id
            //发布视()
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("thumb", id + "");
            hashMap.put("video_type", "video");
            hashMap.put("video", mId + "");


            //获取内容
            String s = releaseText.getText().toString();
            String replace = s.replace(mText, "");
            hashMap.put("title", s);
            //获取谁可见
            String mode = releaseMode.getText().toString();
            int mode1 = getMode(mode);
            hashMap.put("role", mode1 + "");
             //添加音乐id;
            if(musicId!=0){
                hashMap.put("music", musicId + "");
            }


            //添加话题
            if (stringBuffer.toString().length()>0) {
                hashMap.put("topic",stringBuffer.toString());
            }
            //添加@的人
            if(stringName.toString().length()>0){
                hashMap.put("user_ids", stringName.toString());
            }
            if(address!=null&&address.length()>5){
                String[] split = address.split(",");
                hashMap.put("landmark", split[0]);
                //精度
                hashMap.put("longitude", split[3]);
                //维度
                hashMap.put("latitude", split[4]);
            }else{
                //精度

                hashMap.put("longitude", HomeActivity.lon);
                //维度
                hashMap.put("latitude", HomeActivity.lat);
            }

            //判断是否添加商品
            if(dataBeans!=null&&dataBeans.size()>0){
                //格式化参数变为json
                String formative = formative(dataBeans);
                hashMap.put("goods_json",formative);
            }
            mPresenter.getData(hashMap);
        } else {
            UploadVideoBean uploadVideoBean = new Gson().fromJson(success, UploadVideoBean.class);
            UploadVideoBean.DataBean data = uploadVideoBean.getData();
            UploadVideoBean.DataBean.AliyunDataBean aliyunData = data.getAliyunData();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("key", aliyunData.getKey());
            hashMap.put("policy", aliyunData.getPolicy());
            hashMap.put("OSSAccessKeyId", aliyunData.getOSSAccessKeyId());
            hashMap.put("success_action_status", aliyunData.getSuccess_action_status() + "");
            hashMap.put("signature", aliyunData.getSignature());
            hashMap.put("callback", aliyunData.getCallback());
            mPresenter.ossVideo(hashMap, file);
        }
    }

    private String formative(ArrayList<CompleteBean.DataBean> dataBeans) {
        ArrayList<VideoCommdity> commodityBeans = new ArrayList<>();
        Log.i("wxy", "formative: "+dataBeans.get(0).getThumb());
        for (int i = 0; i < dataBeans.size(); i++) {
            CompleteBean.DataBean dataBean = dataBeans.get(i);

            VideoCommdity videoCommdity = new VideoCommdity(dataBean.getAid() + "", dataBean.getTitle(), dataBean.getThumb(), "0", "", "0", "0", "0", "0");
            commodityBeans.add(videoCommdity);
        }
        String s = new Gson().toJson(commodityBeans);
        return s;
    }

    @Override
    public void ossSuccess(String success) {
        OSSVideobean ossVideobean = new Gson().fromJson(success, OSSVideobean.class);
        String path = ossVideobean.getData().getPath();

        String mId = ossVideobean.getData().getId();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("thumb", id + "");
        hashMap.put("video_type", "video");
        hashMap.put("video", mId + "");
        //添加音乐id;
        if(musicId!=0){
            hashMap.put("music", musicId + "");
        }

        //获取内容
        String s = releaseText.getText().toString();
        String[] s2 = s.split(" ");
        hashMap.put("title", s);
        //获取谁可见
        String mode = releaseMode.getText().toString();
        int mode1 = getMode(mode);
        hashMap.put("role", mode1 + "");
        //添加话题
        if (stringBuffer.toString().length()>0) {
            hashMap.put("topic",stringBuffer.toString());
        }
        //添加@的人
        if(stringName.toString().length()>0){
            hashMap.put("user_ids", stringName.toString());
        }
        if(address!=null&&address.length()>5){
            String[] split = address.split(",");
            hashMap.put("landmark", split[0]);
            //精度
            hashMap.put("longitude", split[3]);
            //维度
            hashMap.put("latitude", split[4]);
        }else{
            //精度
            hashMap.put("longitude", HomeActivity.lon);
            //维度
            hashMap.put("latitude", HomeActivity.lat);
        }

        if(dataBeans!=null&&dataBeans.size()>0){
            //格式化参数变为json
            String formative = formative(dataBeans);
            hashMap.put("goods_json",formative);
        }
        mPresenter.getData(hashMap);
        //mPresenter.getData(hashMap);

    }

    @Override
    public void error(String error) {
        releaseBtsend.setEnabled(true);
        spinKit.setVisibility(View.GONE);
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (TencentLocation.ERROR_OK == i) {
            // 定位成功
            if (tencentLocation != null) {

                   // 维度
                HomeActivity.lat = String.valueOf(tencentLocation.getLatitude());
//                精度
                HomeActivity. lon = String.valueOf(tencentLocation.getLongitude());

//                维度
              /*  lat = String.valueOf(tencentLocation.getLatitude());
//                精度
                lon = String.valueOf(tencentLocation.getLongitude());*/

            }
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }




}

