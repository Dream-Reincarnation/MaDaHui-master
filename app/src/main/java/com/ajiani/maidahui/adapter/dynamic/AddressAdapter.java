package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.Address;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    public ArrayList<Address> city;

    private Context context;
    private String TAG = "wxy";
    private onClickLinstener onClick;

    public AddressAdapter(ArrayList<Address> city) {

        this.city = city;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.address_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.addressStair.setText(city.get(position).getTitle());
        holder.addressSencnd.setText(city.get(position).getAddress());
        holder.addressLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick!=null){
                    onClick.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return city.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.address_stair)
        TextView addressStair;
        @BindView(R.id.address_sencnd)
        TextView addressSencnd;
        @BindView(R.id.address_lin)
        LinearLayout addressLin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }
}
