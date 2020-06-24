package com.ajiani.maidahui.base;



import java.lang.ref.WeakReference;

public abstract class BasePresenterImp<V extends BaseView> implements BasePresenter<V> {
   public V mView;
    private WeakReference<V> weakReference;
    @Override
    public void onAttach(V v) {
        if (weakReference == null) {
            weakReference = new WeakReference<V>(v);
            mView = weakReference.get();
        }
    }

    @Override
    public void onDestroyAttach() {
            if (weakReference != null) {
                V v = weakReference.get();
                v=null;
                weakReference.clear();
                weakReference = null;

            }
    }
}
