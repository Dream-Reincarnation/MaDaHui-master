package com.ajiani.maidahui.activity.mine;

import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.EarDetaAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.mine.EarDetailBean;
import com.ajiani.maidahui.mInterface.mine.DetaileIn;
import com.ajiani.maidahui.presenters.mine.DetailsMoneyPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class EarDetaile extends BaseActivity<DetaileIn.detaileView, DetailsMoneyPresenter> implements DetaileIn.detaileView {

    @BindView(R.id.eardeta_back)
    ImageView rechargeBack;
    @BindView(R.id.eardeta_rel)
    RecyclerView eardetaRel;
    private EarDetaAdapter earDetaAdapter;

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        eardetaRel.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<EarDetailBean.DataBean> dataBeans = new ArrayList<>();
        earDetaAdapter = new EarDetaAdapter(dataBeans);
        eardetaRel.setAdapter(earDetaAdapter);

        mPresenter.getEar(new HashMap<String, String>());
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_eardetails;
    }

    @Override
    public void earSuccess(String success) {
        EarDetailBean earDetailBean = new Gson().fromJson(success, EarDetailBean.class);
        List<EarDetailBean.DataBean> data = earDetailBean.getData();
        earDetaAdapter.mList.addAll(data);
        earDetaAdapter.notifyDataSetChanged();
    }

    @Override
    protected DetailsMoneyPresenter preparePresenter() {
        return new DetailsMoneyPresenter();
    }



    @OnClick(R.id.eardeta_back)
    public void onViewClicked() {
        finish();
    }
}
