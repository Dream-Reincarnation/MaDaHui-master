package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface VideoCommentIn {
    interface videoCommentView extends BaseView{
        void commentSuccess(String success);
    }
    interface videoCommentPresenter{
        void getData(HashMap<String,String> map);
    }
}
