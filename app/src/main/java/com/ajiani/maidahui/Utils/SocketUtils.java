package com.ajiani.maidahui.Utils;

import android.util.Log;

import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.bean.sockets.LogicBean;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.bean.sockets.NotificaBean;
import com.ajiani.maidahui.bean.sockets.SendMsg;
import com.ajiani.maidahui.bean.sockets.ShopMsg;
import com.ajiani.maidahui.http.HttpManager;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class SocketUtils  {
    private static Socket socket;
    //设置链接

    public static Socket init(){


        if (socket == null) {
            synchronized (SocketUtils.class) {
                if (socket == null) {
                    try {
                        socket = IO.socket("http://www.maidahui.com:19965");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return socket;

        /*try {
            socket = IO.socket("http://www.maidahui.com:19965");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return socket;*/
    }
    //连接私信
    public static void connectScoket(){


        MyApp.socket.on("broadcastingListen", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("wxy", "connectScoket: asdasdasdsdas");
                EventBus.getDefault().postSticky(2);
            }
            //https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg
        });
    }
    //发送私信
    public static  void sendLetter(SendMsg sendMsg){
        if(socket==null){
            return ;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid",sendMsg.getUid());
            jsonObject.put("msgid",sendMsg.getMsgid());
            jsonObject.put("msg",sendMsg.getMsg());
            jsonObject.put("extra",sendMsg.getExtra());
            socket.emit("sendmsg",jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static  void sendShopLetter(ShopMsg sendMsg){
        if(socket==null){
            return ;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("shopid",sendMsg.getShopid());
            jsonObject.put("msgid",sendMsg.getMsgid());
            jsonObject.put("msg",sendMsg.getMsg());
            jsonObject.put("extra",sendMsg.getExtra());
            socket.emit("sendmsg",jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
