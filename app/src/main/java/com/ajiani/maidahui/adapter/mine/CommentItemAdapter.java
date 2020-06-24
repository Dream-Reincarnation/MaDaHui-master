package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.mine.CommentListBeans;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CommentItemAdapter extends RecyclerView.Adapter<CommentItemAdapter.ViewHolder> {


    private Context context;
     int parentItem;
    public ArrayList<CommentListBeans.DataBean> mList;
    public ViewHolder holder1;
    private onLikesClickLinstener onLikesClick;

    public CommentItemAdapter(int parentItem, ArrayList<CommentListBeans.DataBean> mList) {
        this.parentItem = parentItem;
        this.mList = mList;
    }

    public CommentItemAdapter(ArrayList<CommentListBeans.DataBean> mList) {

        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.comment_item2, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder1 = holder;
        if(mList.get(position).getIs_star().equals("0")){
            holder.like.setImageResource(R.mipmap.aixin);
        }else{
            holder.like.setImageResource(R.mipmap.like);
        }Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.head);
       holder.likeNum.setText(mList.get(position).getStar()+"");
       holder.name.setText(mList.get(position).getNickname());
       holder.context.setText(mList.get(position).getContent());
       holder1.like.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//               Toast.makeText(context, "这是第"+position+"个回复，上一级的条目是"+mList.get(position).getAid(), Toast.LENGTH_SHORT).show();
               if(onLikesClick!=null){

                   onLikesClick.onClick(position,parentItem);

               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView head;
        private  TextView name;
        private  TextView context;
        private  TextView likeNum;
        public  ImageView like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.commitem_head);
            name = itemView.findViewById(R.id.commitem_name);
            context = itemView.findViewById(R.id.commitem_text);
            likeNum = itemView.findViewById(R.id.comment_likenum);
            like = itemView.findViewById(R.id.comment_like);
        }
    }
    public interface onLikesClickLinstener{
            void onClick(int posstion,int item);
        }
        public void setOnLikesClickLinstener(onLikesClickLinstener onClickLinstener){
            this.onLikesClick=onClickLinstener;
        }

}
