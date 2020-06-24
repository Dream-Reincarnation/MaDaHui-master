package com.ajiani.maidahui.activity.dynamic;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureActivity extends SimpleActivity {
    @BindView(R.id.pic_rel)
    RecyclerView picRel;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_picture;
    }
}
