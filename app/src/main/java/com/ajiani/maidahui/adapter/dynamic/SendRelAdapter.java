package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendRelAdapter extends RecyclerView.Adapter {
    public ArrayList<String> mList;
    private Context context;

    public SendRelAdapter(ArrayList<String> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false);
        return new ViewHolder(inflate);
    }

    public void setData(ArrayList<String> mList){
        mList.clear();
        mList.addAll(mList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           ViewHolder holder1= (ViewHolder) holder;
           //Glide.with(context).load().into(holder1.sendImg);
    }

    @Override
    public int getItemCount() {

        return 3;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.send_img)
        ImageView sendImg;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
