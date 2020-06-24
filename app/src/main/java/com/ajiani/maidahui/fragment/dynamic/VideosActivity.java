package com.ajiani.maidahui.fragment.dynamic;

import android.content.Intent;
import android.os.Bundle;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideosActivity extends SimpleActivity {
    @BindView(R.id.jzstd)
    JzvdStd jzstd;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        jzstd.setUp(
               url,
                "", Jzvd.SCREEN_NORMAL);
        jzstd.startVideo();
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_videos;
    }


}
