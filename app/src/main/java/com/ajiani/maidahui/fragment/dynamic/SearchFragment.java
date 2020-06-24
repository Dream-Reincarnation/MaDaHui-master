package com.ajiani.maidahui.fragment.dynamic;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.DynamicSeacherActivity;
import com.ajiani.maidahui.adapter.dynamic.DynaSearAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.Even;
import com.ajiani.maidahui.bean.dynamic.VideoListBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.dynamic.DynamicIn;
import com.ajiani.maidahui.presenters.dynamic.DynamicPresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment<DynamicIn.videoListView, DynamicPresenter> implements DynamicIn.videoListView {
    @BindView(R.id.fragsear_rel)
    RecyclerView fragsearRel;
    @BindView(R.id.fragsear_smart)
    SmartRefreshLayout fragsearSmart;
   String  name;
  String type;
   private DynaSearAdapter dynaSearAdapter;
   int page=1;
    public SearchFragment(String name) {
        this.name = name;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            if(mPresenter!=null){

            }
        }else{

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMessage(String messsge){
      type=messsge;
       /* //进行网络解析
        HashMap<String, String> hashMap = new HashMap<>();
        if(name.equals("综合")){
            hashMap.put("order_way","all");
        }else if(name.equals("最新")){
            hashMap.put("order_way","new");
        }else if(name.equals("热门")) {
            hashMap.put("order_way","hot");
        }else if(name.equals("点击量")){
            hashMap.put("order_way","count");
        }else if(name.equals("火力")){
            hashMap.put("order_way","gift");
        }else{
            hashMap.put("order_way","all");
        }
        dynaSearAdapter.mList.clear();
        hashMap.put("keyword",messsge);
        hashMap.put("page",page+"");
        mPresenter.getListData(hashMap);*/
    }

    @Override
    protected DynamicPresenter preparePresenter() {
        return new DynamicPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onResume() {
        super.onResume();
        HashMap<String, String> hashMap = new HashMap<>();
        if(name.equals("综合")){
            hashMap.put("order_way","all");
        }else if(name.equals("最新")){
            hashMap.put("order_way","new");
        }else if(name.equals("热门")) {
            hashMap.put("order_way","hot");
        }else if(name.equals("点击量")){
            hashMap.put("order_way","count");
        }else if(name.equals("火力")){
            hashMap.put("order_way","gift");
        }else{
            hashMap.put("order_way","all");
        }
        dynaSearAdapter.mList.clear();
        hashMap.put("keyword",type);
        hashMap.put("page",page+"");
        mPresenter.getListData(hashMap);
     //   Log.i("WXY", "getMessage: ");
    }

    @Override
    protected void initData() {

      /*  DynamicSeacherActivity.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //得到输入框的内容
                String s = DynamicSeacherActivity.ed1.getText().toString();
                if(s.length()<1){

                }else{
                    Toast.makeText(mActivity, "请输入你要是搜索的内容", Toast.LENGTH_SHORT).show();
                   *//* DynamicSeacherActivity.dynaVp.setVisibility(View.VISIBLE);
                    DynamicSeacherActivity.dynaTab.setVisibility(View.VISIBLE);*//*
                   DynamicSeacherActivity.set();
                    page = 1;
                    dynaSearAdapter.mList.clear();
                    HashMap<String, String> hashMap = new HashMap<>();
                    Log.i("WXY", "onClick: "+name);
                    if(name.equals("综合")){
                        hashMap.put("order_way","all");
                        Log.i("WXY", "onClick: hot");
                    }else if(name.equals("最新")){
                        hashMap.put("order_way","new");
                    }else if(name.equals("热门")) {
                        hashMap.put("order_way","hot");
                        Log.i("WXY", "onClick: all");
                    }else if(name.equals("点击量")){
                        hashMap.put("order_way","count");
                    }else if(name.equals("火力")){
                        hashMap.put("order_way","gift");
                    }else{
                        hashMap.put("order_way","all");
                        Log.i("WXY", "onClick: gift");
                    }
                    hashMap.put("order_way","all");
                    hashMap.put("keyword",s);
                    hashMap.put("page",page+"");

                    mPresenter.getListData(hashMap);
                }
                
            }
        });*/
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        fragsearRel.setLayoutManager(gridLayoutManager);
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        dynaSearAdapter = new DynaSearAdapter(dataBeans);
        fragsearRel.setAdapter(dynaSearAdapter);

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        fragsearSmart.setRefreshHeader(new MaterialHeader(getActivity()));

        fragsearSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
            }
        });
    }

    private void get() {
        Editable text = DynamicSeacherActivity.ed1.getText();
        String s = text.toString();
        HashMap<String, String> hashMap = new HashMap<>();
        Log.i("WXY", "onClaaaaick: "+name);
        if(name.equals("综合")){
            hashMap.put("order_way","all");

        }else if(name.equals("最新")){
            hashMap.put("order_way","new");
        }else if(name.equals("热门")) {
            hashMap.put("order_way","hot");

        }else if(name.equals("点击量")){
            hashMap.put("order_way","count");
        }else if(name.equals("火力")){
            hashMap.put("order_way","gift");
        }else{
            hashMap.put("order_way","all");
        }
        hashMap.put("keyword",s);
        hashMap.put("page",page+"");

        mPresenter.getListData(hashMap);
    }

    public void onRefresh(){
       fragsearSmart.setOnRefreshListener(new OnRefreshListener() {
           @Override
           public void onRefresh(@NonNull RefreshLayout refreshLayout) {
               page = 1;
               Editable text = DynamicSeacherActivity.ed1.getText();
               String s = text.toString();
               dynaSearAdapter.mList.clear();
               HashMap<String, String> hashMap = new HashMap<>();
               if(name.equals("综合")){
                   hashMap.put("order_way","all");

               }else if(name.equals("最新")){
                   hashMap.put("order_way","new");
               }else if(name.equals("热门")) {
                   hashMap.put("order_way","hot");

               }else if(name.equals("点击量")){
                   hashMap.put("order_way","count");
               }else if(name.equals("火力")){
                   hashMap.put("order_way","gift");
               }else{
                   hashMap.put("order_way","all");
               }
               hashMap.put("keyword",s);
               hashMap.put("page",page+"");

               mPresenter.getListData(hashMap);
           }
       });
   }
    @Override
    protected int createLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void listSuccess(String success) {
        VideoListBean videoListBean = new Gson().fromJson(success, VideoListBean.class);
        List<VideoInfoBean.DataBean> data = videoListBean.getData();
        if(data!=null&&data.size()>0){
            dynaSearAdapter.mList.addAll(data);
            dynaSearAdapter.notifyDataSetChanged();
        }

        fragsearSmart.finishRefresh();
        fragsearSmart.finishLoadMore();
    }

    @Override
    public void followList(String success) {

    }


}
