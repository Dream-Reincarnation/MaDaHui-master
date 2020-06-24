package com.ajiani.maidahui.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    public static MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //初始化
        mediaPlayer = new MediaPlayer();
        File file = new File(Environment.getExternalStorageDirectory() + "/AudioRecord");
        ArrayList<String> strings = listFiles(file);
        Log.i("WXY", "onStartCommand: "+strings.size()+"--------"+strings.get(0));
        try {
            mediaPlayer.setDataSource(strings.get(1));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        return START_STICKY;
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
