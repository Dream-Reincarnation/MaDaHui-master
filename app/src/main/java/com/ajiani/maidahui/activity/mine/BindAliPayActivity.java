package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.mInterface.mine.UpdataSetin;
import com.ajiani.maidahui.presenters.mine.SetInfoPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindAliPayActivity extends BaseActivity<UpdataSetin.upSetView, SetInfoPresenter> implements UpdataSetin.upSetView {


    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bind_name)
    TextView bind_name;
    @BindView(R.id.bind_phone)
    ImageView bindPhone;
    @BindView(R.id.bind_text)
    TextView bindText;
    @BindView(R.id.bindali_phone)
    TextView bindaliPhone;
    @BindView(R.id.bindali_unbind)
    LinearLayout bindaliUnbind;
    @BindView(R.id.alilin)
    LinearLayout alilin;
    @BindView(R.id.changealipay_edp2)
    EditText changealipayEdp2;
    @BindView(R.id.bind)
    TextView bind;
    int a;

    private HashMap<String, String> hashMap;

    @Override
    protected SetInfoPresenter preparePresenter() {
        return new SetInfoPresenter();
    }

    @Override
    public void error(String error) {
    }
    @Override
    protected void initData() {
        Drawable background = bind.getBackground();
        Drawable drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
        bind.setBackground(drawable);
        bind.setEnabled(false);

        changealipayEdp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(changealipayEdp2.getText().toString().length()>1){
                   Drawable background = bind.getBackground();
                   Drawable drawable = DrawUtils.setSolid(R.color.Thme, background);
                   bind.setBackground(drawable);
                   bind.setEnabled(true);
               }else{
                   Drawable background = bind.getBackground();
                   Drawable drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
                   bind.setBackground(drawable);
                   bind.setEnabled(false);
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
    protected void initView() {


    }

    @Override
    protected void onResume() {
        super.onResume();

        String ailipay = (String) SPUtils.get(this, "ailipay", "");
        if(ailipay.length()>3){
            title.setText("绑定支付宝 ");
            alilin.setVisibility(View.VISIBLE);
            if(ailipay.contains("@")){
                String getmail = getmail(ailipay);
                bindaliPhone.setText(getmail);
            }else{
                ailipay= ailipay.substring(0, 3) + "****" + ailipay.substring(7, 11);
                bindaliPhone.setText(ailipay);
            }
        }else{
            title.setText("绑定成功");
            String name = (String) SPUtils.get(this, "true_name", "");

            name =  "*" + name.substring(1,name.length());
            alilin.setVisibility(View.GONE);
            bind_name.setText("该账号为提现收款账号，请确保绑定账号信息与麦达汇账号实名信息一致当前实名信息姓名："+name);
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_bindailipay;
    }

    @Override
    public void success(String success) {
        Bundle bundle = new Bundle();
        bundle.putInt("isact",a);

        if(a==1){
            SPUtils.put(this,"ailipay","");
            JumpUtils.gotoActivity(this,BindAliWinAct.class,bundle);
        }else {
            JumpUtils.gotoActivity(this,BindAliWinAct.class,bundle);
            SPUtils.put(this,"ailipay",changealipayEdp2.getText().toString());
        }
        Toast.makeText(this, "更改成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void upLoadSuccess(String success) {

    }




    @OnClick({R.id.back, R.id.bind,R.id.bindali_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bindali_unbind:
                a=1;
                hashMap = new HashMap<>();
                hashMap.put("alipay","-1");
                mPresenter.getData(hashMap);
                break;
            case R.id.back:
                JumpUtils.gotoActivity(this,AccountBindActivity.class);
                break;
            case R.id.bind:
                a=0;
                //进行数据
                if(changealipayEdp2.length()>9){
                    hashMap = new HashMap<>();
                    hashMap.put("alipay",changealipayEdp2.getText().toString());
                    mPresenter.getData(hashMap);
                }else{
                    Toast.makeText(this, "请输入正确的支付宝账号", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            JumpUtils.gotoActivity(this,AccountBindActivity.class);
            return   true;

        }

        return  super.onKeyDown(keyCode, event);

    }
}
