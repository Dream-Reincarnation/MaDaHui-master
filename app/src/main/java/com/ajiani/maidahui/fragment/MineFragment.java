package com.ajiani.maidahui.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.chat.FansActivity;
import com.ajiani.maidahui.activity.mine.IntengralActivity;
import com.ajiani.maidahui.activity.mine.MoreManageActivity;
import com.ajiani.maidahui.activity.mine.OrderInfo;
import com.ajiani.maidahui.activity.mine.RechargeActivity;
import com.ajiani.maidahui.activity.mine.RestantActivity;
import com.ajiani.maidahui.activity.mine.SetInfoAvtivity;
import com.ajiani.maidahui.activity.mine.SettActivity;
import com.ajiani.maidahui.activity.mine.WebManagerActivity;
import com.ajiani.maidahui.adapter.mine.FragmentAdapter;
import com.ajiani.maidahui.adapter.mine.LikeVideoAdapter;
import com.ajiani.maidahui.adapter.mine.ProjectRelAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.mine.CountBean;
import com.ajiani.maidahui.bean.mine.LikeVideoBean;
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.UserInfo;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.ajiani.maidahui.fragment.mine.MineLikeFragment;
import com.ajiani.maidahui.fragment.mine.VLogShopFragment;
import com.ajiani.maidahui.fragment.mine.WorksFragment;
import com.ajiani.maidahui.mInterface.mine.MineInfo;
import com.ajiani.maidahui.presenters.mine.MinePresenter;
import com.ajiani.maidahui.weight.mine.ScaleScrollView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ajiani.maidahui.Utils.JumpUtils.gotoActivity;

public class MineFragment extends BaseFragment<MineInfo.mineView, MinePresenter> implements MineInfo.mineView, ScaleScrollView.OnScrollChangeListener {
    @BindView(R.id.mine_lins)
    LinearLayout mineLin;
    @BindView(R.id.mine_head)
    ImageView mHead;
    @BindView(R.id.draw)
    FrameLayout draw;
    @BindView(R.id.mine_flow)
    TextView flownum;
    @BindView(R.id.mine_name)
    TextView mMineName;
    @BindView(R.id.mine_id)
    TextView mMineId;
    @BindView(R.id.mine_likenum)
    TextView mMineLikenum;
    @BindView(R.id.mine_zan)
    TextView mMineZan;
    @BindView(R.id.mine_fen)
    TextView mMineFen;
    @BindView(R.id.mine_footprint)
    TextView mine_footnum;
    @BindView(R.id.balance)
    TextView mBalance;
    @BindView(R.id.ear)
    TextView mEar;
    @BindView(R.id.wallet)
    TextView mWallet;
    ImageView mTui;
    @BindView(R.id.mine_tab)
    TabLayout mMineTab;
    @BindView(R.id.rela_zuo)
    RelativeLayout relaZuo;
    @BindView(R.id.rela_top)
    RelativeLayout relaTop;
    @BindView(R.id.start_lin)
    LinearLayout start_lin;
    @BindView(R.id.top_lin)
    LinearLayout topLin;
    @BindView(R.id.lin6)
    FrameLayout lin6;
    ImageView tui2;
    @BindView(R.id.mine_vp)
    ViewPager mineVp;
    @BindView(R.id.mine_paynum)
    TextView minePaynum;
    @BindView(R.id.mine_sharenum)
    TextView mineSharenum;
    @BindView(R.id.mine_shipnum)
    TextView mineShipnum;
    @BindView(R.id.mine_receivernum)
    TextView mineReceivernum;
    @BindView(R.id.mine_commentnum)
    TextView mineCommentnum;
    @BindView(R.id.un_rect)
    ImageView unRect;
    @BindView(R.id.tab_top)
    RelativeLayout tabTop;
    @BindView(R.id.toolbar_name)
    TextView topName;
    @BindView(R.id.un_perfor)
    ImageView unPerfor;
    @BindView(R.id.un_service)
    ImageView unService;
    @BindView(R.id.un_hose)
    ImageView unHose;
    @BindView(R.id.mine_scrollview)
    ScaleScrollView scrollView;
    @BindView(R.id.mine_tab2)
    TabLayout minetab2;
    @BindView(R.id.un_team)
    ImageView unTeam;
    @BindView(R.id.min_share)
    ImageView minShare;
    @BindView(R.id.mine_set)
    ImageView mineSet;
    private ArrayList<VideoInfoBean.DataBean> integers;
    float a = 0.02f;

    boolean isblack = false;//状态栏字体是否是黑色
    boolean iswhite = true;//状态栏字体是否是亮色
    private ProjectRelAdapter relAdapter;
    private Bundle bundle;
    private UserInfo.DataBean data;
    private ArrayList<String> list;
    private String TAG = "wxy";

    float alpha = 0;
    private String token;

    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            String token = (String) SPUtils.get(MyApp.getApp(), "token", "");
           /* if (token.length() < 4) {
                JumpUtils.gotoLoginActivity(HomeActivity.context);
                return;
            }*/
            StatusBarUtil2.setStatusBarMode(HomeActivity.context, false, R.color.Thme);

        }
    }


    public void scroll() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }


    @Override
    protected int createLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        HomeActivity.context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int statusBarHeight = StatusBarUtil2.getStatusBarHeight(getActivity());
        draw.setPadding(0,statusBarHeight,0,0);
        scrollView.setOnScrollChangeListener(this);


        //  scrollView.OnSc
       /* scrollView.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                //  Log.i(TAG, "onScrollChanged: "+(y+(dp2px(44)))+"==="+ topLin.getHeight());
                if (y + (dp2px(44)) > topLin.getHeight()) {
                    tabTop.setVisibility(View.VISIBLE);
                } else {

                    tabTop.setVisibility(View.GONE);
                }
                //上滑  头部显示和隐藏
                if (y > oldy) {
                    if (y < dp2px(50)) {
                        alpha += 0.02f;
                        relaTop.setAlpha(alpha);
                    } else {
                        alpha = 1;
                    }
                } else {
                    if (y <= dp2px(50) & y > dp2px(4)) {
                        alpha -= 0.02f;
                        relaTop.setAlpha(alpha);
                    } else {
                        alpha = 0;
                    }
                }

                //滑动大于50 就直接赋值为1
                if (y > dp2px(50)) {
                    alpha = 1;
                    relaTop.setAlpha(alpha);
                }
                if (y < dp2px(4)) {
                    alpha = 0;
                    relaTop.setAlpha(alpha);
                }
                if(alpha>0.5){
                    StatusBarUtil2.setStatusBarMode(getActivity(),true,R.color.white);
                    mineSet.setImageResource(R.mipmap.bmineset);
                    minShare.setImageResource(R.mipmap.bmineshare);
                }else{
                    StatusBarUtil2.setStatusBarMode(getActivity(),false,R.color.Thme);
                    mineSet.setImageResource(R.mipmap.mine_set);
                    minShare.setImageResource(R.mipmap.mine_share);
                }
            }
        });*/

        mMineTab.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                } else {
                    TextView inflate = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tab_textehite, null, false);
                    inflate.setTextSize(17);
                    inflate.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_red));
                    inflate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                    inflate.setText(tab.getText());
                    tab.setCustomView(inflate);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        minetab2.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView inflate = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tab_textehite, null, false);
                inflate.setTextSize(17);
                inflate.setTextColor(ContextCompat.getColor(getActivity(), R.color.home_red));
                inflate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
                inflate.setText(tab.getText());
                tab.setCustomView(inflate);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ;


    }

    /**
     * 从dp单位转换为px
     *
     * @param dp dp值
     * @return 返回转换后的px值
     */
    private int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    //9b93e593f21d5f73b864a870757b5183
    //a22a74aa38d2cfd1000bce4e8d24982d
    @Override
    protected void initView() {
        String s = Md5Utils.MD5("10003123456");
        Glide.with(getActivity()).load(R.mipmap.unhead).apply(new RequestOptions().circleCrop()).into(mHead);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Vlog");
        strings.add("种草");
        strings.add("喜欢");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new WorksFragment());
        fragments.add(new VLogShopFragment());
        fragments.add(new MineLikeFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), strings, fragments);
        //myViewPager.setAdapter(fragmentAdapter);
        integers = new ArrayList<>();
        mineVp.setAdapter(fragmentAdapter);
        mMineTab.setupWithViewPager(mineVp);
        minetab2.setupWithViewPager(mineVp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mineVp.setCurrentItem(0);
        LinearLayout linearLayout = (LinearLayout) mMineTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                R.mipmap.mine_square));
        linearLayout.setDividerPadding(dp2px(15));
        relAdapter = new ProjectRelAdapter(integers);

        relAdapter.setOnClickLinstener(new ProjectRelAdapter.onClickLinstener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(int posstion) {
                Bundle bundle = new Bundle();
                bundle.putString("id", relAdapter.mList.get(posstion).getAid() + "");
                String video_type = relAdapter.mList.get(posstion).getVideo_type();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals(relAdapter.mList.get(posstion).getAid() + "")) {
                        posstion = i;
                        break;
                    }
                }
                if (video_type.equals("video")) {

                    //视频
                    Bundle bundle1 = new Bundle();
                    bundle1.putStringArrayList("list", list);
                    bundle1.putString("posstion", posstion + "");
                    gotoActivity(getActivity(), Main3Activity.class, bundle1);
                    // JumpUtils.gotoActivity(getActivity(), MineProductActivity.class, bundle);
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        token = (String) SPUtils.get(getActivity(), "token", "");

        if (token.length() > 7) {
            String head = (String) SPUtils.get(getActivity(), "head", "");
            String name = (String) SPUtils.get(getActivity(), "name", "");
            mMineName.setText(name);

            if (head.length() > 3) {
                Glide.with(getActivity()).load(head).apply(new RequestOptions().circleCrop()).into(mHead);
            }
            HashMap<String, String> hashMap1 = new HashMap<>();
            mPresenter.getUserInfo(hashMap1);
            HashMap<String, String> hashMap = new HashMap<>();
            mPresenter.getCount(hashMap);
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put("page", "1");
            mPresenter.getVideo(hashMap2);
        } else {
            //刷新页面

        }

    }

    @Override
    protected MinePresenter preparePresenter() {
        return new MinePresenter();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.waitpay, R.id.waitshare, R.id.waitship, R.id.waitreceive, R.id.waitcomment, R.id.mine_likenum, R.id.mine_zan, R.id.mine_fen, R.id.mine_head, R.id.lin1,
            R.id.lin2, R.id.lin3, R.id.lin5, R.id.mine_set, R.id.footprint_lin, R.id.min_share, R.id.lin6, R.id.follow_lin, R.id.fen_lin, R.id.collection_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.footprint_lin:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                bundle = new Bundle();
                bundle.putString("type", "look");
                bundle.putString("title", "我的收藏");
                JumpUtils.gotoActivity(getActivity(), WebManagerActivity.class, bundle);
                break;
            case R.id.collection_lin:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                bundle = new Bundle();
                bundle.putString("type", "collect");
                bundle.putString("title", "我的收藏");
                JumpUtils.gotoActivity(getActivity(), WebManagerActivity.class, bundle);
                break;

            case R.id.follow_lin:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                gotoActivity(getActivity(), FansActivity.class);
                break;
            case R.id.fen_lin:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                Bundle bundle3 = new Bundle();
                bundle3.putString("type", "fans");
                JumpUtils.gotoActivity(getActivity(), FansActivity.class, bundle3);
                break;
            case R.id.min_share:
                //分享
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                break;

            case R.id.mine_set:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                //设置页面
                bundle = new Bundle();
                gotoActivity(getActivity(), SettActivity.class, bundle, 1224);
                break;


            case R.id.mine_likenum:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                break;
            case R.id.mine_zan:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                break;
            case R.id.mine_fen:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
//                Toast.makeText(mActivity, "点击了分享", Toast.LENGTH_SHORT).show();
                break;

            case R.id.mine_head:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                //更换头像
                Bundle bundle2 = new Bundle();
                bundle2.putString("img", data.getHeadimgurl());
                gotoActivity(getActivity(), SetInfoAvtivity.class, bundle2);
                break;
            case R.id.lin1:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                //余额页面
                Bundle bundle1 = new Bundle();
                if (data != null) {
                    bundle1.putString("restant", data.getVotes());
                }
                bundle1.putString("restant", data.getVotes());
                gotoActivity(getActivity(), RestantActivity.class, bundle1);
                break;
            case R.id.lin2:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                //跳转到钻石
                this.bundle = new Bundle();
                if (data != null) {
                    bundle.putString("recharge", data.getMoney());
                }

                gotoActivity(getActivity(), RechargeActivity.class, bundle);
                break;
            case R.id.lin3:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                //跳转麦穗页面
                Bundle bundle = new Bundle();
                if (data != null) {
                    bundle.putString("integral", data.getIntegral() + "");
                }
                gotoActivity(getActivity(), IntengralActivity.class, bundle);
                break;
            case R.id.lin5:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                this.bundle = new Bundle();
                this.bundle.putString("order_status", "-1");
                this.bundle.putString("headerType", "0");
                // 跳转到订单页面
                gotoActivity(getActivity(), OrderInfo.class, this.bundle);
                break;
            case R.id.lin6:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                Intent intent = new Intent(getActivity(), MoreManageActivity.class);
                startActivity(intent);
                break;
            case R.id.waitpay:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                this.bundle = new Bundle();
                this.bundle.putString("order_status", "0");
                this.bundle.putString("headerType", "1");
                // 跳转到订单页面
                gotoActivity(getActivity(), OrderInfo.class, this.bundle);
                break;
            case R.id.waitshare:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                this.bundle = new Bundle();
                this.bundle.putString("order_status", "1");
                this.bundle.putString("headerType", "2");
                this.bundle.putString("shippingStatus", "-1");
                gotoActivity(getActivity(), OrderInfo.class, this.bundle);
                break;
            case R.id.waitship:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                this.bundle = new Bundle();
                this.bundle.putString("order_status", "1");
                this.bundle.putString("headerType", "3");
                gotoActivity(getActivity(), OrderInfo.class, this.bundle);
                break;
            case R.id.waitreceive:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                this.bundle = new Bundle();
                this.bundle.putString("order_status", "3");
                this.bundle.putString("headerType", "4");
                // 跳转到订单页面
                gotoActivity(getActivity(), OrderInfo.class, this.bundle);
                break;
            case R.id.waitcomment:
                if (token.length() < 7) {
                    JumpUtils.gotoLoginActivity(getActivity());
                    return;
                }
                this.bundle = new Bundle();
                this.bundle.putString("type", "commint");
                this.bundle.putString("title", "我的评价");

                // 跳转到订单页面
                gotoActivity(getActivity(), WebManagerActivity.class, this.bundle);
                break;
        }
    }


    @Override
    public void error(String error) {

    }

    @Override
    public void videoSuccess(String string) {
        MineVideoBean mineVideoBean = new Gson().fromJson(string, MineVideoBean.class);
        relAdapter.mList.addAll(mineVideoBean.getData());
        relAdapter.notifyDataSetChanged();
        List<VideoInfoBean.DataBean> data = mineVideoBean.getData();
        //得到视频的url
        list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getVideo_type().equals("video")) {
                list.add(data.get(i).getAid() + "");
            }

        }

    }

    @Override
    public void userInfo(String success) {
        SPUtils.put(MyApp.getApp(), "userinfo", success);
        UserInfo userInfo = new Gson().fromJson(success, UserInfo.class);
        data = userInfo.getData();
        //设置userid
        if (mMineId != null)
            mMineId.setText("ID:" + data.getUser_id());
        topName.setText(data.getNickname());
        SPUtils.put(MyApp.getApp(), "pay_pass", data.getIs_paypass() + "");
        if (getActivity() != null) {

            SPUtils.put(getActivity(), "pass", data.getPay_pass() + "");
            SPUtils.put(getActivity(), "userid", data.getUser_id() + "");
            SPUtils.put(getActivity(), "phone", data.getMobile());
            SPUtils.put(getActivity(), "username", data.getNickname());
            SPUtils.put(getActivity(), "name", data.getNickname());
            SPUtils.put(getActivity(), "head", data.getHeadimgurl());
            SPUtils.put(getActivity(), "sex", data.getSex() + "");
            SPUtils.put(getActivity(), "sign", data.getSign());
            SPUtils.put(getActivity(), "autonym", data.getUser_auth() + "");
            SPUtils.put(getActivity(), "height", data.getHeight() + "");
            SPUtils.put(getActivity(), "weight", data.getWeight() + "");
            SPUtils.put(getActivity(), "birthday", data.getBirthday());
            SPUtils.put(getActivity(), "ailipay", data.getAlipay());
            SPUtils.put(getActivity(), "votes", data.getVotes());
            SPUtils.put(getActivity(), "email", data.getEmail());
            String headimgurl = data.getHeadimgurl();

            Glide.with(getActivity()).load(headimgurl).apply(new RequestOptions().circleCrop()).into(mHead);
        }


        //设置个人信息


        mMineName.setText(data.getNickname());
        flownum.setText(data.getFollows() + "");
        mMineFen.setText(data.getFans() + "");
        mBalance.setText(data.getVotes() + "");
        //收藏

        //或获赞

        mEar.setText(data.getMoney() + "");
        mWallet.setText(data.getIntegral() + "");

    }

    @Override
    public void loveListSuccess(String success) {
        LikeVideoBean likeVideoBean = new Gson().fromJson(success, LikeVideoBean.class);
        List<VideoInfoBean.DataBean> data = likeVideoBean.getData();
        if (data != null) {
            LikeVideoAdapter likeVideoAdapter = new LikeVideoAdapter((ArrayList<VideoInfoBean.DataBean>) data);

        }
    }

    @Override
    public void getCountSuccess(String success) {
        CountBean countBean = new Gson().fromJson(success, CountBean.class);
        CountBean.DataBean data = countBean.getData();
        if (data.getOrder_pay() > 0) {
            minePaynum.setVisibility(View.VISIBLE);
            minePaynum.setText(data.getOrder_pay() + "");
        }
        if (data.getHistory() >= 0) {
            mine_footnum.setText(data.getHistory() + "");
        }
        if (data.getCollected() >= 0) {
            mMineLikenum.setText(data.getCollection() + "");
        }
        if (data.getOrder_group() > 0) {
            mineSharenum.setVisibility(View.VISIBLE);
            mineSharenum.setText(data.getOrder_group() + "");
        }

        mMineZan.setText(data.getCollected() + "");
        if (data.getOrder_comment() > 0) {
            mineCommentnum.setVisibility(View.VISIBLE);
            mineCommentnum.setText(data.getOrder_comment() + "");
        }
        if (data.getOrder_shipping() > 0) {
            mineShipnum.setVisibility(View.VISIBLE);
            mineShipnum.setText(data.getOrder_shipping() + "");
        }
        if (data.getOrder_success() > 0) {
            mineReceivernum.setVisibility(View.VISIBLE);
            mineReceivernum.setText(data.getOrder_success() + "");
        }

    }

    /*
     *   滑动监听
     *   分三个阶段  头部tab  内容区   底部tab
     *
     *   滑动的高度》内容区的高度头部显示遮挡住底部tab
     *
     * */
    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY + (dp2px(44)) > topLin.getHeight()) {
            tabTop.setVisibility(View.VISIBLE);
        } else {

            tabTop.setVisibility(View.GONE);
        }
        //上滑  头部显示和隐藏
        if (scrollY > oldScrollY) {
            if (scrollY < dp2px(50)) {
                alpha += 0.02f;
                relaTop.setAlpha(alpha);
            } else {
                alpha = 1;
            }
        } else {
            if (scrollY <= dp2px(50) & scrollY > dp2px(4)) {
                alpha -= 0.02f;
                relaTop.setAlpha(alpha);
            } else {
                alpha = 0;
            }
        }

        //滑动大于50 就直接赋值为1
        if (scrollY > dp2px(50)) {
            alpha = 1;
            relaTop.setAlpha(alpha);
        }
        if (scrollY < dp2px(4)) {
            alpha = 0;
            relaTop.setAlpha(alpha);
        }
        if (alpha > 0.5) {
//            StatusBarUtil2.setStatusBarMode(getActivity(), true, R.color.white);

            mineSet.setImageResource(R.mipmap.bmineset);
            minShare.setImageResource(R.mipmap.bmineshare);
        } else {
//            StatusBarUtil2.setStatusBarMode(getActivity(), false, R.color.Thme);

            mineSet.setImageResource(R.mipmap.mine_set);
            minShare.setImageResource(R.mipmap.mine_share);
        }
    }
}
