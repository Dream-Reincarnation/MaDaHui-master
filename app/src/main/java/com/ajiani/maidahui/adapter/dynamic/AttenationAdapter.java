package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.FollowListBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttenationAdapter extends RecyclerView.Adapter<AttenationAdapter.ViewHolder> {
    public ArrayList<FollowListBean.DataBean> mList;

    private Context context;
    private onClickLinstener OnClick;

    public AttenationAdapter(ArrayList<FollowListBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.attention_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.attentionFollow.setBackgroundResource(R.drawable.ciecleblack);
        holder.attentionFollow.setTextColor(Color.BLACK);
        Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.attentionHead);
        holder.attentionName.setText(mList.get(position).getNickname());
        if(mList.get(position).getIs_follow_me()==0){
            holder.attentionFollow.setText("已关注");
        }else{
            holder.attentionFollow.setText("互相关注");
        }
        holder.attentionFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int is_follow_me = mList.get(position).getIs_follow_me();
                if(is_follow_me==0){
                    if(mList.get(position).getIs_follow()==0){

                        holder.attentionFollow.setBackgroundResource(R.drawable.ciecleblack);
                        holder.attentionFollow.setText("已关注");
                        holder.attentionFollow.setTextColor(Color.BLACK);
                        mList.get(position).setIs_follow(1);
                    }else{
                        holder.attentionFollow.setText("关注");
                        holder.attentionFollow.setTextColor(Color.WHITE);
                        holder.attentionFollow.setBackgroundResource(R.drawable.theml);
                        mList.get(position).setIs_follow(0);
                    }
                }else{
                    if(mList.get(position).getIs_follow()==0){

                        holder.attentionFollow.setBackgroundResource(R.drawable.ciecleblack);
                        holder.attentionFollow.setText("互相关注");
                        holder.attentionFollow.setTextColor(Color.BLACK);
                        mList.get(position).setIs_follow(1);
                    }else{
                        holder.attentionFollow.setText("关注");
                        holder.attentionFollow.setTextColor(Color.WHITE);
                        holder.attentionFollow.setBackgroundResource(R.drawable.theml);
                        mList.get(position).setIs_follow(0);
                    }
                }


                if(OnClick!=null){


                    OnClick.onClick(position);
                }
            }
        });

    }

     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.OnClick=onClickLinstener;
         }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.attention_head)
        ImageView attentionHead;
        @BindView(R.id.attention_name)
        TextView attentionName;
        @BindView(R.id.attention_follow)
        TextView attentionFollow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
