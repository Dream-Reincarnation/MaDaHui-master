package com.ajiani.maidahui.weight.dialog;

import android.app.Dialog;
import android.content.Context;


import android.graphics.Rect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.ajiani.maidahui.R;


/**
 * @Author：淘跑
 * @Date: 2018/11/14 17:20
 * @Use：
 */
public class InputDialog extends Dialog {
    public InputDialog(@NonNull Context context) {
     //   super(context);
        super(context, R.style.dialog_soft_input);
        init(context);
    }

    public Context mContext;
    public View mRootView;

    public void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_input, null,false);
        setContentView(mRootView);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
  //      WindowManager.LayoutParams params = window.getAttributes();
      //  params.width = WindowManager.LayoutParams.MATCH_PARENT;
    //     window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
    }

}
