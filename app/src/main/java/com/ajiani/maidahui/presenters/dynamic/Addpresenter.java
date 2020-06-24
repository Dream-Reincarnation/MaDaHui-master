package com.ajiani.maidahui.presenters.dynamic;

import android.util.Log;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.Globals;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.mInterface.dynamic.AddressIn;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Addpresenter extends BasePresenterImp<AddressIn.AddressView> implements AddressIn.AddressPresenter {
    @Override
    public void getData(HashMap<String, String> map) {
        HttpManager.instance().getRetrofit(Globals.Search).create(DynamicServer.class)
                .searchAddress(map)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                        mView.addressSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wxy", "onNext: "+e.getMessage());
                        mView.addressError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
