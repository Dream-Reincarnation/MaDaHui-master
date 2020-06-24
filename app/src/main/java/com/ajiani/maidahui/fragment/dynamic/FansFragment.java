package com.ajiani.maidahui.fragment.dynamic;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.chat.FansAdapter;
import com.ajiani.maidahui.adapter.dynamic.FnasAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.dynamic.FollowListBean;
import com.ajiani.maidahui.mInterface.dynamic.FollowListIn;
import com.ajiani.maidahui.presenters.FollowListPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class FansFragment extends BaseFragment<FollowListIn.followListView, FollowListPresenter> implements FollowListIn.followListView {

    @BindView(R.id.fans_rel)
    RecyclerView fansRel;
    private FnasAdapter fnasAdapter;

    @Override
    public void error(String error) {

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            if(mPresenter!=null){
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page","1");
                mPresenter.fansListData(hashMap);
            }
        }
    }
    @Override
    protected void initData() {
       fansRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<FollowListBean.DataBean> dataBeans = new ArrayList<>();
        fnasAdapter = new FnasAdapter(dataBeans);
        fansRel.setAdapter(fnasAdapter);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page","1");
        mPresenter.fansListData(hashMap);
        fnasAdapter.setOnClickLinstener(new FnasAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("anchor_id",fnasAdapter.mList.get(posstion).getId()+"");
                mPresenter.followUserData(hashMap);
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_fans;
    }

    @Override
    protected FollowListPresenter preparePresenter() {
        return new FollowListPresenter();
    }

    @Override
    public void followListSuccess(String success) {

    }

    @Override
    public void fansListSuccess(String success) {
        FollowListBean followListBean = new Gson().fromJson(success, FollowListBean.class);
        List<FollowListBean.DataBean> data = followListBean.getData();
        fnasAdapter.mList.clear();
        if(data.size()==0){
            fansRel.setVisibility(View.GONE);
        }
        if(data!=null){
            fnasAdapter.mList.addAll(data);
            fnasAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void followUser(String success) {

    }
}
