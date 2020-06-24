package com.ajiani.maidahui.fragment.mine;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.VlogAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.mine.MineInfo;
import com.ajiani.maidahui.presenters.mine.MinePresenter;
import com.flyco.tablayout.SegmentTabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class VLogShopFragment extends BaseFragment<MineInfo.mineView, MinePresenter> implements MineInfo.mineView {


    @BindView(R.id.vlogs_rel)
    RecyclerView vlogsRel;

    int page = 1;
    private VlogAdapter vlogAdapter;
    boolean aBoolean;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (mPresenter != null) {
                aBoolean = true;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page + "");
                hashMap.put("is_goods", "1");
                mPresenter.getVideo(hashMap);
            }
        }
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {


        vlogsRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        vlogAdapter = new VlogAdapter(dataBeans);
        vlogsRel.setAdapter(vlogAdapter);
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_vlog;
    }

    @Override
    protected MinePresenter preparePresenter() {
        return new MinePresenter();
    }

    @Override
    public void videoSuccess(String string) {
        MineVideoBean mineVideoBean = new Gson().fromJson(string, MineVideoBean.class);
        List<VideoInfoBean.DataBean> data = mineVideoBean.getData();
        if (data != null) {
            if (aBoolean) {
                vlogAdapter.mList.clear();
                vlogAdapter.mList.addAll(data);
                vlogAdapter.notifyDataSetChanged();
                aBoolean = false;
            } else {

                vlogAdapter.mList.addAll(data);
                vlogAdapter.notifyDataSetChanged();
            }

        }

    }

    @Override
    public void userInfo(String success) {

    }

    @Override
    public void loveListSuccess(String success) {

    }

    @Override
    public void getCountSuccess(String success) {

    }
}
