package com.ajiani.maidahui.activity.chat;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.adapter.chat.ChatStarAdater;
import com.ajiani.maidahui.adapter.chat.ServiceAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.chat.ServiceListBean;
import com.ajiani.maidahui.mInterface.chat.ShopListIn;
import com.ajiani.maidahui.presenters.chat.ShopMessagePresenter;
import com.ajiani.maidahui.weight.recyclervire.SlideRecyclerView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//服务与提醒
public class ChatStarActivity extends BaseActivity<ShopListIn.shopMessageView, ShopMessagePresenter> implements ShopListIn.shopMessageView {

    @BindView(R.id.chatstar_rel)
    SlideRecyclerView chatstarRel;
    @BindView(R.id.service_set)
    ImageView serviceSet;
    @BindView(R.id.chatstar_smart)
    SmartRefreshLayout chatstarSmart;
    private int page = 1;
    private ServiceAdapter serviceAdapter;
    String type = "TreesSeedPay,TreesSeedSuccess,addGroupFail,addDduobaoFail,TreesOrderSuccess,orderPaySuccess,addDduobao,addDduobaoSuccess,addGroup,addGroupSuccess";
    private PopupWindow popupWindow;

    //剑来
    @Override
    public void error(String error) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", page + "");
        hashMap.put("msg_type", type);
        mPresenter.getShopMessage(hashMap);
    }

    @Override
    protected void initData() {
        serviceSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("type","service");
                JumpUtils.gotoActivity(ChatStarActivity.this, ServiceSettActivity.class,bundle);
            }
        });


        chatstarSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (serviceAdapter.mList != null) {
                    serviceAdapter.mList.clear();
                }
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page + "");
                hashMap.put("msg_type", type);
                mPresenter.getShopMessage(hashMap);
                chatstarSmart.finishRefresh(true);
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
                chatstarSmart.finishLoadMore(true);
            }
        });
    }

    @Override
    protected void initView() {
        chatstarRel.setLayoutManager(new LinearLayoutManager(this));
        ChatStarAdater chatStarAdater = new ChatStarAdater();
        ArrayList<ServiceListBean.DataBean> dataBeans = new ArrayList<>();
        serviceAdapter = new ServiceAdapter(dataBeans);
        chatstarRel.setAdapter(serviceAdapter);
        chatstarSmart.setRefreshHeader(new MaterialHeader(this));
        chatstarSmart.setEnableLoadMore(true);

        serviceAdapter.setOnClickLinstener(new ServiceAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                popupWindow = new PopupWindow(ChatStarActivity.this);
                View inflate = LayoutInflater.from(ChatStarActivity.this).inflate(R.layout.del_item, null, false);
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
                        hashMap.put("msg_type", serviceAdapter.mList.get(posstion).getMsg_type());
                        hashMap.put("msgid", serviceAdapter.mList.get(posstion).getMsgid());
                        mPresenter.getDelData(hashMap);
                        popupWindow.dismiss();
                        serviceAdapter.mList.remove(posstion);
                        serviceAdapter.notifyDataSetChanged();
                    }
                });
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(chatstarRel, Gravity.BOTTOM, 0, 0);
            }
        });

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_chatstar;
    }


    @OnClick(R.id.chatstar_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void shopMessageSuccess(String success) {
        ServiceListBean serviceListBean = new Gson().fromJson(success, ServiceListBean.class);
        List<ServiceListBean.DataBean> data = serviceListBean.getData();
        if (data.size() < 1) {
            Toast.makeText(this, "更多数据了", Toast.LENGTH_SHORT).show();
        } else {
            serviceAdapter.mList.addAll(data);
            serviceAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void delMessageSuccess(String success) {

    }

    @Override
    public void delMsgSuccess(String success) {

    }

    @Override
    protected ShopMessagePresenter preparePresenter() {
        return new ShopMessagePresenter();
    }
}
