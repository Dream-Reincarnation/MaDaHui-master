package com.ajiani.maidahui.mInterface.dynamic;

import com.ajiani.maidahui.base.BaseView;

import java.util.HashMap;

public interface AddshopIn {
    interface AddshopView extends BaseView{
        void collectionStoreSuccess(String success);
        void collectionStoreError(String error);
        void collectionSuccess(String success);
        void collectionError(String error);
        void shopListSuccess(String success);
        void shopListError(String error);
    }
    interface  AddShopPresenter{
        void getShopList(HashMap<String,String> map);
        void getCollectionStore(HashMap<String,String> map);
        void getCollection(HashMap<String,String> map);
    }
}
