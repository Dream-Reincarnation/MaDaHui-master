package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;

public interface AutonymIn {
    interface AutonymView extends BaseView {
        void authenticaSuccess(String success);
        void checkAutonym(String success);
        void upLoadSuccess(String s);
    }

    interface AntonymPresenter {

        void getAutony(HashMap<String, String> map);
        void getAutonymInfo(HashMap<String,String> map);
        void getUpLoadData(File file);
    }
}
