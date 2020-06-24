package com.ajiani.maidahui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetVerifyActivity extends SimpleActivity {
    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.set_mail_verify)
    LinearLayout setMailVerify;
    @BindView(R.id.set_phone_verify)
    LinearLayout setPhoneVerify;
    private Bundle bundle;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        title.setText("安全验证");
       String email = (String) SPUtils.get(this, "email", "");
       if(email.length()<9){
            setMailVerify.setVisibility(View.GONE);

       }else{
           setMailVerify.setVisibility(View.VISIBLE);
       }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_setverify;
    }



    @OnClick({R.id.back, R.id.set_mail_verify, R.id.set_phone_verify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.set_mail_verify:
                bundle = new Bundle();
                bundle.putString("verifytype","email");
                //选择邮箱
                JumpUtils.gotoActivity(this,VerifyActivity.class,bundle);
                break;
            case R.id.set_phone_verify:
                //选择手机号
                bundle = new Bundle();
                bundle.putString("verifytype","phone");
                //选择邮箱
                JumpUtils.gotoActivity(this,VerifyActivity.class,bundle);
                break;
        }
    }
}
