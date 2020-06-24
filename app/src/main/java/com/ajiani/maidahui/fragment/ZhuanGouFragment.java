package com.ajiani.maidahui.fragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.DownloadHandlerThread;
import com.ajiani.maidahui.JsInteration;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.Utils.web.WebUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.adapter.zhuan.FriendsCircleAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.chat.WeXinBean;
import com.ajiani.maidahui.bean.share.ShareBean;
import com.ajiani.maidahui.bean.zhuan.FriendsCircleSel;
import com.ajiani.maidahui.bean.zhuan.ShareTxtBean;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;


import static android.app.Activity.RESULT_OK;

public class ZhuanGouFragment extends BaseFragment {


    @BindView(R.id.zhuan_web)
    public WebView zhuanWeb;
    @BindView(R.id.ed)
    EditText ed;
    //https://keeper.maidahui.com/home/login?token=[UserToken]
//    String url = MyApp.BaseUrl+"#/makepurchse";
    public String url = "https://m.maidahui.com/agent";
    @BindView(R.id.zhuan_back)
    ImageView zhuanBack;
    @BindView(R.id.zhuan_close)
    TextView zhuanClose;
    @BindView(R.id.zhuan_title)
    TextView zhuanTitle;
    private String token;
    String text = "啦啦啦啦啦啦啦啦[微笑]";
    private String EMOJI = "\\[[\u4e00-\u9fa5\\w]+\\]";// 表情
    private int a;
    //web调用相册图片
    public ValueCallback<Uri> uploadMessage;
    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;

 @SuppressLint("HandlerLeak")
 Handler  mUiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 5:
                    //保存完成
                    //跳转到微信
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    getActivity().startActivity(intent);
                    break;
            }

        }
    };
    private ShareTxtBean shareTxtBean;
    private String path;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            HomeActivity.tabMenu.setVisibility(View.GONE);
            StatusBarUtil2.setStatusBarMode(HomeActivity.context, true, R.color.white);
            this.token = (String) SPUtils.get(MyApp.getApp(), "token", "");
            if (this.token.length() > 3) {
                zhuanWeb.loadUrl(url + "?token=" + this.token);
            } else {
                zhuanWeb.loadUrl(url + "?token=");
            }
        }
    }


   //异步线程下载图片
    private DownloadHandlerThread mDownloadHandlerThread;
    @Override
    protected void initData() {
        ed.setVisibility(View.VISIBLE);
        String token = (String) SPUtils.get(getActivity(), "token", "");
        ed.setText(token);
        zhuanWeb.getSettings().setJavaScriptEnabled(true);
        zhuanWeb.getSettings().setTextZoom(100);
        zhuanWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        zhuanWeb.getSettings().setJavaScriptEnabled(true);
        zhuanWeb.getSettings().setDomStorageEnabled(true);
        zhuanWeb.addJavascriptInterface(new JsInteration(), "android");
        zhuanWeb.setHorizontalScrollBarEnabled(false);//水平不显示
        zhuanWeb.setVerticalScrollBarEnabled(false); //垂直不显示
        initweb();

        //设置useRagent
        String userAgentString = zhuanWeb.getSettings().getUserAgentString();
        userAgentString = userAgentString + " com.ajiani.maidahui ";

        zhuanWeb.getSettings().setUserAgentString(userAgentString);
        this.token = (String) SPUtils.get(MyApp.getApp(), "token", "");
        if (this.token.length() > 3) {
            zhuanWeb.loadUrl(url + "?token=" + this.token);
        } else {
            zhuanWeb.loadUrl(url + "?token=");
        }

        zhuanWeb.setWebViewClient(new WebViewClient() {


            private FriendsCircleAdapter friendsCircleAdapter;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //拿到webview的title
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                   zhuanTitle.setText(title);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//app://wechatpay?[base64的JSON串]
//app://alipay?[base64的JSON串]
                Log.i("wxy", "shouldOverrideUrlLoading: "+url);
               if(url.contains("app://sharetxt")){
                    //一键发圈
                   String substring = url.substring(15, url.length());
                   String shareTxt = new String(Base64.decode(substring.getBytes(), Base64.DEFAULT));
                   shareTxtBean = new Gson().fromJson(shareTxt, ShareTxtBean.class);
                   View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.friends_circle, null);
                   PopupWindow popupWindow = new PopupWindow(getActivity());
                   popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                   popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

                   inflate.findViewById(R.id.friendcircles_dis).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           popupWindow.dismiss();
                       }
                   });
                   RecyclerView recyclerView = inflate.findViewById(R.id.friendscircle_rel);
                   inflate.findViewById(R.id.friendscirclr_wechat).setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           //把内容复制发哦粘贴板
                           ClipboardManager cm = (ClipboardManager) MyApp.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
                           // 创建普通字符型ClipData
                           ClipData mClipData = ClipData.newPlainText("Label", shareTxtBean.getContent());
                           // 将ClipData内容放到系统剪贴板里。
                           cm.setPrimaryClip(mClipData);
                           //
                           ArrayList<String> strings = new ArrayList<>();
                           for (int i = 0; i <friendsCircleAdapter.mList.size() ; i++) {
                               boolean sel = friendsCircleAdapter.mList.get(i).isSel();
                               if(sel){
                                   String imgUrl = friendsCircleAdapter.mList.get(i).getImgUrl();
                                   strings.add(imgUrl);
                               }
                           }

                           mDownloadHandlerThread = new DownloadHandlerThread("mHandlerThread",getActivity());
                           mDownloadHandlerThread.setUiHandler(mUiHandler, strings);
                           //必须先开启线程
                           mDownloadHandlerThread.start();

                       }
                   });
                   popupWindow.setContentView(inflate);
                   ArrayList<FriendsCircleSel> friendsCircleSels = new ArrayList<>();
                   for (int i = 0; i < shareTxtBean.getPictures().size(); i++) {
                       FriendsCircleSel friendsCircleSel = new FriendsCircleSel();
                       friendsCircleSel.setImgUrl(shareTxtBean.getPictures().get(i));
                       friendsCircleSel.setSel(true);
                       friendsCircleSels.add(friendsCircleSel);
                   }
                   recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                   friendsCircleAdapter = new FriendsCircleAdapter(friendsCircleSels);
                   recyclerView.setAdapter(friendsCircleAdapter);
                   popupWindow.showAtLocation(zhuanWeb, Gravity.BOTTOM,0,0);

               }else{
                   WebUtils.instance().Client(zhuanWeb,view,url,getActivity());
                }

                return true;
            }

        });

    }
    public ValueCallback<Uri> mUploadMessage;
    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
    }

    public ValueCallback<Uri[]> mUploadMessageForAndroid5;

    private void onenFileChooseImpleForAndroid(ValueCallback<Uri[]> filePathCallback) {
        mUploadMessageForAndroid5 = filePathCallback;

        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");
//         BitmapFactory.decodeFile()
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);


        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

    private void initweb() {
        zhuanWeb.setWebChromeClient(new WebChromeClient() {

            //扩展浏览器上传文件
            //3.0++版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                openFileChooserImpl(uploadMsg);
            }

            //3.0--版本
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooserImpl(uploadMsg);
            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooserImpl(uploadMsg);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                onenFileChooseImpleForAndroid(filePathCallback);
                return true;
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        String path;
        if (requestCode == FILECHOOSER_RESULTCODE) {
            Log.i("wxy", "onActivityResult: +aaa");
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
        } else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
         if (null == mUploadMessageForAndroid5)
                return;
            Uri result = (intent == null || resultCode != RESULT_OK) ? null : intent.getData();
            String path1 = FileUtils.getPath(getActivity(), result);
            String basePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String compressPath = basePath + File.separator + "photos" + File.separator
                    + System.currentTimeMillis() + ".jpg";
            //options.height = xxx;//some compression configuration.
         //   File newFile = CompressHelper.

            if (result != null) {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
            } else {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
            }
            mUploadMessageForAndroid5 = null;

        }
        clearUploadMessage();

    }

    private void clearUploadMessage(){
        if ( mUploadMessageForAndroid5!= null) {
            mUploadMessageForAndroid5.onReceiveValue(null);
            mUploadMessageForAndroid5 = null;
        }
        if (uploadMessage != null) {
            uploadMessage.onReceiveValue(null);
            uploadMessage = null;
        }
    }
    private void shareweixin(String path){
        Uri uriToImage = Uri.fromFile(new File("storage/emulated/0/Android/data/com.ajiani.maidahui/files/Pictures/1573542508725.jpeg"));
        Intent shareIntent = new Intent();
        //发送图片到朋友圈
        //ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        //发送图片给好友。
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        shareIntent.setComponent(comp);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, "分享图片"));
    }

    //分享
    public void share(ShareBean shareBean){
        UMImage thumb =  new UMImage(getActivity(), R.drawable.qwe);
        UMImage image = new UMImage(getActivity(), shareBean.getImgsrc());//资源文件
        image.setThumb(thumb);
        UMWeb umWeb = new UMWeb(shareBean.getUrl());
        umWeb.setTitle(shareBean.getTitle());
        umWeb.setThumb(image);
        if(shareBean.getContent()!=null&&!shareBean.equals("")){
            umWeb.setDescription(shareBean.getContent());
        }else{
            umWeb.setDescription("as");
        }

        //描述
        image.compressStyle = UMImage.CompressStyle.SCALE;
        new ShareAction(getActivity()).withText(shareBean.getContent()).withMedia(umWeb).setDisplayList(SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN)
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
    private void startWexin(String s) {
        //Log.i("wxy", "startWexin: "+s);
        //{"appid":"wxe52595c56f1bfd66","partnerid":"1516783291","prepayid":"wx281437227809984058f1cdb81737697800","package":"Sign=WXPay","noncestr":"j9p63kythnkoa6dxeuyuj4pp7kwnpe7n","timestamp":1572244642,"sign":"D9F98A9DEBB083886A70414D0F010768"}
        WeXinBean data = new Gson().fromJson(s, WeXinBean.class);
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(getActivity(), null);
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
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {

            super.handleMessage(msg);

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

    private void startAliApp(String order) {
        //掉漆支付宝
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
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

    @Override
    protected int createLayout() {
        return R.layout.fragment_zhuan;
    }


    @Override
    protected BasePresenterImp preparePresenter() {
        return null;
    }

    @Override
    public void error(String error) {

    }

    @OnClick({R.id.zhuan_back, R.id.zhuan_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuan_back:
               HomeActivity.tabMenu.setVisibility(View.VISIBLE);
//               HomeActivity.homeVp.setCurrentItem(0);
                HomeActivity homeActivity = (HomeActivity) getActivity();
            /*    homeActivity.homeVp.setCurrentItem(0);
                homeActivity.setSelected();
                homeActivity.tabMenuHome.setSelected(true);*/
            homeActivity.onKeyDown(KeyEvent.KEYCODE_BACK,new KeyEvent(12,12));




                break;
            case R.id.zhuan_close:
                HomeActivity.tabMenu.setVisibility(View.VISIBLE);
                HomeActivity homeActivity1 = (HomeActivity) getActivity();
                homeActivity1.homeVp.setCurrentItem(0);
                homeActivity1.setSelected();
                homeActivity1.tabMenuHome.setSelected(true);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(zhuanWeb!=null){
            zhuanWeb.removeJavascriptInterface("android");
            zhuanWeb.clearHistory();
            zhuanWeb.removeAllViews();
        }

      
            mDownloadHandlerThread.quit();
          if(shareTxtBean!=null){
              for (int i = 0; i < shareTxtBean.getPictures().size(); i++) {
                  mUiHandler.removeMessages(i,null);
              }
          }
    }
}
