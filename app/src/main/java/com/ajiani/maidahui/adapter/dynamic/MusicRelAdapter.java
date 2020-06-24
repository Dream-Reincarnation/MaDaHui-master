package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicRelAdapter extends RecyclerView.Adapter {
    public ArrayList<String> mList;
    private Context context;
    private onClickListener oncLick;

    public MusicRelAdapter(ArrayList<String> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.music_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           ViewHolder holder1= (ViewHolder) holder;
           holder1.sendMuiscname.setText(mList.get(position));
           holder1.sendMuiscname.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(oncLick!=null){
                       oncLick.onClick(position);
                   }
               }
           });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public interface onClickListener{
        void onClick(int posstion);
    }
    public void setOnClickListener(onClickListener onClickListener){
        this.oncLick=onClickListener;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.send_muiscname)
        TextView sendMuiscname;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
