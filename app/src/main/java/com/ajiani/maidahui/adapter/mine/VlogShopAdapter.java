package com.ajiani.maidahui.adapter.mine;

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
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VlogShopAdapter extends RecyclerView.Adapter<VlogShopAdapter.ViewHolder> {
    public ArrayList<VideoInfoBean.DataBean.GoodsBean> mList;
    int a;
    private Context context;

    public VlogShopAdapter(ArrayList<VideoInfoBean.DataBean.GoodsBean> mList, int a) {
        this.mList = mList;
        this.a=a;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.vlog_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoInfoBean.DataBean.GoodsBean goodsBean = mList.get(position);
        Glide.with(context).load(goodsBean.getThumb()).into(holder.vlogshopThumb);
        holder.vlogshopTitle.setText(goodsBean.getName());
        holder.vlogshopPrice.setText("￥"+goodsBean.getPrice());
        //判断已售多少件
        int sales = goodsBean.getSales();
        if(sales>0){
            holder.vlogshopAlerdy.setText("已售"+sales+"件");
        }
        holder.vlogshopLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到商品详情
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vlogshop_lin)
        LinearLayout vlogshopLin;
        @BindView(R.id.vlogshop_thumb)
        ImageView vlogshopThumb;
        @BindView(R.id.vlogshop_title)
        TextView vlogshopTitle;
        @BindView(R.id.vlogshop_price)
        TextView vlogshopPrice;
        @BindView(R.id.vlogshop_alerdy)
        TextView vlogshopAlerdy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
