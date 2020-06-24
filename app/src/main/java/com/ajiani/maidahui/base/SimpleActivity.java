package com.ajiani.maidahui.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.MessageUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.receiver.MyReceiver;
import com.umeng.analytics.MobclickAgent;
import com.wang.avi.AVLoadingIndicatorView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class SimpleActivity extends AppCompatActivity {

    private Unbinder bind;

    public PopupWindow popupWindow;
    private AVLoadingIndicatorView spinKitView;
    private MyReceiver myReceiver;

    //定义广播接收者 来进行接收消息，进行弹窗
   String str;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        create(savedInstanceState);
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // 必须
        setContentView(createLayout());
        setStatuBar();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        bind = ButterKnife.bind(this);
        createPresenter();
        View inflate = LayoutInflater.from(this).inflate(R.layout.progress_item, null, false);
        spinKitView = inflate.findViewById(R.id.av);

        popupWindow = new PopupWindow();
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(inflate);
        if(EventBus.getDefault().isRegistered(this)){

        }else{
            EventBus.getDefault().register(this);
        }




        initView();
        initData();


    }

    public void setStatuBar() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(int reciverBean) {
        Log.i("wxy", "getMessage: asdasdasdas");
    }


    public void create(Bundle savedInstanceState) {

    }



    /**
     * 创建View并启动动画
     */
    @SuppressLint(" ")
    private void createAndStart() {
        /*创建提示消息View*/
        final View view = LayoutInflater.from(this).inflate(R.layout.notifitiom_item, null);
        /*创建属性动画,从下到上*/
        ObjectAnimator bottomToTop = ObjectAnimator.ofFloat(view, "translationY", 0, -dp2px(80)).setDuration(500);
        /*创建属性动画,从上到下*/
        ObjectAnimator topToBottom = ObjectAnimator.ofFloat(view, "translationY", -dp2px(80), 0).setDuration(500);
        /*初始化动画组合器*/
        AnimatorSet animator = new AnimatorSet();
        /*组合动画*/
        animator.play(bottomToTop).after(topToBottom).after(2000);
        /*添加动画结束回调*/
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                /*删除View*/
                MyApp.getApp().hideView(view);
            }
        });
        /*添加View到当前显示的Activity*/
       MyApp.getApp().showView(view);
        /*启动动画*/
        animator.start();
    }

    /**
     * 从dp单位转换为px
     *
     * @param dp dp值
     * @return 返回转换后的px值
     */
    private int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public void show(){
       popupWindow.showAtLocation(spinKitView, Gravity.CENTER,0,0);
    }
    public void diss(){
        popupWindow.dismiss();
    }
    protected void createPresenter() {

    }




    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("1");
        registerReceiver(myReceiver,filter);
         if(HomeActivity.context!=null){
             MessageUtils.instance().messageLinister(HomeActivity.context,this.getWindow().getDecorView());
         }


    }



    protected abstract void initData();

    protected abstract void initView();

    protected abstract int createLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
       if(bind!=null){
           bind.unbind();
       }
       EventBus.getDefault().unregister(this);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
      /*  if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 判断连续点击事件时间差
            if (DoubleClickUtil.isFastClick()) {
                return true;
            }
        }*/
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
         back();
    }

    public void back() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
        MobclickAgent.onPause(this);
        MessageUtils.messageUtils=null;
    }
    
    /**

     * 监听Back键按下事件,方法2:
     *
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);

    }
        @Override
    public boolean onTouchEvent(MotionEvent event) {
       /* if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 判断连续点击事件时间差
            if (DoubleClickUtil.isFastClick()) {
                return true;
            }
        }*/
        return super.onTouchEvent(event);
    }
}
