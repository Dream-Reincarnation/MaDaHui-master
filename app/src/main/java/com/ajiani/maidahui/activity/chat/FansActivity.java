package com.ajiani.maidahui.activity.chat;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.dynamic.PersonVpAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.fragment.dynamic.AttentionsFragment;
import com.ajiani.maidahui.fragment.dynamic.FansFragment;
import com.ajiani.maidahui.mInterface.chat.FansListIn;
import com.ajiani.maidahui.presenters.chat.FansPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FansActivity extends BaseActivity<FansListIn.fansListView, FansPresenter> implements FansListIn.fansListView {


    @BindView(R.id.fans_tab)
    TabLayout fansTab;
    @BindView(R.id.fans_vp)
    ViewPager fansVp;
    private String type;


    @Override
    protected FansPresenter preparePresenter() {
        return new FansPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if(bundle!=null){
            type = bundle.getString("type");
        }
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Fragment> fragments = new ArrayList<>();
        strings.add("关注");
        strings.add("粉丝");
        if(type!=null){
            fansVp.setCurrentItem(1);
        }
        fragments.add(new AttentionsFragment());
        fragments.add(new FansFragment());
        PersonVpAdapter personVpAdapter = new PersonVpAdapter(getSupportFragmentManager(), fragments, strings);
        fansVp.setAdapter(personVpAdapter);
        fansTab.setupWithViewPager(fansVp);
        fansTab.setTabIndicatorFullWidth(false);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_fans;
    }

    @Override
    public void fansSuccess(String success) {

    }


    @OnClick(R.id.fans_back)
    public void onViewClicked() {
        finish();
    }


}
