package com.ajiani.maidahui.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.MainActivity;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DownloadUtil;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.Utils.share.PopupWindows;
import com.ajiani.maidahui.activity.dynamic.PersonActivity;
import com.ajiani.maidahui.activity.dynamic.ReportActivity;
import com.ajiani.maidahui.activity.dynamic.ShopActivity;
import com.ajiani.maidahui.adapter.dynamic.ShareVideoAdapter;
import com.ajiani.maidahui.adapter.mine.VideoInfoAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.Even;
import com.ajiani.maidahui.bean.dynamic.FriendsBean;
import com.ajiani.maidahui.bean.dynamic.FriendsDataBean;
import com.ajiani.maidahui.bean.dynamic.VideoListBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.bean.share.ShareBean;
import com.ajiani.maidahui.fragment.mine.CommentFragment;
import com.ajiani.maidahui.mInterface.dynamic.DynaIn;
import com.ajiani.maidahui.presenters.dynamic.DynaPresenter;
import com.ajiani.maidahui.weight.circle.CircularProgressView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.szing.zxing.QrCodeUtil;
import com.google.gson.Gson;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayConfig;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yqw.hotheart.HeartFrameLayout;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static com.ajiani.maidahui.Utils.http.HttpUtils.url;

public class Main3Activity extends BaseActivity<DynaIn.DynaView, DynaPresenter> implements DynaIn.DynaView, ITXVodPlayListener, View.OnClickListener, UMShareListener {

    private VerticalViewPager mVerticalViewPager;
    private ImageView mIvCover;
    private TXCloudVideoView mTXCloudVideoView;
    private TXVodPlayer mTXVodPlayer;
    private MyPagerAdapter mPagerAdapter;
    private int mCurrentPosition;
    //视频详情的集合
    ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
    @BindView(R.id.lin)
    LinearLayout lin;

    private ArrayList<VideoListBean.DataBean> list;
    private ArrayList<String> strings;
    private String posstion;
    private String TAG = "wxy";
    private ArrayList<String> list1;

    boolean b = false;
    private VideoInfoBean.DataBean data;
    private UMImage thumb1;
    private UMImage image1;
    private UMWeb umWeb1;
    private PopupWindow popupWindow;
    private long sum;
    private RecyclerView recyclerView;
    private VideoInfoBean.DataBean dataBean;
    private FrameLayout frameLayout2;
    private EditText editText;
    private ImageView shareStick;
    private TextView tv_top;

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        strings = new ArrayList<>();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMessage(ArrayList<VideoInfoBean.DataBean> mList){
        dataBeans.addAll(mList);
    }

    int page;

    public void getVideoDetails(ArrayList<String> list) {
        //  for (int i = 0; i < list.size(); i++) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("video_id", list1.get(0));
        mPresenter.getVideoDetails(hashMap);
        //   }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTXCloudVideoView != null) {
            mTXCloudVideoView.onResume();
        }
        if (mTXVodPlayer != null) {
            mTXVodPlayer.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTXCloudVideoView != null) {
            mTXCloudVideoView.onPause();
        }
        if (mTXVodPlayer != null) {
            mTXVodPlayer.pause();
        }
    }

    @Override
    protected DynaPresenter preparePresenter() {
        return new DynaPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTXCloudVideoView != null) {
            mTXCloudVideoView.onDestroy();
            mTXCloudVideoView = null;
        }
        list1.clear();
        stopPlay(true);
        mTXVodPlayer = null;
        EventBus.getDefault().unregister(this);

    }

    protected void stopPlay(boolean clearLastFrame) {
        if (mTXVodPlayer != null) {
            mTXVodPlayer.stopPlay(clearLastFrame);
        }
    }


    @Override
    protected void initView() {

        //得到 视频 列表   点击的那个视频
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        //  list = (ArrayList<VideoListBean.DataBean>) bundle.getSerializable("videoList");
        list1 = bundle.getStringArrayList("list");

        posstion = bundle.getString("posstion");
        String isshow = bundle.getString("isshow");
        if (isshow != null && isshow.equals("0")) {
            CommentFragment commentBottomSheetDialogFragment = new CommentFragment(list1.get(Integer.parseInt(posstion)) + "", "0");
            commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
        }
        
        mVerticalViewPager = (VerticalViewPager) findViewById(R.id.vertical_view_pager);

        mVerticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//                mCurrentPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                // 滑动界面，首先让之前的播放器暂停，并seek到0
                //mPagerAdapter.destroyPlayerInfo(0);
                HashMap<String, String> hashMap = new HashMap<>();
                if(position==dataBeans.size()-1){
                    hashMap.put("video_id", list1.get(position));
                }else{
                    hashMap.put("video_id", list1.get(position+1));
                }

                mPresenter.getVideoDetails(hashMap);
                if (mTXVodPlayer != null) {
                    mTXVodPlayer.seek(0);
                    mTXVodPlayer.pause();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mVerticalViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

                if (position != 0) {
                    return;
                }

                ViewGroup viewGroup = (ViewGroup) page;
                mIvCover = (ImageView) viewGroup.findViewById(R.id.player_iv_cover);
                mTXCloudVideoView = (TXCloudVideoView) viewGroup.findViewById(R.id.player_cloud_view);
                if(mCurrentPosition==0){
                    mIvCover.setVisibility(View.GONE);
                }

                PlayerInfo playerInfo = mPagerAdapter.findPlayerInfo(mCurrentPosition);
                if (playerInfo != null) {
                    playerInfo.txVodPlayer.resume();
                        mTXVodPlayer = playerInfo.txVodPlayer;
                }
            }
        });

        mPagerAdapter = new MyPagerAdapter();
        mVerticalViewPager.setAdapter(mPagerAdapter);
        mVerticalViewPager.setCurrentItem(2);
        mVerticalViewPager.setCurrentItem(Integer.parseInt(posstion));
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_main4;
    }

    private void restartPlay() {
        if (mTXVodPlayer != null) {
            mTXVodPlayer.resume();
        }
    }

    @Override
    public void onPlayEvent(TXVodPlayer player, int event, Bundle param) {
        if (event == TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION) {
            int width = param.getInt(TXLiveConstants.EVT_PARAM1);
            int height = param.getInt(TXLiveConstants.EVT_PARAM2);
            if (width > height) {
                player.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
            } else {
                player.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
            }
        } else if (event == TXLiveConstants.PLAY_EVT_PLAY_END) {
            restartPlay();
        } else if (event == TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME) {// 视频I帧到达，开始播放

            PlayerInfo playerInfo = mPagerAdapter.findPlayerInfo(player);
            if (playerInfo != null) {
                playerInfo.isBegin = true;
            }
            if (mTXVodPlayer == player) {
                mIvCover.setVisibility(View.GONE);
            }
        } else if (event == TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED) {
            if (mTXVodPlayer == player) {
                mTXVodPlayer.resume();
            }
        } else if (event == TXLiveConstants.PLAY_EVT_PLAY_BEGIN) {
            PlayerInfo playerInfo = mPagerAdapter.findPlayerInfo(player);

            if (playerInfo != null && playerInfo.isBegin) {
                mIvCover.setVisibility(View.GONE);
            }

        } else if (event < 0) {
            if (mTXVodPlayer == player) {


                String desc = null;
                switch (event) {
                    case TXLiveConstants.PLAY_ERR_GET_RTMP_ACC_URL_FAIL:
                        desc = "获取加速拉流地址失败";
                        break;
                    case TXLiveConstants.PLAY_ERR_FILE_NOT_FOUND:
                        desc = "文件不存在";
                        break;
                    case TXLiveConstants.PLAY_ERR_HEVC_DECODE_FAIL:
                        desc = "h265解码失败";
                        break;
                    case TXLiveConstants.PLAY_ERR_HLS_KEY:
                        desc = "HLS解密key获取失败";
                        break;
                    case TXLiveConstants.PLAY_ERR_GET_PLAYINFO_FAIL:
                        desc = "获取点播文件信息失败";
                        break;
                    case TXLiveConstants.PLAY_EVT_PLAY_END:

                        break;
                }

            }
            //Toast.makeText(this, "event:" + event, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

    }

    @Override
    public void videoPageSuccess(String success) {

    }

    @Override
    public void videoDetailsSuccess(String success) {

        mVerticalViewPager.setVisibility(View.VISIBLE);
        VideoInfoBean videoInfoBean = new Gson().fromJson(success, VideoInfoBean.class);
        VideoInfoBean.DataBean data = videoInfoBean.getData();
        page++;
        if(mCurrentPosition==dataBeans.size()-1){
            dataBeans.set(mCurrentPosition,data);
        }else{
            dataBeans.set(mCurrentPosition+1,data);
        }


      //  if(list1!=null&&list1.size()!=0){
            /*if (page == list1.size()) {
               *//* if (posstion.equals("0")) {*//*
                  *//*  mVerticalViewPager.setCurrentItem(0);
                    mPagerAdapter.notifyDataSetChanged();
                } else {
*//*
                *//*    mPagerAdapter.notifyDataSetChanged();
                    //选中第二个播放第一个的的遗留问题
                  //  mVerticalViewPager.setCurrentItem(3);
                    mVerticalViewPager.setCurrentItem(Integer.parseInt(posstion));*//*

              //  }
            } else {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("video_id", list1.get(page));
                mPresenter.getVideoDetails(hashMap);
            }*/
     //   }

    }

    @Override
    public void videoStarSuccess(String success) {

    }

    @Override
    public void FollowUserSuccess(String success) {

    }



    @Override
    public void getFriendsSuccess(String friendsSuccess) {

        FriendsBean friendsBean = new Gson().fromJson(friendsSuccess, FriendsBean.class);
        showSharePop(dataBean, friendsBean);
    }


    //置顶
    @Override
    public void setStick(String stickSuccess) {
        if(data.getIs_top().equals("0")){
            data.setIs_top("1");
        }else{
            data.setIs_top("0"); 
        }
        Toast.makeText(this, "置顶成功", Toast.LENGTH_SHORT).show();

    }

    public void videoStar(String videoid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("video_id", videoid);
        mPresenter.getVideoStar(hashMap);
    }

    public void followUser(String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("anchor_id", userid);
        mPresenter.getFollowData(hashMap);
    }


    public void setImage(ImageView img) {
        img.setImageResource(R.mipmap.yiguznzhu);
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }


    class PlayerInfo {
        public TXVodPlayer txVodPlayer;
        public String playURL;
        public boolean isBegin;
        public View playerView;
        public int pos;
        public int reviewstatus;
    }

    public void share(ShareBean shareBean) {
        UMImage thumb = new UMImage(this, R.drawable.qwe);
        UMImage image = new UMImage(this, shareBean.getImgsrc());//资源文件
        image.setThumb(thumb);
        UMWeb umWeb = new UMWeb(shareBean.getUrl());
        umWeb.setTitle(shareBean.getTitle());
        umWeb.setThumb(image);
        umWeb.setDescription(shareBean.getContent());
        //描述
        image.compressStyle = UMImage.CompressStyle.SCALE;
        new ShareAction(this).withText(shareBean.getContent()).withMedia(umWeb).setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }).open();
    }

    class MyPagerAdapter extends PagerAdapter {

        ArrayList<PlayerInfo> playerInfoList = new ArrayList<>();
        @BindView(R.id.player_cloud_view)
        TXCloudVideoView playerCloudView;
        @BindView(R.id.player_iv_cover)
        ImageView playerIvCover;
        @BindView(R.id.video_rel)
        RecyclerView videoRel;
        ImageView userHead;
        @BindView(R.id.user_guan2)
        ImageView userGuan2;
        @BindView(R.id.user_guan)
        FrameLayout userGuan;
        @BindView(R.id.product_like)
        ImageView productLike;
        @BindView(R.id.mproduct_likenum)
        TextView mproductLikenum;
        @BindView(R.id.mproduct_comment)
        ImageView mproductComment;
        @BindView(R.id.mproduct_commnum)
        TextView mproductCommnum;
        @BindView(R.id.mproduct_reward)
        ImageView mproductReward;
        @BindView(R.id.mproduct_rewardnum)
        TextView mproductRewardnum;
        @BindView(R.id.mpro_more)
        ImageView mproMore;
        @BindView(R.id.product_share)
        TextView productShare;
        @BindView(R.id.mproduce_name)
        TextView mproduceName;
        @BindView(R.id.product_content)
        TextView productContent;
        @BindView(R.id.doublelike)
        HeartFrameLayout doublelike;
        @BindView(R.id.player_civ_avatar)
        ImageView playerCivAvatar;
        @BindView(R.id.product_disk)
        ImageView productDisk;
        @BindView(R.id.player_tv_publisher_name)
        TextView playerTvPublisherName;
        @BindView(R.id.product_music_name)
        TextView productMusicName;
        @BindView(R.id.tx_video_review_status)
        TextView txVideoReviewStatus;
        @BindView(R.id.product_shop)
        LinearLayout productShopLin;
        @BindView(R.id.product_musiclin)
        LinearLayout productMusicLin;

        protected PlayerInfo instantiatePlayerInfo(int position) {

            PlayerInfo playerInfo = new PlayerInfo();
            TXVodPlayer vodPlayer = new TXVodPlayer(Main3Activity.this);
            vodPlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
            vodPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
            vodPlayer.setVodListener(Main3Activity.this);
            TXVodPlayConfig config = new TXVodPlayConfig();
            config.setCacheFolderPath(Environment.getExternalStorageDirectory().getPath() + "/txcache");
            config.setMaxCacheItems(5);
            vodPlayer.setConfig(config);
            vodPlayer.setAutoPlay(false);
            VideoInfoBean.DataBean dataBean = dataBeans.get(position);
            playerInfo.playURL = dataBean.getVideo();
            playerInfo.txVodPlayer = vodPlayer;
            playerInfo.pos = position;
            playerInfoList.add(playerInfo);
            return playerInfo;
        }

        protected void destroyPlayerInfo(int position) {
            while (true) {
                PlayerInfo playerInfo = findPlayerInfo(position);
                if (playerInfo == null)
                    break;
                playerInfo.txVodPlayer.stopPlay(true);
               playerInfoList.remove(playerInfo);

            }
        }

        public PlayerInfo findPlayerInfo(int position) {
            for (int i = 0; i < playerInfoList.size(); i++) {
                PlayerInfo playerInfo = playerInfoList.get(i);
                if (playerInfo.pos == position) {
                    return playerInfo;
                }
            }
            return null;
        }

        public PlayerInfo findPlayerInfo(TXVodPlayer player) {
            for (int i = 0; i < playerInfoList.size(); i++) {
                PlayerInfo playerInfo = playerInfoList.get(i);
                if (playerInfo.txVodPlayer == player) {
                    return playerInfo;
                }
            }
            return null;
        }

        public void onDestroy() {
            for (PlayerInfo playerInfo : playerInfoList) {
                playerInfo.txVodPlayer.stopPlay(true);
            }
            playerInfoList.clear();
        }

        @Override
        public int getCount() {
            return dataBeans.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            dataBean = dataBeans.get(position);

            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_player_content, null);
            ButterKnife.bind(this, view);
            view.setId(position);
            // 封面
            ImageView coverImageView = (ImageView) view.findViewById(R.id.player_iv_cover);
            ImageView imageView = view.findViewById(R.id.user_guan2);
            ImageView like = view.findViewById(R.id.product_like);
            view.findViewById(R.id.video_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            //获取大小

            TextView likeNum = view.findViewById(R.id.mproduct_likenum);
            ImageView videoPause = view.findViewById(R.id.video_pause);
            userHead = view.findViewById(R.id.user_head);

            userHead.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", dataBeans.get(mCurrentPosition).getUser_id() + "");

                    //跳转到个人中心页面
                    JumpUtils.gotoActivity(Main3Activity.this, PersonActivity.class, bundle);
                }
            });


            FrameLayout frameLayout = view.findViewById(R.id.user_guan);
            Glide.with(container.getContext()).load(dataBean.getThumb()).into(coverImageView);
            // 头像
            // Log.i(TAG, "instantiateItem: "+dataBean.getAid());
            Glide.with(Main3Activity.this).load(dataBean.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(userHead);
            // 姓名
            mproduceName.setText("@"+dataBean.getNickname());
            //0 为收藏 1收藏
            if (dataBean.getIs_star().equals("0")) {
                productLike.setImageResource(R.mipmap.xinaixinfuben);
            } else {
                productLike.setImageResource(R.mipmap.like);
            }
            //是否关注，是否是自己
            String userid = (String) SPUtils.get(container.getContext(), "userid", "");

            if (dataBean.getIs_follow().equals("0")) {
                userGuan.setVisibility(View.VISIBLE);
            } else {
                userGuan.setVisibility(View.GONE);
            }
            if (userid.equals(dataBean.getUser_id() + "")) {
                userGuan.setVisibility(View.GONE);
            }
            mproductLikenum.setText(dataBean.getStar());

            //得到音乐的信息
            VideoInfoBean.DataBean.MusicBean music = dataBean.getMusic();
            if(music!=null&&!music.getName().equals("")){
                productMusicLin.setVisibility(View.VISIBLE);
                productMusicName.setText(music.getName());
                productDisk.setVisibility(View.VISIBLE);
            }

            //判断是否有商品
            if (dataBean.getGoods() != null && dataBean.getGoods().size() > 0) {
                videoRel.setVisibility(View.VISIBLE);
                List<VideoInfoBean.DataBean.GoodsBean> goods = dataBean.getGoods();
                if(goods!=null){
                    if (goods.size() > 0) {
                        videoRel.setVisibility(View.VISIBLE);
                        productDisk.setVisibility(View.GONE);
                        productShopLin.setVisibility(View.VISIBLE);
                        VideoInfoAdapter videoInfoAdapter = new VideoInfoAdapter((ArrayList<VideoInfoBean.DataBean.GoodsBean>) goods);
                        videoRel.setLayoutManager(new LinearLayoutManager(Main3Activity.this, RecyclerView.HORIZONTAL, false));
                        videoRel.setAdapter(videoInfoAdapter);
                    }else{
                        productShopLin.setVisibility(View.GONE);
                    }
                }

            } else {
                videoRel.setVisibility(View.GONE);
                productShopLin.setVisibility(View.GONE);
            }


            //点击购物袋 跳转web页面
            productShopLin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    int video_id = dataBeans.get(mCurrentPosition).getAid();
                    bundle.putString("videoId",video_id+"");
                    JumpUtils.gotoActivity(Main3Activity.this,  ShopActivity.class,bundle);
                }
            });

           mproductCommnum.setText(dataBean.getComment());
            if (dataBean.getTopic() != null & dataBean.getTopic().length() > 0) {

                String topic = dataBean.getTopic();
                String[] split = topic.split(",");
                Log.i("wxy", "instantiateItem: "+split[0]);
                String title = dataBean.getTitle();

                for (int i = 0; i < split.length; i++) {
                    SpannableString spannableString = new SpannableString("#"+split[i]+" ");
                    spannableString.setSpan(new StyleSpan(Typeface.BOLD),1,split[i].length(),SPAN_INCLUSIVE_INCLUSIVE);

                    if(title.contains(split[i])){
                        //包含代表有話題
                        title.replace("#"+split[i]+" ",spannableString);
                    }
                }
                productContent.setText(title);
            } else {
                productContent.setText(dataBean.getTitle());
            }
            //关注用户
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //关注
                    imageView.setImageResource(R.mipmap.yiguznzhu);
                    //setImage(userGuan2);
                    Animation animation = AnimationUtils.loadAnimation(Main3Activity.this, R.anim.rotes);
                    imageView.startAnimation(animation);//開始动画
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            imageView.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                    });
                    followUser(dataBeans.get(mCurrentPosition).getUser_id()+ "");
                    //进行网络解析关注或取消关注

                }
            });
            //双击 点赞
            doublelike.setOnDoubleClickListener(new HeartFrameLayout.DoubleClickListener() {
                @Override
                public void onDoubleClick(View view) {
                    if (dataBean.getIs_star().equals("0")) {
                        videoStar(dataBeans.get(mCurrentPosition).getAid() + "");
                        //网络请求
                        likeNum.setText(Integer.parseInt(dataBean.getStar()) + 1 + "");
                        dataBean.setIs_star("1");
                        like.setImageResource(R.mipmap.like);
                        dataBean.setStar(Integer.parseInt(dataBean.getStar()) + 1 + "");
                    } else {
                        //不进行网络请求
                        like.setImageResource(R.mipmap.like);
                    }
                }
            });
            //分享
            mproMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //进行弹窗
                    Log.i(TAG, "onClick: " + dataBean.getUser_id());
                    //进行网络请求得到好友列表
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("page", "1");
                    mPresenter.getFriendsData(hashMap);




/*
                    //进项分享
                    ShareBean shareBean = new ShareBean();
                    shareBean.setImgsrc(dataBean.getThumb());
                    shareBean.setTitle(dataBean.getTitle());
                    shareBean.setContent("这是一个视频");
                    String id = (String) SPUtils.get(Main3Activity.this, "userid", "");
                    // if(id.equals(""))
                    shareBean.setUrl("https://m.maidahui.com/videos/detail?video_id="+dataBean.getAid()+"&uid="+dataBean.getUser_id());
                    //share(shareBean);*/
                }
            });
            productLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dataBean.getIs_star().equals("0")) {
                        //关注
                        videoStar(dataBean.getAid() + "");
                        like.setImageResource(R.mipmap.like);
                        dataBean.setIs_star("1");
                        likeNum.setText(Integer.parseInt(dataBean.getStar()) + 1 + "");
                        dataBean.setStar(Integer.parseInt(dataBean.getStar()) + 1 + "");
                    } else {
                        //取消关注
                        videoStar(dataBean.getAid() + "");
                        dataBean.setIs_star("0");
                        like.setImageResource(R.mipmap.xinaixinfuben);
                        likeNum.setText(Integer.parseInt(dataBean.getStar()) - 1 + "");
                        dataBean.setStar(Integer.parseInt(dataBean.getStar()) - 1 + "");
                    }
                }
            });
            //点击评论

            mproductComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //弹出评论框
                    CommentFragment commentBottomSheetDialogFragment = new CommentFragment(dataBeans.get(mCurrentPosition).getAid() + "", dataBeans.get(mCurrentPosition).getComment());
                    commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
                }
            });

            // 获取此player
            TXCloudVideoView playView = (TXCloudVideoView) view.findViewById(R.id.player_cloud_view);
            playView.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
            PlayerInfo playerInfo = instantiatePlayerInfo(position);
            playerInfo.playerView = playView;
            playerInfo.txVodPlayer.setRenderRotation(0);
            playerInfo.txVodPlayer.setPlayerView(playView);

            playerInfo.txVodPlayer.startPlay(playerInfo.playURL);
            /*
             * */
            videoPause.setOnClickListener(new View.OnClickListener() {
                boolean b = false;

                private long prelongTim = 0;

                @Override
                public void onClick(View view) {

                    if (b) {
                        playerInfo.txVodPlayer.resume();
                        b = false;
                        videoPause.setBackgroundResource(R.color.transparent);
                    } else {
                        videoPause.setBackgroundResource(R.drawable.play_arrow);
                        playerInfo.txVodPlayer.pause();
                        b = true;

                    }


                }
            });

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            destroyPlayerInfo(position);

            container.removeView((View) object);
        }
    }

    private LinearLayout mShareVideoWechat;
    private LinearLayout mShareVideoWechatfriend;
    private LinearLayout mShareVideoPicture;
    private LinearLayout mShareVideoSpace;
    private LinearLayout mShareVideoQQ;
    private LinearLayout mShareVideoSign;
    private HorizontalScrollView mShareVideoHorizon;
    private LinearLayout mShareVideoReport;
    private LinearLayout mShareVideoSavepic;
    private LinearLayout mShareVideoColloect;
    private LinearLayout mShareVideoHarmony;
    private LinearLayout mShareVideoLink;
    private LinearLayout mShareVideoStick;
    private LinearLayout mShareVideoJuridict;
    private ImageView mShareVideoStar;
    private LinearLayout mShareVideoNolike;
    private HorizontalScrollView mShareVideoHorizon2;
    boolean isAnmin;

    private void showSharePop(VideoInfoBean.DataBean dataBean, FriendsBean friendsBean) {
        Log.i(TAG, "showSharePop: " + dataBean.getAid());
        ArrayList<FriendsDataBean> friendsDataBeans = new ArrayList<>();
        for (int i = 0; i < friendsBean.getData().size(); i++) {
            FriendsBean.DataBean dataBean1 = friendsBean.getData().get(i);
            FriendsDataBean friendsDataBean = new FriendsDataBean();
            friendsDataBean.setHeadimgurl(dataBean1.getHeadimgurl());
            friendsDataBean.setId(dataBean1.getId());
            friendsDataBean.setIs_follow(dataBean1.getIs_follow());
            friendsDataBean.setLevel(dataBean1.getLevel());
            friendsDataBean.setIs_follow_me(dataBean1.getIs_follow_me());
            friendsDataBean.setNickname(dataBean1.getNickname());
            friendsDataBean.setSel(false);
            friendsDataBean.setLevel_anchor(dataBean1.getLevel_anchor());
            friendsDataBeans.add(friendsDataBean);
        }
        getVideoLenght(dataBean.getVideo());
        this.data = dataBeans.get(mCurrentPosition);
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);
        View inflate = LayoutInflater.from(this).inflate(R.layout.share_video, null, false);
        popupWindow.setContentView(inflate);
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        mShareVideoWechat = (LinearLayout) inflate.findViewById(R.id.share_video_wechat);
        mShareVideoWechatfriend = (LinearLayout) inflate.findViewById(R.id.share_video_wechatfriend);
        mShareVideoPicture = (LinearLayout) inflate.findViewById(R.id.share_video_picture);
        mShareVideoStick = (LinearLayout) inflate.findViewById(R.id.share_video_stick);
        LinearLayout commLin = (LinearLayout) inflate.findViewById(R.id.share_video_commlin);
        commLin.setBackground(DrawUtils.setStroke(R.color.strok, commLin.getBackground()));
        mShareVideoJuridict = (LinearLayout) inflate.findViewById(R.id.share_video_juridict);
        mShareVideoSpace = (LinearLayout) inflate.findViewById(R.id.share_video_space);
        mShareVideoQQ = (LinearLayout) inflate.findViewById(R.id.share_video_QQ);
        LinearLayout shareVideoLin = (LinearLayout) inflate.findViewById(R.id.share_video_lin);
        mShareVideoSign = (LinearLayout) inflate.findViewById(R.id.share_video_sign);
        mShareVideoHorizon = (HorizontalScrollView) inflate.findViewById(R.id.share_video_horizon);
        mShareVideoReport = (LinearLayout) inflate.findViewById(R.id.share_video_report);
        mShareVideoSavepic = (LinearLayout) inflate.findViewById(R.id.share_video_savepic);
        mShareVideoColloect = (LinearLayout) inflate.findViewById(R.id.share_video_colloect);
        mShareVideoHarmony = (LinearLayout) inflate.findViewById(R.id.share_video_harmony);
        tv_top = (TextView) inflate.findViewById(R.id.share_tv_top);
        mShareVideoLink = (LinearLayout) inflate.findViewById(R.id.share_video_link);
        mShareVideoStar = (ImageView) inflate.findViewById(R.id.share_video_star);
        shareStick = (ImageView) inflate.findViewById(R.id.share_stick);
        mShareVideoNolike = (LinearLayout) inflate.findViewById(R.id.share_video_nolike);
        LinearLayout mShareVideoTopLin = (LinearLayout) inflate.findViewById(R.id.share_video_toplin);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.share_video_rel);
        mShareVideoHorizon2 = (HorizontalScrollView) inflate.findViewById(R.id.share_video_horizon2);
        editText = (EditText) inflate.findViewById(R.id.share_video_et);
        ImageView thumb = (ImageView) inflate.findViewById(R.id.share_video_thumb);
        TextView shareSend = (TextView) inflate.findViewById(R.id.share_video_send);
        if(data.getIs_top().equals("0")){
            shareStick.setImageResource(R.mipmap.share_nostick);
            tv_top.setText("置顶");
        }else{
            shareStick.setImageResource(R.mipmap.share_stick);
            tv_top.setText("取消置顶");
        }

        mShareVideoWechat.setOnClickListener(this);
        mShareVideoWechatfriend.setOnClickListener(this);
        mShareVideoPicture.setOnClickListener(this);
        mShareVideoSpace.setOnClickListener(this);
        mShareVideoQQ.setOnClickListener(this);
        mShareVideoSign.setOnClickListener(this);
        mShareVideoHorizon.setOnClickListener(this);
        mShareVideoReport.setOnClickListener(this);
        mShareVideoSavepic.setOnClickListener(this);
        mShareVideoColloect.setOnClickListener(this);
        mShareVideoHarmony.setOnClickListener(this);
        mShareVideoLink.setOnClickListener(this);
        mShareVideoNolike.setOnClickListener(this);
        mShareVideoHorizon2.setOnClickListener(this);
        shareStick.setOnClickListener(this);
        mShareVideoStick.setOnClickListener(this);
        mShareVideoJuridict.setOnClickListener(this);
        Glide.with(this).load(data.getThumb()).into(thumb);
      /*  popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
               
                //改变显示的按钮图片为正常状态
                editText.setFocusable(false);
                hideSoftKeyboard(Main3Activity.this, editText);
                popupWindow.setFocusable(false);
                popupWindow.setFocusable(false);
            }
        });*/

        ShareVideoAdapter shareVideoAdapter = new ShareVideoAdapter(friendsDataBeans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(shareVideoAdapter);
        //点击私信
        float curTranslationX = mShareVideoTopLin.getTranslationY();
        // 获得当前按钮的位置
        ObjectAnimator animator = ObjectAnimator.ofFloat(mShareVideoTopLin, "translationY",
                curTranslationX, 400);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mShareVideoTopLin, "translationY",
                400, curTranslationX);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置

        shareVideoAdapter.setOnClickLinstener(new ShareVideoAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion, int a) {

                for (int i = 0; i < shareVideoAdapter.mList.size(); i++) {
                    boolean sel = shareVideoAdapter.mList.get(i).isSel();
                    if (sel) {

                        if (isAnmin) {


                        } else {
                            mShareVideoHorizon2.setVisibility(View.GONE);
                            isAnmin = true;
                            animator.setDuration(250);
                            animator.start();
                        }
                        shareSend.setVisibility(View.VISIBLE);
                        /*   animator.setDuration(500);
                        animator.start();*/
                        break;
                    } else if (i == shareVideoAdapter.mList.size() - 1) {
                       /* if(isAnmin){

                        }else{*/
                        mShareVideoHorizon2.setVisibility(View.VISIBLE);
                        shareSend.setVisibility(View.GONE);
                        animator2.setDuration(250);
                        animator2.start();
                        //   }

                        isAnmin = false;
                    }
                }
                if (a > 1) {
                    shareSend.setText("发送(" + a + ")");
                } else {
                    shareSend.setText("发送");
                }
            }
        });
        //那个自己的id
        String userid = (String) SPUtils.get(this, "userid", "");
        int user_id = Integer.parseInt(data.getUser_id());
        Log.i(TAG, "showSharePop: " + userid + "=====" + user_id);
        if (userid.equals(user_id + "")) {
            mShareVideoStick.setVisibility(View.VISIBLE);
            mShareVideoReport.setVisibility(View.GONE);
            mShareVideoNolike.setVisibility(View.GONE);
            mShareVideoJuridict.setVisibility(View.VISIBLE);
        } else {
            mShareVideoStick.setVisibility(View.GONE);
            mShareVideoReport.setVisibility(View.VISIBLE);
            mShareVideoNolike.setVisibility(View.VISIBLE);
            mShareVideoJuridict.setVisibility(View.GONE);
        }
        String is_star = dataBean.getIs_star();
        if (is_star.equals("0")) {
            mShareVideoStar.setImageResource(R.mipmap.share_nocollect);
        } else {
            mShareVideoStar.setImageResource(R.mipmap.share_collect);
        }

        inflate.findViewById(R.id.share_video_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusable(false);
                hideSoftKeyboard(Main3Activity.this, editText);

                popupWindow.setFocusable(false);
                popupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.share_videolin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusable(false);
                hideSoftKeyboard(Main3Activity.this, editText);
                popupWindow.setFocusable(false);
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(mVerticalViewPager, Gravity.BOTTOM, 0, 0);
    }

    private UMImage thumb;
    private UMImage image;
    private UMWeb umWeb;

    //隐藏软键盘
    private void hideSoftKeyboard(Context context, EditText editText) {
        if (editText == null || context == null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); //强制隐藏键盘
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //分享到各个平台
            case R.id.share_video_wechat:
                //进项分享
                thumb1 = new UMImage(this, R.drawable.qwe);
                //资源文件
                image1 = new UMImage(this, data.getThumb());
                image1.setThumb(thumb1);
                umWeb1 = new UMWeb("https://m.maidahui.com/videos/detail?video_id=" + data.getAid() + "&uid=" + data.getUser_id());
                umWeb1.setTitle(data.getTitle());
                umWeb1.setThumb(image1);
                umWeb1.setDescription(data.getTopic());
                //描述
                image1.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction(this).withText(data.getTitle()).withMedia(umWeb1).setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(this).share();

                break;
            case R.id.share_video_wechatfriend:
                thumb1 = new UMImage(this, R.drawable.qwe);
                image1 = new UMImage(this, data.getThumb());//资源文件
                image.setThumb(thumb1);
                umWeb1 = new UMWeb("https://m.maidahui.com/videos/detail?video_id=" + data.getAid() + "&uid=" + data.getUser_id());
                umWeb1.setTitle(data.getTitle());
                umWeb1.setThumb(image1);
                umWeb1.setDescription(data.getTopic());
                //描述
                image1.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction(this).withText(data.getTitle()).withMedia(umWeb1).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE)
                        .setCallback(this).share();

                break;
            case R.id.share_video_picture:
                //分享图片
                showRecord(data);
                popupWindow.dismiss();
                break;
            case R.id.share_video_space:
                thumb1 = new UMImage(this, R.drawable.qwe);
                image1 = new UMImage(this, data.getThumb());//资源文件
                image.setThumb(thumb1);
                umWeb1 = new UMWeb("https://m.maidahui.com/videos/detail?video_id=" + data.getAid() + "&uid=" + data.getUser_id());
                umWeb1.setTitle(data.getTitle());
                umWeb1.setThumb(image1);
                umWeb1.setDescription(data.getTopic());
                //描述
                image1.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction(this).withText(data.getTitle()).withMedia(umWeb1).setPlatform(SHARE_MEDIA.QZONE)
                        .setCallback(this).share();
                break;
            case R.id.share_video_QQ:
                thumb1 = new UMImage(this, R.drawable.qwe);
                image1 = new UMImage(this, data.getThumb());//资源文件
                image.setThumb(thumb1);
                umWeb1 = new UMWeb("https://m.maidahui.com/videos/detail?video_id=" + data.getAid() + "&uid=" + data.getUser_id());
                umWeb1.setTitle(data.getTitle());
                umWeb1.setThumb(image1);
                umWeb1.setDescription(data.getTopic());
                //描述
                image1.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction(this).withText(data.getTitle()).withMedia(umWeb1).setPlatform(SHARE_MEDIA.QQ)
                        .setCallback(this).share();
                break;
            case R.id.share_video_sign:
                thumb1 = new UMImage(this, R.drawable.qwe);
                image1 = new UMImage(this, data.getThumb());//资源文件
                image.setThumb(thumb1);
                umWeb1 = new UMWeb("https://m.maidahui.com/videos/detail?video_id=" + data.getAid() + "&uid=" + data.getUser_id());
                umWeb1.setTitle(data.getTitle());
                umWeb1.setThumb(image1);
                umWeb1.setDescription(data.getTopic());
                //描述
                image1.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction(this).withText(data.getTitle()).withMedia(umWeb1).setPlatform(SHARE_MEDIA.SINA)
                        .setCallback(this).share();
                break;
            case R.id.share_video_horizon:
                break;
            case R.id.share_video_report:
                Bundle bundle = new Bundle();
                bundle.putString("videoID", data.getAid() + "");
                //举报页面
                popupWindow.dismiss();
                JumpUtils.gotoActivity(this, ReportActivity.class, bundle);
                break;
            case R.id.share_video_savepic:
                //弹窗消失   显示进度条

                View inflate = LayoutInflater.from(this).inflate(R.layout.upload_progress, null, false);
                PopupWindow popupWindow = new PopupWindow(Main3Activity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                inflate.findViewById(R.id.upload_back).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setBackgroundDrawable(null);
                CircularProgressView circularProgressView = inflate.findViewById(R.id.upload_progress);
                TextView textProgress = inflate.findViewById(R.id.upload_progress_text);
                TextView textView = inflate.findViewById(R.id.upload_txet);
                popupWindow.setContentView(inflate);
                float f = (float) (sum / 1024.0);
                textView.setText(f + "M");

                popupWindow.showAtLocation(mVerticalViewPager, Gravity.CENTER, 0, 0);
                DownloadUtil.get().download(data.getVideo(), FileUtils.getCache(Main3Activity.this),"video_"+data.getAid()+"", new DownloadUtil.DownloadListener() {
                    @Override
                    public void onDownloadSuccess(String path) {
                        //下载成功 保存到相册
                        // 下载后将图片or视频保存到相册中
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        intent.setData(Uri.fromFile(new File(path)));
                        sendBroadcast(intent);//发送一个广播
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Main3Activity.this, "已保存到相册", Toast.LENGTH_SHORT).show();
                                popupWindow.dismiss();
                            }
                        });

                    }

                    @Override
                    public void onDownloading(int progress) {
                        //进度条回调

//                        Log.i(TAG, "onDownloading: " + progress);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textProgress.setText(progress + "%");
                                circularProgressView.setProgress(progress * 150);
                            }
                        });

                    }

                    @Override
                    public void onDownloadFailed() {
                        //下载失败
                        Log.i(TAG, "onDownloading: 失败");
                    }


                });
                this.popupWindow.dismiss();

                break;
            case R.id.share_video_colloect:
                //进行收藏
                break;
            case R.id.share_video_harmony:
                break;
            case R.id.share_video_link:
                break;
            case R.id.share_video_nolike:
                break;
            case R.id.share_video_horizon2:
                break;
            case R.id.share_video_cancle:
                break;

            case R.id.share_video_stick:
                //置顶
                HashMap<String, String> hashMap;

                if (data.getIs_top().equals("0")) {
                    hashMap = new HashMap<>();
                    hashMap.put("name", "is_top");
                    hashMap.put("video_id", data.getAid() + "");
                    hashMap.put("value", "1");
                    tv_top.setText("取消置顶");
                    shareStick.setImageResource(R.mipmap.share_stick);
                } else {
                    hashMap = new HashMap<>();
                    hashMap.put("name", "is_top");
                    hashMap.put("video_id", data.getAid() + "");
                    hashMap.put("value", "0");
                    tv_top.setText("置顶");
                    shareStick.setImageResource(R.mipmap.share_nostick);
                }
                mPresenter.getStickData(hashMap);
                break;
            case R.id.share_video_juridict:
                break;
        }
    }

    private void getVideoLenght(String url) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                try {

                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        sum += len;
                    }
                    long finalSum = sum;

                } catch (Exception e) {

                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    private ImageView shareShop;

    private LinearLayout sharewechat;
    private LinearLayout sharefriedns;
    private LinearLayout shareSave;

    private void showRecord(VideoInfoBean.DataBean data) {
        PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setBackgroundDrawable(null);
        View inflate = LayoutInflater.from(this).inflate(R.layout.share2_item, null, false);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        popupWindow.setContentView(inflate);
  //shareitem_save
        shareShop = inflate.findViewById(R.id.share_shop);
        ImageView shareRecde = inflate.findViewById(R.id.share_recode);
        ImageView shareHead = inflate.findViewById(R.id.share_head);
        frameLayout2 = inflate.findViewById(R.id.share_card);
        FrameLayout frameLayout = inflate.findViewById(R.id.share_farme);
        TextView shareTitle = inflate.findViewById(R.id.share_title);
        TextView shareName = inflate.findViewById(R.id.share_name);
        TextView shareFrom = inflate.findViewById(R.id.share_video_from);
        sharewechat = inflate.findViewById(R.id.shareitem_wechat);
        sharefriedns = inflate.findViewById(R.id.shareitem_wechatfriends);
        shareSave = inflate.findViewById(R.id.shareitem_save);
        shareName.setText(data.getNickname());
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        String name = (String) SPUtils.get(this, "name", "");
        shareFrom.setText("来自" + name + "的分享");
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        Drawable drawable = getResources().getDrawable(R.mipmap.share_img);

        BitmapDrawable bd = (BitmapDrawable) drawable;
        final Bitmap bmm = bd.getBitmap();

        Bitmap bitmap = QrCodeUtil.createQRCodeWithLogo("https://m.maidahui.com/videos/detail?video_id=" + data.getAid() + "&uid=" + this.data.getUser_id(), 500, bmm, 1);
        shareRecde.setImageBitmap(bitmap);
        Glide.with(this).load(data.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(shareHead);

        shareTitle.setText(data.getTitle());
        Bitmap bitmap2 = getBitmap(data.getThumb());

        // shareShop.setImageBitmap(bitmap2);

        popupWindow.showAtLocation(mVerticalViewPager, Gravity.BOTTOM, 0, 0);
        //保存图片
        frameLayout2.setDrawingCacheEnabled(true);
        frameLayout2.buildDrawingCache();
    }

    private Bitmap bitmap;
    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            shareShop.setImageBitmap((Bitmap) msg.obj);
            Bitmap bmp = null;
            try {
                bmp = saveView(Main3Activity.this, frameLayout2, "ajiani", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //final Bitmap bmp = relativeLayout.getDrawingCache(); // 获取图片

            //File file = savePicture(bmp, "qwe.jpg");// 保存图片

            Bitmap finalBmp = bmp;
            sharewechat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UMImage thumb = new UMImage(Main3Activity.this, finalBmp);
                    new ShareAction(Main3Activity.this).withMedia(thumb).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(Main3Activity.this).share();
                }
            });
            Bitmap finalBmp1 = bmp;
            shareSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //保存本地
                }
            });
            sharefriedns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* UMImage thumb = new UMImage(context, finalBmp);
                    new ShareAction(context).withMedia(thumb).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE).setCallback(PopupWindows.this).share();*/
                }
            });

            return false;
        }
    });


    public Bitmap saveView(Activity activity, View view, String savePathName, boolean b) throws Exception {

        //计算设备分辨率
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        // 整个View的大小 参数是左上角 和右下角的坐标
        view.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST);

        //测量，布局View
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cacheBmp = viewConversionBitmap(view);
        if (b) {
            File file = new File(Environment.getExternalStorageDirectory() + "/Ask" + savePathName);
            FileOutputStream fos = new FileOutputStream(file);

            cacheBmp.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
            view.destroyDrawingCache();

            //发送广播更新相册
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            activity.sendBroadcast(intent);
            Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
            return cacheBmp;
        } else {
            return cacheBmp;
        }

    }

    /**
     * view转bitmap
     *
     * @param v View
     * @return Bitmap
     */
    private static Bitmap viewConversionBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */
        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }

    public Bitmap getBitmap(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    Message obtain = Message.obtain();
                    obtain.obj = bitmap;
                    handler.sendMessage(obtain);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }
}
