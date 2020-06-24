package com.ajiani.maidahui.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.HomeActivity;

import cn.jzvd.JzvdStd;

public class MyJzPlayer extends JzvdStd {
    Context context;
    public MyJzPlayer(Context context) {
        super(context);
        this.context=context;
    }

    public MyJzPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fullscreen:

                break;


        }
    }

}
