package com.ajiani.maidahui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.adapter.mine.VideoInfoAdapter;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBeans;
import com.ajiani.maidahui.fragment.mine.CommentFragment;
import com.ajiani.maidahui.weight.FullScreenVideoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ArrayList<VideoInfoBean.DataBean> mList;
    private Context context;
    private onLikeClickLinstener onLikeClick;
    private ViewHolder holder1;
    private int star;
    private String comment;
    private String is_star;
    private onCommentClickLinstener onCommentClick;
    private onFollowClickLinstener onFollowClick;
    private onShareClickLinstener onShareClick;

    public MyAdapter(ArrayList<VideoInfoBean.DataBean> mList) {
        this.mList = mList;
    }

    public MyAdapter() {

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);
        return new ViewHolder(view);
    }

    public void setLike(int status) {
        if (holder1 != null) {

            if (status == 0) {

                if (is_star.equals("0")) {
                    holder1.mproductLikenum.setText(star + "");
                } else {
                    star -= 1;
                    holder1.mproductLikenum.setText(star + "");
                }
                holder1.productLike.setImageResource(R.mipmap.xinaixinfuben);
                //-1
                Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
            } else {
                holder1.productLike.setImageResource(R.mipmap.like);
//                int i = Integer.parseInt(star);
                star += 1;
                holder1.mproductLikenum.setText(star + "");
              /*  if(is_star.equals("0")){
                    holder1.mproductLikenum.setText(i+"");
                }else{
                    holder1.mproductLikenum.setText(i-1+"");
                }*/
                Toast.makeText(context, "搜藏成功", Toast.LENGTH_SHORT).show();

            }
        }

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userGuan.setEnabled(true);
        holder1 = holder;
        Glide.with(context).load(mList.get(position).getThumb()).into(holder.imgThumb);
        Log.i("wxy", "onBindViewHolder: "+mList.get(position).getVideo());
        holder.videoView.setVideoPath(mList.get(position).getVideo());
        //设置评论数量和其他
        if (mList.get(position).getIs_star().equals("0")) {
            //没有收藏
            holder.productLike.setImageResource(R.mipmap.xinaixinfuben);
        } else {
            //收藏看
            holder.productLike.setImageResource(R.mipmap.like);
        }

        String userid = (String) SPUtils.get(context, "userid", "");

        //0 是未关注 1是已关注
        if (mList.get(position).getIs_follow().equals("0")){
          holder.userGuan2.setVisibility(View.VISIBLE);
        }else{
            holder.userGuan2.setVisibility(View.GONE);
        }


        if(userid.equals(mList.get(position).getUser_id()+"")){
            holder.userGuan2.setVisibility(View.GONE);
        }
        if(mList.get(position).getGoods()!=null&&mList.get(position).getGoods().size()>0){
            holder.videoRel.setVisibility(View.VISIBLE);
            List<VideoInfoBean.DataBean.GoodsBean> goods = mList.get(position).getGoods();

            if(goods.size()>0){
                holder.videoRel.setVisibility(View.VISIBLE);
                VideoInfoAdapter videoInfoAdapter = new VideoInfoAdapter((ArrayList<VideoInfoBean.DataBean.GoodsBean>) goods);
                holder1.videoRel.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
                holder.videoRel.setAdapter(videoInfoAdapter);
            }
        }
        holder.mproductCommnum.setText(mList.get(position).getComment());
        Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.userName);
        star = Integer.parseInt(mList.get(position).getStar());
        comment = mList.get(position).getComment();
        holder.mproductLikenum.setText(mList.get(position).getStar());
        String topic = mList.get(position).getTopic();
        String[] split = topic.split(",");

        if(mList.get(position).getTopic()!=null&mList.get(position).getTopic().length()>0){
           holder.productContent.setText("#"+mList.get(position).getTopic()+" "+mList.get(position).getTitle());
       }else{
            holder.productContent.setText(mList.get(position).getTitle());
        }

        is_star = mList.get(position).getIs_star();
        holder.mproductComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCommentClick != null) {
                    onCommentClick.onClick(position);
                }
            }
        });
        holder.productLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLikeClick != null) {
                    onLikeClick.onClick(position);
                }
            }
        });
        holder.mproduceName.setText(mList.get(position).getNickname());

        holder.userGuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onFollowClick!=null){
                    holder.userGuan.setEnabled(false);
                    holder.userGuan2.setImageResource(R.mipmap.yiguznzhu);
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotes);
                    holder.userGuan.startAnimation(animation);//開始动画
                    /*Animation animation = new RotateAnimation(0, 360);
                    animation.setDuration(1000);
                    animation.setRepeatCount(1);//动画的反复次数
                    animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                    holder.userGuan.startAnimation(animation);//開始动画*/
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        //动画开始执行
                        @Override
                        public void onAnimationStart(Animation animation) {
                            // TODO Auto-generated method stub

                        }
                        //动画执行中
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // TODO Auto-generated method stub

                        }
                        //动画结束执行
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            // TODO Auto-generated method stub
                            holder.userGuan2.setVisibility(View.GONE);
                        }
                    });



                   onFollowClick.onFollowClick(position);
                }
            }
        });

        holder.mproMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转分享
             if(onShareClick!=null){
                 onShareClick.onShareClick(position);
             }
            }
        });
    }

    //点赞
    public interface onLikeClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onLikeClickLinstener onClickLinstener) {
        this.onLikeClick = onClickLinstener;
    }



    // 评论
    public interface onCommentClickLinstener {
        void onClick(int posstion);
    }

    public void setOnCommentClickLinstener(onCommentClickLinstener onClickLinstener) {
        this.onCommentClick = onClickLinstener;
    }

    //分享
      public interface onShareClickLinstener{
              void onShareClick(int posstion);
          }
          public void setOnShareClickLinstener(onShareClickLinstener onClickLinstener){
              this.onShareClick=onClickLinstener;
          }

     //关注

     public interface onFollowClickLinstener{
             void onFollowClick(int posstion);
         }
         public void setFolllowOnClickLinstener(onFollowClickLinstener onClickLinstener){
             this.onFollowClick=onClickLinstener;
         }


    /*@Override
    public void onBindViewHolder(ViewPagerLayoutManagerActivity.MyAdapter.ViewHolder holder, int position) {
        holder.img_thumb.setImageResource(imgs[position%2]);
        holder.videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+ videos[position%2]));
    }
*/
    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_view)
        FullScreenVideoView videoView;
        @BindView(R.id.img_thumb)
        ImageView imgThumb;
        @BindView(R.id.user_name)
        ImageView userName;
        @BindView(R.id.product_like)
        ImageView productLike;
        @BindView(R.id.mproduct_likenum)
        TextView mproductLikenum;
        @BindView(R.id.mproduct_comment)
        ImageView mproductComment;
        @BindView(R.id.mproduct_commnum)
        TextView mproductCommnum;    //product_content
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
       /* @BindView(R.id.doublelike)
        HeartFrameLayout doublelike;*/
        @BindView(R.id.img_play)
        ImageView imgPlay;
        @BindView(R.id.user_guan)
        FrameLayout userGuan;
        @BindView(R.id.user_guan2)
        ImageView userGuan2;
        @BindView(R.id.root_view)
        RelativeLayout rootView;
        @BindView(R.id.video_rel)
        RecyclerView videoRel;
        public ViewHolder(View itemView) {
            super(itemView);
          /*  img_thumb = itemView.findViewById(R.id.img_thumb);
            videoView = itemView.findViewById(R.id.video_view);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);*/
            ButterKnife.bind(this, itemView);
        }
    }

   /* class ViewHolder {
        @BindView(R.id.video_view)
        FullScreenVideoView videoView;
        @BindView(R.id.img_thumb)
        ImageView imgThumb;
        @BindView(R.id.user_name)
        ImageView userName;
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
        @BindView(R.id.img_play)
        ImageView imgPlay;
        @BindView(R.id.root_view)
        RelativeLayout rootView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }*/
}

