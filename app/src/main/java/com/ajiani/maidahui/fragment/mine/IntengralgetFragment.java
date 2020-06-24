package com.ajiani.maidahui.fragment.mine;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.IntegralAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.mine.IntegraldilsBean;
import com.ajiani.maidahui.mInterface.mine.IntegralIn;
import com.ajiani.maidahui.presenters.mine.IntegralPresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;

public class IntengralgetFragment extends BaseFragment<IntegralIn.IntegralView, IntegralPresenter> implements IntegralIn.IntegralView {
    @BindView(R.id.rel)
    RecyclerView rel;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    private IntegralAdapter integralAdapter;
    int page=1;

    @Override
    public void error(String error) {
        smart.finishLoadMore();
        smart.finishRefresh();
    }

    @Override
    protected void initData() {

        rel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<IntegraldilsBean.DataBean> dataBeans = new ArrayList<>();
        integralAdapter = new IntegralAdapter(dataBeans);
        rel.setAdapter(integralAdapter);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", page+"");
        hashMap.put("turnover", "1");
        mPresenter.getData(hashMap);

        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                integralAdapter.mList.clear();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page+"");
                hashMap.put("turnover", "1");
                mPresenter.getData(hashMap);
            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page+"");
                hashMap.put("turnover", "1");
                mPresenter.getData(hashMap);
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_integraluse;
    }

    @Override
    public void integralSuccess(String success) {
        IntegraldilsBean integraldilsBean = new Gson().fromJson(success, IntegraldilsBean.class);
        List<IntegraldilsBean.DataBean> data = integraldilsBean.getData();
        if (data != null) {
            if (data.size() > 0) {
                integralAdapter.mList.addAll(data);
                integralAdapter.notifyDataSetChanged();
            } else {
                if (integralAdapter.mList.size() > 0) {
                    Toast.makeText(mActivity, "已经到底了", Toast.LENGTH_SHORT).show();
                }
            }

        }


        smart.finishLoadMore();
        smart.finishRefresh();

    }

    @Override
    public void restantSuccess(String success) {

    }

    @Override
    protected IntegralPresenter preparePresenter() {
        return new IntegralPresenter();
    }
}
