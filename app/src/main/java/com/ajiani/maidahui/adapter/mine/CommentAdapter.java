package com.ajiani.maidahui.adapter.mine;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter {

    private Context context;
    private onClickLinstener onClick;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.comment, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          ViewHolder holder1= (ViewHolder) holder;
          holder1.commentMorelin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(onClick!=null){
                      onClick.onClick(position);
                  }
              }
          });
    }

     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClick=onClickLinstener;
         }

    @Override
    public int getItemCount() {
        return 5;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comment_img)
        ImageView commentImg;
        @BindView(R.id.comment_name)
        TextView commentName;
        @BindView(R.id.comment_time)
        TextView commentTime;
        @BindView(R.id.comment_like)
        ImageView commentLike;
        @BindView(R.id.comment_likenum)
        TextView commentLikenum;
        @BindView(R.id.comment_context)
        TextView commentContext;
        @BindView(R.id.comment_name2)
        TextView commentName2;
        @BindView(R.id.comment_text)
        TextView commentText;
        @BindView(R.id.comment_morelin)
        LinearLayout commentMorelin;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
