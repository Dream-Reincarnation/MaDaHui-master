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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineDynaAdapter extends RecyclerView.Adapter<MineDynaAdapter.ViewHolder> {

    public ArrayList<VideoInfoBean.DataBean> mList;
    private Context context;
    private onClickLinstener onClick;

    public MineDynaAdapter(ArrayList<VideoInfoBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.minedyna_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getThumb()).apply(new RequestOptions().circleCrop()).into(holder.minedynaHead);
        holder.minedynaStar.setText(mList.get(position).getStar()+"收藏");
        holder.minedynaTime.setText(mList.get(position).getCreate_time());
        holder.minedynaEarning.setText(mList.get(position).getGift()+"收益");
        if(mList.get(position).getVideo_type().equals("video")){
            holder.minedynaType.setText("视频");
        }else{
            holder.minedynaType.setText("图片");
        }
        holder.minedynaLin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击去看视频
                if(onClick!=null){
                    onClick.onClick(position);
                }
            }
        });

    }

     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClick=onClickLinstener;
         }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.minedyna_head)
        ImageView minedynaHead;
        @BindView(R.id.minedyna_type)
        TextView minedynaType;
        @BindView(R.id.minedyna_star)
        TextView minedynaStar;
        @BindView(R.id.minedyna_earning)
        TextView minedynaEarning;
        @BindView(R.id.minedyna_time)
        TextView minedynaTime;
        @BindView(R.id.minedyna_lin2)
        LinearLayout minedynaLin2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
