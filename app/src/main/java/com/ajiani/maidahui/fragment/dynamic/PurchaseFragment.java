package com.ajiani.maidahui.fragment.dynamic;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;

public class PurchaseFragment extends BaseFragment {
    @Override
    protected BasePresenterImp preparePresenter() {
        return null;
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_complete;
    }
}