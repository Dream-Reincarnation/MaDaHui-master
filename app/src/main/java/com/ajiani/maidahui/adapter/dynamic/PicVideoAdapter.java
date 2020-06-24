package com.ajiani.maidahui.adapter.dynamic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.TimeUtils;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.activity.dynamic.txrecord.PictureSelActivity;
import com.ajiani.maidahui.bean.dynamic.PicVideoBean;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PicVideoAdapter extends RecyclerView.Adapter<PicVideoAdapter.ViewHolder> {

    public ArrayList<PicVideoBean> mList;
    private Context context;
    public PictureSelActivity activity;
    private PicVideoAdapter.onClickLinstener onClickLinstener;
    public boolean isVideo;

    public PicVideoAdapter(ArrayList<PicVideoBean> mList, Activity activity, boolean isVideo) {
        this.mList = mList;
        this.activity = (PictureSelActivity) activity;
        this.isVideo = isVideo;
    }

    public PicVideoAdapter(ArrayList<PicVideoBean> mList, Activity activity) {
        this.mList = mList;
        this.activity = (PictureSelActivity) activity;
    }

    public PicVideoAdapter(ArrayList<PicVideoBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.picvideo_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(isVideo){
            int duration = FileUtils.getDuration(mList.get(position).getPath());
            holder.picvideoTime.setText(TimeUtils.timeParse(duration) +"");
        }


        Glide.with(context).load(mList.get(position).getPath()).into(holder.picvideoThumb);
        if(mList.get(position).isSel()){
            holder.picvideoCheck.setSelected(true);
            holder.picvideoCheck.setText(mList.get(position).getNum()+"");
        }else{
            holder.picvideoCheck.setSelected(false);
            holder.picvideoCheck.setText("");
        }
        //选择图片.取消时 选择时数字的变化
        holder.picvideoCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.pivcideos.size()==9){
                    Toast.makeText(context, "最多选取9张", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if(holder.picvideoCheck.isSelected()){
                    activity.pivcideos.remove(mList.get(position));
                    holder.picvideoCheck.setSelected(false);
                    mList.get(position).setSel(false);
                    holder.picvideoCheck.setText("");
                    //刷新选择时的数字
                        for (int i = 0; i < activity.pivcideos.size(); i++) {
                            activity.pivcideos.get(i).setNum(i+1);
                        }

                        for (int i=0;i<activity.pivcideos.size();i++){
                            for (int j = 0; j < mList.size(); j++) {
                                String picPath = activity.pivcideos.get(i).getPath();
                                String s = mList.get(j).getPath();
                                if(picPath.equals(s)){
                                    mList.get(j).setSel(true);
                                    mList.get(j).setNum(activity.pivcideos.get(i).getNum());
                                }
                            }
                        }
                    notifyDataSetChanged();
                }else{
                    holder.picvideoCheck.setSelected(true);
                    mList.get(position).setSel(true);
                    mList.get(position).setNum(activity.pivcideos.size()+1);
                    activity.pivcideos.add(mList.get(position));
                    holder.picvideoCheck.setText(activity.pivcideos.size()+"");

                }
                if(onClickLinstener!=null){
                    onClickLinstener.onClick(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.picvideo_thumb)
        ImageView picvideoThumb;
        @BindView(R.id.picvideo_check)
        TextView picvideoCheck;
        @BindView(R.id.picvideo_time)
        TextView picvideoTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClickLinstener =onClickLinstener;
         }
}
