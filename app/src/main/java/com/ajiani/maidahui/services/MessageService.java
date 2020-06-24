package com.ajiani.maidahui.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ajiani.maidahui.activity.MyApp;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.http.POST;

public class MessageService extends Service {

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                  //  Log.i("WXY", "call: 1");
                    if(MyApp.socket==null){

                    }else{

                        MyApp.socket.on("broadcastingListen", new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                for (int i = 0; i <args.length; i++) {


                                    JSONArray jsonArray= (JSONArray) args[i];

                                    try {
                                        Log.i("WXY", "call: "+jsonArray.get(0));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).on("sendmsgerror", new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                Log.i("WXY", "call: 失败");
                            }
                        });
                    }

                    break;
            }

        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
         //接收消息
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,1000,1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        
    }
}
