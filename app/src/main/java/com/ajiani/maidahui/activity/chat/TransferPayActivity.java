package com.ajiani.maidahui.activity.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.chat.TransferBean;
import com.ajiani.maidahui.bean.mine.VoteBean;
import com.ajiani.maidahui.mInterface.Payin;
import com.ajiani.maidahui.mInterface.chat.UserMessageList;
import com.ajiani.maidahui.presenters.chat.MessageListPresenter;
import com.ajiani.maidahui.presenters.mine.PayPresenter;
import com.ajiani.maidahui.weight.SafeKeyboard;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferPayActivity extends BaseActivity<UserMessageList.MessageListView, MessageListPresenter> implements UserMessageList.MessageListView {
    @BindView(R.id.psw_back)
    ImageView pswBack;
    @BindView(R.id.psw_ed)
    EditText pswEd;
    @BindView(R.id.psw_pay)
    Button pswPay;
    @BindView(R.id.keyboardViewPlace)
    LinearLayout keyboardViewPlace;
    @BindView(R.id.main_root)
    FrameLayout mainRoot;
    private SafeKeyboard safeKeyboard;
    private String order_no;
    private String shop_id;
    private String votes;
    private String remark;


    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        pswEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (pswEd.getText().toString().length() >= 6) {
                    pswPay.setBackgroundResource(R.color.Thme);
                    pswPay.setTextColor(Color.WHITE);

                } else {
                    pswPay.setBackgroundResource(R.color.pay);
                    pswPay.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {



        //  showInput(pswEd);
        popupInputMethodWindow(pswEd);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        getWindow().setEnterTransition(new Slide().setDuration(500));
        getWindow().setExitTransition(new Slide().setDuration(500));

        View rootView = findViewById(R.id.main_root);
        LinearLayout keyboardContainer = findViewById(R.id.keyboardViewPlace);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(this).inflate(R.layout.layout_keyboard_containor, null);
        safeKeyboard = new SafeKeyboard(getApplicationContext(), keyboardContainer,
                R.layout.layout_keyboard_containor, view.findViewById(R.id.safeKeyboardLetter).getId(), rootView);
        safeKeyboard.putEditText(pswEd.getId(), pswEd);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        shop_id = bundle.getString("shop_id");
        votes = bundle.getString("votes");
        remark = bundle.getString("remark");

    }

    private void popupInputMethodWindow(EditText editText) {
      /*  handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager=(InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
               inputMethodManager.showSoftInput(editText, 0);
            }
        }, 0);*/
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //设置可获得焦点


                //调用系统输入法
                InputMethodManager inputManager = (InputMethodManager) editText
                        .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 200);
    }

    public void showInput(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    protected int createLayout() {
        return R.layout.pay_psw;
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.psw_back, R.id.psw_pay,R.id.add_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.psw_back:
                finishAfterTransition();
                break;
            case R.id.psw_pay:
                String s = pswEd.getText().toString();
                String pay_pass = (String) SPUtils.get(this, "pass", "");
                Log.i("wxy", "onViewClicked: " + pay_pass);
                String userid = (String) SPUtils.get(this, "userid", "");
                String s1 = Md5Utils.MD5(userid + s);
                if (s1.equals(pay_pass)) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("shop_id", shop_id);
                        hashMap.put("votes",votes);
                        hashMap.put("remark", remark);
                        mPresenter.getVoteData(hashMap);

                } else {
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add_lin:
                finishAfterTransition();
                break;
        }
    }


    @Override
    protected MessageListPresenter preparePresenter() {
        return new MessageListPresenter();
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
        intent.putExtra("text", remark);
        setResult(1024, intent);
        finishAfterTransition();
    }

    @Override
    public void delMessageSuccess(String success) {

    }
}
