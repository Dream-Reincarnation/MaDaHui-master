package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.CountDownTimerUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.login.VeriftyBean;
import com.ajiani.maidahui.bean.mine.UserInfo;
import com.ajiani.maidahui.mInterface.mine.ChangePhoneIn;
import com.ajiani.maidahui.presenters.mine.ChangePresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindPhoneActivity extends BaseActivity<ChangePhoneIn.changPhoneView, ChangePresenter> implements ChangePhoneIn.changPhoneView {

    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.changephone_tip)
    TextView changephoneTip;
    @BindView(R.id.changep_edp)
    EditText changepEdp;
    @BindView(R.id.changep_edv)
    EditText changepEdv;
    @BindView(R.id.changephone_getv)
    TextView changephoneGetv;
    @BindView(R.id.changep_bt)
    TextView changepBt;
    private String code_id;
    private String code;
    private String TAG = "wxy";


    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        Drawable background = changepBt.getBackground();
        Drawable drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
        changepBt.setBackground(drawable);
        changepBt.setEnabled(false);
        changepEdv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (changepEdv.getText().toString().length() > 3) {
                    Drawable background = changepBt.getBackground();
                    Drawable drawable = DrawUtils.setSolid(R.color.Thme, background);
                    changepBt.setBackground(drawable);
                    changepBt.setEnabled(true);
                } else {
                    Drawable background = changepBt.getBackground();
                    Drawable drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
                    changepBt.setBackground(drawable);
                    changepBt.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initView() {
        changepEdp.setFocusable(true);
        changepEdp.setEnabled(true);
        changepEdp.setHint("请输入更换的手机号");
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        code = bundle.getString("vertify");
        title.setText("更换手机号");
        changephoneTip.setText("更换手机号，下次登录使用新的手机号登录。当前绑定手机号为\u0003158****8888");
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_changephone;
    }

    public final String PHONE_REGEX_MOBILE = "^[1][0-9][0-9]{9}$";

    public boolean isMobile(@NonNull EditText view) {
        String s = view.getText().toString();
        return Pattern.matches(PHONE_REGEX_MOBILE, s);
    }

    @OnClick({R.id.back, R.id.changephone_getv, R.id.changep_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.changephone_getv:
                //验证手机号是否正确
                if (isMobile(changepEdp)) {
                    //发送验证码
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(changephoneGetv, 60000, 1000, null); //倒计时1分钟
                    mCountDownTimerUtils.start();
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("mobile", changepEdp.getText().toString());
                    mPresenter.getVertifyData(hashMap);
                } else {
                    Toast.makeText(this, "手机号不正确", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.changep_bt:
                HashMap<String, String> map = new HashMap<>();
                if (changepEdv.getText().toString().length() > 3) {
                    String success = (String) SPUtils.get(this, "userinfo", "");
                    if (success.length() > 5) {
                        UserInfo userInfo = new Gson().fromJson(success, UserInfo.class);
                        String mobile = userInfo.getData().getMobile();
                        map.put("mobile", mobile);
                    }
                    map.put("code_id", HttpUtils.code_id);
                    map.put("code", code);
                    map.put("new_mobile", changepEdp.getText().toString());
                    map.put("new_code_id", code_id);
                    map.put("new_code", changepEdv.getText().toString());
                    mPresenter.getChangeData(map);
                } else {
                    Toast.makeText(this, "请输入验证的验证码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected ChangePresenter preparePresenter() {
        return new ChangePresenter();
    }

    @Override
    public void vertifySuccess(String success) {
        Gson gson = new Gson();
        VeriftyBean veriftyBean = gson.fromJson(success, VeriftyBean.class);
        code_id = veriftyBean.getData().getCode_id();
    }

    @Override
    public void changeSuccess(String success) {

    }
}
