package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.mine.AutonymBean;
import com.ajiani.maidahui.mInterface.mine.AutonymIn;
import com.ajiani.maidahui.presenters.mine.AutonymPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutonymActivity extends BaseActivity<AutonymIn.AutonymView, AutonymPresenter> implements AutonymIn.AutonymView {
    @BindView(R.id.autonym_back)
    ImageView autonymBack;
    @BindView(R.id.autonym_rele)
    TextView autonymRele;
    @BindView(R.id.autonym_status)
    ImageView autonymStatus;
    @BindView(R.id.autonym_text)
    TextView autonymText;
    @BindView(R.id.autonym_bt)
    TextView autonymBt;
    @BindView(R.id.autonym_atten)
    TextView autonymAtten;
    @BindView(R.id.autonym_yin)
    TextView autonymYin;

    private String true_name;
    private String reason;
    private Bundle bundle;
    private boolean isBack;

    @Override
    protected AutonymPresenter preparePresenter() {
        return new AutonymPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        isBack = bundle.getBoolean("isBack");

        String autonym = (String) SPUtils.get(this, "autonym", "");

        HashMap<String, String> hashMap = new HashMap<>();
     /*   hashMap.put("true_name", "王耀");
        hashMap.put("id_card", "130425200011193819");
        hashMap.put("id_card_a", "128");
        hashMap.put("id_card_b", "127");*/
        hashMap.put("is_all", autonym);
        mPresenter.getAutonymInfo(hashMap);
        autonymBt.setEnabled(false);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_autonym;
    }

    @Override
    public void authenticaSuccess(String success) {

    }

    @Override
    public void checkAutonym(String success) {
        autonymBt.setEnabled(true);
        reason=success;

        AutonymBean autonymBean = new Gson().fromJson(success, AutonymBean.class);
        if (autonymBean.getData() == null) {
            autonymStatus.setImageResource(R.mipmap.noattachtion);
            autonymText.setText("你尚未实名认证");
            autonymBt.setText("立即认证");

        } else {
            int status = autonymBean.getData().getStatus();
            //待审核
            if (status == 0) {
                autonymStatus.setImageResource(R.mipmap.attaching);
                autonymText.setText("认证审核中");
               autonymBt.setVisibility(View.VISIBLE);
            } else if (status == 1) {//已通过
                true_name = autonymBean.getData().getTrue_name();
                reason = autonymBean.getData().getId_card();
                autonymStatus.setImageResource(R.mipmap.aleardyattach);
                autonymText.setText("你已认证");

            } else {//已拒绝
                autonymStatus.setImageResource(R.mipmap.filedattach);
                autonymText.setText("审核失败，请:" );
                autonymAtten.setVisibility(View.VISIBLE);

            }
        }


    }

    @Override
    public void upLoadSuccess(String s) {

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.autonym_back, R.id.autonym_bt,R.id.autonym_atten})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.autonym_back:
                if(isBack){
                    JumpUtils.gotoActivity(this,SettActivity.class);
                }else{
                    finish();
                }
                finish();
                break;
            case R.id.autonym_bt:
                bundle = new Bundle();
                bundle.putString("autonym",reason);
                JumpUtils.gotoActivity(this, AutonymWinActivity.class,bundle);
                break;
            case R.id.autonym_atten:
                 bundle = new Bundle();
                bundle.putInt("release",0);
                JumpUtils.gotoActivity(this,AutonymASetting.class);
                break;

        }
    }


}
