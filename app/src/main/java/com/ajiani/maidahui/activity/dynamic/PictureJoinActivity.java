package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.video1.PlayState;
import com.ajiani.maidahui.Utils.video1.TCEditerUtil;
import com.ajiani.maidahui.Utils.video1.TCVideoEditerWrapper;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.tencent.TCConstants;
import com.ajiani.maidahui.weight.record.cut.VideoWorkProgressFragment;
import com.tencent.ugc.TXVideoEditConstants;
import com.tencent.ugc.TXVideoEditer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PictureJoinActivity extends SimpleActivity implements TCVideoEditerWrapper.TXVideoPreviewListenerWrapper, TXVideoEditer.TXVideoGenerateListener {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.picjoin_top)
    RelativeLayout picjoinTop;
    @BindView(R.id.layout_palyer)
    FrameLayout layoutPalyer;
    @BindView(R.id.picjoin_card)
    CardView picjoinCard;
    @BindView(R.id.transition1)
    ImageView transition1;
    @BindView(R.id.transition2)
    ImageView transition2;
    @BindView(R.id.transition3)
    ImageView transition3;
    @BindView(R.id.transition4)
    ImageView transition4;
    @BindView(R.id.transition5)
    ImageView transition5;
    @BindView(R.id.transition6)
    ImageView transition6;
    private TXVideoEditer mTXVideoEditer;
    private TCVideoEditerWrapper wrapper;
    private ArrayList<String> picPathList;
    private ArrayList<Bitmap> bitmapList;
    private long mVideoDuration;
    private int mCurrentState = PlayState.STATE_STOP;       // 播放器当前状态
    private VideoWorkProgressFragment mWorkLoadingProgress;
    private String mVideoOutputPath;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        picPathList = getIntent().getStringArrayListExtra(TCConstants.INTENT_KEY_MULTI_PIC_LIST);
        if (picPathList == null || picPathList.size() == 0) {
            finish();
            return;
        }
        wrapper = TCVideoEditerWrapper.getInstance();
        wrapper.addTXVideoPreviewListenerWrapper(this);
        mTXVideoEditer = new TXVideoEditer(this);
        wrapper.setEditer(mTXVideoEditer);

        decodeFileToBitmap(picPathList);
        int result = mTXVideoEditer.setPictureList(bitmapList, 20);
        if (result == TXVideoEditConstants.PICTURE_TRANSITION_FAILED) {
            Toast.makeText(this, "图片异常，停止编辑", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        mVideoDuration = mTXVideoEditer.setPictureTransition(TXVideoEditConstants.TX_TRANSITION_TYPE_LEFT_RIGHT_SLIPPING);
        TXVideoEditConstants.TXVideoInfo txVideoInfo = new TXVideoEditConstants.TXVideoInfo();
        txVideoInfo.duration = mVideoDuration;
        txVideoInfo.width = 720;
        txVideoInfo.height = 1280;
        wrapper.setTXVideoInfo(txVideoInfo);

        TXVideoEditConstants.TXPreviewParam param = new TXVideoEditConstants.TXPreviewParam();
        param.videoView = layoutPalyer;
        param.renderMode = TXVideoEditConstants.PREVIEW_RENDER_MODE_FILL_EDGE;
        mTXVideoEditer.initWithPreview(param);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TCVideoEditerWrapper.getInstance().addTXVideoPreviewListenerWrapper(this);
        startPlay();
    }

    private void startPlay() {
        if (mCurrentState == PlayState.STATE_NONE || mCurrentState == PlayState.STATE_STOP) {
            if (mTXVideoEditer != null) {
                mTXVideoEditer.startPlayFromTime(0, mVideoDuration);
                mCurrentState = PlayState.STATE_PLAY;
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        TCVideoEditerWrapper.getInstance().removeTXVideoPreviewListenerWrapper(this);
    }


    private void decodeFileToBitmap(List<String> picPathList) {
        bitmapList = new ArrayList<>();
        for (int i = 0; i < picPathList.size(); i++) {
            String filePath = picPathList.get(i);
            Bitmap bitmap = TCEditerUtil.decodeSampledBitmapFromFile(filePath, 720, 1280);
            bitmapList.add(bitmap);
            TCVideoEditerWrapper.getInstance().addThumbnailBitmap(0, bitmap);
        }
    }
    @Override
    protected int createLayout() {
        return R.layout.picture_join;
    }


    @OnClick({R.id.btn_back, R.id.btn_next, R.id.transition1, R.id.transition2, R.id.transition3, R.id.transition4, R.id.transition5, R.id.transition6})
    public void onViewClicked(View view) {
        long duration = mVideoDuration;
        mTXVideoEditer.stopPlay();
        switch (view.getId()) {
            case R.id.btn_back:
                mTXVideoEditer.release();
                finish();
                break;
            case R.id.btn_next:
                startGenerateVideo();
                break;
            case R.id.transition1: //左右
                duration = mTXVideoEditer.setPictureTransition(TXVideoEditConstants.TX_TRANSITION_TYPE_LEFT_RIGHT_SLIPPING);
                break;
            case R.id.transition2: //上下
                duration = mTXVideoEditer.setPictureTransition(TXVideoEditConstants.TX_TRANSITION_TYPE_UP_DOWN_SLIPPING);
                break;
            case R.id.transition3: //放大
                duration = mTXVideoEditer.setPictureTransition(TXVideoEditConstants.TX_TRANSITION_TYPE_ENLARGE);
                break;
            case R.id.transition4: //缩小
                duration = mTXVideoEditer.setPictureTransition(TXVideoEditConstants.TX_TRANSITION_TYPE_NARROW);
                break;
            case R.id.transition5: //旋转
                duration = mTXVideoEditer.setPictureTransition(TXVideoEditConstants.TX_TRANSITION_TYPE_ROTATIONAL_SCALING);
                break;
            case R.id.transition6: //淡入淡出
                duration = mTXVideoEditer.setPictureTransition(TXVideoEditConstants.TX_TRANSITION_TYPE_FADEIN_FADEOUT);
                break;

        }
        mTXVideoEditer.startPlayFromTime(0, duration);
    }

    private void startGenerateVideo() {
        mTXVideoEditer.stopPlay(); // 停止播放

        mCurrentState = PlayState.STATE_GENERATE;
        mVideoOutputPath = TCEditerUtil.generateVideoPath();

        if (mWorkLoadingProgress == null) {
            initWorkLoadingProgress();
        }
        mWorkLoadingProgress.setProgress(0);
        mWorkLoadingProgress.setCancelable(false);
        mWorkLoadingProgress.show(getSupportFragmentManager(), "progress_dialog");

        mTXVideoEditer.setVideoGenerateListener(this);
        mTXVideoEditer.generateVideo(TXVideoEditConstants.VIDEO_COMPRESSED_720P, mVideoOutputPath);
    }

    private void initWorkLoadingProgress() {
        if (mWorkLoadingProgress == null) {
            mWorkLoadingProgress = new VideoWorkProgressFragment();
            mWorkLoadingProgress.setOnClickStopListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopGenerate();
                    startPlay();
                }
            });
        }
        mWorkLoadingProgress.setProgress(0);
    }

    private void stopGenerate() {
        if (mCurrentState == PlayState.STATE_GENERATE) {
            mWorkLoadingProgress.dismiss();
            Toast.makeText(this, "取消视频生成", Toast.LENGTH_SHORT).show();
            mWorkLoadingProgress.setProgress(0);
            mCurrentState = PlayState.STATE_NONE;
            if (mTXVideoEditer != null) {
                mTXVideoEditer.cancel();
            }
        }
    }
    @Override
    public void onPreviewFinishedWrapper() {
        if (mTXVideoEditer != null) {
            mTXVideoEditer.startPlayFromTime(0, mVideoDuration);
            mCurrentState = PlayState.STATE_PLAY;
        }
    }
    @Override
    public void onPreviewProgressWrapper(int time) {

    }


    @Override // 生成进度回调
    public void onGenerateProgress(final float progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWorkLoadingProgress.setProgress((int) (progress * 100));
            }
        });
    }

    @Override // 生成完成
    public void onGenerateComplete(final TXVideoEditConstants.TXGenerateResult result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (result.retCode == TXVideoEditConstants.GENERATE_RESULT_OK) {
                    startCutActivity();
                }
                mCurrentState = PlayState.STATE_NONE;

            }
        });
    }
    private void startCutActivity() {
        mTXVideoEditer.release();
        Intent intent = new Intent(this, TCVideoCutterActivity.class);
        intent.putExtra(TCConstants.VIDEO_EDITER_PATH, mVideoOutputPath);

        startActivity(intent);
        finish();
    }
}
