package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookAutonyma extends SimpleActivity {
    @BindView(R.id.lookauto_back)
    ImageView lookautoBack;
    @BindView(R.id.lookauto_name)
    TextView lookautoName;
    @BindView(R.id.lookauto_num)
    TextView lookautoNum;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String name = bundle.getString("name");
        String reason = bundle.getString("reason");
        lookautoName.setText(name);
        lookautoNum.setText(reason);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_lookautonyma;
    }



    @OnClick(R.id.lookauto_back)
    public void onViewClicked() {
        finish();
    }
}
