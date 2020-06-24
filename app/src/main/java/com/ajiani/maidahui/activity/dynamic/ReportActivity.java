package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportActivity extends SimpleActivity {
    @BindView(R.id.report_eroticismlin)
    LinearLayout reportEroticismlin;
    @BindView(R.id.report_politics)
    LinearLayout reportPolitics;
    @BindView(R.id.report_crimelin)
    LinearLayout reportCrimelin;
    @BindView(R.id.report_rubbishlin)
    LinearLayout reportRubbishlin;
    @BindView(R.id.report_rumourlin)
    LinearLayout reportRumourlin;
    @BindView(R.id.report_insultlin)
    LinearLayout reportInsultlin;
    @BindView(R.id.report_originallin)
    LinearLayout reportOriginallin;
    @BindView(R.id.report_violatelin)
    LinearLayout reportViolatelin;
    @BindView(R.id.report_nochildlin)
    LinearLayout reportNochildlin;
    @BindView(R.id.report_noseelin)
    LinearLayout reportNoseelin;
    @BindView(R.id.report_flauntlin)
    LinearLayout reportFlauntlin;
    @BindView(R.id.report_dramaticlin)
    LinearLayout reportDramaticlin;
    @BindView(R.id.report_hurtlin)
    LinearLayout reportHurtlin;
    @BindView(R.id.report_inducelin)
    LinearLayout reportInducelin;
    @BindView(R.id.report_otherlin)
    LinearLayout reportOtherlin;
    private String videoID;
    private Bundle bundle;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        videoID = bundle.getString("videoID");

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_report1;
    }

    @OnClick({R.id.report_eroticismlin, R.id.report_politics, R.id.report_crimelin, R.id.report_rubbishlin, R.id.report_rumourlin, R.id.report_insultlin, R.id.report_originallin, R.id.report_violatelin, R.id.report_nochildlin, R.id.report_noseelin, R.id.report_flauntlin, R.id.report_dramaticlin, R.id.report_hurtlin, R.id.report_inducelin, R.id.report_otherlin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //色情低俗
            case R.id.report_eroticismlin:
                bundle = new Bundle();
                bundle.putString("title","色情低俗");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
                //政治敏感
            case R.id.report_politics:
                bundle = new Bundle();
                bundle.putString("title","政治敏感");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
                //违法犯罪
            case R.id.report_crimelin:
                bundle = new Bundle();
                bundle.putString("title","违法犯罪");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
                //违法犯罪、售卖假货
            case R.id.report_rubbishlin:
                bundle = new Bundle();
                bundle.putString("title","违法犯罪、售卖假货");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
                //造谣传谣
            case R.id.report_rumourlin:
                bundle = new Bundle();
                bundle.putString("title","造谣传谣");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
                //侮辱谩骂
            case R.id.report_insultlin:
                bundle = new Bundle();
                bundle.putString("title","侮辱谩骂");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
                //非原创内容
            case R.id.report_originallin:
                bundle = new Bundle();
                bundle.putString("title","非原创内容");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            //  侵犯权益
            case R.id.report_violatelin:
                this.bundle = new Bundle();
                this.bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this, ViolateActivity.class, this.bundle);
                break;
            case R.id.report_nochildlin:
                bundle = new Bundle();
                bundle.putString("title","未成年人不当行为");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            case R.id.report_noseelin:
                bundle = new Bundle();
                bundle.putString("title","内容不适合未成年人观看");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            case R.id.report_flauntlin:
                bundle = new Bundle();
                bundle.putString("title","炫富");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            case R.id.report_dramaticlin:
                bundle = new Bundle();
                bundle.putString("title","引人不适");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            case R.id.report_hurtlin:
                bundle = new Bundle();
                bundle.putString("title","疑似自我伤害");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            case R.id.report_inducelin:
                bundle = new Bundle();
                bundle.putString("title","诱导点赞、分享、关注");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            case R.id.report_otherlin:
                bundle = new Bundle();
                bundle.putString("title","其他");
                bundle.putString("videoID",videoID);
                JumpUtils.gotoActivity(this,ReportVideoActivity.class,bundle);
                break;
            case R.id.report_back:
                finish();
                break;
        }
    }
}
