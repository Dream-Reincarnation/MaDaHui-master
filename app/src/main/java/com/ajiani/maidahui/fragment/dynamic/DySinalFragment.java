package com.ajiani.maidahui.fragment.dynamic;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.PersonActivity;
import com.ajiani.maidahui.adapter.dynamic.GrassAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.dynamic.PersonVideoBean;
import com.ajiani.maidahui.mInterface.dynamic.DynamicIn;
import com.ajiani.maidahui.presenters.dynamic.DynamicPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class DySinalFragment extends BaseFragment<DynamicIn.videoListView, DynamicPresenter> implements DynamicIn.videoListView {


    @BindView(R.id.sinal_rel)
    RecyclerView sinalRel;
    int page=1;
    private String TAG="wxy";
    private GrassAdapter grassAdapter;

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        sinalRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<PersonVideoBean.DataBean> dataBeans = new ArrayList<>();
        grassAdapter = new GrassAdapter(dataBeans);
        sinalRel.setAdapter(grassAdapter);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("anchor_id", PersonActivity.id+"");
        hashMap.put("page",page+"");
        hashMap.put("is_goods","1");
        mPresenter.getListData(hashMap);
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_dyspecial;
    }

    @Override
    protected DynamicPresenter preparePresenter() {
        return new DynamicPresenter();
    }

    @Override
    public void listSuccess(String success) {
        PersonVideoBean personVideoBean = new Gson().fromJson(success, PersonVideoBean.class);
        List<PersonVideoBean.DataBean> data = personVideoBean.getData();
        if(data!=null){
            grassAdapter.mList.addAll(data);
            grassAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void followList(String success) {

    }
}
