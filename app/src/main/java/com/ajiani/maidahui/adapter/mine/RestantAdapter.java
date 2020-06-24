package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.mine.RestantBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  RestantAdapter extends RecyclerView.Adapter<RestantAdapter.ViewHolder> {
    public ArrayList<RestantBean.DataBean> mList;

    private Context context;
    private onClickLinstener onClick;

    public RestantAdapter(ArrayList<RestantBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext ();
        View inflate = LayoutInflater.from(context).inflate(R.layout.restant_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.restantmTime.setText(mList.get(position).getCreate_time());
        holder.restantmState.setText(mList.get(position).getRemark());
        holder.restantmMoney.setText(mList.get(position).getChange_votes());
        holder.restantmBlanace.setText(mList.get(position).getAfter_votes());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClick!=null){
                    onClick.onClick(position);
                }
            }
        });
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.restantm_state)
        TextView restantmState;
        @BindView(R.id.restantm_lin)
        LinearLayout linearLayout;
        @BindView(R.id.restantm_blanace)
        TextView restantmBlanace;
        @BindView(R.id.restantm_money)
        TextView restantmMoney;
        @BindView(R.id.restantm_time)
        TextView restantmTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
