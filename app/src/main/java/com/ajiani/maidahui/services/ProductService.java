package com.ajiani.maidahui.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class ProductService extends Service {

    public static MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("name");

        //初始化
        mediaPlayer = new MediaPlayer();
        Log.i("WXY", "ossSuccess: "+name);
        try {
            mediaPlayer.setDataSource(name);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    //回调 播放轮播图
                    Toast.makeText(ProductService.this, "开始播放", Toast.LENGTH_SHORT).show();

                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        //播放错误监听
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(ProductService.this, "播放出错了", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
                return false;
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(ProductService.this, "播放完毕", Toast.LENGTH_SHORT).show();
                // todo
            }
        });
        return START_STICKY;
    }
    //暂停
    public static void pause(){
        mediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        super.onDestroy();

    }


}
