package com.ajiani.maidahui.Utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.ajiani.maidahui.Utils.share.PopupWindows;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.bean.sockets.NotificaBean;
import com.ajiani.maidahui.bean.sockets.RedPackageBean;
import com.ajiani.maidahui.http.HttpManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import io.socket.emitter.Emitter;

public class MessageUtils {

    private static String TAG = "wxy";
    public static MessageUtils messageUtils;

    public static MessageUtils instance() {
        if (messageUtils == null) {
            synchronized (HttpManager.class) {
                if (messageUtils == null) {
                    messageUtils = new MessageUtils();
                }
            }
        }
        return messageUtils;
    }

    public  void isSend(String s, String type, Activity context, View view) {
        boolean isswitch = (boolean) SPUtils.get(context, "isswitch", false);
        boolean isBoardswitch = (boolean) SPUtils.get(context, "isBoardswitch", false);
        boolean isCommentswitch = (boolean) SPUtils.get(context, "isCommentswitch", false);
        boolean isLogisticsswitch = (boolean) SPUtils.get(context, "isLogisticsswitch", false);
        if (isswitch) {

        } else {
          //  sendReciver(s, "service", context, view);
        }
        if(isBoardswitch){

        }else{
            //sendReciver(s, "service", context, view);
        }
        if(isCommentswitch){

        }else{
           // sendReciver(s, "service", context, view);
        }
        if(isLogisticsswitch){

        }else{
           // sendReciver(s, "service", context, view);
        }
    }
//2020-01-14 11:23:05.381 14672-14672/com.ajiani.maidahui I/wxy: run: shopid {"_method_":"SendMsg","action":"1","msgid":"10001501000071578972185636124","userType":0,"nickname":"恰同学少年","avatar":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/5a/f1d37a4156f3fe571f0d46391fb354.png","uid":"100015","toid":"100007","shopid":0,"ct":"ajjkk","extra":"","timestamp":1578972185}
//2020-01-14 11:22:16.573 13991-13991/com.ajiani.maidahui I/wxy: run: shopid {"_method_":"SendMsg","action":"1","msgid":"100022010000715789721370794755","userType":0,"nickname":"啊啊啊","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/IgpNh2icImnWsvicXLEL9qbqn8mEolxBAy5qKSLrIeVxFlrIpIAEXW5y0WSotNcG8kicSHBDWGCPUDEibRhh2oTc1Q/132","uid":"100022","toid":"100007","shopid":0,"ct":"哈哈","extra":{},"timestamp":1578972136}
    public  void messageLinister(Activity context, View view) {
        //判断是否开启商城广播消息
        //消息监听
        MyApp.socket.on("broadcastingListen", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                for (int i = 0; i < 1; i++) {
                    JSONArray jsonArray = (JSONArray) args[0];
                    int finalI = i;
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String s = (String) jsonArray.get(0);
                                JSONObject jsonObject = JSON.parseObject(s);
                                String code = jsonObject.getString("_method_");
                                String toid = jsonObject.getString("uid");
                                String userid = (String) SPUtils.get(context, "userid", "");
//                                Log.i("wxy", "run: shopid " + toid + "---" + userid);
                                switch (code) {
                                    //7，	商户订单发货成功通知
                                    case "orderShippingSuccess":
                                        isSend(s, "service", context, view);
                                      //  sendReciver(s, "service", context, view);

                                        break;
                                    //8，	商户订单下单成功通知（
                                    case "orderPaySuccess":

                                        isSend(s, "service", context, view);

                                        break;
                                    //夺宝
                                    case "addDduobao":
                                        isSend(s, "service", context, view);
                                       /* if (userid.equals(toid)) {
                                            isSend(s, "service", context, view);
//                                            sendReciver(s, "service",context,view);
                                        }*/
                                        break;
                                    //夺宝成功
                                    case "addDduobaoSuccess":
                                     /*   if (userid.equals(toid)) {
                                            isSend(s, "service", context, view);
                                        }*/
                                        isSend(s, "service", context, view);
                                        //啦啦啦啦啦啦
                                        break;
                                    //夺宝失败
                                    case "addDduobaoFail":
                                      /*  if (userid.equals(toid)) {
                                            isSend(s, "service", context, view);
                                        }*/
                                        isSend(s, "service", context, view);
                                        break;
//                                    14，	果园发货通知
                                    case "TreesOrderShipping":
                                        isSend(s, "service", context, view);
//                                        sendReciver(s, "service", context, view);
                                        break;

                                    //13，	果园领取成功通知
                                    case "TreesOrderSuccess":
                                        /*if (userid.equals(toid)) {
                                            isSend(s, "service", context, view);
                                        }*/
                                        isSend(s, "service", context, view);
                                        break;
                                    //11，	果园购买种子成功通知
                                    case "TreesSeedPay":
                                       /* if (!userid.equals(toid)) {
                                            isSend(s, "service", context, view);
                                        }*/
                                        isSend(s, "service", context, view);
                                        break;
                                    //12，	果园种植种子通知（
                                    case "TreesSeedSuccess":
                                        //完了
                                      /*  if (userid.equals(toid)) {
                                            isSend(s, "service", context, view);
                                        }*/
                                        isSend(s, "service", context, view);
                                        break;
                                    //发送私信
                                    case "SendMsg":
                                        JSONObject jsonObject1 = JSON.parseObject(s);
                                        String shopid = jsonObject1.getString("shopid");
                                        //shopid 大于0 是 客服  ，小于0 是私信
                                        if (Integer.parseInt(shopid) > 0) {
                                            MsgBean reciverBean = new Gson().fromJson(s, MsgBean.class);
                                            JSONObject jsonObject2 = JSON.parseObject(s);
                                            String action = jsonObject2.getString("action");
                                           // sendReciver(s, "freight", context, view);
                                        //    reciverBean.setLeft(true);
                                            sel(action, s);

                                            EventBus.getDefault().postSticky(reciverBean);
                                        } else {
                                            MsgBean reciverBean = new Gson().fromJson(s, MsgBean.class);
                                            NotificaBean notificaBean = new NotificaBean();
                                            notificaBean.setAction(reciverBean.getAction());
                                            notificaBean.setContent(reciverBean.getCt());
                                            notificaBean.setHeadUrl(reciverBean.getAvatar());
                                            notificaBean.setName(reciverBean.getNickname());
                                            notificaBean.setId(reciverBean.getUid());
                                            notificaBean.setType("freight");
                                            notificaBean.setShopId(reciverBean.getShopid() + "");
                                            notificaBean.setTime(reciverBean.getTimestamp() + "");
                                            ArrayList<NotificaBean> notificaBeans = new ArrayList<>();
                                            notificaBeans.add(notificaBean);
                                           // sendReciver(s, "freight", context, view);
                                            EventBus.getDefault().postSticky(reciverBean);
                                        }
                                        break;
                                    case "SystemNot":
                                        Log.i("WXY", "run: " + (String) jsonArray.get(0));

                                        break;
                                    //短视频@ 单发
                                    case "AcetexVideo":
                                      /*  sendReciver(s, "service", context, view);*/
                                        isSend(s, "service", context, view);
                                        break;
                                    //拼团成功  群发
                                    case "addGroupSuccess":

//                                        isSend(s, "service", context, view);
                                        isSend(s, "service", context, view);
                                        break;
                                    //添加拼团 群发
                                    case "addGroup":

                                        isSend(s, "service", context, view);

                                        break;
                                    //添加拼团失败 单发
                                    case "addGroupFail":

                                        isSend(s, "service", context, view);

                                        break;
                                    //系统信息
                                    case "system":
                                     //   sendReciver(s, "system", context, view);


                                        break;
                                    //提现成功
                                    case "cashSuccess":
//                                        sendReciver(s, "system", context, view);
                                        isSend(s, "service", context, view);
                                        break;
                                    //提现失败

                                    case "cashFail":
//                                        sendReciver(s, "system", context, view);
                                        isSend(s, "service", context, view);
                                        break;
                                    //携带连接的广播 ，跳转广播
                                    case "LinkMessage":
                                       /* if (userid.equals(toid)) {
                                            sendReciver(s, "link", context, view);
                                        }*/
                                        isSend(s, "service", context, view);
                                        break;
                                    //点击弹窗  跳转webvIew  跳转服务消息
                                    case "ShopsLinkMessage":
                                       /* if (userid.equals(toid)) {
                                            sendReciver(s, "link", context, view);
                                        }*/
                                        isSend(s, "service", context, view);
                                        break;
                                    //举报通知 action 90  //举报反馈 ction 91
                                    case "ComplaintSend":
//                                        sendReciver(s, "service", context, view);
                                        isSend(s, "service", context, view);
                                        break;


                                }
                                if(finalI ==0){
                                    return;
                                }
                                //  EventBus.getDefault().postSticky(reciverBean);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    });



                }
            }
            //https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg
        });
    }

    public  void sendReciver(String s, String type, Activity context, View view) {
        PopupWindows popupWindows = new PopupWindows();
        JSONObject jsonObject2 = JSON.parseObject(s);
        String action = jsonObject2.getString("action");
        String ct = jsonObject2.getString("ct");
        String avatar = jsonObject2.getString("avatar");
        String name = jsonObject2.getString("nickname");
        String time = jsonObject2.getString("timestamp");
        String shopid1 = jsonObject2.getString("shopid");
        String id = jsonObject2.getString("uid");
        NotificaBean notificaBean = new NotificaBean();
        notificaBean.setShopId(shopid1);
        notificaBean.setName(name);
        notificaBean.setHeadUrl(avatar);
        notificaBean.setAction(action);
        notificaBean.setContent(ct);
        notificaBean.setId(id);
        notificaBean.setType(type);
        notificaBean.setTime(time);
       /* intent = new Intent("1");
        Bundle bundle = new Bundle();
        bundle.putSerializable("sss", notificaBean);
        intent.putExtra("bundles", bundle);*/
        popupWindows.show(context, view, notificaBean);
    }

    private static void sel(String action, String s) {
        switch (action) {
            case "1":
                //没有带商品进入客服 普通文字消息
                MsgBean reciverBean1 = new Gson().fromJson(s, MsgBean.class);
                EventBus.getDefault().postSticky(reciverBean1);
                break;
            case "2":
                break;
            case "3":
                //带着商品 进入客服，透传消息里带着商品信息的json
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "8":
                Gson gson = new Gson();
                RedPackageBean reciverBean = gson.fromJson(s, RedPackageBean.class);
                MsgBean messageBean = new MsgBean();
                messageBean.set_method_(reciverBean.get_method_());
                messageBean.setAction(reciverBean.getAction() + "");
                messageBean.setCt(reciverBean.getCt());
                messageBean.setAvatar(reciverBean.getAvatar());
                messageBean.setExtra(reciverBean.getExtra().getMoney());
                messageBean.setMsgid(reciverBean.getMsgid());
                messageBean.setNickname(reciverBean.getNickname());
                messageBean.setShopid(reciverBean.getShopid());
                messageBean.setTimestamp(reciverBean.getTimestamp());
                messageBean.setToid(reciverBean.getToid());
                messageBean.setUid(reciverBean.getUid() + "");
                messageBean.setUserType(reciverBean.getUserType());
                EventBus.getDefault().postSticky(messageBean);
                break;
        }
    }
}
