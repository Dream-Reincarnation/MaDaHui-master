package com.ajiani.maidahui.activity.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.mInterface.mine.UpdataSetin;
import com.ajiani.maidahui.presenters.mine.SetInfoPresenter;
import com.ajiani.maidahui.weight.SafeKeyboard;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class SetPayPass2 extends BaseActivity<UpdataSetin.upSetView, SetInfoPresenter> implements UpdataSetin.upSetView {
    @BindView(R.id.back)
    FrameLayout paypassBack;
    @BindView(R.id.paypass)
    EditText paypass;
    @BindView(R.id.oldpass)
    EditText oldPass;
    @BindView(R.id.paypass2)
    EditText paypass2;
    @BindView(R.id.pass_lin)
    LinearLayout passLin;
    @BindView(R.id.paypass_bt)
    Button paypassBt;
    @BindView(R.id.keyboardViewPlace)
    LinearLayout keyboardViewPlace;
    @BindView(R.id.paypass_farme)
    FrameLayout paypassFarme;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.paypass_forget)
    TextView paypass_forget;

    private SafeKeyboard safeKeyboard;
    private String pass;
    private int isForget;


    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        isForget = bundle.getInt("isForget");
        if(isForget ==1){
            passLin.setVisibility(View.GONE);
            paypass_forget.setVisibility(View.GONE);
        }else{
            passLin.setVisibility(View.VISIBLE);
        }
        String pay_pass = (String) SPUtils.get(this, "pass", "");
        if(pay_pass!=null&&pay_pass.length()<5){
            passLin.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initView() {
        title.setText("设置支付密码");
        passLin.setVisibility(View.VISIBLE);
        View rootView = findViewById(R.id.paypass_farme);
        LinearLayout keyboardContainer = findViewById(R.id.keyboardViewPlace);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(this).inflate(R.layout.layout_keyboard_containor, null);
        safeKeyboard = new SafeKeyboard(getApplicationContext(), keyboardContainer,
                R.layout.layout_keyboard_containor, view.findViewById(R.id.safeKeyboardLetter).getId(), rootView);
        safeKeyboard.putEditText(oldPass.getId(),oldPass);
        safeKeyboard.putEditText(paypass2.getId(), paypass2);
        safeKeyboard.putEditText(paypass2.getId(), paypass2);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_setpaypass;
    }


    @OnClick({R.id.back, R.id.paypass_bt,R.id.paypass_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.paypass_forget:

                JumpUtils.gotoActivity(this,SetVerifyActivity.class);
                break;

            case R.id.back:
                if(passLin.getVisibility()==View.GONE){
                    finish();
                }else{
                    JumpUtils.gotoActivity(this,SettActivity.class);
                }

                break;
            case R.id.paypass_bt:
                //确定之前判断旧密码按是否相等
                String s = oldPass.getText().toString();
                String userid = (String) SPUtils.get(this, "userid", "");
                String pay_pass = (String) SPUtils.get(this, "pass", "");
                Log.i("WXY", "userInfo: "+pay_pass);
                String s1 = Md5Utils.MD5(userid + s);

                    if(pay_pass!=null&&pay_pass.length()<6||isForget==1){

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
                    }else{
                        if(!s1.equals(pay_pass)){
                            Toast.makeText(this, "输入的旧密码不正确", Toast.LENGTH_SHORT).show();
                        }else {
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
                        }
                    }



                break;
        }
    }


    @Override
    public void success(String success) {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        String userid = (String) SPUtils.get(this, "userid", "");

        String s1 = Md5Utils.MD5(userid + pass);
        SPUtils.put(this,"pass",s1);
        JumpUtils.gotoActivity(this,AccountActivity.class);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

                return super.onKeyDown(keyCode, event);



        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public void upLoadSuccess(String success) {

    }

    @Override
    protected SetInfoPresenter preparePresenter() {
        return new SetInfoPresenter();
    }
}
