package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.CountDownTimerUtils;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.mInterface.mine.VerifyIn;
import com.ajiani.maidahui.presenters.mine.VerifyPresenter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VerifyActivity extends BaseActivity<VerifyIn.verifyView, VerifyPresenter> implements VerifyIn.verifyView {
    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.changep_tv)
    TextView changepTv;
    @BindView(R.id.changep_edp)
    EditText changepEdp;
    @BindView(R.id.changep_edv)
    EditText changepEdv;
    @BindView(R.id.changephone_getv)
    TextView changephoneGetv;
    @BindView(R.id.changep_bt)
    TextView changepBt;
    private String verifytype;
    private Drawable background;
    private Drawable drawable;
    private String codeID;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        background = changepBt.getBackground();
        drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
        changepBt.setBackground(drawable);
        changepBt.setEnabled(false);

        changepEdv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(changepEdv.getText().toString().length()>3){
                    background = changepBt.getBackground();
                    drawable = DrawUtils.setSolid(R.color.Thme, background);
                    changepBt.setBackground(drawable);
                    changepBt.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        verifytype = bundle.getString("verifytype");
        changepEdp.setFocusableInTouchMode(false);//不可编辑
        changepEdp.setKeyListener(null);//不可粘贴，长按不会弹出粘贴框
        changepEdp.setClickable(false);//不可点击，但是这个效果我这边没体现出来，不知道怎没用
        changepEdp.setFocusable(false);//不可编辑
        changepEdv.setInputType(InputType.TYPE_CLASS_NUMBER);
        if (verifytype.equals("phone")) {
            title.setText("手机号验证");
            String phone = (String) SPUtils.get(this, "phone", "");
            changepEdp.setText(phone);
            changepTv.setText("手机号");
            changepEdp.setInputType(InputType.TYPE_CLASS_NUMBER);
            changepEdp.setHint("请输入手机号");
        } else {
            String email = (String) SPUtils.get(this, "email", "");
            changepEdp.setHint("请输入邮箱账号");
            changepEdp.setText(email);
            //不可編輯
            title.setText("邮政验证");
            changepTv.setText("邮箱账号");
        }

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_verify;
    }
    public final String PHONE_REGEX_MOBILE = "^[1][0-9][0-9]{9}$";
    public boolean isMobile(@NonNull EditText view) {
        String s = view.getText().toString();
        return Pattern.matches(PHONE_REGEX_MOBILE, s);
    }
    /**
     * 判断邮箱格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @OnClick({R.id.back, R.id.changephone_getv, R.id.changep_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.changephone_getv:
                String changeText = changepEdp.getText().toString();
                //获取验证码 .判断时输入是否正确
                if(verifytype.equals("phone")){
                   if(isMobile(changepEdp)){
                       HashMap<String, String> hashMap = new HashMap<>();
                       hashMap.put("mobile",changeText);
                       mPresenter.phoneData(hashMap);
                       CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(changephoneGetv, 60000, 1000,null); //倒计时1分钟
                       mCountDownTimerUtils.start();
                   }else{
                       Toast.makeText(this, "请输入正确的手机 号", Toast.LENGTH_SHORT).show();
                   }
                }else{
                    if(isEmail(changeText)){
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("email",changeText);
                        hashMap.put("scene","validate");
                        mPresenter.mailData(hashMap);
                        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(changephoneGetv, 60000, 1000,null); //倒计时1分钟
                        mCountDownTimerUtils.start();
                    }else{
                        Toast.makeText(this, "请输入正确的邮箱号", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.changep_bt:
                String verify= changepEdv.getText().toString();
                String changeText1 = changepEdp.getText().toString();
                if(verifytype.equals("phone")){
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("mobile",changeText1);
                    hashMap.put("code",verify);
                    hashMap.put("code_id",codeID);
                    mPresenter.verifyData(hashMap);
                }else{
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("code",verify);
                    hashMap.put("code_id",codeID);
                    hashMap.put("email",changeText1);
                    mPresenter.verifyData(hashMap);
                }

                break;
        }
    }

    @Override
    protected VerifyPresenter preparePresenter() {
        return new VerifyPresenter();
    }

    @Override
    public void mailVerify(String success) {
        JSONObject jsonObject = JSON.parseObject(success);
        String data = jsonObject.getString("data");
        jsonObject= JSON.parseObject(data);
        codeID = jsonObject.getString("code_id");
    }

    @Override
    public void phoneVerify(String success) {
        JSONObject jsonObject = JSON.parseObject(success);
        String data = jsonObject.getString("data");
        jsonObject= JSON.parseObject(data);
        codeID = jsonObject.getString("code_id");
    }

    @Override
    public void verifySuccess(String success) {
        JSONObject jsonObject = JSON.parseObject(success);
        String data = jsonObject.getString("data");
        jsonObject= JSON.parseObject(data);
        String token = jsonObject.getString("token");
        SPUtils.put(this,"token",token);

        //验证成功
        Bundle bundle = new Bundle();
        bundle.putInt("isForget",1);
        JumpUtils.gotoActivity(this,SetPayPass2.class,bundle);
    }
}
