package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;
import com.ajiani.maidahui.bean.dynamic.shop.CommodityInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public interface ReleaseMin {
     interface releaseView extends BaseView{
        void success(String string);
        void sendSuccess(String success);
            void uploadSuccess(String success);
        void ossSuccess(String success);
    }
    interface releasePresenter{
         //上传图片
         void setData(File file);
         void getData(HashMap<String,String> map);
         //获取阿里云是否存在
         void upload(HashMap<String,String> map);
         void ossVideo(HashMap<String,String> map,File file);
         //发布时带标签
         void getData2(HashMap<String,String> map, ArrayList<CommodityInfo> list);

    }
}
