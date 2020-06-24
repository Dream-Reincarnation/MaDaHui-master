package com.ajiani.maidahui.activity.mine;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.VideoInfoAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.mine.StarBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.fragment.mine.CommentFragment;
import com.ajiani.maidahui.mInterface.mine.VideoInfoIn;
import com.ajiani.maidahui.presenters.mine.VideoPresenter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineProductActivity extends BaseActivity<VideoInfoIn.videoInfoView, VideoPresenter> implements VideoInfoIn.videoInfoView, View.OnClickListener {

    @BindView(R.id.user_name)
    ImageView userName;
    @BindView(R.id.product_img)
    ImageView proimg;
    @BindView(R.id.mproduct_likenum)
    TextView mproductLikenum;
    @BindView(R.id.mproduct_comment)
    ImageView mproductComment;
    @BindView(R.id.mproduct_commnum)
    TextView mproductCommnum;
    @BindView(R.id.mpro_more)
    ImageView mproMore;
    /*@BindView(R.id.doublelike)
    HeartFrameLayout like;*/
    @BindView(R.id.mproduce_name)
    TextView mproduceName;
    @BindView(R.id.mproduct_rel)
    RecyclerView mproductRel;
    @BindView(R.id.mproduct_reward)
    ImageView mroductReward;
    @BindView(R.id.mproduct_rewardnum)
    TextView mproduct_rewartxt;
    @BindView(R.id.product)
    VideoView product;
    @BindView(R.id.product_like)
    ImageView productLike;
    @BindView(R.id.product_content)
    TextView productContent;
    private EditText editText;
    private PopupWindow popupWindow;
    private VideoInfoAdapter videoCommAdapter;
    private String is_star1;
    private int aid;
    private String star1;
    private String comment;

    @Override
    protected void initData() {

      /*  like.setOnDoubleClickListener(new HeartFrameLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(View view) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("video_id", aid + "");
                if (is_star1.equals("0")) {
                    productLike.setImageResource(R.mipmap.like);
                    mPresenter.getVideoStar(hashMap);

                } else if (is_star1.equals("1")) {
                    productLike.setImageResource(R.mipmap.like);
                }
            }
        });*/
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String id = bundle.getString("id");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("video_id", id);
        //得到视频的详情
        mPresenter.getData(hashMap);
/*
        productLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MineProductActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void initView() {
        Glide.with(this).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(userName);
        ArrayList<VideoInfoBean.DataBean.GoodsBean> recommedBeans = new ArrayList<>();
        videoCommAdapter = new VideoInfoAdapter(recommedBeans);
        mproductRel.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mproductRel.setAdapter(videoCommAdapter);
        //      proimg.setVisibility(View.GONE);
        product.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                if (i == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                    Toast.makeText(MineProductActivity.this, "缓冲中", Toast.LENGTH_SHORT).show();
                } else {
                    proimg.setVisibility(View.GONE);
                    // Toast.makeText(MineProductActivity.this, "正在播放", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_mproduct;
    }

    @OnClick({R.id.user_name, R.id.mpro_more, R.id.mproduct_comment, R.id.product_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_name:
                break;
            case R.id.mpro_more:
                break;
            case R.id.product_like:

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("video_id", aid + "");
                mPresenter.getVideoStar(hashMap);


                break;
            case R.id.mproduct_comment:
                Log.i("wxy", "onViewClicked: " + aid);
                CommentFragment commentBottomSheetDialogFragment = new CommentFragment(aid + "", comment);
                commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
                View inflate = LayoutInflater.from(this).inflate(R.layout.comment_item, null, false);
                //进行评论
            /*    JumpUtils.gotoActivity(this,CommentActivity.class);
                //弹出popupwindow  进行 评论
                popupWindow = new PopupWindow(this);
                popupWindow.setFocusable(true);

                editText = inflate.findViewById(R.id.comment_ed);
                ImageView head = inflate.findViewById(R.id.comment_head);
                Glide.with(this).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(head);
                ImageView dissmiss = inflate.findViewById(R.id.comment_dis);
                RecyclerView recyclerView = inflate.findViewById(R.id.comment_rel);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                CommentAdapter commentAdapter = new CommentAdapter();
                recyclerView.setAdapter(commentAdapter);
                commentAdapter.setOnClickLinstener(new CommentAdapter.onClickLinstener() {
                    @Override
                    public void onClick(int posstion) {
                        //跳转到更多的页面
                        JumpUtils.gotoActivity(MineProductActivity.this, MoreCommentActivity.class);
                    }
                });

                head.setOnClickListener(this);
                editText.setOnClickListener(this);
                dissmiss.setOnClickListener(this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(dip2px(this, 425));
                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(userName, Gravity.BOTTOM, 0, 0);*/
                break;

        }
    }

    /**
     * 显示键盘
     *
     * @param0 et aasdf
     */
    public void showInput(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.comment_ed:
                showInput(editText);
                break;
            case R.id.comment_dis:
                popupWindow.dismiss();
                break;

        }
    }

    @Override
    protected VideoPresenter preparePresenter() {
        return new VideoPresenter();
    }

    @Override
    public void getVideoData(String success) {
        JSONObject jsonObject = JSON.parseObject(success);
        String code = jsonObject.getString("data");
        String goods1 = JSON.parseObject(code).getString("goods");

/*//        if (goods1.length() < 5) {

            VideoInfoBeans videoInfoBean = new Gson().fromJson(success, VideoInfoBeans.class);
            mproductRel.setVisibility(View.GONE);
            VideoInfoBeans.DataBean data = videoInfoBean.getData();
            Glide.with(this).load(data.getThumb()).into(proimg);
            String video_type = data.getVideo_type();
            //视频
            is_star1 = data.getIs_star();
            aid = data.getAid();
            String video = data.getVideo();
            String music = data.getMusic();
            //评论 点赞 分享
            comment = data.getComment();
            String count = data.getCount();
            String share = data.getShare();
            star1 = data.getStar();
            mproductLikenum.setText(data.getStar() + "");
            mproductCommnum.setText(data.getComment() + "");
            String is_star = data.getIs_star();
            String star = data.getStar();
            if (is_star.equals("0")) {
                //没有收藏
                productLike.setImageResource(R.mipmap.xinaixinfuben);
            } else {
                //收藏看
                productLike.setImageResource(R.mipmap.like);
            }
            mproductLikenum.setText(star + "");
            mproductCommnum.setText(comment + "");
            //用户名称
            mproduceName.setText(data.getNickname());
            //标题
            productContent.setText(data.getTitle());
            //头像
            Glide.with(this).load(data.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(userName);
            Log.i("WXY", "getVideoData: " + video);
            playerConfig = new PlayerConfig.Builder()
                    .enableCache()
                    .usingSurfaceView()
                    .savingProgress()
                    .disableAudioFocus()
                    .setLooping()
                    .addToPlayerManager()
                    .build();
        *//*    product.setUrl(video);
            product.setPlayerConfig(playerConfig);
           product.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
           product.start();*//*
            product.setVideoPath(video);
            product.seekTo(0);
            product.start();
          *//*  videoPlayer.setUp(video, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
            videoPlayer.startVideo();*//*
        } else {*/
            VideoInfoBean videoInfoBean = new Gson().fromJson(success, VideoInfoBean.class);
            VideoInfoBean.DataBean data = videoInfoBean.getData();
            String video_type = data.getVideo_type();
            Glide.with(this).load(data.getThumb()).into(proimg);
            aid = data.getAid();
            //视频
            star1 = data.getStar()+"";
            is_star1 = data.getIs_star();
            mproductLikenum.setText(data.getStar() + "");
            mproductCommnum.setText(data.getComment() + "");
            String video = data.getVideo();

            //评论 点赞 分享
            String comment = data.getComment()+"";
            String count = data.getCount()+"";
            String share = data.getShare()+"";
            String is_star = data.getIs_star();

            String star = data.getStar()+"";
            if (is_star.equals("0")) {
                //没有收藏
                productLike.setImageResource(R.mipmap.xinaixinfuben);
            } else {
                //收藏看
                productLike.setImageResource(R.mipmap.like);
            }
            mproductLikenum.setText(star + "");
            mproductCommnum.setText(comment + "");
            //用户名称
            mproduceName.setText(data.getNickname());
            //标题
            productContent.setText(data.getTitle());
            //头像
            Glide.with(this).load(data.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(userName);
            List<VideoInfoBean.DataBean.GoodsBean> goods = data.getGoods();
            if(goods.size()>0){
                if (goods.size() == 0 || goods == null) {
                    mproductRel.setVisibility(View.GONE);
                } else {
                    //绑定适配请
                    videoCommAdapter.mList.addAll(goods);
                    videoCommAdapter.notifyDataSetChanged();
                }
            }else{
                mproductRel.setVisibility(View.GONE);
            }

          /*  videoPlayer.setUp(video, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
            videoPlayer.startVideo();*/
            product.setVideoPath(video);
            product.seekTo(0);
            product.start();
        /*    playerConfig = new PlayerConfig.Builder()
                    .enableCache()
                    .usingSurfaceView()
                    .savingProgress()
                    .disableAudioFocus()
                    .setLooping()
                    .addToPlayerManager()
                    .build();
            product.setUrl(video);
            product.setPlayerConfig(playerConfig);
            product.setScreenScale(IjkVideoView.SCREEN_SCALE_CENTER_CROP);
            product.start();*/
        }




    @Override
    public void getCommentSuccess(String success) {

    }

    @Override
    public void videoStarSuccess(String success) {
        StarBean starBean = new Gson().fromJson(success, StarBean.class);
        int status = starBean.getData().getStatus();

        if (status == 0) {
            is_star1 = status + "";
            productLike.setImageResource(R.mipmap.xinaixinfuben);
            int i = Integer.parseInt(star1);
            star1 = (i - 1) + "";
            mproductLikenum.setText(star1 + "");
            Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
        } else {
            is_star1 = status + "";
            productLike.setImageResource(R.mipmap.like);
            mproductLikenum.setText(star1 + 1 + "");
            star1 += 1;
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void FollowUserSuccess(String success) {

    }

    @Override
    public void error(String error) {

    }


}
