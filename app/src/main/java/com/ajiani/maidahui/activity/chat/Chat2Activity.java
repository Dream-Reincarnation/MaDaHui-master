package com.ajiani.maidahui.activity.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.MainActivity;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.ChatUiHelper;
import com.ajiani.maidahui.Utils.DaoUtils;
import com.ajiani.maidahui.Utils.DensityUtil;
import com.ajiani.maidahui.Utils.EmotionUtils;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.MediaManager;
import com.ajiani.maidahui.Utils.SPUtils;

import com.ajiani.maidahui.Utils.chat.LogUtil;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.Utils.photo.PictureUtils;
import com.ajiani.maidahui.activity.MyApp;


import com.ajiani.maidahui.activity.mine.BillInfoActivity;
import com.ajiani.maidahui.adapter.chat.StyleAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.ListBean;
import com.ajiani.maidahui.bean.chat.ServiceMessageBean;
import com.ajiani.maidahui.bean.chat.SystemBean;
import com.ajiani.maidahui.bean.chat.TransferBean;
import com.ajiani.maidahui.bean.chat.UserMessageListBean;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.bean.sockets.OfferBean;
import com.ajiani.maidahui.bean.sockets.RedPackageBean;
import com.ajiani.maidahui.bean.sockets.RemarkBean;

import com.ajiani.maidahui.bean.sockets.ShopInfo;
import com.ajiani.maidahui.bean.sockets.ShopMsg;
import com.ajiani.maidahui.bean.sockets.ShopMsg2;

import com.ajiani.maidahui.chat.ChatAdapter;
import com.ajiani.maidahui.chat.bean.Message;
import com.ajiani.maidahui.chat.bean.MsgBody;
import com.ajiani.maidahui.chat.bean.MsgType;
import com.ajiani.maidahui.chat.bean.TextMsgBody;
import com.ajiani.maidahui.dao.MessageBean;
import com.ajiani.maidahui.dao.MessageBeanDao;
import com.ajiani.maidahui.dao.ShopMessage;
import com.ajiani.maidahui.dao.ShopMessageDao;
import com.ajiani.maidahui.mInterface.chat.UserMessageList;
import com.ajiani.maidahui.presenters.chat.MessageListPresenter;
import com.ajiani.maidahui.weight.chat.RecordButton;
import com.ajiani.maidahui.weight.chat.StateButton;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.recordvocie.AudioRecordManager;
import com.example.recordvocie.IAudioRecordListener;
import com.google.gson.Gson;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import io.socket.emitter.Emitter;
import pub.devrel.easypermissions.EasyPermissions;

public class Chat2Activity extends BaseActivity<UserMessageList.MessageListView, MessageListPresenter> implements UserMessageList.MessageListView {
    @BindView(R.id.chatact_rel)
    RecyclerView chatactRel;
    @BindView(R.id.chatact_lin)
    LinearLayout chatactLin;
    @BindView(R.id.chat_style)
    RecyclerView chatstyle;
    @BindView(R.id.chat_lin)
    LinearLayout chatLin;
    @BindView(R.id.chat_lin2)
    LinearLayout chatactLin2;
    @BindView(R.id.chat_et)
    EditText chatEt;
    @BindView(R.id.chat_smart)
    SmartRefreshLayout smart;
    @BindView(R.id.rlVideo)
    RelativeLayout relativeLayout;
    @BindView(R.id.rlFile)
    RelativeLayout relativeLayoutPic;
    @BindView(R.id.chat_send)
    StateButton chatSend;
    @BindView(R.id.chatact_back)
    FrameLayout chatBack;
    @BindView(R.id.chatact_title)
    TextView chatTitle;
    private static ChatAdapter chatAdapter;
    private MsgBean data;
    private static String uerid;
    private MsgBean messageBean;
    private String extra;
    @BindView(R.id.bottom_layout)
    RelativeLayout mRlBottomLayout;//表情,添加底部布局
    @BindView(R.id.ivAdd)
    ImageView mIvAdd;
    @BindView(R.id.chat2_set)
    ImageView chatset;
    @BindView(R.id.ivEmo)
    ImageView mIvEmo;
    public static String mSenderId = "Right";
    @BindView(R.id.rlPhoto)
    RelativeLayout relativeLayoutphoto;//拍照按钮
    @BindView(R.id.ivAudio)
    ImageView mIvAudio;//录音图片
    @BindView(R.id.btnAudio)
    Button mBtnAudio;//录音按钮
    @BindView(R.id.rlEmotion)
    FrameLayout mLlEmotion;//表情布局
    @BindView(R.id.llAdd)
    LinearLayout mLlAdd;//添加布局
    private String name;
    private String avart;
    private float rate;
    private int heightDifference;
    private String time;
    private PopupWindow popupWindow1;
    private String TAG = "wxy";
    String[] psermission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    public boolean isDataBase;
    private int timestamp;
    private int page = 1;

    @Override
    public void error(String error) {

    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private boolean isCancelled(View view, MotionEvent event) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        if (event.getRawX() < location[0] || event.getRawX() > location[0] + view.getWidth() || event.getRawY() < location[1] - 40) {
            return true;
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {

        //跳转聊天设置页面
        chatset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("head",avart);
                bundle.putString("name",name);
                JumpUtils.gotoActivity(Chat2Activity.this,ChatSetActivity.class,bundle);
            }
        });


        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add("ssdsd"+i);
        }
        StyleAdapter styleAdapter = new StyleAdapter(strings);
        chatstyle.setLayoutManager(new GridLayoutManager(this,1,LinearLayoutManager.HORIZONTAL,false));
        chatstyle.setAdapter(styleAdapter);
        AudioRecordManager.getInstance(this).setMaxVoiceDuration(60);

        AudioRecordManager.getInstance(this).setAudioSavePath(FileUtils.getCache(this));

        mBtnAudio.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //申请权限  判断是否有录音权限
                        if (EasyPermissions.hasPermissions(Chat2Activity.this, psermission)) {
                            //打开相机和录像
                            AudioRecordManager.getInstance(Chat2Activity.this).startRecord();
                        } else {
                            //去申请
                            EasyPermissions.requestPermissions(Chat2Activity.this, "需要获取你的录音权限", 1001, psermission);
                        }

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isCancelled(v, event)) {
                            AudioRecordManager.getInstance(Chat2Activity.this).willCancelRecord();
                        } else {
                            AudioRecordManager.getInstance(Chat2Activity.this).continueRecord();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        AudioRecordManager.getInstance(Chat2Activity.this).stopRecord();
                        AudioRecordManager.getInstance(Chat2Activity.this).destroyRecord();
                        break;
                }
                return false;
            }
        });
        AudioRecordManager.getInstance(this).setAudioRecordListener(new IAudioRecordListener() {

            private TextView mTimerTV;
            private TextView mStateTV;
            private ImageView mStateIV;
            private PopupWindow mRecordWindow;

            @Override
            public void initTipView() {
                //初始化提示图
                View view = View.inflate(Chat2Activity.this, R.layout.popup_audio_wi_vo, null);
                mStateIV = (ImageView) view.findViewById(R.id.rc_audio_state_image);
                mStateTV = (TextView) view.findViewById(R.id.rc_audio_state_text);
                mTimerTV = (TextView) view.findViewById(R.id.rc_audio_timer);
                mRecordWindow = new PopupWindow(view, -1, -1);
                mRecordWindow.showAtLocation(mBtnAudio, Gravity.CENTER, 0, 0);
                mRecordWindow.setFocusable(true);
                mRecordWindow.setOutsideTouchable(false);
                mRecordWindow.setTouchable(false);
            }

            @Override
            public void setTimeoutTipView(int counter) {
                //设置倒计时
              /*  if (this.mRecordWindow != null) {
                    this.mStateIV.setVisibility(View.GONE);
                    this.mStateTV.setVisibility(View.VISIBLE);
                    this.mStateTV.setText(R.string.voice_rec);
                    this.mStateTV.setBackgroundResource(R.drawable.bg_voice_popup);
                    this.mTimerTV.setText(String.format("%s", new Object[]{Integer.valueOf(counter)}));
                    this.mTimerTV.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void setRecordingTipView() {
                //设置正在录制提示图
                if (this.mRecordWindow != null) {
                    this.mStateIV.setVisibility(View.VISIBLE);
                    this.mStateIV.setImageResource(R.drawable.ic_volume_0);
                    this.mStateTV.setVisibility(View.VISIBLE);
                    this.mStateTV.setText("手指上滑，取消发送");
                    this.mStateTV.setBackgroundResource(R.drawable.bg_voice_popup);
                    this.mTimerTV.setVisibility(View.GONE);
                }
            }

            @Override
            public void setAudioShortTipView() {
                //设置语音太短提示
                if (this.mRecordWindow != null) {
                    mStateIV.setImageResource(R.drawable.ic_volume_wraning);
                    mStateTV.setText("录音时间太短");
                }
            }

            @Override
            public void setCancelTipView() {
                //设置取消提示图
                if (this.mRecordWindow != null) {
                    this.mTimerTV.setVisibility(View.GONE);
                    this.mStateIV.setVisibility(View.VISIBLE);
                    this.mStateIV.setImageResource(R.drawable.ic_volume_cancel);
                    this.mStateTV.setVisibility(View.VISIBLE);
                    this.mStateTV.setText("松开手指，取消发送");
                    this.mStateTV.setBackgroundResource(R.drawable.corner_voice_style);
                }
            }

            @Override
            public void destroyTipView() {
                // 销毁提示视图
                if (this.mRecordWindow != null) {
                    this.mRecordWindow.dismiss();
                    this.mRecordWindow = null;
                    this.mStateIV = null;
                    this.mStateTV = null;
                    this.mTimerTV = null;
                }
            }

            @Override
            public void onStartRecord() {
                //开始录制
                //     * 如果是做IM的话，这里可以发送一个消息，如：对方正在讲话
            }

            @Override
            public void onFinish(Uri audioPath, int duration) {
                //录制结束
                File file = new File(audioPath.getPath());
                if (file.exists()) {
                    Toast.makeText(getApplicationContext(), "录制成功", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onAudioDBChanged(int db) {

                //分贝的变化
                switch (db / 3) {
                    case 0:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_1);
                        break;
                    case 1:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_2);
                        break;
                    case 2:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_3);
                        break;
                    case 3:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_4);
                        break;
                    case 4:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_5);
                        break;
                    case 5:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_6);
                        break;
                    case 6:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_7);
                        break;
                    default:
                        this.mStateIV.setImageResource(R.drawable.ic_volume_8);
                }
            }
        });



        //发送图片
        relativeLayoutPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureUtils.startPhoto(Chat2Activity.this);
            }

        });
        relativeLayoutphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureUtils.startCarame(Chat2Activity.this);
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出转账的页面
                //商家名称  图片
                Bundle bundle = new Bundle();
                bundle.putString("shopid", uerid);
                bundle.putString("shopname", name);
                bundle.putString("avart", avart);

                JumpUtils.gotoActivity(Chat2Activity.this, TransferActivity.class, bundle, 4811);

               /* HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("shop_id",uerid);
                hashMap.put("votes","1000");
                hashMap.put("remark","赏你的");
                mPresenter.getVoteData(hashMap);*/
            }
        });
        boolean isFirst = (boolean) SPUtils.get(this, "isFirst", false);
        if (isFirst) {
        } else {
        }
        chatactRel.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shop_id", uerid);
        mPresenter.getServiceMessage(hashMap);


        chatAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                List<Message> data = chatAdapter.getData();
                Message message = data.get(position);
                MsgType msgType = message.getMsgType();
                if (MsgType.AUDIO == msgType) {

                    TextMsgBody body = (TextMsgBody) message.getBody();
                    String extra = body.getExtra();

                    JSONObject jsonObject = JSON.parseObject(extra);
                    //上传录音，播放录音
                    MediaManager.playSound(Chat2Activity.this, jsonObject.getString("path"), new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            MediaManager.release();
                        }
                    });
                } else if (msgType == MsgType.Offer) {
                    //跳转到 大勇页面
                    TextMsgBody body = (TextMsgBody) message.getBody();
                    String extra = body.getExtra();
                    OfferBean.ExtraBean extra1 = new Gson().fromJson(extra, OfferBean.ExtraBean.class);
                    String s1 = new Gson().toJson(extra1);

                    String s = Base64.encodeToString(s1.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);

                    Bundle bundle = new Bundle();
                    bundle.putString("json", s);
                    // {"goods_id":769,"number":1,"price":"1.00","remark":"1","sku":"1","sku_path":"5a2df8b2c3ae15ead9281c172f6f497c","thumb":"https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/e4/d7981c8f2f1d47478591fcd7fe069a.jpg","title":"黑色皮鞋男士潮流软底商务正装青年圆头休闲鞋英伦韩版内增高男鞋"}
                    JumpUtils.gotoActivity(Chat2Activity.this, ShopInfoActivity.class, bundle);
                } else if (msgType == MsgType.Transfer) {
                    TextMsgBody body = (TextMsgBody) message.getBody();
                    String extra = body.getExtra();
                    JSONObject jsonObject = JSON.parseObject(extra);
                    Bundle bundle = new Bundle();
                    bundle.putString("order_no", jsonObject.getString("order_no"));
                    JumpUtils.gotoActivity(Chat2Activity.this, BillInfoActivity.class, bundle);
                } else if (msgType == MsgType.COMMNET) {
                    TextMsgBody body = (TextMsgBody) message.getBody();
                    String extra = body.getExtra();
                    //弹出popUpwINDOW 进行点评

                    popupWindow1 = new PopupWindow(Chat2Activity.this);
                    popupWindow1.setWidth(dip2px(Chat2Activity.this, 300));
                    popupWindow1.setHeight(dip2px(Chat2Activity.this, 300));
                    View inflate = LayoutInflater.from(Chat2Activity.this).inflate(R.layout.item_ping, null, false);
                    popupWindow1.setContentView(inflate);
                    TextView cancle = inflate.findViewById(R.id.ping_cancle);
                    cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow1.dismiss();
                        }
                    });
                    ImageView img = inflate.findViewById(R.id.ping_img);
                    Glide.with(Chat2Activity.this).load(avart).into(img);
                    RatingBar ratingBar = inflate.findViewById(R.id.mRatingBar);
                    TextView name = inflate.findViewById(R.id.ping_name);
                    TextView query = inflate.findViewById(R.id.ping_query);
                    ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                            rate = rating * 2;
                        }
                    });

                    popupWindow1.showAtLocation(smart, Gravity.CENTER, 0, 0);

                    query.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //进行点评
                            JSONObject jsonObject = JSON.parseObject(extra);
                            HashMap<String, String> hashMap = new HashMap<>();
                            String stream = jsonObject.getString("stream");
                            hashMap.put("stream", stream);
                            hashMap.put("score", rate + "");
                            if (rate < 1) {
                                Toast.makeText(Chat2Activity.this, "请打分", Toast.LENGTH_SHORT).show();
                            } else {
                                mPresenter.getServiceCommnet(hashMap);
                            }
//                            mPresenter.getServiceCommnet(hashMap);
                        }
                    });
                 /*   //弹窗  进行点评
                    PopupWindow popupWindow = new PopupWindow(Chat2Activity.this);
                    popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    LayoutInflater.from(Chat2Activity.this).inflate(R.layout.remark,null,false);
                    popupWindow.showAtLocation(smart, Gravity.CENTER,0,0);*/
                } else if (msgType == MsgType.IMAGE) {

                    //点击图片 得到ImageView
                    TextMsgBody textMsgBody = (TextMsgBody) message.getBody();
                    String extra = textMsgBody.getExtra();
                    JSONObject jsonObject = JSON.parseObject(extra);
                    String path = jsonObject.getString("path");
                    Bundle bundle = new Bundle();
                    bundle.putString("img", path);
                    JumpUtils.gotoActivity(Chat2Activity.this, ChatImageActivity.class, bundle);
                }
            }
        });
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //判断是否查询数据库
                if(isDataBase){
                    Log.i(TAG, "onRefresh: 走的是数据库");
                    ArrayList<Message> messages = new ArrayList<>();
                    page++;
                    List<ShopMessage>  list= DaoUtils.instance().shopMessageDao.queryBuilder().where(ShopMessageDao.Properties.Room.eq(uerid), ShopMessageDao.Properties.Time.le(timestamp)).orderDesc(ShopMessageDao.Properties.Time).offset(page).limit(20).list();
                    if (list.size() > 0) {
                        if (list.size() == 1) {
                            timestamp = (int) list.get(list.size() - 1).getTime();
                        } else {
                            timestamp = (int) list.get(list.size() - 1).getTime();
                        }
                    }

                    for (int i = list.size() - 1; i >= 0; i--) {
                        ShopMessage messageBean = list.get(i);
                        MsgBean msgBean = new MsgBean();
                        if (messageBean.getIsme()) {
                            msgBean.setLeft(true);
                        } else {
                            msgBean.setLeft(false);
                        }

                        msgBean.setToid(messageBean.getToUserID() + "");
                        msgBean.setUid(messageBean.getUserID() + "");
                        msgBean.setAvatar(messageBean.getAvatar());
                        msgBean.setCt(messageBean.getContent());
                        SpannableString expressionString = getExpressionString(Chat2Activity.this, messageBean.getContent());
                        msgBean.setSpannableString(expressionString);
                        msgBean.setAction(messageBean.getAction());
                        msgBean.setExtra(messageBean.getExtra());
                        msgBean.setMsgid(messageBean.getMsgId());
                        msgBean.setTimestamp((int) messageBean.getTime());
                        Message updata = Updata(msgBean);
                        messages.add(updata);
                    }
                    chatAdapter.addData(0, messages);
                    smart.finishRefresh(true);


                }else{
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("shop_id", uerid);
                    hashMap.put("msgid", chatAdapter.getData().get(0).getMsgId());
                    mPresenter.getServiceMessage(hashMap);
                    smart.finishRefresh(true);
                }

            }
        });
       /*  smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore(true);
            }
        });*/
    /*  chatAdapter.setOnItemClickLinstener(new ChatAdapter.onItemClickLinstener() {
          @Override
          public void onItemClick(int posstion) {
              Toast.makeText(Chat2Activity.this, "你点击了报价", Toast.LENGTH_SHORT).show();
                  String extra = chatAdapter.mList.get(posstion).getExtra();

              OfferBean.ExtraBean extra1 = new Gson().fromJson(extra, OfferBean.ExtraBean.class);

              extra1.setNumber(1);
              String s1 = new Gson().toJson(extra1);
//              Log.i("wxy", "onItemClick: "+s1);
              String s = Base64.encodeToString(s1.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
//              Log.i("WXY", "onItemClick: "+s);
              Bundle bundle = new Bundle();
              bundle.putString("json",s);
              JumpUtils.gotoActivity(Chat2Activity.this,ShopInfoActivity.class,bundle);

          }
      });
        chatAdapter.setOnClickLinstener(new ChatAdapter.onClickLinstener() {

            private PopupWindow popupWindow1;

            @Override
            public void onClick(int posstion) {
                Toast.makeText(Chat2Activity.this, "", Toast.LENGTH_SHORT).show();
                popupWindow1 = new PopupWindow(Chat2Activity.this);
                popupWindow1.setWidth(dip2px(Chat2Activity.this,300));
                popupWindow1.setHeight(dip2px(Chat2Activity.this,300));
                View inflate = LayoutInflater.from(Chat2Activity.this).inflate(R.layout.item_ping, null, false);
                popupWindow1.setContentView(inflate);
                TextView cancle = inflate.findViewById(R.id.ping_cancle);
                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow1.dismiss();
                    }
                });
                ImageView img = inflate.findViewById(R.id.ping_img);
                Glide.with(Chat2Activity.this).load(avart).into(img);
                RatingBar ratingBar = inflate.findViewById(R.id.mRatingBar);
                TextView name = inflate.findViewById(R.id.ping_name);
                TextView query = inflate.findViewById(R.id.ping_query);
                    ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                         rate = rating*2;
                    }
                });

                query.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //进行点评
                        HashMap<String, String> hashMap = new HashMap<>();
                        extra = chatAdapter.mList.get(posstion).getExtra();
                        hashMap.put("stream",extra);
                        hashMap.put("score",ratingBar+"");
                        if(rate<1){
                            Toast.makeText(Chat2Activity.this, "请打分", Toast.LENGTH_SHORT).show();
                        }else{
                            mPresenter.getServiceCommnet(hashMap);
                        }
                        mPresenter.getServiceCommnet(hashMap);
                    }
                });
                //弹窗  进行点评
                PopupWindow popupWindow = new PopupWindow(Chat2Activity.this);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                LayoutInflater.from(Chat2Activity.this).inflate(R.layout.remark,null,false);
                popupWindow.showAtLocation(smart, Gravity.CENTER,0,0);
                //进行网络请求 评分
               
            }
        });*/
    }

    //接受回传值


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> selectList;
        if (requestCode == 4811&&resultCode==1234) {
            String success = data.getStringExtra("transfer");
            String text = data.getStringExtra("text");
            TransferBean transferBean = new Gson().fromJson(success, TransferBean.class);

            org.json.JSONObject jsonObject = new org.json.JSONObject();
            try {
                jsonObject.put("money", transferBean.getData().getMoney());
                jsonObject.put("order_no", transferBean.getData().getOrder_no());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MsgBean msgBean = new MsgBean();
            msgBean.setMsgid("");
            msgBean.setUid(uerid);
            String nickname = (String) SPUtils.get(this, "username", "");
            String headimgurl = (String) SPUtils.get(this, "head", "");
            msgBean.setAvatar(headimgurl);
            msgBean.setNickname(nickname);
            msgBean.setExtra(jsonObject.toString());
            msgBean.setLeft(false);
            msgBean.setCt(text);
            msgBean.setSpannableString(getExpressionString(this, text));
            msgBean.setNickname("");
            msgBean.setAction("8");
            Message updata = Updata(msgBean);
//            chatAdapter.mList.add(msgBean);
            chatAdapter.addData(updata);
            //  chatAdapter.notifyDataSetChanged();
            ShopMsg2 shopMsg2 = new ShopMsg2(uerid, "", text, jsonObject);
            //发送消息
            MyApp.sendShop2(shopMsg2, "8");
        } else if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    // 4.media.getAndroidQToPath();为Android Q版本特有返回的字段，此字段有值就用来做上传使用
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        String androidQToPath = selectList.get(0).getAndroidQToPath();
                        BitmapFactory.Options options = new BitmapFactory.Options();


                        Log.i("wxy", "onActivityResult: " + androidQToPath);
                        Bitmap img = BitmapFactory.decodeFile(androidQToPath, options);
                        Log.i("wxy", "onActivityResult: " + img);
                        HttpUtils.instance().uoLoadImg(new File(androidQToPath));
                        File file = new File(androidQToPath);

                    } else {
                        String compressPath = selectList.get(0).getCompressPath();
                        //上传图片
                        HttpUtils.instance().uoLoadImg(new File(compressPath));

                    }


                    break;
                case PictureConfig.CAMERA:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    // 4.media.getAndroidQToPath();为Android Q版本特有返回的字段，此字段有值就用来做上传使用
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        String androidQToPath = selectList.get(0).getAndroidQToPath();
                        HttpUtils.instance().uoLoadImg(new File(androidQToPath));
                        File file = new File(androidQToPath);
                        Log.i("wxy", "onActivityResult: Q" + androidQToPath + "==" + file.length());
                    } else {
                        String compressPath = selectList.get(0).getCompressPath();
                        //上传图片
                        HttpUtils.instance().uoLoadImg(new File(compressPath));
                        Log.i("wxy", "onActivityResult: " + compressPath);
                    }
                    break;
            }
        }
    }

    public static void getPhotoPath(String url) {
        //图片上传成功       发送消息

        String nickname = (String) SPUtils.get(MyApp.getApp(), "username", "");
        String headimgurl = (String) SPUtils.get(MyApp.getApp(), "head", "");
        MsgBean msgBean = new MsgBean();
        msgBean.setCt("[图片]");
        msgBean.setLeft(false);
        msgBean.setAvatar(headimgurl);
        msgBean.setNickname(nickname);
        long l = System.currentTimeMillis();
        msgBean.setTimestamp((int) (l / 1000));
        msgBean.setAction("4");
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("path", url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        msgBean.setExtra(jsonObject.toString());
        msgBean.setUid(uerid);
        Message updata = Updata(msgBean);
        chatAdapter.addData(updata);
        ShopMsg2 sendMsg = new ShopMsg2(uerid, "", "[图片]", jsonObject);
        MyApp.sendShop2(sendMsg, "4");
    }

/*
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(MsgBean reciverBean) {
        //MsgBean 变为 MessaheBean
        Message message = Updata(reciverBean);
        //    this.data=reciverBean;
        if (chatAdapter != null) {
//            chatAdapter.mList.add(reciverBean);
            chatAdapter.addData(message);
            if (chatAdapter.getItemCount() > 0) {
                chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
            }
          //  chatAdapter.notifyDataSetChanged();
        }
    }
*/


    public static Message Updata(MsgBean messageBean) {
        Message message = new Message();
        message.setAction(messageBean.getAction());
        message.setAvatar(messageBean.getAvatar());
        TextMsgBody textMsgBody = new TextMsgBody();
        textMsgBody.setMessage(messageBean.getCt());
        if (messageBean.getExtra() instanceof String) {
            textMsgBody.setExtra((String) messageBean.getExtra());
        } else {
            textMsgBody.setExtra(new Gson().toJson(messageBean.getExtra()));
        }
        /*  textMsgBody.setExtra( new Gson().toJson(messageBean.getExtra()));*/
        textMsgBody.setSpannableString(messageBean.getSpannableString());
        message.setBody(textMsgBody);
        message.setMsgId(messageBean.getMsgid());
        message.setNickName(messageBean.getNickname());
        message.setSentTime(messageBean.getTimestamp());
        message.setShopID(messageBean.getShopid() + "");
        message.setToUserID(messageBean.getToid());
        message.setUserID(messageBean.getUid());
        String user_id = (String) SPUtils.get(MyApp.getApp(), "userid", "");
        if (messageBean.isLeft()) {
            //是自己的
            message.setSenderId("Left");
        } else {
            message.setSenderId("Right");
        }


        //得到自己的
        //判断action 赋值消息类型
        switch (messageBean.getAction()) {
            //文本消息

            case "1":
                message.setMsgType(MsgType.TEXT);

                break;

            //携带商品
            case "3":
                message.setMsgType(MsgType.Shop);
                break;
            //发送图片
            case "4":
                message.setMsgType(MsgType.IMAGE);
                break;
            //点评
            case "5":
                message.setMsgType(MsgType.COMMNET);
                break;
            //定制报价付款
            case "7":
                message.setMsgType(MsgType.Offer);
                break;
            //转账
            case "8":
                message.setMsgType(MsgType.Transfer);
                break;

            case "6":
                break;
            case "9":
                message.setMsgType(MsgType.AUDIO);
                break;
        }

        return message;
    }

    @Override
    protected void initView() {
        chatEt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            //当键盘弹出隐藏的时候会 调用此方法。
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //获取当前界面可视部分
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //获取屏幕的高度
                int screenHeight = getWindow().getDecorView().getRootView().getHeight();
                //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
                heightDifference = screenHeight - r.bottom;
//                Log.d("Keyboard Size", "Size: " + heightDifference);
            }

        });

        //  smart.setEnableLoadMore(false);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        name = bundle.getString("name");
        String string = bundle.getString("extra");
        avart = bundle.getString("avart");
        time = bundle.getString("time");
        uerid = bundle.getString("uid");
        Log.i(TAG, "initView: "+uerid);
//        ArrayList<MsgBean> msgBeans = new ArrayList<>();
        ArrayList<Message> msgBeans = new ArrayList<>();
        chatactRel.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this, msgBeans);
        chatactRel.setAdapter(chatAdapter);
        if (string != null && string.length() > 5) {
            ShopInfo shopInfo = new Gson().fromJson(string, ShopInfo.class);

            org.json.JSONObject jsonObject = new org.json.JSONObject();
            try {
                jsonObject.put("goods_id", shopInfo.getGoods_id());
                jsonObject.put("title", shopInfo.getTitle());
                jsonObject.put("thumb", shopInfo.getThumb());
                jsonObject.put("price", shopInfo.getGoods_price());
                jsonObject.put("sku", shopInfo.getSku());
                jsonObject.put("sku_path", shopInfo.getSku_path());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MsgBean msgBean = new MsgBean();
            msgBean.setMsgid("");
            msgBean.setUid(uerid);
            String nickname = (String) SPUtils.get(this, "username", "");
            String headimgurl = (String) SPUtils.get(this, "head", "");
            msgBean.setAvatar(headimgurl);
            msgBean.setNickname(nickname);
            msgBean.setExtra(string);
            msgBean.setLeft(false);
            msgBean.setCt("你好");
            msgBean.setSpannableString(getExpressionString(this, "你好"));
            msgBean.setNickname("");
            msgBean.setAction("3");
            Message message = Updata(msgBean);
//            chatAdapter.mList.add(msgBean);
//            chatAdapter.notifyDataSetChanged();
            chatAdapter.addData(message);
            if (chatAdapter.getItemCount() > 0) {
                chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
            }
            ShopMsg2 sendMsg = new ShopMsg2(uerid, "", "你好", jsonObject);
            MyApp.sendShop2(sendMsg, "3");
        } else {
            long l = System.currentTimeMillis();
            long i = Integer.parseInt(time);
            MsgBean msgBean = new MsgBean();
            msgBean.setMsgid("");
            msgBean.setUid(uerid);
            String nickname = (String) SPUtils.get(this, "username", "");
            String headimgurl = (String) SPUtils.get(this, "head", "");
            msgBean.setAvatar(headimgurl);
            msgBean.setNickname(nickname);
            Log.i("WXY", "onBindViewHolder: " + headimgurl);
            msgBean.setExtra(string);
            msgBean.setLeft(false);
            msgBean.setCt("你好");
            msgBean.setSpannableString(getExpressionString(this, "你好"));
            msgBean.setNickname("");
            msgBean.setAction("1");
            Message message = Updata(msgBean);

           /* chatAdapter.mList.add(msgBean);
            chatAdapter.notifyDataSetChanged();*/
            l = l - (i * 1000);

            if (l > 300000) {
                chatAdapter.addData(message);
                ShopMsg2 sendMsg = new ShopMsg2(uerid, "", "你好", string);
                MyApp.sendShop2(sendMsg, "1");
            }

        }


        chatTitle.setText(name);
        initChatUi();

        MyApp.socket.on("broadcastingListen", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                for (int i = 0; i < args.length; i++) {
                    JSONArray jsonArray = (JSONArray) args[i];
                    try {
                        Log.i("WXY", "call: " + jsonArray.get(0));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String s = (String) jsonArray.get(0);
                                    JSONObject jsonObject = JSON.parseObject(s);
                                    String code = jsonObject.getString("_method_");
                                    Log.i("WXY", "run: " + code);
                                    switch (code) {
                                        case "addDuobao":
                                            break;
                                        case "addDuobaoSuccess":
                                            break;
                                        case "SendMsg":
                                            //接受客服的消息
                                            String action = jsonObject.getString("action");
                                            String shopid = jsonObject.getString("shopid");
                                            if (Integer.parseInt(shopid) > 0) {
                                                if (action.equals("1")) {
                                                    data = new Gson().fromJson(s, MsgBean.class);
                                                    data.setLeft(true);

                                                    chatAdapter.addData(Updata(data));
                                                   /* chatAdapter.mList.add(data);
                                                    chatAdapter.notifyDataSetChanged();*/
                                                }
                                                sel(action, s);
                                            }

                                            break;
                                        case "orderShippingSuccess":
                                            JSONObject jsonObject1 = JSON.parseObject(s);
                                            String ct = jsonObject1.getString("ct");
                                            Toast.makeText(Chat2Activity.this, ct, Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            //https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initChatUi() {
        //mBtnAudio
        final ChatUiHelper mUiHelper = ChatUiHelper.with(this);
        mUiHelper.bindContentLayout(chatactLin)
                .bindttToSendButton(chatSend)
                .bindEditText(chatEt)
                .bindBottomLayout(mRlBottomLayout)
                .bindEmojiLayout(mLlEmotion)
                .bindAddLayout(mLlAdd)
                .bindToAddButton(mIvAdd)
                .bindToEmojiButton(mIvEmo)
                .bindAudioBtn(mBtnAudio)
                .bindAudioIv(mIvAudio)
                .bindEmojiData();
        //底部布局弹出,聊天列表上滑
        chatactRel.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    chatactRel.post(new Runnable() {
                        @Override
                        public void run() {
                            if (chatAdapter.getItemCount() > 0) {
                                chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        //点击空白区域关闭键盘
        chatactRel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mUiHelper.hideBottomLayout(false);
                mUiHelper.hideSoftInput();
                chatEt.clearFocus();
                return false;
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    private void sel(String action, String s) {
        switch (action) {
            case "8":
                RedPackageBean reciverBean = new Gson().fromJson(s, RedPackageBean.class);
                messageBean = new MsgBean();
                messageBean.set_method_(reciverBean.get_method_());
                messageBean.setAction(reciverBean.getAction() + "");
                messageBean.setCt(reciverBean.getCt());
                messageBean.setAvatar(reciverBean.getAvatar());
                messageBean.setExtra(new Gson().toJson(reciverBean.getExtra()));
                messageBean.setMsgid(reciverBean.getMsgid());
                messageBean.setNickname(reciverBean.getNickname());
                messageBean.setShopid(reciverBean.getShopid());
                messageBean.setTimestamp(reciverBean.getTimestamp());
                messageBean.setToid(reciverBean.getToid());
                messageBean.setUid(reciverBean.getUid() + "");
                messageBean.setUserType(reciverBean.getUserType());
                messageBean.setLeft(true);
                Message message = Updata(messageBean);
                chatAdapter.addData(message);
             /*   chatAdapter.mList.add(messageBean);
                chatAdapter.notifyDataSetChanged();*/
                break;
            case "5":
                RemarkBean reciverBean1 = new Gson().fromJson(s, RemarkBean.class);
                messageBean = new MsgBean();
                messageBean.set_method_(reciverBean1.get_method_());
                messageBean.setAction(reciverBean1.getAction() + "");
                messageBean.setCt(reciverBean1.getCt());
                messageBean.setAvatar(reciverBean1.getAvatar());
                messageBean.setExtra(reciverBean1.getExtra().getStream());
                messageBean.setMsgid(reciverBean1.getMsgid());
                messageBean.setNickname(reciverBean1.getNickname());
                messageBean.setShopid(reciverBean1.getShopid());
                messageBean.setTimestamp(reciverBean1.getTimestamp());
                messageBean.setToid(reciverBean1.getToid());
                messageBean.setUid(reciverBean1.getUid() + "");
                messageBean.setUserType(reciverBean1.getUserType());
                messageBean.setLeft(true);

                chatAdapter.addData(Updata(messageBean));
            /*    chatAdapter.mList.add(messageBean);
                chatAdapter.notifyDataSetChanged();*/
                break;
            case "7":
                OfferBean reciverBean2 = new Gson().fromJson(s, OfferBean.class);
                OfferBean.ExtraBean extra = reciverBean2.getExtra();
                String s1 = new Gson().toJson(extra);
                Log.i("wxy", "sel: " + s1);
                messageBean = new MsgBean();
                messageBean.set_method_(reciverBean2.get_method_());
                messageBean.setAction(reciverBean2.getAction() + "");
                messageBean.setCt(reciverBean2.getCt());
                messageBean.setAvatar(reciverBean2.getAvatar());
                messageBean.setExtra(s1);
                messageBean.setMsgid(reciverBean2.getMsgid());
                messageBean.setNickname(reciverBean2.getNickname());
                messageBean.setShopid(reciverBean2.getShopid());
                messageBean.setTimestamp(reciverBean2.getTimestamp());
                messageBean.setToid(reciverBean2.getToid());
                messageBean.setUid(reciverBean2.getUid() + "");
                messageBean.setUserType(reciverBean2.getUserType());
                messageBean.setLeft(true);
                chatAdapter.addData(Updata(messageBean));
               /* chatAdapter.mList.add(messageBean);
                chatAdapter.notifyDataSetChanged();*/
                break;
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_chat;
    }


    public SpannableString getExpressionString(Context context, String text) {

        String rgFace = "\\[[^\\]]+\\]";
        Pattern ptFace = Pattern.compile(rgFace, Pattern.CASE_INSENSITIVE);
        return dealExpression(context, text, ptFace, 0);
    }

    private static SpannableString dealExpression(Context context,
                                                  String text, Pattern patten, int start) {
        SpannableString spannableString = new SpannableString(text);
        Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {

            String key = matcher.group();
            if (matcher.start() < start) {
                continue;
            }

            int a = 0;
            for (int i = 0; i < EmotionUtils.list.size(); i++) {
                if (key.equals(EmotionUtils.list.get(i))) {
                    a = i;
                    break;
                }
            }
            a += 1;

            Integer integer = EmotionUtils.emojiMap.get(a);
            // 通过上面匹配得到的字符串来生成图片资源id
            if (integer != null) {

                Drawable drawable = MyApp.getApp().getResources().getDrawable(integer);
                drawable.setBounds(0, 0, DensityUtil.dip2px(MyApp.getApp(), 19), DensityUtil.dip2px(MyApp.getApp(), 19));
                // 通过图片资源id来得到bitmap，用一个ImageSpan来包装*/
                ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
                // 计算该图片名字的长度，也就是要替换的字符串的长度
                int end = matcher.start() + key.length();
                // 将该图片替换字符串中规定的位置中
                spannableString.setSpan(imageSpan, matcher.start(), end,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }

    @OnClick({R.id.chatact_back, R.id.chat_send, R.id.chat_et, R.id.ivEmo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chatact_back:
                finish();
                break;
            case R.id.chat_send:
                //调用发送信息
                String nickname = (String) SPUtils.get(this, "username", "");
                String headimgurl = (String) SPUtils.get(this, "head", "");
                String s = chatEt.getText().toString();
                ShopMsg sendMsg = new ShopMsg(uerid, "", s, "");
                MsgBean msgBean = new MsgBean();
                msgBean.setUid(uerid);
                SpannableString expressionString = getExpressionString(this, s);
                String s1 = new String(String.valueOf(expressionString));
                msgBean.setCt(s1);
                msgBean.setSpannableString(expressionString);
                msgBean.setAvatar(headimgurl);
                msgBean.setAction("1");
                msgBean.setNickname(nickname);
                msgBean.setExtra("");
                msgBean.setLeft(false);

                chatAdapter.addData(Updata(msgBean));
                if (chatAdapter.getItemCount() > 0) {
                    chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                }
               /* chatAdapter.mList.add(msgBean);
                chatAdapter.notifyDataSetChanged();*/
                MyApp.sendShop(sendMsg);
                chatEt.setText("");
                break;
            case R.id.chat_et:

                break;
            case R.id.ivEmo:

                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) chatactLin2.getLayoutParams();
                layoutParams.height = heightDifference;
                chatactLin2.setLayoutParams(layoutParams);
                chatactLin2.setVisibility(View.VISIBLE);
                break;
        }
    }

    //得到url
    public static void getUrl(String path, String time) {
        //得到录音的Url ,发送
        Log.i("wxy", "onFinishedRecord: " + path);
        //发送消息
        String nickname = (String) SPUtils.get(MyApp.getApp(), "username", "");
        String headimgurl = (String) SPUtils.get(MyApp.getApp(), "head", "");
        MsgBean msgBean = new MsgBean();
        msgBean.setCt("[语音]");
        msgBean.setLeft(false);
        msgBean.setAvatar(headimgurl);
        msgBean.setNickname(nickname);
        long l = System.currentTimeMillis();
        msgBean.setTimestamp((int) (l / 1000));
        msgBean.setAction("9");
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("path", path);
            jsonObject.put("time", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        msgBean.setExtra(jsonObject.toString());
        msgBean.setUid(uerid);
        Message updata = Updata(msgBean);
        chatAdapter.addData(updata);
        ShopMsg2 sendMsg = new ShopMsg2(uerid, "", "[语音]", jsonObject);
        MyApp.sendShop2(sendMsg, "9");
        //发送消息


    }

    @Override
    public void messageSuccess(String success) {

//        chatAdapter.notifyDataSetChanged();
    }

    //点评成功
    @Override
    public void serviceCommentSuccess(String success) {

        Toast.makeText(this, "点评成功", Toast.LENGTH_SHORT).show();
        if (popupWindow1 != null) {
            popupWindow1.dismiss();
        }
        //评分成功 发送消息
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("score", "10");
            jsonObject.put("stream", extra);
        } catch (JSONException e) {


        }
        MsgBean msgBean = new MsgBean();
        msgBean.setMsgid("");
        msgBean.setUid(uerid);
        String nickname = (String) SPUtils.get(this, "username", "");
        String headimgurl = (String) SPUtils.get(this, "head", "");
        msgBean.setAvatar(headimgurl);
        msgBean.setNickname(nickname);
        msgBean.setExtra("");
        msgBean.setLeft(false);
        msgBean.setCt("已点评\n" + rate + "分");
        msgBean.setSpannableString(getExpressionString(this, "已点评\n" + rate + "分"));
        msgBean.setNickname("");
        msgBean.setAction("1");
        Updata(msgBean);

    /*    chatAdapter.mList.add(msgBean);
        chatAdapter.notifyDataSetChanged();*/
        ShopMsg2 shopMsg2 = new ShopMsg2(uerid, "", "已点评", jsonObject);
        MyApp.sendShop2(shopMsg2, "6");

    }

    @Override
    public void serviceMessageSucess(String success) {
        ServiceMessageBean serviceMessageBean = new Gson().fromJson(success, ServiceMessageBean.class);
        List<ServiceMessageBean.DataBean> data = serviceMessageBean.getData();
        ArrayList<MsgBean> msgBeans = new ArrayList<>();
        ArrayList<Message> messageBean = new ArrayList<>();
        //查询数据库
        if (data.size() > 0) {
            List<ShopMessage> list = DaoUtils.instance().shopMessageDao.queryBuilder().where(ShopMessageDao.Properties.Id.eq(data.get(0).getAid())).build().list();
            List<ShopMessage> list1 = DaoUtils.instance().shopMessageDao.queryBuilder().where(ShopMessageDao.Properties.Id.eq(data.get(data.size() - 1).getAid())).build().list();
            if (list.size() > 0) {
                //数据库有，刷新时查找..
                // 数据库
                isDataBase = true;
                timestamp = data.get(data.size() - 1).getTimestamp();
            } else {
                //沒有插入数据库

                if (list1.size() > 0) {
                    //判断插入几个
                    //数据库有
                    isDataBase = true;
                    ArrayList<ShopMessage> messageBeans = dataBase(data);
                    DaoUtils.instance().shopMessageDao.insertOrReplaceInTx(messageBeans);
                    timestamp = data.get(data.size() - 1).getTimestamp();

                } else {
                    //数据库没有，下一次刷新调用接口
                    isDataBase = false;
                    ArrayList<ShopMessage> messageBeans = dataBase(data);
                    //更新数据库  插入数据库
                    DaoUtils.instance().shopMessageDao.insertInTx(messageBeans);
                }
            }
        }

        for (int i = data.size() - 1; i >= 0; i--) {
            MsgBean msgBean = new MsgBean();
            ServiceMessageBean.DataBean dataBean = data.get(i);
            if (dataBean.getIs_me() == 1) {
                msgBean.setLeft(false);
            } else {
                msgBean.setLeft(true);
            }
            msgBean.setToid(dataBean.getUser_id() + "");
            msgBean.setUid(dataBean.getShop_id() + "");
            msgBean.setAvatar(dataBean.getAvatar());
            msgBean.setCt(dataBean.getContent());
            msgBean.setSpannableString(getExpressionString(this, dataBean.getContent()));
            msgBean.setExtra(dataBean.getExtra());
            msgBean.setAction(dataBean.getAction() + "");
            msgBean.setMsgid(dataBean.getMsgid());
            msgBean.setTimestamp(dataBean.getTimestamp());
            msgBeans.add(msgBean);
            messageBean.add(Updata(msgBean));

        }
        chatAdapter.addData(0, messageBean);
        chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
          /*  if (chatAdapter.getItemCount() > 0) {
                chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
            }*/
    /*    chatAdapter.mList.addAll(0,msgBeans);
        chatAdapter.notifyDataSetChanged();*/
    }

    private ArrayList<ShopMessage> dataBase(List<ServiceMessageBean.DataBean> data) {
        ArrayList<ShopMessage> messageBeans = new ArrayList<>();
        for (int i = data.size() - 1; i >= 0; i--) {
            ServiceMessageBean.DataBean dataBean = data.get(i);
            ShopMessage messageBean = new ShopMessage();
            messageBean.setExtra(new Gson().toJson(dataBean.getExtra()));
            messageBean.setAvatar(dataBean.getAvatar());
            messageBean.setAction(dataBean.getAction() + "");
            messageBean.setContent(dataBean.getContent());
            if (dataBean.getIs_me() == 0) {
                messageBean.setIsme(true);
            } else {
                messageBean.setIsme(false);
            }
//            String sel = sel(dataBean.getAction()+"","asa");
//            messageBean.setUserType(sel);
            messageBean.setMsgId(dataBean.getMsgid());
            messageBean.setRoom(uerid);
            messageBean.setNickName(dataBean.getNickname());
            messageBean.setShopID("0");
            messageBean.setId(dataBean.getAid());
            messageBean.setTime(dataBean.getTimestamp());
            messageBean.setToUserID(dataBean.getUser_id() + "");
            messageBean.setUserID(dataBean.getShop_id() + "");
            messageBean.setUserType("0");
            messageBeans.add(messageBean);
        }
        return messageBeans;
    }

    @Override
    public void voteSuccess(String success) {
        TransferBean transferBean = new Gson().fromJson(success, TransferBean.class);

        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("money", transferBean.getData().getMoney());
            jsonObject.put("order_no", transferBean.getData().getOrder_no());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MsgBean msgBean = new MsgBean();
        msgBean.setMsgid("");
        msgBean.setUid(uerid);
        String nickname = (String) SPUtils.get(this, "username", "");
        String headimgurl = (String) SPUtils.get(this, "head", "");
        msgBean.setAvatar(headimgurl);
        msgBean.setNickname(nickname);
        msgBean.setExtra(transferBean.getData().getMoney() + "," + transferBean.getData().getOrder_no());
        msgBean.setLeft(false);
        msgBean.setCt("赏你的");
        msgBean.setSpannableString(getExpressionString(this, "赏你的"));
        msgBean.setNickname("");
        msgBean.setAction("8");;
        chatAdapter.addData(0,Updata(msgBean));
       /* if (chatAdapter.getItemCount() > 0) {
            chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
        }*/
        /*chatAdapter.mList.add(msgBean);
        chatAdapter.notifyDataSetChanged();*/
        ShopMsg2 shopMsg2 = new ShopMsg2(uerid, "", "赏你了", jsonObject);
        //发送消息
        MyApp.sendShop2(shopMsg2, "8");
    }

    @Override
    public void delMessageSuccess(String success) {

    }

    @Override
    protected MessageListPresenter preparePresenter() {
        return new MessageListPresenter();
    }
}
