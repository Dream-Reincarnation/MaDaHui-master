package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.mine.AutonymBean;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutonymWinActivity extends SimpleActivity {
    @BindView(R.id.report_back)
    FrameLayout reportBack;
    @BindView(R.id.autonym_etname)
    TextView autonymEtname;
    @BindView(R.id.autonym_etnum)
    TextView autonymEtnum;
    @BindView(R.id.autonym_time)
    TextView autonymTime;
    @BindView(R.id.autonym_release)
    TextView autonymRelease;
    @BindView(R.id.autonym_yin)
    TextView autonymYin;
    private AutonymBean autonymBean;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String autonym = bundle.getString("autonym");
        autonymBean = new Gson().fromJson(autonym, AutonymBean.class);
        if(autonymBean.getData().getStatus()==1){
            autonymRelease.setVisibility(View.VISIBLE);
        }
       String name =  "*" + autonymBean.getData().getTrue_name().substring(1, autonymBean.getData().getTrue_name().length());
        autonymEtname.setText(name);
        String num = autonymBean.getData().getId_card().substring(0, 1) + "****************" + autonymBean.getData().getId_card().substring(17, 18);
        autonymEtnum.setText(num);
        autonymTime.setText(autonymBean.getData().getId_card_exp());
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_autonym_win;
    }


    @OnClick({R.id.report_back, R.id.autonym_release, R.id.autonym_yin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.report_back:
                finish();
                break;
            case R.id.autonym_release:
                Bundle bundle = new Bundle();
                bundle.putInt("release",1);
                bundle.putString("name",autonymBean.getData().getTrue_name());
                bundle.putString("id_card",autonymBean.getData().getId_card_exp());
                JumpUtils.gotoActivity(this,AutonymASetting.class,bundle);
                break;
            case R.id.autonym_yin:
                //查看隐私协议
                break;
        }
    }
}
