package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.sockets.ShopInfo;
import com.google.gson.Gson;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebManagerActivity extends SimpleActivity {
    @BindView(R.id.webman_lin)
    LinearLayout webmanLin;
    @BindView(R.id.webman_back)
    ImageView webmanBack;
    @BindView(R.id.webman_title)
    TextView webmanTitle;

    private WebView webView;
    private String TAG = "WXY";
    private String title;

    @Override
    protected void initData() {
        StatusBarUtil2.setStatusBarMode(this,true,R.color.white);
        webView.setWebChromeClient(new WebChromeClient() {
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
        Uri data = getIntent().getData();
        if (data != null) {
            String url = data.getQueryParameter("url");
            Log.i("wxy", "onResusssssssssme: asdsadadds" + url);
            if (url != null && !url.equals("")) {
                byte[] decode = Base64.decode(url, Base64.URL_SAFE);
                String s = new String(decode);
                String[] split = s.split("\\?");
                Log.i("wxy", "onCffffffreate: " + s);
                if (s.contains("app://share/goods/info")) {
                    str = split[1];
                }
                sel("common",0);
            }
        } else {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("bundle");
            String type = bundle.getString("type");
            title = bundle.getString("title");
            int shopId = bundle.getInt("shopId",0);
            webmanTitle.setText(title);
            sel(type,shopId);
        }
    }

    private void sel(String type,int id) {
        String token = (String) SPUtils.get(this, "token", "");
        switch (type) {
            //地址管理
            case "place":
                webView.loadUrl(MyApp.BaseUrl + "#/place?token=" + token + "&is_user=1");
                break;
            //抽奖
            case "draw":
                webView.loadUrl(MyApp.BaseUrl + "#/mydraw?token=" + token + "&is_user=1");
                break;
            //收藏
            case "collect":
                webView.loadUrl(MyApp.BaseUrl + "#/collect?token=" + token + "&is_user=1");
                break;
            case "look":
                webView.loadUrl(MyApp.BaseUrl + "#/footprint?token=" + token + "&is_user=1");
                break;
                //商品详情
            case "common":
                if(str!=null){
                    webView.loadUrl(MyApp.BaseUrl + "#/details?token=" + token + "&" + str + "&is_user=1");
                }else{
                    webView.loadUrl(MyApp.BaseUrl + "#/details?token=" + token + "&comId=" +id+ "&is_user=1");
                }

                break;
            case "commint":
                webView.loadUrl(MyApp.BaseUrl + "#/commentindex?token=" + token + "&is_user=1");
                break;
            case "order_on":
                webView.loadUrl(MyApp.BaseUrl + "#/rderetails?token=" + token + "&order_no=" + title + "&is_user=1");
                break;
        }
    }

    String str;

    @Override
    protected void initView() {
        webView = new WebView(this);
        webmanLin.addView(webView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) webView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        webView.setLayoutParams(layoutParams);
        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        String userAgentString = webView.getSettings().getUserAgentString();
        userAgentString = userAgentString + " com.ajiani.maidahui ";

        SPUtils.put(this, "agent", userAgentString);
        webView.getSettings().setUserAgentString(userAgentString);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);

                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("app://go/back")) {
                    finish();
                } else if (url.contains("app://message")) {
                    //跳转到客服聊天页面
                    String substring = url.substring(14, url.length());
                    //json 经过urlsafebase64加密
                    String[] split = substring.split("\\/");
                    byte[] decode = Base64.decode(split[1], Base64.URL_SAFE);
                    String s1 = new String(decode);
                    Log.i(TAG, "shouldOverrideUrlLoading: " + s1);
                    Bundle bundle = new Bundle();
                    ShopInfo shopInfo = new Gson().fromJson(s1, ShopInfo.class);
                    bundle.putString("extra", "");
                    bundle.putString("name", shopInfo.getShopname());
                    bundle.putString("uid", split[0]);
                    JumpUtils.gotoActivity(WebManagerActivity.this, Chat2Activity.class, bundle);
                }
                return true;
            }

        });

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_webmanager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
        webView.removeAllViews();
    }


    @OnClick(R.id.webman_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5所在的Activity
    }

    //web调用相册图片
    public ValueCallback<Uri> uploadMessage;
    public final static int FILECHOOSER_RESULTCODE = 1;
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
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
            String path1 = FileUtils.getPath(this, result);
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

    private void clearUploadMessage() {
        if (mUploadMessageForAndroid5 != null) {
            mUploadMessageForAndroid5.onReceiveValue(null);
            mUploadMessageForAndroid5 = null;
        }
        if (uploadMessage != null) {
            uploadMessage.onReceiveValue(null);
            uploadMessage = null;
        }
    }
}