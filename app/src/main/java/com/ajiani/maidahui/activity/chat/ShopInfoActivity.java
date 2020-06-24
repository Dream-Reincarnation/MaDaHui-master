package com.ajiani.maidahui.activity.chat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.web.WebUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.mine.PasswordPay;
import com.ajiani.maidahui.activity.mine.SetPayPass;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.mine.PayParameBean;
import com.ajiani.maidahui.bean.mine.VoteBean;
import com.ajiani.maidahui.bean.mine.WeChatBean;
import com.ajiani.maidahui.mInterface.Payin;
import com.ajiani.maidahui.presenters.mine.PayPresenter;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class ShopInfoActivity extends BaseActivity<Payin.payView, PayPresenter> implements View.OnClickListener, Payin.payView {
    @BindView(R.id.shopinfo_web)
    WebView shopinfoWeb;
//    String URL = MyApp.BaseUrl + "#/";
    String URL = MyApp.BaseUrl+"#/ent";
    private TextView hua3;
    private TextView hua33;
    private TextView hua6;
    private TextView hua66;
    private TextView hua12;
    private TextView hua122;
    private String vote;
    private TextView yuep;
    private WindowManager.LayoutParams lp;
    private View inflate1;
    String sUrl;
    private String token;
    private String TAG="wxy";


    @Override
    public void error(String error) {

    }
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    @Override
    protected void initData() {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(dip2px(this, 400));
        inflate1 = LayoutInflater.from(this).inflate(R.layout.pay_item, null, false);
        popupWindow.setContentView(inflate1);
        popupWindow.setOutsideTouchable(true);//
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
            }
        });
        vote = (String) SPUtils.get(this, "votes", "");

        LinearLayout hualin3 = inflate1.findViewById(R.id.pay_hualin3);
        LinearLayout hualin6 = inflate1.findViewById(R.id.pay_hualin6);
        yuep = inflate1.findViewById(R.id.pay_yuep);
        LinearLayout hualin12 = inflate1.findViewById(R.id.pay_hualin12);
        TextView money = inflate1.findViewById(R.id.pay_money);

        money.setText(vote);
        LinearLayout yue = inflate1.findViewById(R.id.pay_yue);
        LinearLayout wechat = inflate1.findViewById(R.id.pay_wechat);
        LinearLayout zhifu = inflate1.findViewById(R.id.pay_zhifu);
        yue.setOnClickListener(this);
        wechat.setOnClickListener(this);
        zhifu.setOnClickListener(this);
        hualin3.setOnClickListener(this);
        hualin6.setOnClickListener(this);
        hualin12.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        shopinfoWeb.getSettings().setJavaScriptEnabled(true);
        shopinfoWeb.getSettings().setJavaScriptEnabled(true);
        shopinfoWeb.getSettings().setDomStorageEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String json = bundle.getString("json");
        Log.i(TAG, "initView: "+json);
        token = (String) SPUtils.get(this, "token", "");
        if(token.length()>4){
            shopinfoWeb.loadUrl(URL+"?token="+ token +"&commodity_json="+json);
        }else{
            shopinfoWeb.loadUrl(URL+"?token=&commodity_json="+json);
        }

        //支付
        shopinfoWeb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.i("WXY", "shouldOverrideUrlLoading: "+url);
                // 必须处理url，不处理
                if (url.contains("app://go/back")) {
                        finish();
                } else {
                    WebUtils.instance().Client(shopinfoWeb,view,url,ShopInfoActivity.this);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // writeData();
            }
        });


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_shopinfo;
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pay_yue:
                //如果没有支付密码，就跳转到设置密码界面
                String pay_pass = (String) SPUtils.get(this, "pay_pass", "");
                if (pay_pass.equals("0")) {
                    //跳转设置支付密码界面
                    JumpUtils.gotoActivity(this, SetPayPass.class);
                } else {
                    String[] split1 = sUrl.split("/");
                    Log.i("WXY", "onClick: " + split1);
                    String s = split1[4];
                    String s1 = split1[5];

                      HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("order_no", s);
                        mPresenter.getVote(hashMap);


                    Intent intent = new Intent(this, PasswordPay.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("order_no", s);
                    intent.putExtra("bundle", bundle);
                    startActivityForResult(intent, 2345);
                    popupWindow.dismiss();
//                    JumpUtils.gotoActivity(getActivity(), PasswordPay.class,bundle);
                }

                break;
            case R.id.pay_zhifu:

                String[] split = sUrl.split("/");
                Log.i("WXY", "onClick: " + split);
                String s2 = split[4];
                String ss = split[5];
                HashMap<String, String> Map2 = new HashMap<>();
                Map2.put("order_no", s2);
                mPresenter.getPay(Map2);
                break;
            case R.id.pay_wechat:
                HashMap<String, String> stringHashMap = new HashMap<>();
                String[] split2 = sUrl.split("/");
                Log.i("WXY", "onClick: " + split2);
                String wechat = split2[4];
                stringHashMap.put("order_no", wechat);
                mPresenter.getWeChat(stringHashMap);
                break;
            case R.id.pay_hualin3:
                String[] split3 = sUrl.split("/");
                HashMap<String, String> hashMap1 = new HashMap<>();
                String s3 = split3[4];
                String s4 = split3[5];
                hashMap1.put("order_no", s3);
                hashMap1.put("hb_fq_num", "3");
                mPresenter.getPay(hashMap1);
                break;
            case R.id.pay_hualin6:
                String[] split6 = sUrl.split("/");
                String s6 = split6[4];
                String s7 = split6[5];
                HashMap<String, String> hashMap2 = new HashMap<>();
                //   hashMap2.put("order_no", order_no);
                hashMap2.put("hb_fq_num", "6");
                mPresenter.getPay(hashMap2);
                break;
            case R.id.pay_hualin12:
                String[] split12 = sUrl.split("/");
                String s12 = split12[4];
                String s13 = split12[5];
                HashMap<String, String> hashMap3 = new HashMap<>();
                hashMap3.put("order_no", s12);
                hashMap3.put("hb_fq_num", "12");
                mPresenter.getPay(hashMap3);
                break;
        }
    }

    @Override
    public void getSuccess(String success) {

    }

    @Override
    public void createSuccess(String create) {

    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            popupWindow.dismiss();
            //{ when=-132ms what=1 obj={resultStatus=9000, result={"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2019061765629232","auth_app_id":"2019061765629232","charset":"UTF-8","timestamp":"2019-08-30 17:16:03","out_trade_no":"RC_B8308170803376577","total_amount":"1.00","trade_no":"2019083022001427950536975798","seller_id":"2088331613883961"},"sign":"FbFRV85bVYV1ZrXmF+7w3xNRE5V66vdsRF0B/gvFjqi6LUneX21+2fekZQC6EEMIzn4WFPOTEMyl/86Nt/AvcvnPpo7YXE8kkTKazf3kMIYRntBvY0Cb4DVFmkD6qtZTXUr7RQV7hh5F33Cpp8A3N0wCJy8nIvYLCPZoTxeDvVkgwrshXyVIC7VHBL3E5pWSeyZRu7A9JzFjCiCOfXXmIS/xGAS7QbsIjzaTyMrBixvwi7Tw/yI124/ojQnvrzR5D1n/MLZW3C6go75qRE+Cj7Aj/eRZorKlzqmErqIRe/EHZ/TBTZDPI4CNoC7K/6+XgllZ/HfyKtxrU3In6I+pgw==","sign_type":"RSA2"}, memo=, extendInfo={"doNotExit":true,"isDisplayResult":true}} target=com.ajiani.maidahui.activity.mine.RechargeActivity$1 }
            switch (msg.what) {
                case 1:
                  /* HashMap<String,String> result = new Result((String) msg.obj);
                   Toast.makeText(DemoActivity.this, result.getResult(),
                           Toast.LENGTH_LONG).show();*/
                    Map<String, String> map = (Map<String, String>) msg.obj;
                    Set<Map.Entry<String, String>> entrySet = map.entrySet();
                    String s = map.get("resultStatus");
                    if (s.equals("9000")) {
                        Toast.makeText(ShopInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("6001")) {
                        //用户中途取消
                        Toast.makeText(ShopInfoActivity.this, "操作取消", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("4000")) {
                        Toast.makeText(ShopInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("6002")) {
                        Toast.makeText(ShopInfoActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    }
                    Log.i("WXY", "handleMessage: " + s);
                    break;

            }
        }
    };

    @Override
    public void parameterSuccess(String parameter) {
        PayParameBean payParameBean = new Gson().fromJson(parameter, PayParameBean.class);
        String orderString = payParameBean.getData().getOrderString();
        //掉漆支付宝
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(ShopInfoActivity.this);
                // 第二个参数  用户在商户app内部点击付款，是否需要一个 loading 做为在钱包唤起之前的过渡，这个值设置为 true，将会在调用 pay 接口的时候直接唤起一个 loading，直到唤起H5支付页面或者唤起外部的钱包付款页面 loading 才消失。
                // （建议将该值设置为 true，优化点击付款到支付唤起支付页面的过渡过程。）
                Map<String, String> result = alipay.payV2(orderString, true);
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

    @Override
    public void voteSuccess(String success) {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        VoteBean voteBean = new Gson().fromJson(success, VoteBean.class);
        String after_votes = voteBean.getData().getAfter_votes();
        SPUtils.put(this, "votes", after_votes);
    }

    @Override
    public void weChatSuccess(String success) {
        WeChatBean weChatBean = new Gson().fromJson(success, WeChatBean.class);
        WeChatBean.DataBean data = weChatBean.getData();
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
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

    @Override
    protected PayPresenter preparePresenter() {
        return new PayPresenter();
    }
}
