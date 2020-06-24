package com.ajiani.maidahui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.activity.MyApp;

public class JsInteration {
    @JavascriptInterface
    public String getToken(){
        return "啦啦啦啦啦啦啦啦";
    }

    @JavascriptInterface
    public void show(String OrderNum){
//获取剪贴板管理器：
        Toast.makeText(MyApp.getApp(), "点击了复制", Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void finish(String OrderNum){
//获取剪贴板管理器：

    }
}
