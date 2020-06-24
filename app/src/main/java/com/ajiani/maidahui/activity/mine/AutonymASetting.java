package com.ajiani.maidahui.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.photo.PictureUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.UpLoadBean;
import com.ajiani.maidahui.mInterface.mine.AutonymIn;
import com.ajiani.maidahui.presenters.mine.AutonymPresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutonymASetting extends BaseActivity<AutonymIn.AutonymView, AutonymPresenter> implements AutonymIn.AutonymView {


    ArrayList<File> mList = new ArrayList<>();
    ArrayList<String> mList2 = new ArrayList<>();
    @BindView(R.id.report_back)
    FrameLayout reportBack;
    @BindView(R.id.autonym_etname)
    EditText autonymEtname;
    @BindView(R.id.autonym_etnum)
    EditText autonymEtnum;
    @BindView(R.id.autonym_carme)
    ImageView autonymCarme;
    @BindView(R.id.autonym_top)
    ImageView autonymTop;
    @BindView(R.id.autonym_carme2)
    ImageView autonymCarme2;
    @BindView(R.id.autonym_bot)
    ImageView autonymBot;
    @BindView(R.id.autonym_why)
    ImageView autonymWhy;
    @BindView(R.id.autonym_bt)
    TextView autonymBt;
    @BindView(R.id.autonym_sel)
    ImageView autonymSel;
    @BindView(R.id.autonym_yin)
    TextView autonymYin;

    boolean isSel = true;
    private Intent intent;
    ArrayList<File> strings = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    private String TAG="wxy";

    @Override
    protected AutonymPresenter preparePresenter() {
        return new AutonymPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
    /*    autonymBt.setEnabled(false);
        Drawable background = autonymBt.getBackground();
        Drawable drawable = DrawUtils.setSolid(R.color.bt_unsel, background);
        autonymBt.setBackground(drawable);*/
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int release = bundle.getInt("release", 0);
        if(release==1){
            String name = bundle.getString("name");
            String id_card = bundle.getString("id_card");
            autonymEtname.setText(name);
            autonymEtnum.setText(id_card);
            autonymEtnum.setFocusableInTouchMode(false);//不可编辑
            autonymEtnum.setKeyListener(null);//不可粘贴，长按不会弹出粘贴框
            autonymEtnum.setClickable(false);//不可点击，但是这个效果我这边没体现出来，不知道怎没用
            autonymEtnum.setFocusable(false);//不可编辑
            autonymEtname.setFocusableInTouchMode(false);//不可编辑
            autonymEtname.setKeyListener(null);//不可粘贴，长按不会弹出粘贴框
            autonymEtname.setClickable(false);//不可点击，但是这个效果我这边没体现出来，不知道怎没用
            autonymEtname.setFocusable(false);//不可编辑
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_autonymsett;
    }

    @Override
    public void authenticaSuccess(String success) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isBack",true);
        JumpUtils.gotoActivity(this,AutonymActivity.class,bundle);
    }

    @Override
    public void checkAutonym(String  success) {

    }

    @Override
    public void upLoadSuccess(String s) {
        UpLoadBean upLoadBean = new Gson().fromJson(s, UpLoadBean.class);
        String path = upLoadBean.getData().getId()+"";
        images.add(path);
        if(images.size()==2){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("true_name",autonymEtname.getText().toString());
            hashMap.put("id_card",autonymEtnum.getText().toString());
            hashMap.put("id_card_a",images.get(0));
            hashMap.put("id_card_b",images.get(1));
            mPresenter.getAutony(hashMap);
        }
    }

    @OnClick({R.id.report_back, R.id.autonym_carme, R.id.autonym_carme2, R.id.autonym_why, R.id.autonym_bt, R.id.autonym_sel, R.id.autonym_yin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.report_back:
                finish();
                break;
            case R.id.autonym_carme:
                PictureUtils.startCarame(this,0x02);
                break;
            case R.id.autonym_carme2:
                PictureUtils.startCarame(this,909);
                break;
            case R.id.autonym_why:
                //弹窗详情
                break;
            case R.id.autonym_bt:
                if (autonymEtname.getText().toString().length() < 1) {
                    Toast.makeText(this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!personIdValidation(autonymEtnum.getText().toString())) {
                    Toast.makeText(this, "身份证号输入不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (autonymBot.getVisibility() == View.GONE || autonymTop.getVisibility() == View.GONE) {
                    Toast.makeText(this, "请上传身份证照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isSel) {
                    Toast.makeText(this, "请勾选隐私协议", Toast.LENGTH_SHORT).show();
                    return;
                }
            //上传图片
                for(int i=0;i<strings.size();i++){
                    mPresenter.getUpLoadData(strings.get(i));
                }
                break;
            case R.id.autonym_sel:
                if (isSel) {
                    autonymSel.setImageResource(R.mipmap.circle_sel);
                    isSel = false;
                } else {
                    autonymSel.setImageResource(R.mipmap.circle_unsel);
                    isSel = true;
                }
                break;
            case R.id.autonym_yin:
                //查看隐私协议
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> selectList;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 909:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        String androidQToPath = selectList.get(0).getAndroidQToPath();
                        File file = new File(androidQToPath);
                        Glide.with(this).load(file).into(autonymBot);
                        strings.add(file);
                        autonymBot.setVisibility(View.VISIBLE);
                    } else {
                        String compressPath = selectList.get(0).getCompressPath();
                        //上传图片
                        File file = new File(compressPath);
                        autonymBot.setVisibility(View.VISIBLE);
                        Glide.with(this).load(file).into(autonymBot);
                        strings.add(file);
                    }
                    break;
                case 0x02:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    Log.i(TAG, "onActivityResult: "+selectList.size());
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        String androidQToPath = selectList.get(0).getAndroidQToPath();
                        File file = new File(androidQToPath);
                        Glide.with(this).load(file).into(autonymTop);
                        autonymTop.setVisibility(View.VISIBLE);
                        strings.add(file);
                    } else {
                        String compressPath = selectList.get(0).getCompressPath();
                        //上传图片
                        File file = new File(compressPath);
                        autonymTop.setVisibility(View.VISIBLE);
                        Glide.with(this).load(file).into(autonymTop);
                        strings.add(file);
                    }
                    break;
            }
        }
    }



    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    public boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }
}
