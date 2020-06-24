package com.ajiani.maidahui.activity.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatReportAct extends BaseActivity {
    @BindView(R.id.chatact_back)
    FrameLayout chatactBack;
    @BindView(R.id.chatact_title)
    TextView chatactTitle;
    @BindView(R.id.chat_report_lin1)
    LinearLayout chatReportLin1;
    @BindView(R.id.chat_report_lin2)
    LinearLayout chatReportLin2;
    @BindView(R.id.chat_report_lin3)
    LinearLayout chatReportLin3;
    @BindView(R.id.chat_report_lin4)
    LinearLayout chatReportLin4;
    @BindView(R.id.chat_report_lin5)
    LinearLayout chatReportLin5;
    @BindView(R.id.chat_report_lin6)
    LinearLayout chatReportLin6;
    @BindView(R.id.chat_report_ed)
    EditText chatReportEd;
    @BindView(R.id.chat_report_bt)
    TextView chatReportBt;
    @BindView(R.id.chat_repport_img1)
    ImageView chatRepportImg1;
    @BindView(R.id.chat_repport_img2)
    ImageView chatRepportImg2;
    @BindView(R.id.chat_repport_img3)
    ImageView chatRepportImg3;
    @BindView(R.id.chat_repport_img4)
    ImageView chatRepportImg4;
    @BindView(R.id.chat_repport_img5)
    ImageView chatRepportImg5;
    @BindView(R.id.chat_repport_img6)
    ImageView chatRepportImg6;
    private ArrayList<String> strings;
    private ArrayList<Integer> integers;

    @Override
    protected BasePresenterImp preparePresenter() {
        return null;
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        strings = new ArrayList<>();
        integers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            integers.add(0);
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.acticity_chatreport;
    }


    @OnClick({R.id.chatact_back, R.id.chat_report_lin1, R.id.chat_report_lin2, R.id.chat_report_lin3, R.id.chat_report_lin4, R.id.chat_report_lin5, R.id.chat_report_lin6, R.id.chat_report_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatact_back:
                finish();
                break;
            case R.id.chat_report_lin1:
                //点击选中行或未选中
                if (integers.get(0) == 0) {
                    //未选中
                    integers.set(0, 1);
                    chatRepportImg1.setImageResource(R.mipmap.circle_sel);
                } else {
                    //.s
                    integers.set(0, 0);
                    chatRepportImg1.setImageResource(R.mipmap.circle_unsel);
                }
                break;
            case R.id.chat_report_lin2:
                if (integers.get(1) == 0) {
                    //未选中
                    integers.set(1, 1);
                    chatRepportImg2.setImageResource(R.mipmap.circle_sel);
                } else {
                    //.s
                    integers.set(1, 0);
                    chatRepportImg2.setImageResource(R.mipmap.circle_unsel);
                }
                break;
            case R.id.chat_report_lin3:
                if (integers.get(2) == 0) {
                    //未选中
                    integers.set(2, 1);
                    chatRepportImg3.setImageResource(R.mipmap.circle_sel);
                } else {
                    //.s
                    integers.set(2, 0);
                    chatRepportImg3.setImageResource(R.mipmap.circle_unsel);
                }
                break;
            case R.id.chat_report_lin4:
                if (integers.get(3) == 0) {
                    //未选中
                    integers.set(3, 1);
                    chatRepportImg4.setImageResource(R.mipmap.circle_sel);
                } else {
                    //.s
                    integers.set(3, 0);
                    chatRepportImg4.setImageResource(R.mipmap.circle_unsel);
                }
                break;
            case R.id.chat_report_lin5:
                if (integers.get(4) == 0) {
                    //未选中
                    integers.set(4, 1);
                    chatRepportImg5.setImageResource(R.mipmap.circle_sel);
                } else {
                    //.s
                    integers.set(4, 0);
                    chatRepportImg5.setImageResource(R.mipmap.circle_unsel);
                }
                break;
            case R.id.chat_report_lin6:
                if (integers.get(5) == 0) {
                    //未选中
                    integers.set(5, 1);
                    chatRepportImg6.setImageResource(R.mipmap.circle_sel);
                } else {
                    //.s
                    integers.set(5, 0);
                    chatRepportImg6.setImageResource(R.mipmap.circle_unsel);
                }
                break;
            case R.id.chat_report_bt:
                StringBuffer stringBuffer = new StringBuffer();
                //提交按钮
                for (int i = 0; i <integers.size() ; i++) {
                    if(integers.get(i)==1){
                        stringBuffer.append(i+",,");
                    }
                }
                Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
