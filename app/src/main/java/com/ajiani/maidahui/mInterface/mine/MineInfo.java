package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface MineInfo {
    interface mineView extends BaseView{
        void videoSuccess(String string);
        void userInfo(String success);
        void loveListSuccess(String success);
        void getCountSuccess(String success);
    }
    interface minePresenter{
        void getVideo(HashMap<String,String> map);
        void getUserInfo(HashMap<String,String> map);
        void getLoveList(HashMap<String,String> map);
        void getCount(HashMap<String,String> map);
    }
}
