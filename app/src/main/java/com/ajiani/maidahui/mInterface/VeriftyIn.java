package com.ajiani.maidahui.mInterface;

import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.base.BaseView;
import com.ajiani.maidahui.bean.login.LoginBean;
import com.ajiani.maidahui.bean.login.VeriftyBean;


import java.util.HashMap;

public interface VeriftyIn {
    interface VeriftyPresenter{
        void getData(String phone);
        void getLogin(HashMap<String,String> map);
    }
   interface  VeriftyView extends BaseView{
        void setSuccess(VeriftyBean veriftyBean);
        void setLoginSuccess(LoginBean  s);
   }
}
