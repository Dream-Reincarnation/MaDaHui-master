package com.ajiani.maidahui.Utils.share;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DaoUtils;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.TimeUtils;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.activity.chat.ChatActivity;
import com.ajiani.maidahui.activity.chat.ChatStarActivity;
import com.ajiani.maidahui.activity.mine.AccountActivity;
import com.ajiani.maidahui.activity.mine.AutonymASetting;
import com.ajiani.maidahui.activity.mine.BindMailBoxActivity;
import com.ajiani.maidahui.bean.chat.ServiceBean;
import com.ajiani.maidahui.bean.share.GoodsBean;
import com.ajiani.maidahui.bean.share.ShareBean;
import com.ajiani.maidahui.bean.sockets.NotificaBean;
import com.ajiani.maidahui.dao.ChatList;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.ChatServer;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.Params;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.szing.zxing.QrCodeUtil;
import com.google.gson.Gson;
import com.luck.picture.lib.tools.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class PopupWindows implements View.OnClickListener, UMShareListener {

    private PopupWindow popupWindow;
    Activity context;
    ShareBean shareBean;
    private UMImage thumb;
    private UMImage image;
    private UMWeb umWeb;
    private TextView shareEarn;
    private WindowManager.LayoutParams lp;
    View view;
    private String TAG = "wxy";
    private ImageView shareShop;
    private RelativeLayout relativeLayout;
    private LinearLayout sharewechat;
    private LinearLayout sharefriedns;
    private ImageView mShareShop;
    private ImageView mSharegoodsHead;
    private LinearLayout shareSave;
    GoodsBean goodsBean;
    private RelativeLayout mShareRelative;
    private TextView changeQuery;
    private TextView changeTv;
    private TextView changeType;


    public void show(final Activity activity1, View view, NotificaBean notificaBean) {
        Context activity = activity1;

        popupWindow = new PopupWindow();
        View viewContent = LayoutInflater.from(activity).inflate(R.layout.notifitiom_item, null, false);
        TextView time = viewContent.findViewById(R.id.notifica_time);
        ImageView head = viewContent.findViewById(R.id.notifica_head);
        TextView content = viewContent.findViewById(R.id.notifica_content);

        String type = notificaBean.getType();
        TextView name = viewContent.findViewById(R.id.notifica_name);
        if (type.equals("system")) {
            Glide.with(activity).load(R.mipmap.messageaide).into(head);
            name.setText("公告通知");
        } else if (type.equals("freight")) {
            Glide.with(activity).load(notificaBean.getHeadUrl()).into(head);
            if (notificaBean.getName() != null) {
                name.setText(notificaBean.getName());
            }

        } else {
            Glide.with(activity).load(R.mipmap.messageaide).into(head);
            name.setText("服务通知");
        }
        Calendar calendars = Calendar.getInstance();
        calendars.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        time.setText(calendars.get(Calendar.HOUR) + ":" + calendars.get(Calendar.MINUTE));
        content.setText(notificaBean.getContent());
        popupWindow.setContentView(viewContent);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.PopupTopAnim);

        if (MyApp.isShowPop) {

        } else {
            popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
        }


        popupWindow.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("system")) {

                } else if (type.equals("freight")) {
                    //跳转聊天页面，
                    String shopId = notificaBean.getShopId();
                    Bundle bundle = new Bundle();
                    if (Integer.parseInt(shopId) > 0) {
                        //跳转商家聊天 查找数
                        List<ChatList> chatLists = DaoUtils.instance().selAll();
                        if (chatLists.size() > 0) {
                            for (int i = 0; i < chatLists.size(); i++) {
                                if (chatLists.get(i).getShopId().equals(notificaBean.getShopId())) {
                                    bundle.putString("uid", notificaBean.getShopId());
                                    bundle.putString("name", notificaBean.getName());

                                    bundle.putString("avart", notificaBean.getHeadUrl());
                                    JumpUtils.gotoActivity(activity1, Chat2Activity.class, bundle);
                                } else {
                                    //数据库没有，请求数据得到店铺名字
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("page", "1");
                                    hashMap.put("shop_id", notificaBean.getShopId());
                                    HashMap<String, String> map = Params.setParams2();
                                    map.putAll(hashMap);
                                    HashMap<String, String> sign = Params.getSign(map);
                                    HttpManager.instance().getServer(ChatServer.class).getServicesList(sign)
                                            .compose(RxUtils.rxScheduleThread())
                                            .subscribe(new BaseObserver() {
                                                @Override
                                                protected void onSuccess(String string) {
                                                    ServiceBean serviceBean = new Gson().fromJson(string, ServiceBean.class);
                                                    ServiceBean.DataBean dataBean = serviceBean.getData().get(0);
                                                    String name1 = dataBean.getName();
                                                    bundle.putString("uid", dataBean.getShop_id()+"");
                                                    bundle.putString("name", dataBean.getName());
                                                    bundle.putString("time", dataBean.getTimestamp());
                                                    bundle.putString("avart", notificaBean.getHeadUrl());
                                                    JumpUtils.gotoActivity(activity1, Chat2Activity.class, bundle);
                                                }
                                                @Override
                                                protected void onError(String string) {

                                                }
                                            });
                                }
                            }
                        } else {
                            //请求数据,
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("page", "1");
                            hashMap.put("shop_id", notificaBean.getShopId());
                            HashMap<String, String> map = Params.setParams2();
                            map.putAll(hashMap);
                            HashMap<String, String> sign = Params.getSign(map);
                            HttpManager.instance().getServer(ChatServer.class).getServicesList(sign)
                                    .compose(RxUtils.rxScheduleThread())
                                    .subscribe(new BaseObserver() {
                                        @Override
                                        protected void onSuccess(String string) {
                                            ServiceBean serviceBean = new Gson().fromJson(string, ServiceBean.class);
                                            ServiceBean.DataBean dataBean1 = serviceBean.getData().get(0);
                                            ArrayList<ChatList> chatLists = new ArrayList<>();
                                            for(int i=0;i<serviceBean.getData().size();i++){
                                                ServiceBean.DataBean dataBean = serviceBean.getData().get(i);
                                                ChatList chatList = new ChatList(null, dataBean.getShop_id() + "", "0",dataBean.getThumb(), dataBean.getContent(), dataBean.getTimestamp(), false, dataBean.getName(), dataBean.getNoread2(),
                                                        dataBean.getFormat_create_time());
                                                chatLists.add(chatList);
                                            }
                                            Log.i(TAG, "onSuccess: "+chatLists.size());
                                            DaoUtils.instance().addMore(chatLists);
                                            bundle.putString("time", dataBean1.getTimestamp());
                                            bundle.putString("uid", dataBean1.getShop_id()+"");
                                            bundle.putString("name", dataBean1.getName());
                                            bundle.putString("avart", notificaBean.getHeadUrl());
                                            JumpUtils.gotoActivity(activity1, Chat2Activity.class, bundle);
                                        }

                                        @Override
                                        protected void onError(String string) {

                                        }
                                    });


                        }

                    } else {
                        bundle.putString("uid", notificaBean.getId());
                        bundle.putString("name", notificaBean.getName());
                        JumpUtils.gotoActivity(activity1, ChatActivity.class, bundle);
                    }
                    //service ,link
                } else {
                    //跳转服务通知
                    JumpUtils.gotoActivity(activity1, ChatStarActivity.class);
                }
            }
        });


        handler1.removeMessages(1);
        handler1.sendEmptyMessageDelayed(1, 5000);
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            popupWindow.dismiss();
        }
    };


    //消息弹窗
    public void showMessage(Context context, View view) {
        Toast.makeText(context, "asdfasdasdasd", Toast.LENGTH_SHORT).show();
        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View inflate = LayoutInflater.from(context).inflate(R.layout.notifitiom_item, null, false);
        popupWindow.setContentView(inflate);
        popupWindow.showAtLocation(view, Gravity.TOP, 0, 0);
    }
    //
    //去认证弹窗

    public void showAutonym(Context context, View view) {
        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.phone_item, null, false);
        popupWindow.setContentView(inflate);
        changeType = inflate.findViewById(R.id.popchange_type);
        changeTv = inflate.findViewById(R.id.popchange_tv);
        inflate.findViewById(R.id.popchange_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.popchange_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        changeQuery = inflate.findViewById(R.id.popchange_query);
        changeQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("release", 0);
                JumpUtils.gotoActivity(context, AutonymASetting.class, bundle);
                popupWindow.dismiss();
            }
        });
        changeType.setText("提示");
        changeQuery.setText("去认证");
        changeTv.setText("为保证账户安全，设置支付密码需要\u0003进行实名认证请先进行实名认证");
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }


    public void showPop(Context context, View view, String query, String tips, String text) {
        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setTouchable(true);
        View inflate = LayoutInflater.from(context).inflate(R.layout.phone_item, null, false);
        popupWindow.setContentView(inflate);
        changeType = inflate.findViewById(R.id.popchange_type);
        changeTv = inflate.findViewById(R.id.popchange_tv);
        inflate.findViewById(R.id.popchange_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        inflate.findViewById(R.id.popchange_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        changeQuery = inflate.findViewById(R.id.popchange_query);
        changeQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Bundle bundle = new Bundle();
                bundle.putInt("release",0);
                JumpUtils.gotoActivity(context, AutonymASetting.class,bundle);*/
                popupWindow.dismiss();
            }
        });
        changeType.setText(tips);
        changeQuery.setText(query);
        changeTv.setText(text);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }


    //显示弹窗
    public void show(Activity context, View view, boolean b, ShareBean shareBean) {
        this.context = context;
        popupWindow = new PopupWindow(context);
        this.shareBean = shareBean;
        this.view = view;
        View inflate = LayoutInflater.from(context).inflate(R.layout.share_pop, null, false);
        shareEarn = inflate.findViewById(R.id.share_earn);
        ImageView shareWechat = inflate.findViewById(R.id.share_wechat);
        ImageView shareLink = inflate.findViewById(R.id.share_link);
        ImageView shareWechatFriends = inflate.findViewById(R.id.share_wechatfriends);
        ImageView shareQQ = inflate.findViewById(R.id.share_qq);
        ImageView shareSpace = inflate.findViewById(R.id.share_qqspace);
        ImageView shareWeibo = inflate.findViewById(R.id.share_weibo);
        ImageView sharePrint = inflate.findViewById(R.id.share_print);
        LinearLayout shareBottom = inflate.findViewById(R.id.share_bottomlin);
        shareBottom.setVisibility(View.GONE);

        if (b) {
            sharePrint.setVisibility(View.VISIBLE);
        } else {
            sharePrint.setVisibility(View.GONE);
        }
        TextView shareCancle = inflate.findViewById(R.id.share_cancle);
        shareCancle.setOnClickListener(this);
        shareWechat.setOnClickListener(this);
        shareLink.setOnClickListener(this);
        shareEarn.setOnClickListener(this);
        shareWechatFriends.setOnClickListener(this);
        shareQQ.setOnClickListener(this);
        shareSpace.setOnClickListener(this);
        shareWeibo.setOnClickListener(this);
        sharePrint.setOnClickListener(this);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(inflate);

        lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;//代表透明程度，范围为0 - 1.0f
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });

        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        String sharePrice = shareBean.getSharePrice();
        Log.i(TAG, "show: "+sharePrice);
        float i = 0;
        if (sharePrice != null && sharePrice.length() > 0) {
            i = Float.parseFloat(sharePrice);
        }

        if (i > 0 && !sharePrice.equals("")) {
            shareEarn.setVisibility(View.VISIBLE);
            shareEarn.setText("分享赚￥" + i);
        } else {
            shareEarn.setVisibility(View.GONE);
        }
    }

    public void goodsshow(Activity context, View view, GoodsBean shareBean) {
        this.context = context;
        popupWindow = new PopupWindow(context);
        this.view = view;
        this.goodsBean = shareBean;
        Log.i(TAG, "goodsshow: " + goodsBean);
        View inflate = LayoutInflater.from(context).inflate(R.layout.sharegoods_pop, null, false);
        TextView shareEarn = inflate.findViewById(R.id.share_earn);
        ImageView shareWechat = inflate.findViewById(R.id.share_wechat);
        ImageView shareWechatFriends = inflate.findViewById(R.id.share_wechatfriends);
        ImageView sharePrint = inflate.findViewById(R.id.share_print);
        shareEarn.setText(shareBean.getNeedPersor() + "");

        shareWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumb = new UMImage(context, R.drawable.qwe);
                //资源文件
                image = new UMImage(context, shareBean.getImgsrc());
                image.setThumb(thumb);
                umWeb = new UMWeb(shareBean.getUrl());
                umWeb.setTitle(shareBean.getTitle());
                umWeb.setThumb(image);
                if (shareBean.getContent() != null && !shareBean.equals("")) {
                    umWeb.setDescription(shareBean.getContent());
                } else {
                    umWeb.setDescription("as");
                }

                //描述
                image.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction((Activity) context).withText(shareBean.getContent()).withMedia(umWeb).setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(PopupWindows.this).share();

            }
        });
        shareWechatFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumb = new UMImage(context, R.drawable.qwe);
                //资源文件
                image = new UMImage(context, shareBean.getImgsrc());
                image.setThumb(thumb);
                umWeb = new UMWeb(shareBean.getUrl());
                umWeb.setTitle(shareBean.getTitle());
                umWeb.setThumb(image);
                if (shareBean.getContent() != null && !shareBean.equals("")) {
                    umWeb.setDescription(shareBean.getContent());
                } else {
                    umWeb.setDescription("as");
                }

                //描述
                image.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction((Activity) context).withText(shareBean.getContent()).withMedia(umWeb).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE)
                        .setCallback(PopupWindows.this).share();
            }
        });
        sharePrint.setOnClickListener(new View.OnClickListener() {


            private Bitmap bitmap1;

            @Override
            public void onClick(View v) {
                //分享图片
                //加载布局生成图片
                View inflate1 = LayoutInflater.from(context).inflate(R.layout.sharegoods_item, null, false);
                ImageView mShareImg = (ImageView) inflate1.findViewById(R.id.share_img);
                TextView mShareLine = (TextView) inflate1.findViewById(R.id.share_line);
                TextView mShareFrom = (TextView) inflate1.findViewById(R.id.share_from);
                RelativeLayout mShareRela = (RelativeLayout) inflate1.findViewById(R.id.share_rela);
                mShareShop = (ImageView) inflate1.findViewById(R.id.share_shop);
                TextView mSharePrice = (TextView) inflate1.findViewById(R.id.share_price);
                TextView mShareShopprice = (TextView) inflate1.findViewById(R.id.share_shopprice);
                TextView mShareAlerdy = (TextView) inflate1.findViewById(R.id.share_alerdy);
                CardView mShareCards = (CardView) inflate1.findViewById(R.id.share_cards);
                TextView mShareShoptitle = (TextView) inflate1.findViewById(R.id.share_shoptitle);
                mSharegoodsHead = (ImageView) inflate1.findViewById(R.id.sharegoods_head);
                TextView mShareShoptime = (TextView) inflate1.findViewById(R.id.share_shoptime);
                TextView shareNeed = (TextView) inflate1.findViewById(R.id.sharegoods_need);
                ImageView mShareRecode = (ImageView) inflate1.findViewById(R.id.share_recode);
                mShareRelative = (RelativeLayout) inflate1.findViewById(R.id.share_relative);
                ImageView mShareBack = (ImageView) inflate1.findViewById(R.id.share_back);
                CardView mShareCard = (CardView) inflate1.findViewById(R.id.share_card);
                LinearLayout mShareitemWechat = (LinearLayout) inflate1.findViewById(R.id.shareitem_wechat);
                LinearLayout mShareitemWechatfriends = (LinearLayout) inflate1.findViewById(R.id.shareitem_wechatfriends);
                LinearLayout mShareitemSave = (LinearLayout) inflate1.findViewById(R.id.shareitem_save);
                RelativeLayout mShareRelal = (RelativeLayout) inflate1.findViewById(R.id.share_relal);
                FrameLayout mShareFarme = (FrameLayout) inflate1.findViewById(R.id.share_farme);

                mShareFrom.setText("来自" + goodsBean.getNickname() + "的分享");
                Glide.with(context).load(goodsBean.getImgsrc()).into(mShareShop);
                mShareShoptitle.setText(goodsBean.getTitle());
                Glide.with(context).load(goodsBean.getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(mSharegoodsHead);
                mSharePrice.setText("￥" + goodsBean.getMarket_price());
                mShareShopprice.setText("￥" + goodsBean.getShop_price());
                mShareAlerdy.setText("已拼" + goodsBean.getSales() + "件");
                String str = "仅剩<font color='#FF0000'>" + goodsBean.getNeedPersor() + "</font>个名额";
                shareNeed.setText(Html.fromHtml(str));
                String strTime2 = TimeUtils.getStrTime2(goodsBean.getDuration() + "");
                mShareShoptime.setText(strTime2 + "后结束");
                Drawable drawable = context.getResources().getDrawable(R.mipmap.share_img);

                BitmapDrawable bd = (BitmapDrawable) drawable;
                final Bitmap bmm = bd.getBitmap();
                Bitmap bitmap = QrCodeUtil.createQRCodeWithLogo(goodsBean.getUrl(), 500, bmm, 0);
                mShareRecode.setImageBitmap(bitmap);
                mShareRelative.setDrawingCacheEnabled(true);
                mShareRelative.buildDrawingCache();
                ArrayList<String> strings = new ArrayList<>();
                strings.add(goodsBean.getImgsrc());
                strings.add(goodsBean.getHeadimgurl());
                getBitmap(strings);
                /*UMImage thumb = new UMImage(context, bitmap1);
                new ShareAction(context).withMedia(thumb).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(PopupWindows.this).share();*/
            }
        });

        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(inflate);
        lp = context.getWindow().getAttributes();
        lp.alpha = 0.7f;//代表透明程度，范围为0 - 1.0f
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });

        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }

    private void initView(View inflate, GoodsBean sjare) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_earn:
                break;
            case R.id.share_wechat:
                thumb = new UMImage(context, R.drawable.qwe);
                //资源文件
                image = new UMImage(context, shareBean.getImgsrc());
                image.setThumb(thumb);
                umWeb = new UMWeb(shareBean.getUrl());
                umWeb.setTitle(shareBean.getTitle());
                umWeb.setThumb(image);
                if (shareBean.getContent() != null && !shareBean.equals("")) {
                    umWeb.setDescription(shareBean.getContent());
                } else {
                    umWeb.setDescription("as");
                }

                //描述
                image.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction((Activity) context).withText(shareBean.getContent()).withMedia(umWeb).setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(this).share();
                break;
            case R.id.share_wechatfriends:
                thumb = new UMImage(context, R.drawable.qwe);
                //资源文件
                image = new UMImage(context, shareBean.getImgsrc());
                image.setThumb(thumb);
                umWeb = new UMWeb(shareBean.getUrl());
                umWeb.setTitle(shareBean.getTitle());
                umWeb.setThumb(image);
                if (shareBean.getContent() != null && !shareBean.equals("")) {
                    umWeb.setDescription(shareBean.getContent());
                } else {
                    umWeb.setDescription("as");
                }

                //描述
                image.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction((Activity) context).withText(shareBean.getContent()).withMedia(umWeb).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE)
                        .setCallback(this).share();
                break;
            case R.id.share_link:
                //复制链接
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) MyApp.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", shareBean.getUrl());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_qq:
                thumb = new UMImage(context, R.drawable.qwe);
                //资源文件
                image = new UMImage(context, shareBean.getImgsrc());
                image.setThumb(thumb);
                umWeb = new UMWeb(shareBean.getUrl());
                umWeb.setTitle(shareBean.getTitle());
                umWeb.setThumb(image);
                if (shareBean.getContent() != null && !shareBean.equals("")) {
                    umWeb.setDescription(shareBean.getContent());
                } else {
                    umWeb.setDescription("as");
                }
                //描述
                image.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction((Activity) context).withText(shareBean.getContent()).withMedia(umWeb).setPlatform(SHARE_MEDIA.QQ)
                        .setCallback(this).share();
                break;
            case R.id.share_qqspace:
                thumb = new UMImage(context, R.drawable.qwe);
                //资源文件
                image = new UMImage(context, shareBean.getImgsrc());
                image.setThumb(thumb);
                umWeb = new UMWeb(shareBean.getUrl());
                umWeb.setTitle(shareBean.getTitle());
                umWeb.setThumb(image);
                if (shareBean.getContent() != null && !shareBean.equals("")) {
                    umWeb.setDescription(shareBean.getContent());
                } else {
                    umWeb.setDescription("as");
                }

                //描述
                image.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction((Activity) context).withText(shareBean.getContent()).withMedia(umWeb).setPlatform(SHARE_MEDIA.QZONE)
                        .setCallback(this).share();
                break;
            case R.id.share_weibo:
                thumb = new UMImage(context, R.drawable.qwe);
                //资源文件
                image = new UMImage(context, shareBean.getImgsrc());
                image.setThumb(thumb);
                umWeb = new UMWeb(shareBean.getUrl());
                umWeb.setTitle(shareBean.getTitle());
                umWeb.setThumb(image);
                if (shareBean.getContent() != null && !shareBean.equals("")) {
                    umWeb.setDescription(shareBean.getContent());
                } else {
                    umWeb.setDescription("as");
                }
                //描述
                image.compressStyle = UMImage.CompressStyle.SCALE;
                new ShareAction((Activity) context).withText(shareBean.getContent()).withMedia(umWeb).setPlatform(SHARE_MEDIA.SINA)
                        .setCallback(this).share();
                break;
            case R.id.share_print:
                //生成二维码并取消弹窗
                showRecord();
                popupWindow.dismiss();
                break;
            case R.id.share_cancle:

                popupWindow.dismiss();
                break;

        }
    }

    private void showRecord() {
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setBackgroundDrawable(null);
        View inflate = LayoutInflater.from(context).inflate(R.layout.share_item, null, false);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(inflate);
        shareShop = inflate.findViewById(R.id.share_shop);
        relativeLayout = inflate.findViewById(R.id.share_relative);
        ImageView shareRecde = inflate.findViewById(R.id.share_recode);
        FrameLayout frameLayout = inflate.findViewById(R.id.share_farme);
        TextView sharePrice = inflate.findViewById(R.id.share_shopprice);
        TextView shareTitle = inflate.findViewById(R.id.share_shoptitle);
        sharewechat = inflate.findViewById(R.id.shareitem_wechat);
        sharefriedns = inflate.findViewById(R.id.shareitem_wechatfriends);
        shareSave = inflate.findViewById(R.id.shareitem_save);
        ImageView shareBack = inflate.findViewById(R.id.share_back);
        shareBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        Drawable drawable = context.getResources().getDrawable(R.mipmap.share_img);

        BitmapDrawable bd = (BitmapDrawable) drawable;
        final Bitmap bmm = bd.getBitmap();
        Bitmap bitmap = QrCodeUtil.createQRCodeWithLogo(shareBean.getUrl(), 500, bmm, 0);
        shareRecde.setImageBitmap(bitmap);
        sharePrice.setText("￥" + shareBean.getPrice());
        shareTitle.setText(shareBean.getTitle());
        Bitmap bitmap2 = getBitmap(shareBean.getImgsrc());
        shareShop.setImageBitmap(bitmap2);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        //保存图片
        relativeLayout.setDrawingCacheEnabled(true);
        relativeLayout.buildDrawingCache();
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        Log.i(TAG, "onError: " + share_media);
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        Log.i(TAG, "onError: 失败了" + share_media + "===" + throwable.getMessage());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            shareShop.setImageBitmap((Bitmap) msg.obj);
            Bitmap bmp = null;
            try {
                bmp = saveView(context, relativeLayout, "ajiani", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //final Bitmap bmp = relativeLayout.getDrawingCache(); // 获取图片

            //File file = savePicture(bmp, "qwe.jpg");// 保存图片

            Bitmap finalBmp = bmp;
            sharewechat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UMImage thumb = new UMImage(context, finalBmp);
                    new ShareAction(context).withMedia(thumb).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(PopupWindows.this).share();
                }
            });
            Bitmap finalBmp1 = bmp;
            shareSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //保存本地
                    try {
                        saveFile(finalBmp1, "shop.jpg");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            sharefriedns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UMImage thumb = new UMImage(context, finalBmp);
                    new ShareAction(context).withMedia(thumb).setPlatform(SHARE_MEDIA.WEIXIN_FAVORITE).setCallback(PopupWindows.this).share();
                }
            });

            return false;
        }
    });
    private Bitmap bitmap;

    public Bitmap getBitmap(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    Message obtain = Message.obtain();
                    obtain.obj = bitmap;
                    handler.sendMessage(obtain);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return bitmap;
    }

    @SuppressLint("HandlerLeak")
    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    mShareShop.setImageBitmap(bitmap);
                    break;
                case 1:
                    Bitmap bitmap1 = (Bitmap) msg.obj;

                    mSharegoodsHead.setImageBitmap(bitmap1);
                    try {
                        Bitmap bitmap2 = saveView(context, mShareRelative, "ajiani", false);
                        UMImage thumb = new UMImage(context, bitmap2);
                        new ShareAction(context).withMedia(thumb).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(PopupWindows.this).share();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }

        }
    };

    public Bitmap getBitmap(final ArrayList<String> url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;
                for (int i = 0; i < url.size(); i++) {
                    try {
                        imageurl = new URL(url.get(i));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        HttpURLConnection conn = (HttpURLConnection) imageurl.openConnection();
                        conn.setDoInput(true);
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                        Message obtain = Message.obtain();
                        obtain.what = i;
                        obtain.obj = bitmap;
                        handler2.sendMessage(obtain);
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        return bitmap;
    }

    public Bitmap saveView(Activity activity, View view, String savePathName, boolean b) throws Exception {

        //计算设备分辨率
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        // 整个View的大小 参数是左上角 和右下角的坐标
        view.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST);

        //测量，布局View
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cacheBmp = viewConversionBitmap(view);
        if (b) {
            File file = new File(Environment.getExternalStorageDirectory() + "/Ask" + savePathName);
            FileOutputStream fos = new FileOutputStream(file);

            cacheBmp.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
            view.destroyDrawingCache();
            //发送广播更新相册
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            activity.sendBroadcast(intent);
            Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
            return cacheBmp;
        } else {
            return cacheBmp;
        }

    }

    /**
     * view转bitmap
     *
     * @param v View
     * @return Bitmap
     */
    private static Bitmap viewConversionBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */
        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }

    public void saveFile(Bitmap bm, String fileName) throws IOException {//将Bitmap类型的图片转化成file类型，便于上传到服务器
        String path = Environment.getExternalStorageDirectory() + "/Ask";
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
    }
}
