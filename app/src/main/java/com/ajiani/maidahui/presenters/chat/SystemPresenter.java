package com.ajiani.maidahui.presenters.chat;

import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.ChatServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.chat.SystemList;

import java.util.HashMap;

public class SystemPresenter extends BasePresenterImp<SystemList.systemListView> implements SystemList.systemListPresenter {
    @Override
    public void getSystemList(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);

        HttpManager.instance().getServer(ChatServer.class)
                .getSystem(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                            mView.systemListSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
}
