package com.ajiani.maidahui.http;




import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import retrofit2.HttpException;

public abstract class MySubscriber<T> implements org.reactivestreams.Subscriber<T> {
    //回调结果处理
   /* private QzhMoundle.QzhMoundleResult mHttpFinishCallBack;

    public MySubscriber(QzhMoundle.QzhMoundleResult httpFinishCallBack) {
        mHttpFinishCallBack = httpFinishCallBack;
    }*/

    @Override
    public void onSubscribe(Subscription s) {
        s.request(10);
    }

    //网络请求错误的时候
    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {//请求失败的时候
         /*   LogUtil.d("错误",e.getMessage());
            mHttpFinishCallBack.setError(e.getMessage());//返回失败异常*/
        }

    }

    //请求完成的时候
    @Override
    public void onComplete() {

    }

}
