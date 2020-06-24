package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface IntegralIn {
    interface IntegralView  extends BaseView{
        void integralSuccess(String success);
        //余额详情
        void restantSuccess(String success);
    }
    interface integralPresenter{
        void getData(HashMap<String,String> map);

        void getRestant(HashMap<String,String> map);
    }
}
