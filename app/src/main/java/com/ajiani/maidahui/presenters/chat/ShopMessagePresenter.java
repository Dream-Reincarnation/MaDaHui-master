package com.ajiani.maidahui.presenters.chat;

import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.ChatServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.chat.ShopListIn;

import java.util.HashMap;

public class ShopMessagePresenter extends BasePresenterImp<ShopListIn.shopMessageView> implements  ShopListIn.shopMessagePresenter {
    @Override
    public void getShopMessage(HashMap<String, String> map) {
         HashMap<String, String> hashMap = Params.setParams2();
         hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .getShopMessage(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        if(string.equals("201")){
                       //     JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());

                        }else{
                            mView.shopMessageSuccess(string);
                        }

                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getDelData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .delShopMessage(sign)
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
    public void getDelMsgData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .delMessage(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.delMsgSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
}
