package com.ajiani.maidahui.activity.mine;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.adapter.mine.RestantAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.mine.RestantBean;
import com.ajiani.maidahui.mInterface.mine.IntegralIn;
import com.ajiani.maidahui.presenters.mine.IntegralPresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestantDetailsActivity extends BaseActivity<IntegralIn.IntegralView, IntegralPresenter> implements IntegralIn.IntegralView {
    @BindView(R.id.restantdeta_back)
    ImageView restantdetaBack;
    @BindView(R.id.restantdeta_rel)
    RecyclerView restantdetaRel;
    @BindView(R.id.restantdeta_smart)
    SmartRefreshLayout smartRefreshLayout;
    private RestantAdapter restantAdapter;
    private int  page=1;


    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page",page+"");
        mPresenter.getRestant(hashMap);
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                HashMap<String, String> hashMap = new HashMap<>();
                page++;
                hashMap.put("page",page+"");
                mPresenter.getRestant(hashMap);
                smartRefreshLayout.finishLoadMore();
            }
        });
        restantAdapter.setOnClickLinstener(new RestantAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                //跳转月账单页面
                RestantBean.DataBean dataBean = restantAdapter.mList.get(posstion);
                EventBus.getDefault().postSticky(dataBean);
                JumpUtils.gotoActivity(RestantDetailsActivity.this,BillInfoActivity.class);
            }
        });
    }

    @Override
    protected void initView() {
        restantdetaRel.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<RestantBean.DataBean> dataBeans = new ArrayList<>();
        restantAdapter = new RestantAdapter(dataBeans);
        restantdetaRel.setAdapter(restantAdapter);
        smartRefreshLayout.setRefreshHeader(new MaterialHeader(this));
        smartRefreshLayout.setEnableLoadMore(true);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_restantdetails;
    }

    @OnClick(R.id.restantdeta_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected IntegralPresenter preparePresenter() {
        return new IntegralPresenter();
    }

    @Override
    public void integralSuccess(String success) {

    }

    @Override
    public void restantSuccess(String success) {
        RestantBean restantBean = new Gson().fromJson(success, RestantBean.class);
        List<RestantBean.DataBean> data = restantBean.getData();
        restantAdapter.mList.addAll(data);
        restantAdapter.notifyDataSetChanged();

    }
}
