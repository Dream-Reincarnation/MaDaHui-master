package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.MailBoxIn;

import java.util.HashMap;

public class MailBoxPersenter extends BasePresenterImp<MailBoxIn.MainView> implements MailBoxIn.mailBoxPresenter {
    @Override
    public void mailVertifyData(HashMap<String, String> map) {
         HashMap<String, String> hashMap = Params.setParams2();
         hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .sendMailVertify(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.mailVertifySuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }

    @Override
    public void mailBindData(HashMap<String, String> map) {
          HashMap<String, String> hashMap = Params.setParams2();
          hashMap.putAll(map);
          HashMap<String, String> sign = Params.getSign(hashMap);
          HttpManager.instance().getServer(MineServer.class)
                  .bindMailBox(sign)
                  .compose(RxUtils.rxScheduleThread())
                  .subscribe(new BaseObserver() {
                      @Override
                      protected void onSuccess(String string) {
                          mView.mailbindSuccess(string);
                      }

                      @Override
                      protected void onError(String string) {
                            mView.error(string);
                      }
                  });
    }
}
