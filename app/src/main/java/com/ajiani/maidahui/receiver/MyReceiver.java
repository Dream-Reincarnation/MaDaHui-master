package com.ajiani.maidahui.receiver;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.activity.chat.ChatActivity;
import com.ajiani.maidahui.activity.chat.ChatStarActivity;
import com.ajiani.maidahui.bean.sockets.NotificaBean;
import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.TimeZone;

public class MyReceiver extends BroadcastReceiver {
    @SuppressLint("WrongConstant")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("WXY", "onReceive: "+intent.getAction());
        //弹出popupwindow
   /*     PopupWindow popupWindow = new PopupWindow(MyApp.activity);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        View inflate = LayoutInflater.from(MyApp.activity).inflate(R.layout.notifitiom_item, null, false);
        popupWindow.setContentView(inflate);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);*/

        Bundle bundles = intent.getBundleExtra("bundles");
        NotificaBean notificaBean= (NotificaBean) bundles.getSerializable("sss");
        Log.i("WXY", "onReceive: "+notificaBean.getContent());
       createAndStart(context,notificaBean);

    }
    @SuppressLint("InflateParams")
    private void createAndStart(Context context,NotificaBean notificaBean) {
        /*创建提示消息View*/
        final View view = LayoutInflater.from(context).inflate(R.layout.notifitiom_item, null,false);
        TextView time = view.findViewById(R.id.notifica_time);
        ImageView head = view.findViewById(R.id.notifica_head);
        TextView content = view.findViewById(R.id.notifica_content);
        LinearLayout linearLayout = view.findViewById(R.id.view_top_msg_parent);
        String type = notificaBean.getType();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("system")){

                }else if(type.equals("freight")){
                    //跳转聊天页面，
                    String shopId = notificaBean.getShopId();
                    Bundle bundle = new Bundle();
                    if(Integer.parseInt(shopId)>0){
                        bundle.putString("uid",notificaBean.getShopId());
                        bundle.putString("name",notificaBean.getName());
                        bundle.putString("avart",notificaBean.getHeadUrl());
                        JumpUtils.gotoActivity(context, Chat2Activity.class,bundle);
                        MyApp.getApp().hideView(view);
                     }else{
                        bundle.putString("uid",notificaBean.getId());
                        bundle.putString("name",notificaBean.getName());
                        JumpUtils.gotoActivity(context, ChatActivity.class,bundle);
                        MyApp.getApp().hideView(view);
                    }
                }else{
                    //跳转服务通知
                    JumpUtils.gotoActivity(context, ChatStarActivity.class);
                    MyApp.getApp().hideView(view);
                }
            }
        });
        TextView name = view.findViewById(R.id.notifica_name);
        //判断是什么消息类型
        // system 系统   freight 私信   service 广播
        if(type.equals("system")){
            Glide.with(context).load(R.mipmap.messageaide).into(head);
            name.setText("公告通知");
        }else if(type.equals("freight")){
            Glide.with(context).load(notificaBean.getHeadUrl()).into(head);
            name.setText(notificaBean.getName());
        }else{
            Glide.with(context).load(R.mipmap.messageaide).into(head);
            name.setText("服务通知");
        }
        Calendar calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        time.setText(calendars.get(Calendar.HOUR)+":"+calendars.get(Calendar.MINUTE));
        content.setText(notificaBean.getContent());
        /*创建属性动画,从下到上*/
        ObjectAnimator bottomToTop = ObjectAnimator.ofFloat(view, "translationY", 0, 0).setDuration(500);
        /*创建属性动画,从上到下*/
        ObjectAnimator topToBottom = ObjectAnimator.ofFloat(view, "translationY", 0, 0).setDuration(500);
        /*初始化动画组合器*/
        AnimatorSet animator = new AnimatorSet();
        /*组合动画*/
        animator.play(topToBottom).after(bottomToTop).after(1000);
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
}
