package com.ajiani.maidahui.activity.mine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.mInterface.mine.UpdataSetin;
import com.ajiani.maidahui.presenters.mine.SetInfoPresenter;
import com.ajiani.maidahui.weight.SafeKeyboard;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPayPass extends BaseActivity<UpdataSetin.upSetView, SetInfoPresenter> implements UpdataSetin.upSetView {
    @BindView(R.id.back)
    FrameLayout paypassBack;
    @BindView(R.id.paypass)
    EditText paypass;
    @BindView(R.id.paypass2)
    EditText paypass2;
    @BindView(R.id.paypass_bt)
    Button paypassBt;
    @BindView(R.id.keyboardViewPlace)
    LinearLayout keyboardViewPlace;
    @BindView(R.id.paypass_farme)
    FrameLayout paypassFarme;
    private SafeKeyboard safeKeyboard;
    private String pass;


    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        View rootView = findViewById(R.id.paypass_farme);
        LinearLayout keyboardContainer = findViewById(R.id.keyboardViewPlace);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(this).inflate(R.layout.layout_keyboard_containor, null);
        safeKeyboard = new SafeKeyboard(getApplicationContext(), keyboardContainer,
                R.layout.layout_keyboard_containor, view.findViewById(R.id.safeKeyboardLetter).getId(), rootView);
        safeKeyboard.putEditText(paypass.getId(), paypass);
        safeKeyboard.putEditText(paypass2.getId(), paypass2);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_setpaypass;
    }


    @OnClick({R.id.back, R.id.paypass_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.paypass_bt:
                //次改支付密码 判长度，是否相等
                pass = paypass.getText().toString();
                String pass2 = paypass2.getText().toString();
                if (pass.equals(pass2)) {
                    if (pass.length() < 6 || pass.length() > 32) {
                        Toast.makeText(this, "密码至少6-32位", Toast.LENGTH_SHORT).show();
                    } else {
                        //进行更改I密码

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("pay_pass", pass);
                       /* Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();*/
                        mPresenter.getData(hashMap);

                    }
                } else {
                    Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void success(String success) {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        SPUtils.put(this,"pass",pass);
        finish();
    }

    @Override
    public void upLoadSuccess(String success) {

    }

    @Override
    protected SetInfoPresenter preparePresenter() {
        return new SetInfoPresenter();
    }
}
