package com.ajiani.maidahui.adapter.chat;

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
import com.ajiani.maidahui.Utils.TimeUtils;
import com.ajiani.maidahui.bean.chat.SystemBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoradAdapter extends RecyclerView.Adapter<BoradAdapter.ViewHolder> {
    public ArrayList<SystemBean.DataBean> mList;

    private Context context;
    private LogisticsAdapter.onLongClickLinstener onLongClick;

    public BoradAdapter(ArrayList<SystemBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_board, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.boardText.setText(mList.get(position).getContent());
        holder.boardTitle.setText(mList.get(position).getMsg_type_name());
        String dateToString = TimeUtils.getDateToString((long) mList.get(position).getTimestamp()*1000);
        holder.boardTime.setText(dateToString);

        String msg_type = mList.get(position).getMsg_type();
        if(msg_type.equals("ComplaintEnd")|msg_type.equals("ComplaintSend")){
            Glide.with(context).load(R.mipmap.chat_service_nitifi).into(holder.boardImg);
        }else if(msg_type.equals("cashFail")|msg_type.equals("cashSuccess")){
            Glide.with(context).load(R.mipmap.chat_drawwith).into(holder.boardImg);
        }else if(msg_type.equals("AcetexVideo")){
            Glide.with(context).load(R.mipmap.chat_service_nitifi).into(holder.boardImg);
        }else if(msg_type.equals("LinkMessage")){
            Glide.with(context).load(R.mipmap.chat_service_nitifi).into(holder.boardImg);
        }

        holder.boardLin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onLongClick!=null){
                    onLongClick.onLongClick(position);
                }
                return true;
            }
        });

    }
    public interface onLongClickLinstener{
        void onLongClick(int posstion);
    }
    public void setOnLongClickLinstener(LogisticsAdapter.onLongClickLinstener onClickLinstener) {
        this.onLongClick = onClickLinstener;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.board_title)
        TextView boardTitle;
        @BindView(R.id.board_time)
        TextView boardTime;
        @BindView(R.id.board_img)
        ImageView boardImg;
        @BindView(R.id.board_text)
        TextView boardText;
        @BindView(R.id.board_lin)
        LinearLayout boardLin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
