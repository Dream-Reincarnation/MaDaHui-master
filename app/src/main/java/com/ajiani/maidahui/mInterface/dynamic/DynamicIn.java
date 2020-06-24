package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface DynamicIn {
    interface videoListView extends BaseView{
        void  listSuccess(String success);
        void followList(String success);
    }
    interface videoPresenter{
        void getListData(HashMap<String,String> map);
        void getFollowData(HashMap<String,String> map);
    }
}
