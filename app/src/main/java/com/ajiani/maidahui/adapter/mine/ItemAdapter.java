package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.bean.mine.CommentListBean;


import com.ajiani.maidahui.bean.mine.CommentListBeans;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private Context context;
    public ArrayList<CommentListBeans.DataBean> mList;
    public ArrayList<ArrayList<CommentListBeans.DataBean>> list;

    int posstion;
    public CommentItemAdapter commentItemAdapter;
    private onLikeClickLinstener onLikeClick;
    public MyViewHolder holder1;
    private onClickLinstener onCommentClick;

    public ItemAdapter(ArrayList<CommentListBeans.DataBean> mList, ArrayList<ArrayList<CommentListBeans.DataBean>> list) {
        this.mList = mList;
        this.list = list;
    }


    public ItemAdapter(ArrayList<CommentListBeans.DataBean> mList) {
        this.mList = mList;
    }

    private onClickLinstener onClick;

    public void addAll(int posstion, ArrayList<CommentListBeans.DataBean> list) {

        this.posstion = posstion;

        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder1 = holder;
        Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.commentImg);
        holder.commentRell.setLayoutManager(new LinearLayoutManager(context));
        holder.commentName.setText(mList.get(position).getNickname());
        holder.commentTime.setText(mList.get(position).getFormat_create_time());
        holder.commentContext.setText(mList.get(position).getContent());
        holder.commentLikenum.setText(mList.get(position).getStar() + "");
        if(list.size()>0){
            commentItemAdapter = new CommentItemAdapter(position,list.get(position));
            holder.commentRell.setAdapter(commentItemAdapter);
        }
         if(mList.get(position).getIs_star().equals("0")){
             holder.commentLike.setImageResource(R.mipmap.aixin);
         }else{
             holder.commentLike.setImageResource(R.mipmap.like);
         }
        /*
        * 二级评论点赞
        *    在发表一级评论的时候，对应的添加一个空的二级评论列表
        *    并在绑定适配器的时候，把二级评论的上一级评论的下表得到
        *    在点赞二级评论的时候，在一级评论中的到对应二级评论的集合
        *    再根据二级评论的下标，得到要点赞二级评论的id，更改二级评论集合中的东西
        *    刷新一级评论的适配器，那样二级也会刷新完成了
        * */
        if(commentItemAdapter!=null){
            commentItemAdapter.setOnLikesClickLinstener(
                    new CommentItemAdapter.onLikesClickLinstener() {
                @Override
                public void onClick(int posstion,int item) {

                    ArrayList<CommentListBeans.DataBean> dataBeans = list.get(item);
                    CommentListBeans.DataBean dataBean = dataBeans.get(posstion);
                    int aid1 = dataBean.getAid();
                    Log.i("WXY", "onBindViewHolder: "+aid1);
//                    String is_star = commentItemAdapter.mList.get(posstion).getIs_star();
                    String is_star = list.get(item).get(posstion).getIs_star();
                  //  int aid = commentItemAdapter.mList.get(posstion).getAid();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("comment_id", aid1 + "");
                    HashMap<String, String> hashMap = Params.setParams2();
                    hashMap.putAll(map);
                    HashMap<String, String> sign = Params.getSign(hashMap);
                    HttpManager.instance().getServer(MineServer.class).starComment(sign)
                            .compose(RxUtils.rxScheduleThread())
                            .subscribe(new BaseObserver() {
                                @Override
                                protected void onSuccess(String string) {

                                    if(is_star.equals("0")){
                                        list.get(item).get(posstion).setIs_star("1");
                                        int star = list.get(item).get(posstion).getStar();
                                        star++;
                                        list.get(item).get(posstion).setStar(star);
                                        notifyDataSetChanged();
                                        commentItemAdapter.notifyDataSetChanged();
                                    /*    commentItemAdapter.holder1.like.setImageResource(R.mipmap.like);
                                        commentItemAdapter.mList.get(posstion).setIs_star("1");
                                        int star = commentItemAdapter.mList.get(posstion).getStar();
                                        star++;
                                        commentItemAdapter.mList.get(posstion).setStar(star);
                                        commentItemAdapter.notifyDataSetChanged();*/
                                    }else{
                                        list.get(item).get(posstion).setIs_star("0");
                                        int star = list.get(item).get(posstion).getStar();
                                        star--;
                                        list.get(item).get(posstion).setStar(star);
                                        notifyDataSetChanged();
                                        commentItemAdapter.notifyDataSetChanged();
                                      /*  commentItemAdapter.holder1.like.setImageResource(R.mipmap.aixin);
                                        commentItemAdapter.mList.get(posstion).setIs_star("0");
                                        int star = commentItemAdapter.mList.get(posstion).getStar();
                                        star--;
                                        commentItemAdapter.mList.get(posstion).setStar(star);
                                        commentItemAdapter.notifyDataSetChanged();*/
                                    }
                                }

                                @Override
                                protected void onError(String string) {

                                }
                            });


                }
            });
        }
        holder.commentLike.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View view) {
                if(onLikeClick!=null){
                    onLikeClick.onLikeClick(position);
                }
            }
        });
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClick != null) {
                    onClick.onClick(position);
                }
            }
        });


        holder.unFold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展开十条回复
               /* commentItemAdapter.a += 10;
                commentItemAdapter.notifyDataSetChanged();*/
            }
        });
    }


    @Override
    public int getItemCount() {

        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView commentImg;


        public TextView commentName;
        public TextView commentTime;
        public ImageView commentLike;
        public TextView commentLikenum;

        public TextView commentContext;
        public RecyclerView commentRell;
        private final TextView unFold;
        private final LinearLayout lin;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recycle_item_list_dialog, parent, false));
            //      text = (TextView) itemView.findViewById(R.id.text);
            commentContext = itemView.findViewById(R.id.comment_context);
            commentRell = itemView.findViewById(R.id.comment_rell);
            commentLikenum = itemView.findViewById(R.id.comment_likenum);
            commentLike = itemView.findViewById(R.id.comment_like);
            commentTime = itemView.findViewById(R.id.comment_time);
            commentName = itemView.findViewById(R.id.comment_name);
            commentImg = itemView.findViewById(R.id.comment_img);
            lin = itemView.findViewById(R.id.comment_item);

            unFold = itemView.findViewById(R.id.comment_unfold);
        }
    }
     public interface onLikeClickLinstener{
             void onLikeClick(int posstion);
         }
         public void setOnLikeClickLinstener(onLikeClickLinstener onClickLinstener){
             this.onLikeClick=onClickLinstener;
         }
    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }

     public interface onCommentLikeClickLinstener{
             void onCommentClick(int posstion);
         }
         public void setOnCommentClickLinstener(onClickLinstener onClickLinstener){
             this.onCommentClick=onClickLinstener;
         }

}
