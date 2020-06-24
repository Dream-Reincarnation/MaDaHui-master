package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface SearMusic {
    interface searchView extends BaseView{
        //搜索音乐
        void  success(String success);
        void  successInfo(String string);
        //收藏音乐
        void colleectioSuccess(String sucess);
        //获取收藏
        void getCollectionSuccess(String success);

    }
    interface searchPresenter{
        void getData(HashMap<String,String> keyWord);
        void getInfo(HashMap<String,String> id);

        void getCollectionData(HashMap<String,String> map);
        void getCollectionList(HashMap<String,String> map);
        //

    }


}
