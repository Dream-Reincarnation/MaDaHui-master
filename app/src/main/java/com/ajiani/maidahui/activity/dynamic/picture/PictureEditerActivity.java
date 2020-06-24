package com.ajiani.maidahui.activity.dynamic.picture;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.tab.TabUtils;
import com.ajiani.maidahui.adapter.dynamic.VPAdapter;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.fragment.dynamic.picture.AtlasFragment;
import com.ajiani.maidahui.fragment.dynamic.picture.PhotoMovieFragment;
import com.ajiani.maidahui.fragment.dynamic.picture.PiiicFragment;
import com.ajiani.maidahui.weight.MyViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureEditerActivity extends SimpleActivity {
    @BindView(R.id.picture_editer_back)
    FrameLayout pictureEditerBack;
    @BindView(R.id.picture_editer_tab)
    TabLayout pictureEditerTab;
    @BindView(R.id.picture_editer_vp)
    MyViewPager pictureEditerVp;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("图集");
        strings.add("照片电影");
        strings.add("长图");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AtlasFragment());
        fragments.add(new PhotoMovieFragment());
        fragments.add(new PiiicFragment());
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), fragments, strings);
        pictureEditerVp.setAdapter(vpAdapter);
        pictureEditerTab.setupWithViewPager(pictureEditerVp);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_picture_edit;
    }


    @OnClick({R.id.picture_editer_back, R.id.picture_editer_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.picture_editer_back:
                break;
            case R.id.picture_editer_tab:
                break;
        }
    }
}
