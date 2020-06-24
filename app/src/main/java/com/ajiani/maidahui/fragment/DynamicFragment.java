package com.ajiani.maidahui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.dynamic.MusicActivity;
import com.ajiani.maidahui.activity.dynamic.TakePhotoActivity;
import com.ajiani.maidahui.adapter.dynamic.DynaFragmentAdapter;
import com.ajiani.maidahui.base.SimpleFragment;
import com.ajiani.maidahui.fragment.dynamic.CityFragment;
import com.ajiani.maidahui.fragment.dynamic.FindFragment;
import com.ajiani.maidahui.fragment.dynamic.AttentionFragment;
import com.google.android.material.tabs.TabLayout;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class DynamicFragment extends SimpleFragment implements EasyPermissions.PermissionCallbacks, View.OnClickListener {
    @BindView(R.id.dyna_search)
    ImageView dynaSearch;
    @BindView(R.id.dyna_tab)
    TabLayout dynaTab;
    @BindView(R.id.dyna_vp)
    ViewPager dynaVp;
    @BindView(R.id.dynamic_lin)
    LinearLayout dynalin;
    @BindView(R.id.dyna_lins)
    LinearLayout dynalins;
    @BindView(R.id.dyna_camare)
    ImageView camare;
    @BindView(R.id.dyna_ed)
    LinearLayout dynaEd;

    @Override
    protected void initData() {
        StatusBarUtil2.setStatusBarMode(getActivity(), true, R.color.white);
        HomeActivity.context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int statusBarHeight = StatusBarUtil2.getStatusBarHeight(getActivity());
        dynalins.setPadding(0, statusBarHeight, 0, 0);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            String token = (String) SPUtils.get(MyApp.getApp(), "token", "");
            if (token.length() < 4) {
                JumpUtils.gotoLoginActivity(HomeActivity.context);
                return;
            }
        }
    }

    @Override
    protected void initView() {

      /*  getActivity().getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
        //设置状态栏的颜色
        UMShareAPI.get(getActivity()).isAuthorize(mActivity, SHARE_MEDIA.WEIXIN);
        ArrayList<String> strings = new ArrayList<>();

        //strings.add("关注");
        strings.add("同城");
        strings.add("发现");

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CityFragment());
        fragments.add(new FindFragment());

        //fragments.add(new CityFragment());
        // fragments.add(new VicinityFragment());
        DynaFragmentAdapter dynaFragmentAdapter = new DynaFragmentAdapter(getChildFragmentManager(), strings, fragments);

        dynaVp.setAdapter(dynaFragmentAdapter);
        dynaTab.setupWithViewPager(dynaVp);
        dynaTab.setTabIndicatorFullWidth(false);
        dynaTab.setSelectedTabIndicator(getResources().getDrawable(R.drawable.tab_line));
        dynaVp.setCurrentItem(0);
        dynaTab.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dynaVp.setCurrentItem(tab.getPosition());
                TextView inflate = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tab_textehite, null, false);
                inflate.setTextSize(17);
                inflate.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
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

    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_dynamic;
    }

  /*  @OnClick({R.id.dyna_ed, R.id.dyna_camare})
    public void onViewClicked() {


//      JumpUtils.gotoActivity(getActivity(), LoginActivity.class);

    }*/


    public static boolean openAlipayPayPage(Context context) {
        try {
            // final String alipayqr = "alipayqr://platformapi/startapp?saId=10000001&clientVersion=3.7.0.0718";
            final String alipayqr = "alipayqr://platformapi/startapp?clientVersion=3.7.0.0718";
            openUri(context, alipayqr);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 发送一个intent
     */
    private static void openUri(Context context, String s) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        context.startActivity(intent);
    }

    @OnClick({R.id.dyna_camare, R.id.dyna_ed, R.id.ic_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dyna_camare:
                testPermission();
                break;
            case R.id.dyna_ed:
                //跳转到搜索页面
                JumpUtils.gotoActivity(getActivity(), MusicActivity.class);

              /*  AudioRecordManager.getInstance(getActivity()).setAudioSavePath(FileUtils.getCache(getActivity()));
                //开始录音
                AudioRecordManager.getInstance(getActivity()).startRecord();*/

                break;
            case R.id.ic_search:
                //跳转到搜索页面
                JumpUtils.gotoActivity(getActivity(), MusicActivity.class);
                break;
        }
    }

    private void testPermission() {
        String[] psermission;
        psermission = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE};

        //检查是否有权限
        if (EasyPermissions.hasPermissions(getActivity(), psermission)) {
            //打开相机和录像
            Intent intent = new Intent(getActivity(), TakePhotoActivity.class);
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
            Intent intent = new Intent(getActivity(), TakePhotoActivity.class);
            startActivity(intent);
            //todo somthing
        }

    }

    public void sendMessage() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //第二个参数发送号码
        intent.putExtra("address", "18831094384");
        //发送的内容
        intent.putExtra("sms_body", "你好呀");
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //无权限，且被选择"不再提醒"：提醒客户到APP应用设置中打开权限
        if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
            Log.e("tag", "EasyPermission CallBack onPermissionsDenied() : this " + perms.get(0) + " is denied and never ask again");
            Toast.makeText(getActivity(), "拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限", Toast.LENGTH_SHORT).show();

        }
        //无权限，只是单纯被拒绝
        else {
            Log.e("TAG", "EasyPermission CallBack onPermissionsDenied() : " + perms.get(0) + "request denied");
            Toast.makeText(getActivity(), "拒绝权限，等待下次询问哦", Toast.LENGTH_SHORT).show();
        }
    }

    private LinearLayout mShareVideoWechat;
    private LinearLayout mShareVideoWechatfriend;
    private LinearLayout mShareVideoPicture;
    private LinearLayout mShareVideoSpace;
    private LinearLayout mShareVideoQQ;
    private LinearLayout mShareVideoSign;
    private HorizontalScrollView mShareVideoHorizon;
    private LinearLayout mShareVideoReport;
    private LinearLayout mShareVideoSavepic;
    private LinearLayout mShareVideoColloect;
    private LinearLayout mShareVideoHarmony;
    private LinearLayout mShareVideoLink;
    private LinearLayout mShareVideoNolike;
    private HorizontalScrollView mShareVideoHorizon2;

    private void showSharePop() {
        PopupWindow popupWindow = new PopupWindow(getActivity());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.share_video, null, false);
        popupWindow.setContentView(inflate);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        mShareVideoWechat = (LinearLayout) inflate.findViewById(R.id.share_video_wechat);
        mShareVideoWechatfriend = (LinearLayout) inflate.findViewById(R.id.share_video_wechatfriend);
        mShareVideoPicture = (LinearLayout) inflate.findViewById(R.id.share_video_picture);
        mShareVideoSpace = (LinearLayout) inflate.findViewById(R.id.share_video_space);
        mShareVideoQQ = (LinearLayout) inflate.findViewById(R.id.share_video_QQ);
        mShareVideoSign = (LinearLayout) inflate.findViewById(R.id.share_video_sign);
        mShareVideoHorizon = (HorizontalScrollView) inflate.findViewById(R.id.share_video_horizon);
        mShareVideoReport = (LinearLayout) inflate.findViewById(R.id.share_video_report);
        mShareVideoSavepic = (LinearLayout) inflate.findViewById(R.id.share_video_savepic);
        mShareVideoColloect = (LinearLayout) inflate.findViewById(R.id.share_video_colloect);
        mShareVideoHarmony = (LinearLayout) inflate.findViewById(R.id.share_video_harmony);
        mShareVideoLink = (LinearLayout) inflate.findViewById(R.id.share_video_link);
        mShareVideoNolike = (LinearLayout) inflate.findViewById(R.id.share_video_nolike);
        mShareVideoHorizon2 = (HorizontalScrollView) inflate.findViewById(R.id.share_video_horizon2);
        mShareVideoWechat.setOnClickListener(this);
        mShareVideoWechatfriend.setOnClickListener(this);
        mShareVideoPicture.setOnClickListener(this);
        mShareVideoSpace.setOnClickListener(this);
        mShareVideoQQ.setOnClickListener(this);
        mShareVideoSign.setOnClickListener(this);
        mShareVideoHorizon.setOnClickListener(this);
        mShareVideoReport.setOnClickListener(this);
        mShareVideoSavepic.setOnClickListener(this);
        mShareVideoColloect.setOnClickListener(this);
        mShareVideoHarmony.setOnClickListener(this);
        mShareVideoLink.setOnClickListener(this);
        mShareVideoNolike.setOnClickListener(this);
        mShareVideoHorizon2.setOnClickListener(this);


        inflate.findViewById(R.id.share_video_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(dynaVp, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //分享到各个平台
            case R.id.share_video_wechat:

                break;
            case R.id.share_video_wechatfriend:
                break;
            case R.id.share_video_picture:
                break;
            case R.id.share_video_space:
                break;
            case R.id.share_video_QQ:
                break;
            case R.id.share_video_sign:
                break;
            case R.id.share_video_horizon:
                break;
            case R.id.share_video_report:
                break;
            case R.id.share_video_savepic:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();

                        //保存在本地
                        saveVideo("https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/video/68/b3682aaf1c69b37738cdd26f9222c7.mp4");
                    }
                }.start();

                break;
            case R.id.share_video_colloect:
                break;
            case R.id.share_video_harmony:
                break;
            case R.id.share_video_link:
                break;
            case R.id.share_video_nolike:
                break;
            case R.id.share_video_horizon2:
                break;
            case R.id.share_video_cancle:
                break;
        }
    }

    private void saveVideo(String videoUrl) {

        HttpURLConnection conn = null;
        try {
            URL url = new URL(videoUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            // 获取到文件的大小
            //pd.setMax(conn.getContentLength());
            String path = Environment.getExternalStorageDirectory() + "/Ask";
            File file = new File(path, "lfmf.mp4");
            InputStream is = conn.getInputStream();
            Log.i("wxy", "saveVideo: " + is);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            Log.i("wxy", "saveVideo: " + bis.read(buffer));
            while ((len = bis.read(buffer)) != -1) {
                Log.i("wxy", "saveVideo: " + len);
                fos.write(buffer, 0, len);
                //获取下载的进度
                total += len;
                fos.close();
                bis.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
