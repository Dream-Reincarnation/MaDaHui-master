package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface CommodityListIn {
    interface CommodityListView extends BaseView{
        void commoditySuccess(String success);
    }
    interface CommoDityPresenterIml{
        void getCommodityData(HashMap<String,String> map);
    }
}
