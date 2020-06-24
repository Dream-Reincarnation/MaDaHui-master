package com.ajiani.maidahui.adapter.chat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.activity.mine.BillInfoActivity;
import com.ajiani.maidahui.activity.mine.CommentActivity;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.bean.sockets.OfferBean;
import com.ajiani.maidahui.bean.sockets.RedPackageBean;
import com.ajiani.maidahui.bean.sockets.ShopInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {



    private Context context;
    public ArrayList<MsgBean> mList;
    boolean isLeft;
    private onClickLinstener onClick;
    private onItemClickLinstener onItemClick;

    public ChatAdapter(ArrayList<MsgBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.chat_item, null, false);
        View inflate2 = LayoutInflater.from(context).inflate(R.layout.chat_redpack, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (mList.get(position).isLeft()) {
            holder.chatitemRightlin.setVisibility(View.GONE);
            holder.chatitemLeftlin.setVisibility(View.VISIBLE);
            Glide.with(context).load(mList.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder.chatitemLefthead);
            String action = mList.get(position).getAction();
            if (action != null && action.equals("8")) {
                holder.remarklin.setVisibility(View.GONE);
                holder.chatitemOfferlin.setVisibility(View.GONE);
                holder.chatitemLefttext.setVisibility(View.GONE);
                holder.redlinLeft.setVisibility(View.VISIBLE);
                String extra = new Gson().toJson(mList.get(position).getExtra());
                String[] split = extra.split(",");
                holder.redTitleleft.setText(mList.get(position).getCt());
                holder.redMoneyleft.setText(split[0]);
            } else if (action != null && action.equals("5")) {
                holder.chatitemLefttext.setVisibility(View.GONE);
                holder.redlinLeft.setVisibility(View.GONE);
                holder.chatitemOfferlin.setVisibility(View.GONE);
                holder.remarklin.setVisibility(View.VISIBLE);
            } else if (action != null && action.equals("7")) {
                holder.remarklin.setVisibility(View.GONE);
                holder.chatitemLefttext.setVisibility(View.GONE);
                holder.chatitemOfferlin.setVisibility(View.VISIBLE);
                holder.redlinLeft.setVisibility(View.GONE);
                String extra = new Gson().toJson(mList.get(position).getExtra());
                if(extra.length()>5){
                    OfferBean.ExtraBean offerBean = new Gson().fromJson(extra, OfferBean.ExtraBean.class);
                    if(offerBean.getTitle()!=null){
                        holder.shopsTitle.setText(offerBean.getTitle());
                    }
                    Glide.with(context).load(offerBean.getThumb()).into(holder.chatitemShop);
                    holder.shopsMoney.setText(offerBean.getPrice());
                }

            } else {
                holder.remarklin.setVisibility(View.GONE);
                holder.chatitemLefttext.setVisibility(View.VISIBLE);
                holder.chatitemOfferlin.setVisibility(View.GONE);
                holder.redlinLeft.setVisibility(View.GONE);
                holder.chatitemLefttext.setText(mList.get(position).getCt());
            }


        } else {
            holder.chatitemRightlin.setVisibility(View.VISIBLE);
            holder.chatitemLeftlin.setVisibility(View.GONE);

            Glide.with(context).load(mList.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(holder.chatitemRighthead);
            String action = mList.get(position).getAction();
            if (action != null && action.equals("3")) {

                String extra =  new Gson().toJson(mList.get(position).getExtra());
                if (extra!=null&&extra.length() > 2) {
                    holder.shoplin.setVisibility(View.VISIBLE);
                    holder.chatitemRighttext.setVisibility(View.GONE);
                    holder.redlinRight.setVisibility(View.GONE);
                    holder.remarklin.setVisibility(View.GONE);
                    ShopInfo shopInfo = new Gson().fromJson(extra, ShopInfo.class);
                    Log.i("WXY", "onBindViewHolder: "+shopInfo.getThumb());
                    Glide.with(context).load(shopInfo.getThumb()).into(holder.shopimg);
                    holder.shopprice.setText(shopInfo.getGoods_price());
                    holder.shoptitle.setText(shopInfo.getTitle());
                } else {
                    holder.remarklin.setVisibility(View.GONE);
                    holder.redlinRight.setVisibility(View.GONE);
                    holder.shoplin.setVisibility(View.GONE);
                    holder.chatitemRela.setVisibility(View.VISIBLE);
                    holder.chatitemRighttext.setText(mList.get(position).getCt());
                }
            } else if (action != null && action.equals("8")) {
                holder.shoplin.setVisibility(View.GONE);
                holder.remarklin.setVisibility(View.GONE);
                holder.chatitemRela.setVisibility(View.GONE);
                holder.redlinRight.setVisibility(View.VISIBLE);
                holder.redTitleright.setText(mList.get(position).getCt());
                String extra =  new Gson().toJson(mList.get(position).getExtra());
                String[] split = extra.split(",");
                holder.redMoneyright.setText(split[0]);
            } else if (action != null && action.equals("7")) {


            } else {
                holder.remarklin.setVisibility(View.GONE);
                holder.redlinRight.setVisibility(View.GONE);
                holder.shoplin.setVisibility(View.GONE);
                holder.chatitemRela.setVisibility(View.VISIBLE);
                holder.chatitemRighttext.setText(mList.get(position).getCt());
            }

        }

        //点击立即点评 接口回调  调用接口
        holder.chatitemRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClick != null) {
                    onClick.onClick(position);
                }
            }
        });

        holder.shopsTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClick!=null){
                    onItemClick.onItemClick(position);
                }
            }
        });

        holder.redlinLeft.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                String[] split =  new Gson().toJson(mList.get(position).getExtra()).split(",");
                Bundle bundle = new Bundle();
                bundle.putString("order_no", split[1]);
                JumpUtils.gotoActivity(context, BillInfoActivity.class, bundle);
            }
        });

    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }
    public interface onItemClickLinstener {
        void onItemClick(int posstion);
    }

    public void setOnItemClickLinstener(onItemClickLinstener onClickLinstener) {
        this.onItemClick = onClickLinstener;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chatitem_lefthead)
        ImageView chatitemLefthead;
        @BindView(R.id.chatitem_shopimg)
        ImageView chatitemShop;
        @BindView(R.id.chatitem_remark)
        Button chatitemRemark;
        @BindView(R.id.chatitem_cacle)
        Button chatitemCacle;
        @BindView(R.id.remarklin)
        LinearLayout remarklin;
        @BindView(R.id.redimg_left)
        ImageView redimgLeft;
        @BindView(R.id.red_moneyleft)
        TextView redMoneyleft;
        @BindView(R.id.shops_tx)
        TextView shopsTx;
        @BindView(R.id.red_titleleft)
        TextView redTitleleft;
        @BindView(R.id.redlin_left)
        LinearLayout redlinLeft;
        @BindView(R.id.chatitem_lefttext)
        TextView chatitemLefttext;
        @BindView(R.id.shops_title)
        TextView shopsTitle;
        @BindView(R.id.shops_money)
        TextView shopsMoney;
        @BindView(R.id.chatitem_offerlin)
        LinearLayout chatitemOfferlin;
        @BindView(R.id.chatitem_leftlin)
        LinearLayout chatitemLeftlin;
        @BindView(R.id.chatitem_righttext)
        TextView chatitemRighttext;
        @BindView(R.id.shoptitle)
        TextView shoptitle;
        @BindView(R.id.shopprice)
        TextView shopprice;
        @BindView(R.id.shopimg)
        ImageView shopimg;
        @BindView(R.id.shoplin)
        LinearLayout shoplin;
        @BindView(R.id.redimg_right)
        ImageView redimgRight;
        @BindView(R.id.red_moneyright)
        TextView redMoneyright;
        @BindView(R.id.red_titleright)
        TextView redTitleright;
        @BindView(R.id.redlin_right)
        LinearLayout redlinRight;
        @BindView(R.id.chatitem_righthead)
        ImageView chatitemRighthead;
        @BindView(R.id.chatitem_rightlin)
        LinearLayout chatitemRightlin;
        @BindView(R.id.chatitem_rela)
        RelativeLayout chatitemRela;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
