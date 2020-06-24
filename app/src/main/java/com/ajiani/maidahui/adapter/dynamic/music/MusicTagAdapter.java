package com.ajiani.maidahui.adapter.dynamic.music;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.xugter.xflowlayout.XFlowLayout;

import java.util.ArrayList;

public class MusicTagAdapter extends XFlowLayout.Adapter{

    public ArrayList<String> mList;
    public Context context;
    private onClickLinstener onClick;

    public MusicTagAdapter(ArrayList<String> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public View getItemViewByPos(int pos) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        TextView tv = new TextView(context);
        tv.setPadding(30, 16, 30, 9);
        tv.setText(mList.get(pos));
        tv.setTextSize(12);
        tv.setSingleLine();
        tv.setTextColor(context.getResources().getColor(R.color.black));
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setLayoutParams(layoutParams);

        tv.setBackgroundResource(R.drawable.search_shop);
        Drawable background = tv.getBackground();
        Drawable drawable = DrawUtils.setSolid(R.color.background, background);
        tv.setBackground(drawable);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClick!=null){
                    onClick.onClick(pos);
                }
            }
        });
        return tv;
    }

     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.onClick=onClickLinstener;
         }
}
