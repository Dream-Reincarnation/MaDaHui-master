package com.ajiani.maidahui.activity.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.CountDownTimerUtils;
import com.ajiani.maidahui.Utils.GpsUtil;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.mine.AboutActivity;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.login.LoginBean;
import com.ajiani.maidahui.bean.login.VeriftyBean;
import com.ajiani.maidahui.bean.sockets.LogicBean;
import com.ajiani.maidahui.mInterface.VeriftyIn;
import com.ajiani.maidahui.presenters.VeriftyPresenter;
import com.ajiani.maidahui.weight.verfity.PhoneCode;
import com.google.gson.Gson;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;

import butterknife.OnClick;

import pub.devrel.easypermissions.EasyPermissions;


public class PhoneActivity extends BaseActivity<VeriftyIn.VeriftyView, VeriftyPresenter> implements VeriftyIn.VeriftyView, EasyPermissions.PermissionCallbacks, TencentLocationListener {
    @BindView(R.id.phone_back)
    FrameLayout phoneBack;
    @BindView(R.id.phone_ed)
    EditText phoneEd;
    @BindView(R.id.phone_verifty)
    EditText phoneVerifty;
    @BindView(R.id.phone_send)
    TextView phoneSend;
    @BindView(R.id.phonte_bt)
    Button phonteBt;
    @BindView(R.id.goto_pass)
    TextView gotoPass;
    @BindView(R.id.phone_text)
    TextView phoneText;
    private String mEditContent;
    private String mPhone;
    private String mCode_id;
    private String province;
    private String city;
    private String lon;
    private String lat;
    //验证码按钮是否可以点击
    boolean isClick;
    private Timer timer;

    @Override
    protected void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {
        Drawable background = phoneSend.getBackground();
        phoneSend.setBackground(DrawUtils.setSolid(R.color.colorgrey, background));
        Drawable background2 = phonteBt.getBackground();
        phonteBt.setBackground(DrawUtils.setSolid(R.color.colorgrey, background2));
        testPersmission();
        phoneSend.setEnabled(false);
        phonteBt.setEnabled(false);
        phoneEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (phoneEd.getText().toString().length() == 11) {

                    if (!isClick) {
                        Drawable background = phoneSend.getBackground();
                        phoneSend.setBackground(DrawUtils.setSolid(R.color.Thme, background));
                        phoneSend.setEnabled(true);
                    }

                } else {

                    if (!isClick) {
                        Drawable background = phoneSend.getBackground();
                        phoneSend.setBackground(DrawUtils.setSolid(R.color.colorgrey, background));
                        phoneSend.setEnabled(true);
                    }
                }
                if (phoneEd.getText().length() == 11 && phoneVerifty.getText().toString().length() == 4) {
                    Drawable background2 = phonteBt.getBackground();
                    phonteBt.setBackground(DrawUtils.setSolid(R.color.Thme, background2));
                    phonteBt.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPhone = phoneEd.getText().toString();

            }
        });
        phoneVerifty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (phoneEd.getText().length() == 11 && phoneVerifty.getText().toString().length() == 4) {
                    Drawable background2 = phonteBt.getBackground();
                    phonteBt.setBackground(DrawUtils.setSolid(R.color.Thme, background2));
                    phonteBt.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        TencentLocationRequest tencentLocationRequest = TencentLocationRequest.create();
        tencentLocationRequest.setInterval(30000).setRequestLevel(3).setAllowCache(true);
        TencentLocationManager locationManager = TencentLocationManager.getInstance(this);
        int error = locationManager.requestLocationUpdates(tencentLocationRequest, this);
        /*if (error == 0) {
            Log.i("WXY", "initView: 注册监听器成功");
        } else {
            Log.i("WXY", "initView: 注册监听器失败");
        }*/


    }

    public static final String PHONE_REGEX_MOBILE = "^[1][0-9][0-9]{9}$";

    public boolean isMobile(@NonNull EditText view) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String s = view.getText().toString();
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(s))
            return false;
        else
            return s.matches(telRegex);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_phone;
    }

    int time = 60;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    time--;
                    Drawable background1 = phoneSend.getBackground();
                    phoneSend.setBackground(DrawUtils.setSolid(R.color.colorgrey, background1));
                    phoneSend.setText(time + "s后重发");
                    if (time < 1) {

                        if (phoneEd.getText().toString().length() == 11) {
                            Drawable background = phoneSend.getBackground();
                            phoneSend.setBackground(DrawUtils.setSolid(R.color.Thme, background));
                        } else {
                            Drawable background = phoneSend.getBackground();
                            phoneSend.setBackground(DrawUtils.setSolid(R.color.colorgrey, background));
                        }
                        phoneSend.setText("重新获取");
                        isClick = false;
                        phoneSend.setEnabled(true);

                        timer.cancel();
                        time = 60;
                    }
                    break;
            }
        }
    };


    //TxLocation{level=3,name=豫新公寓,address=河南省郑州市金水区农业路东12号,provider=network,latitude=34.787362,longitude=113.688342,altitude=0.0,accuracy=20.0,cityCode=410105,areaStat=0,nation=中国,province=河南省,city=郑州市,district=金水区,street=政七街,streetNo=,town=文化路街道,village=Unknown,bearing=0.0,time=1566789901788,poilist=[]}
    @OnClick({R.id.phone_verifty, R.id.phone_back, R.id.phone_send, R.id.phonte_bt, R.id.goto_pass, R.id.phone_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.phone_verifty:
                break;
            case R.id.phone_send:
                String mPhone = phoneEd.getText().toString();
                if (mPhone.length() < 10 | mPhone == null) {

                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();

                } else {

                    if (isMobile(phoneEd)) {
                        //开始倒计时
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(1);
                            }
                        };
                        timer = new Timer();
                        timer.schedule(timerTask, 0, 1000);
                       /* CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(phoneSend, 60000, 1000, phoneSend.getBackground()); //倒计时1分钟
                        mCountDownTimerUtils.start();*/
                        phoneSend.setEnabled(false);
                        isClick = true;
                        mPresenter.getData(mPhone);
                    } else {
                        Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }

                }


                break;
            case R.id.phonte_bt:

                if (GpsUtil.isOPen(this)) {
                    testPersmission();
                } else {
                    GpsUtil.openGPS(this);
                }

                mEditContent = phoneVerifty.getText().toString();
                String s = phoneEd.getText().toString();
                if (!isMobile(phoneEd)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mEditContent.length() != 4 | mEditContent.equals("")) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent intent = getIntent();
                    Bundle bundle = intent.getBundleExtra("bundle");


                    HashMap<String, String> hashMap = new HashMap<>();
                    if (bundle != null) {
                        hashMap.put("nickname", bundle.getString("name"));
                        hashMap.put("headimgurl", bundle.getString("head"));
                        hashMap.put("sex", bundle.getString("gender"));
                        hashMap.put("unionid", bundle.getString("unionid"));
                        hashMap.put("openid", bundle.getString("openid"));
                    }
                    hashMap.put("mobile", s);
                    hashMap.put("code", mEditContent);
                    hashMap.put("code_id", mCode_id);
                    hashMap.put("province", province);
                    hashMap.put("city", city);
                    hashMap.put("longitude", lon);
                    hashMap.put("latitude", lat);
                    mPresenter.getLogin(hashMap);
                }
                break;
            case R.id.goto_pass:
                //跳转到面登录
                JumpUtils.gotoActivity(this, PasswActivity.class);
                break;
            case R.id.phone_text:
                //
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://m.maidahui.com/pages/privacy.html");
                JumpUtils.gotoActivity(this, AboutActivity.class, bundle);

                break;
            case R.id.phone_back:
                Intent intent = getIntent();
                setResult(10001, intent);
                finish();
                break;
        }
    }

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    public byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        Mac mac = Mac.getInstance(MAC_NAME);
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        byte[] digest = mac.doFinal(text);
        return digest;
    }

    @Override
    public void error(String error) {
        Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
    }

    //检查权限
    public void testPersmission() {
        String[] psermission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (EasyPermissions.hasPermissions(this, psermission)) {


        } else {
            //去申请
            EasyPermissions.requestPermissions(this, "需要获取你的位置权限", 1001, psermission);
        }

    }

    @Override
    public void setSuccess(VeriftyBean veriftyBean) {
        mCode_id = veriftyBean.getData().getCode_id();
    }

    @Override
    public void setLoginSuccess(LoginBean s) {
        //注册私信
        String headimgurl = s.getData().getHeadimgurl();
        String nickname = s.getData().getNickname();
        String user_id = s.getData().getUser_id();
        LogicBean logicBean = new LogicBean(user_id, nickname, headimgurl);
        String ss = new Gson().toJson(logicBean);
        MyApp.socket.emit("conn", ss);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        //传给 h5
        String tooken = (String) SPUtils.get(MyApp.getApp(), "token", "");
        /*Log.i("WXY", "setLoginSuccess: "+s.getData().getToken());*/
        SPUtils.put(MyApp.getApp(), "token", s.getData().getToken());
        SPUtils.put(this, "name", s.getData().getNickname());
        SPUtils.put(this, "head", s.getData().getHeadimgurl());
        SPUtils.put(this, "userid", s.getData().getUser_id());

        String url = (String) SPUtils.get(this, "url", "");
        if (url.contains("?")) {
            url = url + "&token=" + s.getData().getToken();
        } else {
            url = url + "?token=" + s.getData().getToken();
        }
        SPUtils.put(this, "url", url);
//        EventBus.getDefault().postSticky(111);
//        Intent intent = new Intent(this,HomeActivity.class);
        Intent intent = getIntent();
        setResult(1314, intent);
        finish();
    }

    @Override
    protected VeriftyPresenter preparePresenter() {
        return new VeriftyPresenter();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //省略switch requestCode
        //授权了

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //省略switch requestCode
        //无权限，且被选择"不再提醒"：提醒客户到APP应用设置中打开权限
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            Toast.makeText(this, "拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限", Toast.LENGTH_SHORT).show();
            //todo nothing
        }

        //无权限，只是单纯被拒绝
        else {

            Toast.makeText(this, "拒绝权限，等待下次询问哦", Toast.LENGTH_SHORT).show();
            //todo request permission again
        }


    }
    //  新的位置   错误码   错误信息

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭定位监听器
        timer.cancel();
        TencentLocationManager locationManager = TencentLocationManager.getInstance(this);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (TencentLocation.ERROR_OK == i) {
            // 定位成功
            if (tencentLocation != null) {
                lat = String.valueOf(tencentLocation.getLatitude());
                lon = String.valueOf(tencentLocation.getLongitude());
                city = tencentLocation.getCity();
                province = tencentLocation.getProvince();

            }
        } else {
            // 定位失败
            //  Log.i("WXY", "initView: 注册监听器成功定位失败");
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

}
