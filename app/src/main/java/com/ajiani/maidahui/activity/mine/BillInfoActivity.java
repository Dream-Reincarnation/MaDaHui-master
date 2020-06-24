package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.mine.BillinfoBean;
import com.ajiani.maidahui.bean.mine.RestantBean;
import com.ajiani.maidahui.mInterface.mine.IntegralIn;
import com.ajiani.maidahui.presenters.mine.IntegralPresenter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BillInfoActivity extends BaseActivity<IntegralIn.IntegralView, IntegralPresenter> implements IntegralIn.IntegralView {
    @BindView(R.id.bail_back)
    ImageView bailBack;
    @BindView(R.id.bill_time)
    TextView billTime;
    @BindView(R.id.bill_bei)
    TextView billBei;
    @BindView(R.id.bill_yu)
    TextView billYu;
    @BindView(R.id.bill_statu)
    TextView billStatu;
    @BindView(R.id.bill_ding)
    TextView billDing;



    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if(bundle!=null){

            String order_no = bundle.getString("order_no");
            Log.i("WXY", "getMessage: +asdasdasda"+order_no);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("order_no",order_no);
            hashMap.put("page",1+"");
            mPresenter.getRestant(hashMap);
        }else{
        EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMessage(RestantBean.DataBean dataBean){
            Log.i("WXY", "getMessage:edfgerf " + "");
            billTime.setText(dataBean.getCreate_time());
            billDing.setText(dataBean.getOrder_no());
            billStatu.setText("已完成");
            billYu.setText(dataBean.getAfter_votes());
            billBei.setText(dataBean.getRemark());
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_bill;
    }


    @OnClick(R.id.bail_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected IntegralPresenter preparePresenter() {
        return new IntegralPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void integralSuccess(String success) {

    }

    @Override
    public void restantSuccess(String success) {
        BillinfoBean billinfoBean = new Gson().fromJson(success, BillinfoBean.class);
        List<BillinfoBean.DataBean> data = billinfoBean.getData();
        if(data!=null){
            BillinfoBean.DataBean dataBean = data.get(0);
            billTime.setText(dataBean.getCreate_time());
            billDing.setText(dataBean.getOrder_no());
            billStatu.setText("已完成");
            billYu.setText(dataBean.getAfter_votes());
            billBei.setText(dataBean.getRemark());
        }
    }
}
