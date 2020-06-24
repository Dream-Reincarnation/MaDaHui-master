package com.ajiani.maidahui.activity.mine;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.mInterface.mine.CastIn;
import com.ajiani.maidahui.presenters.mine.CastPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawPassActivity extends BaseActivity<CastIn.CastView, CastPresenter> implements CastIn.CastView {

    @BindView(R.id.draw_rel)
    RecyclerView drawRel;
    private String votes;
    private String account_type;

    @Override
    protected CastPresenter preparePresenter() {
        return new CastPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        HashMap<String, String> hashMap = new HashMap<>();
        mPresenter.getDetailsData(hashMap);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_drawpass;
    }

    @Override
    public void castSuccess(String success) {

    }

    @Override
    public void detailsSuccess(String success) {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
