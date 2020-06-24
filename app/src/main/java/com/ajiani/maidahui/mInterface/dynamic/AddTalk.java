package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface AddTalk {
    interface talkView extends BaseView{
         void talkSuccess(String success);
    }
    interface  talkPresenter{
        void getTalk(HashMap<String,String> map);
    }
}
