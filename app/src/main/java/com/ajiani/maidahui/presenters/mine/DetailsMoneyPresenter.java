package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.DetaileIn;

import java.util.HashMap;

public class DetailsMoneyPresenter extends BasePresenterImp<DetaileIn.detaileView> implements DetaileIn.detailsPresenter {
    @Override
    public void getEar(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class).getEraDetails(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                           mView.earSuccess(string);
                     }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }
}
