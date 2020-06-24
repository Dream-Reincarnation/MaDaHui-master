package com.ajiani.maidahui.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.activity.mine.PasswordPay;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.chat.TransferBean;
import com.ajiani.maidahui.mInterface.chat.UserMessageList;
import com.ajiani.maidahui.presenters.chat.MessageListPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferActivity extends BaseActivity<UserMessageList.MessageListView, MessageListPresenter> implements UserMessageList.MessageListView {
    @BindView(R.id.transfer_back)
    ImageView transferBack;
    @BindView(R.id.chatact_title)
    TextView chatactTitle;
    @BindView(R.id.transfer_head)
    ImageView transferHead;
    @BindView(R.id.transfer_name)
    TextView transferName;
    @BindView(R.id.transfer_ed)
    EditText transferEd;
    @BindView(R.id.transfer_bt)
    Button transferBt;
    @BindView(R.id.spinner)
    Spinner spinner;
    private String shopid;
    private String text;

    @Override
    protected MessageListPresenter preparePresenter() {
        return new MessageListPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2345&&resultCode==1024){
            String transfer = data.getStringExtra("transfer");
            String text = data.getStringExtra("text");
            Intent intent = getIntent();
            intent.putExtra("transfer", transfer);
            intent.putExtra("text", text);
            setResult(1234, intent);
            finish();
        }
    }

    @Override
    protected void initView() {
        final String[] spinnerItems = {"退运费","赏你的","小李子"};
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        //下拉的样式res
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner.setAdapter(spinnerAdapter);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 text = spinnerItems[i];
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        shopid = bundle.getString("shopid");
        String shopname = bundle.getString("shopname");
        String avart = bundle.getString("avart");
        Glide.with(this).load(avart).apply(new RequestOptions().circleCrop()).into(transferHead);
        transferName.setText(shopname);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_transfer;
    }

    @Override
    public void messageSuccess(String success) {

    }

    @Override
    public void serviceCommentSuccess(String success) {

    }

    @Override
    public void serviceMessageSucess(String success) {

    }

    @Override
    public void voteSuccess(String success) {
        TransferBean transferBean = new Gson().fromJson(success, TransferBean.class);
        //成功之后 ，返回聊天页面
        Intent intent = getIntent();
        String after_votes = transferBean.getData().getAfter_votes();
        SPUtils.put(this,"votes",after_votes);
        intent.putExtra("transfer", success);
        intent.putExtra("text", text);
        setResult(1234, intent);
        finish();
    }

    @Override
    public void delMessageSuccess(String success) {

    }


    @OnClick({R.id.transfer_back, R.id.transfer_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.transfer_back:
                finish();
                break;
            case R.id.transfer_bt:
                //确认转账之后
                Editable text = transferEd.getText();
                String votes = (String) SPUtils.get(this, "votes", "");
                float v = Float.parseFloat(votes);
            
                // String s = transferEt.getText().toString();
                if(text.toString()!=null&&this.text!=null&&text.toString().length()>0){
                    String s = text.toString();
                    float v1 = Float.parseFloat(s);
                    if(v>=v1){

                        Bundle bundle = new Bundle();
                        bundle.putString("shop_id",shopid);
                        bundle.putString("votes",text.toString());
                        bundle.putString("remark",this.text);
                        JumpUtils.gotoActivity(this,TransferPayActivity.class,bundle,2345);
/*
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("shop_id", shopid);
                        hashMap.put("votes", text.toString());
                        hashMap.put("votes", this.text);
                        mPresenter.getVoteData(hashMap);*/
                    }else{
                        Toast.makeText(this, "余额不足", Toast.LENGTH_SHORT).show();
                    }
                   
                }else {
                    Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
                }
                
                
              
                break;
        }
    }


}
