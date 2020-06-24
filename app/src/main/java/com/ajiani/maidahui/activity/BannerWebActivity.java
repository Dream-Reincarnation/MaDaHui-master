package com.ajiani.maidahui.activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.DownloadHandlerThread;
import com.ajiani.maidahui.JsInteration;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.web.WebUtils;
import com.ajiani.maidahui.adapter.zhuan.FriendsCircleAdapter;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.zhuan.FriendsCircleSel;
import com.ajiani.maidahui.bean.zhuan.ShareTxtBean;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BannerWebActivity extends SimpleActivity {
    @BindView(R.id.zhuan_back)
    ImageView zhuanBack;
    @BindView(R.id.zhuan_close)
    TextView zhuanClose;
    @BindView(R.id.zhuan_title)
    TextView zhuanTitle;
    @BindView(R.id.ed)
    EditText ed;
    @BindView(R.id.zhuan_web)
    WebView zhuanWeb;
    private ShareTxtBean shareTxtBean;
    private DownloadHandlerThread mDownloadHandlerThread;
    @Override
    protected void initData() {

    }
    @SuppressLint("HandlerLeak")
    Handler mUiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 5:
                    //保存完成
                    //跳转到微信
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                     BannerWebActivity.this.startActivity(intent);
                    break;
            }

        }
    };
    @Override
    protected void initView() {
        zhuanClose.setVisibility(View.GONE);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String url = bundle.getString("url");
        zhuanWeb.getSettings().setJavaScriptEnabled(true);
        zhuanWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        zhuanWeb.getSettings().setJavaScriptEnabled(true);
        zhuanWeb.getSettings().setDomStorageEnabled(true);
        zhuanWeb.addJavascriptInterface(new JsInteration(), "android");

        //设置useRagent
        String userAgentString = zhuanWeb.getSettings().getUserAgentString();
        userAgentString = userAgentString + " com.ajiani.maidahui ";

        zhuanWeb.getSettings().setUserAgentString(userAgentString);


            zhuanWeb.loadUrl(url );
        zhuanWeb.setWebViewClient(new WebViewClient() {


            private FriendsCircleAdapter friendsCircleAdapter;

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //拿到webview的title
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    zhuanTitle.setText(title);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//app://wechatpay?[base64的JSON串]
//app://alipay?[base64的JSON串]
                Log.i("wxy", "shouldOverrideUrlLoading: "+url);
                if(url.contains("app://sharetxt")){
                    //一键发圈
                    String substring = url.substring(15, url.length());
                    String shareTxt = new String(Base64.decode(substring.getBytes(), Base64.DEFAULT));
                    shareTxtBean = new Gson().fromJson(shareTxt, ShareTxtBean.class);
                    View inflate = LayoutInflater.from(BannerWebActivity.this).inflate(R.layout.friends_circle, null);
                    PopupWindow popupWindow = new PopupWindow(BannerWebActivity.this);
                    popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

                    inflate.findViewById(R.id.friendcircles_dis).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                        }
                    });
                    RecyclerView recyclerView = inflate.findViewById(R.id.friendscircle_rel);
                    inflate.findViewById(R.id.friendscirclr_wechat).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //把内容复制发哦粘贴板
                            ClipboardManager cm = (ClipboardManager) MyApp.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
                            // 创建普通字符型ClipData
                            ClipData mClipData = ClipData.newPlainText("Label", shareTxtBean.getContent());
                            // 将ClipData内容放到系统剪贴板里。
                            cm.setPrimaryClip(mClipData);
                            //创建异步HandlerThread
                            ArrayList<String> strings = new ArrayList<>();
                            for (int i = 0; i <friendsCircleAdapter.mList.size() ; i++) {
                                boolean sel = friendsCircleAdapter.mList.get(i).isSel();
                                if(sel){
                                    String imgUrl = friendsCircleAdapter.mList.get(i).getImgUrl();
                                    strings.add(imgUrl);

                                }

                            }

                            mDownloadHandlerThread = new DownloadHandlerThread("mHandlerThread",BannerWebActivity.this);
                            mDownloadHandlerThread.setUiHandler(mUiHandler, strings);
                            //必须先开启线程
                            mDownloadHandlerThread.start();

                        }
                    });
                    popupWindow.setContentView(inflate);
                    ArrayList<FriendsCircleSel> friendsCircleSels = new ArrayList<>();
                    for (int i = 0; i < shareTxtBean.getPictures().size(); i++) {
                        FriendsCircleSel friendsCircleSel = new FriendsCircleSel();
                        friendsCircleSel.setImgUrl(shareTxtBean.getPictures().get(i));
                        friendsCircleSel.setSel(true);
                        friendsCircleSels.add(friendsCircleSel);
                    }
                    recyclerView.setLayoutManager(new GridLayoutManager(BannerWebActivity.this,3));
                    friendsCircleAdapter = new FriendsCircleAdapter(friendsCircleSels);
                    recyclerView.setAdapter(friendsCircleAdapter);
                    popupWindow.showAtLocation(zhuanWeb, Gravity.BOTTOM,0,0);

                }else{
                    WebUtils.instance().Client(zhuanWeb,view,url,BannerWebActivity.this);
                }

                return true;
            }

        });

    }
    @OnClick({R.id.zhuan_back, R.id.zhuan_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuan_back:
                finish();
                break;
            case R.id.zhuan_close:
            /*    HomeActivity.tabMenu.setVisibility(View.VISIBLE);
                HomeActivity homeActivity1 = (HomeActivity) getActivity();
                homeActivity1.homeVp.setCurrentItem(0);
                homeActivity1.setSelected();
                homeActivity1.tabMenuHome.setSelected(true);*/
                break;
        }
    }
    @Override
    protected int createLayout() {
        return R.layout.activity_bannerweb;
    }


}
