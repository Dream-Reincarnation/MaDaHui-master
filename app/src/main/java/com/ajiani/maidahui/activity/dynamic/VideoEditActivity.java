package com.ajiani.maidahui.activity.dynamic;


import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DownloadUtil;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.Utils.video1.PlayState;
import com.ajiani.maidahui.Utils.video1.TCEditerUtil;
import com.ajiani.maidahui.Utils.video1.TCVideoEditerWrapper;
import com.ajiani.maidahui.activity.dynamic.txrecord.EffectActivity;
import com.ajiani.maidahui.adapter.dynamic.AddshopVpAdapter;
import com.ajiani.maidahui.adapter.dynamic.DynaFragmentAdapter;
import com.ajiani.maidahui.adapter.dynamic.music.TvEditAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.dynamic.music.MusicBean;
import com.ajiani.maidahui.fragment.dynamic.music.EditFragment;
import com.ajiani.maidahui.fragment.dynamic.music.SelectFragment;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.tencent.TCConstants;
import com.ajiani.maidahui.weight.music.DoubleHeadedDragonBar;
import com.example.txrecord.MusicInfo;
import com.example.txrecord.baseic.IEditMusicPannel;
import com.example.txrecord.ettect.PlayerManagerKit;
import com.example.txrecord.ettect.VideoEditerSDK;
import com.example.txrecord.ettect.VideoPlayLayout;
import com.example.txrecord.utils.ConfigureLoader;
import com.example.txrecord.utils.DraftEditer;
import com.example.txrecord.utils.EffectEditer;
import com.example.txrecord.utils.UGCKit;
import com.example.txrecord.weight.TCEditMusicPannel;
import com.example.txrecord.weight.TimeLineView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXLog;
import com.tencent.ugc.TXRecordCommon;
import com.tencent.ugc.TXVideoEditConstants;
import com.tencent.ugc.TXVideoEditer;
import com.tencent.ugc.TXVideoInfoReader;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class VideoEditActivity extends AppCompatActivity implements TXVideoEditer.TXVideoPreviewListener, TXVideoEditer.TXVideoGenerateListener {
    @BindView(R.id.video_play_layout)
    FrameLayout videoetView;

    @BindView(R.id.take_videomusic)
    LinearLayout takeVideomusic;

    @BindView(R.id.edit_musicname)
    TextView musicName;

    @BindView(R.id.take_change)
    ImageView takeChange;
    @BindView(R.id.tc_record_bgm_pannel)
    TCEditMusicPannel mTCEditMusicPannel;
    @BindView(R.id.take_videocover)
    LinearLayout takeVideocover;
    @BindView(R.id.take_changespeed)
    ImageView takeChangespeed;
    @BindView(R.id.take_videotailor)
    LinearLayout takeVideotailor;
    @BindView(R.id.take_videocaption)
    LinearLayout takeVideocaption;
    @BindView(R.id.video_tagslin)
    LinearLayout videoTagslin;
    @BindView(R.id.video_filterlin)
    LinearLayout videoFilterlin;
    @BindView(R.id.video_spcaillin)
    LinearLayout videoSpcaillin;
    @BindView(R.id.video_next)
    TextView videoNext;
    int startTime = 0;
    int a;
    int endTime = 0;
    private int mCurrentState = PlayState.STATE_NONE;       // 播放器当前状态
    // 短视频SDK获取到的视频信息
        private TXVideoEditer mTXVideoEditer;                   // SDK接口类
    private String mVideoOutputPath;                        // 视频输出路径
    private int mVideoResolution = -1;                      // 分辨率类型（如果是从录制过来的话才会有，这参数）
    private String mCoverImagePath;                         // 视频封面

    private long mVideoDuration;                            // 视频的总时长
    private int mCustomBitrate;
    private int mVideoFrom;

    private String mRecordProcessedPath;
    private KeyguardManager mKeyguardManager;
    private String TAG = "wxy";
    private Unbinder bind;
    private String thumbImg;
    private DraftEditer mDraftEditer;
    private int duration;
    private ArrayList<MusicBean.DataBean> recommed;
    private ArrayList<MusicBean.DataBean> collect;
    private String path;
    private int end;
    private int start;
    private String thumbth;
    private String musicName1;
    private MusicBean.DataBean dataBean;
    private DoubleHeadedDragonBar doubleHeadedDragonBar;
    private TextView musicEnd;
    private String musicPath;
    private TextView musicStart;
    private SeekBar micSeek;
    private SeekBar seekBar;
    private String musicUrl;
    private TextView musicName2;
    private TvEditAdapter tvEditAdapter;
    private boolean isUserMusic;
    private int musicId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoedit);
        bind = ButterKnife.bind(this);
        ConfigureLoader.getInstance().loadConfigToDraft();
        initView();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        mDraftEditer.setBgmPath(null);
        mTXVideoEditer.setBGM(null);
    }

    protected void initData() {

    }


    protected void initView() {
        StatusBarUtil2.setStatusBarMode(this, false, R.color.blacks);
      /*  TXVideoEditer editer = VideoEditerSDK.getInstance().getEditer();
        if (editer == null) {
            VideoEditerSDK.getInstance().initSDK(this);
        }*/

        mDraftEditer = DraftEditer.getInstance();
        mRecordProcessedPath = getIntent().getStringExtra(TCConstants.VIDEO_EDITER_PATH);
        mTXVideoEditer = VideoEditerSDK.getInstance().getEditer();
        if (mTXVideoEditer == null) {
            VideoEditerSDK.getInstance().initSDK(this);
            mTXVideoEditer = VideoEditerSDK.getInstance().getEditer();
        }
        mTXVideoEditer.setTXVideoPreviewListener(this);
        TXVideoEditConstants.TXVideoInfo info4 = TXVideoInfoReader.getInstance().getVideoFileInfo(mRecordProcessedPath);

        VideoEditerSDK.getInstance().setVideoPath(mRecordProcessedPath);

        /*mTXVideoEditer.setBGM(TakePhotoActivity.musicInfo.path);
        mTXVideoEditer.startPlayFromTime(TakePhotoActivity.musicInfo.startTime,TakePhotoActivity.musicInfo.endTime);*/


     /*   TXVideoEditConstants.TXPreviewParam param = new TXVideoEditConstants.TXPreviewParam();
        param.videoView = videoetView;
        param.renderMode = TXVideoEditConstants.PREVIEW_RENDER_MODE_FILL_SCREEN;

        mTXVideoEditer.initWithPreview(param);*/

        VideoEditerSDK.getInstance().resetDuration();

        if (mTXVideoEditer == null || VideoEditerSDK.getInstance().getTXVideoInfo() == null) {
            finish();
            return;
        }
        long cutterStartTime = VideoEditerSDK.getInstance().getCutterStartTime();
        long cutterEndTime = VideoEditerSDK.getInstance().getCutterEndTime();
        if (cutterEndTime - cutterStartTime != 0) {
            mVideoDuration = cutterEndTime - cutterStartTime;
            endTime = (int) mVideoDuration;
        } else {
            mVideoDuration = VideoEditerSDK.getInstance().getTXVideoInfo().duration;
            endTime = (int) mVideoDuration;
        }

        mVideoResolution = getIntent().getIntExtra(TCConstants.VIDEO_RECORD_RESOLUTION, -1);
        mCustomBitrate = getIntent().getIntExtra(TCConstants.RECORD_CONFIG_BITE_RATE, 0);

        mVideoFrom = getIntent().getIntExtra(TCConstants.VIDEO_RECORD_TYPE, TCConstants.VIDEO_RECORD_TYPE_UGC_RECORD);
        // 录制经过预处理的视频路径，在编辑后需要删掉录制源文件
        thumbImg = getIntent().getStringExtra("thumb");
        mKeyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        TXVideoEditConstants.TXVideoInfo info = VideoEditerSDK.getInstance().getTXVideoInfo();
        if (info == null) {
            info = TXVideoInfoReader.getInstance().getVideoFileInfo(mRecordProcessedPath);
            VideoEditerSDK.getInstance().setTXVideoInfo(info);
            VideoEditerSDK.getInstance().setCutterStartTime(0, info.duration);
        }
        VideoEditerSDK.getInstance().clearThumbnails();
        VideoEditerSDK.getInstance().initThumbnailList(new TXVideoEditer.TXThumbnailListener() {
            @Override
            public void onThumbnail(final int index, long timeMs, final Bitmap bitmap) {

                VideoEditerSDK.getInstance().addThumbnailBitmap(timeMs, bitmap);
            }
        }, 1000);


    }


    /**
     * ==========================================SDK播放器生命周期==========================================
     */
    private void initPlayerLayout() {
        TXVideoEditConstants.TXPreviewParam param = new TXVideoEditConstants.TXPreviewParam();
        param.videoView = videoetView;
        param.renderMode = TXVideoEditConstants.PREVIEW_RENDER_MODE_FILL_SCREEN;
        mTXVideoEditer.initWithPreview(param);
    }

    /**
     * 给子Fragment调用 （子Fragment不在意Activity中对于播放器的生命周期）
     */
    public void restartPlay() {
        stopPlay();

    }

    public void startPlay(long startTime, long endTime) {
        mTXVideoEditer.startPlayFromTime(startTime, endTime);
        mCurrentState = PlayState.STATE_PLAY;
    }

    public void stopPlay() {
        if (mCurrentState == PlayState.STATE_RESUME || mCurrentState == PlayState.STATE_PLAY ||
                mCurrentState == PlayState.STATE_STOP || mCurrentState == PlayState.STATE_PAUSE) {

            mCurrentState = PlayState.STATE_STOP;
        }
    }
    public  String getMimeType(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        String mime = "text/plain";
        if (filePath != null) {
            try {
                mmr.setDataSource(filePath);
                mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            } catch (IllegalStateException e) {
                return mime;
            } catch (IllegalArgumentException e) {
                return mime;
            } catch (RuntimeException e) {
                return mime;
            }
        }
        return mime;
    }
    /**
     * ==========================================activity生命周期==========================================
     */
    @Override
    protected void onResume() {
        super.onResume();
        a++;
        if (!mKeyguardManager.inKeyguardRestrictedInputMode()) {

            TXVideoEditConstants.TXPreviewParam param = new TXVideoEditConstants.TXPreviewParam();
            param.videoView = videoetView;
            param.renderMode = TXVideoEditConstants.PREVIEW_RENDER_MODE_FILL_EDGE;
            mTXVideoEditer.initWithPreview(param);
            mVideoDuration = VideoEditerSDK.getInstance().getTXVideoInfo().duration;


            start = (int) getIntent().getLongExtra("starttime", 0);
            end = (int) getIntent().getLongExtra("endtime", 0);
            path = getIntent().getStringExtra("path");
            thumbth = getIntent().getStringExtra("thumbth");
            musicName1 = getIntent().getStringExtra("musicname");
            musicUrl = getIntent().getStringExtra("musicurl");
            musicId = getIntent().getIntExtra("musicid",0);



            //initPlayerLayout();

            mDraftEditer.setVideoVolume(0.5f);
            if (path != null) {

                String mimeType = getMimeType(path);

                mDraftEditer.setBgmEndTime(end);
                mDraftEditer.setBgmPath(path);
                mDraftEditer.setBgmDuration(getDuration(path));
                mDraftEditer.setBgmStartTime(start);
                mDraftEditer.setBgmName(musicName1);
                mDraftEditer.setBgmVolume(0.5f);
                mDraftEditer.setVideoVolume(0);

                dataBean = new MusicBean.DataBean();
                dataBean.setThumb(thumbth);
                dataBean.setName(musicName1);
                dataBean.setUrl(musicUrl);
                dataBean.setPlay(true);
                mTXVideoEditer.setVideoVolume(0);
                if (a == 1) {

                    mTXVideoEditer.setBGM(path);
                    mTXVideoEditer.setBGMStartTime(start, end);

                    mTXVideoEditer.setBGMVolume(0.5f);
                }

            }
            mTXVideoEditer.setRenderRotation(0);
            mTXVideoEditer.startPlayFromTime(0, mVideoDuration);
        }


    }


    @Override
    protected void onPause() {
        super.onPause();
        mTXVideoEditer.stopPlay();
        //stopPlay();
        //   PlayerManagerKit.getInstance().stopPlay();
        // 若当前处于生成状态，离开当前activity，直接停止生成
        if (mCurrentState == PlayState.STATE_GENERATE) {
            stopGenerate();
        }
    }

    private void stopGenerate() {
        if (mCurrentState == PlayState.STATE_GENERATE) {
            mCurrentState = PlayState.STATE_NONE;
            if (mTXVideoEditer != null) {
                mTXVideoEditer.cancel();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == 345) {
            //重新加载播放
            if (mTXVideoEditer != null) {
                startTime = data.getIntExtra("starttime", 0);
                endTime = data.getIntExtra("endtime", (int) mVideoDuration);
                mTXVideoEditer.setVideoPath(mRecordProcessedPath);
            }
        } else if (requestCode == 1213 && resultCode == 1212) {
            MusicInfo musicInfo = new MusicInfo();
            musicInfo.path = data.getStringExtra("path");
            musicInfo.name = data.getStringExtra("name");
            musicName.setText(data.getStringExtra("name"));
            musicPath = data.getStringExtra("path");
            String musicurl = data.getStringExtra("musicurl");
            String thumb = data.getStringExtra("thumb");


            if(dataBean!=null) {

                recommed.remove(0);

            }
            dataBean = new MusicBean.DataBean();
            dataBean.setUrl(musicurl);
            dataBean.setPlay(true);
            dataBean.setName(musicInfo.name);
            dataBean.setThumb(thumb);
            if(tvEditAdapter!=null){
                recommed.add(0,dataBean);
                tvEditAdapter.mList.clear();
                tvEditAdapter.addAll(recommed,true);
            }
            musicInfo.position = data.getIntExtra("poss", -1);
            if (TextUtils.isEmpty(musicInfo.path)) {
                finish();
                return;
            }

            mTXVideoEditer.setBGM(musicInfo.path);

            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(musicInfo.path);
                mediaPlayer.prepare();
                duration = mediaPlayer.getDuration();
                musicInfo.duration = mediaPlayer.getDuration();
                mediaPlayer.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mTXVideoEditer.setBGMStartTime(0, musicInfo.duration);
            mTXVideoEditer.setBGMVolume(0.5f);
            mTXVideoEditer.setVideoVolume(0.5f);


            ConfigureLoader.getInstance().saveConfigFromDraft();
            if(path==null){
                musicInfo.videoVolume = 0.5f;
            }else{
                musicInfo.videoVolume = 0;
            }

            musicInfo.bgmVolume = 0.5f;
            musicInfo.startTime = 0;
            musicInfo.endTime = musicInfo.duration;
            DraftEditer.getInstance().saveRecordMusicInfo(musicInfo);

        }
    }

    @OnClick({R.id.video_tagslin, R.id.dyna_musiclin, R.id.video_backs, R.id.take_videocut, R.id.take_videocover, R.id.take_videomusic, R.id.video_filterlin, R.id.take_videotailor, R.id.video_spcaillin, R.id.video_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.take_videocover:
                //跳转封面
                //JumpUtils.gotoActivity(this, EffectActivity.class);
                break;

            case R.id.dyna_musiclin:
                //弹窗配乐
//                 showPop();
                showPops();
                break;

            case R.id.take_videocut:
                //
                showPop();
                break;
            case R.id.video_tagslin:

                break;

            case R.id.video_filterlin:
                //跳转滤镜页面

                JumpUtils.gotoActivity(this, EffectActivity.class);


                break;
            case R.id.video_spcaillin:
                //跳转 添加商品 页面

                break;
            case R.id.video_backs:
                mTXVideoEditer.release();
                mDraftEditer.clear();
                finish();
                break;
            case R.id.take_videomusic:
                //跳转音乐选择
                JumpUtils.gotoActivity(this, MusicActivity.class, new Bundle(), 1213);
                break;

            case R.id.video_next:
                //合成视频

                mTXVideoEditer.generateVideo(TXVideoEditConstants.VIDEO_COMPRESSED_720P, mRecordProcessedPath);
                List<Bitmap> thumbnailList = VideoEditerSDK.getInstance().getAllThumbnails();
                Bitmap bitmap = thumbnailList.get(4);
                //存入file
                if(thumbImg==null){
                    thumbImg = getCustomVideoOutputPath("ajiani");
                    File file = new File(thumbImg);
                    FileUtils.compressImage(bitmap,file);
                }


                //跳转时回聘发布页面
                Bundle bundle = new Bundle();
                bundle.putString("file", mRecordProcessedPath);
                bundle.putString("thumb", thumbImg);
                bundle.putInt("music", musicId);
                JumpUtils.gotoActivity(this, VideoReleaaseActivity.class, bundle);
                break;
            case R.id.take_videotailor:
                //裁剪 ,跳转到裁剪页面
                /*Intent intent = new Intent(this, VideoCutActivity.class);
                intent.putExtra(TCConstants.VIDEO_EDITER_PATH,mRecordProcessedPath);
                intent.putExtra(TCConstants.VIDEO_RECORD_TYPE,TCConstants.VIDEO_RECORD_TYPE_UGC_RECORD);
                startActivityForResult(intent,123);*/
                break;
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
            tempOutputPath = outputDir + File.separator + "TXUGC_" + time + ".jpg";
        } else {
            tempOutputPath = outputDir + File.separator + "TXUGC_" + fileNamePrefix + time + ".jpg";
        }

        return tempOutputPath;
    }



    boolean isfirst = false;

    private void showPops() {

        recommed = new ArrayList<>();
        collect = new ArrayList<>();
        //进项请求
        PopupWindow popupWindow = new PopupWindow(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.addmusic_items, null);
        //绑定适配器
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        TabLayout tabLayout = inflate.findViewById(R.id.addmusic_tab);
        RecyclerView recyclerView = inflate.findViewById(R.id.addmusic_rel);
        TimeLineView timeLineView = inflate.findViewById(R.id.timeline_view);
        TextView musicPlay = inflate.findViewById(R.id.edit_music_play);
        timeLineView.initVideoProgressLayout();
        init(inflate);
        //时间轴监听
        timeLineView.setOnClickLinstener(new TimeLineView.onClickLinstener() {
            @Override
            public void onClick(int time) {

                /*mTXVideoEditer.setBGM(mDraftEditer.getBgmPath());
                mTXVideoEditer.setBGMAtVideoTime(time);
                mTXVideoEditer.startPlayFromTime(0, mVideoDuration);*/
                 time= time/1000;
                musicPlay.setText("视频从"+time+"s开始播放音乐");


            }
        });
        FrameLayout frameLayout = inflate.findViewById(R.id.addmusic_farm);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        LinearLayout selLin = inflate.findViewById(R.id.addmusic_sel);
        LinearLayout editLin = inflate.findViewById(R.id.addmusic_edit);
        inflate.findViewById(R.id.addmusic_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.addmusic_cancles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        TextView selTv = inflate.findViewById(R.id.addmusic_seltv);
        TextView editTv = inflate.findViewById(R.id.addmusic_seled);
        ArrayList<MusicBean.DataBean> dataBeans = new ArrayList<>();
        tvEditAdapter = new TvEditAdapter(dataBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(tvEditAdapter);
        request(tvEditAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    isfirst = false;
                    if (recommed.size() > 0) {
                        tvEditAdapter.mList.clear();
                        tvEditAdapter.addAll(recommed, true);
                        tvEditAdapter.notifyDataSetChanged();
                    }
                } else {
                    isfirst = true;
                    if (collect.size() > 0) {
                        tvEditAdapter.mList.clear();
                        tvEditAdapter.addAll(collect, false);
                        tvEditAdapter.notifyDataSetChanged();
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
        editLin.setVisibility(View.GONE);
        selLin.setVisibility(View.VISIBLE);
        selTv.setTextColor(getResources().getColor(R.color.white));
        editTv.setTextColor(getResources().getColor(R.color.mine_unselect));
        selTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLin.setVisibility(View.GONE);
                selLin.setVisibility(View.VISIBLE);
                selTv.setTextColor(getResources().getColor(R.color.white));
                editTv.setTextColor(getResources().getColor(R.color.mine_unselect));
            }
        });

            editTv.setOnClickListener(new View.OnClickListener() {

                private int duration;

                @Override
                public void onClick(View v) {
                    if (mDraftEditer.getBgmPath() != null) {
                        editLin.setVisibility(View.VISIBLE);
                        selLin.setVisibility(View.GONE);
                        selTv.setTextColor(getResources().getColor(R.color.mine_unselect));
                        editTv.setTextColor(getResources().getColor(R.color.white));
                        micSeek.setProgress((int) (mDraftEditer.getBgmVolume() * 100));
                        seekBar.setProgress((int) (mDraftEditer.getVideoVolume() * 100));
                        if (mDraftEditer.getBgmName() != null && !
                                mDraftEditer.getBgmName().equals("")) {
                            musicName2.setText(mDraftEditer.getBgmName());
                        }

                        if (musicPath != null) {
                            try {
                                MediaPlayer mediaPlayer = new MediaPlayer();
                                mediaPlayer.setDataSource(musicPath);
                                mediaPlayer.prepare();
                                duration = mediaPlayer.getDuration();
                                mediaPlayer.release();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            doubleHeadedDragonBar.setMinValue((int) mDraftEditer.getBgmStartTime());
                            doubleHeadedDragonBar.setMaxValue((int) mDraftEditer.getBgmDuration(), (int) mDraftEditer.getBgmEndTime());

                            musicEnd.setText(timeParse((int) mDraftEditer.getBgmEndTime()));
                            musicStart.setText(timeParse((int) mDraftEditer.getBgmStartTime()));
                        }

                        //给进度条赋值

                    }
                }
            });



        tvEditAdapter.setOnClickLinstener(new TvEditAdapter.onClickLinstener() {
            @Override
            public void onClick(int position, String path) {
                mTXVideoEditer.stopPlay();

                //                    mTXVideoEditer.setBGMStartTime(0,VideoEditerSDK.getInstance().getTXVideoInfo().duration);
                if (tvEditAdapter.mList.get(position).isPlay())
                    DownloadUtil.get().download(tvEditAdapter.mList.get(position).getUrl(), FileUtils.getCache(VideoEditActivity.this), "music_" + tvEditAdapter.mList.get(position).getName() + "_" + tvEditAdapter.mList.get(position).getMusic_id() + ".mp3", new DownloadUtil.DownloadListener() {

                        @Override
                        public void onDownloadSuccess(String path) {
                            //开始使用音乐
                            isUserMusic = true;
                            musicId = tvEditAdapter.mList.get(position).getMusic_id();

                            if (path != null) {
                                mDraftEditer.setVideoVolume(0);
                            } else {
                                mDraftEditer.setVideoVolume(0.5f);
                            }
                            musicPath = path;
                            // mTXVideoEditer.startPlayFromTime(0, VideoEditerSDK.getInstance().getTXVideoInfo().duration);
                            mTXVideoEditer.setBGM(path);
                            mTXVideoEditer.setBGMVolume(0.5f);
                            // mTXVideoEditer.setVideoVolume(0.5f);
                            int duration = getDuration(path);
                            mDraftEditer.setBgmPath(path);
                            mDraftEditer.setBgmDuration(duration);
                            mDraftEditer.setBgmStartTime(0);
                            mDraftEditer.setBgmEndTime(duration);

                            mTXVideoEditer.setBGMStartTime(0, duration);
                            mTXVideoEditer.startPlayFromTime(0, mVideoDuration);
                        }

                        @Override
                        public void onDownloading(int progress) {

                        }

                        @Override
                        public void onDownloadFailed() {

                        }
                    });
                else {
                    //取消 使用音乐
                    isUserMusic = false;
                    musicId=0;
                    mDraftEditer.setBgmPath(null);
                    mTXVideoEditer.setBGM(null);
                    mTXVideoEditer.startPlayFromTime(0, mVideoDuration);
//                    mTXVideoEditer.startPlayFromTime(0,VideoEditerSDK.getInstance().getTXVideoInfo().duration);
                }

                if (isfirst) {
                    //点击收藏
                    for (int i = 0; i < recommed.size(); i++) {
                        recommed.get(i).setPlay(false);
                    }
                } else {
                    //点击推荐
                    for (int i = 0; i < collect.size(); i++) {
                        collect.get(i).setPlay(false);
                    }
                }
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("推荐"), true);
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));

        popupWindow.setContentView(inflate);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(videoetView, Gravity.BOTTOM, 0, 0);

    }

    public int getDuration(String path){
        int duration = 0;
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            duration = mediaPlayer.getDuration();
            mediaPlayer.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return duration;


    }


    private void init(View inflate) {
        musicName2 = inflate.findViewById(R.id.edit_music_name);


        TextView editMusic = inflate.findViewById(R.id.edit_music_music);
        TextView editVoide = inflate.findViewById(R.id.edit_music_video);
        seekBar = inflate.findViewById(R.id.musicvlome);
        micSeek = inflate.findViewById(R.id.micvolume);

        if(path!=null){
            seekBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    return true;
                }
            });

        }else {
            seekBar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
        }

        musicStart = inflate.findViewById(R.id.edit_music_start);
        musicEnd = inflate.findViewById(R.id.edit_music_end);

        seekBar.setProgress((int) (mDraftEditer.getVideoVolume()*100));
        editVoide.setText(mDraftEditer.getVideoVolume()*100+"");
        setWidths(mDraftEditer.getVideoVolume(),editVoide);
        /*float textWidth = editMusic.getWidth();

        //获取seekbar最左端的x位置
        float left = seekBar.getLeft();

        //进度条的刻度值
        float max =Math.abs(seekBar.getMax());

        //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
        float thumb = dip2px(VideoEditActivity.this,15);

        //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
        float average = (((float) seekBar.getWidth())-2*thumb)/max;

        //int to float
        float currentProgress =  mDraftEditer.getVideoVolume()*100;

        //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
        float pox = left - textWidth/2 +thumb + average * currentProgress;
        editMusic.setX(pox);*/

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mDraftEditer.setVideoVolume(progress/100);
                editVoide.setText(progress+"");
                //获取文本宽度
                float textWidth = editVoide.getWidth();

                //获取seekbar最左端的x位置
                float left = seekBar.getLeft();

                //进度条的刻度值
                float max =Math.abs(seekBar.getMax());

                //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
                float thumb = dip2px(VideoEditActivity.this,15);

                //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
                float average = (((float) seekBar.getWidth())-2*thumb)/max;

                //int to float
                float currentProgress = progress;

                //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
                float pox = left - textWidth/2 +thumb + average * currentProgress;
                editVoide.setX(pox);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        micSeek.setProgress((int) (mDraftEditer.getBgmVolume()*100));

        editMusic.setText(mDraftEditer.getBgmVolume()*100+"");
        setWidths(mDraftEditer.getBgmVolume(),editMusic);

        micSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDraftEditer.setBgmVolume(progress/100);
                editMusic.setText(progress+"");
                //获取文本宽度
                float textWidth = editMusic.getWidth();

                //获取seekbar最左端的x位置
                float left = seekBar.getLeft();

                //进度条的刻度值
                float max =Math.abs(seekBar.getMax());

                //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
                float thumb = dip2px(VideoEditActivity.this,15);

                //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
                float average = (((float) seekBar.getWidth())-2*thumb)/max;

                //int to float
                float currentProgress = progress;

                //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
                float pox = left - textWidth/2 +thumb + average * currentProgress;
                editMusic.setX(pox);
                float BGMvoice = (float) (progress / 100.0);
                mDraftEditer.setBgmVolume(BGMvoice);

                mTXVideoEditer.setBGMVolume(BGMvoice);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        doubleHeadedDragonBar = inflate.findViewById(R.id.dyna_seek);
        if(musicPath!=null){
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(musicPath);
                mediaPlayer.prepare();
                duration = mediaPlayer.getDuration();
                mediaPlayer.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
            doubleHeadedDragonBar.setMinValue(0);
            doubleHeadedDragonBar.setMaxValue(duration, (int) mDraftEditer.getBgmEndTime());
            musicEnd.setText(timeParse(duration));
            musicStart.setText(timeParse(0));
        }
        doubleHeadedDragonBar.setCallBack(new DoubleHeadedDragonBar.DhdBarCallBack() {
            @Override
            public void getValue(int strat, int end) {
               /* mDraftEditer.setBgmStartTime(strat);
                mDraftEditer.setBgmEndTime(end);
*/
                // bgm 播放时间区间设置
                mTXVideoEditer.setBGMStartTime(strat, end);
                //   seekTime.setText("当前从" + timeParse(strat) + "开始");
                        musicStart.setText(timeParse(strat));
                        musicEnd.setText(timeParse(end));
                        mDraftEditer.setBgmStartTime(strat);
                        mDraftEditer.setBgmEndTime(end);

            }
        });


//        seekTime.setText("当前从"++"开始");




    }

    private void setWidths(float bgmVolume, TextView editMusic) {
        float textWidth = editMusic.getWidth();

        //获取seekbar最左端的x位置
        float left = seekBar.getLeft();

        //进度条的刻度值
        float max =Math.abs(seekBar.getMax());

        //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
        float thumb = dip2px(VideoEditActivity.this,15);

        //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
        float average = (((float) seekBar.getWidth())-2*thumb)/max;

        //int to float
        float currentProgress = bgmVolume*100;

        //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
        float pox = left - textWidth/2 +thumb + average * currentProgress;
        editMusic.setX(pox);
    }

    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    private void request(TvEditAdapter adapter) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", "1");
        hashMap.put("is_recommend", "1");
        HashMap<String, String> map = Params.setParams2();
        map.putAll(hashMap);
        HashMap<String, String> sign = Params.getSign(map);
        HttpManager.instance().getServer(DynamicServer.class).searchMusic(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        MusicBean musicBean = new Gson().fromJson(s, MusicBean.class);
                        List<MusicBean.DataBean> data = musicBean.getData();
                        recommed.clear();

                        recommed.addAll(data);
                        if (dataBean != null) {
                            recommed.add(0, dataBean);
                        }
                        adapter.mList.clear();
                        adapter.addAll(recommed, true);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        HashMap<String, String> hashMap1 = new HashMap<>();

        hashMap1.put("page", "1");
        HashMap<String, String> map1 = Params.setParams2();
        map1.putAll(hashMap1);
        HashMap<String, String> sign1 = Params.getSign(map1);

        HttpManager.instance().getServer(DynamicServer.class).getCollectionMusic(sign1)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "onNext: " + s);
                        MusicBean musicBean = new Gson().fromJson(s, MusicBean.class);
                        List<MusicBean.DataBean> data = musicBean.getData();
                        collect.clear();
                        collect.addAll(data);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void showPop() {


        PopupWindow popupWindow = new PopupWindow(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.addmusic_item, videoetView, false);
        //绑定适配器
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(inflate);

        SeekBar micSeek = inflate.findViewById(R.id.micvolume);
        SeekBar musicSeek = inflate.findViewById(R.id.musicvlome);
        TextView seekTime = inflate.findViewById(R.id.seek_time);
        DoubleHeadedDragonBar doubleHeadedDragonBar = inflate.findViewById(R.id.dyna_seek);
        micSeek.setProgress((int) (mDraftEditer.getVideoVolume() * 100));
        musicSeek.setProgress((int) (mDraftEditer.getBgmVolume() * 100));
        doubleHeadedDragonBar.setMinValue((int) mDraftEditer.getBgmStartTime());
       // doubleHeadedDragonBar.setMaxValue(mDraftEditer,(int) mDraftEditer.getBgmEndTime());
        seekTime.setText("当前从" + timeParse(mDraftEditer.getBgmStartTime()) + "开始");
        inflate.findViewById(R.id.dyna_take_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消所保存的
                ConfigureLoader.getInstance().loadConfigToDraft();
                EffectEditer effectEditer = EffectEditer.getInstance();
                long bgmEndTime = effectEditer.getBgmEndTime();
                long bgmStartTime = effectEditer.getBgmStartTime();
                float videoVolume = effectEditer.getVideoVolume();
                float bgmVolume = effectEditer.getBgmVolume();
                Log.i(TAG, "onClick: " + bgmEndTime + "===" + bgmStartTime + "=====" + videoVolume);
                mTXVideoEditer.setBGMVolume(bgmVolume);
                mTXVideoEditer.setVideoVolume(videoVolume);
                mTXVideoEditer.setBGMStartTime(bgmStartTime, bgmEndTime);
                popupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.dyna_take_true).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfigureLoader.getInstance().saveConfigFromDraft();

                //取消所保存的
                popupWindow.dismiss();
            }
        });

        inflate.findViewById(R.id.seek_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除音乐
                mDraftEditer.setBgmPath(null);
                mTXVideoEditer.setBGM(null);

                musicName.setText("选择音乐");

                //  musicName.setMarqueeRepeatLimit(-1);

            }
        });


        //doubleHeadedDragonBar.setMaxValue(duration);

//        seekTime.setText("当前从"++"开始");

        doubleHeadedDragonBar.setCallBack(new DoubleHeadedDragonBar.DhdBarCallBack() {
            @Override
            public void getValue(int strat, int end) {
                mDraftEditer.setBgmStartTime(strat);
                mDraftEditer.setBgmEndTime(end);

                // bgm 播放时间区间设置
                mTXVideoEditer.setBGMStartTime(strat, end);
                seekTime.setText("当前从" + timeParse(strat) + "开始");


            }
        });

        micSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float voice = (float) (progress / 100.0);
                mDraftEditer.setVideoVolume(voice);

//                TXVideoEditer editer = VideoEditerSDK.getInstance().getEditer();
                mTXVideoEditer.setVideoVolume(voice);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        musicSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float BGMvoice = (float) (progress / 100.0);

                mDraftEditer.setBgmVolume(BGMvoice);

                mTXVideoEditer.setBGMVolume(BGMvoice);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(videoetView, Gravity.BOTTOM, 0, 0);
    }


    public String timeParse(long duration) {
        String time = "";
        long minute = duration / 60000;
        long seconds = duration % 60000;
        long second = Math.round((float) seconds / 1000);
        if (minute < 10) {
            time += "0";
        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;
    }


  /*  @Override// 预览进度回调
    public void onPreviewProgressWrapper(int time) {

    }

    @Override// 预览完成回调
    public void onPreviewFinishedWrapper() {
        stopPlay();
        startPlay(0, mVideoDuration);
    }*/


    private void startGenerateVideo() {
        stopPlay(); // 停止播放

        // 处于生成状态
        mCurrentState = PlayState.STATE_GENERATE;
        // 生成视频输出路径
        mVideoOutputPath = TCEditerUtil.generateVideoPath();

        //生成是的进度条

     /*   if (mWorkLoadingProgress == null) {
            initWorkLoadingProgress();
        }
        mWorkLoadingProgress.setProgress(0);
        mWorkLoadingProgress.setCancelable(false);
        mWorkLoadingProgress.show(getSupportFragmentManager(), "progress_dialog");*/

        // 添加片尾水印
        // addTailWaterMark();

        mTXVideoEditer.setCutFromTime(0, mVideoDuration);
        mTXVideoEditer.setVideoGenerateListener(this);

        if (mVideoResolution == -1) {// 默认情况下都将输出720的视频
            if (mCustomBitrate != 0) { // 是否自定义码率
                mTXVideoEditer.setVideoBitrate(mCustomBitrate);
            }
            mTXVideoEditer.generateVideo(TXVideoEditConstants.VIDEO_COMPRESSED_720P, mVideoOutputPath);
        } else if (mVideoResolution == TXRecordCommon.VIDEO_RESOLUTION_360_640) {
            mTXVideoEditer.generateVideo(TXVideoEditConstants.VIDEO_COMPRESSED_360P, mVideoOutputPath);
        } else if (mVideoResolution == TXRecordCommon.VIDEO_RESOLUTION_540_960) {
            mTXVideoEditer.generateVideo(TXVideoEditConstants.VIDEO_COMPRESSED_540P, mVideoOutputPath);
        } else if (mVideoResolution == TXRecordCommon.VIDEO_RESOLUTION_720_1280) {
            mTXVideoEditer.generateVideo(TXVideoEditConstants.VIDEO_COMPRESSED_720P, mVideoOutputPath);
        }
    }


    @Override // 生成进度回调
    public void onGenerateProgress(final float progress) {
        //  mWorkLoadingProgress.setProgress((int) (progress * 100));
    }

    @Override // 生成完成
    public void onGenerateComplete(final TXVideoEditConstants.TXGenerateResult result) {
        if (result.retCode == TXVideoEditConstants.GENERATE_RESULT_OK) {
            // 生成成功
            //createThumbFile(result);
        } else {
            // Toast.makeText(TCVideoEditerActivity.this, result.descMsg, Toast.LENGTH_SHORT).show();
        }
        String desc = null;
        switch (result.retCode) {
            case TXVideoEditConstants.GENERATE_RESULT_OK:
                desc = "视频编辑成功";
                break;
            case TXVideoEditConstants.GENERATE_RESULT_FAILED:
                desc = "视频编辑失败";
                break;
            case TXVideoEditConstants.GENERATE_RESULT_LICENCE_VERIFICATION_FAILED:
                desc = "licence校验失败";
                break;
        }

        mCurrentState = PlayState.STATE_NONE;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onPreviewProgress(int i) {

    }

    @Override
    public void onPreviewFinished() {
        startPlay(startTime, VideoEditerSDK.getInstance().getCutterEndTime());
    }
}
