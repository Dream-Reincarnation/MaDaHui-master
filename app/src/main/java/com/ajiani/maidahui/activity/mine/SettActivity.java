package com.ajiani.maidahui.activity.mine;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationManagerCompat;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.SettUtils;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.mine.AutonymBean;
import com.ajiani.maidahui.bean.mine.UserInfo;
import com.ajiani.maidahui.mInterface.mine.AutonymIn;
import com.ajiani.maidahui.presenters.mine.AutonymPresenter;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static org.json.XMLTokener.entity;

public class SettActivity extends BaseActivity<AutonymIn.AutonymView, AutonymPresenter> implements AutonymIn.AutonymView , UMAuthListener {
    private final String TAG = "wxy";
    @BindView(R.id.sett_back)
    ImageView settBack;
    @BindView(R.id.setimg)
    ImageView settImg;
    @BindView(R.id.sett_name)
    TextView settName;
    @BindView(R.id.sett_lin)
    LinearLayout settLin;
    @BindView(R.id.sett_namelin)
    LinearLayout settNamelin;
    @BindView(R.id.sett_sign)
    TextView settSign;
    @BindView(R.id.set_noti)
    TextView settNotifi;
    @BindView(R.id.sett_signlin)
    LinearLayout settSignlin;
    @BindView(R.id.sett_sex)
    TextView settSex;
    @BindView(R.id.sett_autonmy)
    TextView settAutonmy;
    @BindView(R.id.sett_sexlin)
    LinearLayout settSexlin;
    @BindView(R.id.sett_birth)
    TextView settBirth;
    @BindView(R.id.sett_birthlin)
    LinearLayout settBirthlin;
    @BindView(R.id.sett_heigh)
    TextView settHeigh;
    @BindView(R.id.sett_heighlin)
    LinearLayout settHeighlin;
    @BindView(R.id.sett_binds)
    TextView settBinds;
    @BindView(R.id.sett_bindslin)
    LinearLayout settBindslin;
    @BindView(R.id.sett_exitlin)
    LinearLayout settExitlin;
    @BindView(R.id.sett_aboutlin)
    LinearLayout settAboutlin;
    private Bundle bundle;
    private Intent intent;
    private UserInfo.DataBean dataBean;
    private String weight;
    private boolean notificationEnabled;
    private UserInfo userInfo;
    private String autonym;
    private AutonymBean autonymBean;
    private String autonyms;
    private Bundle bundle1;
    private int wechat1;

    @Override
    protected void initData() {

    }
    @Override
    protected void onResume() {
        super.onResume();
        notificationEnabled = isNotificationEnabled(this);
        if(notificationEnabled){
            settName.setText("已开启");
            settNotifi.setVisibility(View.GONE);
            settImg.setVisibility(View.GONE);
        }else{
            settName.setText("去开启");
            settImg.setVisibility(View.VISIBLE);
            settNotifi.setVisibility(View.VISIBLE);
        }
        autonym = (String) SPUtils.get(this, "autonym", "");
        if(autonym.equals("0")){
            settAutonmy.setText("未认证");
        }else {
            settAutonmy.setText("已认证");
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("is_all",autonym);
        mPresenter.getAutonymInfo(hashMap);
    }

    @Override
    protected void initView() {
        //初始化数据
        //EventBus.getDefault().register(this);
        String success = (String) SPUtils.get(this, "userinfo", "");
        if (success.length() > 5) {
            userInfo = new Gson().fromJson(success, UserInfo.class);

            wechat1 = userInfo.getData().getWechat();
            if (wechat1 == 0) {
                settBinds.setText("未绑定");
            } else {
                settBinds.setText("已绑定");
            }

            UserInfo.DataBean dataBean = userInfo.getData();
            this.dataBean = dataBean;
            String wechat = dataBean.getAlipay();
        } else {
            // settBind.setText("未绑定");
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_sett;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.sett_back, R.id.sett_namelin,R.id.sett_lin, R.id.sett_signlin, R.id.sett_sexlin, R.id.sett_birthlin, R.id.sett_heighlin, R.id.sett_bindslin, R.id.sett_exitlin, R.id.sett_aboutlin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sett_lin:
             //跳转到实名认证
               /* if(autonym.equals("0")){
                    JumpUtils.gotoActivity(this,AutonymASetting.class);
                }else{*/
              if(autonymBean==null){
                   bundle1 = new Bundle();
                  bundle1.putInt("release",0);
                  JumpUtils.gotoActivity(this,AutonymASetting.class);
              }else if(autonymBean.getData().getStatus()==1){
                  bundle1 = new Bundle();
                  bundle1.putString("autonym",autonyms);
                  JumpUtils.gotoActivity(this,AutonymWinActivity.class,bundle1);
              }else{
                  bundle1=new Bundle();
                  bundle1.putBoolean("isBack",false);
                  JumpUtils.gotoActivity(this,AutonymActivity.class,bundle1);
              }

//                }

                break;
            case R.id.sett_back:
                finish();
                break;
            case R.id.sett_namelin:
                if(notificationEnabled){
                    //已开启
                    settImg.setVisibility(View.GONE);
                }else{
                    //去开启 跳转设置页面
                    settImg.setVisibility(View.VISIBLE);
                    SettUtils.gotoSet(this);
                }
                break;
            case R.id.sett_signlin:
                //跳转到设置个人信息接口
                bundle = new Bundle();
                JumpUtils.gotoActivity(this,SetInfoAvtivity.class,bundle);
                break;
            case R.id.sett_sexlin:
                //跳转到地址管理
                bundle = new Bundle();
                bundle.putString("type","place");
                bundle.putString("title","地址管理");
                JumpUtils.gotoActivity(this,WebManagerActivity.class,bundle);
                break;
            case R.id.sett_birthlin:
                //调到账号与安全页面  弹窗

                bundle = new Bundle();
                bundle.putString("autonym",autonyms);
                JumpUtils.gotoActivity(SettActivity.this,AccountActivity.class,bundle);
                break;
            case R.id.sett_heighlin:
                //删除缓存
                String cache = FileUtils.getCache(this);
                File file = new File(cache);
                File[] files = file.listFiles();
                long size = 0;
                for (int i = 0; i < files.length; i++) {
                    File childFile = files[i];
                    String childName = childFile.getName();
                    long fileSize = formetFileSize(childFile);

                    size+=fileSize;
                    Log.i(TAG, "onViewClicked: "+size);
                    Log.i(TAG, "onViewClicked: "+childName);

                }

             /*   Log.i(TAG, "onViewClicked: "+cache);
                File file = new File(cache);
                long length = file.length();
                Log.i(TAG, "onViewClicked: "+length);*/

                break;

            case R.id.sett_bindslin:
                bundle=new Bundle();
                bundle.putInt("wehcat",wechat1);
                //绑定公众号
                JumpUtils.gotoActivity(this, BindWechatActivity.class,bundle);

                break;
            case R.id.sett_exitlin:
                showMyDialog();
                break;
            case R.id.sett_aboutlin:

                //跳转页面
                 bundle=new Bundle();
                bundle.putString("url","https://www.maidahui.com/wechat/document");
                JumpUtils.gotoActivity(this, AboutActivity.class,bundle);
                break;
        }
    }
    private long formetFileSize(File file){
       long fileSize = 0;
        if (file == null){

        }
        long fileLength = file.length();

        return fileLength;
    }


    //退出登录弹出的
    private void showMyDialog() {




        // 创建退出对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = getIntent();
                setResult(4221,intent);
                finish();
                UMShareAPI.get(MyApp.getApp()).deleteOauth(SettActivity.this, SHARE_MEDIA.WEIXIN, SettActivity.this);
                SPUtils.put(SettActivity.this, "token", "");
                SPUtils.remove(SettActivity.this, "token");
            }
        });

        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == 1234) {
            String name = data.getStringExtra("name");
            settName.setText(name);
        }
        if (requestCode == 22 && resultCode == 1234) {
            String name = data.getStringExtra("name");
            settBirth.setText(name);
        }
        if (requestCode == 33 && resultCode == 1234) {
            String name = data.getStringExtra("name");
            settSign.setText(name);
        }
        if (requestCode == 44 && resultCode == 1234) {
            String name = data.getStringExtra("name");
            settSex.setText(name);
        }
        if (requestCode == 55 && resultCode == 1234) {
            String name = data.getStringExtra("name");
            settHeigh.setText(name + " cm");
        }
        if (requestCode == 66 && resultCode == 1234) {
            String name = data.getStringExtra("name");

        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        if(map!=null){
            for (Iterator y = map.keySet().iterator(); y.hasNext();) {
                String obj = (String) y.next();
                Log.i("wxy", "onComplete: "+"key=" + obj + " value=" + map.get(obj));
            }
        }
        Log.i(TAG, "onComplete: "+i);
        Log.i(TAG, "onComplete: "+share_media.toString());

    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        Log.i(TAG, "onError: "+throwable.getMessage());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        Toast.makeText(this, "取消了", Toast.LENGTH_SHORT).show();
    }

    //判断是否开启通知
    private boolean isNotificationEnabled(Context context) {
        boolean isOpened = false;
        try {
            isOpened = NotificationManagerCompat.from(context).areNotificationsEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            isOpened = false;
        }
        return isOpened;

    }

    @Override
    protected AutonymPresenter preparePresenter() {
        return new AutonymPresenter();
    }

    @Override
    public void authenticaSuccess(String success) {

    }

    @Override
    public void checkAutonym(String success) {
        autonyms=success;
        SPUtils.put(this,"autonyms",autonyms);
        autonymBean = new Gson().fromJson(success, AutonymBean.class);
        int status = autonymBean.getData().getStatus();
        if(status==1){
            SPUtils.put(this, "autonym", 1+"");
        }
        settLin.setEnabled(true);
    }

    @Override
    public void upLoadSuccess(String s) {

    }

    @Override
    public void error(String error) {

    }
}
