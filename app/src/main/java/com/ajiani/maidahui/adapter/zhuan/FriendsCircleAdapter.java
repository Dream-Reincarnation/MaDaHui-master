package com.ajiani.maidahui.adapter.zhuan;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.zhuan.FriendsCircleSel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsCircleAdapter extends RecyclerView.Adapter<FriendsCircleAdapter.ViewHolder> {
    public ArrayList<FriendsCircleSel> mList;

    private Context context;
    private onClickLinstener onClick;

    public FriendsCircleAdapter(ArrayList<FriendsCircleSel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.friendscircle_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getImgUrl()).into(holder.friendscircleImg);
        if(mList.get(position).isSel()){
            holder.friendscircleSel.setImageResource(R.mipmap.friendscirclesel);
        }else{
            holder.friendscircleSel.setImageResource(R.mipmap.friendscircleunsel);
        }
        boolean sel = mList.get(position).isSel();
        holder.friendscircleSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sel){
                    holder.friendscircleSel.setImageResource(R.mipmap.friendscircleunsel);
                    mList.get(position).setSel(false);
                    notifyDataSetChanged();
                }else{
                    holder.friendscircleSel.setImageResource(R.mipmap.friendscirclesel);
                    mList.get(position).setSel(true);
                    notifyDataSetChanged();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.friendscircle_img)
        ImageView friendscircleImg;
        @BindView(R.id.friendscircle_sel)
        ImageView friendscircleSel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
