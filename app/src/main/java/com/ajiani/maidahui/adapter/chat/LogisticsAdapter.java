package com.ajiani.maidahui.adapter.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.TimeUtils;
import com.ajiani.maidahui.bean.chat.ServiceListBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogisticsAdapter extends RecyclerView.Adapter<LogisticsAdapter.ViewHolder> {

    public ArrayList<ServiceListBean.DataBean> mList;

    private Context context;
    private onClickLinstener onClick;
    private onLongClickLinstener onLongClick;

    public LogisticsAdapter(ArrayList<ServiceListBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.logistics_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder.logisticsmHead);
        String dateToString = TimeUtils.getDateToString((long) mList.get(position).getTimestamp() * 1000);
        holder.logisticsmTime.setText(dateToString);
        holder.logisticsmShopname.setText(mList.get(position).getNickname());
        if (mList.get(position).getMsg_type().equals("orderPaySuccess")) {
            holder.logisticsmContent.setText(mList.get(position).getContent());
        } else {
            holder.logisticsmContent.setText(mList.get(position).getContent());
        }
        holder.logisticsmAddress.setText(mList.get(position).getExtra().getAddress());
        holder.logisticsmPhone.setText(mList.get(position).getExtra().getReceiver_phone());
        holder.logisticsmExpress.setText(mList.get(position).getExtra().getOrder_no());
        holder.logisticsName.setText(mList.get(position).getExtra().getLogistics_name());
        holder.logisticsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClick != null) {
                    onClick.onClick(position);
                }
            }
        });

        holder.logisticsCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onLongClick!=null){
                    onLongClick.onLongClick(position);
                }
                return true;
            }
        });

        //      holder.logisticsmExpress.setText(mList.get(position).getExtra().getOrder_no());
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
    public void setOnLongClickLinstener(onLongClickLinstener onClickLinstener) {
        this.onLongClick = onClickLinstener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.logisticsm_time)
        TextView logisticsmTime;
        @BindView(R.id.logisticsm_head)
        ImageView logisticsmHead;
        @BindView(R.id.logisticsm_shopname)
        TextView logisticsmShopname;
        @BindView(R.id.logisticsm_content)
        TextView logisticsmContent;
        @BindView(R.id.logis_address)
        TextView logisticsmAddress;
        @BindView(R.id.logisticsm_express)
        TextView logisticsmExpress;
        @BindView(R.id.logisticsm_phone)
        TextView logisticsmPhone;
        @BindView(R.id.logistics_info)
        LinearLayout logisticsInfo;
        @BindView(R.id.logisticsm_lin)
        LinearLayout logisticsmLin;
        @BindView(R.id.logistics_card)
        CardView logisticsCard;

        @BindView(R.id.logisticsm_name)
        TextView logisticsName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
