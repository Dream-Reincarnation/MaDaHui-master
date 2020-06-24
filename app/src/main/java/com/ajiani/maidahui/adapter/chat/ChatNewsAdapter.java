package com.ajiani.maidahui.adapter.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatNewsAdapter extends RecyclerView.Adapter<ChatNewsAdapter.ViewHolder> {


    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.chatnews_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chatnews_status)
        TextView chatnewsStatus;
        @BindView(R.id.chatnews_time)
        TextView chatnewsTime;
        @BindView(R.id.chatnews_title)
        TextView chatnewsTitle;
        @BindView(R.id.chatnews_text)
        TextView chatnewsText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
