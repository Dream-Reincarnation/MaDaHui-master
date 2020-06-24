package com.ajiani.maidahui;

import android.widget.ImageView;

import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.mInterface.ParamsIn;
import com.ajiani.maidahui.presenters.ParamsPresenter;

import java.util.HashMap;

import butterknife.BindView;

public class StartActivity extends BaseActivity<ParamsIn.ParamsView, ParamsPresenter> implements ParamsIn.ParamsView {
    @BindView(R.id.start_img)
    ImageView startImg;

   String str;
    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

        HashMap<String, String> hashMap = new HashMap<>();
        mPresenter.getParamData(hashMap);
    }

    @Override
    protected void initView() {



    }

    @Override
    protected int createLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void paramSuccess(String success) {
       /* Uri data =getIntent().getData();
        if(data!=null){
            String url = data.getQueryParameter("url");
            Log.i("wxy", "onResume: asdsadadds" + url);
            if(url!=null&&!url.equals("")) {
                byte[] decode = Base64.decode(url, Base64.URL_SAFE);
                String s = new String(decode);
                String[] split = s.split("\\?");

                if(s.contains("app://share/goods/info")){
                    str=   split[1]+"&outfrom=1";
                    Log.i("wxy", "onResume: " + str);
                    Bundle bundle = new Bundle();

                    bundle.putString("url",split[1]+"&outfrom=1");
                    JumpUtils.gotoActivity(this, HomeActivity.class,bundle);
                    finish();
                }
                SPUtils.put(this, "isfirstApp", true);
            }
        }else{
            Bundle bundle = new Bundle();

            bundle.putString("url",str);
            JumpUtils.gotoActivity(this, HomeActivity.class,bundle);
            finish();
        }*/

    }

    @Override
    protected ParamsPresenter preparePresenter() {
        return new ParamsPresenter();
    }
}
