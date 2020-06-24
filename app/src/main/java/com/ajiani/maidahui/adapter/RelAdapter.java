package com.ajiani.maidahui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RelAdapter extends RecyclerView.Adapter<RelAdapter.ViewHolder> {


    private Context context;
    private TXVodPlayer mVodPlayer;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.rel_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//mPlayerView 即step1中添加的界面 view

//创建 player 对象
        mVodPlayer = new TXVodPlayer(context);
//关键 player 对象与界面 view
        mVodPlayer.setPlayerView(holder.videoView);
        String url = "https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/cf/9dd312e309363525371b3376277ea1.mp4";
        mVodPlayer.startPlay(url);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_view)
        TXCloudVideoView videoView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
