package com.ajiani.maidahui.mInterface.chat;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface ShopListIn {
    public interface shopMessageView extends BaseView{
        void shopMessageSuccess(String success);
        void delMessageSuccess(String success);
        void delMsgSuccess(String success);
    }
    interface shopMessagePresenter{
        void getShopMessage(HashMap<String,String> map);
        void getDelData(HashMap<String,String> map);
        void getDelMsgData(HashMap<String,String> map);
    }
}
