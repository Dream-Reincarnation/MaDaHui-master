package com.ajiani.maidahui.presenters.dynamic;

import android.util.Log;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.dynamic.SearMusic;

import java.util.HashMap;

public class SearMusicPresenter extends BasePresenterImp<SearMusic.searchView> implements SearMusic.searchPresenter {
    @Override
    public void getData(HashMap<String,String> keyWord) {
          HashMap<String, String> hashMap = Params.setParams2();
      hashMap.putAll(keyWord);
      HashMap<String, String> sign = Params.getSign(hashMap);

        HttpManager.instance().getServer(DynamicServer.class).searchMusic(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        Log.i("tag", "onSuccess: "+string);
                         mView.success(string);
                    }
                    @Override
                    protected void onError(String string) {

                    }

                });
    }

    @Override
    public void getInfo(HashMap<String, String> id) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(id);
        HashMap<String, String> map = Params.getSign(hashMap);

        HttpManager.instance().getServer(DynamicServer.class).musicInfo(map)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.successInfo(string);
                    }
                    @Override
                    protected void onError(String string) {

                    }

                });
    }

    @Override
    public void getCollectionData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class).getCollectionMusic(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {

                        mView.getCollectionSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }

    @Override
    public void getCollectionList(HashMap<String, String> map) {
            HashMap<String, String> hashMap = Params.setParams2();
            hashMap.putAll(map);
            HashMap<String, String> sign = Params.getSign(hashMap);
            HttpManager.instance().getServer(DynamicServer.class)
                    .collectionMusic(sign)
                    .compose(RxUtils.rxScheduleThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        protected void onSuccess(String string) {
                            mView.colleectioSuccess(string);
                        }

                        @Override
                        protected void onError(String string) {
                            mView.error(string);
                        }
                    });
    }
}
