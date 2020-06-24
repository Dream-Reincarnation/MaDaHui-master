package com.ajiani.maidahui.presenters.chat;

import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.ChatServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.chat.BoardListIn;

import java.util.HashMap;

public class BoardPresenter extends BasePresenterImp<BoardListIn.boardListView> implements BoardListIn.boardListPresenter {
    @Override
    public void boardListData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class).getBoardList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        if(string.equals("-201")){
                            //JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());

                        }else{
                            mView.boardListSuccess(string);
                        }

                    }

                    @Override
                    protected void onError(String string) {
                        if(string.equals("-201")){
                           // JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());
                        }else{
                            mView.error(string);
                        }

                    }
                });
    }

    @Override
    public void getDelData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class).delMessage(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        if(string.equals("-201")){
                            //JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());

                        }else{
                            mView.delMessage(string);
                        }

                    }

                    @Override
                    protected void onError(String string) {
                        if(string.equals("-201")){
                            // JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());
                        }else{
                            mView.error(string);
                        }

                    }
                });
    }
}
