package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.CastIn;

import java.util.HashMap;

public class CastPresenter extends BasePresenterImp<CastIn.CastView> implements CastIn.casePresenter {
    @Override
    public void getCastData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .castList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.castSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }

    @Override
    public void getDetailsData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .castDetails(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.detailsSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
}
