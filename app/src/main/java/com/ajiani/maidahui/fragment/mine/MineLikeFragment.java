package com.ajiani.maidahui.fragment.mine;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.adapter.mine.LikeVideoAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.mine.LikeVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.mine.MineInfo;
import com.ajiani.maidahui.presenters.mine.MinePresenter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MineLikeFragment extends BaseFragment<MineInfo.mineView, MinePresenter> implements MineInfo.mineView {
    @BindView(R.id.like_rel)
    RecyclerView likeRel;

    private int page=1;
    private LikeVideoAdapter likeVideoAdapter;
    private ArrayList<String> list;

    @Override
    protected MinePresenter preparePresenter() {
        return new MinePresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        likeVideoAdapter = new LikeVideoAdapter(dataBeans);
        likeRel.setAdapter(likeVideoAdapter);
        likeRel.setLayoutManager(new GridLayoutManager(getActivity(),3){
                @Override
                public boolean canScrollVertically() {
                return false;

            }

        });
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page",page+"");
        hashMap.put("is_left","0");
        mPresenter.getLoveList(hashMap);



        likeVideoAdapter.setOnClickLinstener(new LikeVideoAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                if(String.valueOf(likeVideoAdapter.mList.get(posstion).getAid()).equals("0")){
                    //弹窗删除
                    PopupWindow popupWindow = new PopupWindow(getActivity());
                    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                    View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.phone_item, null, false);
                    popupWindow.setContentView(inflate);
                    TextView changeType = inflate.findViewById(R.id.popchange_type);
                    TextView changeTv = inflate.findViewById(R.id.popchange_tv);
                    inflate.findViewById(R.id.popchange_cancle).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });
                    changeType.setText("视频已失效");
                    changeTv.setText("是否将他从你的喜欢列表删除");
                    TextView changeQuery = inflate.findViewById(R.id.popchange_query);
                    changeQuery.setText("删除");
                    int finalPosstion = posstion;
                    changeQuery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HttpUtils.videostar(likeVideoAdapter.mList.get(finalPosstion).getAid()+"");
                            likeVideoAdapter.mList.remove(finalPosstion);
                            popupWindow.dismiss();
                            likeVideoAdapter.notifyDataSetChanged();
                        }
                    });
                    popupWindow.showAtLocation(likeRel, Gravity.CENTER,0,0);
                }else{
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
                        EventBus.getDefault().postSticky(likeVideoAdapter.mList);
                        // JumpUtils.gotoActivity(getActivity(), MineProductActivity.class, bundle);
                    }
                }

            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_like;
    }

    @Override
    public void videoSuccess(String string) {

    }

    @Override
    public void userInfo(String success) {

    }

    @Override
    public void loveListSuccess(String success) {
        //
        LikeVideoBean likeVideoBean = new Gson().fromJson(success, LikeVideoBean.class);
        List<VideoInfoBean.DataBean> data = likeVideoBean.getData();

        if(data!=null){
            likeVideoAdapter.mList.addAll(data);
            likeVideoAdapter.notifyDataSetChanged();
            list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if (!String.valueOf(data.get(i).getAid()).equals("0")) {
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
