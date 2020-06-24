package com.ajiani.maidahui.adapter.dynamic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.VideoListBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttentAdapter  extends RecyclerView.Adapter<AttentAdapter.ViewHolder> {
    public ArrayList<VideoInfoBean.DataBean> mList;
    private Context context;
    private onLikeListener onLike;
    boolean b;
    private onHeadListener onHead;


    public AttentAdapter(ArrayList<VideoInfoBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.recommed_rel, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        String video_type = mList.get(position).getVideo_type();
        String is_star = mList.get(position).getIs_star();
        if (video_type.contains("video")) {
            holder1.recommedVideo.setVisibility(View.VISIBLE);
        }

        if (is_star.equals("0")) {
            b = false;
        } else {
            b = true;
        }
      /*  Glide.with(context)
            //    .asBitmap()
                .load(mList.get(position).getThumb())
                .into(holder1.recommedImg);*/
        Glide.with(context).asBitmap().load(mList.get(position).getThumb()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                int screenWidth = ((Activity) holder1.recommedImg.getContext()).getWindowManager().getDefaultDisplay().getWidth();
                int width = resource.getWidth();
                int height = resource.getHeight();
                float imgWidth= (float) (screenWidth/2.0);
                float imgHeight=height*(imgWidth/width);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder1.recommedImg.getLayoutParams();
                //设置图片的相对于屏幕的宽高比
                params.width= (int) imgWidth;
                params.height = (int) imgHeight;
                holder1.recommedImg.setLayoutParams(params);
                holder1.recommedImg.setImageBitmap(resource);
            }
        });





        holder1.recommedImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLike != null) {
                    onLike.onLike(position);
                }
            }
        });

        holder1.recommedHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onHead != null) {
                    onHead.onHead(position);
                }
            }
        });
        if (is_star.equals("0")) {
            holder1.recommedLike.setImageResource(R.mipmap.aixin);
        } else {
            holder1.recommedLike.setImageResource(R.mipmap.aixinn);
        }
        //  Glide.with(context).load(R.mipmap.photo).into(holder1.recommedImg);
        Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder1.recommedHead);
        holder1.recommedName.setText(mList.get(position).getNickname());
        holder1.recommedTitle.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface onLikeListener{
        void onLike(int posstion);
    }
    public void setOnLike(onLikeListener onLike){
        this.onLike=onLike;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recommed_img)
        ImageView recommedImg;
        @BindView(R.id.recommed_video)
        ImageView recommedVideo;
        @BindView(R.id.recommed_title)
        TextView recommedTitle;
        @BindView(R.id.recommed_head)
        ImageView recommedHead;
        @BindView(R.id.recommed_name)
        TextView recommedName;
        @BindView(R.id.recommed_like)
        ImageView recommedLike;
        @BindView(R.id.recommed_likenum)
        TextView recommedLikenum;
             public ViewHolder(@NonNull View itemView) {
                 super(itemView);
                 ButterKnife.bind(this, itemView);
             }
         }


    public interface onHeadListener {
        void onHead(int posstion);
    }

    public void setOnHead(onHeadListener onHead) {
        this.onHead = onHead;
    }





}
