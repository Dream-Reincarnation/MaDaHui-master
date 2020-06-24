package com.ajiani.maidahui.presenters;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.BaiDuAI;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BaiDuAIPresenter extends BasePresenterImp<BaiDuAI.baiduAIView> implements BaiDuAI.baiduAIPresenter {
    @Override
    public void getData(File file) {
        HashMap<String, String> hashMap = Params.setParams2();
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
        HttpManager.instance().getServer(MyServer.class).baiduAI(build)
                 .compose(RxUtils.rxScheduleThread())
                                 .subscribe(new BaseObserver() {
                                     @Override
                                     protected void onSuccess(String string) {
                                         mView.success(string);
                                     }

                                     @Override
                                     protected void onError(String string) {

                                     }
                                 });
    }
}
