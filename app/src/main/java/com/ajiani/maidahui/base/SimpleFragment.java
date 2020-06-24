package com.ajiani.maidahui.base;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.receiver.MyReceiver;
import com.github.ybq.android.spinkit.SpinKitView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class SimpleFragment extends Fragment {
    public Activity mActivity;
    private Unbinder bind;
    public View inflate;
    private Unbinder mBind;
    private MyReceiver myReceiver;

    @Override
    public void onAttach(Context context) {
        isToken();
        super.onAttach(context);

        mActivity = (Activity) context;
    }

    public void isToken() {

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(myReceiver);
    }



    @Override
    public void onResume() {
        super.onResume();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("1");
        getActivity().registerReceiver(myReceiver, filter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(createLayout(), null, false);
        mBind = ButterKnife.bind(this, inflate);
        createPresenter();
        initView();
        initData();
        return inflate;
    }


    protected void createPresenter() {

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int createLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
