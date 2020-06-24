package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface CommentIn  {
    interface  commentView extends BaseView{
        void commentListSuccess(String success);
        //发布评论
        void addComment(String success);
    }
    interface  commentPresenter {
        void commentListData(HashMap<String,String> map);
        void addCommentData(HashMap<String,String> map);
    }
}
