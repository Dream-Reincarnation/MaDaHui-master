package com.ajiani.maidahui.mInterface;

import com.ajiani.maidahui.base.BaseView;

import java.io.File;

public interface UploadImg {
    interface upLoadView extends BaseView {
        void sucess(String string);
    }
    interface upLoadPresenter{
        void setData(File file);
    }
}
