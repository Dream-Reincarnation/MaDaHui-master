package com.ajiani.maidahui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import com.previewlibrary.GPreviewActivity;
import com.previewlibrary.GPreviewBuilder;
import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.enitity.ThumbViewInfo;
import com.previewlibrary.wight.PhotoViewPager;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends GPreviewActivity {


    @Override
    protected void transformOut() {
        finish();
    }

    @Override
    public PhotoViewPager getViewPager() {
        return super.getViewPager();
    }

    /***
     * 重写该方法
     * 使用你的自定义布局
     **/
    @Override
    public int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }*/
}
