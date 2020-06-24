package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.VerifyIn;

import java.util.HashMap;

public class VerifyPresenter extends BasePresenterImp<VerifyIn.verifyView> implements  VerifyIn.verifyPresenter {
    @Override
    public void mailData(HashMap<String, String> map) {
        //获取
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .sendMailVertify(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.mailVerify(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }

    @Override
    public void phoneData(HashMap<String, String> map) {
         HashMap<String, String> hashMap = Params.setParams();
         hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
         HttpManager.instance().getServer(MyServer.class)
                 .getVerifty(sign)
                 .compose(RxUtils.rxScheduleThread())
                 .subscribe(new BaseObserver() {
                     @Override
                     protected void onSuccess(String string) {
                         mView.phoneVerify(string);
                     }

                     @Override
                     protected void onError(String string) {
                         mView.error(string);
                     }
                 });
    }

    @Override
    public void verifyData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class)
                .findPass(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.verifySuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
}
