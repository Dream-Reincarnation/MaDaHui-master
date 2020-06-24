package com.ajiani.maidahui.adapter.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.TimeUtils;
import com.ajiani.maidahui.bean.chat.BoardCastMessageBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.commonsdk.statistics.common.MLog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatCommentAdapter extends RecyclerView.Adapter<ChatCommentAdapter.ViewHolder> {


    private Context context;
    public ArrayList<BoardCastMessageBean.DataBean> list;
    private onClickLinstener onClick;
    private LogisticsAdapter.onLongClickLinstener onLongClick;

    public ChatCommentAdapter(ArrayList<BoardCastMessageBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View inflate = LayoutInflater.from(context).inflate(R.layout.chatcom_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder.chatcommHead);
        holder.chatcommName.setText(list.get(position).getNickname());
        holder.chatcommText.setText(list.get(position).getContent());
        String dateToString = TimeUtils.getDateToString((long) list.get(position).getTimestamp() * 1000);
        holder.chatcommTime.setText("在发布作品时提到了你   " + dateToString);
        Glide.with(context).load(list.get(position).getExtra().getThumb()).into(holder.chatcommVideo);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(position);
            }
        });
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("wxy  ", "onLongClick: "+position);
                if(onLongClick!=null){
                    onLongClick.onLongClick(position);
                }
                return false;
            }
        });
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }
    public interface onLongClickLinstener{
        void onLongClick(int posstion);
    }
    public void setOnLongClickLinstener(LogisticsAdapter.onLongClickLinstener onClickLinstener) {
        this.onLongClick = onClickLinstener;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chatcomm_head)
        ImageView chatcommHead;
        @BindView(R.id.chatcomm_name)
        TextView chatcommName;
        @BindView(R.id.chatcomm_text)
        TextView chatcommText;
        @BindView(R.id.chatcomm_time)
        TextView chatcommTime;
        @BindView(R.id.chatcomm_video)
        ImageView chatcommVideo;
        @BindView(R.id.chatcomm_rela)
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
