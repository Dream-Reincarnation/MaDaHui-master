package com.ajiani.maidahui.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;

import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.Utils.http.HttpNetWorkUtils;
import com.ajiani.maidahui.activity.MyApp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by asd on 2019/1/17.
 */

public class HttpManager {
    private String BaseUrl= Globals.URL;
    public static HttpManager mHttpManager;
    private String TAG="wxy";

    public static HttpManager instance() {
        if (mHttpManager == null) {
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager();
                }
            }
        }
        return mHttpManager;
    }
    public Retrofit getRetrofit(String url) {

        return new Retrofit.Builder()
                .client(getOkhttp())
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public Retrofit getRetrofit2() {
        return new Retrofit.Builder()
                .client(getOkhttp())
                .baseUrl("https://ajiani.oss-cn-beijing.aliyuncs.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
     public MyServer getServer(){
        return getRetrofit2().create(MyServer.class);
     }
    public <S>S getServer(Class<S> tsClass){
        return  getRetrofit(Globals.URL).create(tsClass);
    }




    private OkHttpClient getOkhttp() {
        long num=1024 * 1024 * 1024;
        //设置缓存 的 位置  和 缓存的大小
        String cachePath = FileUtils.getCache(MyApp.getApp());
//        Log.i(TAG, "getOkhttp: "+cachePath);
        Cache cache = new Cache(new File(cachePath, "httpcache"),num);
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                //设置 缓存
                .cache(cache)
                //启用错误重连
                .retryOnConnectionFailure(true)
                .build();


    }
    private class MyCacheinterceptor implements Interceptor {
        private MyCacheinterceptor() {
        }

        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!HttpNetWorkUtils.isNetworkConnected(MyApp.getApp().getApplicationContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            Response originalResponse = chain.proceed(request);
            if (HttpNetWorkUtils.isNetworkConnected(MyApp.getApp().getApplicationContext())) {
                int maxAge = 0;
                return originalResponse.newBuilder().removeHeader("Pragma").header("Cache-Control", "public ,max-age=" + maxAge).build();
            } else {
                int maxStale = 604800;
                return originalResponse.newBuilder().removeHeader("Pragma").header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale).build();
            }
        }
    }
}
