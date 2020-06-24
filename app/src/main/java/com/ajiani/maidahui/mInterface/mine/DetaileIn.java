package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface DetaileIn  {
    interface detaileView extends BaseView {
        void earSuccess(String success);
    }
    interface detailsPresenter{
        void getEar(HashMap<String,String> map);
    }
}
