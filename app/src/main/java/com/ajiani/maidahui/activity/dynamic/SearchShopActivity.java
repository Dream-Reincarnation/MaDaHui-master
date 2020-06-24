package com.ajiani.maidahui.activity.dynamic;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.adapter.dynamic.AddshopVpAdapter;
import com.ajiani.maidahui.adapter.dynamic.TagAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.fragment.dynamic.PurchaseFragment;
import com.ajiani.maidahui.fragment.dynamic.addshop.CollectionFragment;
import com.ajiani.maidahui.fragment.dynamic.addshop.CollectionStoreFragment;
import com.ajiani.maidahui.fragment.dynamic.addshop.CompleteFragment;
import com.ajiani.maidahui.fragment.dynamic.searchshop.CollectionFragment2;
import com.ajiani.maidahui.fragment.dynamic.searchshop.CollectionStoreFragment2;
import com.ajiani.maidahui.fragment.dynamic.searchshop.CompleteFragment2;
import com.ajiani.maidahui.mInterface.dynamic.AddshopIn;
import com.ajiani.maidahui.presenters.dynamic.AddShopPresenter;
import com.google.android.material.tabs.TabLayout;
import com.xugter.xflowlayout.XFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchShopActivity extends BaseActivity<AddshopIn.AddshopView, AddShopPresenter> implements AddshopIn.AddshopView {

    @BindView(R.id.friends_search)
    ImageView friendsSearch;

    @BindView(R.id.search_shop_tv)
    TextView searchShopTv;

    public FrameLayout searchShopSearch;
    @BindView(R.id.search_shop_del)
    FrameLayout searchShopDel;
    @BindView(R.id.search_shop_cacle)
    FrameLayout searchShopCancle;
    @BindView(R.id.search_shop_flow)
    XFlowLayout searchShopFlow;
    @BindView(R.id.search_shop_lin)
    LinearLayout searchShopLin;
    public  LinearLayout searchShopHistorylin;
    @BindView(R.id.addshop_tab)
    TabLayout addshopTab;
    @BindView(R.id.addshop_vp)
    ViewPager addshopVp;
    @BindView(R.id.addshop_shop)
    TextView addshopShop;
   public  LinearLayout searchShopLin2;
    private int dimensionPixelSize;
    private TagAdapter tagAdapter;
    public TextView addshop;
    public EditText friednsEd;
    public ImageView friendsDel;
    private String history;
    public  ArrayList<String> strings;
    private CompleteFragment2 completeFragment;
    private CollectionStoreFragment2 collectionStoreFragment;
    private CollectionFragment2 collectionFragment;


    @Override
    protected AddShopPresenter preparePresenter() {
        return new AddShopPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

        friednsEd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("wxy", "onKey: "+keyCode);
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                  data();
                    return true;
                }
                return false;
            }
        });







    }




    @Override
    protected void initView() {
        addshop = findViewById(R.id.addshop_shop);
        friednsEd = findViewById(R.id.friedns_ed);
        friendsDel = findViewById(R.id.friends_del);
        searchShopSearch = findViewById(R.id.search_shop_search);
        searchShopHistorylin = findViewById(R.id.search_shop_historylin);
        searchShopLin2 = findViewById(R.id.search_shop_lin2);
        //我还是充钱的那个少年，没有一丝丝改变
      /*  SharedPreferences agsin = getSharedPreferences("agsin", MODE_PRIVATE);
        history = agsin.getString("search", "");*/
        //得到搜索历史记录
        history = (String) SPUtils.get(this, "searchhistory", "");
        strings = new ArrayList<String>();
        if (history.length() > 3) {
            String[] split = history.split(",");
            for (int i = 0; i < split.length; i++) {
                strings.add(split[i]);
            }
        }
        tagAdapter = new TagAdapter(strings, this);
        searchShopFlow.setAdapter(tagAdapter);

        tagAdapter.setOnClickLinstener(new TagAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                //点击事件
                friednsEd.setText(tagAdapter.mList.get(posstion));
                 data();

            }
        });

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            dimensionPixelSize = getResources().getDimensionPixelSize(resourceId);
        }
        searchShopLin.setPadding(0, dimensionPixelSize, 0, 0);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //

        friednsEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //进行网络请求

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = friednsEd.getText().toString();

                if (s1.length() <= 0 || s1.equals("")) {
                   searchShopHistorylin.setVisibility(View.VISIBLE);
                   searchShopLin2.setVisibility(View.GONE);
                    friendsDel.setVisibility(View.GONE);

                    searchShopCancle.setVisibility(View.VISIBLE);
                    searchShopSearch.setVisibility(View.GONE);
                } else {
                    friendsDel.setVisibility(View.VISIBLE);
                    searchShopCancle.setVisibility(View.GONE);
                    searchShopSearch.setVisibility(View.VISIBLE);
                }
            }
        });


        if (AddShopActivity.dataBeans.size() > 0) {
            addshop.setText("加入商品货架(" + AddShopActivity.dataBeans.size() + "件)");
            addshop.setBackgroundResource(R.drawable.buttonred);
        } else {
            addshop.setText("加入商品货架");
            addshop.setBackgroundResource(R.drawable.buttonredun);
        }

    }


    @Override
    protected int createLayout() {
        return R.layout.activity_searchshop;
    }

    @Override
    public void collectionStoreSuccess(String success) {

    }

    @Override
    public void collectionStoreError(String error) {

    }

    @Override
    public void collectionSuccess(String success) {

    }

    @Override
    public void collectionError(String error) {

    }

    @Override
    public void shopListSuccess(String success) {

    }

    @Override
    public void shopListError(String error) {

    }


    @OnClick({R.id.friends_del,R.id.search_shop_cacle,R.id.search_shop_search,R.id.addshop_shop ,R.id.friedns_ed,R.id.search_shop_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.friends_del:
                //
                friednsEd.setText("");
                friednsEd.setHint("试试搜 商品/店铺");
                break;
            case R.id.search_shop_search:
                //点击搜素 ,存入数据

                    data();

                break;
            case R.id.search_shop_cacle:
                finish();
                break;
            case R.id.friedns_ed:
                searchShopHistorylin.setVisibility(View.VISIBLE);
                searchShopLin2.setVisibility(View.GONE);
                break;

            case R.id.search_shop_del:
                //刪除历史记录
                SPUtils.remove(this, "searchhistory");
                tagAdapter.mList.clear();
                tagAdapter  .notifyDataChanged();
                break;
            case R.id.addshop_shop:
                EventBus.getDefault().postSticky(AddShopActivity.dataBeans);
                JumpUtils.gotoActivity(this,VideoReleaaseActivity.class);

                break;
        }
    }

     public void data(){

         String s = friednsEd.getText().toString();
         if(s.length()<1||s.equals("")){
             Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
             return;
         }
         if (strings.size() < 1) {
             strings.add(s);
         } else {
             for (int i = 0; i < strings.size(); i++) {
                 if (s.equals(strings.get(i))) {
                     strings.remove(i);
                     strings.add(0, s);
                     break;
                 } else if (i == strings.size() - 1) {
                     if (strings.size() >= 8) {
                         strings.remove(7);
                         strings.add(0, s);
                     } else {
                         strings.add(0, s);
                     }
                     break;
                 }
             }
         }

         StringBuffer stringBuffer = new StringBuffer();
         for (int i = 0; i < strings.size(); i++) {
             if (i == strings.size() - 1) {
                 stringBuffer.append(strings.get(i));
                 break;
             }
             stringBuffer.append(strings.get(i) + ",");
         }
         SPUtils.put(this, "searchhistory", stringBuffer.toString());
         //进项搜索
         searchShopHistorylin.setVisibility(View.GONE);
         searchShopLin2.setVisibility(View.VISIBLE);
         InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
         imm.hideSoftInputFromWindow(friednsEd.getWindowToken(), 0);
         friednsEd.clearFocus();//取消焦点
         ArrayList<String> strings = new ArrayList<>();
         strings.add("全部");
         strings.add("收藏店铺");
         strings.add("我收藏的");
         strings.add("我购买的");
         ArrayList<Fragment> fragments = new ArrayList<>();
         completeFragment = new CompleteFragment2(s);
         collectionStoreFragment = new CollectionStoreFragment2(s);
         collectionFragment = new CollectionFragment2(s);
         fragments.add(completeFragment);
         fragments.add(collectionStoreFragment);
         fragments.add(collectionFragment);
         fragments.add(new PurchaseFragment());
         AddshopVpAdapter personVpAdapter = new AddshopVpAdapter(getSupportFragmentManager(), fragments, strings);
         addshopVp.setOffscreenPageLimit(3);
         addshopVp.setAdapter(personVpAdapter);
         addshopTab.setupWithViewPager(addshopVp);

     }

}
