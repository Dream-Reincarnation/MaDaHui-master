package com.ajiani.maidahui.fragment.dynamic;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.PersonActivity;
import com.ajiani.maidahui.adapter.dynamic.PsonRelAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.base.SimpleFragment;
import com.ajiani.maidahui.bean.RecommedBean;
import com.ajiani.maidahui.bean.dynamic.PersonVideoBean;
import com.ajiani.maidahui.mInterface.dynamic.DynamicIn;
import com.ajiani.maidahui.mInterface.mine.MineInfo;
import com.ajiani.maidahui.presenters.dynamic.DynamicPresenter;
import com.ajiani.maidahui.presenters.mine.MinePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class DyWorkFragment extends BaseFragment<DynamicIn.videoListView, DynamicPresenter> implements DynamicIn.videoListView  {
    @BindView(R.id.pson_rel)
    RecyclerView psonRel;
    int page=1;
    private PsonRelAdapter psonRelAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        psonRel.setLayoutManager(new GridLayoutManager(getActivity(),3));

        ArrayList<PersonVideoBean.DataBean> dataBeans = new ArrayList<>();

        psonRelAdapter = new PsonRelAdapter(dataBeans);
        psonRel.setAdapter(psonRelAdapter);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("anchor_id", PersonActivity.id+"");
        hashMap.put("page",page+"");
        mPresenter.getListData(hashMap);
    }

    @Override
    protected DynamicPresenter preparePresenter() {
        return new DynamicPresenter();
    }


    @Override
    protected int createLayout() {
        return R.layout.fragment_dywork;
    }

    @Override
    public void error(String error) {

    }


    @Override
    public void listSuccess(String success) {
        PersonVideoBean personVideoBean = new Gson().fromJson(success, PersonVideoBean.class);
        List<PersonVideoBean.DataBean> data = personVideoBean.getData();
        if(data!=null){
            psonRelAdapter.mList.addAll(data);
            psonRelAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void followList(String success) {

    }
}
