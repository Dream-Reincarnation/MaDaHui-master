package com.ajiani.maidahui.mInterface;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface FindPass  {
    interface findPassView extends BaseView {
        void success(String str);
        void successVerfity(String success);
    }
    interface  findPassPresenter{
        void setData(HashMap<String,String> map);
        void setData2(HashMap<String,String> map);
    }
}
