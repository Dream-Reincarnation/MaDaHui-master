package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface ChangePhoneIn {
    public interface changPhoneView extends BaseView{
        void vertifySuccess(String success);
        void changeSuccess(String success);
    }
    interface  ChangePhonePresenter{
        void getVertifyData(HashMap<String,String> map);
        void getChangeData(HashMap<String,String> map);
    }
}
