package com.ajiani.maidahui.mInterface;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface ParamsIn  {
    interface  ParamsView extends BaseView{
        void paramSuccess(String success);
    }
    interface paramsPresenter {
        void getParamData(HashMap<String,String> map);
    }
}
