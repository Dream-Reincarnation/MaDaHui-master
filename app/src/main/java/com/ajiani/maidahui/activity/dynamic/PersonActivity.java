package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.chat.ChatActivity;
import com.ajiani.maidahui.activity.chat.FansActivity;
import com.ajiani.maidahui.adapter.dynamic.PersonVpAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.dynamic.PersonBean;
import com.ajiani.maidahui.fragment.dynamic.DyLikeFragment;
import com.ajiani.maidahui.fragment.dynamic.DySinalFragment;
import com.ajiani.maidahui.fragment.dynamic.DyWorkFragment;
import com.ajiani.maidahui.mInterface.dynamic.PersonIn;
import com.ajiani.maidahui.presenters.dynamic.PersonPresenter;
import com.ajiani.maidahui.weight.mine.ScaleScrollView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class  PersonActivity extends BaseActivity<PersonIn.PersonView, PersonPresenter> implements PersonIn.PersonView,ScaleScrollView.OnScrollChangeListener {

    @BindView(R.id.pson_head)
    ImageView psonHead;
    @BindView(R.id.person_name)
    TextView psonName;
    //签名
    @BindView(R.id.qianm)
    TextView psonSign;
    @BindView(R.id.pson_chat2)
    TextView psonChat2;
    @BindView(R.id.pson_unatten)
    ImageView pson_unatten;

    @BindView(R.id.person_id)
    TextView person_id;
    @BindView(R.id.pson_goodnum)
    TextView psonGoodnum;
    @BindView(R.id.pson_lin1)
    LinearLayout psonLin1;
    @BindView(R.id.pson_fennum)
    TextView psonFennum;
    @BindView(R.id.pson_lin2)
    LinearLayout psonLin2;
    @BindView(R.id.pson_guannum)
    TextView psonGuannum;
    @BindView(R.id.pson_lin3)
    LinearLayout psonLin3;
    @BindView(R.id.pson_guan)
    TextView psonGuan;
    @BindView(R.id.pson_chat)
    TextView psonChat;
    /* @BindView(R.id.pson_name)
     TextView psonName;
     @BindView(R.id.pson_seximg)
     ImageView psonSeximg;*/
    @BindView(R.id.pson_sex)
    TextView psonSex;
    @BindView(R.id.pson_eare)
    TextView psonEare;
    @BindView(R.id.pson_city)
    TextView psonCity;
    @BindView(R.id.pson_location)
    ImageView psonLocation;
    @BindView(R.id.pson_km)
    TextView psonKm;
    @BindView(R.id.person_lin)
    LinearLayout perlin;
    /*@BindView(R.id.pson_id)
    TextView psonId;*/

    @BindView(R.id.pson_tab)
    TabLayout psonTab;
    @BindView(R.id.pson_vp)
    ViewPager psonVp;
    @BindView(R.id.pson_share)
    ImageView psonShare;
   /* @BindView(R.id.pson_more)
    ImageView psonMore;*/

    @BindView(R.id.person_sroll)
    ScaleScrollView scrollview;
    boolean b = true;
    float i2;
    float i3 = 0.02f;
    public static String id;
    @BindView(R.id.pson_tabtop)
    TabLayout psonTabtop;
    @BindView(R.id.rela_prttop)
    RelativeLayout relaPrttop;
    private PersonBean.DataBean data;



    @Override
    protected void initData() {

        scrollview.setOnScrollChangeListener(this);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user_id", id);
        hashMap.put("longitude", HomeActivity.lon);
        hashMap.put("latitude", HomeActivity.lat);
        mPresenter.getData(hashMap);
    }


    private int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        id = bundle.getString("id");
        Glide.with(this).load(R.mipmap.head).apply(new RequestOptions().circleCrop()).into(psonHead);
        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        //3015723448
   /*     strings.add("音乐");
        strings.add("特效");*/
        strings.add("Vlog");
        strings.add("种草");
        strings.add("喜欢");
        /*fragments.add(new DyMusicFragment());
        fragments.add(new DySpecialFragment());*/
        fragments.add(new DyWorkFragment());
        fragments.add(new DySinalFragment());
        fragments.add(new DyLikeFragment());
        PersonVpAdapter personVpAdapter = new PersonVpAdapter(getSupportFragmentManager(), fragments, strings);
        psonVp.setAdapter(personVpAdapter);
        psonTab.setupWithViewPager(psonVp);
        psonTabtop.setupWithViewPager(psonVp);
        psonTabtop.setTabIndicatorFullWidth(false);
        psonTab.setTabIndicatorFullWidth(false);
        psonTabtop.setTabIndicatorFullWidth(false);
        psonTab.setTabIndicatorFullWidth(false);

        LinearLayout linearLayout = (LinearLayout) psonTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(PersonActivity.this,
                R.mipmap.mine_square));
        linearLayout.setDividerPadding(dp2px(15));
        LinearLayout linearLayout1 = (LinearLayout) psonTabtop.getChildAt(0);
        linearLayout1.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout1.setDividerDrawable(ContextCompat.getDrawable(PersonActivity.this,
                R.mipmap.mine_square));
        linearLayout1.setDividerPadding(dp2px(15));
        psonTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // TextView inflate = (TextView) LayoutInflater.from(PersonActivity.this).inflate(R.layout.tab_text, null, false);
                //inflate.setText(tab.getText());
                //inflate.setTextColor(ContextCompat.getColor(PersonActivity.this, R.color.Thme));
                //tab.setCustomView(inflate);
                psonTab.setTabIndicatorFullWidth(false);
                TextView inflate = (TextView) LayoutInflater.from(PersonActivity.this).inflate(R.layout.tab_textehite, null, false);
                inflate.setTextSize(17);
                inflate.setTextColor(ContextCompat.getColor(PersonActivity.this, R.color.home_red));
                inflate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                inflate.setText(tab.getText());

                tab.setCustomView(inflate);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                psonTab.setTabIndicatorFullWidth(false);
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                psonTab.setTabIndicatorFullWidth(false);
            }
        });
        psonTabtop.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // TextView inflate = (TextView) LayoutInflater.from(PersonActivity.this).inflate(R.layout.tab_text, null, false);
                //inflate.setText(tab.getText());
                //inflate.setTextColor(ContextCompat.getColor(PersonActivity.this, R.color.Thme));
                //tab.setCustomView(inflate);
                psonTabtop.setTabIndicatorFullWidth(false);
                TextView inflate = (TextView) LayoutInflater.from(PersonActivity.this).inflate(R.layout.tab_textehite, null, false);
                inflate.setTextSize(17);
                inflate.setTextColor(ContextCompat.getColor(PersonActivity.this, R.color.home_red));
                inflate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                inflate.setText(tab.getText());
                tab.setCustomView(inflate);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                psonTabtop.setTabIndicatorFullWidth(false);
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                psonTabtop.setTabIndicatorFullWidth(false);
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.person;
    }

    @OnClick({R.id.pson_head,R.id.pson_chat2, R.id.pson_back,R.id.pson_unatten, R.id.pson_share, R.id.pson_guan, R.id.pson_lin3, R.id.pson_lin2, R.id.pson_lin1})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.pson_chat2:
                Bundle bundle = new Bundle();
                bundle.putString("name",data.getNickname());
                bundle.putString("uid",data.getId()+"");
                JumpUtils.gotoActivity(this, ChatActivity.class,bundle);
                break;
            case R.id.pson_unatten:
                //取消关注 弹出弹窗
                PopupWindow popupWindow = new PopupWindow(this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                View inflate = LayoutInflater.from(this).inflate(R.layout.unatten, null, false);
                popupWindow.setContentView(inflate);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setTouchable(true);
                inflate.findViewById(R.id.icon_btn_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.ll_pop).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.icon_btn_select).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //取消关注
                        String s = HttpUtils.instance().followUser(id);

                        // Toast.makeText(this, "取消成功", Toast.LENGTH_SHORT).show();
                        data.setIs_follow("0");
                        psonChat.setVisibility(View.VISIBLE);
                        psonGuan.setVisibility(View.VISIBLE);
                        psonChat2.setVisibility(View.GONE);
                        pson_unatten.setVisibility(View.GONE);


                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAtLocation(psonHead, Gravity.BOTTOM,0,0);

                break;
            case R.id.pson_head:
                break;
            case R.id.pson_back:
                finish();
                break;
            case R.id.pson_share:
                break;
            case R.id.pson_guan:
                //取消关注和关注
                if (data != null) {
                    String is_follow = data.getIs_follow();
                    if (is_follow.equals("0")) {

                        psonChat2.setVisibility(View.VISIBLE);
                        pson_unatten.setVisibility(View.VISIBLE);
                        psonChat.setVisibility(View.GONE);
                        psonGuan.setVisibility(View.GONE);
                        //关注
                        String s = HttpUtils.instance().followUser(id);
                        psonGuan.setText("已关注");
                        psonGuan.setBackgroundResource(R.drawable.dyna_unfollow);
                        if (s.equals("1")) {
                            // Toast.makeText(this, "关注成功", Toast.LENGTH_SHORT).show();
                        }
                        psonChat.setBackgroundResource(R.drawable.dyna_follow);
                        psonGuan.setTextColor(Color.WHITE);
                        data.setIs_follow("1");

                    }/* else {
                     *//*   psonChat2.setVisibility(View.VISIBLE);
                        pson_unatten.setVisibility(View.VISIBLE);*//*

                        //取消关注
                        String s = HttpUtils.instance().followUser(id);
                        psonGuan.setText("关注");
                        psonGuan.setTextColor(Color.WHITE);
                        // Toast.makeText(this, "取消成功", Toast.LENGTH_SHORT).show();
                        psonGuan.setBackgroundResource(R.drawable.dyna_follow);
                        data.setIs_follow("0");
                        psonChat.setBackgroundResource(R.drawable.dyna_unfollow);
                        if (s.equals("0")) {

                        }
                    }
*/

                }
                break;
            case R.id.pson_lin3:
                JumpUtils.gotoActivity(this, FansActivity.class);
                break;
            //粉丝
            case R.id.pson_lin2:
                Bundle bundle3 = new Bundle();
                bundle3.putString("type", "fans");
                JumpUtils.gotoActivity(this, FansActivity.class, bundle3);
                break;
            //关注
            case R.id.pson_lin1:

                break;
        }
    }


    @Override
    public void error(String error) {

    }

    @Override
    public void success(String success) {
        PersonBean personBean = new Gson().fromJson(success, PersonBean.class);
        data = personBean.getData();
        //是否关注 0未 1关
        String is_follow = data.getIs_follow();
        if (is_follow.equals("0")) {
          /*  psonGuan.setText("关注");
            psonGuan.setTextColor(Color.WHITE);*/
            psonChat.setVisibility(View.VISIBLE);
            psonGuan.setVisibility(View.VISIBLE);
            psonChat2.setVisibility(View.GONE);
            pson_unatten.setVisibility(View.GONE);
       /*     psonGuan.setBackgroundResource(R.drawable.dyna_follow);
            psonChat.setBackgroundResource(R.drawable.dyna_unfollow);*/
        } else {
         /*   psonGuan.setText("已关注");
            psonGuan.setTextColor(Color.BLACK);
            psonGuan.setBackgroundResource(R.drawable.dyna_unfollow);
            psonChat.setBackgroundResource(R.drawable.dyna_follow);*/
            psonChat.setVisibility(View.GONE);
            psonGuan.setVisibility(View.GONE);
            psonChat2.setVisibility(View.VISIBLE);
            pson_unatten.setVisibility(View.VISIBLE);
        }

        psonCity.setText(data.getCity());
        psonName.setText(data.getNickname());
        person_id.setText("ID:" + data.getId());
        if (data.getSex() == 1) {
            psonSex.setText("男");
        } else if (data.getSex() == 2) {
            psonSex.setText("女");
        }
        if (data.getDistance().equals("")) {
            psonKm.setVisibility(View.GONE);
            psonLocation.setVisibility(View.GONE);
        } else {
            psonKm.setText(data.getDistance());
        }


        psonSign.setText(data.getSign());
        Log.i("wxy", "success: " + data.getCollection());
        if (data.getCollection().equals("")) {
            psonGoodnum.setText("0");
        } else {
            psonGoodnum.setText(data.getCollection());
        }
        if (data.getFans().equals("")) {
            psonFennum.setText("0");
        } else {
            psonFennum.setText(data.getFans());
        }
        if (data.getFollows().equals("")) {
            psonGuannum.setText("0");
        } else {
            psonGuannum.setText(data.getFollows());
        }


        Glide.with(this).load(data.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(psonHead);
    }

    @Override
    protected PersonPresenter preparePresenter() {
        return new PersonPresenter();
    }


    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        Log.i("wxy", "onScrollChange: "+scrollY+"==="+dp2px(44)+"======"+perlin.getHeight());
        if (scrollY +dp2px(44) > perlin.getHeight()) {
            relaPrttop.setVisibility(View.VISIBLE);
        } else {
            relaPrttop.setVisibility(View.GONE);
        }


    }
}
