package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreCommAdapter extends RecyclerView.Adapter {

    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.morecomm_item, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1= (ViewHolder) holder;
         if(position==3){
             holder1.moreitemName.setText("关虎屯长回复啦啦啦啦");
            setColor(holder1.moreitemName,4);
         }
    }
  public void setColor(TextView textView,int lenght){
      SpannableStringBuilder ssbuilder = new SpannableStringBuilder(textView.getText().toString());
      //ForegroundColorSpan--文字前景色，BackgroundColorSpan--文字背景色
      ForegroundColorSpan yellowSpan = new ForegroundColorSpan(R.color.mine_unselect);
      ssbuilder.setSpan(yellowSpan, lenght,lenght+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      textView.setText(ssbuilder);
  }
    @Override
    public int getItemCount() {
        return 5;
    }


    class ViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.moreitem_img)
        ImageView moreitemImg;
        @BindView(R.id.moreitem_name)
        TextView moreitemName;
        @BindView(R.id.moreitem_time)
        TextView moreitemTime;
        @BindView(R.id.moreitem_like)
        ImageView moreitemLike;
        @BindView(R.id.moreitem_likenum)
        TextView moreitemLikenum;
        @BindView(R.id.moreitem_context)
        TextView moreitemContext;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
