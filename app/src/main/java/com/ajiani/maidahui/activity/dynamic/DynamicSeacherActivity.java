package com.ajiani.maidahui.activity.dynamic;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.FragmentAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.fragment.dynamic.SearchFragment;
import com.ajiani.maidahui.mInterface.dynamic.DynamicIn;
import com.ajiani.maidahui.presenters.dynamic.DynamicPresenter;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
 //动态搜索页面
public class DynamicSeacherActivity extends BaseActivity<DynamicIn.videoListView, DynamicPresenter> implements DynamicIn.videoListView {


    @BindView(R.id.dynasear_search)
    ImageView dynasearSearch;



    public static ViewPager dynaVp;
     private EditText ed;
     public static EditText ed1;
     public static TextView search;
     public static TabLayout dynaTab;
     @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

     @Override
     protected void onDestroy() {
         super.onDestroy();
         ed1=null;
         search=null;
         dynaTab=null;
         dynaVp=null;
     }

     @Override
    protected void initView() {

        ed1 = findViewById(R.id.dynasear_ed);
        search = findViewById(R.id.dynasear_seartv);
        dynaTab = findViewById(R.id.dyna_tab);
        dynaVp= findViewById(R.id.dyna_vp);
        DynamicSeacherActivity.dynaVp.setVisibility(View.GONE);
         DynamicSeacherActivity.dynaTab.setVisibility(View.GONE);
        //绑定适配器啦啦啦
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("综合");
        strings.add("最新");
        strings.add("热门");
        strings.add("点击量");
        strings.add("火力");
        //fragments.add(new AllFragment("综合"));
        for (int i =0; i <5; i++) {
            fragments.add(new SearchFragment(strings.get(i)));

        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),strings ,fragments);
        dynaVp.setAdapter(fragmentAdapter);
        dynaTab.setupWithViewPager(dynaVp);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_dynasearch;
    }

    @Override
    protected DynamicPresenter preparePresenter() {
        return new DynamicPresenter();
    }

    @Override
    public void listSuccess(String success) {

    }

     @Override
     public void followList(String success) {

     }


     @OnClick({R.id.dynasear_back, R.id.dynasear_ed,R.id.dynasear_seartv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dynasear_back:
                finish();
                break;
            case R.id.dynasear_ed:
                break;
            case R.id.dynasear_seartv:
                DynamicSeacherActivity.dynaVp.setVisibility(View.VISIBLE);
                DynamicSeacherActivity.dynaTab.setVisibility(View.VISIBLE);
                //进行网络请求
                String s = ed1.getText().toString();
                EventBus.getDefault().postSticky(s);
                break;
        }
    }
    public static void set(){
       /* DynamicSeacherActivity.dynaVp.setVisibility(View.VISIBLE);
        DynamicSeacherActivity.dynaTab.setVisibility(View.VISIBLE);*/
    }
}
