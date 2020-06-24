package com.ajiani.maidahui.base;

public abstract class BaseActivity<V extends BaseView,P extends BasePresenterImp<V>> extends SimpleActivity implements BaseView  {
    protected  P mPresenter;
    @Override
    protected void createPresenter() {
        super.createPresenter();

        mPresenter=preparePresenter();
        if(mPresenter!=null){
            mPresenter.onAttach((V)this);
        }
    }

    protected abstract P preparePresenter();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroyAttach();
            mPresenter = null;
        }
    }
    public void showprogress(){
        show();
    }
    public void dissprogress(){
        diss();
    }
}
