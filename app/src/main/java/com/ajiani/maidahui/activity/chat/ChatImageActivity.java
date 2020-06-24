package com.ajiani.maidahui.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleActivity;
import com.bumptech.glide.Glide;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatImageActivity extends SimpleActivity {
    @BindView(R.id.photoview)
    ImageView photoview;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String img = bundle.getString("img");
        Glide.with(this).load(img).into(photoview);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_chatimg;
    }


}
