package com.example.txrecord.ettect;

import android.app.Activity;
import android.content.Context;

import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.txrecord.R;
import com.tencent.ugc.TXVideoEditConstants;
import com.tencent.ugc.TXVideoEditer;

/**
 * 视频预览播放Layout
 */
public class VideoPlayLayout extends FrameLayout {

    private Activity mActivity;
    private FrameLayout mPlayer;

    public VideoPlayLayout(@NonNull Context context) {
        super(context);
        initViews();
    }

    public VideoPlayLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public VideoPlayLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        mActivity = (Activity) getContext();
        inflate(mActivity, R.layout.video_play_layout, this);

        mPlayer = (FrameLayout) findViewById(R.id.layout_player);
    }

    /**
     * 初始化预览播放器
     */
    public void initPlayerLayout() {

    }
}
