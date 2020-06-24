package com.ajiani.maidahui.activity.mine;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.mine.UserInfo;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountActivity extends SimpleActivity {
    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.account_wechat)
    TextView accountWechat;
    @BindView(R.id.account_Wechat_lin)
    LinearLayout accountWechatLin;
    @BindView(R.id.account_phone)
    TextView accountPhone;
    @BindView(R.id.account_phone_lin)
    LinearLayout accountPhoneLin;
    @BindView(R.id.account_mailbox)
    TextView accountMailbox;
    @BindView(R.id.account_mailbox_lin)
    LinearLayout accountMailboxLin;
    @BindView(R.id.account_deposite)
    TextView accountDeposite;
    @BindView(R.id.account_deposite_lin)
    LinearLayout accountDepositeLin;
    @BindView(R.id.account_bind)
    TextView accountBind;
    @BindView(R.id.account_bind_lin)
    LinearLayout accountBindLin;
    @BindView(R.id.account_paypass)
    TextView accountPaypass;
    @BindView(R.id.account_paypass_lin)
    LinearLayout accountPaypassLin;
    private String mobile;
    private String email;
    private PopupWindow popupWindow;
    private TextView changeQuery;
    private TextView changeTv;
    private TextView changeType;

    @Override
    protected void initData() {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        View inflate = LayoutInflater.from(this).inflate(R.layout.phone_item, null, false);
        popupWindow.setContentView(inflate);
        changeType = inflate.findViewById(R.id.popchange_type);
        changeTv = inflate.findViewById(R.id.popchange_tv);
        inflate.findViewById(R.id.popchange_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        changeQuery = inflate.findViewById(R.id.popchange_query);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        String success = (String) SPUtils.get(this, "userinfo", "");
        if (success.length() > 5) {
            UserInfo userInfo = new Gson().fromJson(success, UserInfo.class);
            mobile = userInfo.getData().getMobile();
            //方法二
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);

            accountPhone.setText(mobile);
        }
        email = (String) SPUtils.get(this, "email", "");
        if(email.length()>4){
            accountMailbox.setText(getmail(email));
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
        return R.layout.activity_account;
    }


    @OnClick({R.id.back, R.id.account_Wechat_lin, R.id.account_phone_lin, R.id.account_mailbox_lin, R.id.account_deposite_lin, R.id.account_bind_lin, R.id.account_paypass_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.account_Wechat_lin:
                break;
            case R.id.account_phone_lin:
                changeType.setText("更换绑定的手机号");
                changeTv.setText("当前绑定的手机号为\n"+mobile);
                changeQuery.setText("确定");
              popupWindow.showAtLocation(back, Gravity.BOTTOM,0,0);
              changeQuery.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      JumpUtils.gotoActivity(AccountActivity.this,ChangePhone.class);
                      popupWindow.dismiss();
                  }
              });
                break;
            case R.id.account_mailbox_lin:
                //绑定邮箱
             if(email.length()>4) {
                 popupWindow.showAtLocation(back, Gravity.BOTTOM,0,0);
                 changeType.setText("更换绑定的邮箱号");
                 changeTv.setText("当前绑定的邮箱号为\n"+getmail(email));
                 changeQuery.setText("确定");
                 changeQuery.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         JumpUtils.gotoActivity(AccountActivity.this,BindMailBoxActivity.class);
                         popupWindow.dismiss();
                     }
                 });
             }else{
                 JumpUtils.gotoActivity(this,BindMailBoxActivity.class);
             }

                break;
            case R.id.account_deposite_lin:
                //提现账号绑定
                JumpUtils.gotoActivity(this,AccountBindActivity.class);
                break;
            case R.id.account_bind_lin:

                break;
            case R.id.account_paypass_lin:
                //更改支付密码
                String autonym = (String) SPUtils.get(this, "autonym", "");
                String pass = (String) SPUtils.get(this, "pass", "");
                changeType.setText("提示");
                changeQuery.setText("去认证");
                Bundle bundle = new Bundle();
//                autonym="1";
                if(autonym.equals("1")){
                    if(pass.length()<6){
                        bundle.putInt("isForget",1);
                        JumpUtils.gotoActivity(this,SetPayPass2.class,bundle);
                    }else{
                        bundle.putInt("isForget",0);
                        JumpUtils.gotoActivity(this,SetPayPass2.class,bundle);
                    }


                }else{
                 //跳转到安全验证页面

                    popupWindow.showAtLocation(back, Gravity.BOTTOM,0,0);

                    changeTv.setText("为保证账户安全，设置支付密码需要\u0003进行实名认证请先进行实名认证");
                    changeQuery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            JumpUtils.gotoActivity(AccountActivity.this,BindMailBoxActivity.class);
                            popupWindow.dismiss();
                        }
                    });

                }
                break;
        }
    }
}
