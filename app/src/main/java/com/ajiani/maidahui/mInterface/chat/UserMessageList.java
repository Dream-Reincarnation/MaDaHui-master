package com.ajiani.maidahui.mInterface.chat;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface UserMessageList  {
    interface MessageListView extends BaseView{
       void messageSuccess(String success);
       //客服评价
       void serviceCommentSuccess(String success);
       //客服聊天记录、
        void serviceMessageSucess(String success);
        //小额转账
        void voteSuccess(String success);
        void delMessageSuccess(String success);
    };
    interface messagePresenter{
        void getMessageList(HashMap<String,String> map);
        void getServiceCommnet(HashMap<String,String> map);
        void getServiceMessage(HashMap<String,String> map);
        void getVoteData(HashMap<String,String> map);
        void getDelData(HashMap<String,String> map);
    }
}
