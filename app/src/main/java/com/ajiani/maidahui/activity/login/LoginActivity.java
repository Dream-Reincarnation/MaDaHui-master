package com.ajiani.maidahui.activity.login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.login.LoginBean;
import com.ajiani.maidahui.bean.sockets.LogicBean;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends SimpleActivity {
    @BindView(R.id.wechat_login)
    LinearLayout wechatLogin;
    @BindView(R.id.phone_login)
    LinearLayout phoneLogin;
    @BindView(R.id.login_ban)
    TextView ban;
    private Intent mIntent;
    private int posstion;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if(bundle!=null){
            posstion = bundle.getInt("posstion");
        }
        Drawable background = wechatLogin.getBackground();
        Drawable drawable = DrawUtils.setSolid(R.color.wechatgreen, background);
        wechatLogin.setBackground(drawable);
        Drawable background1 = phoneLogin.getBackground();
        Drawable drawable2 = DrawUtils.setSolid(R.color.phoneloginbg, background1);
        phoneLogin.setBackground(drawable2);
        try {
            String versionName = getVersionName();
            ban.setText("当前版本" + versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
        /*  verifyCodeView = (VerifyCodeView) findViewById(R.id.verify_code_view);
        verifyCodeView.setInputCompleteListener(new VerifyCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                Toast.makeText(MainActivity.this, "inputComplete: " + verifyCodeView.getEditContent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void invalidContent() {

            }
        });*/
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.wechat_login, R.id.phone_login, R.id.login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.login_back:
                finish();
                break;
            case R.id.wechat_login:
             /*   mIntent = new Intent(this, MusicService2.class);
                Toast.makeText(this, "启动了服务", Toast.LENGTH_SHORT).show();
                startService(mIntent);*/

                UMShareConfig config = new UMShareConfig();
                config.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(this).setShareConfig(config);
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        // Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        for (Iterator y = map.keySet().iterator(); y.hasNext(); ) {
                            String obj = (String) y.next();
                            Log.i("wxy", "onComplete: " + "key=" + obj + " value=" + map.get(obj));
                        }
                        String unionid = map.get("unionid");
                        HashMap<String, String> stringStringHashMap = new HashMap<>();
                        stringStringHashMap.put("unionid", unionid);
                        HashMap<String, String> hashMap = Params.setParams();
                        hashMap.putAll(stringStringHashMap);
                        HashMap<String, String> sign = Params.getSign(hashMap);
                        //调用接口  进行收钱
                        HttpManager.instance().getServer(MyServer.class).weChatLogin(sign).compose(RxUtils.rxScheduleThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    protected void onSuccess(String string) {
                                        Log.i("wxy", "onError: "+string);
                                        //跳转登录页面\
                                        JSONObject jsonObject = JSON.parseObject(string);
                                        String code = jsonObject.getString("code");
                                        if (code.equals("1000")) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString("openid", map.get("openid"));
                                            if (map.get("gender").equals("男")) {
                                                bundle.putString("gender", "1");
                                            } else if (map.get("gender").equals("女")) {
                                                bundle.putString("gender", "2");
                                            } else {
                                                bundle.putString("gender", "0");
                                            }

                                            bundle.putString("head", map.get("profile_image_url"));
                                            bundle.putString("name", map.get("name"));
                                            bundle.putString("unionid", map.get("unionid"));
                                            JumpUtils.gotoActivity(LoginActivity.this, PhoneActivity.class, bundle);
                                            finish();
                                        } else {
                                            LoginBean s = new Gson().fromJson(string, LoginBean.class);
                                            //注册私信
                                            String headimgurl = s.getData().getHeadimgurl();
                                            String nickname = s.getData().getNickname();
                                            String user_id = s.getData().getUser_id();
                                            LogicBean logicBean = new LogicBean(user_id, nickname, headimgurl);
                                            String ss = new Gson().toJson(logicBean);
                                            MyApp.socket.emit("conn", ss);
                                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                            //传给 h5
                                            String tooken = (String) SPUtils.get(MyApp.getApp(), "token", "");
                                            Log.i("WXY", "setLoginSuccess: " + s.getData().getToken());
                                            SPUtils.put(MyApp.getApp(), "token", s.getData().getToken());
                                            SPUtils.put(LoginActivity.this, "name", s.getData().getNickname());
                                            SPUtils.put(LoginActivity.this, "head", s.getData().getHeadimgurl());
                                            SPUtils.put(LoginActivity.this, "userid", s.getData().getUser_id());

                                            String url = (String) SPUtils.get(LoginActivity.this, "url", "");
                                            if (url.contains("?")) {
                                                url = url + "&token=" + s.getData().getToken();
                                            } else {
                                                url = url + "?token=" + s.getData().getToken();
                                            }
                                            SPUtils.put(LoginActivity.this, "url", url);
                                            Intent intent = getIntent();
                                            intent.putExtra("posstion",posstion);
                                            setResult(14, intent);
                                            finish();
                                        }

                                    }

                                    @Override
                                    protected void onError(String string) {
                                        Log.i("wxy", "onError: "+string);
                                        Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("wxy", "onError: "+throwable.getMessage());
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
            case R.id.phone_login:
                //stopService(mIntent);
                Intent intent = new Intent(this, PhoneActivity.class);
                startActivityForResult(intent,1124);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1124&&resultCode==1314){
            Intent intent = getIntent();
            intent.putExtra("posstion",posstion);
            setResult(14, intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


}
