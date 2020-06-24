package com.ajiani.maidahui.mInterface;

import com.ajiani.maidahui.base.BaseView;

import java.io.File;

public interface BaiDuAI {
    interface baiduAIView extends BaseView {
        void success(String success);
    }
    interface baiduAIPresenter{
        void getData(File file);
    }
}
