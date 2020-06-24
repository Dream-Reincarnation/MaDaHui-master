package com.ajiani.maidahui.weight.web;

import android.net.Uri;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.ajiani.maidahui.activity.OpenFileChooserCallBack;



public class MyWebChromeClient extends WebChromeClient {

    private OpenFileChooserCallBack mOpenFileChooserCallBack;

    public MyWebChromeClient(OpenFileChooserCallBack openFileChooserCallBack) {
        mOpenFileChooserCallBack = openFileChooserCallBack;
    }

    //针对 Android 5.0+
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback,
                                     FileChooserParams fileChooserParams) {
        Log.e("hagan", "onShowFileChooser");
        mOpenFileChooserCallBack.openFileChooser5CallBack(webView, valueCallback, fileChooserParams);
        return true;

    }


    //针对 Android版本 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        mOpenFileChooserCallBack.openFileChooserCallBack(uploadMsg, acceptType);
    }

    // 针对 Android版本 < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    //针对 Android版本  > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }


    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        //  return super.onJsAlert(view, url, message, result);
        return true;
    }
}