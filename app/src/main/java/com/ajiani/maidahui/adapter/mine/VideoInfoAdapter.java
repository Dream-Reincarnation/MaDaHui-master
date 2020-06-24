package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.activity.dynamic.ShopActivity;
import com.ajiani.maidahui.activity.mine.WebManagerActivity;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoInfoAdapter extends RecyclerView.Adapter {
    public ArrayList<VideoInfoBean.DataBean.GoodsBean> mList;
    private Context context;

    public VideoInfoAdapter(ArrayList<VideoInfoBean.DataBean.GoodsBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.videoinfo, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder holder1= (ViewHolder) holder;
        Glide.with(context).load(mList.get(position).getThumb()).into(holder1.videoinfoImg);
        holder1.videoinfoText.setText(mList.get(position).getName());
        holder1.videoinfoprice.setText("￥"+mList.get(position).getPrice());
        holder1.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder1.videoinfoBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle  bundle = new Bundle();
                bundle.putString("type", "common");
                bundle.putString("title", "商品详情");
                bundle.putInt("shopId", mList.get(position).getGoods_id());
                JumpUtils.gotoActivity(context, WebManagerActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.videoinfolin)
        LinearLayout linearLayout;
        @BindView(R.id.videoinfo_img)
        ImageView videoinfoImg;
        @BindView(R.id.videoinfo_text)
        TextView videoinfoText;
        @BindView(R.id.videoinfo_price)
        TextView videoinfoprice;
        @BindView(R.id.videoinfo_gobuy)
        TextView videoinfoBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
