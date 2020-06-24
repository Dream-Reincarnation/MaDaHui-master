package com.ajiani.maidahui.presenters.mine;

import android.app.Dialog;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.CommentIn;

import java.util.HashMap;

public class CommentPresenter extends BasePresenterImp<CommentIn .commentView> implements CommentIn.commentPresenter {
    @Override
    public void commentListData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class).CommentList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                      //  mView.commentListSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void addCommentData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
         HttpManager.instance().getServer(MineServer.class)
                 .addComment(sign)
                 .compose(RxUtils.rxScheduleThread())
                 .subscribe(new BaseObserver() {
                     @Override
                     protected void onSuccess(String string) {
                       //     mView.addComment(string);
                     }

                     @Override
                     protected void onError(String string) {

                     }
                 });
    }
}
