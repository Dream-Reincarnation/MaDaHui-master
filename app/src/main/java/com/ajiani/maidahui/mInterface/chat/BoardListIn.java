package com.ajiani.maidahui.mInterface.chat;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface BoardListIn {

    interface boardListView extends BaseView{
        void boardListSuccess(String sucess);
        void delMessage(String success);
    }
    interface boardListPresenter{
        void boardListData(HashMap<String,String> map);
        void getDelData(HashMap<String,String> map);
    }
}
