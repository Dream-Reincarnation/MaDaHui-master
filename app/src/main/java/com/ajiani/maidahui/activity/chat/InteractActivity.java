package com.ajiani.maidahui.activity.chat;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.adapter.chat.BoradAdapter;
import com.ajiani.maidahui.adapter.chat.LogisticsAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.chat.BoardCastMessageBean;
import com.ajiani.maidahui.bean.chat.SystemBean;
import com.ajiani.maidahui.mInterface.chat.BoardListIn;
import com.ajiani.maidahui.mInterface.chat.SystemList;
import com.ajiani.maidahui.presenters.chat.BoardPresenter;
import com.ajiani.maidahui.presenters.chat.SystemPresenter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InteractActivity extends BaseActivity<BoardListIn.boardListView, BoardPresenter> implements BoardListIn.boardListView {
    @BindView(R.id.chatinter_rel)
    RecyclerView chatnewsRel;
    @BindView(R.id.chatinter_smart)
    SmartRefreshLayout chatnewsSmart;
    private BoradAdapter boradAdapter;
    int page = 1;
    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("msg_type", type);
        hashMap.put("page", page + "");
        mPresenter.boardListData(hashMap);
    }

    String type = "cashSuccess,cashFail,ComplaintSend,ComplaintEnd,LinkMessage";

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {


        boradAdapter.setOnLongClickLinstener(new LogisticsAdapter.onLongClickLinstener() {
            @Override
            public void onLongClick(int posstion) {
                popupWindow = new PopupWindow(InteractActivity.this);
                View inflate = LayoutInflater.from(InteractActivity.this).inflate(R.layout.del_item, null, false);
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
                        hashMap.put("msg_type", boradAdapter.mList.get(posstion).getMsg_type());
                        hashMap.put("msgid", boradAdapter.mList.get(posstion).getMsgid());
                        mPresenter.getDelData(hashMap);
                        popupWindow.dismiss();
                        boradAdapter.mList.remove(posstion);
                        boradAdapter.notifyDataSetChanged();
                    }
                });
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(chatnewsRel, Gravity.BOTTOM, 0, 0);
            }
        });

    }

    @Override
    protected void initView() {
        chatnewsRel.setLayoutManager(new LinearLayoutManager(this));
        chatnewsSmart.setEnableRefresh(false);
        chatnewsSmart.setEnableLoadMore(false);
        ArrayList<SystemBean.DataBean> dataBeans = new ArrayList<>();
        boradAdapter = new BoradAdapter(dataBeans);
        chatnewsRel.setAdapter(boradAdapter);


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_interact;
    }


    @OnClick({R.id.chatinter_back,R.id.chat_board_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatinter_back:
                finish();
                break;
            case R.id.chat_board_set:
                Bundle bundle = new Bundle();
                bundle.putString("type","board");
                JumpUtils.gotoActivity(this, ServiceSettActivity.class,bundle);
                break;
        }

    }

    @Override
    protected BoardPresenter preparePresenter() {
        return new BoardPresenter();
    }

    @Override
    public void boardListSuccess(String sucess) {
        SystemBean boardCastMessageBean = new Gson().fromJson(sucess, SystemBean.class);
        List<SystemBean.DataBean> data = boardCastMessageBean.getData();
        if(data!=null){
            boradAdapter.mList.addAll(data);
            boradAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void delMessage(String success) {

    }
}
