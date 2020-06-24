package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectRelAdapter extends RecyclerView.Adapter {
    public ArrayList<VideoInfoBean.DataBean> mList;


    private Context mContext;
    private onClickLinstener onClick;

    //设置一下状态栏
    public ProjectRelAdapter(ArrayList<VideoInfoBean.DataBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.mine_project, null, false);
//                LayoutInflater.from(mContext).inflate(R.layout.recommed_rel, null, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        VideoInfoBean.DataBean dataBean = mList.get(position);
        if(dataBean.getVideo_type().equals("video")){
          holder1.isPhoto.setImageResource(R.mipmap.mine_pause);
          holder1.isVideo.setImageResource(R.mipmap.mine_video);
        }else{
            holder1.isPhoto.setImageResource(R.mipmap.mine_photo);
            holder1.isVideo.setImageResource(R.mipmap.mine_look);
        }
       Glide.with(mContext).load(dataBean.getThumb()).into(holder1.vlogThumb);
       holder1.vlogPlayerNum.setText(dataBean.getCount()+"");
        List<VideoInfoBean.DataBean.GoodsBean> goods = dataBean.getGoods();
        if(goods.size()>0){
            holder1.shop.setVisibility(View.VISIBLE);
            if(goods.size()==1){
                holder1.vlogFarme1.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(goods.get(0).getThumb()).into(holder1.vlogShop1);


            }else if(goods.size()==2){
                holder1.vlogFarme1.setVisibility(View.VISIBLE);
                holder1.vlogFarme2.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(goods.get(0).getThumb()).into(holder1.vlogShop1);
                Glide.with(mContext).load(goods.get(1).getThumb()).into(holder1.vlogShop2);
            }else{
                holder1.vlogFarme1.setVisibility(View.VISIBLE);
                holder1.vlogFarme2.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(goods.get(0).getThumb()).into(holder1.vlogShop1);
                Glide.with(mContext).load(goods.get(1).getThumb()).into(holder1.vlogShop2);
            }
        }


        holder1.vlogThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClick!=null){
                    onClick.onClick(position);
                }
            }
        });
    }


    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vlog_thumb)
        ImageView vlogThumb;
        @BindView(R.id.is_vlogphoto)
        ImageView isVideo;

        @BindView(R.id.vlog_farme1)
        FrameLayout vlogFarme1;
        @BindView(R.id.vlog_farme2)
        FrameLayout vlogFarme2;
        @BindView(R.id.is_vlogvideo)
        ImageView isPhoto;
        @BindView(R.id.vlog_player_num)
        TextView vlogPlayerNum;
        @BindView(R.id.vlog_shop1)
        ImageView vlogShop1;
        @BindView(R.id.vlog_shop2)
        ImageView vlogShop2;
        @BindView(R.id.mine_shop)
        ImageView shop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //mImg = itemView.findViewById(R.id.project);
        }
    }
}
