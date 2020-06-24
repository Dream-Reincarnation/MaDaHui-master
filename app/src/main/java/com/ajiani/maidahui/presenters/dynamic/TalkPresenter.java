package com.ajiani.maidahui.presenters.dynamic;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.dynamic.AddTalk;

import java.util.HashMap;

public class TalkPresenter extends BasePresenterImp<AddTalk.talkView> implements AddTalk.talkPresenter {
    @Override
    public void getTalk(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
           HttpManager.instance().getServer(DynamicServer.class).getTalke(sign)
                           .compose(RxUtils.rxScheduleThread())
                           .subscribe(new BaseObserver() {
                               @Override
                               protected void onSuccess(String string) {
                                   mView.talkSuccess(string);
                               }

                               @Override
                               protected void onError(String string) {
                                    mView.error(string);
                               }
                           });
    }
}
