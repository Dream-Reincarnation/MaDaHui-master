package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.RecommedBean;
import com.ajiani.maidahui.bean.dynamic.CommodityBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoCommAdapter extends RecyclerView.Adapter {
    public ArrayList<CommodityBean.DataBean> mList;
    private Context context;
    private onClickLinstener onClick;

    public VideoCommAdapter(ArrayList<CommodityBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.videocomm_item, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         ViewHolder holder1= (ViewHolder) holder;
        Glide.with(context).load(mList.get(position).getThumb());
        holder1.videcommStitle.setText(mList.get(position).getTitle());
        holder1.videocommName.setText(mList.get(position).getTitle());

        holder1.videocommPrice.setText("￥"+mList.get(position).getMarket_price());

        holder1.videocommcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //删除这个商品
                mList.remove(position);
                notifyDataSetChanged();
                if(mList.size()==0){
                    if(onClick!=null){
                          onClick.onClick(position);
                    }
                }
            }

        });
        holder1.videocommLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClick!=null){
                  //  onClick.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClick=onClickLinstener;
         }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.videocomm_img)
        ImageView videocommImg;
        @BindView(R.id.videocomm_cancle)
        ImageView videocommcancle;
        @BindView(R.id.videocomm_name)
        TextView videocommName;
        @BindView(R.id.videcomm_stitle)
        TextView videcommStitle;
        @BindView(R.id.videocomm_price)
        TextView videocommPrice;
        @BindView(R.id.videocomm_lin)
        LinearLayout videocommLin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
