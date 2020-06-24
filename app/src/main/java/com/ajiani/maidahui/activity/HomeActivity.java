package com.ajiani.maidahui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.Utils.share.PopupWindows;
import com.ajiani.maidahui.activity.dynamic.TakePhotoActivity;
import com.ajiani.maidahui.activity.login.LoginActivity;
import com.ajiani.maidahui.adapter.HomeVpAdapter;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.bean.sockets.NotificaBean;
import com.ajiani.maidahui.bean.sockets.RedPackageBean;
import com.ajiani.maidahui.fragment.ChatFragment;
import com.ajiani.maidahui.fragment.DynamicFragment;
import com.ajiani.maidahui.fragment.HomeFragment;
import com.ajiani.maidahui.fragment.MineFragment;
import com.ajiani.maidahui.fragment.dynamic.AttentionFragment;
import com.ajiani.maidahui.fragment.dynamic.CityFragment;
import com.ajiani.maidahui.weight.MyViewPager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JzvdStd;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends SimpleActivity implements EasyPermissions.PermissionCallbacks, TencentLocationListener {
    /*  @BindView(R.id.home_vp)
      MyViewPager homeVp;*/
    @BindView(R.id.tab_menu_home)
    public TextView tabMenuHome;

    @BindView(R.id.tab_menu_deal_num)
    TextView tabMenuDealNum;

    @BindView(R.id.ly_tab_menu_home)
    LinearLayout lyTabMenuHome;

    @BindView(R.id.tab_menu_zhuan)
    public TextView tabMenuZhuan;
    @BindView(R.id.tab_menu_poi_num)
    TextView tabMenuPoiNum;
    @BindView(R.id.ly_tab_menu_zhuan)
    LinearLayout lyTabMenuZhuan;
    @BindView(R.id.tab_menu_dynamic)
    TextView tabMenuDynamic;
    @BindView(R.id.tab_menu_more_num)
    TextView tabMenuMoreNum;
    @BindView(R.id.ly_tab_menu_dynamic)
    LinearLayout lyTabMenuDynamic;
    @BindView(R.id.tab_menu_chat)
    TextView tabMenuChat;

    @BindView(R.id.ly_tab_menu_chat)
    LinearLayout lyTabMenuChat;
    @BindView(R.id.tab_menu_mine)
    TextView tabMenuMine;
    @BindView(R.id.tab_menu_user_mine)
    TextView tabMenuUserMine;
    @BindView(R.id.tab_menu_select)
    ImageView tabMenuSelect;
    @BindView(R.id.ly_tab_menu_mine)
    LinearLayout lyTabMenuMine;
    @BindView(R.id.div_tab_bar)
    View divTabBar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    public boolean b = false;
    //是否在home页面
    boolean isHome = true;
    private ArrayList<Fragment> fragments;
    public HomeVpAdapter homeVpAdapter;
    boolean check;
    private HomeFragment homeFragment;
    public MyViewPager homeVp;
    public static LinearLayout tabMenu;
    private Intent intent;
    public static Activity context;
    private String token;
    public static String lat;
    public static String lon;

    public static boolean isFullscreen;
    private Bundle bundle;
    private AttentionFragment attentionFragment;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        lat = null;
        lon = null;
        context = null;
        tabMenu = null;
        lat = null;
    }

    public static void hide() {
        tabMenu.setVisibility(View.GONE);
    }

    public static void showFooter() {
        tabMenu.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        SPUtils.remove(this, "true_name");

        context = HomeActivity.this;
        //注册地图，获取位置
        TencentLocationRequest tencentLocationRequest = TencentLocationRequest.create();
        tencentLocationRequest.setInterval(30000).setRequestLevel(3).setAllowCache(true);
        TencentLocationManager locationManager = TencentLocationManager.getInstance(this);
        int error = locationManager.requestLocationUpdates(tencentLocationRequest, this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1224 && resultCode == 4221) {
            Log.i("wxy", "onActivityResult: asdasdaswuu");


            homeVpAdapter = new HomeVpAdapter(getSupportFragmentManager(), fragments);
            homeVp.setAdapter(homeVpAdapter);
            homeVp.setCurrentItem(4);
        } else if (requestCode == 10002 && resultCode == 10001) {
            homeVp.setAdapter(homeVpAdapter);
            setSelected();
            homeVp.setCurrentItem(0);
            tabMenuHome.setSelected(true);
        } else if (requestCode == 1111 & resultCode == -1) {

            String codedContent = data.getStringExtra("codedContent");
            if (codedContent.contains("m.maidahui.com")) {
                homeFragment.mHomeWeb.loadUrl(codedContent);
            } else if (codedContent.contains("www.maidahui")) {
                homeFragment.mHomeWeb.loadUrl(codedContent);
            } else {
                //弹窗  去浏览器打开或者取消
                PopupWindow popupWindow = new PopupWindow(this);
                popupWindow.setWidth(dp2px(300));
                popupWindow.setHeight(dp2px(150));
                popupWindow.setBackgroundDrawable(null);
                View inflate = LayoutInflater.from(this).inflate(R.layout.webdialog, null);
                popupWindow.setOutsideTouchable(true);
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;//代表透明程度，范围为0 - 1.0f
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getWindow().setAttributes(lp);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        lp.alpha = 1.0f;
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
                popupWindow.setContentView(inflate);
                inflate.findViewById(R.id.webdialog_dis).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.webdialog_go).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse(codedContent);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                popupWindow.showAtLocation(tabMenu, Gravity.CENTER, 0, 0);
            }


        } else if (requestCode == 11 && resultCode == 14) {
            int posstion = data.getIntExtra("posstion", 0);
            homeVpAdapter = new HomeVpAdapter(getSupportFragmentManager(), fragments);
            homeVp.setAdapter(homeVpAdapter);
            setSelected();
            homeVp.setCurrentItem(posstion);
            tabMenuHome.setSelected(true);
            /*String tooken = (String) SPUtils.get(MyApp.getApp(), "token", "");
            Toast.makeText(this, "aasdsaasda", Toast.LENGTH_SHORT).show();
            HomeFragment.mHomeWeb.loadUrl(MyApp.BaseUrl + "?token" + tooken);*/
        }

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



    @Override
    protected void initView() {


        if (!this.isTaskRoot()) { // 判断当前activity是不是所在任务栈的根
            Intent intent = getIntent();
            if (intent != null) {
                String action = intent.getAction();
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
                    finish();
                    return;
                }
            }
        }

        String absolutePath = getCacheDir().getAbsolutePath();
        String absolutePath1 = getExternalCacheDir().getAbsolutePath();


        token = (String) SPUtils.get(MyApp.getApp(), "token", "");

        StatusBarUtil2.setStatusBarMode(this, false, R.color.white);

        tabMenu = findViewById(R.id.ly_tab_menu);
        tabMenuHome.setSelected(true);
        tabMenuHome.setVisibility(View.VISIBLE);

        tabMenuSelect.setVisibility(View.GONE);
        homeVp = findViewById(R.id.home_vp);
        fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        attentionFragment = new AttentionFragment();

        fragments.add(new DynamicFragment());
        fragments.add(attentionFragment);
        fragments.add(homeFragment);
        fragments.add(new ChatFragment());
        fragments.add(new MineFragment());
        homeVpAdapter = new HomeVpAdapter(getSupportFragmentManager(), fragments);
        homeVp.setAdapter(homeVpAdapter);
//        MessageUtils.messageLinister(this,homeVp);
    }
    //广播全局弹窗

    public void sendReciver(String s, String type) {
        PopupWindows popupWindows = new PopupWindows();

        Log.i("WXY", "sendReciver: 走了啊啊啊啊");
        JSONObject jsonObject2 = JSON.parseObject(s);
        String action = jsonObject2.getString("action");
        String ct = jsonObject2.getString("ct");
        String avatar = jsonObject2.getString("avatar");
        String name = jsonObject2.getString("nickname");
        String time = jsonObject2.getString("timestamp");
        String shopid1 = jsonObject2.getString("shopid");
        String id = jsonObject2.getString("uid");
        NotificaBean notificaBean = new NotificaBean();
        notificaBean.setShopId(shopid1);
        notificaBean.setName(name);
        notificaBean.setHeadUrl(avatar);
        notificaBean.setAction(action);
        notificaBean.setContent(ct);
        notificaBean.setId(id);
        notificaBean.setType(type);
        notificaBean.setTime(time);
       /* intent = new Intent("1");
        Bundle bundle = new Bundle();
        bundle.putSerializable("sss", notificaBean);
        intent.putExtra("bundles", bundle);*/

        popupWindows.show(this, homeVp, notificaBean);
    }

    private void sel(String action, String s) {
        switch (action) {
            case "1":
                //没有带商品进入客服 普通文字消息
                MsgBean reciverBean1 = new Gson().fromJson(s, MsgBean.class);
                EventBus.getDefault().postSticky(reciverBean1);
                break;
            case "2":
                break;
            case "3":
                //带着商品 进入客服，透传消息里带着商品信息的json
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "8":
                Gson gson = new Gson();
                RedPackageBean reciverBean = gson.fromJson(s, RedPackageBean.class);
                MsgBean messageBean = new MsgBean();
                messageBean.set_method_(reciverBean.get_method_());
                messageBean.setAction(reciverBean.getAction() + "");
                messageBean.setCt(reciverBean.getCt());
                messageBean.setAvatar(reciverBean.getAvatar());
                messageBean.setExtra(reciverBean.getExtra().getMoney());
                messageBean.setMsgid(reciverBean.getMsgid());
                messageBean.setNickname(reciverBean.getNickname());
                messageBean.setShopid(reciverBean.getShopid());
                messageBean.setTimestamp(reciverBean.getTimestamp());
                messageBean.setToid(reciverBean.getToid());
                messageBean.setUid(reciverBean.getUid() + "");
                messageBean.setUserType(reciverBean.getUserType());
                EventBus.getDefault().postSticky(messageBean);
                break;
        }
    }


    @Override
    protected int createLayout() {
        return R.layout.activity_home;
    }


    private void testPermission() {
        String[] psermission = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE};

        //检查是否有权限
        if (EasyPermissions.hasPermissions(this, psermission)) {
            //打开相机和录像
            Intent intent = new Intent(this, TakePhotoActivity.class);
            startActivity(intent);
        } else {
            //去申请
            EasyPermissions.requestPermissions(this, "需要获取你的相机权限", 1001, psermission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //省略switch requestCode
        //授权了
        if (perms.get(0).equals(Manifest.permission.CAMERA)) {
            Intent intent = new Intent(this, TakePhotoActivity.class);
            startActivity(intent);
            //todo somthing
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("wxy", "onResume: aaabbbbccc");
        SPUtils.remove(this, "photop");
        SPUtils.remove(this, "photo3");
        SPUtils.remove(this, "photo1");
        SPUtils.remove(this, "music");
        SPUtils.remove(this, "luyin");

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //无权限，且被选择"不再提醒"：提醒客户到APP应用设置中打开权限
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Log.e("tag", "EasyPermission CallBack onPermissionsDenied() : this " + perms.get(0) + " is denied and never ask again");
            Toast.makeText(this, "拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限", Toast.LENGTH_SHORT).show();

        }
        //无权限，只是单纯被拒绝
        else {
            Log.e("TAG", "EasyPermission CallBack onPermissionsDenied() : " + perms.get(0) + "request denied");
            Toast.makeText(this, "拒绝权限，等待下次询问哦", Toast.LENGTH_SHORT).show();

        }


    }



   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("wxy", "onKeyDown: " + isHome);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 10) {
            return true;
        }
      *//*  if (isHome) {
            if (keyCode == KeyEvent.KEYCODE_BACK && homeFragment.mHomeWeb.canGoBack()) {
                homeFragment.mHomeWeb.goBack();//返回上个页面
                //说明还有可返回的页面
                // hide();
                return true;
            } else {
                //  tabMenu.setVisibility(View.VISIBLE);
                //没有上一个页面
                return super.onKeyDown(keyCode, event);//退出H5所在的Activity
            }
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK && zhuanGouFragment.zhuanWeb.canGoBack()) {
                zhuanGouFragment.zhuanWeb.goBack();//返回上个页面
                return true;
            } else {
                HomeActivity.tabMenu.setVisibility(View.VISIBLE);
                homeVp.setCurrentItem(0);
                setSelected();
                tabMenuHome.setSelected(true);
                return true;
            }

        }*//*


    }*/
    private long firstTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
// TODO Auto-generated method stub
        //H5页面返回上一级
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (homeFragment.mHomeWeb.canGoBack()) {
                homeFragment.mHomeWeb.goBack();//返回上一页面
                return true;
            }
        }
        if (JzvdStd.backPress()) {
            return false;
        }


            long secondTime = System.currentTimeMillis();

            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (secondTime - firstTime < 2000) {
                    System.exit(0);
                } else {
                    Toast.makeText(getApplicationContext(), "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                    firstTime = System.currentTimeMillis();
                }

                return true;
            }




        return super.onKeyDown(keyCode, event);
    }


    public void reload() {

        FragmentManager mFragmentManager = (FragmentManager) getSupportFragmentManager();
        for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); i++) {
            mFragmentManager.popBackStack();
        }
    }


    public void setSelected() {
        tabMenuChat.setSelected(false);
        tabMenuDynamic.setSelected(false);
        tabMenuHome.setSelected(false);
        tabMenuHome.setVisibility(View.VISIBLE);
        tabMenuSelect.setVisibility(View.GONE);
        tabMenuHome.setText("首页");
        tabMenuMine.setSelected(false);
        tabMenuZhuan.setSelected(false);
    }

    @OnClick({R.id.ly_tab_menu_home, R.id.ly_tab_menu_zhuan, R.id.ly_tab_menu_dynamic, R.id.ly_tab_menu_chat, R.id.ly_tab_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_tab_menu_home:
                setSelected();
                JzvdStd.releaseAllVideos();
                isHome = true;
                tabMenuHome.setSelected(true);
                tabMenuSelect.setVisibility(View.GONE);
                //tabMenuHome.setVisibility(View.GONE);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tabMenuHome.getLayoutParams();

                homeVp.setCurrentItem(0);
                check = false;

                break;
            case R.id.ly_tab_menu_zhuan:
                isHome = false;

                String token = (String) SPUtils.get(MyApp.getApp(), "token", "");
                setSelected();
                tabMenuZhuan.setSelected(true);
                homeVp.setCurrentItem(1);
                check = false;
                /*if (zhuanGouFragment != null) {
                    if (token.length() > 3) {
                        zhuanGouFragment.zhuanWeb.loadUrl(zhuanGouFragment.url + "?token=" + token);
                    } else {
                        zhuanGouFragment.zhuanWeb.loadUrl(zhuanGouFragment.url + "?token=");
                    }
                }
*/
                break;

            case R.id.ly_tab_menu_dynamic:
                //将视频删掉
                JzvdStd.releaseAllVideos();
                this.token = (String) SPUtils.get(MyApp.getApp(), "token", "");
                bundle = new Bundle();
                if (this.token.length() < 4) {
                    bundle.putInt("posstion", 2);
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("bundle", bundle);
                    startActivityForResult(intent, 11);
                    return;
                } else {
                    setSelected();
                    tabMenuDynamic.setSelected(true);
                    homeVp.setCurrentItem(2);
                }

                break;
            case R.id.ly_tab_menu_chat:
                this.token = (String) SPUtils.get(MyApp.getApp(), "token", "");
                bundle = new Bundle();
              /*  if (this.token.length() < 4) {
                    bundle.putInt("posstion", 3);
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("bundle", bundle);
                    startActivityForResult(intent, 11);
                    return;
                } else {*/
                    setSelected();
                    check = false;
                    tabMenuChat.setSelected(true);
                    homeVp.setCurrentItem(3);
               // }

                break;
            case R.id.ly_tab_menu:
               /* this.token = (String) SPUtils.get(MyApp.getApp(), "token", "");
                if (this.token.length() < 4) {
                    JumpUtils.gotoActivity(this, LoginActivity.class);
                    return;
                } else {*/
                setSelected();
                check = false;
                tabMenuMine.setSelected(true);
                homeVp.setCurrentItem(4);
                //  }

                break;
        }
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (TencentLocation.ERROR_OK == i) {
            // 定位成功
            if (tencentLocation != null) {
//                维度
                lat = String.valueOf(tencentLocation.getLatitude());
//                精度
                lon = String.valueOf(tencentLocation.getLongitude());

            }
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }
}
