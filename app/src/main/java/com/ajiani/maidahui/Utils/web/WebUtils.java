package com.ajiani.maidahui.Utils.web;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ajiani.maidahui.ImageLoader;
import com.ajiani.maidahui.MainActivity;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.CountDownTimerUtils;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.MoneyUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.Utils.share.PopupWindows;
import com.ajiani.maidahui.activity.BannerWebActivity;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.ViewPagerLayoutManagerActivity;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.activity.login.LoginActivity;
import com.ajiani.maidahui.activity.mine.AutonymASetting;
import com.ajiani.maidahui.activity.mine.IntegralDetailsActivity;
import com.ajiani.maidahui.activity.mine.PasswordPay;
import com.ajiani.maidahui.activity.mine.SetPayPass2;
import com.ajiani.maidahui.bean.PictureBean;
import com.ajiani.maidahui.bean.chat.WeXinBean;
import com.ajiani.maidahui.bean.mine.PayParameBean;
import com.ajiani.maidahui.bean.mine.WeChatBean;
import com.ajiani.maidahui.bean.share.GoodsBean;
import com.ajiani.maidahui.bean.share.ShareBean;
import com.ajiani.maidahui.bean.sockets.ShopInfo;
import com.ajiani.maidahui.fragment.HomeFragment;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.enitity.ThumbViewInfo;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pub.devrel.easypermissions.EasyPermissions;

public class WebUtils implements View.OnClickListener {
    public static WebUtils mHttpManager;
    private LinearLayout yue;
    private ImageView image;
    private TextView tv_right;
    private TextView paymoney;
    private ImageView paySel;
    private String[] split2;
    private String TAG="wxy";

    public static WebUtils instance() {
        if (mHttpManager == null) {
            synchronized (HttpUtils.class) {
                if (mHttpManager == null) {
                    mHttpManager = new WebUtils();
                }
            }
        }
        return mHttpManager;
    }

    private static TextView hua3;
    private static TextView hua33;
    private static TextView hua6;
    private static TextView hua66;
    private static TextView hua12;
    private static TextView hua122;
    private static String token;
    private static String sUrl;
    private static WebView web;
    private static String mUrl = MyApp.BaseUrl + "#/";
    private static WindowManager.LayoutParams lp;
    public static Activity activity;

    private void startWexin(String s) {
        //Log.i("wxy", "startWexin: "+s);
        //{"appid":"wxe52595c56f1bfd66","partnerid":"1516783291","prepayid":"wx281437227809984058f1cdb81737697800","package":"Sign=WXPay","noncestr":"j9p63kythnkoa6dxeuyuj4pp7kwnpe7n","timestamp":1572244642,"sign":"D9F98A9DEBB083886A70414D0F010768"}
        WeXinBean data = new Gson().fromJson(s, WeXinBean.class);
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(activity, null);
        // 将该app注册到微信
        msgApi.registerApp(data.getAppid());
        //调起微信支付
        PayReq req = new PayReq();
        req.appId = data.getAppid();
        req.partnerId = data.getPartnerid();//商户号
        req.prepayId = data.getPrepayid();//预支付交易会话ID
        req.nonceStr = data.getNoncestr();//随机字符串
        req.timeStamp = data.getTimestamp() + "";//时间戳
        req.packageValue = data.getPackageX();//扩展字段,这里固定填写
        req.sign = data.getSign();//签名
//              req.extData         = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        //api.registerApp()
        msgApi.sendReq(req);

    }

    public void Client(WebView view, WebView webView, String url, Activity context) {
        web = view;
        web.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setJavaScriptEnabled(true);
        activity = context;
        token = (String) SPUtils.get(MyApp.getApp(), "token", "");
        Log.i(TAG, "Client: "+url);
        // 必须处理url，不处理
      /*  if (url.contains("app://hiddenfooter")) {
            *//*view.getSettings().setTextZoom(100);
            //  HomeActivity.relativeLayout.setVisibility(View.GONE);
            view.getSettings().setUseWideViewPort(true);
            view.getSettings().setLoadWithOverviewMode(true);
            HomeActivity.hide();*//*
        } else*/
        if (url.contains("app://checklogin")) {
            token = (String) SPUtils.get(MyApp.getApp(), "token", "");
            String substring1 = url.substring(17, url.length());
            Log.i("wxy", "shouldOverrideUrlLoading: " + token);

            if (token.length() > 4) {
                String url1 = substring1 + "?token=" + token;
                view.loadUrl(url1);

            } else {
                String substring = url.substring(17, url.length());
                SPUtils.put(context, "url", substring);
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivityForResult(intent, 11);
            }


        } else if (url.contains("app://alipay")) {
            String[] split = url.split("\\?");
            String str2 = new String(Base64.decode(split[1].getBytes(), Base64.DEFAULT));
            //唤醒支付宝支付
            startAliApp(str2);

        } else if (url.contains("app://wechatpay")) {

            String[] split = url.split("\\?");
            String str2 = new String(Base64.decode(split[1].getBytes(), Base64.DEFAULT));
            startWexin(str2);
            //唤醒微信支付
        } /*else if (url.contains("app://showfooter")) {
           *//*    HomeActivity.relativeLayout.setVisibility(View.VISIBLE);
            HomeActivity.tabMenu.setVisibility(View.VISIBLE);*//*

        } */else if (url.contains("app://shoporder/pay")) {
            Log.i("wxy", "Client:jjjjjjjjjjjjj " + url);
            String[] split1 = url.split("\\?");

            String s = url;
            //将String类型的地址转变为URI类型
            Uri uri = Uri.parse(s);
            String type = uri.getQueryParameter("type"); //id 值 10943
            web = view;
            web.getSettings().setJavaScriptEnabled(true);
            if (type.equals("all")) {
                showPayPop(activity);

                //支付商品
                sUrl = url;
//                    Log.i("WXY", "shouldOverrideUrlLoading: " + url);
                lp = context.getWindow().getAttributes();
                lp.alpha = 0.7f;//代表透明程度，范围为0 - 1.0f
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
                String[] split = sUrl.split("/");
                //设置花呗分期
                //  String[] split1 = split[5].split(".");
                //String s=split1[0];
                String[] split2 = split[5].split("\\?");
                float money = Float.parseFloat(split2[0]);
                float v;
                if (vote.length() > 1) {
                    v = Float.parseFloat(vote);
                } else {
                    v = 0.00f;
                }
                if (money > v) {
                    yuep.setTextColor(context.getResources().getColor(R.color.tintgrey));
                    yuep.setText("余额不足(");
                    paymoney.setText(v + "");
                    yue.setEnabled(false);
                    tv_right.setTextColor(context.getResources().getColor(R.color.tintgrey));
                    paymoney.setTextColor(context.getResources().getColor(R.color.tintgrey));
                    image.setImageResource(R.mipmap.pay_money);
                    image.setEnabled(false);
                    paySel.setImageResource(R.mipmap.pay_unsel);
                } else {
                    image.setEnabled(true);
                    yue.setEnabled(true);
                    yuep.setText("余额(");
                    tv_right.setTextColor(context.getResources().getColor(R.color.black));
                    paymoney.setTextColor(context.getResources().getColor(R.color.colorred));
                    paymoney.setText(v + "");
                    yuep.setTextColor(context.getResources().getColor(R.color.black));
                    image.setImageResource(R.mipmap.yue);
                    paySel.setImageResource(R.mipmap.pay_sel);
                }

                int money1 = (int) money;
                hua3 = inflate1.findViewById(R.id.pay_hua3);
                hua33 = inflate1.findViewById(R.id.pay_hua33);
                hua6 = inflate1.findViewById(R.id.pay_hua6);
                hua66 = inflate1.findViewById(R.id.pay_hua66);
                hua12 = inflate1.findViewById(R.id.pay_hua12);
                hua122 = inflate1.findViewById(R.id.pay_hua122);

                float ben3 = MoneyUtils.getBen((float) (money1 * 1.00), 3);
                float ben6 = MoneyUtils.getBen((float) (money1 * 1.00), 6);
                float ben12 = MoneyUtils.getBen((float) (money1 * 1.00), 12);
                float li3 = MoneyUtils.getli((float) (money1 * 1.00), 3);

                float li6 = MoneyUtils.getli((float) (money1 * 1.00), 6);
                float li12 = MoneyUtils.getli((float) (money1 * 1.00), 12);
                DecimalFormat mFormat = new DecimalFormat("0.00");

                String formatNum3 = mFormat.format((ben3 + li3));

                String formatNum6 = mFormat.format((ben6 + li6));
                String formatNum12 = mFormat.format((ben12 + li12));
                String interest3 = mFormat.format(li3);

                String interest6 = mFormat.format(li6);
                String interest12 = mFormat.format(li12);
                hua3.setText(formatNum3 + "");
                hua6.setText(formatNum6 + "");
                hua12.setText(formatNum12 + "");
                hua33.setText(interest3 + ")");
                hua66.setText(interest6 + ")");
                hua122.setText(interest12 + ")");
                popupWindow.setBackgroundDrawable(null);
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            } else if (type.equals("wechat")) {
                web.loadUrl("javascript:orderDone()");
                split2 = url.split("/");
                String wechat = split2[4];
                wechatPay(wechat);
            } else if (type.equals("votes")) {
                sUrl = url;
                split2 = url.split("/");
                getVotesPay(split2[4]);
            } else if (type.equals("alipay")) {
                Log.i("wxy", "Client: 你啦啦啦啦啦");
                String num = uri.getQueryParameter("hb_fq_num"); //id 值 10943
                split2 = url.split("/");
                if (num != null && !num.equals("")) {
                    int i = Integer.parseInt(num);
                    if (i == 3) {
                        getAlipay(split2[4], "3");
                    } else if (i == 6) {
                        getAlipay(split2[4], "6");
                    } else {
                        getAlipay(split2[4], "12");
                    }
                } else {
                    split2 = url.split("/");
                    getAlipay(split2[4], "0");
                }
            }


        }else if(url.contains("app://auth")){
            //跳转海外购，实名认证
                JumpUtils.gotoActivity(context, AutonymASetting.class);
                JumpUtils.gotoActivity(context, AutonymASetting.class);

        } else if (url.contains("app://login")) {
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivityForResult(intent, 11);
        } else if (url.contains("app://send/copy")) {
            String[] split = url.split("\\?");
            //  Toast.makeText(getActivity(), "复制", Toast.LENGTH_SHORT).show();
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) MyApp.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", split[1]);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();

        } else if (url.contains("app://reload")) {
            view.reload();
        } else if (url.contains("app://play/video")) {
            String[] split = url.split("\\?");
            Bundle bundle = new Bundle();
            ArrayList<String> strings = new ArrayList<>();
            strings.add(split[1]);
            bundle.putStringArrayList("list", strings);
            bundle.putString("posstion", "0");
            JumpUtils.gotoActivity(context, ViewPagerLayoutManagerActivity.class, bundle);
        } else if (url.contains("app://close")) {
            view.clearHistory();
            if (token.length() > 3) {
                view.loadUrl(mUrl + "?token=" + token);
            } else {
                view.loadUrl(mUrl + "?token=");
            }
            //
        } else if (url.contains("app://tomake/purchse")) {
            HomeActivity homeActivity = (HomeActivity) context;
            homeActivity.homeVp.setCurrentItem(1);
            homeActivity.tabMenuZhuan.setSelected(true);
        } else if (url.contains("app://message")) {
            //跳转到客服聊天页面
            String substring = url.substring(14, url.length());
            //json 经过urlsafebase64加密
            String[] split = substring.split("\\/");
            byte[] decode = Base64.decode(split[1], Base64.URL_SAFE);
            String s1 = new String(decode);
            Bundle bundle = new Bundle();
            Log.i("wxy", "shouldOverrideUrlLoading: " + s1);
            ShopInfo shopInfo = new Gson().fromJson(s1, ShopInfo.class);
            bundle.putString("extra", s1);
            bundle.putString("name", shopInfo.getShopname());
            bundle.putString("uid", split[0]);
            JumpUtils.gotoActivity(context, Chat2Activity.class, bundle);
        }
        //落地页打开店铺
        else if (url.contains("app://share/shops/index")) {
            String[] split = url.split("\\?");
            String info = split[1];
            webView.loadUrl(mUrl + "allshop?token=" + token + "&" + info);

            //分享带图片
        } else if (url.contains("app://sharegoods")) {
            String substring = url.substring(17, url.length());
            byte[] decode = Base64.decode(substring, Base64.URL_SAFE);
            String s = new String(decode);
            Log.i(TAG, "Client: "+s);
            if(s.contains("extra_type")){
                //是拼团分享
                GoodsBean goodsBean = new Gson().fromJson(s, GoodsBean.class);
                PopupWindows popupWindows = new PopupWindows();
                popupWindows.goodsshow(context, webView,goodsBean);
            }else{
                //普通商品分享
                ShareBean shareBean = new Gson().fromJson(s, ShareBean.class);
                PopupWindows popupWindows = new PopupWindows();
                popupWindows.show(context, webView, true, shareBean);
            }

        }
        //落地页
        else if (url.contains("app://share/goods/info")) {
            String[] split = url.split("\\?");
            String info = split[1];

            webView.loadUrl(mUrl + "details?token=" + token + "&" + info);
        }
        //分享
        else if (url.contains("app://share")) {

            String substring = url.substring(12, url.length());

            byte[] decode = Base64.decode(substring, Base64.URL_SAFE);
            String s = new String(decode);

            ShareBean shareBean = new Gson().fromJson(s, ShareBean.class);
            PopupWindows popupWindows = new PopupWindows();
            popupWindows.show(context, webView, false, shareBean);
//            share(shareBean);
            //弹窗 分享


        } else if (url.contains("app://integral")) {
            JumpUtils.gotoActivity(context, IntegralDetailsActivity.class);
        } else if(url.contains("app://picture/")){
            String[] split = url.split("\\?");
            byte[] decode = Base64.decode(split[1], Base64.URL_SAFE);
            String s1 = new String(decode);
            Log.i(TAG, "Client: "+s1);
            ZoomMediaLoader.getInstance().init(new ImageLoader());
            PictureBean pictureBean = new Gson().fromJson(s1, PictureBean.class);
            List<String> pictureArr = pictureBean.getPictureArr();
            ArrayList<ThumbViewInfo> mThumbViewInfoList = new ArrayList<>(); // 这个最好定义成成员变量
            ThumbViewInfo item;
            mThumbViewInfoList.clear();
            for (int i = 0;i < pictureArr.size(); i++) {
                Rect bounds = new Rect();
                //new ThumbViewInfo(图片地址);
                item=new ThumbViewInfo(pictureArr.get(i));
                item.setBounds(bounds);
                mThumbViewInfoList.add(item);
            }
            GPreviewBuilder.from(context)
                    .to(MainActivity.class)
                    .setData(mThumbViewInfoList)
                    .setCurrentIndex(pictureBean.getIndex())
                    .setSingleFling(true)
                    .setType(GPreviewBuilder.IndicatorType.Number)
                    // 小圆点
                    .start();//启动
        }else if (url.contains("app://go/home")) {
            view.setWebViewClient(new WebViewClient() {

                @Override
                public void doUpdateVisitedHistory(WebView webView, String s, boolean b) {
                    view.clearHistory();
                }
            });
            token = (String) SPUtils.get(MyApp.getApp(), "token", "");
            if (token.length() > 3) {
                view.loadUrl(mUrl + "?token=" + token);
            } else {
                view.loadUrl(mUrl + "?token=" + token);
            }

        } else if (url.contains("app://view/")) {
            String substring = url.substring(11, url.length());
            Bundle bundle = new Bundle();
            bundle.putString("url", substring);
            JumpUtils.gotoActivity(context, BannerWebActivity.class, bundle);
        } else if (url.contains("tel://")) {
            String substring = url.substring(6, url.length());
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + substring);
            intent.setData(data);
            context.startActivity(intent);
//            setPerssiom(context,substring);
        } else {
            view.loadUrl(url);
        }

    }

    @SuppressLint("MissingPermission")
    public void setPerssiom(Activity activity, String phone) {
        String[] psermission = {Manifest.permission.CALL_PHONE};

        //检查是否有权限
        if (EasyPermissions.hasPermissions(activity, psermission)) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + phone);
            intent.setData(data);
            activity.startActivity(intent);
        } else {
            //去申请
            EasyPermissions.requestPermissions(activity, "需要获取你的相机权限", 1001, psermission);
        }
    }

    public void wechatPay(String order) {
        HashMap<String, String> stringHashMap = new HashMap<>();

        stringHashMap.put("order_no", order);
        HttpUtils.instance().getWechat(stringHashMap);
    }


    public static void share(ShareBean shareBean) {
        UMImage thumb = new UMImage(activity, R.drawable.qwe);
        UMImage image = new UMImage(activity, shareBean.getImgsrc());//资源文件
        image.setThumb(thumb);
        UMWeb umWeb = new UMWeb(shareBean.getUrl());
        umWeb.setTitle(shareBean.getTitle());
        umWeb.setThumb(image);
        if (shareBean.getContent() != null && !shareBean.equals("")) {
            umWeb.setDescription(shareBean.getContent());
        } else {
            umWeb.setDescription("as");
        }

        //描述
        image.compressStyle = UMImage.CompressStyle.SCALE;
        new ShareAction(activity).withText(shareBean.getContent()).withMedia(umWeb).setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }).open();
    }

    private static PopupWindow popupWindow;
    private static View inflate1;
    private static String vote;
    private static TextView yuep;

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //popupwindow
    public void showPayPop(Activity activity) {
        popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(dip2px(activity, 400));
        inflate1 = LayoutInflater.from(activity).inflate(R.layout.pay_item, null, false);
        popupWindow.setContentView(inflate1);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);//
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                web.loadUrl("javascript:orderDone()");
                lp.alpha = 1.0f;
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                activity.getWindow().setAttributes(lp);
            }
        });
        vote = (String) SPUtils.get(activity, "votes", "");

        LinearLayout hualin3 = inflate1.findViewById(R.id.pay_hualin3);
        LinearLayout hualin6 = inflate1.findViewById(R.id.pay_hualin6);
        image = inflate1.findViewById(R.id.pay_yu);
        yuep = inflate1.findViewById(R.id.pay_yuep);
        tv_right = inflate1.findViewById(R.id.pay_right);
        LinearLayout hualin12 = inflate1.findViewById(R.id.pay_hualin12);
        paymoney = inflate1.findViewById(R.id.pay_money);
        inflate1.findViewById(R.id.pay_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*web.loadUrl("javascript:orderDone()");*/
                popupWindow.dismiss();


            }
        });
        paymoney.setText(vote);
        yue = inflate1.findViewById(R.id.pay_yue);
        LinearLayout wechat = inflate1.findViewById(R.id.pay_wechat);
        paySel = inflate1.findViewById(R.id.pay_sel);
        LinearLayout zhifu = inflate1.findViewById(R.id.pay_zhifu);
        yue.setOnClickListener(this);
        wechat.setOnClickListener(this);
        zhifu.setOnClickListener(this);
        hualin3.setOnClickListener(this);
        hualin6.setOnClickListener(this);
        hualin12.setOnClickListener(this);

        TextView payTime = inflate1.findViewById(R.id.pay_time);

        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(payTime, 1800000, 1000,null); //倒计时1分钟
        mCountDownTimerUtils.start();

    }

    public void getVotesPay(String url) {
        //如果没有支付密码，就跳转到设置密码界面
        String pay_pass = (String) SPUtils.get(activity, "pass", "");
        String autonym = (String) SPUtils.get(activity, "autonym", "");
        //判断是否认证
     //   if(autonym.equals("1")){
            if (pay_pass.length()<6) {
                Bundle bundle = new Bundle();
                bundle.putInt("isForget",1);
                //跳转设置支付密码界面
                JumpUtils.gotoActivity(activity, SetPayPass2.class,bundle);
            } else {
                String[] split1 = sUrl.split("/");
                Log.i("WXY", "onClick: " + split1);
                String s = split1[4];
                Intent intent = new Intent(activity, PasswordPay.class);
                Bundle bundle = new Bundle();
                bundle.putString("order_no", s);
                intent.putExtra("bundle", bundle);
                activity.startActivityForResult(intent, 2345);
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }
//                    JumpUtils.gotoActivity(getActivity(), PasswordPay.class,bundle);
            }
        /*}else{
            //提示去认证，
            PopupWindows popupWindows = new PopupWindows();
            popupWindows.showAutonym(activity,web);
        }*/


    }
    public void getAlipay(String url, String num) {
//        String[] split = sUrl.split("/");mE
        HashMap<String, String> Map2 = new HashMap<>();
        Map2.put("order_no", url);
        if (Integer.parseInt(num) > 2) {
            Map2.put("hb_fq_num", num);
        }
        HttpUtils.instance().getAli(Map2);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pay_yue:

                String[] split1 = sUrl.split("/");
                String s = split1[4];
                getVotesPay(s);

                break;
            case R.id.pay_zhifu:
                //    web.loadUrl("javascript:orderDone()");
                String[] split = sUrl.split("/");
                Log.i("WXY", "onClick: " + split);
                String s2 = split[4];
//                String ss = split[5];
                getAlipay(s2, "0");
                break;
            case R.id.pay_wechat:
                web.loadUrl("javascript:orderDone()");
                String[] split2 = sUrl.split("/");
                String wechat = split2[4];
                wechatPay(wechat);
                break;
            case R.id.pay_hualin3:
                //    web.loadUrl("javascript:orderDone()");
                String[] split3 = sUrl.split("/");

                String s3 = split3[4];
                getAlipay(s3, "3");

                break;
            case R.id.pay_hualin6:
                // web.loadUrl("javascript:orderDone()");
                String[] split6 = sUrl.split("/");
                String s6 = split6[4];
                getAlipay(s6, "6");
                break;
            case R.id.pay_hualin12:
                // web.loadUrl("javascript:orderDone()");
                String[] split12 = sUrl.split("/");
                String s12 = split12[4];

                getAlipay(s12, "12");
                break;
        }
    }

    public static void wetChatSuccess(String succcess) {
        WeChatBean weChatBean = new Gson().fromJson(succcess, WeChatBean.class);
        WeChatBean.DataBean data = weChatBean.getData();
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(activity, null);
        // 将该app注册到微信
        msgApi.registerApp(weChatBean.getData().getAppid());
        //调起微信支付
        PayReq req = new PayReq();
        req.appId = data.getAppid();
        req.partnerId = data.getPartnerid();//商户号
        req.prepayId = data.getPrepayid();//预支付交易会话ID
        req.nonceStr = data.getNoncestr();//随机字符串
        req.timeStamp = data.getTimestamp() + "";//时间戳
        req.packageValue = data.getPackageX();//扩展字段,这里固定填写
        req.sign = data.getSign();//签名
//              req.extData         = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        //api.registerApp()
        msgApi.sendReq(req);
    }

    @SuppressLint("HandlerLeak")
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            super.handleMessage(msg);
            if(popupWindow!=null){
                popupWindow.dismiss();
            }

            //{ when=-132ms what=1 obj={resultStatus=9000, result={"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2019061765629232","auth_app_id":"2019061765629232","charset":"UTF-8","timestamp":"2019-08-30 17:16:03","out_trade_no":"RC_B8308170803376577","total_amount":"1.00","trade_no":"2019083022001427950536975798","seller_id":"2088331613883961"},"sign":"FbFRV85bVYV1ZrXmF+7w3xNRE5V66vdsRF0B/gvFjqi6LUneX21+2fekZQC6EEMIzn4WFPOTEMyl/86Nt/AvcvnPpo7YXE8kkTKazf3kMIYRntBvY0Cb4DVFmkD6qtZTXUr7RQV7hh5F33Cpp8A3N0wCJy8nIvYLCPZoTxeDvVkgwrshXyVIC7VHBL3E5pWSeyZRu7A9JzFjCiCOfXXmIS/xGAS7QbsIjzaTyMrBixvwi7Tw/yI124/ojQnvrzR5D1n/MLZW3C6go75qRE+Cj7Aj/eRZorKlzqmErqIRe/EHZ/TBTZDPI4CNoC7K/6+XgllZ/HfyKtxrU3In6I+pgw==","sign_type":"RSA2"}, memo=, extendInfo={"doNotExit":true,"isDisplayResult":true}} target=com.ajiani.maidahui.activity.mine.RechargeActivity$1 }
            switch (msg.what) {
                case 1:
                 /*  HashMap<String,String> result = new Result((String) msg.obj);
                   Toast.makeText(DemoActivity.this, result.getResult(),
                           Toast.LENGTH_LONG).show();*/
                    Map<String, String> map = (Map<String, String>) msg.obj;
                    Set<Map.Entry<String, String>> entrySet = map.entrySet();
                    String s = map.get("resultStatus");
                    if (s.equals("9000")) {
                        web.loadUrl("javascript:orderDone()");
                        Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("6001")) {
                        //用户中途取消
                        Toast.makeText(activity, "操作取消", Toast.LENGTH_SHORT).show();
                        web.loadUrl("javascript:orderDone()");
                    } else if (s.equals("4000")) {
                        web.loadUrl("javascript:orderDone()");
                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("6002")) {
                        web.loadUrl("javascript:orderDone()");
                        Toast.makeText(activity, "网络连接错误", Toast.LENGTH_SHORT).show();
                    }
                    Log.i("WXY", "handleMessage: " + s);
                    break;

            }
        }
    };

    private static void startAliApp(String order) {
        //掉漆支付宝
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                // 第二个参数  用户在商户app内部点击付款，是否需要一个 loading 做为在钱包唤起之前的过渡，这个值设置为 true，将会在调用 pay 接口的时候直接唤起一个 loading，直到唤起H5支付页面或者唤起外部的钱包付款页面 loading 才消失。
                // （建议将该值设置为 true，优化点击付款到支付唤起支付页面的过渡过程。）
                Map<String, String> result = alipay.payV2(order, true);
                //handler 回调支付结果
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public static void AliPay(String success) {
        PayParameBean payParameBean = new Gson().fromJson(success, PayParameBean.class);
        String orderString = payParameBean.getData().getOrderString();
        //掉漆支付宝
        startAliApp(orderString);

    }

    public WebView init(Activity context,WebView mHomeWeb) {

        mHomeWeb.getSettings().setUseWideViewPort(true);
        mHomeWeb.getSettings().setLoadWithOverviewMode(true);
        mHomeWeb.getSettings().setJavaScriptEnabled(true);
        mHomeWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        mHomeWeb.getSettings().setTextZoom(100);
        mHomeWeb.getSettings().setJavaScriptEnabled(true);
        mHomeWeb.getSettings().setDomStorageEnabled(true);
        mHomeWeb.getSettings().setTextZoom(100);
        mHomeWeb.setVerticalScrollBarEnabled(false);
        mHomeWeb.setHorizontalScrollBarEnabled(false);
        String userAgentString = mHomeWeb.getSettings().getUserAgentString();
        userAgentString = userAgentString + " com.ajiani.maidahui ";
        mHomeWeb.getSettings().setUserAgentString(userAgentString);
        setWebViewInitialScale(context,mHomeWeb);
        return mHomeWeb;
    }

    private void setWebViewInitialScale(Activity activity,WebView mHomeWeb) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        if (width > 650) {
            mHomeWeb.setInitialScale(320);
        } else if (width > 520) {
            mHomeWeb.setInitialScale(300);
        } else if (width > 450) {
           mHomeWeb.setInitialScale(280);
        } else if (width > 300) {
            mHomeWeb.setInitialScale(260);
        } else {
            mHomeWeb.setInitialScale(240);
        }
    }
}
