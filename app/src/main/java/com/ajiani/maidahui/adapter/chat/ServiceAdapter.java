package com.ajiani.maidahui.adapter.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.ItemSlideHelper;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.TimeUtils;
import com.ajiani.maidahui.bean.chat.ServiceListBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder>  {
    public ArrayList<ServiceListBean.DataBean> mList;
    private onClickLinstener onClick;

    RecyclerView mRecyclerView;
    public ServiceAdapter(ArrayList<ServiceListBean.DataBean> mList) {
        this.mList = mList;
    }

    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.service_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.serviceContent.setText(mList.get(position).getContent());

        holder.serviceCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("wxy", "onLongClick: sasdasdda");
                if(onClick!=null){
                    onClick.onClick(position);
                }
                return true;
            }
        }
    );

        String strTime = TimeUtils.getDateToString((long) mList.get(position).getTimestamp() * 1000);
        holder.serviceTime.setText(strTime);
        Glide.with(context).load(mList.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder.serviceHead);
        holder.serviceType.setText(mList.get(position).getMsg_type_name());
        holder.serviceContent.setText(mList.get(position).getContent());
        //判断消息类型
        String msg_type = mList.get(position).getMsg_type();
        switch (msg_type) {
            //抽奖
            case "addDduobao":
            case "addDduobaoSuccess":
                Glide.with(context).load(R.mipmap.chat_service_draw).into(holder.serviceShopimg);
                break;
                //拼团
            case "addGroup":
            case "addGroupSuccess":
                Glide.with(context).load(R.mipmap.chat_service_group).into(holder.serviceShopimg);
                break;
             //果园
            case "TreesSeedPay":
            case "TreesSeedSuccess":
            case "TreesOrderSuccess":
            case "TreesOrderShipping":
                Glide.with(context).load(R.mipmap.chat_service_tree).into(holder.serviceShopimg);
                break;
             //下单成功
            case "orderPaySuccess":
                Glide.with(context).load(R.mipmap.chat_service_send).into(holder.serviceShopimg);
                break;
             //发货成功
            case "orderShippingSuccess":
                Glide.with(context).load(R.mipmap.chat_service_receiver).into(holder.serviceShopimg);
                break;
        }
    }





    public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClick=onClickLinstener;
         }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.service_head)
        ImageView serviceHead;
        @BindView(R.id.service_type)
        TextView serviceType;
        @BindView(R.id.service_time)
        TextView serviceTime;
        @BindView(R.id.service_item_card)
        CardView serviceCard;
        @BindView(R.id.service_shopimg)
        ImageView serviceShopimg;
        @BindView(R.id.service_content)
        TextView serviceContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
