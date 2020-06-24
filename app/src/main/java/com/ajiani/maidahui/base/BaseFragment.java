package com.ajiani.maidahui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ajiani.maidahui.Utils.SPUtils;

public abstract class BaseFragment<V extends BaseView,P extends BasePresenterImp<V>> extends SimpleFragment implements BaseView{
    protected  P mPresenter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String token = (String) SPUtils.get(getActivity(), "token", "");

    }

    @Override
    protected void createPresenter() {
        super.createPresenter();
        mPresenter=preparePresenter();
        if(mPresenter!=null){
            mPresenter.onAttach((V)this);
        }
    }

    @Override
    protected void initView() {

    }

    protected abstract P preparePresenter();
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroyAttach();
            mPresenter = null;
        }
    }
    public void showprogress(){

    }
    public void dissprogress(){
    }
}
