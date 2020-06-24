package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.CompleteBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAdapter extends RecyclerView.Adapter {
    public ArrayList<CompleteBean.DataBean> mList;
    private Context context;
    private onClickLinstener OnClick;
    public ArrayList<CompleteBean.DataBean> list = new ArrayList<>();

    public AddAdapter(ArrayList<CompleteBean.DataBean> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.add_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;


        if (mList.get(position).isSel()) {
            holder1.shopCheck.setImageResource(R.mipmap.addshop_sel);
        } else {
            holder1.shopCheck.setImageResource(R.mipmap.addshop_unsel);
        }
        Glide.with(context).load(mList.get(position).getThumb()).into(holder1.shopImg);
        holder1.shopName.setText(mList.get(position).getTitle());
        holder1.shopPrice.setText("￥" + mList.get(position).getShop_price());
        holder1.shopMoney.setText("佣金￥"+mList.get(position).getShare_price());
        holder1.shopImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if(mList.get(position).isSel()){
                    mList.get(position).setSel(false);
                    holder1.shopCheck.setImageResource(R.mipmap.addshop_unsel);
                }else{
                    mList.get(position).setSel(true);
                    holder1.shopCheck.setImageResource(R.mipmap.addshop_sel);
                }*/

                if (OnClick != null) {

                    OnClick.onClick(position);
                }
            }
        });
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.OnClick = onClickLinstener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_img)
        ImageView shopImg;
        @BindView(R.id.shop_check)
        ImageView shopCheck;
        @BindView(R.id.shop_name)
        TextView shopName;
        @BindView(R.id.shop_price)
        TextView shopPrice;
        @BindView(R.id.shop_money)
        TextView shopMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
