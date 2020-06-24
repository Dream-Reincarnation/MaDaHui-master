package com.ajiani.maidahui.weight;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.VideoReleaaseActivity;
import com.ajiani.maidahui.bean.dynamic.topic.TopObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("AppCompatCustomView")
public class ReleaseEditText extends EditText {

    private ArrayList<TopObject> topics=  new ArrayList<>();;
    private Context context1;
    private VideoReleaaseActivity activity;


    public ReleaseEditText(Context context) {
        super(context);
    }

    public ReleaseEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        context1 = context;
        activity = (VideoReleaaseActivity)context1;
        initView();
    }




    private void initView() {
        /**
         * 输入框内容变化监听<br/>
         * 1.当文字内容产生变化的时候实时更新UI
         */
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 文字改变刷新UI
              //  refreshEditTextUI(s.toString());
            }
        });

        /**
         * 监听删除键 <br/>
         * 1.光标在话题后面,将整个话题内容删除 <br/>
         * 2.光标在普通文字后面,删除一个字符
         */
        this.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_DEL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {

                    int selectionStart = getSelectionStart();
                    int selectionEnd = getSelectionEnd();

                    /**
                     * 如果光标起始和结束不在同一位置,删除文本
                     */
                    if (selectionStart != selectionEnd) {
                        // 查询文本是否属于目标对象,若是移除列表数据
                        String tagetText = getText().toString().substring(
                                selectionStart, selectionEnd);
                        for (int i = 0; i < topics.size(); i++) {
                            TopObject object = topics.get(i);
                            if (tagetText.equals(object.getText())) {
                                topics.remove(object);
                                String text = object.getText();
                                activity.topics.remove(text+" ");
                            }
                        }
                        return false;
                    }

                    int lastPos = 0;
                    Editable editable = getText();
                    // 遍历判断光标的位置
                    for (int i = 0; i < topics.size(); i++) {

                        String objectText = topics.get(i)
                                .getText();

                        lastPos = getText().toString().indexOf(objectText,
                                lastPos);
                        if (lastPos != -1) {
                            if (selectionStart != 0
                                    && selectionStart >= lastPos
                                    && selectionStart <= (lastPos + objectText
                                    .length())) {
                                // 选中话题
                                setSelection(lastPos,
                                        lastPos + objectText.length());
                                // 设置背景色
                               /* editable.setSpan(new BackgroundColorSpan(
                                                mBackgroundColor), lastPos, lastPos
                                                + objectText.length(),
                                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
                                return true;
                            }
                        }
                        lastPos += objectText.length();
                    }
                }

                return false;
            }
        });


    }

    /**
     * 监听光标的位置,若光标处于话题内容中间则移动光标到话题结束位置
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        if (topics == null || topics.size() == 0) {
            return;
        }

        int startPosition = 0;
        int endPosition = 0;
        String objectText = "";
        for (int i = 0; i < topics.size(); i++) {
            objectText = topics.get(i).getText();// 文本
            startPosition = getText().toString().indexOf(objectText);// 获取文本开始下标
            endPosition = startPosition + objectText.length();
            if (startPosition != -1 && selStart > startPosition
                    && selStart <= endPosition) {// 若光标处于话题内容中间则移动光标到话题结束位置
                setSelection(endPosition);
            }
        }

    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        if (id == android.R.id.paste) {
            //调用剪贴板
            ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            //改变剪贴板中Content
            if (clip != null){
                //改为黑色字体
                CharSequence text = clip.getText();
                String s = String.valueOf(text);
                SpannableString spannableString = new SpannableString(s);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#212121")),0,s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                clip.setText(spannableString);
            }
        }
        return super.onTextContextMenuItem(id);
    }


    /**
     * EditText内容修改之后刷新UI
     *
     * @param
     */
    private void refreshEditTextUI(String content) {

        /**
         * 内容变化时操作<br/>
         * 1.查找匹配所有话题内容 <br/>
         * 2.设置话题内容特殊颜色
         */

        if (topics.size() == 0)
            return;

        if (TextUtils.isEmpty(content)) {
            topics.clear();
            return;
        }

        /**
         * 重新设置span
         */
        Editable editable = getText();
        int findPosition = 0;
        //找出所有话题的下表




       /* for (int i = 0; i < topics.size(); i++) {
            final TopObject object = topics.get(i);
            String objectText = object.getText();// 文本
            findPosition = content.indexOf(objectText);// 获取文本开始下标
            if (findPosition != -1) {// 设置话题内容前景色高亮
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#f55056"));
                editable.setSpan(colorSpan, findPosition, findPosition
                                + objectText.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        }*/

    }

    /**
     * 获取object列表数据
     */
    public List<TopObject> getObjects() {
        List<TopObject> objectsList = new ArrayList<TopObject>();
        // 由于保存时候文本内容添加了匹配字符#,此处去除,还原数据
        if (topics != null && topics.size() > 0) {
            for (int i = 0; i < topics.size(); i++) {
                TopObject object = topics.get(i);
                String objectText = object.getText();
                String objectRule = object.getRule();
                if(objectRule.equals("#")){
                    object.setText(objectText.replace("#", ""));// 将匹配规则字符替换
                }else{
                    object.setText(objectText.replace("@", ""));// 将匹配规则字符替换
                }
                objectsList.add(object);
            }
        }
        return objectsList;
    }




    //添加话题
   public void setData(TopObject object){



       if (object == null)
           return;

    /*   String text = object.getText();
       for (int i = 0; i < topics.size(); i++) {
           if(text.equals(topics.get(i).getText())){

               break;
           }

       }*/

       String objectRule = object.getRule();
       String objectText = object.getText();
       if (TextUtils.isEmpty(objectText) || TextUtils.isEmpty(objectRule))
           return;

       // 拼接字符# %s #,并保存
       if(objectRule.equals("#")){
           objectText = "#"+objectText ;
       }else{
           objectText ="@"+ objectText ;
       }

       object.setText(objectText);

       /**
        * 添加话题<br/>
        * 1.将话题内容添加到数据集合中<br/>
        * 2.将话题内容添加到EditText中展示
        */


       /**
        * 1.添加话题内容到数据集合
        */
       topics.add(object);

       /**
        * 2.将话题内容添加到EditText中展示
        */
    /*   int selectionStart = getSelectionStart();// 光标位置
       Editable editable = getText();// 原先内容

       if (selectionStart >= 0) {
           editable.insert(selectionStart, objectText);// 在光标位置插入内容
           editable.insert(getSelectionStart(), " ");// 话题后面插入空格,至关重要
           setSelection(getSelectionStart());// 移动光标到添加的内容后面
       }
       */
   /*    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#f55056"));
       editable.setSpan(colorSpan, selectionStart, selectionStart+objectText.length()
                       + objectText.length(),
               Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/


   }
}
