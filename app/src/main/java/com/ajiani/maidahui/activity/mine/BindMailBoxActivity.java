package com.ajiani.maidahui.activity.mine;

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

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.CountDownTimerUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.mInterface.mine.MailBoxIn;
import com.ajiani.maidahui.presenters.mine.MailBoxPersenter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindMailBoxActivity extends BaseActivity<MailBoxIn.MainView, MailBoxPersenter> implements MailBoxIn.MainView {


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
    private String TAG="wxy";
    private String code_id;

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
                if(changepEdv.getText().toString().length()>3){
                    Drawable background = changepBt.getBackground();
                    Drawable drawable = DrawUtils.setSolid(R.color.Thme, background);
                    changepBt.setBackground(drawable);
                    changepBt.setEnabled(true);
                }else{
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
        String email = (String) SPUtils.get(this, "email", "");
        if(email.length()>4){
            title.setText("更换邮箱");
            changephoneTip.setText("更换邮箱，当前绑定邮箱号为"+getmail(email));
        }else{
            changephoneTip.setText("为了您的账户安全，需要验证您的身份，将会发送一封验证邮箱至您所填邮箱请登录您的邮箱查收验证码");
            title.setText("绑定邮箱");
        }

    }
    public String getmail(String email){
        StringBuffer stringBuffer = new StringBuffer();
        boolean b=false;
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if(i==0){
                stringBuffer.append(c);
                continue;
            }
            if(b){
                stringBuffer.append(c);
                continue;
            }
            if(String.valueOf(c).equals("@")){
                stringBuffer.append(c);
                b=true;
            }else{
                stringBuffer.append("*");
            }
        }
        return stringBuffer.toString();
    }
    @Override
    protected int createLayout() {
        return R.layout.activity_bindmail;
    }

    @Override
    protected MailBoxPersenter preparePresenter() {
        return new MailBoxPersenter();
    }

    @Override
    public void mailVertifySuccess(String success) {
        JSONObject jsonObject = JSON.parseObject(success);
        String code = jsonObject.getString("data");
        code_id = JSON.parseObject(code).getString("code_id");

    }

    @Override
    public void mailbindSuccess(String success) {
        SPUtils.put(this,"email",changepEdp.getText().toString());
        Toast.makeText(this, "绑定成功}", Toast.LENGTH_SHORT).show();
        finish();
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
                //发送验证码 验证邮箱是否正确
                String s = changepEdp.getText().toString();
                if(isEmail(s)){
                     //发送验证码
                   HashMap<String, String> hashMap = new HashMap<>();
                   hashMap.put("email",s);
                   hashMap.put("scene","bind");
                   mPresenter.mailVertifyData(hashMap);
                    CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(changephoneGetv, 60000, 1000,null); //倒计时1分钟
                    mCountDownTimerUtils.start();
                }else{
                    Toast.makeText(this, "请输入正确的邮箱账号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.changep_bt:
                //
                if(changepEdv.getText().toString().length()>3){
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("email",changepEdp.getText().toString());
                    hashMap.put("code",changepEdv.getText().toString());
                    hashMap.put("code_id",code_id);
                    mPresenter.mailBindData(hashMap);
                }else{
                    Toast.makeText(this, "验证码填写不规范", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
