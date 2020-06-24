package com.ajiani.maidahui.activity.chat;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.adapter.chat.ChatCommentAdapter;
import com.ajiani.maidahui.adapter.chat.LogisticsAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.chat.BoardCastMessageBean;
import com.ajiani.maidahui.mInterface.chat.BoardListIn;
import com.ajiani.maidahui.presenters.chat.BoardPresenter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatCommentActivity extends BaseActivity<BoardListIn.boardListView, BoardPresenter> implements BoardListIn.boardListView {

    @BindView(R.id.chatcom_rel)
    RecyclerView chatcomRel;
    @BindView(R.id.chatcom_smart)
    SmartRefreshLayout chatcomSmart;
    private int page=1;
    private ChatCommentAdapter chatCommentAdapter;
    //尔等休要放肆，大风起兮龙飞翔

    @Override
    public void error(String error) {

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void initData() {

        chatcomSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(ChatCommentActivity.this, "刷新页面", Toast.LENGTH_SHORT).show();
                chatcomSmart.finishRefresh(true);
            }
        });
        chatcomSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(ChatCommentActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                chatcomSmart.finishLoadMore(true);
            }
        });

        chatCommentAdapter.setOnClickLinstener(new ChatCommentAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                String video_id = chatCommentAdapter.list.get(posstion).getExtra().getVideo_id();
                ArrayList<String> strings = new ArrayList<>();
                strings.add(video_id);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("list",strings);
                bundle.putString("posstion","0");
                JumpUtils.gotoActivity(ChatCommentActivity.this, Main3Activity.class,bundle);
            }
        });

        chatCommentAdapter.setOnLongClickLinstener(new LogisticsAdapter.onLongClickLinstener() {
            @Override
            public void onLongClick(int posstion) {
                popupWindow = new PopupWindow(ChatCommentActivity.this);
                View inflate = LayoutInflater.from(ChatCommentActivity.this).inflate(R.layout.del_item, null, false);
                inflate.findViewById(R.id.del_lin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.del_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("msg_type", chatCommentAdapter.list.get(posstion).getMsg_type());
                        hashMap.put("msgid", chatCommentAdapter.list.get(posstion).getMsgid());
                        mPresenter.getDelData(hashMap);
                        popupWindow.dismiss();
                        chatCommentAdapter.list.remove(posstion);
                        chatCommentAdapter.notifyDataSetChanged();
                    }
                });
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(chatcomRel, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    @Override
    protected void initView() {
        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("msg_type","AcetexVideo,cashSuccess,cashFail,ComplaintSend,ComplaintEnd");
        hashMap.put("msg_type","AcetexVideo");
        hashMap.put("page",page+"");
        mPresenter.boardListData(hashMap);


       chatcomRel.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<BoardCastMessageBean.DataBean> dataBeans = new ArrayList<>();
        chatCommentAdapter = new ChatCommentAdapter(dataBeans);
        chatcomRel.setAdapter(chatCommentAdapter);
        chatcomSmart.setRefreshHeader(new MaterialHeader(this));
        chatcomSmart.setEnableLoadMore(true);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_chatcomment;
    }



    @OnClick({R.id.chatcom_back,R.id.chat_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatcom_back:
                finish();
                break;
            case R.id.chat_set:
                Bundle bundle = new Bundle();
                bundle.putString("type","comment");
                JumpUtils.gotoActivity(this, ServiceSettActivity.class,bundle);
                break;
        }

    }

    @Override
    public void boardListSuccess(String sucess) {
        JSONObject jsonObject = JSON.parseObject(sucess);
        String code = jsonObject.getString("data");
        if(code.length()>5){
            BoardCastMessageBean boardCastMessageBean = new Gson().fromJson(sucess, BoardCastMessageBean.class);
            List<BoardCastMessageBean.DataBean> data = boardCastMessageBean.getData();
           chatCommentAdapter.list.addAll(data);
           chatCommentAdapter.notifyDataSetChanged();
        }
        }

    @Override
    public void delMessage(String success) {

    }


    @Override
    protected BoardPresenter preparePresenter() {
        return new BoardPresenter();
    }
}
