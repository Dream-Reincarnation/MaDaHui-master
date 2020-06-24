package com.ajiani.maidahui.fragment.mine;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;

import android.content.Context;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.Utils.web.WebUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.mine.PasswordPay;
import com.ajiani.maidahui.activity.mine.SetPayPass;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.mine.PayParameBean;
import com.ajiani.maidahui.bean.mine.VoteBean;
import com.ajiani.maidahui.bean.mine.WeChatBean;
import com.ajiani.maidahui.bean.share.ShareBean;
import com.ajiani.maidahui.mInterface.Payin;
import com.ajiani.maidahui.presenters.mine.PayPresenter;
import com.alipay.sdk.app.PayTask;
import com.example.szing.zxing.android.CaptureActivity;
import com.google.gson.Gson;
import com.gyf.immersionbar.components.ImmersionOwner;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;


import java.io.ByteArrayInputStream;
import java.io.File;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class HomeFragment extends BaseFragment<Payin.payView, PayPresenter> implements View.OnClickListener, Payin.payView, ImmersionOwner {

    private Button mBtShare;
    public static WebView mHomeWeb;
    private String token;
    String mUrl = MyApp.BaseUrl + "#/";
    String sUrl;
    public WebView web;
    private TextView hua3;
    private TextView hua33;
    private TextView hua6;
    private TextView hua66;
    private TextView hua12;
    private TextView hua122;
    private PopupWindow popupWindow;
    private WindowManager.LayoutParams lp;

    String isHomeUrl;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;
    private String vote;
    private TextView yuep;
    private HashMap<String, String> hashMap;
    private View inflate1;
    //web调用相册图片
    private ValueCallback<Uri> mUploadMessage;
    //这对5.0以上版本
    private ValueCallback<Uri[]> filePathCallback;
    private String TAG = "wxy";
    private int height;

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        HomeActivity.context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            String url1 = bundle.getString("url");
            if (url1 != null) {
                mHomeWeb.loadUrl(MyApp.BaseUrl + "#/details?" + url1 + "&outfrom=1");
            }
        }

        inflate.findViewById(R.id.asd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.tabMenu.setVisibility(View.GONE);
            }
        });
        popupWindow = new PopupWindow(getActivity());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(dip2px(getActivity(), 400));
        inflate1 = LayoutInflater.from(getActivity()).inflate(R.layout.pay_item, null, false);
        popupWindow.setContentView(inflate1);
        popupWindow.setOutsideTouchable(true);//
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        vote = (String) SPUtils.get(getActivity(), "votes", "");

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


    private final int CAMERA_ID = 0x0001;
    private final int ALBUM_ID = 0x0002;
    private final int CANCEL_ID = 0x0003;
    private static final int REQUEST_CODE_PICK_IMAGE = 0x1111;
    private static final int REQUEST_CODE_TAKE_CAMERA = 0x2222;
    private File tempFile;
    private Uri photoUri;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    class JsIntercw {

        @JavascriptInterface
        public void showTab() {
            HomeActivity.tabMenu.setVisibility(View.VISIBLE);
        }

        @JavascriptInterface
        public void hideTab() {

            HomeActivity.tabMenu.setVisibility(View.GONE);
        }
        @JavascriptInterface
        public void setStatusBar(String color) {
           /* String substring = color.substring(1, color.length());
            StatusBarUtil2.setStatusBarMode(getActivity(),false,);*/
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        height = 0;
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = getActivity().getResources().getDimensionPixelSize(resourceId);
        }

        token = (String) SPUtils.get(MyApp.getApp(), "token", "");
        String url = (String) SPUtils.get(getActivity(), "url", "");
        mHomeWeb = inflate.findViewById(R.id.home_web);
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
        mHomeWeb.addJavascriptInterface(new JsIntercw(), "android");

        Display display = getActivity().getWindowManager().getDefaultDisplay();


        Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int x = point.x;
        int y = point.y;
        int i = dip2px(getActivity(), 88);
        y -= i;

//        Log.i("wxy", "initView: "+y+"===="+i);

        String userAgentString = mHomeWeb.getSettings().getUserAgentString();
        userAgentString = userAgentString + " com.ajiani.maidahui ";

        SPUtils.put(getActivity(), "agent", userAgentString);
        mHomeWeb.getSettings().setUserAgentString(userAgentString);
        //清空WebView的localStorage
//        WebStorage.getInstance().deleteAllData();

        File file = new File("/storage/sdcard0/vvv.mp4.mp4");
        mHomeWeb.setWebChromeClient(new WebChromeClient() {
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;
                Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
                albumIntent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(albumIntent, FILECHOOSER_RESULTCODE);
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }

        });
        setWebViewInitialScale();
        if (token.length() > 3) {
            isHomeUrl = mUrl + "?token=" + token;
            mHomeWeb.loadUrl(mUrl + "?token=" + token);
        } else {
            isHomeUrl = mUrl + "?token=" + token;
            mHomeWeb.loadUrl(mUrl + "?token=" + token);
        }


        mHomeWeb.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if(url.endsWith("safe-area-inset.css")){
                    Log.i(TAG, "shouldInterceptRequest: 拦截到了"+px2dip(getActivity(),height));
                    String result ="body .steep_padding{padding-top:"+(px2dip(getActivity(),height)+10)+"px !important}";
                    WebResourceResponse response = new WebResourceResponse("text/css",
                            "utf-8",
                            new ByteArrayInputStream(result.getBytes()));
                    return response;
                }
                return super.shouldInterceptRequest(view, url);
            }
           /*  @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }*/

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {






                if (url.contains("app://scan")) {
                    web = view;
                    //跳转到扫一扫
                    Intent intent = new Intent(getActivity(), CaptureActivity.class);
                    getActivity().startActivityForResult(intent, 1111);
                }else if(url.contains("safe-area-inset.css")){
                    try{
                      mHomeWeb.loadData(":root{    --safe-area-inset-top: 44px;    --safe-area-inset-bottom: 34px ; --safe-area-inset-left: 0px;--safe-area-inset-right: 0px;}","text/css","utf-8");
                      return false;
                       // new WebResourceResponse("text/css","utf-8",getStringStream(":root{    --safe-area-inset-top: 44px;    --safe-area-inset-bottom: 34px ; --safe-area-inset-left: 0px;--safe-area-inset-right: 0px;}"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }



                } else {
                    WebUtils.instance().Client(mHomeWeb, view, url, getActivity());
                    return true;
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
             //   Log.i(TAG, "onPageFinished: " + isHomeUrl);
                if (url.equals(isHomeUrl)) {

                    HomeActivity.showFooter();
                } else {

                    HomeActivity.hide();
                }

            }
        });

        //设置url
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    @Override
    public void onResume() {

        super.onResume();
        if (getActivity() != null) {
            StatusBarUtil2.setStatusBarMode(getActivity(), false, R.color.home_red);
        }

        Boolean isfirstApp = (Boolean) SPUtils.get(getActivity(), "isfirstApp", false);
        if (isfirstApp) {
            Intent intent = getActivity().getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");

            String url1 = bundle.getString("url");
            Log.i("wxy", "paramSuccess: asdasdadds" + url1);
            if (url1 != null) {
                mHomeWeb.loadUrl(MyApp.BaseUrl + "#/details?" + url1 + "&outfrom=1");
            }
        }

    }

    //分享
    public void share(ShareBean shareBean) {
        UMImage thumb = new UMImage(getActivity(), R.drawable.qwe);
        UMImage image = new UMImage(getActivity(), shareBean.getImgsrc());//资源文件
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
        new ShareAction(getActivity()).withText(shareBean.getContent()).withMedia(umWeb).setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1234 && resultCode == 1314) {
            String url = (String) SPUtils.get(getActivity(), "url", "");
            Log.i("wxy", "onActivityResult:奥术大师大多 ");
            web.loadUrl(url + "?token=" + token, hashMap);
        } else if (requestCode == 2345 && resultCode == 123) {
            Toast.makeText(mActivity, "sdfs", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        } else {
//            Log.i("WXY", "initViasdsdsew: + url");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (requestCode == FILECHOOSER_RESULTCODE) {
                    if (uploadMessage == null)
                        return;
                    uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                    uploadMessage = null;
                }
            } else if (requestCode == FILECHOOSER_RESULTCODE) {
                if (null == mUploadMessage)
                    return;
                Uri result = intent == null || resultCode != HomeActivity.RESULT_OK ? null : intent.getData();
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            } else {
                Toast.makeText(getActivity(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
            }

        }


    }

    @Override
    protected PayPresenter preparePresenter() {
        return new PayPresenter();
    }



   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }*/

    @Override
    protected int createLayout() {
        return R.layout.fragment_home;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pay_yue:
                //如果没有支付密码，就跳转到设置密码界面
                String pay_pass = (String) SPUtils.get(getActivity(), "pay_pass", "");
                if (pay_pass.equals("0")) {
                    //跳转设置支付密码界面
                    JumpUtils.gotoActivity(getActivity(), SetPayPass.class);
                } else {
                    String[] split1 = sUrl.split("/");
                    Log.i("WXY", "onClick: " + split1);
                    String s = split1[4];
                    String s1 = split1[5];

                 /*     HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("order_no", s);
                        mPresenter.getVote(hashMap);
                      */

                    Intent intent = new Intent(getActivity(), PasswordPay.class);
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

    private void setWebViewInitialScale() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        if (width > 650) {
            this.mHomeWeb.setInitialScale(320);
        } else if (width > 520) {
            this.mHomeWeb.setInitialScale(300);
        } else if (width > 450) {
            this.mHomeWeb.setInitialScale(280);
        } else if (width > 300) {
            this.mHomeWeb.setInitialScale(260);
        } else {
            this.mHomeWeb.setInitialScale(240);
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
                 /*  HashMap<String,String> result = new Result((String) msg.obj);
                   Toast.makeText(DemoActivity.this, result.getResult(),
                           Toast.LENGTH_LONG).show();*/
                    Map<String, String> map = (Map<String, String>) msg.obj;
                    Set<Map.Entry<String, String>> entrySet = map.entrySet();
                    String s = map.get("resultStatus");
                    if (s.equals("9000")) {
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("6001")) {
                        //用户中途取消
                        Toast.makeText(getActivity(), "操作取消", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("4000")) {
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    } else if (s.equals("6002")) {
                        Toast.makeText(getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
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
                PayTask alipay = new PayTask(getActivity());
                // 第二个参数  用户在商户app内部点击付款，是否需要一个 loading 做为在钱包唤起之前的过渡，这个值设置为 true，将会在调用 pay 接口的时候直接唤起一个 loading，直到唤起H5支付页面或者唤起外部的钱包付款页面 loading 才消失。
                // （建议将该值设置为 true，优化点击付款到支付唤起支付页面的过渡过程。）
                Log.i("WXY", "run: " + orderString);
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
        Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        VoteBean voteBean = new Gson().fromJson(success, VoteBean.class);
        String after_votes = voteBean.getData().getAfter_votes();
        SPUtils.put(getActivity(), "votes", after_votes);
    }

    @Override
    public void weChatSuccess(String success) {
        WeChatBean weChatBean = new Gson().fromJson(success, WeChatBean.class);
        WeChatBean.DataBean data = weChatBean.getData();
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(getActivity(), null);
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
    public void error(String error) {

    }

    @Override
    public void onLazyBeforeView() {

    }

    @Override
    public void onLazyAfterView() {

    }

    @Override
    public void onVisible() {

    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void initImmersionBar() {

    }

    @Override
    public boolean immersionBarEnabled() {
        return false;
    }
}
