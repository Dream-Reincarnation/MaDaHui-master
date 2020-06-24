package com.ajiani.maidahui.fragment.dynamic;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.activity.dynamic.PersonActivity;
import com.ajiani.maidahui.adapter.mine.LikeVideoAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.mine.LikeVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.mine.MineInfo;
import com.ajiani.maidahui.presenters.mine.MinePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class DyLikeFragment extends BaseFragment<MineInfo.mineView, MinePresenter> implements MineInfo.mineView {


    @BindView(R.id.like_rel)
    RecyclerView likeRel;
    private int page=1;
    private LikeVideoAdapter likeVideoAdapter;
    private ArrayList<String> list;
    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        likeVideoAdapter = new LikeVideoAdapter(dataBeans);
        likeRel.setAdapter(likeVideoAdapter);
        likeRel.setLayoutManager(new GridLayoutManager(getActivity(),3));
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page",page+"");
        hashMap.put("is_left","0");
        hashMap.put("user_id", PersonActivity.id);
        mPresenter.getLoveList(hashMap);
        likeVideoAdapter.setOnClickLinstener(new LikeVideoAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                Bundle bundle = new Bundle();
                bundle.putString("id", likeVideoAdapter.mList.get(posstion).getAid() + "");
                String video_type = likeVideoAdapter.mList.get(posstion).getVideo_type();

                for(int i=0;i<list.size();i++){
                    if(list.get(i).equals(likeVideoAdapter.mList.get(posstion).getAid()+"")){
                        posstion=i;
                        break;
                    }
                }
                if (video_type.equals("video")) {
                    //视频
                    Bundle bundle1 = new Bundle();
                    bundle1.putStringArrayList("list", list);
                    bundle1.putString("posstion", posstion+"");
                    JumpUtils.gotoActivity(getActivity(), Main3Activity.class,bundle1);
                    // JumpUtils.gotoActivity(getActivity(), MineProductActivity.class, bundle);
                }
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_like;
    }

    @Override
    protected MinePresenter preparePresenter() {
        return new MinePresenter();
    }

    @Override
    public void videoSuccess(String string) {

    }

    @Override
    public void userInfo(String success) {

    }

    @Override
    public void loveListSuccess(String success) {
        LikeVideoBean likeVideoBean = new Gson().fromJson(success, LikeVideoBean.class);
        List<VideoInfoBean.DataBean> data = likeVideoBean.getData();
        if(data!=null){
            likeVideoAdapter.mList.addAll(data);
            likeVideoAdapter.notifyDataSetChanged();
            list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getVideo_type().equals("video")) {
                    list.add(data.get(i).getAid() + "");
                }
            }
        }else{

        }

    }

    @Override
    public void getCountSuccess(String success) {

    }
}
