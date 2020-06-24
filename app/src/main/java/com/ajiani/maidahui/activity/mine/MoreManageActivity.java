package com.ajiani.maidahui.activity.mine;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreManageActivity extends SimpleActivity {
    @BindView(R.id.manager_back)
    ImageView managerBack;
    @BindView(R.id.manager_lin)
    LinearLayout managerLin;
    private Bundle bundle;

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_moremanage;
    }



    @OnClick({R.id.manager_back, R.id.manager_village, R.id.manager_performance, R.id.manager_association, R.id.manager_help, R.id.manager_cooperation, R.id.manager_shop, R.id.manager_draw, R.id.manger_place, R.id.manager_lin, R.id.manager_anchor, R.id.mine_dynamiclin, R.id.manager_anchorshop, R.id.manager_lin2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.manager_back:
                //返回按钮
                finish();
                break;
            case R.id.manager_village:
                //小区长管理
                break;
            case R.id.manager_performance:
                //业绩管理
                break;
            case R.id.manager_association:
                //社群管理
                break;
            case R.id.manager_help:
                //帮助中心
                break;
            case R.id.manager_cooperation:
                //合作中心
                break;
            case R.id.manager_shop:
                //我的店铺
                break;
            case R.id.manager_draw:
                //我的抽奖
                bundle = new Bundle();
                bundle.putString("type","draw");
                bundle.putString("title","我的抽奖");
                JumpUtils.gotoActivity(this,WebManagerActivity.class,bundle);
                break;
            case R.id.manger_place:
                //地址管理
                bundle = new Bundle();
                bundle.putString("type","place");
                bundle.putString("title","地址管理");
                JumpUtils.gotoActivity(this,WebManagerActivity.class,bundle);
                break;
            case R.id.manager_lin:
                //我的动态
                JumpUtils.gotoActivity(this, AutonymActivity.class);
                break;
            case R.id.manager_anchor:
                //直播管理
                bundle = new Bundle();
                bundle.putString("type","collect");
                bundle.putString("title","我的收藏");
                JumpUtils.gotoActivity(this,WebManagerActivity.class,bundle);
                break;
            case R.id.mine_dynamiclin:
                //我的动态
                JumpUtils.gotoActivity(this, MineDynamicActivity.class);
                break;
            case R.id.manager_anchorshop:
                //主播商品
                break;
            case R.id.manager_lin2:
                //公会
                break;
        }
    }


}
