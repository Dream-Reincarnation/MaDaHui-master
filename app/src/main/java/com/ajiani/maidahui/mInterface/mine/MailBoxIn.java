package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface MailBoxIn {
    public  interface MainView extends BaseView{
        void mailVertifySuccess(String success);
        void mailbindSuccess(String success);
    }
    interface  mailBoxPresenter{
        void mailVertifyData(HashMap<String,String> map);
        void mailBindData(HashMap<String,String> map);
    }
}
