package com.ajiani.maidahui.Utils.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.activity.mine.PasswordPay;
import com.ajiani.maidahui.activity.mine.SetPayPass;

import java.util.HashMap;

public class PayUtils  {
 /*   public PayUtils(Activity context) {
        this.context = context;
    }
    //支付的弹窗

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public Activity context;

    private PopupWindow popupWindow;
    private View inflate1;
    private String vote;
    private TextView yuep;

    public void showPayPop(Activity activity, View view, WindowManager.LayoutParams lp){
        popupWindow = new PopupWindow(activity);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(dip2px(activity, 400));
        inflate1 = LayoutInflater.from(activity).inflate(R.layout.pay_item, null, false);
        popupWindow.setContentView(inflate1);
        popupWindow.setOutsideTouchable(true);//
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
               activity.getWindow().setAttributes(lp);
            }
        });
        vote = (String) SPUtils.get(activity, "votes", "");

        LinearLayout hualin3 = inflate1.findViewById(R.id.pay_hualin3);
        LinearLayout hualin6 = inflate1.findViewById(R.id.pay_hualin6);
        yuep = inflate1.findViewById(R.id.pay_yuep);
        LinearLayout hualin12 = inflate1.findViewById(R.id.pay_hualin12);
        TextView money = inflate1.findViewById(R.id.pay_money);

        money.setText(vote);
        LinearLayout yue = inflate1.findViewById(R.id.pay_yue);
        LinearLayout wechat = inflate1.findViewById(R.id.pay_wechat);
        LinearLayout zhifu = inflate1.findViewById(R.id.pay_zhifu);
        yue.setOnClickListener(this);
        wechat.setOnClickListener(this);
        zhifu.setOnClickListener(this);
        hualin3.setOnClickListener(this);
        hualin6.setOnClickListener(this);
        hualin12.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pay_yue:
                //如果没有支付密码，就跳转到设置密码界面
                String pay_pass = (String) SPUtils.get(context, "pay_pass", "");
                if (pay_pass.equals("0")) {
                    //跳转设置支付密码界面
                    JumpUtils.gotoActivity(context, SetPayPass.class);
                } else {
                    String[] split1 = sUrl.split("/");
                    Log.i("WXY", "onClick: " + split1);
                    String s = split1[4];
                    String s1 = split1[5];

                 *//*     HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("order_no", s);
                        mPresenter.getVote(hashMap);
                      *//*

                    Intent intent = new Intent(context, PasswordPay.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("order_no", s);
                    intent.putExtra("bundle", bundle);
                   context.startActivityForResult(intent, 2345);
                    popupWindow.dismiss();
//                    JumpUtils.gotoActivity(getActivity(), PasswordPay.class,bundle);
                }

                break;
            case R.id.pay_zhifu:

                String[] split = sUrl.split("/");
                Log.i("WXY", "onClick: " + split);
                String s2 = split[4];
                String ss = split[5];
                HashMap<String, String> Map2 = new HashMap<>();
                Map2.put("order_no", s2);
                mPresenter.getPay(Map2);
                break;
            case R.id.pay_wechat:
                HashMap<String, String> stringHashMap = new HashMap<>();
                String[] split2 = sUrl.split("/");
                Log.i("WXY", "onClick: " + split2);
                String wechat = split2[4];
                stringHashMap.put("order_no", wechat);
                mPresenter.getWeChat(stringHashMap);
                break;
            case R.id.pay_hualin3:
                String[] split3 = sUrl.split("/");
                HashMap<String, String> hashMap1 = new HashMap<>();
                String s3 = split3[4];
                String s4 = split3[5];
                hashMap1.put("order_no", s3);
                hashMap1.put("hb_fq_num", "3");
                mPresenter.getPay(hashMap1);
                break;
            case R.id.pay_hualin6:
                String[] split6 = sUrl.split("/");
                String s6 = split6[4];
                String s7 = split6[5];
                HashMap<String, String> hashMap2 = new HashMap<>();
                //   hashMap2.put("order_no", order_no);
                hashMap2.put("hb_fq_num", "6");
                mPresenter.getPay(hashMap2);
                break;
            case R.id.pay_hualin12:
                String[] split12 = sUrl.split("/");
                String s12 = split12[4];
                String s13 = split12[5];
                HashMap<String, String> hashMap3 = new HashMap<>();
                hashMap3.put("order_no", s12);
                hashMap3.put("hb_fq_num", "12");
                mPresenter.getPay(hashMap3);
                break;
    }*/
}
