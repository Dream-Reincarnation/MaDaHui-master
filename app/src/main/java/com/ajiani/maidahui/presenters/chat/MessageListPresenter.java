package com.ajiani.maidahui.presenters.chat;

import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.ChatServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.chat.UserMessageList;

import java.util.HashMap;

public class MessageListPresenter extends BasePresenterImp<UserMessageList.MessageListView> implements  UserMessageList.messagePresenter {
    @Override
    public void getMessageList(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .getMessageList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        if(string.equals("-201")){
                           // JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());

                        }else{
                            mView.messageSuccess(string);
                        }

                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getServiceCommnet(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .serviceComment(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.serviceCommentSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getServiceMessage(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .getServiceMessage(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.serviceMessageSucess(string);
                    }

                    @Override
                    protected void onError(String string) {
                   mView.error(string);
                    }
                });
    }

    @Override
    public void getVoteData(HashMap<String, String> map) {
         HashMap<String, String> hashMap = Params.setParams2();
         hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
         HttpManager.instance().getServer(ChatServer.class)
                 .toVotes(sign)
                 .compose(RxUtils.rxScheduleThread())
                 .subscribe(new BaseObserver() {
                     @Override
                     protected void onSuccess(String string) {
                         mView.voteSuccess(string);
                     }

                     @Override
                     protected void onError(String string) {

                     }
                 });
    }

    @Override
    public void getDelData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(ChatServer.class)
                .delMsgId(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.delMessageSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
}
