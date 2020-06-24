package com.ajiani.maidahui.http;

import android.util.Base64;
import android.util.Log;

import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.activity.MyApp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Params {

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";
    private static String mString;

    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        Mac mac = Mac.getInstance(MAC_NAME);
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        byte[] digest = mac.doFinal(text);
        return digest;
    }

    public static HashMap<String,String>  setParams(){
        long l = System.currentTimeMillis();
        String replace = UUID.randomUUID().toString().replace("-", "");

        String tooken = (String) SPUtils.get(MyApp.getApp(), "token", "");
        HashMap<String, String> hashMap = new HashMap<>();
        l=l/1000;
     /*   if(tooken.length()>3){
            hashMap.put("UserToken",tooken);
        }*/
        hashMap.put("AppId","47065188");
        hashMap.put("SignatureNonce", replace);
        hashMap.put("Timestamp",String.valueOf(l));
        return hashMap;
    }
    public static HashMap<String,String>  setParams2(){
        long l = System.currentTimeMillis();
        String replace = UUID.randomUUID().toString().replace("-", "");
        String tooken = (String) SPUtils.get(MyApp.getApp(), "token", "");

        l=l/1000;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("AppId","47065188");
        hashMap.put("SignatureNonce", replace);
        hashMap.put("Timestamp",String.valueOf(l));
        if(tooken.equals("")){
            hashMap.put("UserToken","");
        }else{
            hashMap.put("UserToken",tooken);
        }
        return hashMap;
    }
    public static HashMap<String,String> getSign(HashMap<String,String> map){
        //先排序
        Collection<String> strings = map.keySet();
        List<String> list = new ArrayList<String>(strings);
        Collections.sort(list);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                stringBuffer.append(list.get(i) + "=" + map.get(list.get(i)));
            } else {
                stringBuffer.append(list.get(i) + "=" + map.get(list.get(i)) + "&");
            }
        }
        String s = stringBuffer.toString();
//        Log.i("WXY", "getSign: "+s);

        try {
            byte[] bytes = HmacSHA1Encrypt(s, "JaGauosjFbtQQYLkKdrXYKOJyAITyjMv");
            mString = Base64.encodeToString(bytes, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }
//       Log.i("WXY", "getSign: "+mString);
        mString=mString.substring(0,28);
        map.put("Signature",mString);
            return map;
    }
}
