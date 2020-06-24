package com.ajiani.maidahui.mInterface.chat;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface MessageList {
    interface  MessageView extends BaseView{
        void UserListSuccess(String sucess);
        //客服聊天对象
       void serviceListSuccess(String success);
       //获取消息数量
        void systemSuccess(String success);
        //删除聊天对象
        void delMessageSuccess(String success);
        //删除客服聊天对象
        void delShopMessageSuccess(String success);
    }
    interface MessagePresenter{
        void getUserList(HashMap<String,String> map);
        void getServiceList(HashMap<String,String> map);
        void getSystemList(HashMap<String,String> map);
        void getDelMessage(HashMap<String,String> map);
        void getData(HashMap<String,String> map);
    }
}
