package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.VideoView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.weight.FullScreenVideoView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends SimpleActivity {
    @BindView(R.id.video)
    FullScreenVideoView video;

    @Override
    protected void initData() {

    }

    public ArrayList<String> listFiles(File dir) {
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

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String path = bundle.getString("file");

       // String path = Environment.getExternalStorageDirectory().getPath() + "/Video1";
        //ArrayList<String> strings = listFiles(new File(path));
        Log.i("wxy", "initView: "+path);
        video.setVideoPath(path);
        video.seekTo(0);
        video.start();
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_video;
    }

}
