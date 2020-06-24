package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface AddressIn {
    public  interface AddressView extends BaseView{
        void addressSuccess(String success);
        void addressError(String error);
    }
    interface AddressPresenter {
        void getData(HashMap<String,String>  map);
    }
}
