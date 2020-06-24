package com.ajiani.maidahui.activity.mine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.share.PopupWindows;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.mInterface.mine.CastIn;
import com.ajiani.maidahui.presenters.mine.CastPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//
/*   
 * 提现页面
 * */
public class WithdrawActivity extends BaseActivity<CastIn.CastView, CastPresenter> implements CastIn.CastView {
    @BindView(R.id.withdraw_ed)
    EditText withdrawEd;
    @BindView(R.id.withdraw_farm)
    FrameLayout withdraw_farm;
    @BindView(R.id.alipay_lin)
    LinearLayout alipayLin;
    @BindView(R.id.wechat_lin)
    LinearLayout wechatLin;
    @BindView(R.id.withdraw_bt)
    Button withdrawBt;
    @BindView(R.id.alipay)
    ImageView alipay;
    @BindView(R.id.wechat)
    ImageView wechat;
    boolean b=true;
    @BindView(R.id.withdraw_edpas)
    TextView withdrawEdpas;
    @BindView(R.id.withdraw_text)
    TextView withdrawText;
    private String votes;

    @Override
    protected CastPresenter preparePresenter() {
        return new CastPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        Drawable background = withdrawBt.getBackground();
        Drawable drawable = DrawUtils.setSolid(R.color.mine_unselect, background);
        withdrawBt.setBackground(drawable);
        withdrawBt.setEnabled(false);


        listenerMoney(withdrawEd);

       /* withdrawEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

    }

    @Override
    protected void initView() {
        votes = (String) SPUtils.get(this, "votes", "");
        withdrawText.setText("可提现金额￥"+ votes);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void castSuccess(String success) {

    }

    @Override
    public void detailsSuccess(String success) {

    }
    /**
     * 监听输入最小金额为0.01
     * 且只能输入两位小数
     */
    private void listenerMoney(EditText etGoodPrice) {
        etGoodPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    return;
                }
                // 判断小数点后只能输入两位
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0, s.toString().indexOf(".") + 3);
                        etGoodPrice.setText(s);
                        etGoodPrice.setSelection(s.length());
                    }
                }
                //如果第一个数字为0，第二个不为点，就不允许输入
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etGoodPrice.setText(s.subSequence(0, 1));
                        etGoodPrice.setSelection(1);
                        return;
                    }
                    if (s.toString().length() == 4) {
                        //针对输入0.00的特殊处理
                        if (Double.valueOf(s.toString()) < 0.01) {
                            Toast.makeText(WithdrawActivity.this, "最小为0.01", Toast.LENGTH_SHORT).show();
                            etGoodPrice.setText("0.01");
                            etGoodPrice.setSelection(etGoodPrice.getText().toString().trim().length());
                        }
                    }
                }
                if(etGoodPrice.getText().toString().length()>0){
                    String vote = etGoodPrice.getText().toString();
                    float v = Float.parseFloat(vote);
                    float votes=  (v*100);
                    votes=votes*5;

                    //显示服务费
                    withdrawText.setText("服务费"+votes/10000);
                    withdraw_farm.setVisibility(View.VISIBLE);
                    Drawable background = withdrawBt.getBackground();
                    Drawable drawable = DrawUtils.setSolid(R.color.Thme, background);
                    withdrawBt.setBackground(drawable);
                    withdrawBt.setEnabled(true);

                }else{
                    withdraw_farm.setVisibility(View.GONE);
                    Drawable background = withdrawBt.getBackground();
                    Drawable drawable = DrawUtils.setSolid(R.color.mine_unselect, background);
                    withdrawBt.setBackground(drawable);
                    withdrawBt.setEnabled(false);
                    withdrawText.setText("可提现金额￥"+ votes);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.back, R.id.alipay_lin,R.id.withdraw_farm,R.id.withdraw_edpas, R.id.wechat_lin, R.id.withdraw_bt,R.id.draw_details})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.withdraw_farm:
                PopupWindows popupWindows = new PopupWindows();
                popupWindows.showPop(this,withdrawBt,"确定","温馨提示","因支付机构要求,个人提现\u0003会收取相应金额的服务费用");
                break;
            case R.id.withdraw_edpas:
                //全部提现
                withdrawEd.setText(votes);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.alipay_lin:
                b = true;
                alipay.setImageResource(R.mipmap.alerdysel);
                wechat.setImageResource(R.mipmap.unsel);
                break;
            case R.id.wechat_lin:
                alipay.setImageResource(R.mipmap.unsel);
                wechat.setImageResource(R.mipmap.alerdysel);
                b = false;
                break;
            case R.id.withdraw_bt:

                if(Float.parseFloat(withdrawEd.getText().toString())<1.0){
                    Toast.makeText(this, "最低金额不能低于1元", Toast.LENGTH_SHORT).show();
                    return;
                }
               if(Float.parseFloat(withdrawEd.getText().toString())>Float.parseFloat(votes)){
                   Toast.makeText(this, "超出本次可提现金额", Toast.LENGTH_SHORT).show();
                   return;
               }
                HashMap<String, String> hashMap = new HashMap<>();

                //得到输入的金额
                String s = withdrawEd.getText().toString();
                if (b) {
                    hashMap.put("account_type", "alipay");
                } else {
                    hashMap.put("account_type", "wechat");
                }
                hashMap.put("votes", s);
                String pass = withdrawEdpas.getText().toString();
                String pass1 = (String) SPUtils.get(this, "pass", "");
                String userid = (String) SPUtils.get(this, "userid", "");
                String s1 = Md5Utils.MD5(userid +pass);
                if(s.equals("")|pass.equals("")){
                    Toast.makeText(this, "请先输入提现金额和密码", Toast.LENGTH_SHORT).show();
                }else if(s1.equals(pass1)){
                    hashMap.put("pay_pass",pass);
                    mPresenter.getCastData(hashMap);
                }else {
                    Toast.makeText(this, "你输入的密码不正确", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.draw_details:
                JumpUtils.gotoActivity(this,WithdrawPassActivity.class);
                break;
        }
    }



}
