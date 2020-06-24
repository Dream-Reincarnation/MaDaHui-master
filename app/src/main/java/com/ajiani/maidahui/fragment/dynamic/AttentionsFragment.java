package com.ajiani.maidahui.fragment.dynamic;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.dynamic.AttenationAdapter;

import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.dynamic.FollowListBean;
import com.ajiani.maidahui.mInterface.dynamic.FollowListIn;
import com.ajiani.maidahui.presenters.FollowListPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class AttentionsFragment extends BaseFragment<FollowListIn.followListView, FollowListPresenter> implements FollowListIn.followListView {

    int isFollow=1;
    @BindView(R.id.attention_rel)
    RecyclerView attentionRel;
    private AttenationAdapter attenationAdapter;

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
                mPresenter.followListData(hashMap);
            }
        }
    }

    @Override
    protected void initData() {
        attentionRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<FollowListBean.DataBean> dataBeans = new ArrayList<>();
        attenationAdapter = new AttenationAdapter(dataBeans);
        attentionRel.setAdapter(attenationAdapter);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page","1");
        mPresenter.followListData(hashMap);


        attenationAdapter.setOnClickLinstener(new AttenationAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                //取消关注
               HashMap<String, String> hashMap = new HashMap<>();
               hashMap.put("anchor_id",attenationAdapter.mList.get(posstion).getId()+"");
               mPresenter.followUserData(hashMap);

            }
        });

    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_attention;
    }

    @Override
    public void followListSuccess(String success) {
        FollowListBean followListBean = new Gson().fromJson(success, FollowListBean.class);
        List<FollowListBean.DataBean> data = followListBean.getData();
        attenationAdapter.mList.clear();

        if(data!=null){
            attenationAdapter.mList.addAll(data);
            attenationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void fansListSuccess(String success) {

    }

    @Override
    public void followUser(String success) {

    }

    @Override
    protected FollowListPresenter preparePresenter() {
        return new FollowListPresenter();
    }
}
