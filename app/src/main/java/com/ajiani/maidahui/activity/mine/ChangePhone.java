package com.ajiani.maidahui.activity.mine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ajiani.maidahui.R;

import com.ajiani.maidahui.Utils.CountDownTimerUtils;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.mine.UserInfo;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhone extends BaseActivity {
    @BindView(R.id.back)
    FrameLayout changepBack;
    @BindView(R.id.changep_edp)
    EditText changepEdp;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.changep_edv)
    EditText changepEdv;
    @BindView(R.id.changephone_getv)
    TextView changephoneGetv;
    @BindView(R.id.changep_bt)
    TextView bt;
    private UserInfo userInfo;

    @Override
    protected BasePresenterImp preparePresenter() {
        return null;
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

            Drawable background = bt.getBackground();
            Drawable drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
            bt.setBackground(drawable);
    }

    @Override
    protected void initView() {
        changepEdp.setFocusable(false);
        title.setText("验证账号");
        String success = (String) SPUtils.get(this, "userinfo", "");
        if (success.length() > 5) {
            userInfo = new Gson().fromJson(success, UserInfo.class);
            String mobile = userInfo.getData().getMobile();
            //方法二
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);

            changepEdp.setText(mobile);
        }
        changepEdv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(changepEdv.getText().toString().length()>3){
                    Drawable background = bt.getBackground();
                    Drawable drawable = DrawUtils.setSolid(R.color.Thme, background);
                    bt.setBackground(drawable);
                    bt.setEnabled(true);
                }else{
                    Drawable background = bt.getBackground();
                    Drawable drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
                    bt.setBackground(drawable);
                    bt.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_changephone;
    }
/*
    public final String PHONE_REGEX_MOBILE = "^[1][0-9][0-9]{9}$";

    public boolean isMobile(@NonNull EditText view) {
        String s = view.getText().toString();
        return Pattern.matches(PHONE_REGEX_MOBILE, s);
    }*/

    @OnClick({R.id.back, R.id.changephone_getv, R.id.changep_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.changephone_getv:
                //发送验证码
                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(changephoneGetv, 60000, 1000,null); //倒计时1分钟
                mCountDownTimerUtils.start();
                HttpUtils.sendVertify(userInfo.getData().getMobile());
                break;
            case R.id.changep_bt:
                //点击提交 跳转到更换到手机号
                Bundle bundle = new Bundle();
                bundle.putString("vertify",changepEdv.getText().toString());
                JumpUtils.gotoActivity(this,BindPhoneActivity.class,bundle);
                break;
        }
    }
}
