package com.ajiani.maidahui.mInterface;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface Payin {
    interface  payView extends BaseView{
        void getSuccess(String success);
        void createSuccess(String create);
        void parameterSuccess(String parameter);
        void voteSuccess(String success);

        void weChatSuccess(String success);
    }
    interface payPresenter {
        void getRule(HashMap<String,String> map);
        void getOrder(HashMap<String,String> map);
        void getPay(HashMap<String,String> map);
        void getVote(HashMap<String,String> map);
        void getWeChat(HashMap<String,String> map);
    }
}
