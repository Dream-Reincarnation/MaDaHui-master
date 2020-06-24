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
import com.ajiani.maidahui.activity.dynamic.SearchMusic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MusicService3 extends Service {

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
        File file = new File(Environment.getExternalStorageDirectory() + "/SoundRecorder");
        ArrayList<String> strings = listFiles(file);
        try {
            mediaPlayer.setDataSource(name);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Toast.makeText(MusicService3.this, "开始播放网络歌曲", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MusicService3.this, "播放出错了", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
                return false;
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(MusicService3.this, "播放完毕", Toast.LENGTH_SHORT).show();
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

    public  ArrayList<String> listFiles(File dir) {
        ArrayList<String> strings = new ArrayList<>();
        if (!dir.exists() || !dir.isDirectory())
            return strings;
        String[] files = dir.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(dir, files[i]);
            if (file.isFile()) {
                String absolutePath = file.getAbsolutePath();
                strings.add(absolutePath);

            } else {
                listFiles(file);
            }
        }
        return strings;
    }

}
