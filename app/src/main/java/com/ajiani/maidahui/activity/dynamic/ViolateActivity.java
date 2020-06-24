package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.web.WebUtils;
import com.ajiani.maidahui.base.SimpleActivity;
import com.example.szing.zxing.android.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViolateActivity extends SimpleActivity {
    @BindView(R.id.violate_web)
    WebView violateWeb;
    String VIOLATE_URL = "https://m.maidahui.com/videos/tort_complaint";

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        String videoID = bundle.getString("videoID");
        Log.i("wxy", "initView: " + videoID);
        String token = (String) SPUtils.get(this, "token", "");
        violateWeb.loadUrl(VIOLATE_URL + "?token=" + token + "&video_id=" + videoID);

        violateWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.contains("app://scan")) {

                } else {
                    WebUtils.instance().Client(violateWeb, view, url, ViolateActivity.this);
                    return true;
                }
                return true;
            }



        });
    }

    @Override
    protected int createLayout() {
        return R.layout.violate;
    }


    @OnClick(R.id.violate_back)
    public void onViewClicked() {
        finish();
    }
}
