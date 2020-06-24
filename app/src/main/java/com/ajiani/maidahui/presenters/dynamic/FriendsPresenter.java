package com.ajiani.maidahui.presenters.dynamic;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.dynamic.FriendListIn;

import java.util.HashMap;

public class FriendsPresenter extends BasePresenterImp<FriendListIn.friendsListView> implements FriendListIn.friendsListPresenter {
    @Override
    public void getData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class)
                .friendsList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.friendsListSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.friendsListError(string);
                    }
                });
    }
}
