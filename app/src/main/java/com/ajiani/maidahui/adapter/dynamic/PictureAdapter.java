package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.PicVideoBean;
import com.bumptech.glide.Glide;
;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    public ArrayList<PicVideoBean> mList;

    private Context context;
    private PictureAdapter.onClickLinstener onClickLinstener;

    public PictureAdapter(ArrayList<PicVideoBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.picture_bottom_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(mList.get(position).getPath()).into(holder.pictureImg);
        holder.pictureDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        @BindView(R.id.picture_img)
        ImageView pictureImg;
        @BindView(R.id.picture_del)
        FrameLayout pictureDel;
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
