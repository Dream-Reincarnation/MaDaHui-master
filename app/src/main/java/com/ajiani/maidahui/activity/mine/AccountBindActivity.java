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
import com.ajiani.maidahui.bean.mine.AutonymBean;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountBindActivity extends SimpleActivity {
    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.account_bind_ailitv)
    TextView account_tv;
    @BindView(R.id.account_bind_ailipay)
    LinearLayout accountBindAilipay;
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
      title.setText("提现账号绑定");

    }

    @Override
    protected void onResume() {
        super.onResume();
        String ailipay = (String) SPUtils.get(this, "ailipay", "");
        if(ailipay.length()>3){
            account_tv.setText("修改");
        }else{
            account_tv.setText("未绑定");
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_account_bind;
    }


    @OnClick({R.id.back, R.id.account_bind_ailipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.account_bind_ailipay:
                String autonyms = (String) SPUtils.get(this, "autonyms", "");
                if(autonyms.length()>3){
                    AutonymBean autonymBean = new Gson().fromJson(autonyms, AutonymBean.class);
                    AutonymBean.DataBean data = autonymBean.getData();
                    if(true){
                        //认证成功
                        Bundle bundle = new Bundle();
                        SPUtils.put(this,"true_name",data.getTrue_name());
                        JumpUtils.gotoActivity(this,BindAliPayActivity.class,bundle);
                    }else if(data.getStatus()==0){
                        //审核中

                    }else{

                         //已拒绝
                        changeType.setText("提示");
                        changeQuery.setText("去认证");
                        changeTv.setText("为保证账户安全，绑定支付宝需要进行实名认证请先进行实名认证");
                        popupWindow.showAtLocation(back, Gravity.BOTTOM,0,0);
                        changeQuery.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                JumpUtils.gotoActivity(AccountBindActivity.this,AutonymActivity.class);
                                popupWindow.dismiss();
                            }
                        });
                    }
                }
                break;
        }
    }
}
