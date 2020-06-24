package com.ajiani.maidahui.presenters;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.ParamsIn;

import java.util.HashMap;

public class ParamsPresenter extends BasePresenterImp<ParamsIn.ParamsView> implements ParamsIn.paramsPresenter {
    @Override
    public void getParamData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).getParams(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.paramSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                      mView.error(string);
                    }
                });
    }
}
