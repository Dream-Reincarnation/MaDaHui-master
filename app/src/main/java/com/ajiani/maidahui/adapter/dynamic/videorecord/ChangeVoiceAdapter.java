package com.ajiani.maidahui.adapter.dynamic.videorecord;

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
import com.ajiani.maidahui.bean.dynamic.ChanageVocie;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeVoiceAdapter extends RecyclerView.Adapter<ChangeVoiceAdapter.ViewHolder> {
    public ArrayList<ChanageVocie> mList;

    private Context context;
    private onClickLinstener onClick;

    public ChangeVoiceAdapter(ArrayList<ChanageVocie> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.dyna_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.dynaVoiceName.setText(mList.get(position).getName());
        ChanageVocie chanageVocie = mList.get(position);
        boolean issel = chanageVocie.isIssel();
        if(issel){
            holder.dynaVoiceImg.setImageResource(mList.get(position).getSel());
        }else{
            holder.dynaVoiceImg.setImageResource(mList.get(position).getUnsel());
        }


         holder.dynaVoiceLin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  if(onClick!=null){
                      onClick.onClick(position);
                  }

             }
         });

       }

    public void clear() {
        for(int i=0;i<mList.size();i++){
             mList.get(i).setIssel(false);

        }

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dyna_voice_img)
        ImageView dynaVoiceImg;
        @BindView(R.id.dyna_lin)
        LinearLayout dynaVoiceLin;
        @BindView(R.id.dyna_voice_name)
        TextView dynaVoiceName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
