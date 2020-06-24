package com.ajiani.maidahui.presenters.mine;

import android.util.Log;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.AutonymIn;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AutonymPresenter extends BasePresenterImp<AutonymIn.AutonymView> implements AutonymIn.AntonymPresenter {
    @Override
    public void getAutony(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .autonym(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.authenticaSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    public void getAutonymInfo(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
         HashMap<String, String> sign = Params.getSign(hashMap);
         HttpManager.instance().getServer(MineServer.class)
                 .autonymInfo(sign)
                 .compose(RxUtils.rxScheduleThread())
                 .subscribe(new BaseObserver() {
                     @Override
                     protected void onSuccess(String string) {
                            mView.checkAutonym(string);
                     }

                     @Override
                     protected void onError(String string) {

                     }
                 });
    }

    @Override
    public void getUpLoadData(File file) {
        Log.i("WXY", "setData: " + file.length());
        HashMap<String, String> hashMap = Params.setParams();
        HashMap<String, String> map = Params.getSign(hashMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Builder file1 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), requestBody);
        Log.i("WXY", "setData: "+file.getName());
        //遍历 hashMAp
        for (String o : map.keySet()) {
            file1.addFormDataPart(o, map.get(o));
        }
        mView.showprogress();
        MultipartBody build = file1.build();
        HttpManager.instance().getServer(MyServer.class).upLoadImg(build)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                               @Override
                               protected void onSuccess(String string) {
                                   mView.upLoadSuccess(string);
                               }

                               @Override
                               protected void onError(String string) {

                               }

                           }


                );
    }
}
