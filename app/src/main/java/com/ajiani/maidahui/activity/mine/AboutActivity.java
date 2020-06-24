package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends SimpleActivity {
    @BindView(R.id.about_web)
    WebView aboutWeb;

    @BindView(R.id.title)
    TextView textView;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String url = bundle.getString("url");
        WebSettings webSettings = aboutWeb.getSettings();
        aboutWeb.getSettings().setJavaScriptEnabled(true);
        aboutWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        aboutWeb.getSettings().setJavaScriptEnabled(true);
        aboutWeb.getSettings().setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        aboutWeb.loadUrl(url);

        aboutWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title != null) {
                    textView.setText(title);
                }
            }
        });
        aboutWeb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
                aboutWeb.loadUrl(url);

                return true;
            }


            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // writeData();
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_about;
    }




    @OnClick(R.id.about_back)
    public void onViewClicked() {
        finish();
    }
}
