package com.ajiani.maidahui.presenters;

import android.util.Log;
import android.widget.Toast;

import com.ajiani.maidahui.Utils.RequestBodyBuilder;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.login.LoginBean;
import com.ajiani.maidahui.bean.login.VeriftyBean;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.VeriftyIn;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;



public class VeriftyPresenter extends BasePresenterImp<VeriftyIn.VeriftyView> implements VeriftyIn.VeriftyPresenter {

    private String TAG="WXY";

    @Override
    public void getData(String phone) {
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.put("mobile", phone);
        HashMap<String, String> sign = Params.getSign(hashMap);

//        FormBody.Builder builder = new FormBody.Builder();
//        Iterator iter = hashMap.keySet().iterator();
//        while (iter.hasNext()) {
//            String key = (String) iter.next();
//            String val = hashMap.get(key);
//            builder.add(key,val);
//        }
//
//        FormBody build = builder.build();
        HttpManager.instance().getServer(MyServer.class).getVerifty(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        Gson gson = new Gson();
                        VeriftyBean veriftyBean = gson.fromJson(string, VeriftyBean.class);
                        mView.setSuccess(veriftyBean);
                    }
                    @Override
                    protected void onError(String string) {

                    }

                });


    }

    @Override
    public void getLogin(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams();
           hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).phoneLogin(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        LoginBean loginBean = new Gson().fromJson(string, LoginBean.class);
                        mView.setLoginSuccess(loginBean);
                    }
                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }

                });

    }
}
