package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface VerifyIn {
    interface verifyView extends BaseView{
        void mailVerify(String success);
        void phoneVerify(String success);
        void verifySuccess(String success);
    }

    interface verifyPresenter{
        void mailData(HashMap<String,String> map);
        void phoneData(HashMap<String,String> map);
        void verifyData(HashMap<String,String> map);
    }
}
