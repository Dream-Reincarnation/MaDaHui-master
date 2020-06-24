package com.ajiani.maidahui.adapter.dynamic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.dynamic.FriendsBean;
import com.ajiani.maidahui.bean.dynamic.FriendsDataBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShareVideoAdapter extends RecyclerView.Adapter<ShareVideoAdapter.ViewHolder> {

    private Context context;
    public ArrayList<FriendsDataBean> mList;
    private onClickLinstener onClick;

    public ShareVideoAdapter(ArrayList<FriendsDataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        View inflate = LayoutInflater.from(context).inflate(R.layout.share_video_item, parent, false);
        return new ViewHolder(inflate);
    }

    boolean b = true;
    int num;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendsDataBean friendsDataBean = mList.get(position);

        if (position == 11) {
            holder.shareVideoHead.setVisibility(View.GONE);
            holder.shareVideoName.setText("更多");
        } else {
            Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.shareVideoHead);
            holder.shareVideoName.setText(mList.get(position).getNickname());
        }
        holder.shareVideoFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件
                boolean sel = friendsDataBean.isSel();
                if (sel) {
                    num--;
                    holder.shareVideoSel.setVisibility(View.GONE);
                    friendsDataBean.setSel(false);
                } else {
                    num++;
                    holder.shareVideoSel.setVisibility(View.VISIBLE);
                    friendsDataBean.setSel(true);
                }
                if(onClick!=null){
                    onClick.onClick(position,num);
                }

            }
        });


    }



     public interface onClickLinstener{
             void onClick(int posstion,int a);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClick=onClickLinstener;
         }

    @Override
    public int getItemCount() {
        if (mList.size() > 10) {
            return mList.size() + 1;
        } else {
            return mList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.share_video_head)
        ImageView shareVideoHead;
        @BindView(R.id.share_video_farm)
        FrameLayout shareVideoFarm;
        @BindView(R.id.share_video_sel)
        ImageView shareVideoSel;
        @BindView(R.id.share_video_name)
        TextView shareVideoName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
