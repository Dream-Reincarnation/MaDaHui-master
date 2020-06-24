package com.ajiani.maidahui.fragment.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.adapter.dynamic.AttentAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.dynamic.VideoListBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.dynamic.DynamicIn;
import com.ajiani.maidahui.presenters.dynamic.DynamicPresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class CityFragment extends BaseFragment<DynamicIn.videoListView, DynamicPresenter> implements DynamicIn.videoListView, TencentLocationListener {
    @BindView(R.id.attent_rel)
    RecyclerView attentRel;

    @BindView(R.id.attent_smart)
    SmartRefreshLayout attentSmart;
    int page = 1;
    private AttentAdapter attentAdapter;
    private String lat;
    private String lon;
    private int time = 500;

    @Override
    public void onResume() {
        super.onResume();
        Log.d("123", "onResume");
        boolean userVisibleHint = getUserVisibleHint();
        if (userVisibleHint) {
            attentSmart.autoRefresh(500);
        }
    }

    @Override
    protected void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", page + "");
        hashMap.put("geohash", "3");
        hashMap.put("longitude", HomeActivity.lon);
        hashMap.put("latitude", HomeActivity.lat);
        mPresenter.getListData(hashMap);
        attentAdapter.setOnLike(new AttentAdapter.onLikeListener() {
            @Override
            public void onLike(int posstion) {
                String video_type = attentAdapter.mList.get(posstion).getVideo_type();
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < attentAdapter.mList.size(); i++) {
                    strings.add(attentAdapter.mList.get(i).getAid() + "");
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("list", strings);

                bundle.putString("posstion", posstion + "");

                //视频的ID

                if (video_type.equals("video")) {
                    JumpUtils.gotoActivity(getActivity(), Main3Activity.class, bundle);
                    EventBus.getDefault().postSticky(attentAdapter.mList);
//                    JumpUtils.gotoActivity(getActivity(), ViewPagerLayoutManagerActivity.class,bundle);
                }
            }
        });
    }
/*    ///////////////////生命周期
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("123","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("123","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("123","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("123","onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("123", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("123", "onStop");
    }*/

    @Override
    protected void initView() {
        TencentLocationRequest tencentLocationRequest = TencentLocationRequest.create();
        tencentLocationRequest.setInterval(30000).setRequestLevel(3).setAllowCache(true);
        TencentLocationManager locationManager = TencentLocationManager.getInstance(getActivity());
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        attentRel.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        attentAdapter = new AttentAdapter(dataBeans);
        attentRel.setAdapter(attentAdapter);

        attentSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                attentAdapter.mList.clear();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page + "");
                hashMap.put("geohash", "3");
                hashMap.put("longitude", HomeActivity.lon);
                hashMap.put("latitude", HomeActivity.lat);
                mPresenter.getListData(hashMap);
            }
        });

        attentSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", page + "");
                hashMap.put("geohash", "3");
                hashMap.put("longitude", HomeActivity.lon);
                hashMap.put("latitude", HomeActivity.lat);
                mPresenter.getListData(hashMap);
            }
        });
    }

    @Override
    protected DynamicPresenter preparePresenter() {
        return new DynamicPresenter();
    }

    @Override
    protected int createLayout() {
        return R.layout.find;
    }

    @Override
    public void listSuccess(String success) {
        VideoListBean videoListBean = new Gson().fromJson(success, VideoListBean.class);
        List<VideoInfoBean.DataBean> data = videoListBean.getData();
        if (data != null && data.size() > 0) {
            attentAdapter.mList.addAll(data);
            attentAdapter.notifyDataSetChanged();
        } else {
            //Toast.makeText(mActivity, "没有数据", Toast.LENGTH_SHORT).show();
        }
        if (attentSmart != null) {
            attentSmart.finishLoadMore();
            attentSmart.finishRefresh();
        }


    }

    @Override
    public void followList(String success) {

    }

    @Override
    public void error(String error) {

    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (TencentLocation.ERROR_OK == i) {
            // 定位成功
            if (tencentLocation != null) {
//                维度
                lat = String.valueOf(tencentLocation.getLatitude());
//                精度
                lon = String.valueOf(tencentLocation.getLongitude());

            }
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }
}
