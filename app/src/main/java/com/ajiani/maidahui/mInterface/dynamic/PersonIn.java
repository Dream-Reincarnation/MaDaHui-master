package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface PersonIn {
    interface PersonView extends BaseView {
        void success(String success);
    }
    interface PersonPresenters{
        void getData(HashMap<String,String> map);
    }
}
