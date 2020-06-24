package com.ajiani.maidahui.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PasswActivity extends SimpleActivity {

    @BindView(R.id.pass_back)
    ImageView passBack;
    @BindView(R.id.pass_phone)
    EditText passPhone;
    @BindView(R.id.pass_psw)
    EditText passPsw;
    @BindView(R.id.pass_bt)
    Button passBt;
    @BindView(R.id.goto_find)
    TextView gotoFind;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int createLayout() {
        return R.layout.actvity_password;
    }

    @OnClick({R.id.pass_back, R.id.pass_bt, R.id.goto_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pass_back:
                finish();
                break;
            case R.id.pass_bt:
                break;
            case R.id.goto_find:
                JumpUtils.gotoActivity(this,FindPassActivity.class);
                break;
        }
    }
}
