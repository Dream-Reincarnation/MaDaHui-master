package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.Even;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrivateActivity extends SimpleActivity {
    @BindView(R.id.rellase_back)
    ImageView rellaseBack;
    @BindView(R.id.release_rele)
    TextView releaseRele;
    @BindView(R.id.private_img1)
    ImageView privateImg1;
    @BindView(R.id.private_lin1)
    LinearLayout privateLin1;
    @BindView(R.id.private_img2)
    ImageView privateImg2;
    @BindView(R.id.private_lin2)
    LinearLayout privateLin2;
    @BindView(R.id.private_img3)
    ImageView privateImg3;
    @BindView(R.id.private_lin3)
    LinearLayout privateLin3;
    String text = "公开";
    private Even mEven;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        int mode = bundle.getInt("mode");
        if(mode==0){
            privateImg1.setVisibility(View.VISIBLE);
        }else if(mode==1){
            privateImg2.setVisibility(View.VISIBLE);
        }else{
            privateImg3.setVisibility(View.VISIBLE);
        }

        mEven = new Even(1, text);
    }

    @Override
    protected int createLayout() {
        return R.layout.private1;
    }


    @OnClick({R.id.rellase_back, R.id.private_lin1, R.id.private_lin2, R.id.private_lin3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rellase_back:
                finish();
                EventBus.getDefault().postSticky(mEven);
                break;
            case R.id.private_lin1:
                privateImg1.setVisibility(View.VISIBLE);
                privateImg2.setVisibility(View.GONE);
                privateImg3.setVisibility(View.GONE);
                text = "0";
                mEven.setText(text);
                finish();
                EventBus.getDefault().postSticky(mEven);
                break;
            case R.id.private_lin2:
                privateImg3.setVisibility(View.VISIBLE);
                privateImg1.setVisibility(View.GONE);
                privateImg2.setVisibility(View.GONE);
                text = "1";
                mEven.setText(text);
                finish();
                EventBus.getDefault().postSticky(mEven);
                break;
            case R.id.private_lin3:
                privateImg2.setVisibility(View.VISIBLE);
                privateImg3.setVisibility(View.GONE);
                privateImg1.setVisibility(View.GONE);
                text = "2";
                mEven.setText(text);
                finish();
                EventBus.getDefault().postSticky(mEven);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();//注释掉这行,back键不退出activity
        mEven.setText(text);
        EventBus.getDefault().postSticky(mEven);

    }
}
