package com.ajiani.maidahui.activity.mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.MoneyUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.adapter.mine.RechargeAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.mine.OrderBean;
import com.ajiani.maidahui.bean.mine.PayParameBean;
import com.ajiani.maidahui.bean.mine.RuleBean;
import com.ajiani.maidahui.bean.mine.WeChatBean;
import com.ajiani.maidahui.mInterface.Payin;
import com.ajiani.maidahui.presenters.mine.PayPresenter;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity<Payin.payView, PayPresenter> implements Payin.payView, View.OnClickListener {

    @BindView(R.id.recharge_back)
    ImageView rechargeBack;
    @BindView(R.id.recharge_lin)
    LinearLayout rechargeLin;
    @BindView(R.id.rechage_jewel)
    TextView rechageJewel;
    @BindView(R.id.recharge_rlt)
    RelativeLayout rechargeRlt;
    @BindView(R.id.recharge_rel)
    RecyclerView rechargeRel;
    private int aid;
    private String order_no;
    private String orderString;
    private RechargeAdapter rechargeAdapter;
    private TextView hua3;
    private TextView hua33;
    private TextView hua6;
    private TextView hua66;
    private TextView hua12;
    private TextView hua122;
    private PopupWindow popupWindow;
    private WindowManager.LayoutParams lp;

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        popupWindow = new PopupWindow(RechargeActivity.this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(dip2px(RechargeActivity.this, 440));
        View inflate = LayoutInflater.from(this).inflate(R.layout.pay_item, null, false);
        popupWindow.setContentView(inflate);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);//
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
              getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
              getWindow().setAttributes(lp);
            }
        });
        hua3 = inflate.findViewById(R.id.pay_hua3);
        hua33 = inflate.findViewById(R.id.pay_hua33);
        hua6 = inflate.findViewById(R.id.pay_hua6);
        hua66 = inflate.findViewById(R.id.pay_hua66);
        hua12 = inflate.findViewById(R.id.pay_hua12);
        hua122 = inflate.findViewById(R.id.pay_hua122);
        LinearLayout hualin3 = inflate.findViewById(R.id.pay_hualin3);
        LinearLayout hualin6 = inflate.findViewById(R.id.pay_hualin6);
        LinearLayout hualin12 = inflate.findViewById(R.id.pay_hualin12);
        TextView money = inflate.findViewById(R.id.pay_money);
        LinearLayout yue = inflate.findViewById(R.id.pay_yue);
        yue.setVisibility(View.GONE);
        hualin3.setVisibility(View.GONE);
        hualin6.setVisibility(View.GONE);
        hualin12.setVisibility(View.GONE);
        LinearLayout wechat = inflate.findViewById(R.id.pay_wechat);
        LinearLayout zhifu = inflate.findViewById(R.id.pay_zhifu);
        yue.setOnClickListener(this);
        wechat.setOnClickListener(this);
        zhifu.setOnClickListener(this);
        hualin3.setOnClickListener(this);
        hualin6.setOnClickListener(this);
        hualin12.setOnClickListener(this);

        rechargeAdapter.setOnClickLinstener(new RechargeAdapter.onClickLinstener() {

            private int money;

            @Override
            public void onClick(int posstion) {
                //创建订单
                int aid = rechargeAdapter.mList.get(posstion).getAid();

                money = rechargeAdapter.mList.get(posstion).getMoney();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("rule_id", aid + "");

                float ben3 = MoneyUtils.getBen((float) (money * 1.00), 3);
                float ben6 = MoneyUtils.getBen((float) (money * 1.00), 6);
                float ben12 = MoneyUtils.getBen((float) (money * 1.00), 12);
                float li3 = MoneyUtils.getli((float) (money * 1.00), 3);
                float li6 = MoneyUtils.getli((float) (money * 1.00), 6);
                float li12 = MoneyUtils.getli((float) (money * 1.00), 12);
                DecimalFormat mFormat = new DecimalFormat("0.00");
                String formatNum3 = mFormat .format((ben3+li3));
                String formatNum6 = mFormat .format((ben6+li6));
                String formatNum12 = mFormat .format((ben12+li12));
                String interest3 = mFormat.format(li3);
                String interest6 = mFormat.format(li6);
                String interest12 = mFormat.format(li12);
                hua3.setText(formatNum3+"");
                hua6.setText(formatNum6+"");
                hua12.setText(formatNum12+"");
                hua33.setText(interest3);
                hua66.setText(interest6);
                hua122.setText(interest12);

                popupWindow.showAtLocation(rechageJewel, Gravity.BOTTOM, 0, 0);
                lp = getWindow().getAttributes();
                lp.alpha = 0.7f;//代表透明程度，范围为0 - 1.0f
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);


                mPresenter.getOrder(hashMap);
            }
        });
    }

    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String recharge = bundle.getString("recharge");
        rechageJewel.setText(recharge);
        rechargeRel.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<RuleBean.DataBean> dataBeans = new ArrayList<>();
        rechargeAdapter = new RechargeAdapter(dataBeans);
        rechargeRel.setAdapter(rechargeAdapter);
        //得到充值规则

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("is_ios", "0");
        mPresenter.getRule(hashMap);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_recharge;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            popupWindow.dismiss();
            //{ when=-132ms what=1 obj={resultStatus=9000, result={"alipay_trade_app_pay_response":{"code":"10000","msg":"Success","app_id":"2019061765629232","auth_app_id":"2019061765629232","charset":"UTF-8","timestamp":"2019-08-30 17:16:03","out_trade_no":"RC_B8308170803376577","total_amount":"1.00","trade_no":"2019083022001427950536975798","seller_id":"2088331613883961"},"sign":"FbFRV85bVYV1ZrXmF+7w3xNRE5V66vdsRF0B/gvFjqi6LUneX21+2fekZQC6EEMIzn4WFPOTEMyl/86Nt/AvcvnPpo7YXE8kkTKazf3kMIYRntBvY0Cb4DVFmkD6qtZTXUr7RQV7hh5F33Cpp8A3N0wCJy8nIvYLCPZoTxeDvVkgwrshXyVIC7VHBL3E5pWSeyZRu7A9JzFjCiCOfXXmIS/xGAS7QbsIjzaTyMrBixvwi7Tw/yI124/ojQnvrzR5D1n/MLZW3C6go75qRE+Cj7Aj/eRZorKlzqmErqIRe/EHZ/TBTZDPI4CNoC7K/6+XgllZ/HfyKtxrU3In6I+pgw==","sign_type":"RSA2"}, memo=, extendInfo={"doNotExit":true,"isDisplayResult":true}} target=com.ajiani.maidahui.activity.mine.RechargeActivity$1 }
            switch (msg.what) {
                case 1:
                 /*  HashMap<String,String> result = new Result((String) msg.obj);
                   Toast.makeText(DemoActivity.this, result.getResult(),
                           Toast.LENGTH_LONG).show();*/
                    Map<String, String> map = (Map<String, String>) msg.obj;
                    Set<Map.Entry<String, String>> entrySet = map.entrySet();
                    for (Map.Entry<String, String> entry : entrySet) {
                        String key = entry.getKey();
                        String value = entry.getValue();

                    }
                    String s = map.get("resultStatus");
                    if(s.equals("9000")){
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    }else if(s.equals("6001")){
                        //用户中途取消
                        Toast.makeText(RechargeActivity.this, "操作取消", Toast.LENGTH_SHORT).show();
                    }else if(s.equals("4000")){
                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }else if(s.equals("6002")){
                        Toast.makeText(RechargeActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
                    }
                    Log.i("WXY", "handleMessage: " +s);
                    break;


            }
        }
    };
    @Override
    protected PayPresenter preparePresenter() {
        return new PayPresenter();
    }

    @Override
    public void getSuccess(String success) {
        RuleBean ruleBean = new Gson().fromJson(success, RuleBean.class);
        aid = ruleBean.getData().get(0).getAid();
        //创建订单

        List<RuleBean.DataBean> data = ruleBean.getData();
        rechargeAdapter.mList.addAll(data);
        rechargeAdapter.notifyDataSetChanged();


    }

    @Override
    public void createSuccess(String create) {
        OrderBean orderBean = new Gson().fromJson(create, OrderBean.class);
        order_no = orderBean.getData().getOrder_no();
        //获取支付参数


    }

    @Override
    public void parameterSuccess(String parameter) {
        PayParameBean payParameBean = new Gson().fromJson(parameter, PayParameBean.class);
        orderString = payParameBean.getData().getOrderString();
       //掉漆支付宝
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
                Map<String,String> result = alipay.payV2(orderString,true);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void voteSuccess(String success) {

    }

    @Override
    public void weChatSuccess(String success) {
        WeChatBean weChatBean = new Gson().fromJson(success, WeChatBean.class);
        WeChatBean.DataBean data = weChatBean.getData();

        final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        msgApi.registerApp("wxe52595c56f1bfd66");
        //调起微信支付
        PayReq req = new PayReq();
        req.appId           = data.getAppid();
        req.partnerId       = data.getPartnerid();//商户号
        req.prepayId        = data.getPrepayid();//预支付交易会话ID
        req.nonceStr        = data.getNoncestr();//随机字符串
        req.timeStamp       = data.getTimestamp()+"";//时间戳
        req.packageValue    =data.getPackageX();//扩展字段,这里固定填写
        req.sign            = data.getSign();//签名
//              req.extData         = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        //api.registerApp()
        msgApi.sendReq(req);
    }

    @OnClick({R.id.recharge_back, R.id.recharge_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recharge_back:
                finish();
                break;
            case R.id.recharge_lin:
                //跳转到明细界面

                JumpUtils.gotoActivity(this,EarDetaile.class);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_yue:
                String pay_pass = (String) SPUtils.get(this, "pay_pass", "");
                if (pay_pass.length() < 2) {
                    //跳转设置支付密码界面
                    JumpUtils.gotoActivity(this, SetPayPass.class);
                } else {
                Intent intent = new Intent(this, PasswordPay.class);
                Bundle bundle = new Bundle();
                bundle.putString("order_no", order_no);
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, 2345);
                popupWindow.dismiss();}
                break;
            case R.id.pay_zhifu:
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("order_no", order_no);
                // hashMap.put("hb_fq_num","3");
                mPresenter.getPay(hashMap);
                break;
            case R.id.pay_wechat:
                HashMap<String, String> weChat = new HashMap<>();
                weChat.put("order_no", order_no);
                mPresenter.getWeChat(weChat);
                break;
            case R.id.pay_hualin3:
                HashMap<String, String> hashMap1 = new HashMap<>();
                hashMap1.put("order_no", order_no);
                 hashMap1.put("hb_fq_num","3");
                mPresenter.getPay(hashMap1);
                break;
            case R.id.pay_hualin6:
                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put("order_no", order_no);
                 hashMap2.put("hb_fq_num","6");
                mPresenter.getPay(hashMap2);
                break;
            case R.id.pay_hualin12:
                HashMap<String, String> hashMap3 = new HashMap<>();
                hashMap3.put("order_no", order_no);
                 hashMap3.put("hb_fq_num","12");
                mPresenter.getPay(hashMap3);
                break;
        }
    }
}
