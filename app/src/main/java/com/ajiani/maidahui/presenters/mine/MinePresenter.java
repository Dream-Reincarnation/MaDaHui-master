package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.mine.MoreCommentActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.MineInfo;

import java.util.HashMap;

public class MinePresenter extends BasePresenterImp<MineInfo.mineView> implements MineInfo.minePresenter {
    @Override
    public void getVideo(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> map2 = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class).getmineVideo(map2)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        if (mView != null) {
                            mView.videoSuccess(string);
                        }
                    }

                    @Override
                    protected void onError(String string) {

                        mView.error(string);
                    }
                });
    }

    @Override
    public void getUserInfo(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .getUserInfo(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {

                        mView.userInfo(string);

                    }

                    @Override
                    protected void onError(String string) {
                        if (string.equals("201")) {
                            //   JumpUtils.gotoLoginActivity(MyApp.getApp().getApplicationContext());
                            return;
                        } else {
                            mView.error(string);
                        }
                    }
                });
    }

    @Override
    public void getLoveList(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .getLoveList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.loveListSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                        mView.error(string);
                    }
                });
    }

    @Override
    public void getCount(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .getCount(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.getCountSuccess(string);
                    }


                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
}
