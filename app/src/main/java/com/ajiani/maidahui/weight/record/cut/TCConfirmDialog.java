package com.ajiani.maidahui.weight.record.cut;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ajiani.maidahui.R;
import com.luck.picture.lib.tools.ScreenUtils;


/**
 * Created by hanszhli on 2017/11/8.
 */

public class TCConfirmDialog extends DialogFragment {
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_MSG = "key_msg";
    private static final String KEY_CANCEL = "key_cancel";
    private static final String KEY_SURE_TXT = "key_sure_txt";
    private static final String KEY_CANCEL_TXT = "key_cancel_txt";

    private TextView mTvTitle,mTvContent, mTvSure, mTvCancel;


    public static TCConfirmDialog newInstance(String title, String msg, boolean isHaveCancel, String sureTxt, String cancalTxt) {
        TCConfirmDialog dialog = new TCConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_MSG, msg);
        bundle.putString(KEY_SURE_TXT, sureTxt);
        bundle.putString(KEY_CANCEL_TXT, cancalTxt);
        bundle.putBoolean(KEY_CANCEL, isHaveCancel);
        dialog.setArguments(bundle);
        return dialog;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null)
                window.setLayout((int) (ScreenUtils.getScreenWidth(dialog.getContext()) * 0.9),//设置宽度最小为 90%
                        WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }

    /**
     * 去掉标题栏
     */
    private void setDialogStyle() {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setDialogStyle();
        return inflater.inflate(R.layout.mine_project, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title = getArguments().getString(KEY_TITLE);
        String msg = getArguments().getString(KEY_MSG, "");
        boolean isHaveCancel = getArguments().getBoolean(KEY_CANCEL, true);
        String cancelTxt = getArguments().getString(KEY_CANCEL_TXT);
        String sureTxt = getArguments().getString(KEY_SURE_TXT);


        if (sureTxt != null) {
            mTvSure.setText(sureTxt);
        }
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mCallback != null)
                    mCallback.onSureCallback();
            }
        });


        if (!isHaveCancel) {
            mTvCancel.setVisibility(View.GONE);
        } else {
            mTvCancel.setVisibility(View.VISIBLE);
        }
        if (cancelTxt != null) {
            mTvCancel.setText(cancelTxt);
        }
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mCallback != null) {
                    mCallback.onCancelCallback();
                }
            }
        });


    }

    private OnConfirmCallback mCallback;

    public void setOnConfirmCallback(OnConfirmCallback callback) {
        mCallback = callback;
    }


    public interface OnConfirmCallback {
        void onSureCallback();

        void onCancelCallback();
    }
}
