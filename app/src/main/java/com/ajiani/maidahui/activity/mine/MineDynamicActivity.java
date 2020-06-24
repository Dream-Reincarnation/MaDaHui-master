package com.ajiani.maidahui.activity.mine;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.adapter.mine.MineDynaAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.mine.MineInfo;
import com.ajiani.maidahui.presenters.mine.MinePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineDynamicActivity extends BaseActivity<MineInfo.mineView, MinePresenter> implements MineInfo.mineView {
    @BindView(R.id.minedyna_back)
    ImageView minedynaBack;
    @BindView(R.id.minedyna_rel)
    RecyclerView minedynaRel;
    @BindView(R.id.minedyna_lin)
    LinearLayout minedynaLin;
    private MineDynaAdapter mineDynaAdapter;

    @Override
    protected MinePresenter preparePresenter() {
        return new MinePresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page","1");
        mPresenter.getVideo(hashMap);

        mineDynaAdapter.setOnClickLinstener(new MineDynaAdapter.onClickLinstener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(int posstion) {
                //去掉转视频和图片
                Bundle bundle = new Bundle();
                bundle.putString("id", mineDynaAdapter.mList.get(posstion).getAid() + "");
                String video_type = mineDynaAdapter.mList.get(posstion).getVideo_type();
                if(video_type.equals("video")){
                    JumpUtils.gotoActivity(MineDynamicActivity.this, MineProductActivity.class, bundle);
                }else{

                }
            }
        });
    }

    @Override
    protected void initView() {
         //得到我发布的视频
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        minedynaRel.setLayoutManager(new LinearLayoutManager(this));
        mineDynaAdapter = new MineDynaAdapter(dataBeans);
        minedynaRel.setAdapter(mineDynaAdapter);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_minedynamic;
    }

    @Override
    public void videoSuccess(String string) {
        MineVideoBean mineVideoBean = new Gson().fromJson(string, MineVideoBean.class);
        List<VideoInfoBean.DataBean> data = mineVideoBean.getData();
        if(data.size()==0){
            minedynaLin.setVisibility(View.VISIBLE);
        }else{
            minedynaLin.setVisibility(View.GONE);
            mineDynaAdapter.mList.addAll(data);
            mineDynaAdapter.notifyDataSetChanged();
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


    @OnClick(R.id.minedyna_back)
    public void onViewClicked() {
        finish();
    }


}
