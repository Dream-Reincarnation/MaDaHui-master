package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.mine.IntegraldilsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.ViewHolder> {
    public ArrayList<IntegraldilsBean.DataBean> mList;
    private Context context;

    public IntegralAdapter(ArrayList<IntegraldilsBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.integral_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//         holder.integralmTime.setText(mList.get(position).getCreate_time());
         holder.integralmState.setText(mList.get(position).getChange_type());
         if((mList.get(position).getChange_integral()+"").contains("-")){
             holder.integralmMoney.setText(mList.get(position).getChange_integral()+"");
         }else{
             holder.integralmMoney.setText("+"+mList.get(position).getChange_integral());
         }

         holder.integralmBlanace.setText(mList.get(position).getCreate_time()+"");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.integralm_state)
        TextView integralmState;
        @BindView(R.id.integralm_blanace)
        TextView integralmBlanace;
        @BindView(R.id.integralm_money)
        TextView integralmMoney;
        @BindView(R.id.integralm_time)
        TextView integralmTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
