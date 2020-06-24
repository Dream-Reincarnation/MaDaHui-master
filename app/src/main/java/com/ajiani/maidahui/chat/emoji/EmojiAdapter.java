package com.ajiani.maidahui.chat.emoji;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import androidx.annotation.Nullable;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.EmotionUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

public class EmojiAdapter extends BaseQuickAdapter< EmojiBean, BaseViewHolder> {


    public EmojiAdapter(@Nullable List<EmojiBean> data, int index, int pageSize) {
         super(R.layout.item_emoji,  data);
     }

    @Override
    protected void convert(BaseViewHolder helper, EmojiBean item) {
        //判断是否为最后一个item
        if (item.getId()==0) {
           //  helper.setBackgroundRes(R.id.et_emoji,R.mipmap.rc_icon_emoji_delete );
        } else {
            String s = EmotionUtils.list.get(item.getId());
            helper.setText(R.id.et_emoji,s );
            Bitmap bitmap = BitmapFactory.decodeResource(MyApp.getApp().getResources(), item.getUnicodeInt());
            ImageSpan imageSpan = new ImageSpan(MyApp.getApp(),bitmap);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(s);
            spannableStringBuilder.setSpan(imageSpan,0,s.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

             helper.setText(R.id.et_emoji,spannableStringBuilder );
        }



    }


}
