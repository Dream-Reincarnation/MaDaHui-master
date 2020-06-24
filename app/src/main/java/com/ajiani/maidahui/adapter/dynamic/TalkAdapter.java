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
import com.ajiani.maidahui.bean.dynamic.TalkBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalkAdapter extends RecyclerView.Adapter {

    private Context context;
    public ArrayList<TalkBean.DataBean> mList;
    private onClickLinstener onClick;
    public TalkAdapter(ArrayList<TalkBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.talk_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         ViewHolder holder1= (ViewHolder) holder;
         holder1.talkName.setText("#"+mList.get(position).getName());

        String count = mList.get(position).getCount();
            if(count.equals("-1")){
                holder1.talkNum.setText("新话题");
            }else{
                Glide.with(context).load(mList.get(position).getThumb()).into(((ViewHolder) holder).talkImg);
                holder1.talkNum.setText(mList.get(position).getCount()+"人参与");
            }

         holder1.lin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(onClick!=null){
                     onClick.onClick(position);
                 }
             }
         });
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

    public void addAll(List<TalkBean.DataBean> data) {
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.talk_img)
        ImageView talkImg;
        @BindView(R.id.talk_name)
        TextView talkName;
        @BindView(R.id.talk_num)
        TextView talkNum;
        @BindView(R.id.talk_lin)
        LinearLayout lin;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
