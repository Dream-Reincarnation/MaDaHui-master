package com.ajiani.maidahui.http;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.login.LoginActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by asd on 2019/1/17.
 */
//把错误的给抽出来
public abstract class BaseObserver implements Observer<String> {
    Context mContext = MyApp.getApp();

    //管理一下网络请求
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }
    @Override
    public void onNext(String string) {

        JSONObject jsonObject = JSON.parseObject(string);
        String code = jsonObject.getString("code");
        if (code.equals("1")|code.equals("1000")) {
            onSuccess(string);
        } else if (code.equals("0")) {
            String info = jsonObject.getString("info");
           onError(string);
        } else if (code.equals("-101")) {
            Toast.makeText(mContext, "应用AppID非法", Toast.LENGTH_SHORT).show();
            return;
        } else if (code.equals("-102")) {
            Toast.makeText(mContext, "缺少SignatureNonce参数", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-1")) {
            Toast.makeText(mContext, "hash参数无效", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-103")) {
            Toast.makeText(mContext, "缺少Signature参数", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-104")) {
            Toast.makeText(mContext, "缺少Timestamp参数", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-105")) {
            Toast.makeText(mContext, "请求时间不正确", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-106")) {
            Toast.makeText(mContext, "请求时间已过期", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-107")) {
            Toast.makeText(mContext, "APPID不正确", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-108")) {
            Toast.makeText(mContext, "签名不正确", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-109")) {
            Toast.makeText(mContext, "签名已失效", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-201")) {
            Intent intent = new Intent(HomeActivity.context,LoginActivity.class);
          //  HomeActivity.context.startActivity(intent);
        } else if (code.equals("-202")) {
            Toast.makeText(mContext, "UserToken令牌无效", Toast.LENGTH_SHORT).show();

        } else if (code.equals("-203")) {
            Toast.makeText(mContext, "UserToken令牌过期", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-230")) {
            Toast.makeText(mContext, "应用已禁用", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-301")) {
            Toast.makeText(mContext, "缺少HttpToken参数", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-302")) {
            Toast.makeText(mContext, "HttpToken参数不相符", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-800")) {
            Toast.makeText(mContext, "没有数据", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-900")) {
            Toast.makeText(mContext, "参数错误", Toast.LENGTH_SHORT).show();
        } else if (code.equals("-999")) {
            Toast.makeText(mContext, "系统错误", Toast.LENGTH_SHORT).show();
        }
    }


    protected abstract void onSuccess(String string);

    protected abstract void onError(String string);

    @Override
    public void onError(Throwable e) {
        //0.......
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }


        if (e instanceof HttpException) {
            e.getMessage();
        }
//        Toast.makeText(mContext, "错误了", Toast.LENGTH_SHORT).show();
        onError(e.getMessage());

    }

    @Override
    public void onComplete() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
