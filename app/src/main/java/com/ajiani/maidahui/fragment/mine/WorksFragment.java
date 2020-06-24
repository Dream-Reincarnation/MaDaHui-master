package com.ajiani.maidahui.fragment.mine;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.adapter.mine.ProjectRelAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.mine.MineInfo;
import com.ajiani.maidahui.presenters.mine.MinePresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class WorksFragment extends BaseFragment<MineInfo.mineView, MinePresenter> implements MineInfo.mineView  {
    @BindView(R.id.work_rel)
    RecyclerView workRel;
    @BindView(R.id.work_smart)
    SmartRefreshLayout smart;
    private ProjectRelAdapter projectRelAdapter;
    int page=1;
    private ArrayList<String> list;
    @Override
    protected void initData() {
          HashMap<String, String> hashMap = new HashMap<>();
          hashMap.put("page",page+"");
          mPresenter.getVideo(hashMap);

          smart.setOnRefreshListener(new OnRefreshListener() {
              @Override
              public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=1;
                  HashMap<String, String> hashMap = new HashMap<>();
                  hashMap.put("page",page+"");
                  mPresenter.getVideo(hashMap);
              }
          });
          smart.setOnLoadMoreListener(new OnLoadMoreListener() {
              @Override
              public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                  page++;
                  HashMap<String, String> hashMap = new HashMap<>();
                  hashMap.put("page",page+"");
                  mPresenter.getVideo(hashMap);
              }
          });
          projectRelAdapter.setOnClickLinstener(new ProjectRelAdapter.onClickLinstener() {
              @Override
              public void onClick(int posstion) {
                  Bundle bundle = new Bundle();
                  bundle.putString("id", projectRelAdapter.mList.get(posstion).getAid() + "");
                  String video_type = projectRelAdapter.mList.get(posstion).getVideo_type();
                  list=new ArrayList<String>();
                  for(int i=0;i<projectRelAdapter.mList.size();i++){
                      list.add(projectRelAdapter.mList.get(i).getAid()+"");
                  }
                  for(int i=0;i<list.size();i++){
                      if(list.get(i).equals(projectRelAdapter.mList.get(posstion).getAid()+"")){
                          posstion=i;
                          break;
                      }
                  }
                  if (video_type.equals("video")) {
                      //视频

                      Bundle bundle1 = new Bundle();
                      Log.i("wxy", "onClick: "+list.size());
                      bundle1.putStringArrayList("list", list);
                      bundle1.putString("posstion", posstion+"");
                      JumpUtils.gotoActivity(getActivity(), Main3Activity.class,bundle1);
                      EventBus.getDefault().postSticky(projectRelAdapter.mList);
                      // JumpUtils.gotoActivity(getActivity(), MineProductActivity.class, bundle);
                  }
              }
          });
    }

    @Override
    protected void initView() {
        workRel.setLayoutManager(new GridLayoutManager(getActivity(),3));
        ArrayList<VideoInfoBean.DataBean> integers = new ArrayList<>();
        projectRelAdapter = new ProjectRelAdapter(integers);
        workRel.setAdapter(projectRelAdapter);
    }

    @Override
    protected MinePresenter preparePresenter() {
        return new MinePresenter();
    }

    @Override
    protected int createLayout() {
        return R.layout.works_item;
    }

    @Override
    public void videoSuccess(String string) {
        if(smart!=null){
            smart.finishLoadMore();
            smart.finishRefresh();
        }

        MineVideoBean mineVideoBean = new Gson().fromJson(string, MineVideoBean.class);
        projectRelAdapter.mList.addAll(mineVideoBean.getData());
        projectRelAdapter.notifyDataSetChanged();
        List<VideoInfoBean.DataBean> data = mineVideoBean.getData();
        //得到视频的url
        list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getVideo_type().equals("video")) {
                list.add(data.get(i).getAid() + "");
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

    @Override
    public void error(String error) {
        smart.finishLoadMore();
        smart.finishRefresh();    }
}
