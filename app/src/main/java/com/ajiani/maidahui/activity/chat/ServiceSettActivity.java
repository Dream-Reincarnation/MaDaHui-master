package com.ajiani.maidahui.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.mInterface.chat.ShopListIn;
import com.ajiani.maidahui.presenters.chat.ShopMessagePresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceSettActivity extends BaseActivity<ShopListIn.shopMessageView, ShopMessagePresenter> implements ShopListIn.shopMessageView  {
    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.service_type)
    TextView serviceType;
    @BindView(R.id.service_typeimg)
    ImageView serviceImg;
    @BindView(R.id.service_compat)
    SwitchCompat serviceCompat;
    @BindView(R.id.service_clear)
    TextView serviceClear;
    private PopupWindow popupWindow;
    String delType = "TreesSeedPay,TreesSeedSuccess,addGroupFail,addDduobaoFail,TreesOrderSuccess,TreesOrderShipping,addDduobao,addDduobaoSuccess,addGroup,addGroupSuccess";
    private String type;

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        serviceCompat.setChecked(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        type = bundle.getString("type");
        if(type.equals("service")){
            serviceImg.setImageResource(R.mipmap.mail_ico_remind);
            serviceType.setText("服务提醒");
            delType = "TreesSeedPay,TreesSeedSuccess,addGroupFail,addDduobaoFail,TreesOrderSuccess,orderPaySuccess,addDduobao,addDduobaoSuccess,addGroup,addGroupSuccess";
            isChecked(type);
        }else if(type.equals("board")){
            serviceImg.setImageResource(R.mipmap.interact);
            serviceType.setText("广播");
            delType = "cashSuccess,cashFail,ComplaintSend,ComplaintEnd,LinkMessage";
            isChecked(type);
        }else if(type.equals("comment")){
            serviceImg.setImageResource(R.mipmap.comment);
            serviceType.setText("评论与@");
            delType = "AcetexVideo";
            isChecked(type);
        }else if(type.equals("logistics")){
            serviceImg.setImageResource(R.mipmap.logistics);
            delType = "TreesOrderShipping,orderShippingSuccess";
            serviceType.setText("物流");
            isChecked(type);
        }


        title.setText("设置");


        //
        serviceCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    setCheck1(type,false);
                }else{
                    setCheck1(type,true);
                }

            }
        });
    }

    private void setCheck1(String type,boolean b) {
        switch (type) {
            case "service":
                SPUtils.put(this,"isswitch",b);
                break;
            case "board":
                Log.i("wxy", "onCheckedChanged: "+b);
                SPUtils.put(this,"isBoardswitch",b);
                break;
            case "comment":
                SPUtils.put(this,"isCommentswitch",b);
                break;
            case "logistics":
                Log.i("wxy", "onCheckedChanged: "+b);
                SPUtils.put(this,"isLogisticsswitch",b);
                break;

        }
    }

    private void isChecked(String type) {
        if(type.equals("service")){
            boolean isswitch = (boolean) SPUtils.get(this, "isswitch", false);
            if(isswitch){
                serviceCompat.setChecked(false);
            }else{
                serviceCompat.setChecked(true);
            }
        }else if(type.equals("board")){
            boolean isswitch = (boolean) SPUtils.get(this, "isBoardswitch", false);
            Log.i("wxy", "onCheckedChanged: "+isswitch);
            if(isswitch){
                serviceCompat.setChecked(false);
            }else{
                serviceCompat.setChecked(true);
            }
        }else if(type.equals("comment")){
            boolean isswitch = (boolean) SPUtils.get(this, "isCommentswitch", false);
            if(isswitch){
                serviceCompat.setChecked(false);
            }else{
                serviceCompat.setChecked(true);
            }
        }else if(type.equals("logistics")){
            boolean isswitch = (boolean) SPUtils.get(this, "isLogisticsswitch", false);
            if(isswitch){
                serviceCompat.setChecked(false);
            }else{
                serviceCompat.setChecked(true);
            }
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_serviceset;
    }



    @OnClick({R.id.back, R.id.service_clear})
    public void onViewClicked(View view) {
         TextView changeQuery;
         TextView changeTv;
         TextView changeType;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.service_clear:
                //弹窗  清除消息
                popupWindow = new PopupWindow(this);
                View inflate = LayoutInflater.from(this).inflate(R.layout.phone_item, null, false);
                changeType = inflate.findViewById(R.id.popchange_type);
                changeTv = inflate.findViewById(R.id.popchange_tv);
                changeType.setVisibility(View.GONE);
                changeTv.setText("是否要清空所有消息");
                changeQuery = inflate.findViewById(R.id.popchange_query);
                changeQuery.setText("确定");
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setContentView(inflate);
                inflate.findViewById(R.id.popchange_cancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                changeQuery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //确认清除

                        isPresenter(type);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(back, Gravity.CENTER ,0,0);
                break;

        }
    }

    private void isPresenter(String type) {
        if(type.equals("service")){
            delType = "TreesSeedPay,TreesSeedSuccess,addGroupFail,addDduobaoFail,TreesOrderSuccess,orderPaySuccess,addDduobao,addDduobaoSuccess,addGroup,addGroupSuccess";
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("msg_type",delType);
            mPresenter.getDelData(hashMap);
        }else if(type.equals("board")){
            delType = "cashSuccess,cashFail,ComplaintSend,ComplaintEnd,LinkMessage";
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("msg_type",delType);
            mPresenter.getDelMsgData(hashMap);
        }else if(type.equals("comment")){
            delType = "AcetexVideo";
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("msg_type",delType);
            mPresenter.getDelMsgData(hashMap);
        }else if(type.equals("logistics")){
            delType = "TreesOrderShipping,orderShippingSuccess";
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("msg_type",delType);
            mPresenter.getDelData(hashMap);
        }
    }

    @Override
    protected ShopMessagePresenter preparePresenter() {
        return new ShopMessagePresenter();
    }

    @Override
    public void shopMessageSuccess(String success) {

    }

    @Override
    public void delMessageSuccess(String success) {
        //删除成功
        Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        JumpUtils.gotoActivity(this, HomeActivity.class);
    }

    @Override
    public void delMsgSuccess(String success) {

    }
}
