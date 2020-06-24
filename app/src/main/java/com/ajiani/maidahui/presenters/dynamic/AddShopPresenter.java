package com.ajiani.maidahui.presenters.dynamic;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.dynamic.AddshopIn;

import java.util.HashMap;

public class AddShopPresenter extends BasePresenterImp<AddshopIn.AddshopView> implements AddshopIn.AddShopPresenter {
    @Override
    public void getShopList(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class)
                .shopList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.shopListSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.shopListError(string);
                    }
                });
    }

    @Override
    public void getCollectionStore(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class)
                .collectionStore(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.collectionStoreSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.collectionStoreError(string);
                    }
                });
    }

    @Override
    public void getCollection(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class)
                .collectionShip(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.collectionSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.collectionError(string);
                    }
                });
    }
}
