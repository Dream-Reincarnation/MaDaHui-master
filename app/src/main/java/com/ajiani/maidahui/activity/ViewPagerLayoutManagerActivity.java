package com.ajiani.maidahui.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.adapter.MyAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.dynamic.FollowUserBean;
import com.ajiani.maidahui.bean.mine.StarBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBeans;
import com.ajiani.maidahui.bean.share.ShareBean;
import com.ajiani.maidahui.fragment.mine.CommentFragment;
import com.ajiani.maidahui.mInterface.mine.VideoInfoIn;
import com.ajiani.maidahui.presenters.mine.VideoPresenter;
import com.ajiani.maidahui.weight.viewpager.OnViewPagerListener;
import com.ajiani.maidahui.weight.viewpager.ViewPagerLayoutManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * ViewPagerLayoutManager
 */
public class ViewPagerLayoutManagerActivity extends BaseActivity<VideoInfoIn.videoInfoView, VideoPresenter> implements VideoInfoIn.videoInfoView{
    private static final String TAG = "ViewPagerActivity";
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    public ArrayList<String> list;
    int posstion;
    int a;
    private ArrayList<VideoInfoBean.DataBean> dataBeans;
    private String posstion1;
    public void share(ShareBean shareBean){
        UMImage thumb =  new UMImage(this, R.drawable.qwe);
        UMImage image = new UMImage(this, shareBean.getImgsrc());//资源文件
        image.setThumb(thumb);
        UMWeb umWeb = new UMWeb(shareBean.getUrl());
        umWeb.setTitle(shareBean.getTitle());
        umWeb.setThumb(image);
        umWeb.setDescription(shareBean.getContent());
        //描述
        image.compressStyle = UMImage.CompressStyle.SCALE;
        new ShareAction(this).withText(shareBean.getContent()).withMedia(umWeb).setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN)
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
    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        list = bundle.getStringArrayList("list");
        posstion1 = bundle.getString("posstion");


        initdata();
        mAdapter.setFolllowOnClickLinstener(new MyAdapter.onFollowClickLinstener() {
            @Override
            public void onFollowClick(int posstion) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("anchor_id",mAdapter.mList.get(posstion).getUser_id()+"");
                mPresenter.getFollowData(hashMap);
            }
        });
        //分享
 //https://www.maidahui.com/wechat/videos/detail?video_id=视频ID&uid=分享人会员ID
        mAdapter.setOnShareClickLinstener(new MyAdapter.onShareClickLinstener() {
            @Override
            public void onShareClick(int posstion) {
                //调用分享
                VideoInfoBean.DataBean dataBean = mAdapter.mList.get(posstion);
                ShareBean shareBean = new ShareBean();
                shareBean.setImgsrc(dataBean.getThumb());
                shareBean.setTitle(dataBean.getTitle());
                shareBean.setContent("这是一个视频");
                String id = (String) SPUtils.get(ViewPagerLayoutManagerActivity.this, "userid", "");
               // if(id.equals(""))
                shareBean.setUrl("https://www.maidahui.com/wechat/videos/detail?video_id="+dataBean.getAid()+"&uid="+dataBean.getUser_id());
                share(shareBean);

            }
        });

      mAdapter.setOnClickLinstener(new MyAdapter.onLikeClickLinstener() {
          @Override
          public void onClick(int posstion) {
              //
              HashMap<String, String> hashMap = new HashMap<>();
              hashMap.put("video_id", mAdapter.mList.get(posstion).getAid() + "");
              mPresenter.getVideoStar(hashMap);
          }
      });
    //
        mAdapter.setOnCommentClickLinstener(new MyAdapter.onCommentClickLinstener() {
            @Override
            public void onClick(int posstion) {
                VideoInfoBean.DataBean dataBean = mAdapter.mList.get(posstion);
                int aid = dataBean.getAid();
                String comment = dataBean.getComment()+"";
                CommentFragment commentBottomSheetDialogFragment = new CommentFragment(aid + "", comment);
                commentBottomSheetDialogFragment.show(getSupportFragmentManager(), "");
            }
        });
    }

    private void initdata() {
        //进行网络解析 请求视频详情，
        if(posstion>=list.size()){
            return;
        }else{
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("video_id", list.get(posstion));
         //   Log.i("WXY", "onClick:aaaa "+list.get(posstion));
            mPresenter.getData(hashMap);
        }


    }

    @Override
    protected  void initView() {
        dataBeans = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recycler);
        mLayoutManager = new ViewPagerLayoutManager(this, OrientationHelper.VERTICAL);
       /* MoveToPosition(mLayoutManager,posstion1);*/
        ArrayList<VideoInfoBean.DataBean> dataBeans = new ArrayList<>();
        mAdapter = new MyAdapter(dataBeans);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        initListener();
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_view_pager_layout_manager;
    }

    private void initListener(){
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {

            @Override
            public void onInitComplete() {
                playVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext,int position) {

                int index = 0;
                if (isNext){
                    index = 0;
                }else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position,boolean isBottom) {

                playVideo(0);
            }
        });
    }

    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                }else {
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index){
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }

    @Override
    public void error(String error) {

    }

    @Override
    public void getVideoData(String success){
        JSONObject jsonObject = JSON.parseObject(success);
        String code = jsonObject.getString("data");
        String goods1 = JSON.parseObject(code).getString("goods");
      //  if(goods1.length()<5){

            VideoInfoBean videoInfoBean = new Gson().fromJson(success, VideoInfoBean.class);
            VideoInfoBean.DataBean data = videoInfoBean.getData();
            dataBeans.add(data);
//            a++;
      /*  }else{
            VideoInfoBean videoInfoBean = new Gson().fromJson(success, VideoInfoBean.class);
            a++;
        }*/

         posstion++;
        initdata();

        if(posstion==list.size()){

            mAdapter.mList.addAll(dataBeans);

            mAdapter.notifyDataSetChanged();

        }
        if(posstion1==null&posstion1.equals("")){

        }else{
            mRecyclerView.scrollToPosition(Integer.parseInt(posstion1));
        }


    }
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }

    @Override
    public void getCommentSuccess(String success) {

    }

    @Override
    public void videoStarSuccess(String success) {
        StarBean starBean = new Gson().fromJson(success, StarBean.class);
        int status = starBean.getData().getStatus();
        mAdapter.setLike(status);
    }

    @Override
    public void FollowUserSuccess(String success) {
        FollowUserBean followUserBean = new Gson().fromJson(success, FollowUserBean.class);
        FollowUserBean.DataBean data = followUserBean.getData();
        int follow = data.getFollow();
        if(follow==1){
            Toast.makeText(this, "关注成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "取消知道", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected VideoPresenter preparePresenter() {
        return new VideoPresenter();
    }
}
