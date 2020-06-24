package com.ajiani.maidahui.presenters.chat;

import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.ChatServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.chat.FansListIn;

import java.util.HashMap;

public class FansPresenter extends BasePresenterImp<FansListIn.fansListView> implements FansListIn.fansListPresenter {
    @Override
    public void getFansData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .getMineFans(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        if(string.equals("-201")){
//                            JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());

                        }else{
                            mView.fansSuccess(string);
                        }

                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }
}
