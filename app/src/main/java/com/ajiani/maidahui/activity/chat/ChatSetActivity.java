package com.ajiani.maidahui.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.base.SimpleActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatSetActivity extends SimpleActivity {
    @BindView(R.id.chatact_back)
    FrameLayout chatactBack;
    @BindView(R.id.chatact_title)
    TextView chatactTitle;
    @BindView(R.id.chat_set_head)
    ImageView chatSetHead;
    @BindView(R.id.chat_set_name)
    TextView chatSetName;
    @BindView(R.id.chat_set_lin)
    LinearLayout chatSetLin;
    @BindView(R.id.chat_set_compat)
    SwitchCompat chatSetCompat;
    @BindView(R.id.chat_set_compat2)
    SwitchCompat chatSetCompat2;
    @BindView(R.id.chat_set_report)
    CardView chatSetReport;
    @BindView(R.id.chat_set_block)
    CardView chatSetBlock;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String name = bundle.getString("name");
        String head = bundle.getString("head");
        Glide.with(this).load(head).apply(new RequestOptions().circleCrop()).into(chatSetHead);
        chatSetName.setText(name);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_chatset;
    }



    @OnClick({R.id.chatact_back, R.id.chat_set_lin, R.id.chat_set_compat, R.id.chat_set_compat2, R.id.chat_set_report, R.id.chat_set_block})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatact_back:
                finish();
                break;
            case R.id.chat_set_lin:
                break;
            case R.id.chat_set_compat:
                break;
            case R.id.chat_set_compat2:
                break;
            case R.id.chat_set_report:
                //举报页面
                Bundle bundle = new Bundle();
                JumpUtils.gotoActivity(this,ChatReportAct.class,bundle);
                break;
            case R.id.chat_set_block:
                break;
        }
    }
}
