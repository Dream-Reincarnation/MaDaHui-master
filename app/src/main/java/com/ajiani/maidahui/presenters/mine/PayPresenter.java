package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.Payin;

import java.util.HashMap;

public class PayPresenter extends BasePresenterImp<Payin.payView> implements Payin.payPresenter {
    private HashMap<String, String> sign;

    @Override
    public void getRule(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).getRecharge(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.getSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getOrder(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).createRecharge(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.createSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getPay(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).getAliparameter(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.parameterSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getVote(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).votesPay(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.voteSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getWeChat(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
         HttpManager.instance().getServer(MyServer.class).getWeChatPay(sign)
                 .compose(RxUtils.rxScheduleThread())
                 .subscribe(new BaseObserver() {
                     @Override
                     protected void onSuccess(String string) {
                         mView.weChatSuccess(string);
                     }

                     @Override
                     protected void onError(String string) {

                     }
                 });
    }
}
