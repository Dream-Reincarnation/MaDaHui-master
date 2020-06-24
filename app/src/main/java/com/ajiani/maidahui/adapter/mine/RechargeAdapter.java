package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.mine.RuleBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeAdapter extends RecyclerView.Adapter {
    public ArrayList<RuleBean.DataBean> mList;
    private onClickLinstener onClick;

    public RechargeAdapter(ArrayList<RuleBean.DataBean> mList) {
        this.mList = mList;
    }

    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.recharge_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      ViewHolder holder1= (ViewHolder) holder;
        String name = mList.get(position).getName();
        String[] str = name.split("送");
        holder1.rechageMoney.setText("充值"+str[0]);
        holder1.rechargeSend.setText("送"+str[1]);
        holder1.rechargeNum.setText("￥"+mList.get(position).getMoney());
        holder1.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClick!=null){
                    onClick.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
      public interface onClickLinstener{
              void onClick(int posstion);
          }
          public void setOnClickLinstener(onClickLinstener onClickLinstener){
              this.onClick=onClickLinstener;
          }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rechage_money)
        TextView rechageMoney;
        @BindView(R.id.recharge_send)
        TextView rechargeSend;
        @BindView(R.id.recharge_num)
        TextView rechargeNum;
        @BindView(R.id.recharge_lin2)
        CardView lin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
