package com.ajiani.maidahui.activity.login;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.CountDownTimerUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.login.VeriftyBean;
import com.ajiani.maidahui.mInterface.FindPass;
import com.ajiani.maidahui.presenters.FindPswPresenter;
import com.ajiani.maidahui.weight.VerifyCodeView;
import com.ajiani.maidahui.weight.verfity.PhoneCode;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPassActivity extends BaseActivity<FindPass.findPassView, FindPswPresenter> implements FindPass.findPassView {
    @BindView(R.id.pass_back)
    ImageView passBack;
    @BindView(R.id.find_phone)
    EditText findPhone;
    @BindView(R.id.find_verifty)
    PhoneCode findVerifty;
    @BindView(R.id.find_send)
    TextView findSend;
    @BindView(R.id.find_pass)
    EditText findPass;
    @BindView(R.id.find_pass2)
    EditText findPass2;
    @BindView(R.id.find_bt)
    Button findBt;
    private String code_id;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_findpsw;
    }



    @OnClick({R.id.pass_back, R.id.find_send, R.id.find_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pass_back:
                finish();
                break;
            case R.id.find_send:
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(findSend, 60000, 1000,null); //倒计时1分钟
                mCountDownTimerUtils.start();
                //网络请求
                //得到验证码 手机号
                String s = findPhone.getText().toString();

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("mobile",s);
                mPresenter.setData2(hashMap);

                break;
            case R.id.find_bt:
                //得到验证码，手机号
                String phoneCode = findVerifty.getPhoneCode();
                String s1 = findPhone.getText().toString();
                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put("mobile",s1);
                hashMap2.put("code_id",code_id);
                hashMap2.put("code",phoneCode);
                mPresenter.setData(hashMap2);
                break;
        }
    }

    @Override
    protected FindPswPresenter preparePresenter() {
        return new FindPswPresenter();
    }

    @Override
    public void success(String str) {

    }

    @Override
    public void successVerfity(String success) {
        VeriftyBean veriftyBean = new Gson().fromJson(success, VeriftyBean.class);
        code_id = veriftyBean.getData().getCode_id();
    }

    @Override
    public void error(String error) {

    }
}
