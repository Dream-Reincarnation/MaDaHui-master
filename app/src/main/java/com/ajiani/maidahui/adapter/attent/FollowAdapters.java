package com.ajiani.maidahui.adapter.attent;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DPUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.adapter.mine.VlogShopAdapter;
import com.ajiani.maidahui.bean.attention.FollowListBeans;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.weight.head.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class FollowAdapters extends RecyclerView.Adapter<FollowAdapters.ViewHolder> {


    private Context context;

    public ArrayList<FollowListBeans.DataBean> mList;
    private onClickLinstener onClick;
    boolean isStar;


    public FollowAdapters(ArrayList<FollowListBeans.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.follow_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        FollowListBeans.DataBean dataBean = mList.get(position);
        Glide.with(context).load(dataBean.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.followHead);
        holder.followName.setText(dataBean.getNickname());
        String create_time = mList.get(position).getCreate_time();
        String format_create_time = mList.get(position).getFormat_create_time();
        String[] time = create_time.split(" ");
//        holder.followTime.setText(format_create_time+" "+time[1]);
        holder.followTime.setText(format_create_time);
        String title = dataBean.getTitle();
        String topic = dataBean.getTopic();
        String[] split = topic.split(",");
        if (topic != null & topic.length() > 0) {

            for (int i = 0; i < split.length; i++) {
                SpannableString spannableString = new SpannableString("#" + split[i] + " ");
                spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, split[i].length() + 1, SPAN_INCLUSIVE_INCLUSIVE);

                if (title.contains(split[i])) {
                    //包含代表有話題
                    title.replace("#" + split[i] + " ", spannableString);
                }
            }
        }
        holder.followTitle.setText(title);
        //判断是否有商品
        List<VideoInfoBean.DataBean.GoodsBean> goods = mList.get(position).getGoods();
        if (goods != null && goods.size() > 0) {
            holder.followItemRel.setVisibility(View.VISIBLE);
            holder.followItemRel.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            VlogShopAdapter vlogShopAdapter = new VlogShopAdapter((ArrayList<VideoInfoBean.DataBean.GoodsBean>) goods, dataBean.getAid());
            holder.followItemRel.setAdapter(vlogShopAdapter);
        } else {

            holder.followItemRel.setVisibility(View.GONE);
        }

        //判断是否有评论.前面名字加粗
        int comment = dataBean.getComment();
        if (comment > 0) {
            holder.followCommentNum.setText("共" + comment + "条评论");
            holder.followCommentLin.setVisibility(View.VISIBLE);
            if (comment == 1) {
                SpannableString spannableString = new SpannableString(dataBean.getComment_data().get(0).getNickname() + ":" +
                        dataBean.getComment_data().get(0).getContent());
                spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, dataBean.getComment_data().get(0).getNickname().length() + 1, SPAN_INCLUSIVE_INCLUSIVE);
                holder.followFirstComment.setText(spannableString);
                holder.followSecondComment.setVisibility(View.GONE);
            } else {
                SpannableString spannableString = new SpannableString(dataBean.getComment_data().get(0).getNickname() + ":" +
                        dataBean.getComment_data().get(0).getContent());
                spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, dataBean.getComment_data()
                        .get(0).getNickname().length() + 1, SPAN_INCLUSIVE_INCLUSIVE);
                holder.followFirstComment.setText(spannableString);
                SpannableString spannableString1 = new SpannableString(dataBean.getComment_data().get(1).getNickname() + ":" +
                        dataBean.getComment_data().get(1).getContent());
                spannableString1.setSpan(new StyleSpan(Typeface.BOLD), 0, dataBean.getComment_data().get(1)
                        .getNickname().length() + 1, SPAN_INCLUSIVE_INCLUSIVE);
                holder.followSecondComment.setText(spannableString1);
            }
        } else {
            holder.followCommentLin.setVisibility(View.GONE);
        }

        //判断是都已点赞
        if (dataBean.getIs_star().equals("1")) {
            holder.attentLike.setImageResource(R.mipmap.mine_like);
        } else {
            holder.attentLike.setImageResource(R.mipmap.mine_unlike);
        }
        holder.followStarNum.setText("等"+dataBean.getStar()+"次赞");


        Glide.with(context).asBitmap().load(dataBean.getThumb())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        int screenWidth = ((Activity) holder.followVideoThumb.getContext()).getWindowManager().getDefaultDisplay().getWidth();
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        float imgWidth= (float) (screenWidth);
                        float imgHeight=height*(imgWidth/width);
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)  holder.followVideoThumb.getLayoutParams();
                        //设置图片的相对于屏幕的宽高比
                        params.width= (int) imgWidth;
                        params.height = (int) imgHeight;
                        holder.followVideoThumb.setLayoutParams(params);
                        holder.followVideoThumb.setImageBitmap(resource);
                    }
                });

        //显示点赞的头像显示与隐藏
        List<FollowListBeans.DataBean.NewStarUsersBean> new_star_users = dataBean.getNew_star_users();
        if(dataBean.getStar()==0){
            holder.followStarRela.setVisibility(View.GONE);
        }else{
            //判断点赞数量
            if(mList.get(position).getStar()==1){
                if(mList.get(position).getIs_star().equals("1")){
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartHead);
                }else{
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartFirst);
                    holder.followStartSecond.setVisibility(View.INVISIBLE);
                    holder.followStartThird.setVisibility(View.INVISIBLE);
                }

            }else if(mList.get(position).getStar()==2){
                if(mList.get(position).getIs_star().equals("1")){
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartHead);
                    Glide.with(context).load(new_star_users.get(1).getHeadimgurl()).into(holder.followStartFirst);
                    holder.followStartSecond.setVisibility(View.INVISIBLE);
                    holder.followStartThird.setVisibility(View.INVISIBLE);
                }else{
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartFirst);
                    Glide.with(context).load(new_star_users.get(1).getHeadimgurl()).into(holder.followStartSecond);
                    holder.followStartThird.setVisibility(View.INVISIBLE);
                }
            }else if(mList.get(position).getStar()==3){
                if(mList.get(position).getIs_star().equals("1")){
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartHead);
                    Glide.with(context).load(new_star_users.get(1).getHeadimgurl()).into(holder.followStartFirst);
                    Glide.with(context).load(new_star_users.get(2).getHeadimgurl()).into(holder.followStartSecond);

                }else{
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartFirst);
                    Glide.with(context).load(new_star_users.get(1).getHeadimgurl()).into(holder.followStartSecond);
                    Glide.with(context).load(new_star_users.get(2).getHeadimgurl()).into(holder.followStartThird);
                }
            }else {
                if(mList.get(position).getIs_star().equals("1")){
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartHead);
                    Glide.with(context).load(new_star_users.get(1).getHeadimgurl()).into(holder.followStartFirst);
                    Glide.with(context).load(new_star_users.get(2).getHeadimgurl()).into(holder.followStartSecond);
                    Glide.with(context).load(new_star_users.get(3).getHeadimgurl()).into(holder.followStartThird);
                }else{
                    Glide.with(context).load(new_star_users.get(0).getHeadimgurl()).into(holder.followStartFirst);
                    Glide.with(context).load(new_star_users.get(1).getHeadimgurl()).into(holder.followStartSecond);
                    Glide.with(context).load(new_star_users.get(2).getHeadimgurl()).into(holder.followStartThird);
                }
            }
        }

        String head = (String) SPUtils.get(context, "head", "");
        //点赞
        holder.attentLikeFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.getIs_star().equals("1")) {
                    dataBean.setIs_star("0");
                    dataBean.setStar(dataBean.getStar()-1);
                    //去掉第一个图片
                    new_star_users.remove(0);
                    dataBean.setNew_star_users(new_star_users);
                    holder.followStarNum.setText("等"+dataBean.getStar()+"次赞");
                    holder.attentLike.setImageResource(R.mipmap.mine_unlike);
                    //取消点赞


                    if(isStar){
                        holder.followStartHead.setVisibility(View.GONE);
                        if(new_star_users.size()>3){
                            holder.followStartThird.setVisibility(View.VISIBLE);
                        }

                        rightAnima(holder.followStarRelas);
                    }
                } else {
                    isStar=true;
                    //进行点赞
                    dataBean.setIs_star("1");
                    dataBean.setStar(dataBean.getStar()+1);
                    //保存起来
                    FollowListBeans.DataBean.NewStarUsersBean newStarUsersBean1 = new FollowListBeans.DataBean.NewStarUsersBean();
                    newStarUsersBean1.setHeadimgurl(head);
                    new_star_users.add(0,newStarUsersBean1);
                    dataBean.setNew_star_users(new_star_users);
                    holder.followStarNum.setText("等"+dataBean.getStar()+"次赞");
                    holder.attentLike.setImageResource(R.mipmap.mine_like);
                    holder.followStartThird.setVisibility(View.INVISIBLE);
                    holder.followStartHead.setVisibility(View.VISIBLE);
                    //进行动画
                    Glide.with(context).load(head).into(holder.followStartHead);
                    leftAnima(holder.followStarRelas);
                }
                //进行网络请求
                HttpUtils.instance().videostar(dataBean.getAid() + "");
            }
        });
        //分享


        //弹出评论框
        holder.attentComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(position,"comment");
                }
            }
        });
        holder.followCommentLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(position,"comment");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.follow_head)
        ImageView followHead;
        @BindView(R.id.follow_name)
        TextView followName;
        @BindView(R.id.follow_time)
        TextView followTime;
        @BindView(R.id.follow_title)
        TextView followTitle;
        @BindView(R.id.follow_item_rel)
        RecyclerView followItemRel;
        @BindView(R.id.follow_start_first)
        CircleImageView followStartFirst;
        @BindView(R.id.follow_star_head)
        CircleImageView followStartHead;
        @BindView(R.id.follow_start_second)
        CircleImageView followStartSecond;
        @BindView(R.id.follow_star_relas)
        RelativeLayout followStarRelas;
        @BindView(R.id.follow_start_third)
        CircleImageView followStartThird;
        @BindView(R.id.follow_star_num)
        TextView followStarNum;
        @BindView(R.id.follow_star_rela)
        RelativeLayout followStarRela;
        @BindView(R.id.attent_share)
        FrameLayout attentShare;
        @BindView(R.id.attent_like)
        ImageView attentLike;
        @BindView(R.id.attent_like_farm)
        FrameLayout attentLikeFarm;
        @BindView(R.id.attent_comment)
        FrameLayout attentComment;
        @BindView(R.id.follow_comment_num)
        TextView followCommentNum;
        @BindView(R.id.follow_first_comment)
        TextView followFirstComment;
        @BindView(R.id.follow_second_comment)
        TextView followSecondComment;
        @BindView(R.id.follow_comment_lin)
        LinearLayout followCommentLin;
        @BindView(R.id.follow_video_thumb)
        ImageView followVideoThumb;
        /*@BindView(R.id.follow_jzplayer)
        JZVideoPlayerStandard jzVideoPlayerStandard;*/




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface onClickLinstener {
        void onClick(int posstion,String type);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }

    //左移动画
    public ObjectAnimator leftAnima(View view){
   /*     Animation translateAnimation = new TranslateAnimation(0, DPUtils.dp2px(14), 0, 0);//设置平移的起点和终点
        translateAnimation.setDuration(500);//动画持续的时间为10s
        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
        translateAnimation.setFillAfter(true);//不回到起始位置
        //如果不添加setFillEnabled和setFillAfter则动画执行结束后会自动回到远点
        //translateAnimation.setAnimationListener(context);
        view.setAnimation(translateAnimation);//给imageView添加的动画效果
        translateAnimation.startNow();//动画开始执行 放在最后即可*/
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat( view, "translationX", 0,  DPUtils.dp2px(14));
        //执行事件
        alphaAnim.setDuration(200);

        alphaAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);


            }
        });

        //延迟
        alphaAnim.start();
        return  alphaAnim;
    }
    //左移动画
    public ObjectAnimator rightAnima(View view){
        /*Animation translateAnimation = new TranslateAnimation( DPUtils.dp2px(14),0, 0, 0);//设置平移的起点和终点
        translateAnimation.setDuration(500);//动画持续的时间为10s
        translateAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
        translateAnimation.setFillAfter(true);//不回到起始位置
        //如果不添加setFillEnabled和setFillAfter则动画执行结束后会自动回到远点
        //translateAnimation.setAnimationListener(context);
        view.setAnimation(translateAnimation);//给imageView添加的动画效果
        translateAnimation.startNow();//动画开始执行 放在最后即可*/
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat( view, "translationX", DPUtils.dp2px(14),0  );
        //执行事件
        alphaAnim.setDuration(200);

        alphaAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);


            }
        });

        //延迟
        alphaAnim.start();
        return  alphaAnim;
    }
}
