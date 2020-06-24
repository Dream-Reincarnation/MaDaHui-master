package com.ajiani.maidahui.adapter.dynamic.music;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.anmion.ExpandableViewHoldersUtil;
import com.ajiani.maidahui.bean.dynamic.music.MusicBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {


    private Context context;
    private onClickLinstener onClick;
    private View inflate;
    public ArrayList<MusicBean.DataBean> mList;
    private onItemClickLinstener onItemClick;
    public int pos=-1;
    private ViewHolder oldHolder;
    private ViewHolder holder1;
    public  boolean isSencond;
    private onDownClickLinstener onDownClick;

    public MusicAdapter(ArrayList<MusicBean.DataBean> mList) {
        this.mList = mList;
    }

   public  ExpandableViewHoldersUtil.KeepOneH<ViewHolder> keepOne = new ExpandableViewHoldersUtil.KeepOneH<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        inflate = LayoutInflater.from(context).inflate(R.layout.item_music, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder1 = holder;

        keepOne.bind(holder,position);
        MusicBean.DataBean dataBean = mList.get(position);
        Glide.with(context).load(mList.get(position).getThumb()).into(holder.musicItemThumb);
        holder.musicItemName.setText(mList.get(position).getName());
        holder.musicItemNum.setText(dataBean.getCount() + "人使用");
        holder.musicItemTime.setText(timeParse(mList.get(position).getDuration() * 1000));
        holder.musicItem.setText(dataBean.getAuthor());
        if (dataBean.getIs_star() == 0) {
            holder.musicItemStar.setImageResource(R.mipmap.collectionunsel);
        } else {
            holder.musicItemStar.setImageResource(R.mipmap.collectionsel);
        }

        if(mList.get(position).isPlay()){
            holder.musicItemPause.setImageResource(R.mipmap.music_start);
        }else{
            holder.musicItemPause.setImageResource(R.mipmap.music_pause);
        }
       /* if(pos==-1){
            close(holder);
        }*/

        holder.musicItemRela.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //别的都是false
             /*   if(pos>=0){
                    oldHolder = (ViewHolder) ((RecyclerView) holder.itemView.getParent()).findViewHolderForPosition(pos);
                }*/

                if(position!=pos){
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setPlay(false);
                    }
                    if(pos>=0){
                        oldHolder = (ViewHolder) ((RecyclerView) holder.itemView.getParent()).findViewHolderForPosition(pos);
                    }
                    if(oldHolder!=null){
                        oldHolder.musicItemPause.setImageResource(R.mipmap.music_pause);
                    }

                    if(mList.get(position).isPlay()){
                        holder.musicItemPause.setImageResource(R.mipmap.music_pause);
                        mList.get(position).setPlay(false);
                    }else{
                        holder.musicItemPause.setImageResource(R.mipmap.music_start);
                        mList.get(position).setPlay(true);
                    }
                    keepOne.toggle(holder,position);
                }else{
                    if(mList.get(position).isPlay()){
                        holder.musicItemPause.setImageResource(R.mipmap.music_pause);
                        mList.get(position).setPlay(false);
                    }else{
                        holder.musicItemPause.setImageResource(R.mipmap.music_start);
                        mList.get(position).setPlay(true);
                    }

                }





                if(onItemClick!=null){
                    onItemClick.onClick(position,pos);
                }
                pos=position;
            }
        });

        holder.musicItemFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.get(position).getIs_star()==0){
                    mList.get(position).setIs_star(1);
                    holder.musicItemStar.setImageResource(R.mipmap.collectionsel);
                }else{
                    mList.get(position).setIs_star(0);
                    holder.musicItemStar.setImageResource(R.mipmap.collectionunsel);
                }


                if(onClick!=null){
                    onClick.onClick(position,v);
                }
            }
        });

        holder.musicItemUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDownClick!=null){
                    onDownClick.onClick(position);
                }
            }
        });
    }

    public void close(ViewHolder holder){
        keepOne.close(holder);
    }


  public  void closepos(int posstion){

      keepOne.close(oldHolder);
  }



    public String timeParse(long duration) {
        String time = "";
        long minute = duration / 60000;
        long seconds = duration % 60000;
        long second = Math.round((float) seconds / 1000);
        if (minute < 10) {
            time += "0";
        }
        time += minute + ":";
        if (second < 10) {
            time += "0";
        }
        time += second;
        return time;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ExpandableViewHoldersUtil.Expandable {
        @BindView(R.id.music_item_thumb)
        ImageView musicItemThumb;
        @BindView(R.id.music_item_rela)
        RelativeLayout musicItemRela;
        @BindView(R.id.music_item_pause)
        ImageView musicItemPause;
        @BindView(R.id.music_item_card)
        CardView musicItemCard;
        @BindView(R.id.music_item_name)
        TextView musicItemName;
        @BindView(R.id.music_item)
        TextView musicItem;
        @BindView(R.id.music_item_time)
        TextView musicItemTime;
        @BindView(R.id.music_item_num)
        TextView musicItemNum;
        @BindView(R.id.music_item_star)
        ImageView musicItemStar;
        @BindView(R.id.music_item_use)
        TextView musicItemUse;
        @BindView(R.id.music_item_farm)
        FrameLayout musicItemFarm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public View getExpandView() {
            return musicItemUse;
        }
    }

    public interface onClickLinstener {
        void onClick(int posstion, View view);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }


     public interface onItemClickLinstener{
             void onClick(int posstion,int pos);
         }
         public void setOnItemClickLinstener(onItemClickLinstener onClickLinstener){
             this.onItemClick=onClickLinstener;
         }
    public interface onDownClickLinstener {
        void onClick(int posstion);
    }

    public void setOnDownClickLinstener(onDownClickLinstener onClickLinstener) {
        this.onDownClick = onClickLinstener;
    }

}
