package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.StoreBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    public ArrayList<StoreBean.DataBean> mList;

    private Context context;
    private onClickLinstener onClick;

    public StoreAdapter(ArrayList<StoreBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.store_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(mList.get(position).getThumb()).apply(new RequestOptions().circleCrop()).into(holder.storeImg);
        holder.storeName.setText(mList.get(position).getName());
        Glide.with(context).load(mList.get(position).getType_icon2()).into(holder.storeType);
        holder.storeIntroduce.setText(mList.get(position).getContent());
        holder.rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件
                onClick.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.store_img)
        ImageView storeImg;
        @BindView(R.id.store_rela)
        RelativeLayout rela;
        @BindView(R.id.store_name)
        TextView storeName;
        @BindView(R.id.store_type)
        ImageView storeType;
        @BindView(R.id.store_introduce)
        TextView storeIntroduce;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }
}
