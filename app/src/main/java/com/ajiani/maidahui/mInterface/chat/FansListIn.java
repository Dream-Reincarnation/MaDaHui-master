package com.ajiani.maidahui.mInterface.chat;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface FansListIn {
    interface fansListView extends BaseView{
        void fansSuccess(String success);
    }
    interface  fansListPresenter{
        void getFansData(HashMap<String,String> map);
    }
}
