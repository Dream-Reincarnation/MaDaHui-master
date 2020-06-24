package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface FriendListIn {
    interface friendsListView extends BaseView {
       void friendsListSuccess(String success);
       void friendsListError(String error);
    }

    interface friendsListPresenter {
        void getData(HashMap<String,String> map);
    }
}
