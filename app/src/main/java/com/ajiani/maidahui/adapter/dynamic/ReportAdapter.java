package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.photo.PictureUtils;
import com.ajiani.maidahui.bean.ReportBean;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    public ArrayList<ReportBean> mList;

    private Context context;
    private onClickLinstener onClick;
    private onClickImgLinstener onImgClick;

    public ReportAdapter(ArrayList<ReportBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.report_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReportBean reportBean = mList.get(position);
        if (reportBean.isEnd()) {
            holder.reportVideoImgfarm.setVisibility(View.GONE);
        } else {
            holder.reportVideoImgfarm.setVisibility(View.VISIBLE);
            Glide.with(context).load(reportBean.getPicUrl()).into(holder.reportVideoImg);
        }
        holder.reportVideoFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除图片

                if(onClick!=null){
                    onClick.onClick(position);
                }

            }
        });
        holder.reportVideoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到相册
               if(onImgClick!=null){
                   onImgClick.onClick(position);
               }
            }
        });

    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }
    public interface onClickImgLinstener {
        void onClick(int posstion);
    }

    public void setOnClickImgLinstener(onClickImgLinstener onClickLinstener) {
        this.onImgClick = onClickLinstener;
    }

    @Override
    public int getItemCount() {
        if(mList.size()<=3){
            return mList.size();
        }else{
            return 3;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.report_video_imgnum)
        TextView reportVideoImgnum;
        @BindView(R.id.report_video_card)
        CardView reportVideoCard;
        @BindView(R.id.report_video_img)
        ImageView reportVideoImg;
        @BindView(R.id.report_video_imgfarm)
        CardView reportVideoImgfarm;
        @BindView(R.id.report_false)
        FrameLayout reportVideoFalse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
