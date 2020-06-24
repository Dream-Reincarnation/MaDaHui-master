package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface VideoInfoIn {
    interface videoInfoView extends BaseView{
        void getVideoData(String success);
        //得到评论
        void getCommentSuccess(String success);
        //点赞视频
        void videoStarSuccess(String success);
        //关注
        void FollowUserSuccess(String success);
    }
    interface videoPresenter{
        void getData(HashMap<String,String> map);
        void getCommentData(HashMap<String,String> map);
        void getVideoStar(HashMap<String,String> map);
        void getFollowData(HashMap<String,String> map);
    }
}
