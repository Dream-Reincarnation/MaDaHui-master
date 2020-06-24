package com.ajiani.maidahui.Utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.MyApp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CountDownTimerUtils extends CountDownTimer {

    private TextView mTextView; //显示倒计时的文字
    private Drawable drawable;

    /**
     * @param textView          The TextView
     * @param millisInFuture    millisInFuture  从开始调用start()到倒计时完成
     *                          并onFinish()方法被调用的毫秒数。（译者注：倒计时时间，单位毫秒）
     * @param countDownInterval 接收onTick(long)回调的间隔时间。（译者注：单位毫秒）
     */
    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval, Drawable drawable) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
        this.drawable = drawable;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setEnabled(false); //设置不可点击
        if (millisUntilFinished > 600000) {
            //设置成时间格式
            String dateToString = TimeUtils.getDateToString(millisUntilFinished);

            SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss", Locale.CHINA);

// time为转换格式后的字符串
            String time = dateFormat.format(new Date(millisUntilFinished));
            mTextView.setText(time);  //设置倒计时时间
            SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            /**
             * public void setSpan(Object what, int start, int end, int flags) {
             * 主要是start跟end，start是起始位置,无论中英文，都算一个。
             * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
             */
            spannableString.setSpan(span, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
            mTextView.setText(spannableString);
        } else {
            mTextView.setText(millisUntilFinished / 1000 + "秒");  //设置倒计时时间
            SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            /**
             * public void setSpan(Object what, int start, int end, int flags) {
             * 主要是start跟end，start是起始位置,无论中英文，都算一个。
             * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
             */
            if (drawable != null) {
                mTextView.setBackgroundResource(R.drawable.validate_code_press_bg); //设置按钮为灰色，这时是不能点击的
            }
            //mTextView.setBackgroundResource(R.drawable.validate_code_press_bg); //设置按钮为灰色，这时是不能点击的
            spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
            mTextView.setText("重新获取 " + spannableString);
        }

        mTextView.setTextColor(MyApp.getApp().getResources().getColor(R.color.mine_unselect));
        /**
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */

    }

    @Override
    public void onFinish() {
        mTextView.setTextColor(R.color.Thme);//重新获得点击
        mTextView.setText("重新获取");
        mTextView.setClickable(true);//重新获得点击
        mTextView.setEnabled(true);//重新获得点击
        if (drawable != null) {
            mTextView.setBackgroundResource(R.drawable.verifty);  //还原背景色

        }
    }


}
