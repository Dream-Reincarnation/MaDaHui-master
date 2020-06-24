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

public class ChatStarAdater extends RecyclerView.Adapter<ChatStarAdater.ViewHolder> {

    int count = 8;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.chatstar_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(holder.chatstarmHead);

        if (count == 4) {
            holder.chatstarmLin.setVisibility(View.VISIBLE);
            Glide.with(context).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(holder.chatstarimg1);
            Glide.with(context).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(holder.chatstarimg2);
            Glide.with(context).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(holder.chatstarimg3);
        }else{
            holder.chatstarmLin.setVisibility(View.GONE);
        }
        holder.chatstarmDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return count;
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chatstarm_head)
        ImageView chatstarmHead;
        @BindView(R.id.chatstarm_name)
        TextView chatstarmName;
        @BindView(R.id.chatstarm_text)
        TextView chatstarmText;
        @BindView(R.id.chatstarm_time)
        TextView chatstarmTime;
        @BindView(R.id.chatstarm_video)
        ImageView chatstarmVideo;
        @BindView(R.id.chatstarm_del)
        LinearLayout chatstarmDel;
        @BindView(R.id.chatstartmlin)
        LinearLayout chatstarmLin;
        @BindView(R.id.chatstarimg1)
        ImageView chatstarimg1;
        @BindView(R.id.chatstarimg2)
        ImageView chatstarimg2;
        @BindView(R.id.chatstarimg3)
        ImageView chatstarimg3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
