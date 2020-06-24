package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynaSearAdapter extends RecyclerView.Adapter<DynaSearAdapter.ViewHolder> {
    public ArrayList<VideoInfoBean.DataBean> mList;
    private RecommedAdapter.onHeadListener onHead;

    public DynaSearAdapter(ArrayList<VideoInfoBean.DataBean> mList) {
        this.mList = mList;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.dynasear_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder holder1=holder;
        String video_type = mList.get(position).getVideo_type();
        String is_star = mList.get(position).getIs_star();

//        Glide.with(context).load(mList.get(position).getThumb()).into(holder1.recommedImg);
//        Glide.with(context).load(mList.get(position).getThumb()).into();
        Glide.with(context).asBitmap().load(mList.get(position).getThumb()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                int width = resource.getWidth();
                int height = resource.getHeight();

                int imageWidth = holder1.recommedImg.getWidth();
                int imageHeight = imageWidth;
                //2.等比例缩放,设置显示图片ImageView的宽和高, 固定的宽度,获取高度
//                                if (width > 0 && height > 0) {
//                                    imageHeight = imageWidth * height / width;
//                                }
                //2.等比例缩放,最高和最宽不能超过指定的宽和高
                if (width > R.dimen.xdp_174) {
                    imageWidth = R.dimen.xdp_174;
                    imageHeight = imageWidth * height / width;
                } else if (height > R.dimen.xdp_174) {
                    imageHeight = R.dimen.xdp_174;
                    imageWidth = width * imageHeight / height;
                } else {
                    imageWidth = width;
                    imageHeight = height;
                }


                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        imageWidth, imageHeight);
                holder1.recommedImg.setLayoutParams(lp);

            }
        });

        Glide.with(context)
                .asBitmap()
                .load(mList.get(position).getThumb())
                .into(holder1.recommedImg);

        holder1.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onHead!=null){
                    onHead.onHead(position);
                }
            }
        });
        //  Glide.with(context).load(R.mipmap.photo).into(holder1.recommedImg);
        Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder1.recommedHead);
        holder1.recommedName.setText(mList.get(position).getNickname());
        holder1.recommedTitle.setText(mList.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recommed_img)
        ImageView recommedImg;
        @BindView(R.id.rela)
        RelativeLayout rela;
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

    public interface onHeadListener{
        void onHead(int posstion);
    }
    public void setOnHead(RecommedAdapter.onHeadListener onHead){
        this.onHead=onHead;
    }
}
