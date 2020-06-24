package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.mine.EarDetailBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarDetaAdapter extends RecyclerView.Adapter {
    public ArrayList<EarDetailBean.DataBean> mList;
    private Context context;

    public EarDetaAdapter(ArrayList<EarDetailBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.eardeta_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         ViewHolder holder1= (ViewHolder) holder;
        String before_money = mList.get(position).getBefore_money();
        String after_money = mList.get(position).getAfter_money();
        holder1.earBlanace.setText("余额:"+after_money);
        holder1.earTime.setText(mList.get(position).getCreate_time());
        holder1.earMoney.setText(mList.get(position).getChange_money());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.top_state)
        TextView topState;
        @BindView(R.id.ear_blanace)
        TextView earBlanace;
        @BindView(R.id.ear_money)
        TextView earMoney;
        @BindView(R.id.ear_time)
        TextView earTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
