package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.mInterface.mine.CastIn;
import com.ajiani.maidahui.presenters.mine.CastPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestantActivity extends BaseActivity<CastIn.CastView,CastPresenter> implements CastIn.CastView {
    @BindView(R.id.restant_back)
    ImageView restantBack;
    @BindView(R.id.restant_jewel)
    TextView restantJewel;
    @BindView(R.id.restant_money)
    TextView restantMoney;
    @BindView(R.id.restant_rlt)
    RelativeLayout restantRlt;
    @BindView(R.id.restant_lin)
    LinearLayout restantLin;
    @BindView(R.id.restant_detaile)
    LinearLayout restantDetaile;

    private PopupWindow popupWindow;
    private TextView changeQuery;
    private TextView changeTv;
    private TextView changeType;


    @Override
    public void error(String error) {

    }

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
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String restant = bundle.getString("restant");
        restantMoney.setText(restant);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_restant;
    }



    @OnClick({R.id.restant_back, R.id.restant_lin, R.id.restant_detaile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.restant_back:
                finish();
                break;
            case R.id.restant_lin:
                //跳转到提现  判断是否实名认证
                //更改支付密码
                String autonym = (String) SPUtils.get(this,  "autonym", "");
                changeType.setText("提示");
                changeQuery.setText("去认证");

                if(true){
                    JumpUtils.gotoActivity(this,WithdrawActivity.class);
                }else{
                    //跳转到安全验证页面
                    popupWindow.showAtLocation(restantBack, Gravity.BOTTOM,0,0);

                    changeTv.setText("为保证账户安全，设置支付密码需要\u0003进行实名认证请先进行实名认证");
                    changeQuery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            JumpUtils.gotoActivity(RestantActivity.this,AutonymASetting.class);
                            popupWindow.dismiss();
                        }
                    });

                }

                break;
            case R.id.restant_detaile:
                //跳到积分明细
                JumpUtils.gotoActivity(this,RestantDetailsActivity.class);
                break;
        }
    }

    @Override
    public void castSuccess(String success) {

    }

    @Override
    public void detailsSuccess(String success) {

    }

    @Override
    protected CastPresenter preparePresenter() {
        return new CastPresenter();
    }
}
