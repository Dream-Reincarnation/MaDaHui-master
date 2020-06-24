package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface CastIn {
    interface  CastView extends BaseView{
        void castSuccess(String success);
        void detailsSuccess(String success);
    }
    interface casePresenter{
        void getCastData(HashMap<String,String> map);
        void getDetailsData(HashMap<String,String> map);
    }
}
