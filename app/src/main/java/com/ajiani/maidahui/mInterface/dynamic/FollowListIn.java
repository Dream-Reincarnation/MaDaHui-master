package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface FollowListIn {
    interface followListView extends BaseView{
        //获取关注列表
        void followListSuccess(String success);
        //获取粉丝列表
        void fansListSuccess(String success);
        //取消关注
        void followUser(String success);

    }
    interface followListPresenter{
        void followListData(HashMap<String,String> map);
        void fansListData(HashMap<String,String> map);
        void followUserData(HashMap<String,String> map);
    }
}
