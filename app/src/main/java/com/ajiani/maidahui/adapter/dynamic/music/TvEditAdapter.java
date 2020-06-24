package com.ajiani.maidahui.adapter.dynamic.music;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DownloadUtil;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.activity.dynamic.MusicActivity;
import com.ajiani.maidahui.bean.dynamic.music.MusicBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvEditAdapter extends RecyclerView.Adapter<TvEditAdapter.ViewHolder> {

    public ArrayList<MusicBean.DataBean> mList;
    private Context context;
    private onClickLinstener OnClick;
    boolean isShow;

    public TvEditAdapter(ArrayList<MusicBean.DataBean> mList, boolean isShow) {
        this.mList = mList;
        this.isShow = isShow;
    }

    public TvEditAdapter(ArrayList<MusicBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            context = parent.getContext();
            View inflate = LayoutInflater.from(context).inflate(R.layout.tvedit_item, parent, false);
            return new ViewHolder(inflate);


    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


                if(position==0){
                    if(isShow) {
                        holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.music_bg));
                        holder.tvFa.setBackgroundDrawable(context.getResources().getDrawable(R.color.music_bg));
                        holder.tveditImg.setVisibility(View.GONE);
                        holder.tv.setText("更多");
                    }else{
                        holder.tveditImg.setVisibility(View.VISIBLE);
                        holder.tvFa.setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
                        Glide.with(context).load(mList.get(position).getThumb()).into(holder.tveditImg);
                        holder.tv.setText(mList.get(position).getName());
                        if (mList.get(position).isPlay()) {

                            holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.Thme));
                            holder.tv.setTextColor(context.getResources().getColor(R.color.Thme));

                        } else {
                            holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
                            holder.tv.setTextColor(context.getResources().getColor(R.color.white));
                        }
                    }
                }else{
                    if(isShow){
                        position=position-1;
                    }
                    holder.tveditImg.setVisibility(View.VISIBLE);
                    holder.tvFa.setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
                   Glide.with(context).load(mList.get(position).getThumb()).into(holder.tveditImg);
                   holder.tv.setText(mList.get(position).getName());
                   if (mList.get(position).isPlay()) {

                holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.Thme));
                holder.tv.setTextColor(context.getResources().getColor(R.color.Thme));

                } else {
                holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
                holder.tv.setTextColor(context.getResources().getColor(R.color.white));
                  }
                }

                holder.tveditMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JumpUtils.gotoActivity((Activity) context,MusicActivity.class,new Bundle(),1213);
                    }
                });

        int finalPosition = position;
        holder.tveditImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* if(isShow){
                        if(finalPosition==0){
                            JumpUtils.gotoActivity((Activity) context,MusicActivity.class,new Bundle(),1213);
                        }else{
                            if (mList.get(finalPosition).isPlay()) {
                                for (int i = 0; i < mList.size(); i++) {
                                    mList.get(i).setPlay(false);
                                }
                                holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
                                mList.get(finalPosition).setPlay(false);
                                holder.tv.setTextColor(context.getResources().getColor(R.color.white));
                                notifyDataSetChanged();
                            } else {
                                for (int i = 0; i < mList.size(); i++) {
                                    mList.get(i).setPlay(false);
                                }
                                holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.Thme));
                                mList.get(finalPosition).setPlay(true);
                                holder.tv.setTextColor(context.getResources().getColor(R.color.Thme));
                                notifyDataSetChanged();

                            }
                        }
                    }else{*/
                        if (mList.get(finalPosition).isPlay()) {
                            for (int i = 0; i < mList.size(); i++) {
                                mList.get(i).setPlay(false);
                            }
                            holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.white));
                            mList.get(finalPosition).setPlay(false);
                            holder.tv.setTextColor(context.getResources().getColor(R.color.white));
                            notifyDataSetChanged();
                        } else {
                            for (int i = 0; i < mList.size(); i++) {
                                mList.get(i).setPlay(false);
                            }
                            holder.tveditFarm.setBackgroundDrawable(context.getResources().getDrawable(R.color.Thme));
                            mList.get(finalPosition).setPlay(true);
                            holder.tv.setTextColor(context.getResources().getColor(R.color.Thme));
                            notifyDataSetChanged();

                        }
                  //  }

                    if (OnClick != null) {
                        OnClick.onClick(finalPosition, "");
                    }

                }
            });


    }

    @Override
    public int getItemCount() {
        if(isShow){
            return mList.size()+1;
        }else{
            return mList.size();
        }

    }

    public void addAll(ArrayList<MusicBean.DataBean> recommed, boolean b) {
        mList.addAll(recommed);
        isShow=b;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvedit_img)
        ImageView tveditImg;
        @BindView(R.id.tvedit_more)
        ImageView tveditMore;
        @BindView(R.id.tvedit_farm)
        FrameLayout tveditFarm;
        @BindView(R.id.tvedit_tv)
        TextView tv;
        @BindView(R.id.tv_fa)
        FrameLayout tvFa;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

     public interface onClickLinstener{
             void onClick(int posstion,String path);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.OnClick=onClickLinstener;
         }
}
