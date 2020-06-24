package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface DynaIn {
    interface DynaView extends BaseView {
        void videoPageSuccess(String success);
        void videoDetailsSuccess(String success);
        //点赞
        void videoStarSuccess(String success);
        //关注
        void FollowUserSuccess(String success);
        //获取私信的列表
        void getFriendsSuccess(String friendsSuccess);
        //置顶
        void setStick(String stickSuccess);
    }
    interface  dynaPresenter {
        void getVideoPage(HashMap<String,String> map);
        void getVideoDetails(HashMap<String,String> map);
        void getVideoStar(HashMap<String,String> map);
        void getFollowData(HashMap<String,String> map);
        //获取私信的列表
        void getFriendsData(HashMap<String,String> map);
        //设置指定权限
        void getStickData(HashMap<String,String> map);

    }
}
