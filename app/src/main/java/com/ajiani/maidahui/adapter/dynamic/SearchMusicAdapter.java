package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.SMusicBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchMusicAdapter extends RecyclerView.Adapter {

    public ArrayList<SMusicBean.DataBean> mList;
    private Context mContext;
    private onClickLinstener OnClick;
    private OnClick onClck;

    public SearchMusicAdapter(ArrayList<SMusicBean.DataBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.searmic_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder holder1 = (ViewHolder) holder;
        if (mList.get(position).isIsshow()){
            holder1.bt.setVisibility(View.VISIBLE);
        }else{
            holder1.bt.setVisibility(View.GONE);
        }
        holder1.searmicLinitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OnClick != null) {
                    OnClick.onClick(position);
                    notifyDataSetChanged();
                    holder1.bt.setVisibility(View.VISIBLE);

                }
            }
        });
        holder1.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onClck != null) {
                    onClck.onClick(position);
                }
            }
        });
        holder1.searmicName.setText(mList.get(position).getName() );
        holder1.searmicAnother.setText( mList.get(position).getAuthor());
       /* if(mList.size()==0){
            holder1.searmicLin2.setVisibility(View.VISIBLE);
        }else{
            holder1.searmicLin2.setVisibility(View.GONE);
        }*/
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnClick {
        void onClick(int posstion);
    }

    public void setOnClick(OnClick onClick) {
        this.onClck = onClick;
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.OnClick = onClickLinstener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.searmic_name)
        TextView searmicName;
        @BindView(R.id.searmic_lin)
        LinearLayout searmicLin;
        @BindView(R.id.searmic_another)
        TextView searmicAnother;
        @BindView(R.id.searmic_linitem)
        LinearLayout searmicLinitem;

        @BindView(R.id.searmic_bt)
        Button bt;
//        @BindView(R.id.searmic_lin2)
//        LinearLayout searmicLin2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
