package com.ajiani.maidahui.activity.dynamic;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.MainActivity;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Spannel;
import com.ajiani.maidahui.Utils.DialogUtil;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.Utils.TimeUtils;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.Utils.txrecord.ProgressFragmentUtil;
import com.ajiani.maidahui.activity.dynamic.txrecord.PictureSelActivity;
import com.ajiani.maidahui.adapter.MenuAdapter;
import com.ajiani.maidahui.adapter.dynamic.videorecord.ChangeVoiceAdapter;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.dynamic.ChanageVocie;
import com.ajiani.maidahui.tencent.TCConstants;
import com.ajiani.maidahui.weight.AutoLocateHorizontalView;
import com.ajiani.maidahui.weight.circle.CircularProgressView;
import com.ajiani.maidahui.weight.record.BeautySettingPannel;
import com.ajiani.maidahui.weight.record.RecordProgressView;
import com.example.txrecord.MusicInfo;
import com.example.txrecord.TCVideoFileInfo;
import com.example.txrecord.baseic.IVideoJoinKit;
import com.example.txrecord.baseic.UGCKitResult;
import com.example.txrecord.ettect.VideoEditerSDK;
import com.example.txrecord.utils.ConfigureLoader;
import com.example.txrecord.utils.EffectEditer;
import com.google.android.material.tabs.TabLayout;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.ugc.TXRecordCommon;
import com.tencent.ugc.TXUGCPartsManager;
import com.tencent.ugc.TXUGCRecord;
import com.tencent.ugc.TXVideoEditConstants;
import com.tencent.ugc.TXVideoJoiner;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TakePhotoActivity extends SimpleActivity implements IVideoJoinKit,TXRecordCommon.ITXVideoRecordListener,View.OnTouchListener, GestureDetector.OnGestureListener,
        ScaleGestureDetector.OnScaleGestureListener, BeautySettingPannel.IOnBeautyParamsChangeListener, TXVideoJoiner.TXVideoJoinerListener {
    @BindView(R.id.take_rel)
    AutoLocateHorizontalView rel;
    @BindView(R.id.spin_kit)
    FrameLayout spin_kit;
    @BindView(R.id.take_camare)
    ImageView takeCamare;
    @BindView(R.id.edit_musicname)
    TextView takeMusic;
    @BindView(R.id.take_videomusic)
    LinearLayout takeVideo;
    @BindView(R.id.take_cut)
    ImageView takeCut;
    @BindView(R.id.take_videocut)
    LinearLayout takeVideoCut;
    @BindView(R.id.take_camare2)
    ImageView takeCamare2;
    @BindView(R.id.take_camare3)
    ImageView takeCamare3;
    @BindView(R.id.take_circle)
    CircularProgressView circle;

    @BindView(R.id.take_change)
    ImageView takeChange;
    @BindView(R.id.recordprogress)
    RecordProgressView recordProgressView;
    @BindView(R.id.take_time)
    LinearLayout takeTimeLin;
    @BindView(R.id.dyna_musiclin)
    LinearLayout dynamusicLin;
    @BindView(R.id.dyna_photolin)
    LinearLayout dynaphotoLin;
    boolean take;
    @BindView(R.id.video_view)
    TXCloudVideoView videoView;
    @BindView(R.id.take_back)
    ImageView takeBack;
    @BindView(R.id.iv_snapshot_photo)
    ImageView mIvSnapshotPhoto;
    @BindView(R.id.take_changecarme)
    LinearLayout takeChangecarme;
    @BindView(R.id.take_lightlin)
    LinearLayout takeLightlin;
    @BindView(R.id.take_changespeed)
    ImageView takeChangespeed;
    @BindView(R.id.dyna_photo)
    ImageView dynaPhoto;
    @BindView(R.id.take_chagesplin)
    LinearLayout takeChagesplin;
    @BindView(R.id.take_time_start)
    TextView takeTimeStart;
    @BindView(R.id.take_farme)
    FrameLayout frameLayout;
    @BindView(R.id.take_time_end)
    TextView takeTimeEnd;
    @BindView(R.id.take_speedname)
    TextView speedName;
    @BindView(R.id.record_tv_filter)
    TextView mTvFilter;
    @BindView(R.id.take_tab)
    TabLayout takeTab;
    @BindView(R.id.take_lin)
    LinearLayout takeLin;
    @BindView(R.id.take_piclin)
    LinearLayout takePiclin;
    @BindView(R.id.take_nextLin)
    LinearLayout takeNextLin;
    @BindView(R.id.take_del)
    LinearLayout takeDel;

    @BindView(R.id.mask)
    FrameLayout mMaskLayout;
    @BindView(R.id.dyna_card)
    CardView cardView;
    @BindView(R.id.take_next)
    LinearLayout takeNext;
    @BindView(R.id.carme_rela)
    RelativeLayout carmeRela;
    @BindView(R.id.take_voicelin)
    LinearLayout takeVoicelin;
    @BindView(R.id.beauty_pannel)
    BeautySettingPannel mBeautyPannelView;
    @BindView(R.id.dyna_snapshot_img)
    ImageView dynaSnapshotImg;
    @BindView(R.id.dyna_snapshot_farm)
    FrameLayout dynaSnapshotFarm;
    @BindView(R.id.dyna_snashot)
    LinearLayout dynaSnashot;
    private TXUGCRecord mTXCameraRecord;
    //切换摄像头 判断
    boolean isFront = false;
    //打开闪光灯
    boolean isStartLight = false;
    //设置进度条的最大值】
    int maxProgress = 15;
    private String TAG = "wxy";
    //是都暂停录制
    boolean isPause;
    //是不是第一启动拍摄
    boolean isFirst = true;
    //变数是否显示
    boolean isSpeend = false;
    //是否是变声还是混响
    boolean isVoice = false;
    //是都是变声
    int voiceType = TXRecordCommon.VIDOE_VOICECHANGER_TYPE_0;
    //拍摄速度的变量
    int mRecordSpeed = TXRecordCommon.RECORD_SPEED_NORMAL;
    //判断是否要加白点
    boolean iswhite;
    //是否是录制
    boolean isVideo = true;




    private MyHandler mMyHandler = new MyHandler();
    /**
     * ------------------------ 滑动滤镜相关 ------------------------
     */

    private int mCurrentIndex = 0; // 当前滤镜Index
    private int mLeftIndex = 0, mRightIndex = 1;// 左右滤镜的Index
    private int mLastLeftIndex = -1, mLastRightIndex = -1; // 之前左右滤镜的Index
    private float mLeftBitmapRatio;      // 左侧滤镜的比例
    private float mMoveRatio;      // 滑动的比例大小
    private boolean mStartScroll;  // 已经开始滑动了标记
    private boolean mMoveRight;    // 滑动是否往右
    private boolean mIsNeedChange;    // 滤镜的是否需要发生改变
    private ValueAnimator mFilterAnimator;
    private boolean mIsDoingAnimator;
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private Bitmap mLeftBitmap;
    private Bitmap mRightBitmap;
    //--------------------------------------------------------------
    private TXVideoJoiner mTXVideoJoiner;
    private ProgressFragmentUtil mProgressFragmentUtil;
    ArrayList<CircularProgressView> circularProgressViews = new ArrayList<>();
    private Spannel.BeautyParams mBeautyParams = new Spannel.BeautyParams();
    private TXRecordCommon.TXUGCSimpleConfig param;
    private String customVideoPath;
    ArrayList<ChanageVocie> chanageVocies = new ArrayList<>();
    ArrayList<ChanageVocie> chanageSound = new ArrayList<>();
    private String customCoverPath;
    private ScaleAnimation scaleAnimation;
    private ScaleAnimation scaleAnimation2;
    private String mOutputPath;
    private String path;

    public static MusicInfo musicInfo;
    private int lastStartTime;
    private int lastTime=-1;
    private String musicUrl;
    private int musicID;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (view == mMaskLayout) {
            if (motionEvent.getPointerCount() >= 2) {
                mScaleGestureDetector.onTouchEvent(motionEvent);
            } else if (motionEvent.getPointerCount() == 1) {
                mGestureDetector.onTouchEvent(motionEvent);
                // 说明是滤镜滑动后结束
                if (mStartScroll && motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    doFilterAnimator();
                }
            }
        }
        return true;
    }


    private ValueAnimator generateValueAnimator(float start, float end) {
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(400);
        return animator;
    }

    // OnGestureListener回调start
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        mStartScroll = false;
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (mIsDoingAnimator) {
            return true;
        }
        boolean moveRight = e2.getX() > e1.getX();
        if (moveRight && mCurrentIndex == 0) {
            return true;
        } else if (!moveRight && mCurrentIndex == mBeautyPannelView.getBeautyFilterArr().length - 1) {
            return true;
        } else {
            mStartScroll = true;
            if (moveRight) {//往右滑动
                mLeftIndex = mCurrentIndex - 1;
                mRightIndex = mCurrentIndex;
            } else {// 往左滑动
                mLeftIndex = mCurrentIndex;
                mRightIndex = mCurrentIndex + 1;
            }

            if (mLastLeftIndex != mLeftIndex) { //如果不一样，才加载bitmap出来；避免滑动过程中重复加载
                mLeftBitmap = mBeautyPannelView.getFilterBitmapByIndex(mLeftIndex);
                mLastLeftIndex = mLeftIndex;
            }

            if (mLastRightIndex != mRightIndex) {//如果不一样，才加载bitmap出来；避免滑动过程中重复加载
                mRightBitmap = mBeautyPannelView.getFilterBitmapByIndex(mRightIndex);
                mLastRightIndex = mRightIndex;
            }
            int width = mMaskLayout.getWidth();
            float dis = e2.getX() - e1.getX();
            float leftBitmapRatio = Math.abs(dis) / (width * 1.0f);

            float leftSpecialRatio = mBeautyPannelView.getFilterProgress(mLeftIndex) / 10.0f;
            float rightSpecialRatio = mBeautyPannelView.getFilterProgress(mRightIndex) / 10.0f;
            mMoveRatio = leftBitmapRatio;
            if (moveRight) {
                leftBitmapRatio = leftBitmapRatio;
            } else {
                leftBitmapRatio = 1 - leftBitmapRatio;
            }
            this.mMoveRight = moveRight;
            mLeftBitmapRatio = leftBitmapRatio;
            if (mTXCameraRecord != null)
                mTXCameraRecord.setFilter(mLeftBitmap, leftSpecialRatio, mRightBitmap, rightSpecialRatio, leftBitmapRatio);
            return true;
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    private void doTextAnimator() {
        // 设置当前滤镜的名字
        mTvFilter.setText(mBeautyPannelView.getBeautyFilterArr()[mCurrentIndex]);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(400);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTvFilter.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTvFilter.setVisibility(View.GONE);
                mIsDoingAnimator = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mTvFilter.startAnimation(alphaAnimation);
    }

    private void doFilterAnimator() {
        if (mMoveRatio >= 0.2f) { //当滑动距离达到0.2比例的时候，则说明要切换
            mIsNeedChange = true;
            if (mMoveRight) { //说明是右滑动
                mCurrentIndex--;
                mFilterAnimator = generateValueAnimator(mLeftBitmapRatio, 1);
            } else { //左滑动
                mCurrentIndex++;
                mFilterAnimator = generateValueAnimator(mLeftBitmapRatio, 0);
            }
        } else {
            if (mCurrentIndex == mLeftIndex) {//说明用户向左侧滑动
                mFilterAnimator = generateValueAnimator(mLeftBitmapRatio, 1);
            } else {
                mFilterAnimator = generateValueAnimator(mLeftBitmapRatio, 0);
            }
        }
        mFilterAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mIsDoingAnimator = true;
                if (mTXCameraRecord == null) return;
                float leftRatio = (float) valueAnimator.getAnimatedValue();
                // 动画结束
                if (leftRatio == 0 || leftRatio == 1) {
                    mLeftBitmapRatio = leftRatio;
                    if (mIsNeedChange) {
                        mIsNeedChange = false;
                        //设置字体的颜色
                        doTextAnimator();
                    } else {
                        mIsDoingAnimator = false;
                    }
                    //给下面设置东西
                    // mBeautyPannelView.setCurrentFilterIndex(mCurrentIndex);

                    // 保存到params 以便程序切换后恢复滤镜
                    if (mCurrentIndex == mLeftIndex) {
                        mBeautyParams.mFilterBmp = mLeftBitmap;
                    } else {
                        mBeautyParams.mFilterBmp = mRightBitmap;
                    }
                    mBeautyParams.mFilterMixLevel = mBeautyPannelView.getFilterProgress(mCurrentIndex);
                }
                float leftSpecialRatio = mBeautyPannelView.getFilterProgress(mLeftIndex) / 10.f;
                float rightSpecialRatio = mBeautyPannelView.getFilterProgress(mRightIndex) / 10.f;
                mTXCameraRecord.setFilter(
                        mLeftBitmap,
                        leftSpecialRatio,
                        mRightBitmap,
                        rightSpecialRatio, leftRatio
                );
            }


        });
        mFilterAnimator.start();
    }


    public void inits() {
        ChanageVocie sound = new ChanageVocie(R.mipmap.dyna_reverb_sound, R.mipmap.dyna_reverb_unsound,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_0, "原声", false);
        ChanageVocie chlidren = new ChanageVocie(R.mipmap.dyna_unchildern, R.mipmap.dyna_childern,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_1, "熊孩子", false);
        ChanageVocie lolita = new ChanageVocie(R.mipmap.dyna_unlolita, R.mipmap.dyna_lolita,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_2, "萝莉", false);
        ChanageVocie uncle = new ChanageVocie(R.mipmap.dyna_ununcle, R.mipmap.dyna_uncle,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_3, "大叔", false);
        ChanageVocie metal = new ChanageVocie(R.mipmap.dyna_unmetal, R.mipmap.dyna_metal,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_4, "重金属", false);
        ChanageVocie foreigner = new ChanageVocie(R.mipmap.dyna_unforeigner, R.mipmap.dyna_foreigner,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_6, "外国人", false);
        ChanageVocie suspet = new ChanageVocie(R.mipmap.dyna_unsuspect, R.mipmap.dyna_suspect,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_7, "困兽", false);
        ChanageVocie curtilage = new ChanageVocie(R.mipmap.dyna_uncurtilage, R.mipmap.dyna_curtilage,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_8, "死肥宅", false);
        ChanageVocie electricity = new ChanageVocie(R.mipmap.dyna_unelectricity, R.mipmap.dyna_electricity,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_9, "电流", false);
        ChanageVocie mechanical = new ChanageVocie(R.mipmap.dyna_unmechanical, R.mipmap.dyna_machinery,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_10, "重机械", false);
        ChanageVocie vacant = new ChanageVocie(R.mipmap.dyna_unintangible, R.mipmap.dyna_vacant,
                TXRecordCommon.VIDOE_VOICECHANGER_TYPE_11, "空灵", false);

        chanageVocies.add(sound);
        chanageVocies.add(chlidren);
        chanageVocies.add(lolita);
        chanageVocies.add(uncle);
        chanageVocies.add(metal);
        chanageVocies.add(mechanical);
        chanageVocies.add(foreigner);
        chanageVocies.add(electricity);
        chanageVocies.add(suspet);
        chanageVocies.add(curtilage);
        chanageVocies.add(vacant);
        //设置混响
        //


        ChanageVocie ktv = new ChanageVocie(R.mipmap.dyna_reverb_ktv, R.mipmap.dyna_reverb_unktv,
                TXRecordCommon.VIDOE_REVERB_TYPE_1, "ktv", false);
        ChanageVocie room = new ChanageVocie(R.mipmap.dyna_reverb_room, R.mipmap.dyna_reverb_unroom,
                TXRecordCommon.VIDOE_REVERB_TYPE_2, "房间", false);
        ChanageVocie hall = new ChanageVocie(R.mipmap.dyna_reverb_hall, R.mipmap.dyna_reverb_unhall,
                TXRecordCommon.VIDOE_REVERB_TYPE_3, "会堂", false);
        ChanageVocie lower = new ChanageVocie(R.mipmap.dyna_reverb_lower, R.mipmap.dyna_reverb_unlower,
                TXRecordCommon.VIDOE_REVERB_TYPE_4, "低沉", false);
        ChanageVocie resonant = new ChanageVocie(R.mipmap.dyna_reverb_resonant, R.mipmap.dyna_reverb_unresonant,
                TXRecordCommon.VIDOE_REVERB_TYPE_5, "洪亮", false);
        ChanageVocie magnetism = new ChanageVocie(R.mipmap.dyna_reverb_magnetism, R.mipmap.dyna_reverb_unmagnetism,
                TXRecordCommon.VIDOE_REVERB_TYPE_7, "磁性", false);
        ChanageVocie metals = new ChanageVocie(R.mipmap.dyna_reverb_metal, R.mipmap.dyna_reverb_unmetal,
                TXRecordCommon.VIDOE_REVERB_TYPE_6, "金属", false);

        chanageSound.add(ktv);
        chanageSound.add(room);
        chanageSound.add(hall);
        chanageSound.add(lower);
        chanageSound.add(resonant);
        chanageSound.add(metals);
        chanageSound.add(magnetism);

        chanageVocies.addAll(chanageSound);
    }

    @Override
    protected void initData() {

        //设置成黑色
        StatusBarUtil2.setStatusBarMode(this,false,R.color.blacks);

        inits();
        List<String> ajiani = getFilesAllName(getCustomVideoOutputPath("ajiani"));
        takeTab.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    mRecordSpeed = TXRecordCommon.RECORD_SPEED_SLOWEST;
                } else if (position == 1) {
                    mRecordSpeed = TXRecordCommon.RECORD_SPEED_SLOW;
                } else if (position == 2) {
                    mRecordSpeed = TXRecordCommon.RECORD_SPEED_NORMAL;
                } else if (position == 3) {
                    mRecordSpeed = TXRecordCommon.RECORD_SPEED_FAST;
                } else {
                    mRecordSpeed = TXRecordCommon.RECORD_SPEED_FASTEST;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mBeautyPannelView.setBeautyParamsChangeListener(this);

        //根据选中的tab 判断是录制还是拍照
        rel.setOnSelectedPositionChangedListener(new AutoLocateHorizontalView.OnSelectedPositionChangedListener() {
            @Override
            public void selectedPositionChanged(int pos) {
                switch (pos) {
                    case 0:
                        isVideo=true;
                        cardView.setBackground(null);
                        //拍60
                        maxProgress = 60;
                        //circle.setMaxProgress(maxProgress * 1000);
                       // recordProgressView.MAX_RECORD_TIME=30000f;
                        recordProgressView.setMaxTime(30000f);
                        //    init();
                        takeChagesplin.setVisibility(View.VISIBLE);
                        takeVoicelin.setVisibility(View.VISIBLE);
                        takeTab.setVisibility(View.INVISIBLE);
                        takePiclin.setVisibility(View.VISIBLE);
                        dynamusicLin.setVisibility(View.VISIBLE);
                        dynaphotoLin.setVisibility(View.VISIBLE);
                        takeNextLin.setVisibility(View.GONE);
                        dynaSnashot.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        isVideo=true;
                        cardView.setBackground(null);
                        //拍15
                        maxProgress = 15;
//                        recordProgressView.MAX_RECORD_TIME=7500f;
                        recordProgressView.setMaxTime(7500f);
                        //circle.setMaxProgress(maxProgress * 1000);
                        takeChagesplin.setVisibility(View.VISIBLE);
                        takeVoicelin.setVisibility(View.VISIBLE);
                        takeTab.setVisibility(View.INVISIBLE);
                        takePiclin.setVisibility(View.VISIBLE);
                        dynamusicLin.setVisibility(View.VISIBLE);
                        dynaphotoLin.setVisibility(View.VISIBLE);
                        takeNextLin.setVisibility(View.GONE);
                        dynaSnashot.setVisibility(View.INVISIBLE);
                        //     init();
                        break;
                    case 2:
                        isVideo=false;
                        cardView.setBackground(null);
                        //拍照
                        maxProgress = 0;
                        takeChagesplin.setVisibility(View.INVISIBLE);
                        takeVoicelin.setVisibility(View.INVISIBLE);
                        takeTab.setVisibility(View.INVISIBLE);
                        takePiclin.setVisibility(View.VISIBLE);
                        dynamusicLin.setVisibility(View.INVISIBLE);
                        dynaphotoLin.setVisibility(View.VISIBLE);
                        takeNextLin.setVisibility(View.GONE);
                        dynaSnashot.setVisibility(View.VISIBLE);
                        //拍照页面
                        break;
                }
            }
        });
    }

    //添加录制时的节点
    public void addCircle(float angle) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) circle.getLayoutParams();
        CircularProgressView circularProgressView = new CircularProgressView(this);
        circularProgressView.setLayoutParams(layoutParams);
        circularProgressView.setBackColor(R.color.transparent);
        circularProgressView.setProgColor(R.color.white);
        circularProgressView.setProgress(2000);
        float b = (float) (360.0 / ((maxProgress) * 1000));

        Log.i(TAG, "addCircle: " + angle + "====" + b);
        //现在录制的角度
        float c = angle * b;
        Log.i(TAG, "addCircle: " + c);
        /*ircularProgressView.setAngle((float) (275 + (c - 0.36)));
        circularProgressView.setProgress(15);
        frameLayout.addView(circularProgressView);*/
        circularProgressViews.add(circularProgressView);
    }

    @Override
    protected void initView() {
        dynaPhoto.setImageResource(R.mipmap.dyna_picture);
        //初始化视图
        init();


        take = false;
        mMaskLayout.setOnTouchListener(this);
        takeCamare.setVisibility(View.VISIBLE);
      //  circle.setMaxProgress(60000);
        ArrayList<String> strings1 = new ArrayList<>();
        //选择拍照的滑动
        strings1.add("拍60秒");
        strings1.add("拍15秒");
        strings1.add("拍照");
        MenuAdapter menuAdapter = new MenuAdapter(this, strings1, rel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rel.setLayoutManager(linearLayoutManager);
        rel.setAdapter(menuAdapter);
        takeTab.addTab(takeTab.newTab().setText("极慢"));
        takeTab.addTab(takeTab.newTab().setText("慢"));
        takeTab.addTab(takeTab.newTab().setText("标准"), true);
        takeTab.addTab(takeTab.newTab().setText("快"));
        takeTab.addTab(takeTab.newTab().setText("极快"));
        mGestureDetector = new GestureDetector(this, this);
        mScaleGestureDetector = new ScaleGestureDetector(this, this);
    }

    private void init() {
        mTXCameraRecord = TXUGCRecord.getInstance(this.getApplicationContext());
        mTXCameraRecord.setVideoRecordListener(this);
        mTXCameraRecord.setRecordSpeed(mRecordSpeed);
        mTXCameraRecord.setVoiceChangerType(voiceType);
        //设置滤镜效果程度
        mTXCameraRecord.setSpecialRatio(mBeautyParams.mFilterMixLevel / 10.0f);
        // 设置指定素材滤镜特效
        mTXCameraRecord.setFilter(mBeautyParams.mFilterBmp);
        //默认后置的
        param = new TXRecordCommon.TXUGCSimpleConfig();
        param.videoQuality = TXRecordCommon.VIDEO_QUALITY_MEDIUM;
        //聚焦
        param.touchFocus = true;
        // 设置横竖屏录制
        param.videoQuality = TXRecordCommon.VIDEO_QUALITY_HIGH;        // 720p
        param.isFront = true;           // 是否前置摄像头，使用
        param.minDuration = 2000;    // 视频录制的最小时长ms
        param.maxDuration = maxProgress * 1000;    // 视频录制的最大时长ms
        param.touchFocus = false; // false为自动聚焦；true为手动聚焦
        param.needEdit = true;
        mTXCameraRecord.setMute(false);
        //声音设置
        mTXCameraRecord.setMicVolume(1);
        //拍摄速度设置

        // 2、启动画面预览
        mTXCameraRecord.startCameraSimplePreview(param, videoView);
        mTXCameraRecord.switchCamera(false);
        mTXCameraRecord.setBeautyDepth(mBeautyParams.mBeautyStyle, mBeautyParams.mBeautyLevel, mBeautyParams.mWhiteLevel, mBeautyParams.mRuddyLevel);
        mTXCameraRecord.setFaceScaleLevel(mBeautyParams.mFaceSlimLevel);
        mTXCameraRecord.setEyeScaleLevel(mBeautyParams.mBigEyeLevel);
        mTXCameraRecord.setSpecialRatio(mBeautyParams.mFilterMixLevel / 10.0f);
        mTXCameraRecord.setFilter(mBeautyParams.mFilterBmp);
        mTXCameraRecord.setGreenScreenFile(mBeautyParams.mGreenFile, true);
        mTXCameraRecord.setMotionTmpl(mBeautyParams.mMotionTmplPath);
        mTXCameraRecord.setFaceShortLevel(mBeautyParams.mFaceShortLevel);
        mTXCameraRecord.setFaceVLevel(mBeautyParams.mFaceVLevel);
        mTXCameraRecord.setChinLevel(mBeautyParams.mChinSlimLevel);
        mTXCameraRecord.setNoseSlimLevel(mBeautyParams.mNoseScaleLevel);
        mTXCameraRecord.setBGMVolume(1);
        if(mTXCameraRecord.getPartsManager().getPartsPathList().size()>0){
            mTXCameraRecord.getPartsManager().deleteAllParts();
        }

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_take;
    }


    public boolean open = true;

    boolean isSnapshot;
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    @OnClick({R.id.take_changecarme,R.id.take_videocut,R.id.take_videomusic,R.id.back, R.id.dyna_photolin, R.id.voice_lin, R.id.dyna_snapshot_farm, R.id.take_lightlin, R.id.take_camare, R.id.take_next, R.id.take_chagesplin, R.id.take_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.take_videocut:
                //弹窗设置音乐起止时间
                PopupWindow popupWindow = new PopupWindow(this);
                View inflate = LayoutInflater.from(this).inflate(R.layout.activity_take_item, videoView, false);
                //绑定适配器
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(inflate);
                SeekBar seekBar = inflate.findViewById(R.id.micvolume);
                TextView musicTime=inflate.findViewById(R.id.take_music_time);
                seekBar.setMax((int) musicInfo.duration);
                //seekBar.setProgress((int) musicInfo.startTime);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        mTXCameraRecord.setBGM(musicInfo.path);
                        mTXCameraRecord.playBGMFromTime(progress, (int) musicInfo.duration);
                        musicTime.setText(TimeUtils.timeParse(progress));
                        musicInfo.startTime=progress;
                        //获取文本宽度
                        float textWidth = musicTime.getWidth();

                        //获取seekbar最左端的x位置
                        float left = seekBar.getLeft();

                        //进度条的刻度值
                        float max =Math.abs(seekBar.getMax());

                        //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
                        float thumb = dip2px(TakePhotoActivity.this,15);

                        //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
                        float average = (((float) seekBar.getWidth())-2*thumb)/max;

                        //int to float
                        float currentProgress = progress;

                        //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
                        float pox = left - textWidth/2 +thumb + average * currentProgress;
                        musicTime.setX(pox);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }

                });
                inflate.findViewById(R.id.dyna_take_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTXCameraRecord.pauseBGM();
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.dyna_take_true).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        musicInfo.startTime=seekBar.getProgress();
                        //取消所保存的
                        popupWindow.dismiss();
                        mTXCameraRecord.pauseBGM();

                    }
                });
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAtLocation(videoView,Gravity.BOTTOM,0,0);
                break;
            case R.id.take_videomusic:
                //选择音乐
                JumpUtils.gotoActivity(this,MusicActivity.class,new Bundle(),1213);
                break;

            case R.id.dyna_photolin:
                //选择照片
                if(isVideo){
                    showVideo();
                }else{
                    showPicture();
                }
              //  JumpUtils.gotoActivity(this, PictureSelActivity.class,new Bundle());
                break;
            case R.id.back:
                finish();
                //清空缓存的视频
                break;
            case R.id.dyna_snapshot_farm:
                //切换快拍模式
                if (isSnapshot) {
                    dynaSnapshotImg.setImageResource(R.mipmap.dyna_circle);
                    isSnapshot = false;
                } else {
                    dynaSnapshotImg.setImageResource(R.mipmap.dyna_right);
                    isSnapshot = true;
                }
                break;
            case R.id.take_changecarme:
                // 切换到后置摄像头 true 切换到前置摄像头 false 切换到后置摄像头
                if (isFront) {
                    mTXCameraRecord.switchCamera(false);
                    isFront = false;
                    takeLightlin.setVisibility(View.VISIBLE);
                } else {
                    isFront = true;
                    mTXCameraRecord.switchCamera(true);
                    takeLightlin.setVisibility(View.GONE);
                }
                break;
            case R.id.take_lightlin:
                //闪光灯
                // 打开闪光灯 true为打开， false为关闭.
                if (isStartLight) {
                    isStartLight = false;
                    mTXCameraRecord.toggleTorch(false);
                    takeChange.setImageResource(R.mipmap.shanguangdengguanbi);
                } else {
                    isStartLight = true;
                    mTXCameraRecord.toggleTorch(true);
                    takeChange.setImageResource(R.mipmap.shanguangdengkaiqi);
                }
                break;
            case R.id.take_chagesplin:
                //变速的显示和隐藏
                if (isSpeend) {
                    takeTab.setVisibility(View.INVISIBLE);
                    isSpeend = false;
                    speedName.setText("速度:关");
                } else {
                    takeTab.setVisibility(View.VISIBLE);
                    isSpeend = true;
                    speedName.setText("速度:开");
                }

                break;
            case R.id.take_camare:
                //播放动画


                if (isFirst) {
                    isFirst = false;
                    //拍照 还是录制
                    if (maxProgress == 0) {
                        takePic();
                    } else {
                        //拍摄视频
                       /* if(mediaPlayer!=null){
                            mediaPlayer.setOnDrmPreparedListener(this);
                        }*/
                        takeCut.setImageResource(R.mipmap.unvideocut);
                        takeVideo.setEnabled(false);
                        takeVideoCut.setEnabled(false) ;
                        takeMusic.setTextColor(getResources().getColor(R.color.mine_unselect));
                        showtime();
                        //拍摄视频，启动进度条
                    }

                } else {
                    //暂停之后
                    TXUGCPartsManager mTXUGCPartsManager = mTXCameraRecord.getPartsManager();
                     //判断是继续拍摄还是暂停
                    if (isPause) {

                        mTXCameraRecord.pauseBGM();
                        pause();
                        takeCamare2.clearAnimation();
                        takeCamare3.clearAnimation();
                        recordProgressView.changeRecordState(RecordProgressView.RecordState.PAUSE);
                        frameLayout.setVisibility(View.VISIBLE);
                        mTXCameraRecord.pauseRecord();
                        //      mTXCameraRecord.stopRecord();
                        isPause = false;

                    } else {

                        resume();

                        takeCamare2.startAnimation(scaleAnimation);
                        takeCamare3.startAnimation(scaleAnimation2);
                        recordProgressView.changeRecordState(RecordProgressView.RecordState.START);
                        frameLayout.setVisibility(View.GONE);
                       /* if (iswhite) {
                            addCircle(circle.getProgress());
                        }
*//*
                        if(musicInfo!=null){
                            mTXCameraRecord.playBGMFromTime(, (int) musicInfo.duration);
                        }      */
                        int lastStartTime = recordProgressView.getLastStartTime();
                        int lastTime = recordProgressView.getLastTime();
                        //得到进度条的时间
                        int time = recordProgressView.getTime();

                        int duration = mTXUGCPartsManager.getDuration();
                        Log.i(TAG, "onViewClicked: "+time);
//                        if(lastTime==-1){
                            if(musicInfo!=null){
                                time+=musicInfo.startTime;
                                mTXCameraRecord.playBGMFromTime(time, (int) musicInfo.duration);
                            }
                       /* }else{
                            if(musicInfo!=null){
                                mTXCameraRecord.playBGMFromTime(duration, (int) musicInfo.duration);
                                lastTime=-1;
                            }
                        }*/

                        mTXCameraRecord.resumeRecord();
                        isPause = true;
                    }
                }
                break;
            //回删视频
            case R.id.take_del:
                TXUGCPartsManager partsManager = mTXCameraRecord.getPartsManager();
                partsManager.deleteLastPart();
                recordProgressView.changeRecordState(RecordProgressView.RecordState.ROLLBACK);
                recordProgressView.changeRecordState(RecordProgressView.RecordState.DELETE);

                lastTime = recordProgressView.getLastTime();
                lastStartTime = recordProgressView.getLastStartTime();
                int duration = partsManager.getDuration();


                if(duration==0){
                    takeCut.setImageResource(R.mipmap.cut_music);
                    takeVideo.setEnabled(true);
                    takeVideoCut.setEnabled(true) ;
                    takeMusic.setTextColor(getResources().getColor(R.color.white));
                }
             //   circle.setProgress((float) duration);
                //删除白点
               /* if (circularProgressViews.size() > 0) {
                    frameLayout.removeView(circularProgressViews.get(circularProgressViews.size() - 1));
                    circularProgressViews.remove(circularProgressViews.size() - 1);
                    iswhite = true;
                } else {
                    iswhite = false;
                }*/

                break;
            case R.id.take_next:
         /*       Intent intent = new Intent(this, LoginActivity.class);
            //    intent.putExtra(TCConstants.VIDEO_EDITER_PATH, customVideoPath);
                startActivity(intent);
*/
                //
                //彈窗


                stopRecord();
                break;
            case R.id.voice_lin:
                //弹出popwindow
                showPop();

                break;
        }
    }



    private void showPop() {
        PopupWindow popupWindow = new PopupWindow(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.dyna_voice_item, null, false);
        //绑定适配器
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(inflate);
        RecyclerView dynaRel = inflate.findViewById(R.id.dyna_voice_rel);
        TabLayout tabLayout = inflate.findViewById(R.id.dyna_voice_tab);
        tabLayout.addTab(tabLayout.newTab().setText("变声"));
        tabLayout.addTab(tabLayout.newTab().setText("混响"));
        ChangeVoiceAdapter changeVoiceAdapter = new ChangeVoiceAdapter(chanageVocies);
        dynaRel.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        dynaRel.setAdapter(changeVoiceAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                  /*  ChangeVoiceAdapter changeVoiceAdapter = new ChangeVoiceAdapter(chanageVocies);
                    dynaRel.setLayoutManager(new LinearLayoutManager(TakePhotoActivity.this,RecyclerView.HORIZONTAL,false));
                    dynaRel.setAdapter(changeVoiceAdapter);*/
                    for (int i = 0; i < changeVoiceAdapter.mList.size(); i++) {
                        if (changeVoiceAdapter.mList.get(i).isIssel()) {
                            dynaRel.smoothScrollToPosition(i);
                            break;
                        } else if (i == changeVoiceAdapter.mList.size() - 1) {
                            dynaRel.smoothScrollToPosition(0);
                            break;
                        }
                    }
                } else {
                   /* ChangeVoiceAdapter changeVoiceAdapter = new ChangeVoiceAdapter(chanageSound);
                    dynaRel.setLayoutManager(new LinearLayoutManager(TakePhotoActivity.this,RecyclerView.HORIZONTAL,false));
                    dynaRel.setAdapter(changeVoiceAdapter);*/

                    for (int i = 0; i < changeVoiceAdapter.mList.size(); i++) {
                        if (changeVoiceAdapter.mList.get(i).isIssel()) {
                            dynaRel.smoothScrollToPosition(i);
                            break;
                        } else if (i == changeVoiceAdapter.mList.size() - 1) {
                            dynaRel.smoothScrollToPosition(15);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        changeVoiceAdapter.setOnClickLinstener(new ChangeVoiceAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                ChanageVocie chanageVocie = changeVoiceAdapter.mList.get(posstion);
                changeVoiceAdapter.clear();
                chanageVocie.setIssel(true);
                changeVoiceAdapter.notifyDataSetChanged();
                if (posstion > 10) {
                    isVoice = false;
                } else {
                    isVoice = true;
                }

                voiceType = changeVoiceAdapter.mList.get(posstion).getType();
            }
        });

        popupWindow.showAtLocation(takeTab, Gravity.BOTTOM, 0, 0);

    }

    //停止播放
    private void stopRecord() {
        if (mTXCameraRecord.getPartsManager().getPartsPathList().size() == 0) {
            return;
        }
        //    mCompleteProgressDialog.show();
        spin_kit.setVisibility(View.VISIBLE);
        if (mTXCameraRecord != null) {
            mTXCameraRecord.stopRecord();
        }
    }



    private void resume() {
        if (isVoice) {
            //设置变声
            mTXCameraRecord.setReverb(TXRecordCommon.VIDOE_REVERB_TYPE_0);
            mTXCameraRecord.setVoiceChangerType(voiceType);
        } else {
            mTXCameraRecord.setVoiceChangerType(TXRecordCommon.VIDOE_VOICECHANGER_TYPE_0);
            //设置混响
            mTXCameraRecord.setReverb(voiceType);
        }
        mTXCameraRecord.setRecordSpeed(mRecordSpeed);
        takeVoicelin.setVisibility(View.INVISIBLE);
        carmeRela.setVisibility(View.GONE);
        rel.setVisibility(View.GONE);
        takeNextLin.setVisibility(View.VISIBLE);
        takeDel.setVisibility(View.INVISIBLE);
        takePiclin.setVisibility(View.GONE);
        takeTab.setVisibility(View.INVISIBLE);

    }

    private void pause() {

// 获取片段管理对象
        TXUGCPartsManager partsManager = mTXCameraRecord.getPartsManager();
        List<String> partsPathList = partsManager.getPartsPathList();
        for (int i = 0; i < partsPathList.size(); i++) {
            Log.i(TAG, "pause: " + partsPathList.get(i));
        }

        //暂停时显和隐藏
        takeVoicelin.setVisibility(View.VISIBLE);
        carmeRela.setVisibility(View.VISIBLE);
        rel.setVisibility(View.VISIBLE);
        takeNextLin.setVisibility(View.VISIBLE);
        takeDel.setVisibility(View.VISIBLE);
        takePiclin.setVisibility(View.GONE);
        if (isSpeend) {
            takeTab.setVisibility(View.VISIBLE);
        } else {
            takeTab.setVisibility(View.INVISIBLE);
        }

    }

    //视频的地址
    private String getCustomVideoOutputPath(String fileNamePrefix) {
        //放app目录下   voice
        long currentTime = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        String time = sdf.format(new Date(currentTime));
        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_MOVIES);

        String outputDir = Environment.getExternalStorageDirectory() + File.separator + TCConstants.OUTPUT_DIR_NAME;
        File outputFolder = new File(outputDir);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
        String tempOutputPath;
        if (TextUtils.isEmpty(fileNamePrefix)) {
            tempOutputPath = outputDir + File.separator + "TXUGC_" + time + ".mp4";
        } else {
            tempOutputPath = outputDir + File.separator + "TXUGC_" + fileNamePrefix + time + ".mp4";
        }
        //Log.i(TAG, "getCustomVideoOutputPath: " + externalFilesDir.getPath() + "===" + tempOutputPath);
        return tempOutputPath;
    }

    public List<String> getFilesAllName(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        if (files == null) {
            Log.e("error", "空目录");
            return null;
        }
        List<String> s = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            Log.i(TAG, "getFilesAllName: " + files[i].getAbsolutePath());
        }
        return s;
    }

    private void showtime() {
        TXUGCPartsManager mTXUGCPartsManager = mTXCameraRecord.getPartsManager();
        if (mTXUGCPartsManager.getPartsPathList().size() > 0
        ) {
            Log.i(TAG, "onRecordComplete: " + mTXUGCPartsManager.getPartsPathList().size() + mTXUGCPartsManager.getPartsPathList().get(0));

        }
        iswhite = true;
        //隐藏所有东西
        mTXCameraRecord.setVoiceChangerType(voiceType);
        takeVoicelin.setVisibility(View.INVISIBLE);
        carmeRela.setVisibility(View.GONE);
        rel.setVisibility(View.GONE);
        takePiclin.setVisibility(View.GONE);
        takeNextLin.setVisibility(View.VISIBLE);
        takeDel.setVisibility(View.INVISIBLE);
        //设置视频存储地址
        // 开始录制
        isPause = true;
        customVideoPath = getCustomVideoOutputPath("ajiani");
        customCoverPath = customVideoPath.replace(".mp4", ".jpg");

        //进度条，按钮动画
        recordProgressView.changeRecordState(RecordProgressView.RecordState.START);
        frameLayout.setVisibility(View.GONE);
        //2大 3中
       /* Animation animation = new ScaleAnimation(0, 1.0f, 0f, 1.0f);
        animation.setDuration(1500);//动画时间
        animation.setRepeatCount(-1);//动画的重复次数
        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        takeCamare2.startAnimation(animation);//开始动画*/


        scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.take_scale2);
        takeCamare2.startAnimation(scaleAnimation);
        scaleAnimation2 = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.take_scale);
        takeCamare3.startAnimation(scaleAnimation2);
        if(musicInfo!=null){
            mTXCameraRecord.playBGMFromTime((int) musicInfo.startTime, (int) musicInfo.duration);
        }

        // 开始录制，可以指定输出视频文件地址和封面地址
        int result = mTXCameraRecord.startRecord(customVideoPath, customCoverPath);
        request(result);

    }

    private void request(int result) {
        //检查录制时的状态
        String desc = null;
        switch (result) {
            case TXRecordCommon.START_RECORD_OK:
                desc = getResources().getString(R.string.tc_video_record_activity_start_record_start_record_ok);
                break;
            case TXRecordCommon.START_RECORD_ERR_IS_IN_RECORDING:
                desc = getResources().getString(R.string.tc_video_record_activity_start_record_start_record_err_is_in_recording);
                break;
            case TXRecordCommon.START_RECORD_ERR_VIDEO_PATH_IS_EMPTY:
                desc = getResources().getString(R.string.tc_video_record_activity_start_record_start_record_err_video_path_is_empty);
                break;
            case TXRecordCommon.START_RECORD_ERR_API_IS_LOWER_THAN_18:
                desc = getResources().getString(R.string.tc_video_record_activity_start_record_start_record_err_api_is_lower_than_18);
                break;
            case TXRecordCommon.START_RECORD_ERR_NOT_INIT:
                desc = getResources().getString(R.string.tc_video_record_activity_start_record_start_record_err_not_init);
                break;
            case TXRecordCommon.START_RECORD_ERR_LICENCE_VERIFICATION_FAILED:
                desc = getResources().getString(R.string.tc_video_record_activity_start_record_start_record_err_licence_verification_failed);
                break;
        }
        Log.i(TAG, "request: " + desc);
    }

    private static final int MSG_TAKE_PHOTO_SUCCESS = 1;
    private static final int MSG_TAKE_PHOTO_FAIL = 2;

    @Override
    public void onJoinProgress(float v) {
        Log.i(TAG, "onJoinProgress: "+v);

        mProgressFragmentUtil.updateGenerateProgress((int) (v * 100));
    }

    @Override
    public void onJoinComplete(TXVideoEditConstants.TXJoinerResult txJoinerResult) {

        mProgressFragmentUtil.dismissLoadingProgress();
        if (txJoinerResult.retCode == TXVideoEditConstants.JOIN_RESULT_OK) {


            Intent intent = new Intent(this, TCVideoCutterActivity.class);
            intent.putExtra(TCConstants.VIDEO_EDITER_PATH, mOutputPath);


            startActivity(intent);
               /* UGCKitResult ugcKitResult = new UGCKitResult();
                ugcKitResult.errorCode = txJoinerResult.retCode;
                ugcKitResult.descMsg = txJoinerResult.descMsg;
                ugcKitResult.outputPath =mOutputPath ;
                mOnVideoJoinListener.onJoinCompleted(ugcKitResult);*/
            }


    }

   /* @Override
    public void onJoinCanceled() {

    }

    @Override
    public void onJoinCompleted(UGCKitResult ugcKitResult) {
        //跳转裁剪页面
        Intent intent = new Intent(this, TCVideoCutterActivity.class);
        intent.putExtra(TCConstants.VIDEO_EDITER_PATH, ugcKitResult.outputPath);

        startActivity(intent);
       // finish();
    }*/

    @Override
    public void setVideoJoinList(ArrayList<TCVideoFileInfo> videoList) {
      /*  mTCVideoFileInfoList = videoList;
        if (mTCVideoFileInfoList == null || mTCVideoFileInfoList.size() == 0) {
            if (mOnVideoJoinListener != null) {
                mOnVideoJoinListener.onJoinCanceled();
            }
            return;
        }
        startJoin();*/
    }

    @Override
    public void setVideoJoinListener(OnVideoJoinListener videoJoinListener) {

    }




    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_TAKE_PHOTO_SUCCESS: {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    // showSnapshotPhoto(bitmap);
                    break;
                }
                case MSG_TAKE_PHOTO_FAIL: {
                  /*  Toast.makeText(TCVideoRecordActivity.this, getResources().getString(R.string.activity_video_record_take_photo_fail), Toast.LENGTH_SHORT).show();
                    mIsTakingPhoto = true;*/
                    break;
                }
                default:
                    break;

            }
        }
    }

    private void showSnapshotPhoto(Bitmap bitmap) {
        mIvSnapshotPhoto.setTranslationX(0.0f);
        mIvSnapshotPhoto.setTranslationY(0.0f);
        mIvSnapshotPhoto.setScaleX(1.0f);
        mIvSnapshotPhoto.setScaleY(1.0f);
        mIvSnapshotPhoto.setPivotX(0.0f);
        mIvSnapshotPhoto.setPivotY(0.0f);
        mIvSnapshotPhoto.setAlpha(1.0f);
        mIvSnapshotPhoto.setImageBitmap(bitmap);
        mIvSnapshotPhoto.setVisibility(View.VISIBLE);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float screenWidth = dm.widthPixels;

        float vWidth = mIvSnapshotPhoto.getWidth();

        float density = getResources().getDisplayMetrics().density;

        float targetWidthInDP = 80;

        float targetWidth = targetWidthInDP * density;

        float scale = targetWidth / vWidth;

        float targetLocalX = screenWidth - 40 * density - targetWidth;
        float targetLocalY = 40 * density;

        float translationX = targetLocalX - 0;
        float translationY = targetLocalY - 0;

        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(mIvSnapshotPhoto, "scaleX", 1.0f, scale);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(mIvSnapshotPhoto, "scaleY", 1.0f, scale);
        ObjectAnimator animatorTranslationX = ObjectAnimator.ofFloat(mIvSnapshotPhoto, "translationX", 0.0f, translationX);
        ObjectAnimator animatorTranslationY = ObjectAnimator.ofFloat(mIvSnapshotPhoto, "translationY", 0.0f, translationY);

        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.setDuration(500);
        animatorSet1.setInterpolator(new DecelerateInterpolator());
        animatorSet1.play(animatorScaleX).with(animatorScaleY).with(animatorTranslationX).with(animatorTranslationY);

        ObjectAnimator animatorFadeOut = ObjectAnimator.ofFloat(mIvSnapshotPhoto, "alpha", 1.0f, 1.0f, 0.0f);


        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(500);
        animatorSet2.setInterpolator(new LinearInterpolator());
        animatorSet2.play(animatorFadeOut);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorSet1);
        animatorSet.play(animatorSet2).after(animatorSet1);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIvSnapshotPhoto.setVisibility(View.INVISIBLE);

                mIvSnapshotPhoto.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.start();

    }

    private void takePic() {
        Toast.makeText(this, "拍照", Toast.LENGTH_SHORT).show();
        //拍摄照片
        mTXCameraRecord.snapshot(new TXRecordCommon.ITXSnapshotListener() {
            @Override
            public void onSnapshot(Bitmap bmp) {
                //保存或者显示截图

                isFirst = true;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        cardView.setBackgroundResource(R.mipmap.mine_shopbg);
                        dynaPhoto.setImageBitmap(bmp);
                        String fileName = System.currentTimeMillis() + ".jpg";
                        MediaStore.Images.Media.insertImage(TakePhotoActivity.this.getContentResolver(), bmp, fileName, null);
                        Message message = new Message();
                        message.what = MSG_TAKE_PHOTO_SUCCESS;
                        message.obj = bmp;
                        mMyHandler.sendMessage(message);
                    }
                });
            }
        });
    }

    @Override
    public void onRecordEvent(int i, Bundle bundle) {

    }

    @Override
    public void onRecordProgress(long l) {
        //得到录制的时长 给进度条赋值
        //float a= ()l/1000;
        // float b=a*100/15;l

        if (l > 2000) {
            //显示下一步
            takeNext.setVisibility(View.VISIBLE);
        }
       // circle.setProgress(l);
    }

    private void pauseRecord() {
        isPause = true;
        //  mIvDeleteLastPart.setImageResource(R.drawable.selector_delete_last_part);
        //可以删除一段I一段的视频了
        //  mIvDeleteLastPart.setEnabled(true);

        if (mTXCameraRecord != null) {

            mTXCameraRecord.pauseRecord();

        }
        //聚焦
        // abandonAudioFocus();


    }

    @Override
    public void onRecordComplete(TXRecordCommon.TXRecordResult result) {

        TXUGCPartsManager mTXUGCPartsManager = mTXCameraRecord.getPartsManager();

        if (result.retCode < 0) {

        } else {

        }
        //  desc = "onRecordComplete录制失败:" + result.descMsg;
        if (result.retCode < 0) {
            isPause = false;
        } else {

            //   desc = "视频录制成功";            isPause = true;
            if (mTXCameraRecord != null) {
                mTXCameraRecord.pauseRecord();
            }
            startPreprocess(result.videoPath);
        }


    }

    private void startPreprocess(String videoPath) {
        Intent intent = new Intent(this, VideoEditActivity.class);
        intent.putExtra(TCConstants.VIDEO_EDITER_PATH, videoPath);
        intent.putExtra("thumb", customCoverPath);
        if(musicInfo!=null){
            intent.putExtra("starttime", musicInfo.startTime);
            intent.putExtra("endtime", musicInfo.endTime);
            intent.putExtra("path", musicInfo.path);
            intent.putExtra("thumbth", musicInfo.thumb);
            intent.putExtra("musicurl",musicUrl);
            intent.putExtra("musicname", musicInfo.name);
            if(musicID!=0){
                intent.putExtra("musicid",musicID );
            }

        }
        spin_kit.setVisibility(View.GONE);


        pause();
        takeCamare2.clearAnimation();
        takeCamare3.clearAnimation();
        recordProgressView.changeRecordState(RecordProgressView.RecordState.PAUSE);
        frameLayout.setVisibility(View.VISIBLE);
        isPause=false;


        startActivity(intent);

    }

    //选择视频 小于20秒
    private void showVideo() {
        PictureSelector.create(TakePhotoActivity.this)
                .openGallery(PictureMimeType.ofVideo())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(3)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .isCamera(false)// 是否显示拍照按钮
                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(3, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                //.selectionMedia(selectList)// 是否传入已选图片
                //.previewEggs(false1)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()+//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                // .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                .videoMaxSecond(50)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(5)// 显示多少秒以内的视频or音频也可适用 int
                .rotateEnabled(false) // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                .recordVideoSecond(10)//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回五种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 4.media.getOriginalPath()); media.isOriginal());为true时此字段才有值
                    // 5.media.getAndroidQToPath();为Android Q版本特有返回的字段，此字段有值就用来做上传使用
                    // 如果同时开启裁剪和压缩，则取压缩路径为准因为是先裁剪后压缩

                    // 从2.3.6开始加入了原图功能，所以再使用的时候需要判断media.isOriginal()); 如果为true有可能是用户选择要上传原图则要取
                    //    media.getOriginalPath());作为上传路径，前提是你开启了.isOriginalImageControl(true);开关

                    mTXVideoJoiner = new TXVideoJoiner(this);
                    ArrayList<String> videoSourceList = new ArrayList<>();
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        for (int i = 0; i < selectList.size(); i++) {
                            videoSourceList.add(selectList.get(i).getAndroidQToPath());
                        }
                        if(isVideo){
                            //合成视频跳转页面

                            int ret = mTXVideoJoiner.setVideoPathList(videoSourceList);
                            if (ret == 0) {
                            } else if (ret == TXVideoEditConstants.ERR_UNSUPPORT_VIDEO_FORMAT) {
                                DialogUtil.showDialog(this, "视频合成失败", "本机型暂不支持此视频格式", null);
                            } else if (ret == TXVideoEditConstants.ERR_UNSUPPORT_AUDIO_FORMAT) {
                                DialogUtil.showDialog(this, "视频合成失败", "暂不支持非单双声道的视频格式", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                       /* if (mOnVideoJoinListener != null) {
                                            mOnVideoJoinListener.onJoinCanceled();
                                        }*/
                                    }
                                });
                            }
                            mTXVideoJoiner.setVideoJoinerListener(this);
                            mOutputPath = getCustomVideoOutputPath("ajiani");

                            mProgressFragmentUtil = new ProgressFragmentUtil(this,getResources().getString(R.string.video_joining));
                            mProgressFragmentUtil.showLoadingProgress(new ProgressFragmentUtil.IProgressListener() {
                                @Override
                                public void onStop() {
                                    mProgressFragmentUtil.dismissLoadingProgress();
                                    cancelJoin();
                                }
                            });



                            mTXVideoJoiner.joinVideo(TXVideoEditConstants.VIDEO_COMPRESSED_720P, mOutputPath);
                        }else{

                            //跳转到另一个页面
                            Intent intent = new Intent(this, PictureJoinActivity.class);
                            ArrayList<String> strings = new ArrayList<>();
                            for (int i = 0; i < selectList.size(); i++) {
                                strings.add(selectList.get(i).getAndroidQToPath());
                            }
                            intent.putStringArrayListExtra(TCConstants.INTENT_KEY_MULTI_PIC_LIST, strings);
                            startActivity(intent);
                            finish();
                        }



                    } else {
                        if(isVideo){
                            //合成视频跳转页面
                            for (int i = 0; i < selectList.size(); i++) {
                                videoSourceList.add(selectList.get(i).getCompressPath());
                            }
                            int ret = mTXVideoJoiner.setVideoPathList(videoSourceList);
                            if (ret == 0) {
                            } else if (ret == TXVideoEditConstants.ERR_UNSUPPORT_VIDEO_FORMAT) {
                                DialogUtil.showDialog(this, "视频合成失败", "本机型暂不支持此视频格式", null);
                            } else if (ret == TXVideoEditConstants.ERR_UNSUPPORT_AUDIO_FORMAT) {
                                DialogUtil.showDialog(this, "视频合成失败", "暂不支持非单双声道的视频格式", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                       /* if (mOnVideoJoinListener != null) {
                                            mOnVideoJoinListener.onJoinCanceled();
                                        }*/
                                    }
                                });
                            }
                            mTXVideoJoiner.setVideoJoinerListener(this);
                            mOutputPath = getCustomVideoOutputPath("ajiani");

                            mProgressFragmentUtil = new ProgressFragmentUtil(this,getResources().getString(R.string.video_joining));
                            mProgressFragmentUtil.showLoadingProgress(new ProgressFragmentUtil.IProgressListener() {
                                @Override
                                public void onStop() {
                                    mProgressFragmentUtil.dismissLoadingProgress();
                                    cancelJoin();
                                }
                            });
                            mTXVideoJoiner.joinVideo(TXVideoEditConstants.VIDEO_COMPRESSED_720P, mOutputPath);
                        }else{
                            Intent intent = new Intent(TakePhotoActivity.this, PictureJoinActivity.class);
                            ArrayList<String> strings = new ArrayList<>();
                            for (int i = 0; i < selectList.size(); i++) {
                                strings.add(selectList.get(i).getAndroidQToPath());
                            }
                            intent.putStringArrayListExtra(TCConstants.INTENT_KEY_MULTI_PIC_LIST, strings);
                            startActivity(intent);
                            finish();
                        }

                    }

                   /* adapter.setList(selectList);
                    adapter.notifyDataSetChanged();*/
                    break;
            }
        }else if(requestCode==1213&&resultCode==1212) {
            takeVideoCut.setVisibility(View.VISIBLE);
            //拿到音乐的路径
            musicInfo = new MusicInfo();
            musicInfo.path = data.getStringExtra("path");
            path = data.getStringExtra("path");
            musicInfo.name = data.getStringExtra("name");
            musicInfo.thumb = data.getStringExtra("thumb");
            musicUrl = data.getStringExtra("musicurl");
            musicID = data.getIntExtra("music", 0);
            musicInfo.position = data.getIntExtra("poss", -1);
            Log.i(TAG, "onActivityResult: "+path);
            int duration = mTXCameraRecord.setBGM(path);
            musicInfo.startTime=0;
            musicInfo.duration=duration;
            musicInfo.endTime=duration;
            mTXCameraRecord.setBGMVolume(1);
            mTXCameraRecord.setMicVolume(0);
            //mTXCameraRecord.playBGMFromTime((int) 0, );
            takeMusic.setText(musicInfo.name);

        }

    }
    /**
     * 取消视频合成
         */
    private void cancelJoin() {

            if (mTXVideoJoiner != null) {
                mTXVideoJoiner.cancel();
            }

         /*   if (mOnVideoJoinListener != null) {
                mOnVideoJoinListener.onJoinCanceled();
            }*/

    }




    @Override
    protected void onResume() {
        super.onResume();
        StatusBarUtil2.setStatusBarMode(this,false,R.color.black);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(musicInfo!=null){
            mTXCameraRecord.stopBGM();
            mTXCameraRecord.setBGM(null);
        }
        if(mTXCameraRecord!=null){
            mTXCameraRecord.getPartsManager().deleteAllParts();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(musicInfo!=null){
            mTXCameraRecord.pauseBGM();
        }
    }

    private void showPicture() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(5)// 最大图片选择数量 int
                .minSelectNum(0)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .isOpenStyleCheckNumMode(true)// 是否开启数字选择模式 类似QQ相册
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(false)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                /*           .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                           .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义*/
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
//                .compressSavePath(getPath())//压缩图片保存地址
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                .cropCompressQuality(50)// 裁剪压缩质量 默认90 int
                .openClickSound(false)// 是否开启点击声音 true or false           178919  3102484
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    private float mScaleFactor;
    private float mLastScaleFactor;

    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        int maxZoom = mTXCameraRecord.getMaxZoom();
        if (maxZoom == 0) {
            TXCLog.i(TAG, "camera not support zoom");
            return false;
        }

        float factorOffset = scaleGestureDetector.getScaleFactor() - mLastScaleFactor;

        mScaleFactor += factorOffset;
        mLastScaleFactor = scaleGestureDetector.getScaleFactor();
        if (mScaleFactor < 0) {
            mScaleFactor = 0;
        }
        if (mScaleFactor > 1) {
            mScaleFactor = 1;
        }

        int zoomValue = Math.round(mScaleFactor * maxZoom);
        mTXCameraRecord.setZoom(zoomValue);
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        mLastScaleFactor = scaleGestureDetector.getScaleFactor();
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

    }

    @Override
    public void onBeautyParamsChange(BeautySettingPannel.BeautyParams params, int key) {
        switch (key) {
            case BeautySettingPannel.BEAUTYPARAM_BEAUTY:
                mBeautyParams.mBeautyLevel = params.mBeautyLevel;
                mBeautyParams.mBeautyStyle = params.mBeautyStyle;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setBeautyDepth(mBeautyParams.mBeautyStyle, mBeautyParams.mBeautyLevel, mBeautyParams.mWhiteLevel, mBeautyParams.mRuddyLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_WHITE:
                mBeautyParams.mWhiteLevel = params.mWhiteLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setBeautyDepth(mBeautyParams.mBeautyStyle, mBeautyParams.mBeautyLevel, mBeautyParams.mWhiteLevel, mBeautyParams.mRuddyLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_FACE_LIFT:
                mBeautyParams.mFaceSlimLevel = params.mFaceSlimLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setFaceScaleLevel(params.mFaceSlimLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_BIG_EYE:
                mBeautyParams.mBigEyeLevel = params.mBigEyeLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setEyeScaleLevel(params.mBigEyeLevel);
                }
                break;             //BEAUTYPARAM_FILTER
            case BeautySettingPannel.BEAUTYPARAM_FILTER:
                mBeautyParams.mFilterBmp = params.mFilterBmp;
                mCurrentIndex = params.filterIndex;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setSpecialRatio(mBeautyPannelView.getFilterProgress(mCurrentIndex) / 10.f);
                    mTXCameraRecord.setFilter(params.mFilterBmp);
                }
                doTextAnimator();
                break;
            case BeautySettingPannel.BEAUTYPARAM_MOTION_TMPL:
                mBeautyParams.mMotionTmplPath = params.mMotionTmplPath;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setMotionTmpl(params.mMotionTmplPath);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_GREEN:
                mBeautyParams.mGreenFile = params.mGreenFile;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setGreenScreenFile(params.mGreenFile, true);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_RUDDY:
                mBeautyParams.mRuddyLevel = params.mRuddyLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setBeautyDepth(mBeautyParams.mBeautyStyle, mBeautyParams.mBeautyLevel, mBeautyParams.mWhiteLevel, mBeautyParams.mRuddyLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_FACEV:
                mBeautyParams.mFaceVLevel = params.mFaceVLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setFaceVLevel(params.mFaceVLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_FACESHORT:
                mBeautyParams.mFaceShortLevel = params.mFaceShortLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setFaceShortLevel(params.mFaceShortLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_CHINSLIME:
                mBeautyParams.mChinSlimLevel = params.mChinSlimLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setChinLevel(params.mChinSlimLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_NOSESCALE:
                mBeautyParams.mNoseScaleLevel = params.mNoseScaleLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setNoseSlimLevel(params.mNoseScaleLevel);
                }
                break;
            case BeautySettingPannel.BEAUTYPARAM_FILTER_MIX_LEVEL:
                mBeautyParams.mFilterMixLevel = params.mFilterMixLevel;
                if (mTXCameraRecord != null) {
                    mTXCameraRecord.setSpecialRatio(params.mFilterMixLevel / 10.f);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 结束录制
        //  mTXCameraRecord.stopRecord();
        if (mTXCameraRecord != null) {
            mTXCameraRecord.getPartsManager().deleteAllParts();
        }
        String outputDir = Environment.getExternalStorageDirectory() + File.separator + TCConstants.OUTPUT_DIR_NAME;
        FileUtils.deleteEveryThing(outputDir);
        return super.onKeyDown(keyCode, event);
    }


}
