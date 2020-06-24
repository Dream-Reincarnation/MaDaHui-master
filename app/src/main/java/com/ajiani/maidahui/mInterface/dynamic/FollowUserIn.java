package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface FollowUserIn  {
    interface  followUserView extends BaseView{
       void FollowUserSuccess(String success);
    }
    interface followPresenter{
        void getFollowData(HashMap<String,String> map);
    }
}
