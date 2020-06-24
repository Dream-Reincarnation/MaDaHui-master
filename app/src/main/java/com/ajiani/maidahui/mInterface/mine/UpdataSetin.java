package com.ajiani.maidahui.mInterface.mine;

import com.ajiani.maidahui.base.BaseView;

import java.io.File;
import java.util.HashMap;

public interface UpdataSetin {
    interface upSetView extends BaseView {
        void success(String success);
        void upLoadSuccess(String success);
    }
    interface  upSetPresenter{
        void getData(HashMap<String,String> map);
        void upLoadData(File file);
    }
}
