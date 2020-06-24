package com.ajiani.maidahui.presenters.dynamic;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.dynamic.FollowUserIn;

import java.util.HashMap;

public class FollowPresenter extends BasePresenterImp<FollowUserIn.followUserView> implements FollowUserIn.followPresenter {
    @Override
    public void getFollowData(HashMap<String, String> map) {
       HashMap<String, String> hashMap = Params.setParams2();
       hashMap.putAll(map);
       HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class)
                .followUser(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.FollowUserSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }
}
