package com.ajiani.maidahui.fragment.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.AutoPlayTool;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.adapter.attent.FollowAdapter;
import com.ajiani.maidahui.adapter.attent.FollowAdapters;
import com.ajiani.maidahui.adapter.dynamic.AttentAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.attention.FollowListBeans;
import com.ajiani.maidahui.bean.dynamic.VideoListBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.fragment.mine.CommentFragment;
import com.ajiani.maidahui.mInterface.dynamic.DynamicIn;
import com.ajiani.maidahui.presenters.dynamic.DynamicPresenter;
import com.google.android.material.tabs.TabLayout;
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
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class AttentionFragment extends BaseFragment<DynamicIn.videoListView, DynamicPresenter> implements DynamicIn.videoListView {
    int page = 1;
    @BindView(R.id.attent_rel)
    RecyclerView attentRel;
    @BindView(R.id.attent_smart)
    SmartRefreshLayout attentSmart;
    @BindView(R.id.attent_lins)
    LinearLayout attentLin;
    public FollowAdapter followAdapter;

    @Override
    protected void initData() {
        //设置屏幕顶部的距离
        HomeActivity.context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int statusBarHeight = StatusBarUtil2.getStatusBarHeight(getActivity());
        attentLin.setPadding(0, statusBarHeight, 0, 0);

        //列表刷新
        attentSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page+"");
                mPresenter.getFollowData(hashMap);

            }
        });
        attentSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                 page=1;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page+"");
                mPresenter.getFollowData(hashMap);

            }
        });


        //点击弹出评论框
        followAdapter.setOnClickLinstener(new FollowAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion,String type) {
                switch (type) {
                    //评论
                    case "comment":
                        CommentFragment commentBottomSheetDialogFragment = new CommentFragment(followAdapter.mList.get(posstion).getAid() + "", followAdapter.mList.get(posstion).getComment()+"");
                        commentBottomSheetDialogFragment.show(getFragmentManager(), "");
                        break;
                        //点赞
                    case "like":
                        //进行动画

                    case "rela":
                        //播放视频
                        Intent intent = new Intent(getActivity(),VideosActivity.class);
                        intent.putExtra("url",followAdapter.mList.get(posstion).getVideo());
                        startActivity(intent);
                        break;
                }

            }
        });

        attentRel.addOnScrollListener(new RecyclerView.OnScrollListener() {
            AutoPlayTool autoPlayTool=new AutoPlayTool(60,1);
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    autoPlayTool.onActiveWhenNoScrolling(recyclerView);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                autoPlayTool.onScrolledAndDeactivate();
            }
        });
        //点击点赞的动画
      /*  attentRel.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
              *//*  Jzvd jzvd = view.findViewById(R.id.follow_video);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        jzvd.startVideo();
                    }
                }*//*
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.follow_video);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.goOnPlayOnPause();
                    }
                }
            }
        });*/



    }

    @Override
    public void onResume() {
        super.onResume();
        JzvdStd.goOnPlayOnResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        JzvdStd.goOnPlayOnPause();
    }

   public void backPressed(){

   }


    @Override
    protected void initView() {

        attentRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<FollowListBeans.DataBean> dataBeans = new ArrayList<>();
        followAdapter = new FollowAdapter(dataBeans);
        attentRel.setAdapter(followAdapter);
        attentRel.setItemViewCacheSize(100);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page",page+"");
        mPresenter.getFollowData(hashMap);

    }

    @Override
    protected DynamicPresenter preparePresenter() {
        return new DynamicPresenter();
    }

    @Override
    protected int createLayout() {
        return R.layout.attention;
    }

    @Override
    public void listSuccess(String success) {

    }

    @Override
    public void followList(String success) {
        attentSmart.finishLoadMore();
        attentSmart.finishRefresh();

        FollowListBeans followListBeans = new Gson().fromJson(success, FollowListBeans.class);
        List<FollowListBeans.DataBean> data = followListBeans.getData();
        if(page==1){
            followAdapter.mList.clear();
        }
        followAdapter.mList.addAll(data);
       followAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        JzvdStd.releaseAllVideos();
    }

    @Override
    public void error(String error) {

    }
}

