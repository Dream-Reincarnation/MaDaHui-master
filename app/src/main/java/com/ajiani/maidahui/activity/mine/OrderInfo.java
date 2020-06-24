package com.ajiani.maidahui.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ajiani.maidahui.JsInteration;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.Utils.web.WebUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.MyWebChromeClient;
import com.ajiani.maidahui.activity.OpenFileChooserCallBack;
import com.ajiani.maidahui.base.SimpleActivity;

import java.io.ByteArrayInputStream;

import butterknife.BindView;

public class OrderInfo extends SimpleActivity implements OpenFileChooserCallBack {
    @BindView(R.id.order_web)
    WebView orderWeb;
    String mUrl = MyApp.BaseUrl + "#/order";
    private String token;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    private static final int REQUEST_CODE_PICK_IMAGE = 0x1111;
    private ValueCallback<Uri[]> filePathCallback;
    private final static int FILECHOOSER_RESULTCODE = 2;
    private Uri photoUri;

    @Override
    protected void initData() {
        StatusBarUtil2.setStatusBarMode(this, true, R.color.white);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

      /*  orderWeb.setWebChromeClient(new WebChromeClient() {
            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }


            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;
                //showAlbum();
            *//*    Intent intent = fileChooserParams.createIntent();
                try
                {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(getActivity(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }*//*
         *//*  Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(i, FILECHOOSER_RESULTCODE);*//*
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

        });*/
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        float height = 0;
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        orderWeb.getSettings().setTextZoom(100);
        float finalHeight = height;
        orderWeb.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.endsWith("safe-area-inset.css")) {

                    String result = "body .steep_padding{padding-top:" + (px2dip(OrderInfo.this, finalHeight) ) + "px !important}" +
                            "body .steep_padding_bottom{padding-bottom:1rem !important}";
                    WebResourceResponse response = new WebResourceResponse("text/css",
                            "utf-8",
                            new ByteArrayInputStream(result.getBytes()));
                    return response;
                }
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.i("WXY", "shouldOverrideUrlLoading: "+url);
                // 必须处理url，不处理
                if (url.contains("app://go/back")) {
                    finish();
                } else {
                    WebUtils.instance().Client(orderWeb, view, url, OrderInfo.this);
                    return true;
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

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            if (null == mUploadMessage && filePathCallback == null)
                return;
            if (resultCode == RESULT_CANCELED) {
                if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(null);
                    mUploadMessage = null;
                }
                if (filePathCallback != null) {
                    filePathCallback.onReceiveValue(null);
                    filePathCallback = null;
                }
            } else if (resultCode == RESULT_OK) {
                Uri result = null;
                if (intent != null && intent.getData() != null) {
                    result = intent.getData();
                }
                if (result == null) {
                    if (photoUri != null) {
                        result = photoUri;
                    }
                }
                Uri[] uris = new Uri[1];
                uris[0] = result;
                if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                }

                if (filePathCallback != null) {
                    filePathCallback.onReceiveValue(uris);
                    mUploadMessage = null;
                }
            }
        }


    }

    private void setWebViewInitialScale() {
        WindowManager wm = (WindowManager) OrderInfo.this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        if (width > 650) {
            this.orderWeb.setInitialScale(320);
        } else if (width > 520) {
            this.orderWeb.setInitialScale(300);
        } else if (width > 450) {
            this.orderWeb.setInitialScale(280);
        } else if (width > 300) {
            this.orderWeb.setInitialScale(260);
        } else {
            this.orderWeb.setInitialScale(240);
        }
    }

    @Override
    protected void initView() {
        //加载
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String order_status = bundle.getString("order_status");
        String headerType = bundle.getString("headerType");
        orderWeb = WebUtils.instance().init(this, orderWeb);
       /* orderWeb.getSettings().setJavaScriptEnabled(true);
        orderWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        orderWeb.setVerticalScrollBarEnabled(false);
        orderWeb.setHorizontalScrollBarEnabled(false);


        orderWeb.getSettings().setJavaScriptEnabled(true);
        orderWeb.getSettings().setDomStorageEnabled(true);
        orderWeb.addJavascriptInterface(new JsInteration(), "android"
        );*/
        orderWeb.setWebChromeClient(new MyWebChromeClient(this));
        String userAgentString = orderWeb.getSettings().getUserAgentString();
        setWebViewInitialScale();
        userAgentString = userAgentString + " com.ajiani.maidahui";

        orderWeb.getSettings().setUserAgentString(userAgentString);
        String token = (String) SPUtils.get(this, "token", "");
        if (token.length() > 3) {
            Log.i("wxy", "initView: " + token);
            orderWeb.loadUrl(mUrl + "?token=" + token + "&order_status=" + order_status + "&is_user=1" + "&headerType=" + headerType);
        } else {
            orderWeb.loadUrl(mUrl + "?token=" + "&order_status=" + order_status + "&is_user=1" + "&headerType=" + headerType);
        }
        //  orderWeb.loadUrl(mUrl);

        orderWeb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("app://go/back")) {
                    finish();
                    return true;
                }

                return true;

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected int createLayout() {
        return R.layout.order_info;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && orderWeb.canGoBack()) {
            orderWeb.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出H5所在的Activity
    }

    @Override
    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
        mUploadMessage = uploadMsg;
        gotoPhoto();
    }

    @Override
    public void openFileChooser5CallBack(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        //    filePathCallback = valueCallback;
        gotoPhoto();
    }

    private void gotoPhoto() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(albumIntent, "请选择图片"), REQUEST_CODE_PICK_IMAGE);
    }
}
