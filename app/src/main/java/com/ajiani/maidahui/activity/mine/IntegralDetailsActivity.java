package com.ajiani.maidahui.activity.mine;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.IntegralAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.mine.IntegraldilsBean;
import com.ajiani.maidahui.mInterface.mine.IntegralIn;
import com.ajiani.maidahui.presenters.mine.IntegralPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegralDetailsActivity extends BaseActivity<IntegralIn.IntegralView, IntegralPresenter> implements IntegralIn.IntegralView {
    @BindView(R.id.intedeta_back)
    ImageView intedetaBack;
    @BindView(R.id.intedeta_rel)
    RecyclerView intedetaRel;
    private IntegralAdapter integralAdapter;

    @Override
    protected IntegralPresenter preparePresenter() {
        return new IntegralPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page","1");
        mPresenter.getData(hashMap);
    }

    @Override
    protected void initView() {
        intedetaRel.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<IntegraldilsBean.DataBean> dataBeans = new ArrayList<>();
        integralAdapter = new IntegralAdapter(dataBeans);
        intedetaRel.setAdapter(integralAdapter);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_integraldetails;
    }

    @Override
    public void integralSuccess(String success) {
        IntegraldilsBean integraldilsBean = new Gson().fromJson(success, IntegraldilsBean.class);
        List<IntegraldilsBean.DataBean> data = integraldilsBean.getData();
        integralAdapter.mList.addAll(data);
        integralAdapter.notifyDataSetChanged();
    }

    @Override
    public void restantSuccess(String success) {

    }


    @OnClick(R.id.intedeta_back)
    public void onViewClicked() {
        finish();
    }
}
