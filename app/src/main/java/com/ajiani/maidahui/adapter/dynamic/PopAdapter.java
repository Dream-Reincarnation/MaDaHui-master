package com.ajiani.maidahui.adapter.dynamic;

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
import com.ajiani.maidahui.bean.dynamic.LocalMedia;
import com.ajiani.maidahui.bean.dynamic.LocalMediaFolder;
import com.bumptech.glide.Glide;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopAdapter extends RecyclerView.Adapter<PopAdapter.ViewHolder> {

    public ArrayList<LocalMediaFolder> mList;
    private List<LocalMedia> images = new ArrayList<>();
    private Context context;
    private PopAdapter.onClickLinstener onClickLinstener;

    public PopAdapter(ArrayList<LocalMediaFolder> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.popadapter_item, parent, false);
        return new ViewHolder(inflate);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocalMediaFolder localMediaFolder = mList.get(position);
        holder.popadaName.setText(localMediaFolder.getName());

        holder.popadaNum.setText(localMediaFolder.getImageNum()+"");
        Glide.with(context).load(localMediaFolder.getFirstImagePath()).into(holder.popadaImg);
        //选中某一个相册
        holder.popadaLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickLinstener!=null){
                    onClickLinstener.onClick(position);
                }
            }
        });

    }
    public void bindImagesData(List<LocalMedia> images) {
        this.images = images == null ? new ArrayList<>() : images;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.popada_img)
        ImageView popadaImg;
        @BindView(R.id.popada_lin)
        LinearLayout popadaLin;
        @BindView(R.id.popada_name)
        TextView popadaName;
        @BindView(R.id.popada_num)
        TextView popadaNum;
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
