package com.ajiani.maidahui.presenters.mine;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.activity.chat.ChatActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.UpLoadBean;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.UpdataSetin;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SetInfoPresenter extends BasePresenterImp<UpdataSetin.upSetView> implements UpdataSetin.upSetPresenter {
    @Override
    public void getData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MineServer.class)
                .setInfo(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.success(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }

    @Override
    public void upLoadData(File file) {
        HashMap<String, String> hashMap = Params.setParams();
        HashMap<String, String> map = Params.getSign(hashMap);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Builder file1 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), requestBody);
        //遍历 hashMAp
        for (String o : map.keySet()) {
            file1.addFormDataPart(o, map.get(o));
        }
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
                                    mView.error(string);
                               }

                           }
                );

    }
}
