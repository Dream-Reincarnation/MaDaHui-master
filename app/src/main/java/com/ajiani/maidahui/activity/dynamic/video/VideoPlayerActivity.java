package com.ajiani.maidahui.activity.dynamic.video;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.dynamic.VideoListBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.mInterface.dynamic.DynaIn;
import com.ajiani.maidahui.presenters.dynamic.DynaPresenter;
import com.ajiani.maidahui.tencent.TCVideoInfo;
import com.ajiani.maidahui.tencent.TCVodPlayerActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.ITXVodPlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayConfig;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yqw.hotheart.HeartFrameLayout;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class VideoPlayerActivity extends BaseActivity<DynaIn.DynaView, DynaPresenter> implements DynaIn.DynaView, ITXVodPlayListener, View.OnClickListener, UMShareListener {

    @BindView(R.id.view_pager)
    VerticalViewPager mVerticalViewPager;
    private ImageView mIvCover;
    private TXCloudVideoView mTXCloudVideoView;
    private TXVodPlayer mTXVodPlayer;
    private int mCurrentPosition;
    private MyPagerAdapter mPagerAdapter;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected DynaPresenter preparePresenter() {
        return new DynaPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        ArrayList<VideoListBean.DataBean> videoList= (ArrayList<VideoListBean.DataBean>) bundle.getSerializable("videoList");
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            VideoListBean.DataBean dataBean = videoList.get(i);
            VideoInfoBean.DataBean dataBean1 = new VideoInfoBean.DataBean();
           dataBean1.setVideo(dataBean.getVideo());
           dataBean1.setThumb(dataBean.getThumb());
           dataBean1.setHeadimgurl(dataBean.getHeadimgurl());
           dataBeans.add(dataBean1);
        }
        mVerticalViewPager.setOffscreenPageLimit(2);

        mVerticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//                mCurrentPosition = position;
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                // 滑动界面，首先让之前的播放器暂停，并seek到0
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


                PlayerInfo playerInfo = mPagerAdapter.findPlayerInfo(mCurrentPosition);
                if (playerInfo != null) {
                    playerInfo.txVodPlayer.resume();
                    mTXVodPlayer = playerInfo.txVodPlayer;

                }
            }
        });
        mPagerAdapter = new MyPagerAdapter(dataBeans);
        mVerticalViewPager.setAdapter(mPagerAdapter);


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_videoplayer;
    }

    @Override
    public void videoPageSuccess(String success) {

    }

    @Override
    public void videoDetailsSuccess(String success) {

    }

    @Override
    public void videoStarSuccess(String success) {

    }

    @Override
    public void FollowUserSuccess(String success) {

    }

    @Override
    public void getFriendsSuccess(String friendsSuccess) {

    }

    @Override
    public void setStick(String stickSuccess) {

    }

    @Override
    public void onPlayEvent(TXVodPlayer txVodPlayer, int i, Bundle bundle) {

    }

    @Override
    public void onNetStatus(TXVodPlayer txVodPlayer, Bundle bundle) {

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

    //viewpager适配器

    class MyPagerAdapter extends PagerAdapter {

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
        ArrayList<PlayerInfo> playerInfoList = new ArrayList<>();


        public ArrayList<VideoInfoBean.DataBean> mList;

        public MyPagerAdapter(ArrayList<VideoInfoBean.DataBean> mList) {
            this.mList = mList;
            //初始化playinfo
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            VideoInfoBean.DataBean dataBean = mList.get(position);

            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_player_content, null);
            view.setId(position);
            // 封面
            ImageView coverImageView = (ImageView) view.findViewById(R.id.player_iv_cover);
            Glide.with(container.getContext()).load(dataBean.getThumb()).into(coverImageView);
            //人名
           // mproduceName.setText("@"+dataBean.getNickname());
           // mproductLikenum.setText(dataBean.getStar());
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
            FrameLayout frameLayout = view.findViewById(R.id.user_guan);
            TXCloudVideoView playView = (TXCloudVideoView) view.findViewById(R.id.player_cloud_view);
            Glide.with(VideoPlayerActivity.this).load(dataBean.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(userHead);
            //初始化数据
            PlayerInfo playerInfo = instantiatePlayerInfo(position);
            playerInfo.playerView = playView;
            playerInfo.txVodPlayer.setPlayerView(playView);
            playerInfo.txVodPlayer.startPlay(playerInfo.playURL);
            container.addView(view);
            return view;
        }

        protected PlayerInfo instantiatePlayerInfo(int position) {
            PlayerInfo playerInfo = new PlayerInfo();
            TXVodPlayer vodPlayer = new TXVodPlayer(VideoPlayerActivity.this);
            vodPlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT);
            vodPlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
            vodPlayer.setVodListener(VideoPlayerActivity.this);
            TXVodPlayConfig config = new TXVodPlayConfig();
            config.setCacheFolderPath(Environment.getExternalStorageDirectory().getPath() + "/txcache");
            config.setMaxCacheItems(5);
            vodPlayer.setConfig(config);
            vodPlayer.setAutoPlay(false);

            VideoInfoBean.DataBean dataBean = mList.get(position);
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
        public void destroyItem(ViewGroup container, int position, Object object) {

            destroyPlayerInfo(position);

            container.removeView((View) object);
        }

       @Override
        public int getCount() {
            return mList.size();
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
             return view == object;
        }
    }




    class PlayerInfo {
        public TXVodPlayer txVodPlayer;
        public String playURL;
        public boolean isBegin;
        public View playerView;
        public int pos;

    }

}
