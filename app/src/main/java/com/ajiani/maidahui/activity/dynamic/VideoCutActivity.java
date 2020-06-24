package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.video1.PlayState;
import com.ajiani.maidahui.Utils.video1.TCVideoEditerWrapper;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.tencent.TCConstants;
import com.ajiani.maidahui.weight.record.cut.Edit;
import com.ajiani.maidahui.weight.record.cut.TCConfirmDialog;
import com.ajiani.maidahui.weight.record.cut.TCVideoEditView;
import com.ajiani.maidahui.weight.record.cut.VideoWorkProgressFragment;
import com.tencent.ugc.TXVideoEditConstants;
import com.tencent.ugc.TXVideoEditer;
import com.tencent.ugc.TXVideoInfoReader;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoCutActivity extends SimpleActivity implements TCVideoEditerWrapper.TXVideoPreviewListenerWrapper, TXVideoEditer.TXVideoProcessListener {
    @BindView(R.id.layout_palyer)
    FrameLayout mPlayer;
    @BindView(R.id.video_cut_next)
    FrameLayout videoCutNext;
    @BindView(R.id.video_cut_back)
    FrameLayout videoCutBack;
    public String mInVideoPath;                                // 编辑的视频源路径
    private int mCurrentState = -1;       // 播放器当前状态
    TCVideoEditerWrapper mWrapper;
    private VideoWorkProgressFragment mWorkProgressFragment;    // 生成进度条
    private TXVideoEditer mTXVideoEditer;                       // SDK接口类
    private VideoMainHandler mVideoMainHandler;                 // 加载完信息后的回调主线程Hanlder
    private Thread mLoadBackgroundThread;                       // 后台加载视频信息的线程
    private int mVideoResolution = -1;                          // 视频分辨率相关（从录制过来的这个参数才有效） -1说明不是从录制过来的
    private boolean mGenerateSuccess;                           // 是否预处理成功
    private int mVideoFrom;
    private int mCustomBitrate;
    private long mCutterStartTime;
    private long mCutterEndTime;
    private TXVideoInfoReader mTXVideoInfoReader;
    private TCVideoEditView mTCVideoEditView;
    private int mRotation;


    private Edit.OnCutChangeListener mCutChangeListener = new Edit.OnCutChangeListener() {

        @Override
        public void onCutClick() {

        }

        @Override
        public void onCutChangeKeyDown() {

            mTXVideoEditer.stopPlay();
        }

        @Override
        public void onCutChangeKeyUp(long startTime, long endTime, int type) {

            mCutterStartTime = startTime;
            mCutterEndTime = endTime;
            mTXVideoEditer.startPlayFromTime(startTime, endTime);
           /* mTvChoose.setText(
                    getResources().getString(R.string.tc_video_cutter_activity_load_video_success_already_picked)
                            + String.valueOf((endTime - startTime) / 1000) + "s");*/
            mCurrentState = PlayState.STATE_PLAY;
            TCVideoEditerWrapper.getInstance().setCutterStartTime(mCutterStartTime, mCutterEndTime);
        }
    };


    @Override
    protected void initData() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        TCVideoEditerWrapper.getInstance().removeTXVideoPreviewListenerWrapper(this);
        if (mCurrentState == PlayState.STATE_PLAY) {
            if (mTXVideoEditer != null) {
                mTXVideoEditer.stopPlay();
                mCurrentState = PlayState.STATE_STOP;
            }
        }

    }


    @Override
    protected void initView() {
        TCVideoEditerWrapper.getInstance().clear();
        mInVideoPath = getIntent().getStringExtra(TCConstants.VIDEO_EDITER_PATH);
        Log.i("wxy", "onCreate: " + mInVideoPath);
        mVideoResolution = getIntent().getIntExtra(TCConstants.VIDEO_RECORD_RESOLUTION, 1);
        mCustomBitrate = getIntent().getIntExtra(TCConstants.RECORD_CONFIG_BITE_RATE, 0);
        mVideoFrom = getIntent().getIntExtra(TCConstants.VIDEO_RECORD_TYPE, TCConstants.VIDEO_RECORD_TYPE_EDIT);
        mTCVideoEditView = (TCVideoEditView) findViewById(R.id.video_edit_view);
        mTXVideoEditer = new TXVideoEditer(this);
        int ret = mTXVideoEditer.setVideoPath(mInVideoPath);
        mTXVideoInfoReader = TXVideoInfoReader.getInstance();
        mWrapper = TCVideoEditerWrapper.getInstance();
        mTCVideoEditView.setCutChangeListener(mCutChangeListener);
        mWrapper.setEditer(mTXVideoEditer);

        // 开始加载视频信息
        mVideoMainHandler = new VideoMainHandler(this);
        mLoadBackgroundThread = new Thread(new LoadVideoRunnable(this));
        mLoadBackgroundThread.start();


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_videocut;
    }

    @Override
    public void onPreviewProgressWrapper(int time) {

    }

    @Override
    public void onPreviewFinishedWrapper() {

    }

    @OnClick({R.id.video_cut_next, R.id.video_cut_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video_cut_next:
                //完成
                startProcess();

                break;
            case R.id.video_cut_back:
                //取消
                if (mTXVideoEditer != null) {
                    mTXVideoEditer.stopPlay();
                }
                finish();
                break;
        }
    }

    private TXVideoEditer.TXThumbnailListener mThumbnailListener = new TXVideoEditer.TXThumbnailListener() {
        @Override

        public void onThumbnail(int index, long timeMs, Bitmap bitmap) {

            TCVideoEditerWrapper.getInstance().addThumbnailBitmap(timeMs, bitmap);
        }
    };
    /**
     * 取消预处理视频
     */
    private void cancelProcessVideo() {
        if (!mGenerateSuccess) {
            if (mWorkProgressFragment != null)
                mWorkProgressFragment.dismiss();
            if (mTXVideoEditer != null)
                mTXVideoEditer.cancel();
        }
    }
    private void initWorkLoadingProgress() {
        if (mWorkProgressFragment == null) {
            mWorkProgressFragment = VideoWorkProgressFragment.newInstance("视频预处理中...");
            mWorkProgressFragment.setOnClickStopListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelProcessVideo();
                    if (mCurrentState == PlayState.STATE_STOP) {
                        if (mTXVideoEditer != null) {
                            mTXVideoEditer.startPlayFromTime(mCutterStartTime, mCutterEndTime);
                            mCurrentState = PlayState.STATE_PLAY;
                        }
                    }
                }
            });
        }
        mWorkProgressFragment.setProgress(0);
        mWorkProgressFragment.show(getSupportFragmentManager(), "work_progress");
    }
    private void startProcess() {
        mTXVideoEditer.stopPlay();
        mCurrentState = PlayState.STATE_STOP;
        initWorkLoadingProgress();
        mWorkProgressFragment.setProgress(0);
        //监听进度条
        mTXVideoEditer.setVideoProcessListener(this);

        int thumbnailCount = (int) (mCutterEndTime - mCutterStartTime) / 1000;


        TXVideoEditConstants.TXThumbnail thumbnail = new TXVideoEditConstants.TXThumbnail();
        thumbnail.count = thumbnailCount;
        thumbnail.width = 100;
        thumbnail.height = 100;

        mTXVideoEditer.setThumbnail(thumbnail);
        mTXVideoEditer.setThumbnailListener(mThumbnailListener);
        Log.i("wxy", "startProcess: "+mTCVideoEditView.getSegmentFrom()+"======="+mTCVideoEditView.getSegmentTo());
        mTXVideoEditer.setCutFromTime(mTCVideoEditView.getSegmentFrom(), mTCVideoEditView.getSegmentTo());
        mTXVideoEditer.processVideo();


    }

    @Override
    public void onProcessProgress(float v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWorkProgressFragment.setProgress((int) (v * 100));
            }
        });
    }

    @Override
    public void onProcessComplete(TXVideoEditConstants.TXGenerateResult txGenerateResult) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mWorkProgressFragment != null && mWorkProgressFragment.isAdded()) {
                    mWorkProgressFragment.dismiss();
                }
                if (txGenerateResult.retCode == TXVideoEditConstants.GENERATE_RESULT_OK) {
                    mWrapper.getTXVideoInfo().duration = mCutterEndTime - mCutterStartTime;
                    //返回上一个页面
                    Intent intent = getIntent();
                    //, mTCVideoEditView.getSegmentTo()
                    intent.putExtra("starttime",mTCVideoEditView.getSegmentFrom());
                    intent.putExtra("endtime",mTCVideoEditView.getSegmentTo());
                    setResult(345,intent);
                    finish();
                    mGenerateSuccess = true;
                } else {
                    Toast.makeText(VideoCutActivity.this, "失败了", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 主线程的Handler 用于处理load 视频信息的完后的动作
     */
    private static class VideoMainHandler extends Handler {
        static final int LOAD_VIDEO_SUCCESS = 0;
        static final int LOAD_VIDEO_ERROR = -1;
        private WeakReference<VideoCutActivity> mWefActivity;


        VideoMainHandler(VideoCutActivity activity) {
            mWefActivity = new WeakReference<VideoCutActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            VideoCutActivity activity = mWefActivity.get();
            if (activity == null) return;
            switch (msg.what) {
                case LOAD_VIDEO_ERROR:
                  /*  DialogUtil.showDialog(activity, TCApplication.getApplication().getResources().getString(R.string.tc_video_cutter_activity_video_main_handler_edit_failed),
                            TCApplication.getApplication().getResources().getString(R.string.tc_video_cutter_activity_video_main_handler_does_not_support_android_version_below_4_3),
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            });*/
                    Toast.makeText(activity, "加载失败", Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_VIDEO_SUCCESS:
                    activity.loadVideoSuccess((TXVideoEditConstants.TXVideoInfo) msg.obj);
                    break;
            }
        }
    }

    private TXVideoEditer.TXThumbnailListener mThumbnailListener2 = new TXVideoEditer.TXThumbnailListener() {
        @Override

        public void onThumbnail(final int index, long timeMs, final Bitmap bitmap) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTCVideoEditView.addBitmap(index, bitmap);
                }
            });
        }
    };

    private void loadVideoSuccess(TXVideoEditConstants.TXVideoInfo videoInfo) {
       /* if (mLoadingProgressDialog != null) {
            mLoadingProgressDialog.dismiss();
        }*/
        int duration = (int) (videoInfo.duration / 1000); //s
        int thumbCount = duration / 3;
        mTXVideoEditer.getThumbnail(thumbCount, 100, 100, true, mThumbnailListener2);
        mTCVideoEditView.setMediaFileInfo(videoInfo);

        TXVideoEditConstants.TXPreviewParam param = new TXVideoEditConstants.TXPreviewParam();
        param.renderMode = TXVideoEditConstants.PREVIEW_RENDER_MODE_FILL_EDGE;
        param.videoView = mPlayer;
        mTXVideoEditer.initWithPreview(param);

        mCutterStartTime = 0;
        mCutterEndTime = videoInfo.duration;

        mRotation = 0;
        mTXVideoEditer.setRenderRotation(0);
        mTXVideoEditer.startPlayFromTime(0, videoInfo.duration);
        mCurrentState = PlayState.STATE_PLAY;

        if (duration >= 16) {
            duration = 16;
        }
        //时间的显示
     /*   mTvChoose.setText(
                getResources().getString(R.string.tc_video_cutter_activity_load_video_success_already_picked)
                        + String.valueOf(duration)
                        + "s");*/
        mTCVideoEditView.setCount(thumbCount);
        mTCVideoEditView.setVisibility(View.VISIBLE);
    }
    /**
     * ===========================================加载视频相关 ===========================================
     */

    /**
     * 加在视频信息的runnable
     */
    private static class LoadVideoRunnable implements Runnable {
        private WeakReference<VideoCutActivity> mWekActivity;

        LoadVideoRunnable(VideoCutActivity activity) {
            mWekActivity = new WeakReference<VideoCutActivity>(activity);
        }

        @Override
        public void run() {
            if (mWekActivity == null || mWekActivity.get() == null) {
                return;
            }
            VideoCutActivity activity = mWekActivity.get();
            if (activity == null) return;
            Log.i("WXY", "run: " + activity.mInVideoPath);
            TXVideoEditConstants.TXVideoInfo info = TXVideoInfoReader.getInstance().getVideoFileInfo(activity.mInVideoPath);

            if (info == null) {// error 发生错误
                activity.mVideoMainHandler.sendEmptyMessage(VideoMainHandler.LOAD_VIDEO_ERROR);
            } else {
                TCVideoEditerWrapper.getInstance().setTXVideoInfo(info);
                Message msg = Message.obtain();
                msg.what = VideoMainHandler.LOAD_VIDEO_SUCCESS;
                msg.obj = info;
                activity.mVideoMainHandler.sendMessage(msg);
            }
        }
    }
}
