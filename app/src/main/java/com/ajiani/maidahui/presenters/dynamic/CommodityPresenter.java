package com.ajiani.maidahui.presenters.dynamic;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.dynamic.CommodityListIn;

import java.util.HashMap;

public class CommodityPresenter extends BasePresenterImp<CommodityListIn.CommodityListView>implements CommodityListIn.CommoDityPresenterIml {
    @Override
    public void getCommodityData(HashMap<String, String> map) {
         HashMap<String, String> hashMap = Params.setParams2();
         hashMap.putAll(map);
          HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class).getCommodityList(sign)
                .compose(RxUtils.rxScheduleThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    protected void onSuccess(String string) {
                                        mView.commoditySuccess(string);
                                    }

                                    @Override
                                    protected void onError(String string) {

                                    }
                                });
    }
}
