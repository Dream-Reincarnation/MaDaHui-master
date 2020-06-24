package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.ItemSlideHelper;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.chat.MessageListBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter2 extends RecyclerView.Adapter<ChatAdapter2.ViewHolder> implements ItemSlideHelper.Callback {

    public ArrayList<MessageListBean.DataBean> mList;
    private onItemClickLinstener onItemClick;
    private onLongClickLinstener onLongClick;

    public ChatAdapter2(ArrayList<MessageListBean.DataBean> mList) {
        this.mList = mList;
    }

    private Context context;
    private onClickLinstener onClick;
    RecyclerView mRecyclerView;

    int posstion;

    public int getPosstion() {
        return posstion;
    }

    public void setPosstion(int posstion) {
        this.posstion = posstion;
    }


    /**
     * 将recyclerView绑定Slide事件
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
        mRecyclerView.addOnItemTouchListener(new ItemSlideHelper(mRecyclerView.getContext(), this));
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_chat_item, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chatitemTitle.setText(mList.get(position).getNickname());
        holder.ll_hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /*   mList.remove(holder.getAdapterPosition());
                  notifyItemRemoved(holder.getAdapterPosition());*/
                if (onClick != null) {
                    onClick.onClick(position);
                }
            }
        });
        if (mList.get(position).getHeadimgurl().length() < 6) {
            Glide.with(context).load(R.mipmap.logo)/*.apply(new RequestOptions().circleCrop())*/.into(holder.chatitemImg);

        } else {
            Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.chatitemImg);
        }
        holder.chatitemTime.setText(mList.get(position).getFormat_create_time());
        if (mList.get(position).getNoread() > 0) {
            if (mList.get(position).getNoread() > 99) {
                holder.chatNomsg.setText("99+");
            } else {
                holder.chatNomsg.setText(mList.get(position).getNoread() + "");
            }
            holder.chatNomsg.setVisibility(View.VISIBLE);
        } else {
            holder.chatNomsg.setVisibility(View.GONE);
        }


        holder.chatitemText.setText(mList.get(position).getContent());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });

        holder.ll_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                setPosstion(holder.getLayoutPosition());
                if(onLongClick!=null){
                    onLongClick.onLongClick(posstion);
                }
                return true;
            }
        });
    }

    @Override
    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
        if (holder.itemView instanceof LinearLayout) {
            ViewGroup viewGroup = (ViewGroup) holder.itemView;
            //viewGroup包含3个控件，即消息主item、标记已读、删除，返回为标记已读宽度+删除宽度
            return viewGroup.getChildAt(1).getLayoutParams().width;
        }
        return 0;

    }

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
        return mRecyclerView.getChildViewHolder(childView);
    }

    @Override
    public View findTargetView(float x, float y) {
        return mRecyclerView.findChildViewUnder(x, y);
    }

    public interface onItemClickLinstener {
        void onItemClick(int posstion);
    }

    public void setOnItemClickLinstener(onItemClickLinstener onClickLinstener) {
        this.onItemClick = onClickLinstener;
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }


    public interface onLongClickLinstener {
        void onLongClick(int posstion);
    }

    public void setOnLongClickLinstener(onLongClickLinstener onClickLinstener) {
        this.onLongClick = onClickLinstener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        @BindView(R.id.chatitem_img)
        ImageView chatitemImg;
        @BindView(R.id.chatitem_title)
        TextView chatitemTitle;
        @BindView(R.id.chatitem_time)
        TextView chatitemTime;
        @BindView(R.id.chat_top)
        TextView chatTop;
        @BindView(R.id.chatitem_text)
        TextView chatitemText;
        @BindView(R.id.chat_nomsg)
        TextView chatNomsg;
        @BindView(R.id.chat_notifation)
        LinearLayout chatNotifation;
        public
        LinearLayout ll_item;
        LinearLayout ll_hidden;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
            ll_hidden = (LinearLayout) itemView.findViewById(R.id.ll_hidden);
            //     ll_item.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.add(ContextMenu.NONE, 1, ContextMenu.NONE, "删除");

        }
    }


}
