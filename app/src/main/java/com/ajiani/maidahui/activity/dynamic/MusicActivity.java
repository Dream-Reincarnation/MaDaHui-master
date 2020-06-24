package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.adapter.dynamic.AddshopVpAdapter;
import com.ajiani.maidahui.adapter.dynamic.TagAdapter;
import com.ajiani.maidahui.adapter.dynamic.music.MusicTagAdapter;
import com.ajiani.maidahui.adapter.dynamic.music.MusicVpAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.fragment.dynamic.PurchaseFragment;
import com.ajiani.maidahui.fragment.dynamic.music.MusicCollectionFragment;
import com.ajiani.maidahui.fragment.dynamic.music.MusicHotFragment;
import com.ajiani.maidahui.fragment.dynamic.music.MusicRecommendFragment;
import com.ajiani.maidahui.fragment.dynamic.searchshop.CollectionFragment2;
import com.ajiani.maidahui.fragment.dynamic.searchshop.CollectionStoreFragment2;
import com.ajiani.maidahui.fragment.dynamic.searchshop.CompleteFragment2;
import com.ajiani.maidahui.weight.SimpleRoundProgress;
import com.google.android.material.tabs.TabLayout;
import com.xugter.xflowlayout.XFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicActivity extends BaseActivity {

    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.friends_search)
    ImageView friendsSearch;
    @BindView(R.id.friedns_ed)
    TextView friednsEd;
    @BindView(R.id.friends_del)
    ImageView friendsDel;
    @BindView(R.id.music_tab)
    TabLayout musicTab;
    @BindView(R.id.music_vp)
    ViewPager musicVp;
    @BindView(R.id.music_search)
    FrameLayout musicSearch;
    @BindView(R.id.music_cancle)
    FrameLayout musicCancle;
    @BindView(R.id.search_shop_del)
    FrameLayout searchShopDel;
    @BindView(R.id.search_shop_flow)
    XFlowLayout searchShopFlow;
    @BindView(R.id.music_lin)
    LinearLayout musicLin;
    private MusicVpAdapter personVpAdapter;
    private MusicRecommendFragment musicRecommendFragment;
    private MusicCollectionFragment musicCollectionFragment;
    private MusicHotFragment musicHotFragment;
    public  ArrayList<String> strings;
    private String history;
    private MusicTagAdapter tagAdapter;
    public  SimpleRoundProgress roundProgress;
    public  LinearLayout musicLinPop;

    @Override
    protected BasePresenterImp preparePresenter() {
        return null;
    }

    @Override
    public void error(String error) {

    }
     public void backpress(Intent intent){
         setResult(1212, intent);
         finish();
     }

    @Override
    protected void initData() {


        musicTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // TextView inflate = (TextView) LayoutInflater.from(PersonActivity.this).inflate(R.layout.tab_text, null, false);
                //inflate.setText(tab.getText());
                //
                // inflate.setTextColor(ContextCompat.getColor(PersonActivity.this, R.color.Thme));
                //tab.setCustomView(inflate);
                musicTab.setTabIndicatorFullWidth(false);
                TextView inflate = (TextView) LayoutInflater.from(MusicActivity.this).inflate(R.layout.tab_textehite, null, false);
                inflate.setTextSize(15);
                inflate.getPaint().setFakeBoldText(true);
                inflate.setText(tab.getText());
                inflate.setTextColor(ContextCompat.getColor(MusicActivity.this, R.color.black));
                inflate.getPaint().setFakeBoldText(true);
                tab.setCustomView(inflate);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                musicTab.setTabIndicatorFullWidth(false);
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                musicTab.setTabIndicatorFullWidth(false);
            }
        });
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

        friednsEd.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    musicLin.setVisibility(View.VISIBLE);
                    friendsDel.setVisibility(View.GONE);

                    musicCancle.setVisibility(View.VISIBLE);
                    musicSearch.setVisibility(View.GONE);
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });

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

                    musicLin.setVisibility(View.VISIBLE);
                    friendsDel.setVisibility(View.GONE);
                    musicCancle.setVisibility(View.VISIBLE);
                    musicSearch.setVisibility(View.GONE);
                } else {
                    musicLin.setVisibility(View.VISIBLE);
                    friendsDel.setVisibility(View.VISIBLE);
                    musicCancle.setVisibility(View.GONE);
                    musicSearch.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    protected void initView() {

        musicLinPop = findViewById(R.id.music_lin_pop);
        roundProgress = findViewById(R.id.music_progress);

        history = (String) SPUtils.get(this, "musichtory", "");

        strings = new ArrayList<String>();
        if (history.length() > 3) {
            String[] split = history.split(",");
            for (int i = 0; i < split.length; i++) {
                strings.add(split[i]);
            }
        }
        tagAdapter = new MusicTagAdapter(strings, this);
        searchShopFlow.setAdapter(tagAdapter);

        tagAdapter.setOnClickLinstener(new MusicTagAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                //点击事件
                friednsEd.setText(tagAdapter.mList.get(posstion));
                data();
            }
        });

        StatusBarUtil2.setStatusBarMode(this, true, R.color.white);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("推荐");
        strings.add("收藏");
        strings.add("最热");
        ArrayList<Fragment> fragments = new ArrayList<>();
        musicRecommendFragment = new MusicRecommendFragment();
        musicCollectionFragment = new MusicCollectionFragment();
        musicHotFragment = new MusicHotFragment();
        fragments.add(musicRecommendFragment);
        fragments.add(musicCollectionFragment);
        fragments.add(musicHotFragment);
        personVpAdapter = new MusicVpAdapter(getSupportFragmentManager(), fragments, strings);
        musicVp.setAdapter(personVpAdapter);
        musicVp.setOffscreenPageLimit(2);
        musicTab.setupWithViewPager(musicVp);
        musicTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    if (musicCollectionFragment.voicePlayer != null) {
                        musicCollectionFragment.voicePlayer.stop();
                    }
                    if (musicHotFragment.voicePlayer != null) {
                        musicHotFragment.voicePlayer.stop();
                    }
                } else if (position == 1) {
                    if (musicRecommendFragment.voicePlayer != null) {
                        musicRecommendFragment.voicePlayer.stop();
                    }
                    if (musicHotFragment.voicePlayer != null) {
                        musicHotFragment.voicePlayer.stop();
                    }
                } else {
                    if (musicCollectionFragment.voicePlayer != null) {
                        musicCollectionFragment.voicePlayer.stop();
                    }
                    if (musicRecommendFragment.voicePlayer != null) {
                        musicRecommendFragment.voicePlayer.stop();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_music;
    }


    @OnClick({R.id.back, R.id.friends_del,R.id.friedns_ed,R.id.music_search, R.id.music_cancle, R.id.search_shop_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.friends_del:
                friednsEd.setText("");
                musicCancle.setVisibility(View.VISIBLE);
                musicSearch.setVisibility(View.GONE);
                break;
            case R.id.music_search:
                //进项网络请求
                data();



                break;
            case R.id.music_cancle:
                friednsEd.setText("");
                friednsEd.setHint("搜索音乐");
                friendsDel.setVisibility(View.GONE);
                musicCancle.setVisibility(View.GONE);
                musicSearch.setVisibility(View.GONE);
                friednsEd.clearFocus();

                musicLin.setVisibility(View.GONE);
                musicRecommendFragment.cancle();
               // musicRecommendFragment.setUserVisibleHint(true);
                musicVp.setCurrentItem(1);
                musicVp.setCurrentItem(0);
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(friednsEd.getWindowToken(), 0);
                break;
            case R.id.search_shop_del:
                //刪除历史记录
                SPUtils.remove(this, "musichtory");
                tagAdapter.mList.clear();
                tagAdapter  .notifyDataChanged();
                break;
            case R.id.friedns_ed:
                musicLin.setVisibility(View.VISIBLE);
                friendsDel.setVisibility(View.GONE);

                musicCancle.setVisibility(View.VISIBLE);
                musicSearch.setVisibility(View.GONE);
                break;
        }
    }
    public void data(){
        musicCancle.setVisibility(View.VISIBLE);
        musicSearch.setVisibility(View.GONE);
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
        SPUtils.put(this, "musichtory", stringBuffer.toString());
        //进项搜索
        musicLin.setVisibility(View.GONE);

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(friednsEd.getWindowToken(), 0);
        friednsEd.clearFocus();//取消焦点

        musicRecommendFragment.search(s);
        musicHotFragment.search(s);
        musicCollectionFragment.search(s);
    }
}
