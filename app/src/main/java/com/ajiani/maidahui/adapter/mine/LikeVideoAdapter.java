package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
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
import com.ajiani.maidahui.bean.mine.LikeVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LikeVideoAdapter extends RecyclerView.Adapter<LikeVideoAdapter.ViewHolder> {
    public ArrayList<VideoInfoBean.DataBean> mList;

    private Context context;
    private onClickLinstener onClick;

    public LikeVideoAdapter(ArrayList<VideoInfoBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.mine_project, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoInfoBean.DataBean dataBean = mList.get(position);

        if(dataBean.getVideo_type().equals("video")){
            holder.isPhoto.setImageResource(R.mipmap.mine_ulike);
            holder.isVideo.setImageResource(R.mipmap.mine_video);
        }else{
            holder.isPhoto.setImageResource(R.mipmap.mine_photo);
            holder.isVideo.setImageResource(R.mipmap.mine_ulike);
        }
        if(!String.valueOf(dataBean.getAid()).equals("0")){
            Glide.with(context).load(dataBean.getThumb()).into(holder.vlogThumb);
        }else{
            holder.vlogThumb.setVisibility(View.GONE);
        }

        holder.vlogPlayerNum.setText(dataBean.getStar()+"");
        List<VideoInfoBean.DataBean.GoodsBean> goods = dataBean.getGoods();
        if(goods.size()>0){
            holder.shop.setVisibility(View.VISIBLE);
            if(goods.size()==1){
                holder.vlogFarme1.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.mipmap.photo).into(holder.vlogShop1);
            }else if(goods.size()==2){
                holder.vlogFarme1.setVisibility(View.VISIBLE);
                holder.vlogFarme2.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.mipmap.photo).into(holder.vlogShop1);
                Glide.with(context).load(R.mipmap.photo).into(holder.vlogShop2);
            }else{

            }
        }




        holder.vlogThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClick!=null){
                    onClick.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.i("wxy", "onBindViewHolder: "+mList.size());
        return mList.size();
    }

     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClick=onClickLinstener;
         }

    public class ViewHolder extends RecyclerView.ViewHolder {
     //   @BindView(R.id.project)
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
        }
    }
}
