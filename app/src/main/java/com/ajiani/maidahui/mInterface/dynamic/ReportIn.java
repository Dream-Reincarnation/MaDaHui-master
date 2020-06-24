package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface ReportIn {

    interface reportView extends BaseView{
        void reportSuccess(String success);
    }

    interface reportPresenter {
        void getReportData(HashMap<String,String> map);
    }
}
