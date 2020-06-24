package com.ajiani.maidahui.activity.dynamic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.adapter.dynamic.AddshopVpAdapter;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.dynamic.CompleteBean;
import com.ajiani.maidahui.fragment.dynamic.addshop.CollectionFragment;
import com.ajiani.maidahui.fragment.dynamic.addshop.CollectionStoreFragment;
import com.ajiani.maidahui.fragment.dynamic.addshop.CompleteFragment;
import com.ajiani.maidahui.fragment.dynamic.PurchaseFragment;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AddShopActivity extends SimpleActivity {
    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.addshop_lin)
    LinearLayout addShopLin;


    @BindView(R.id.addshop_tab)
    TabLayout addshopTab;
    @BindView(R.id.addshop_vp)
    ViewPager addshopVp;
    @BindView(R.id.addshop_shop)
        public TextView addshop;
        public EditText friednsEd;
       public ImageView friendsDel;
    @BindView(R.id.addshop_cancle)
    TextView addshopCancle;
    private int dimensionPixelSize;
    public static ArrayList<CompleteBean.DataBean> dataBeans = new ArrayList<>();
    private CompleteFragment completeFragment;
    private CollectionStoreFragment collectionStoreFragment;
    private CollectionFragment collectionFragment;

   public boolean isSecond;
    @Override
    protected void initData() {

        addshopTab.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    if(completeFragment!=null){
                        collectionFragment.setData();
                    }
                }
                friednsEd.setText("");
                addshopCancle.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
                friednsEd.clearFocus();//取消焦点
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(friednsEd.getWindowToken(), 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        friednsEd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    back.setVisibility(View.GONE);
                    addshopCancle.setVisibility(View.VISIBLE);
                } else {
                }
            }
        });


        addshopTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // TextView inflate = (TextView) LayoutInflater.from(PersonActivity.this).inflate(R.layout.tab_text, null, false);
                //inflate.setText(tab.getText());
                //inflate.setTextColor(ContextCompat.getColor(PersonActivity.this, R.color.Thme));
                //tab.setCustomView(inflate);
                addshopTab.setTabIndicatorFullWidth(false);
                TextView inflate = (TextView) LayoutInflater.from(AddShopActivity.this).inflate(R.layout.tab_textehite, null, false);
                /*    inflate.setTextSize(17);*/
                inflate.setTextColor(ContextCompat.getColor(AddShopActivity.this, R.color.Thme));
                //inflate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                inflate.getPaint().setFakeBoldText(true);
                inflate.setText(tab.getText());
                inflate.getPaint().setFakeBoldText(true);
                tab.setCustomView(inflate);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                addshopTab.setTabIndicatorFullWidth(false);
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                addshopTab.setTabIndicatorFullWidth(false);
            }
        });
    }

    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
        protected void initView() {
            dataBeans.clear();
            if(VideoReleaaseActivity.dataBeans!=null){
                dataBeans.addAll(VideoReleaaseActivity.dataBeans);
                if(VideoReleaaseActivity.dataBeans.size()>0){
                    isSecond=true;
                }else{

                }
            }

            addshop = findViewById(R.id.addshop_shop);
            friednsEd = findViewById(R.id.friedns_ed);
            friendsDel = findViewById(R.id.friends_del);
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                dimensionPixelSize = getResources().getDimensionPixelSize(resourceId);
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) addShopLin.getLayoutParams();

            addShopLin.setPadding(0, dimensionPixelSize, 0, 0);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ArrayList<String> strings = new ArrayList<>();
            strings.add("全部");
            strings.add("收藏店铺");
            strings.add("我收藏的");
            strings.add("我购买的");
            ArrayList<Fragment> fragments = new ArrayList<>();
            completeFragment = new CompleteFragment();
            collectionStoreFragment = new CollectionStoreFragment();
            collectionFragment = new CollectionFragment();
            fragments.add(completeFragment);
            fragments.add(collectionStoreFragment);
            fragments.add(collectionFragment);
            fragments.add(new PurchaseFragment());
            AddshopVpAdapter personVpAdapter = new AddshopVpAdapter(getSupportFragmentManager(), fragments, strings);
            addshopVp.setOffscreenPageLimit(3);
            addshopVp.setAdapter(personVpAdapter);
            addshopTab.setupWithViewPager(addshopVp);
            if (dataBeans.size() > 0) {
                addshop.setText("加入商品货架(" + dataBeans.size() + "件)");
                addshop.setBackgroundResource(R.drawable.buttonred);
            } else {
                addshop.setText("加入商品货架");
                addshop.setBackgroundResource(R.drawable.buttonredun);
            }
            if(isSecond){
                addshop.setText("更新商品货架(" +dataBeans.size() + "件)");
            }
        }

    @Override
    protected int createLayout() {
        return R.layout.activity_addshop;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (dataBeans.size() > 0) {
            addshop.setText("加入商品货架(" + dataBeans.size() + "件)");
            addshop.setBackgroundResource(R.drawable.buttonred);
        } else {
            addshop.setText("加入商品货架");
            addshop.setBackgroundResource(R.drawable.buttonredun);
        }
        if(isSecond){
            addshop.setText("更新商品货架(" +dataBeans.size() + "件)");
        }

        completeFragment.syna();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick({R.id.back, R.id.addshop_shop,R.id.friedns_ed,R.id.friends_del, R.id.addshop_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.addshop_shop:
                EventBus.getDefault().postSticky(dataBeans);
                dataBeans.clear();
                finish();
                break;
            case R.id.friends_del:
                break;
            case R.id.addshop_cancle:
                back.setVisibility(View.VISIBLE);
                addshopCancle.setVisibility(View.GONE);
                friednsEd.clearFocus();//取消焦点
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(friednsEd.getWindowToken(), 0);
                friednsEd.setText("");
                break;

            case R.id.friedns_ed:
                 //跳转搜索页面
                JumpUtils.gotoActivity(this,SearchShopActivity.class);
                break;
        }
    }


}
