package com.ajiani.maidahui.adapter.chat;

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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InteractAdapter extends RecyclerView.Adapter<InteractAdapter.ViewHolder> {

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.inter_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(holder.interactmHead);
        Glide.with(context).load(R.mipmap.photo).into(holder.interactmThmb);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.interactm_head)
        ImageView interactmHead;
        @BindView(R.id.interactm_name)
        TextView interactmName;
        @BindView(R.id.interactm_time)
        TextView interactmTime;
        @BindView(R.id.interactm_thmb)
        ImageView interactmThmb;
        @BindView(R.id.interactm_text)
        TextView interactmText;
        @BindView(R.id.interactm_return)
        TextView interactmReturn;
        @BindView(R.id.interactm_lin)
        LinearLayout interactmLin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
