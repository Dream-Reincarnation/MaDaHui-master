package com.ajiani.maidahui.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DaoUtils;
import com.ajiani.maidahui.Utils.MessageUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.SocketUtils;
import com.ajiani.maidahui.bean.sockets.LogicBean;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.bean.sockets.NotificaBean;
import com.ajiani.maidahui.bean.sockets.SendMsg;
import com.ajiani.maidahui.bean.sockets.ShopMsg;
import com.ajiani.maidahui.bean.sockets.ShopMsg2;
import com.ajiani.maidahui.dao.MySqliteOpenHelper;

import com.alibaba.fastjson.JSON;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.ugc.TXUGCBase;
import com.umeng.commonsdk.UMConfigure;
//import com.umeng.message.IUmengRegisterCallback;
//import com.umeng.message.PushAgent;
//import com.umeng.message.UTrack;
//import com.umeng.message.UmengNotificationClickHandler;
//import com.umeng.message.entity.UMessage;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;



public class MyApp extends Application implements Application.ActivityLifecycleCallbacks {

    public static boolean isShowPop = false;
    public String Appid = "wxe52595c56f1bfd66";
    public String AppKey = "5d8d67ec0cafb2d543000d20";
//        public static String BaseUrl = "http://192.168.1.10:8080/";
    public static String BaseUrl = "file:///android_asset/www/dist/index.html";
    public static MyApp activity;
    public static Socket socket = null;
    private Activity activity1;

    @Override
    public void onCreate() {
        super.onCreate();
        //建立socket service
        Configuration config = getResources().getConfiguration();
        config.setTo(config);
        //设置主机名
        SPUtils.put(this, "isfirstApp", false);
        activity = this;
        //清空数据库
        SPUtils.put(this, "dao", true);
        initGreenDao();
        MultiDex.install(this);
//        UMConfigure.init(this, "5d8d67ec0cafb2d543000d20", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.init(this, "5d8d67ec0cafb2d543000d20"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //dbcb43708beb61abc0b5ddfea869bc9d

        this.registerActivityLifecycleCallbacks(this);


        TXUGCBase.getInstance().setLicence(this, "http://license.vod2.myqcloud.com/license/v1/3e2f3442282325c71fd763188b8b2256/TXUgcSDK.licence", "27fe28b1a292b896f6292de10be2bb4b");

        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i("WXY", "onSuccess: " + s);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i("wxy", "注册成功：deviceToken：-------->  " + s + "[[[" + s1);
            }
        });

        //
        String userid = (String) SPUtils.get(MyApp.activity, "userid", "");
        Log.i("WXY", "onCreate: " + userid);
       /* mPushAgent.addAlias(userid, "user", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                Log.i("WXY", "onMessage: " + s);
            }
        });
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void launchApp(Context context, UMessage uMessage) {
                super.launchApp(context, uMessage);
                Log.i("wxy", "launchApp: " + uMessage.extra);
            }*/
/*
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Log.i("wxy", "dealWithCustomAction: asdasdsasd");
                Toast.makeText(context, msg.text, Toast.LENGTH_SHORT).show();
            }*/

         /*   @Override
            public void dealWithCustomAction(Context context, UMessage uMessage) {
                super.dealWithCustomAction(context, uMessage);
                Log.i("wxy", "dealWithCustomAction: asdasdsasd");
                Toast.makeText(context, uMessage.custom + "--" + uMessage.text, Toast.LENGTH_SHORT).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //注册 友盟消息推送
        //  PushAgent mPushAgent = PushAgent.getInstance(this);

        //*/
         //微信三方登录
        PlatformConfig.setWeixin("wxe52595c56f1bfd66", "dbcb43708beb61abc0b5ddfea869bc9d");

        socket = SocketUtils.init();
      /*  String token = (String) SPUtils.get(this, "token", "");
        String nickname = (String) SPUtils.get(this, "username", "");
        String headimgurl = (String) SPUtils.get(this, "head", "");
        String asd = (String) SPUtils.get(this, "userid", "");
        *//*if (token.length() > 2) {
            SocketUtils.init();
            SocketUtils.connectScoket(new LogicBean(asd, nickname, headimgurl));
            ;
        }*/


        init();
        connectScoket();
        connect();
        initGreenDao();

     /*   SocketUtils.init();
        SocketUtils.connectScoket();*/
    }

    private void initGreenDao() {
        // 初始化//如果你想查看日志信息，请将 DEBUG 设置为 true


    }

    /**
     * 提供一个全局的会话
     *
     * @return
     */

    public static void connectScoket() {
        if (socket == null) {
            return;
        }
        String nickname = (String) SPUtils.get(activity, "username", "");
        String headimgurl = (String) SPUtils.get(activity, "head", "");
        String userid = (String) SPUtils.get(activity, "userid", "");
        org.json.JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", userid);
            jsonObject.put("nickname", nickname);
            jsonObject.put("avatar", headimgurl);
            socket.emit("conn", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static Socket init() {

        try {
            socket = IO.socket("http://www.maidahui.com:19965");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.connect();
        return socket;
    }

    public static void connect() {

        socket.on("connect", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.i("wxy", "call: 链接");
            }

        }).on("reconnect", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.i("wxy", "call: 重连" + args);
            }

        }).on("disconnect", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("wxy", "call: 退出" + args);
            }

        }).on("heartbeat", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.emit("heartbeat", "heartbeat");
            }
        }).on("conn", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("WXY", "call: 连接成功");
            }
        }).on("broadcastingListen", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (int i = 0; i < args.length; i++) {

                }
            }

        }).on("delmsg", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("wxy", "call: 发送成功了");
                for (int i = 0; i < args.length; i++) {
                    JSONArray jsonArray = (JSONArray) args[i];


                    String s = null;
                    try {
                        s = (String) jsonArray.get(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 显示View
     *
     * @param view 需要显示到Activity的视图
     */
    public void showView(View view) {
        /*Activity不为空并且没有被释放掉*/
        if (this.activity != null && !this.activity1.isFinishing()) {
            /*获取Activity顶层视图,并添加自定义View*/
            ((ViewGroup) this.activity1.getWindow().getDecorView()).addView(view);
        }
    }

    /**
     * 隐藏View
     *
     * @param view 需要从Activity中移除的视图
     */
    public void hideView(View view) {
        /*Activity不为空并且没有被释放掉*/
        if (this.activity != null && !this.activity1.isFinishing()) {
            /*获取Activity顶层视图*/
            ViewGroup root = ((ViewGroup) this.activity1.getWindow().getDecorView());
            /*如果Activity中存在View对象则删除*/
            if (root.indexOfChild(view) != -1) {
                /*从顶层视图中删除*/
                root.removeView(view);
            }
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        /*获取当前显示的Activity*/
        this.activity1 = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }


    public static class Options extends io.socket.engineio.client.Socket.Options {
        // 是否自动重连
        public boolean reconnection = true;
        // 重连尝试次数
        public int reconnectionAttempts;

        // 重连间隔
        public long reconnectionDelay;
        // 最大连接等待时间
        public long reconnectionDelayMax;
        // 连接超时时间 (ms)，设置为-1表示不超时
        public long timeout = 20000;

    }
    //发送

    //发送私信
    public static void sendLetter(SendMsg sendMsg) {
        if (socket == null) {
            return;
        }
        String userid = (String) SPUtils.get(MyApp.getApp(), "userid", "");
        String uid = sendMsg.getUid();
        String msgId = getMsgId(userid, "0", uid);
        sendMsg.setMsgid(msgId);
        JSONObject jsonObject = new JSONObject();


        try {
            jsonObject.put("uid", sendMsg.getUid());
            jsonObject.put("msgid", sendMsg.getMsgid());
            jsonObject.put("msg", sendMsg.getMsg());
            jsonObject.put("action", sendMsg.getAction());
            jsonObject.put("extra", sendMsg.getExtra());
            socket.emit("sendmsg", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void sendShop(ShopMsg sendMsg) {
        if (socket == null) {
            return;
        }
        String userid = (String) SPUtils.get(MyApp.getApp(), "userid", "");
        String uid = sendMsg.getShopid();
        String msgId = getMsgId(userid, uid, "0");
        sendMsg.setMsgid(msgId);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("shopid", sendMsg.getShopid());
            jsonObject.put("msgid", sendMsg.getMsgid());
            jsonObject.put("msg", sendMsg.getMsg());
            jsonObject.put("extra", sendMsg.getExtra());
            socket.emit("sendmsg", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //生成MsgId  msgid = 发件人uid  + shopid + 收件人uid + 时间戳 + 1000~9999随机数
    public static String getMsgId(String uid, String shopid, String toId) {
        long time = System.currentTimeMillis();
        Random rand = new Random();
        int randNum = rand.nextInt(8999);
        return uid + shopid + toId + time + (randNum + 1000);
    }

    public static void sendShop2(ShopMsg2 sendMsg, String action) {
        if (socket == null) {
            return;
        }
        String userid = (String) SPUtils.get(MyApp.getApp(), "userid", "");
        String uid = sendMsg.getShopid();
        String msgId = getMsgId(userid, uid, "0");
        sendMsg.setMsgid(msgId);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("shopid", sendMsg.getShopid());
            jsonObject.put("msgid", sendMsg.getMsgid());
            jsonObject.put("msg", sendMsg.getMsg());
            jsonObject.put("action", action);
            jsonObject.put("extra", sendMsg.getExtra());
            socket.emit("sendmsg", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static MyApp getApp() {
        return activity;
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
