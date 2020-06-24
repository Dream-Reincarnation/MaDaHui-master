package com.ajiani.maidahui.activity.dynamic.txrecord;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.BaseRecyclerAdapter;
import com.ajiani.maidahui.adapter.TCStaticFilterViewInfoManager;
import com.ajiani.maidahui.adapter.dynamic.music.StaticFilterAdapter;
import com.ajiani.maidahui.base.SimpleActivity;
import com.example.txrecord.baseic.PlayState;
import com.example.txrecord.ettect.PlayerManagerKit;
import com.example.txrecord.ettect.VideoEditerSDK;
import com.example.txrecord.ettect.VideoPlayLayout;
import com.example.txrecord.utils.ConfigureLoader;
import com.example.txrecord.weight.TimeLineView;
import com.example.txrecord.weight.times.PlayControlLayout;
import com.example.txrecord.weight.times.TimeLineViews;
import com.tencent.ugc.TXVideoEditConstants;
import com.tencent.ugc.TXVideoEditer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EffectActivity extends SimpleActivity implements BaseRecyclerAdapter.OnItemClickListener {
    @BindView(R.id.video_play_layout)
    VideoPlayLayout videoPlayLayout;
    @BindView(R.id.play_control_layout)
    PlayControlLayout playControlLayout;
    @BindView(R.id.timeline_view)
    TimeLineViews timelineView;
    @BindView(R.id.video_bottom_layout)
    RelativeLayout videoBottomLayout;
    @BindView(R.id.dyna_take_back)
    FrameLayout dynaTakeBack;
    @BindView(R.id.dyna_take_true)
    FrameLayout dynaTakeTrue;
    @BindView(R.id.paster_rv_list)
    RecyclerView pasterRvList;
    private static final int[] FILTER_ARR = {
            R.drawable.filter_biaozhun, R.drawable.filter_yinghong,
            R.drawable.filter_yunshang, R.drawable.filter_chunzhen,
            R.drawable.filter_bailan, R.drawable.filter_yuanqi,
            R.drawable.filter_chaotuo, R.drawable.filter_xiangfen,

            R.drawable.filter_langman, R.drawable.filter_qingxin,
            R.drawable.filter_weimei, R.drawable.filter_fennen,
            R.drawable.filter_huaijiu, R.drawable.filter_landiao,
            R.drawable.filter_qingliang, R.drawable.filter_rixi};

    private List<Integer> mFilterList;
    private List<String> mFilerNameList;
    private RecyclerView mRvFilter;
    private StaticFilterAdapter mAdapter;
    private int mCurrentPosition = 0;

    //定制化Icon
    private int originIcon = R.drawable.orginal;
    private int normalIcon = R.drawable.biaozhun;
    private int yinghongIcon = R.drawable.yinghong;
    private int yunchangIcon = R.drawable.yunshang;
    private int chunzhenIcon = R.drawable.chunzhen;
    private int bailanIcon = R.drawable.bailan;
    private int yuanqiIcon = R.drawable.yuanqi;
    private int chaotuoIcon = R.drawable.chaotuo;
    private int xiangfengIcon = R.drawable.xiangfen;
    private int langmanIcon = R.drawable.langman;
    private int qingxinIcon = R.drawable.qingxin;
    private int weimeiIcon = R.drawable.weimei;
    private int fennenIcon = R.drawable.fennen;
    private int huaijiuIcon = R.drawable.huaijiu;
    private int landiaoIcon = R.drawable.landiao;
    private int qingliangIcon = R.drawable.qingliang;
    private int rixiIcon = R.drawable.rixi;


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


        // 加载草稿配置
        ConfigureLoader.getInstance().loadConfigToDraft();

        preivewVideo();

        mFilterList = new ArrayList<Integer>();
        mFilterList.add(originIcon);
        mFilterList.add(normalIcon);
        mFilterList.add(yinghongIcon);
        mFilterList.add(yunchangIcon);
        mFilterList.add(chunzhenIcon);
        mFilterList.add(bailanIcon);
        mFilterList.add(yuanqiIcon);
        mFilterList.add(chaotuoIcon);
        mFilterList.add(xiangfengIcon);
        mFilterList.add(langmanIcon);
        mFilterList.add(qingxinIcon);
        mFilterList.add(weimeiIcon);
        mFilterList.add(fennenIcon);
        mFilterList.add(huaijiuIcon);
        mFilterList.add(landiaoIcon);
        mFilterList.add(qingliangIcon);
        mFilterList.add(rixiIcon);


        mFilerNameList = new ArrayList<>();
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_original));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_standard));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_cheery));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_cloud));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_pure));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_orchid));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_vitality));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_super));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_fragrance));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_romantic));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_fresh));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_beautiful));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_pink));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_reminiscence));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_blues));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_cool));
        mFilerNameList.add(getResources().getString(R.string.tc_static_filter_fragment_Japanese));

        mRvFilter = (RecyclerView)findViewById(R.id.paster_rv_list);
        mRvFilter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new StaticFilterAdapter(mFilterList, mFilerNameList);
        mAdapter.setOnItemClickListener(this);
        mRvFilter.setAdapter(mAdapter);

        mCurrentPosition = TCStaticFilterViewInfoManager.getInstance().getCurrentPosition();
        mAdapter.setCurrentSelectedPos(mCurrentPosition);


    }

    private void preivewVideo() {
        // 初始化图片时间轴
        timelineView.initVideoProgressLayout();
        // 初始化播放器
        TXVideoEditConstants.TXPreviewParam param = new TXVideoEditConstants.TXPreviewParam();
        param.videoView = videoPlayLayout;
        param.renderMode = TXVideoEditConstants.PREVIEW_RENDER_MODE_FILL_EDGE;
        TXVideoEditer videoEditer = VideoEditerSDK.getInstance().getEditer();
        if (videoEditer != null) {
            videoEditer.initWithPreview(param);
            /*long startTime = VideoEditerSDK.getInstance().getCutterStartTime();
            long endTime = VideoEditerSDK.getInstance().getCutterEndTime();
            videoEditer.startPlayFromTime(startTime, endTime);
            videoEditer.setTXVideoPreviewListener(this);*/
        }


        PlayerManagerKit.getInstance().startPlay();


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_effect;
    }


    @OnClick({R.id.dyna_take_back, R.id.dyna_take_true})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dyna_take_back:
                ConfigureLoader.getInstance().saveConfigFromDraft();

                PlayerManagerKit.getInstance().stopPlay();
                VideoEditerSDK.getInstance().getEditer().setFilter(null);
                finish();

                break;
            case R.id.dyna_take_true:
                ConfigureLoader.getInstance().saveConfigFromDraft();

                PlayerManagerKit.getInstance().stopPlay();
                finish();

                break;
        }
    }

    @Override
    public void back() {
        super.back();
        ConfigureLoader.getInstance().saveConfigFromDraft();

        PlayerManagerKit.getInstance().stopPlay();
        VideoEditerSDK.getInstance().getEditer().setFilter(null);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        TXVideoEditer mEditer = VideoEditerSDK.getInstance().getEditer();
        if (mEditer != null) {
            mEditer.stopPlay();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PlayerManagerKit.getInstance().removeAllPreviewListener();
        PlayerManagerKit.getInstance().removeAllPlayStateListener();
      //  PlayerManagerKit.getInstance().stopPlay();
    }

    @Override
    public void onItemClick(View view, int position) {
        Bitmap bitmap = null;
        if (position == 0) {
            bitmap = null;  // 没有滤镜
        } else {
            bitmap = BitmapFactory.decodeResource(getResources(), FILTER_ARR[position - 1]);
        }
        mAdapter.setCurrentSelectedPos(position);
        // 设置滤镜图片
        VideoEditerSDK.getInstance().getEditer().setFilter(bitmap);

        mCurrentPosition = position;
    }


}
