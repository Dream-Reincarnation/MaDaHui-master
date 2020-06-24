package com.ajiani.maidahui.activity.chat;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.chat.ChatNewsAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.mInterface.chat.SystemList;
import com.ajiani.maidahui.presenters.chat.SystemPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsAssistant extends BaseActivity<SystemList.systemListView, SystemPresenter> implements SystemList.systemListView  {
    @BindView(R.id.chatnews_rel)
    RecyclerView chatnewsRel;
    @BindView(R.id.chatnews_smart)
    SmartRefreshLayout chatnewsSmart;



    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("msg_type", "system");
        stringStringHashMap.put("page", "1");
        mPresenter.getSystemList(stringStringHashMap);
    }

    @Override
    protected void initView() {
          chatnewsRel.setLayoutManager(new LinearLayoutManager(this));
        chatnewsSmart.setEnableRefresh(false);
        chatnewsSmart.setEnableLoadMore(false);
        ChatNewsAdapter chatNewsAdapter = new ChatNewsAdapter();
        chatnewsRel.setAdapter(chatNewsAdapter);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_newsassistant;
    }



    @OnClick(R.id.chatnews_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void systemListSuccess(String success){

    }

    @Override
    protected SystemPresenter preparePresenter() {
        return new SystemPresenter();
    }
}
