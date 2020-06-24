package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.FragmentAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.fragment.mine.IntengralUserFragment;
import com.ajiani.maidahui.fragment.mine.IntengralgetFragment;
import com.ajiani.maidahui.fragment.mine.MineLikeFragment;
import com.ajiani.maidahui.fragment.mine.VLogShopFragment;
import com.ajiani.maidahui.fragment.mine.WorksFragment;
import com.ajiani.maidahui.mInterface.mine.IntegralIn;
import com.ajiani.maidahui.presenters.mine.IntegralPresenter;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntengralActivity extends BaseActivity<IntegralIn.IntegralView, IntegralPresenter> implements IntegralIn.IntegralView {
    @BindView(R.id.integral_back)
    FrameLayout integralBack;
    @BindView(R.id.integral_info)
    FrameLayout integralInfo;
    @BindView(R.id.integral_lin)
    LinearLayout integral_lin;
    @BindView(R.id.integral_num)
    TextView integralNum;
    @BindView(R.id.integral_tab)
    TabLayout integralTab;
    @BindView(R.id.integral_vp)
    ViewPager integralVp;

    private ArrayList<Fragment> fragments;

    @Override
    protected IntegralPresenter preparePresenter() {
        return new IntegralPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    private int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //得到状态栏高度
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        float height = 0;
        if (resourceId > 0) {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) integral_lin.getLayoutParams();
        layoutParams.height= layoutParams.height+(int) height;
        integral_lin.setLayoutParams(layoutParams);

       integral_lin.setPadding(0,(int)(height+10),0,0);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("获取");
        strings.add("使用");
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String integral = bundle.getString("integral");
        integralNum.setText(integral);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new IntengralgetFragment());
        fragments.add(new IntengralUserFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), strings, fragments);
        integralVp.setAdapter(fragmentAdapter);
        integralTab.setupWithViewPager(integralVp);
        integralTab.setTabIndicatorFullWidth(false);
        LinearLayout linearLayout = (LinearLayout) integralTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.mipmap.mine_square));
        linearLayout.setDividerPadding(dp2px(30));
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_integral;
    }

    @Override
    public void integralSuccess(String success) {

    }

    @Override
    public void restantSuccess(String success) {

    }


    @OnClick({R.id.integral_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.integral_back:
                finish();
                break;

        }
    }

}
