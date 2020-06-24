package com.ajiani.maidahui.mInterface.chat;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface SystemList  {
    interface systemListView extends BaseView {
        void systemListSuccess(String success);
    }

    interface systemListPresenter{
        void getSystemList(HashMap<String,String> map);
    }
}
