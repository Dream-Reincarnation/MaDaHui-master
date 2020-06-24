package com.ajiani.maidahui.presenters;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.FindPass;

import java.util.HashMap;

public class FindPswPresenter extends BasePresenterImp<FindPass.findPassView> implements FindPass.findPassPresenter {
    @Override
    public void setData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.putAll(map);
        HashMap<String, String> map2 = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).findPass(map2).compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.success(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }

                });
    }

    @Override
    public void setData2(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.putAll(map);
        HashMap<String, String> map2 = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).getVerifty(map2).compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.successVerfity(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }

                });
    }
}
