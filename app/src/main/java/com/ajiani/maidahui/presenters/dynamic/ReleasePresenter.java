package com.ajiani.maidahui.presenters.dynamic;

import android.util.Log;

import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.dynamic.shop.CommodityInfo;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MyServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.dynamic.ReleaseMin;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ReleasePresenter extends BasePresenterImp<ReleaseMin.releaseView> implements ReleaseMin.releasePresenter {

    private String s;
    private String string;

    @Override
    public void setData(File file) {
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
                                   mView.success(string);
                               }

                               @Override
                               protected void onError(String string) {
                                mView.error(string);
                               }

                           }


                );
    }

    @Override
    public void getData(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(DynamicServer.class).sendVideo(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        mView.sendSuccess(string);
                    }

                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });

    }

    @Override
    public void upload(HashMap<String, String> map) {
        HashMap<String, String> hashMap = Params.setParams();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
        HttpManager.instance().getServer(MyServer.class).uploadVideo(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {


                        mView.uploadSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.error(e.getMessage());
                        Log.i("tag", "onError: " + e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Override
    public void ossVideo(HashMap<String, String> map, File file) {
        MediaType mediaType = MediaType.parse("application/octet-stream");
        RequestBody requestBody = RequestBody.create(mediaType, file);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (String o : map.keySet()) {
            builder.addFormDataPart(o, map.get(o));
        }
        MultipartBody file1 = builder.addFormDataPart("file", file.getName(), requestBody).build();
        HttpManager.instance().getServer().ossVideo(file1)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        mView.ossSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.error(e.getMessage());
                        Log.i("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getData2(HashMap<String, String> map, ArrayList<CommodityInfo> list) {
        Gson gson = new Gson();
        String s = gson.toJson(list);
         map.put("goods_json",s);
     /*   CommodityInfo commodityInfos[]= new CommodityInfo[list.size()];
        for(int i=0;i<list.size();i++){
            commodityInfos[i]=list.get(i);
        }
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            CommodityInfo commodityInfo = list.get(i);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("goods_id",commodityInfo.getGoods_id());
            jsonObject1.put("name ",commodityInfo.getName());
            jsonObject1.put("thumb",commodityInfo.getThumb());
            jsonObject1.put("goods_type",commodityInfo.getGoods_type());
            jsonObject1.put("top",commodityInfo.getTop());
            jsonObject1.put("left",commodityInfo.getLeft());
            jsonObject1.put("link",commodityInfo.getLink());
            jsonObject1.put("sort",commodityInfo.getSort());
            jsonObject1.put("direction",commodityInfo.getDirection());
            jsonArray.put(jsonObject1);
        }*/


        HashMap<String, String> hashMap = Params.setParams2();
        hashMap.putAll(map);
        HashMap<String, String> sign = Params.getSign(hashMap);
   /*   //  Log.i("wxy", "getData2: "+jsonArray.toString());
        HashMap<String, Object> stringHashMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
       //     stringHashMap.put(new String("goods[]"),jsonArray);
        }*/


        HttpManager.instance().getServer(DynamicServer.class).
                sendVideo2(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        CommodityInfo[] list;
                        mView.sendSuccess(string);
                    }
                    @Override
                    protected void onError(String string) {
                        mView.error(string);
                    }
                });
    }
    public String get(HashMap<String, String> sign, ArrayList<CommodityInfo> list)  {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (String o : sign.keySet()) {
            jsonObject.put(o, sign.get(o));
        }

        for (int i = 0; i < list.size(); i++) {
            CommodityInfo commodityInfo = list.get(i);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("goods_id",commodityInfo.getGoods_id());
            jsonObject1.put("name ",commodityInfo.getName());
            jsonObject1.put("thumb",commodityInfo.getThumb());
            jsonObject1.put("goods_type",commodityInfo.getGoods_type());
            jsonObject1.put("top",commodityInfo.getTop());
            jsonObject1.put("left",commodityInfo.getLeft());
            jsonObject1.put("link",commodityInfo.getLink());
            jsonObject1.put("sort",commodityInfo.getSort());
            jsonObject1.put("direction",commodityInfo.getDirection());
            jsonArray.put(jsonObject1);
        }
         jsonObject.put("goods[]",jsonArray);
        return "";
    }


}
