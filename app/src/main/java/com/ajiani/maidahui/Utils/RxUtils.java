package com.ajiani.maidahui.Utils;




import com.ajiani.maidahui.bean.BaseBean;
import com.ajiani.maidahui.http.ApiException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名：MyViewDemo2
 * 包名：  com.jiyun.firstnavigation.utils
 * 文件名：RxUtils
 * 创建者：liangxq
 * 创建时间：2019/1/18  8:51
 * 描述：TODO
 */
public class RxUtils {

    //网络请求线程的切换
    public static <T> ObservableTransformer<T,T> rxScheduleThread(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
/*
*
* flatMap方法具有多个重载方法，在调用flatMap方法时，首先需要传入一个Function的对象，但对是Function类中的泛型参数有要求，
* 第二个参数必须是继承自ObservableSource类，然后在抽象的apply方法中返回该类型参数。
* 而我们都知道Observable就是继承自该类。所以在Function中，最后必须返回的是一个Observable的对象。

* */

    //将我们封装的公共实体类转换成我们需要的实体类对象(T),并且用Observer包裹，便于向下继续发送
    //observableTransformer 就是可以将一个 observable 装欢为另一个observable 的转换器
    public static <T> ObservableTransformer<BaseBean<T>,T> handeResult(){
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            //这里的  onservableSource 是observable 的实现类 所以还是observable
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {

                return upstream.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseBean<T> tHttpResponse) throws Exception {
                        if(tHttpResponse.getCode()==0){
                            //创建T对象的Observable
                            return createData(tHttpResponse.getData());
                        }else{
                            //将服务器错误信息转换到我们的Error中  自己封装 的错误信息   ,到时候只需要判断他是不是  e  的 子类就可以了z，在根据case 进行判断
                            return Observable.error(new ApiException(tHttpResponse.getCode(),tHttpResponse.getInfo()));
                        }

                    }
                });
            }
        };


    }

   //创建  obserable 对象
    public static <T> Observable<T> createData(final T t){

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            //  observableEmitter 是发射器  ,sunscriable实际的订阅方法
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        });
    }
}
