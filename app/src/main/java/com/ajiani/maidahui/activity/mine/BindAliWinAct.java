package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindAliWinAct extends SimpleActivity {
    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bind_win_text)
    TextView bindWinText;
    @BindView(R.id.bind_win_bt)
    TextView bindWinBt;
    private int anInt;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        anInt = bundle.getInt("isact");
        if(anInt==1){
            //解绑
            title.setText("解绑成功");
            Drawable background = bindWinBt.getBackground();
            Drawable drawable = DrawUtils.setSolid(R.color.Thme, background);
            bindWinBt.setBackground(drawable);
            bindWinBt.setText("重新绑定");
        }else{
            title.setText("绑定成功");
            bindWinBt.setText("确定");
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_bindwin;
    }


    @OnClick({R.id.back, R.id.bind_win_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                JumpUtils.gotoActivity(this,AccountBindActivity.class);
                break;
            case R.id.bind_win_bt:
                if(anInt==1){
                    JumpUtils.gotoActivity(this,BindAliPayActivity.class);
                }else{
                    JumpUtils.gotoActivity(this,AccountBindActivity.class);
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
