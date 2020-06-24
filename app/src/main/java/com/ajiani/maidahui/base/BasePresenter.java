package com.ajiani.maidahui.base;

public interface BasePresenter<V extends BaseView> {
     void onAttach(V v);
     void onDestroyAttach();

}
