package com.ajiani.maidahui.weight.record;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.ajiani.maidahui.R;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class MyVideoView extends RelativeLayout {


    public MyVideoView(Context context) {
        super(context);
        initView(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }



    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    //初始化view
    private void initView(Context context) {
      //加载布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.video_view, null, false);


    }


}
