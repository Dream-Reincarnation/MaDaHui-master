package com.ajiani.maidahui.activity.chat;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.activity.mine.WebManagerActivity;
import com.ajiani.maidahui.adapter.chat.LogisticsAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.chat.ServiceBean;
import com.ajiani.maidahui.bean.chat.ServiceListBean;
import com.ajiani.maidahui.mInterface.chat.ShopListIn;
import com.ajiani.maidahui.presenters.chat.ShopMessagePresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogisticsActivity extends BaseActivity<ShopListIn.shopMessageView, ShopMessagePresenter> implements ShopListIn.shopMessageView  {

    @BindView(R.id.logistics_rel)
    RecyclerView logisticsRel;
    @BindView(R.id.logistics_smart)
    SmartRefreshLayout chatstarSmart;
    int page=1;
    private LogisticsAdapter logisticsAdapter;
    String type="TreesOrderShipping,orderShippingSuccess";

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", page + "");
        hashMap.put("msg_type", type);
        mPresenter.getShopMessage(hashMap);
        chatstarSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (logisticsAdapter.mList != null) {
                    logisticsAdapter.mList.clear();
                }
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page + "");
                hashMap.put("msg_type", type);
                mPresenter.getShopMessage(hashMap);

            }
        });
        chatstarSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page + "");
                hashMap.put("msg_type", type);
                mPresenter.getShopMessage(hashMap);

            }
        });
        logisticsAdapter.setOnClickLinstener(new LogisticsAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                Bundle bundle = new Bundle();
                bundle.putString("title",logisticsAdapter.mList.get(posstion).getExtra().getOrder_no());
                bundle.putString("type","order_on");
                JumpUtils.gotoActivity(LogisticsActivity.this, WebManagerActivity.class,bundle);
            }
        });

        logisticsAdapter.setOnLongClickLinstener(new LogisticsAdapter.onLongClickLinstener() {
            @Override
            public void onLongClick(int posstion) {
                popupWindow = new PopupWindow(LogisticsActivity.this);
                View inflate = LayoutInflater.from(LogisticsActivity.this).inflate(R.layout.del_item, null, false);
                inflate.findViewById(R.id.del_lin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.del_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("msg_type", logisticsAdapter.mList.get(posstion).getMsg_type());
                        hashMap.put("msgid", logisticsAdapter.mList.get(posstion).getMsgid());
                        mPresenter.getDelData(hashMap);
                        popupWindow.dismiss();
                        logisticsAdapter.mList.remove(posstion);
                        logisticsAdapter.notifyDataSetChanged();
                    }
                });
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(logisticsRel, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    @Override
    protected void initView() {
         logisticsRel.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<ServiceListBean.DataBean> dataBeans = new ArrayList<>();
        logisticsAdapter = new LogisticsAdapter(dataBeans);
        logisticsRel.setAdapter(logisticsAdapter);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_logistics;
    }



    @OnClick({R.id.logistics_back,R.id.chat_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logistics_back:
                finish();
                break;
            case R.id.chat_set:
                Bundle bundle = new Bundle();
                bundle.putString("type","logistics");
                JumpUtils.gotoActivity(this, ServiceSettActivity.class,bundle);
                break;
        }

    }

    @Override
    protected ShopMessagePresenter preparePresenter() {
        return new ShopMessagePresenter();
    }

    @Override
    public void shopMessageSuccess(String success) {
        ServiceListBean serviceListBean = new Gson().fromJson(success, ServiceListBean.class);
        List<ServiceListBean.DataBean> data = serviceListBean.getData();
        logisticsAdapter.mList.addAll(data);
        logisticsAdapter.notifyDataSetChanged();
        chatstarSmart.finishLoadMore(true);
        chatstarSmart.finishRefresh(true);
    }

    @Override
    public void delMessageSuccess(String success) {

    }

    @Override
    public void delMsgSuccess(String success) {

    }
}
