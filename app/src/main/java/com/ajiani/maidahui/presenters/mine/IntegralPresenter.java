package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.IntegralIn;

import java.util.HashMap;

public class IntegralPresenter extends BasePresenterImp<IntegralIn.IntegralView> implements IntegralIn.integralPresenter {
    @Override
    public void getData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .getIntegral(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.integralSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getRestant(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .mineRestant(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.restantSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }
}
