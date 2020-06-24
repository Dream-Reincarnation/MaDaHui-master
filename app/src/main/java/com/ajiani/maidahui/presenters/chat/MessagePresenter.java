package com.ajiani.maidahui.presenters.chat;

import android.util.Log;
import android.widget.Toast;

import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.ChatServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.chat.MessageList;

import java.util.HashMap;

public class MessagePresenter extends BasePresenterImp<MessageList.MessageView> implements MessageList.MessagePresenter {
    @Override
    public void getUserList(HashMap<String, String> map) {
       HashMap<String, String> hashMap = Params.setParams2();
       hashMap.putAll(map);
       HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .getUserList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {

                            mView.UserListSuccess(string);

                    }

                    @Override
                    protected void onError(String string) {
                        Log.i("WXY", "onError: "+string);
                    }
                });

    }

    @Override
    public void getServiceList(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .getServicesList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        if(string.equals("-201")){
                           // JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());

                        }else{
                            mView.serviceListSuccess(string);
                        }

                    }

                    @Override
                    protected void onError(String string) {
                        Log.i("WXY", "onError: "+string);
                    }
                });

    }

    @Override
    public void getSystemList(HashMap<String, String> map) {
         HashMap<String, String> hashMap = Params.setParams2();
         hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
         HttpManager.instance().getServer(ChatServer.class).getSystemNum(sign)
                 .compose(RxUtils.rxScheduleThread())
                 .subscribe(new BaseObserver() {
                     @Override
                     protected void onSuccess(String string) {
                         mView.systemSuccess(string);
                     }

                     @Override
                     protected void onError(String string) {
                            mView.error(string);
                     }
                 });
    }

    @Override
    public void getDelMessage(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class).delUserMesage(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.delMessageSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }

    @Override
    public void getData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class).delShopMesage(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.delShopMessageSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
}
