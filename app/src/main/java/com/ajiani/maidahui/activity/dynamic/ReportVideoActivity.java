package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.Utils.photo.PictureUtils;
import com.ajiani.maidahui.adapter.dynamic.ReportAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.Even;
import com.ajiani.maidahui.bean.ReportBean;
import com.ajiani.maidahui.mInterface.dynamic.ReportIn;
import com.ajiani.maidahui.presenters.dynamic.ReportPresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportVideoActivity extends BaseActivity<ReportIn.reportView, ReportPresenter> implements ReportIn.reportView {
    @BindView(R.id.report_back)
    FrameLayout reportBack;
    @BindView(R.id.report_video_commint)
    TextView reportVideoCommint;
    @BindView(R.id.report_video_reason)
    TextView reportVideoReason;
    @BindView(R.id.report_video_min)
    TextView reportVideoMin;
    @BindView(R.id.report_video_et)
    EditText reportVideoEt;
    @BindView(R.id.report_video_another)
    EditText reportVideoAnother;
    @BindView(R.id.report_video_link)
    EditText reportVideoLink;
    @BindView(R.id.report_video_lin)
    LinearLayout reportVideoLin;
    @BindView(R.id.report_video_farm)
    FrameLayout reportVideoFarm;
    @BindView(R.id.report_video_rel)
    RecyclerView reportVideoRel;
    private ReportAdapter reportAdapter;
    ArrayList<ReportBean> reportBeans = new ArrayList<>();
    List<LocalMedia> selectList = new ArrayList<>();
    private String videoID;
    private String title;

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        
        reportVideoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //设置打字
                reportVideoMin.setText(reportVideoEt.getText().toString().length() + " ");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        reportAdapter.setOnClickLinstener(new ReportAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                if (reportAdapter.mList.size() == 3) {
                    reportAdapter.mList.remove(posstion);
                 /*   ReportBean reportBean = new ReportBean();
                    reportBean.setPicUrl("");
                    reportBean.setEnd(true);
                    reportAdapter.mList.add(reportBean);*/
                } else {
                    reportAdapter.mList.remove(posstion);
                }
                reportBeans.remove(posstion);

                reportAdapter.notifyDataSetChanged();
            }
        });
        reportAdapter.setOnClickImgLinstener(new ReportAdapter.onClickImgLinstener() {
            @Override
            public void onClick(int posstion) {
                //调用相册
                if (reportAdapter.mList.size() == 1) {
                    PictureUtils.startPhoto(ReportVideoActivity.this, 3, true);
                } else if (reportAdapter.mList.size() == 2) {
                    PictureUtils.startPhoto(ReportVideoActivity.this, 2, true);
                } else if (reportAdapter.mList.size() == 3) {
                    PictureUtils.startPhoto(ReportVideoActivity.this, 1, true);
                } else {
                    PictureUtils.startPhoto(ReportVideoActivity.this, 0, true);
                }
            }
        });
    }
  
    public void success(){
                 HashMap<String, String> hashMap = new HashMap<>();
                 hashMap.put("type","video");
                 hashMap.put("article_id",videoID);
                 hashMap.put("reason",title);
                 if(title.equals("非原创内容")){
                     if(reportVideoAnother.getText().toString().length()>0){
                         hashMap.put("author_id",reportVideoAnother.getText().toString());
                     }
                     if(reportVideoLink.getText().toString().length()>0){
                         hashMap.put("author_link",reportVideoLink.getText().toString());
                     }
                 }
                 if(reportVideoEt.getText().toString().length()>0){
                     hashMap.put("content",reportVideoEt.getText().toString());
                 }
                 mPresenter.getReportData(hashMap);
    }
    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        title = bundle.getString("title");
        videoID = bundle.getString("videoID");
        reportVideoReason.setText(title);
        reportVideoLin.setVisibility(View.GONE);
        if(title.equals("非原创内容")){
            reportVideoLin.setVisibility(View.VISIBLE);
        }

        Drawable background = reportVideoFarm.getBackground();
        Drawable drawable = DrawUtils.setStroke(R.color.strok, background);
        reportVideoFarm.setBackground(drawable);

        reportVideoRel.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        ArrayList<ReportBean> reportBeans = new ArrayList<>();
        ReportBean reportBean = new ReportBean();
        reportBean.setPicUrl("");
        reportBean.setEnd(true);
        reportBeans.add(reportBean);
        reportAdapter = new ReportAdapter(reportBeans);
        reportVideoRel.setAdapter(reportAdapter);

    }

    @Override
    protected int createLayout() {
        return R.layout.report_video;
    }

    @OnClick({R.id.report_back, R.id.report_video_commint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.report_back:
                finish();
                break;
            case R.id.report_video_commint:
                //进行网络请求
                ArrayList<String> strings = new ArrayList<>();
                ArrayList<File> files = new ArrayList<>();
                if(reportAdapter.mList.size()>1){
                    //上传图片
                    for (int i = 0; i < reportAdapter.mList.size(); i++) {
                        ReportBean reportBean = reportAdapter.mList.get(i);
                        if(reportBean.isEnd()){

                        }else{
                            //
                            strings.add(reportBean.getPicUrl());
                            files.add(new File(reportBean.getPicUrl()));
                        }
                    }
                    //得到所有照片的路径上传
                    HttpUtils httpUtils = new HttpUtils();
                    httpUtils.uploadMoreImg(files,this);
                }

                break;
        }
    }

    @Override
    public void reportSuccess(String success) {
        Toast.makeText(this, "举报成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    //跳转相册回调


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path， .isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    // 4.media.getAndroidQToPath();为Android Q版本特有返回的字段，此字段有值就用来做上传使用
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        for (int i = 0; i < selectList.size(); i++) {
                            long size = selectList.get(i).getSize();
                            Log.i("wxy", "onActivityResult: "+size);
                            ReportBean reportBean = new ReportBean();
                            reportBean.setPicUrl(selectList.get(i).getAndroidQToPath());
                            reportBean.setEnd(false);
                            reportBeans.add(reportBean);
                        }

                        if (reportBeans.size() == 3) {
                            reportAdapter.mList.clear();
                            ReportBean reportBean = new ReportBean();
                            reportBean.setPicUrl("");
                            reportBean.setEnd(true);
                            reportAdapter.mList.add(reportBean);
                            reportAdapter.mList.addAll(0, reportBeans);
                            reportAdapter.notifyDataSetChanged();
                        } else {
                            reportAdapter.mList.clear();
                            ReportBean reportBean = new ReportBean();
                            reportBean.setPicUrl("");
                            reportBean.setEnd(true);
                            reportAdapter.mList.add(reportBean);
                            reportAdapter.mList.addAll(0, reportBeans);
                            reportAdapter.notifyDataSetChanged();

                        }
                        selectList.clear();
                        //绑定适配器
                    } else {
                        for (int i = 0; i < selectList.size(); i++) {
                            ReportBean reportBean = new ReportBean();
                            reportBean.setPicUrl(selectList.get(i).getCompressPath());
                            reportBean.setEnd(false);
                            reportBeans.add(reportBean);
                        }

                        if (reportBeans.size() == 3) {
                            reportAdapter.mList.clear();
                            ReportBean reportBean = new ReportBean();
                            reportBean.setPicUrl("");
                            reportBean.setEnd(true);
                            reportAdapter.mList.add(reportBean);
                            reportAdapter.mList.addAll(0, reportBeans);
                            reportAdapter.notifyDataSetChanged();
                        } else {
                            reportAdapter.mList.clear();
                            ReportBean reportBean = new ReportBean();
                            reportBean.setPicUrl("");
                            reportBean.setEnd(true);

                            reportAdapter.mList.add(reportBean);
                            reportAdapter.mList.addAll(0, reportBeans);
                            reportAdapter.notifyDataSetChanged();
                        }
                        selectList.clear();
                    }
                    break;
            }
        }
    }

    @Override
    protected ReportPresenter preparePresenter() {
        return new ReportPresenter();
    }

   
}

