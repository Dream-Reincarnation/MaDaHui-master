package com.ajiani.maidahui.activity.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Base64;
import android.util.Log;
import android.util.Size;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
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
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
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
import com.ajiani.maidahui.Utils.web.FileUtil;
import com.ajiani.maidahui.activity.MyApp;

import com.ajiani.maidahui.activity.dynamic.TakePhotoActivity;
import com.ajiani.maidahui.activity.mine.BillInfoActivity;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.chat.TransferBean;
import com.ajiani.maidahui.bean.chat.UserMessageListBean;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.bean.sockets.NotificaBean;
import com.ajiani.maidahui.bean.sockets.OfferBean;
import com.ajiani.maidahui.bean.sockets.SendMsg;
import com.ajiani.maidahui.bean.sockets.ShopMsg2;
import com.ajiani.maidahui.chat.ChatAdapter;
import com.ajiani.maidahui.chat.bean.Message;
import com.ajiani.maidahui.chat.bean.MsgBody;
import com.ajiani.maidahui.chat.bean.MsgType;
import com.ajiani.maidahui.chat.bean.TextMsgBody;
import com.ajiani.maidahui.dao.ChatList;
import com.ajiani.maidahui.dao.ChatListDao;
import com.ajiani.maidahui.dao.MessageBean;
import com.ajiani.maidahui.dao.MessageBeanDao;
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
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.noober.menu.FloatMenu;
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

public class ChatActivity extends BaseActivity<UserMessageList.MessageListView, MessageListPresenter> implements EasyPermissions.PermissionCallbacks, UserMessageList.MessageListView {
    @BindView(R.id.chatact_rel)
    RecyclerView chatactRel;
    @BindView(R.id.chatact_lin)
    LinearLayout chatactLin;
    @BindView(R.id.chat_delmsg)
    LinearLayout chatDelMsg;
    @BindView(R.id.chat_et)
    EditText chatEt;
    @BindView(R.id.chat_smart)
    SmartRefreshLayout smart;
    public static boolean isShop;
    public static String mSenderId = "Right";
    @BindView(R.id.chat_send)
    StateButton chatSend;
    @BindView(R.id.chatact_back)
    FrameLayout chatBack;
    @BindView(R.id.chatact_title)
    TextView chatTitle;
    private static ChatAdapter chatAdapter;
    private MsgBean data;
    private static String uerid;
    String[] psermission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    @BindView(R.id.bottom_layout)
    RelativeLayout mRlBottomLayout;//表情,添加底部布局
    @BindView(R.id.rlVideo)
    RelativeLayout relativeLayout;//表情,添加底部布局
    @BindView(R.id.ivAdd)
    ImageView mIvAdd;
    @BindView(R.id.ivEmo)
    ImageView mIvEmo;
    @BindView(R.id.rlFile)
    RelativeLayout relativeLayoutPic;//图片按钮
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
    private String TAG = "wxy";
    //判断第一次请求，数据库是否存在
    public boolean isDataBase;
    private int timestamp;
    private int page = 1;
    private boolean isShow;

    public static void getUrl(String path, String time) {
        isShop = false;
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
        SendMsg sendMsg = new SendMsg(uerid, "", "[语音]", "9", jsonObject);
        sendMsg.setAction("9");
        MyApp.sendLetter(sendMsg);
    }

    @Override
    public void error(String error) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        isShop = false;
    }

    //图片上传成功
    public static void getPhotoPath(String url) {
        //图片上传成功       发送消息
//        Log.i("tag", "getPhotoPath: " + url);
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
        SendMsg sendMsg1 = new SendMsg(uerid, "", "[图片]", "4", jsonObject);
//        ShopMsg2 sendMsg = new ShopMsg2(uerid, "", "[图片]", jsonObject);
        MyApp.sendLetter(sendMsg1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> selectList;

        if (resultCode == RESULT_OK) {
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
                        isShop = true;
                        HttpUtils.instance().uoLoadImg(new File(androidQToPath));
                        File file = new File(androidQToPath);
                        Log.i("wxy", "onActivityResult: Q" + androidQToPath + "==" + file.length());
                    } else {
                        isShop = true;
                        String compressPath = selectList.get(0).getCompressPath();
                        //上传图片
                        HttpUtils.instance().uoLoadImg(new File(compressPath));
                        Log.i("wxy", "onActivityResult: " + compressPath);
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
                        isShop = true;
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
        textMsgBody.setSpannableString(messageBean.getSpannableString());
        message.setBody(textMsgBody);
        message.setMsgId(messageBean.getMsgid());
        message.setNickName(messageBean.getNickname());
        message.setSentTime(messageBean.getTimestamp());
        message.setShopID(messageBean.getShopid() + "");
        message.setToUserID(messageBean.getToid());
        message.setUserID(messageBean.getUid());
        message.setaId(messageBean.getaId());
        String user_id = (String) SPUtils.get(MyApp.getApp(), "userid", "");
        if (messageBean.isLeft()) {
            //是自己的
            message.setSenderId("Left");
        } else {
            message.setSenderId("Right");
        }
        message.setNickName(messageBean.getNickname());
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

    private boolean isCancelled(View view, MotionEvent event) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        if (event.getRawX() < location[0] || event.getRawX() > location[0] + view.getWidth() || event.getRawY() < location[1] - 40) {
            return true;
        }
        return false;
    }


    private Point point = new Point();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            point.x = (int) ev.getRawX();
            point.y = (int) ev.getRawY();
        }
        return super.dispatchTouchEvent(ev);
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {


        chatDelMsg.setVisibility(View.GONE);

        AudioRecordManager.getInstance(this).setMaxVoiceDuration(60);

        AudioRecordManager.getInstance(this).setAudioSavePath(FileUtils.getCache(this));

        chatAdapter.setOnClickLinstener(new ChatAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                FloatMenu floatMenu = null;
                FloatMenu float2Menu = null;
                long l = System.currentTimeMillis();
                long sentTime = chatAdapter.getData().get(posstion).getSentTime();
                Log.i(TAG, "onClick: " + chatAdapter.getData().get(posstion).getSentTime() + "====" + l);
                long a = (l / 1000) - sentTime;
                if (a > 120) {
                    floatMenu = new FloatMenu(ChatActivity.this);
                    floatMenu.items("删除", "多选");
                    floatMenu.show(point);
                } else {
                    float2Menu = new FloatMenu(ChatActivity.this);
                    float2Menu.items("撤回", "删除", "多选");
                    float2Menu.show(point);
                }
                if (floatMenu == null) {
                    float2Menu.setOnItemClickListener(new FloatMenu.OnItemClickListener() {
                        @Override
                        public void onClick(View v, int position) {
                            if (position == 0) {
                                List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Id.eq(chatAdapter.getData().get(position).getaId())).build().list();
                                if (list != null) {
                                    DaoUtils.instance().messageBeanDao.deleteInTx(list);
                                }

                                HashMap<String, String> hashMap = new HashMap<>();

                                hashMap.put("msgid", chatAdapter.getData().get(posstion).getMsgId());
                                hashMap.put("type", "repeal");
                                org.json.JSONObject jsonObject = new org.json.JSONObject();
                                //发送消息，撤回消息
                                try {
                                    jsonObject.put("uid", uerid);
                                    jsonObject.put("msgid", chatAdapter.getData().get(posstion).getMsgId());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                ;
                                MyApp.socket.emit("delmsg", jsonObject);
                                chatAdapter.getData().remove(posstion);
                                mPresenter.getDelData(hashMap);
                                chatAdapter.notifyDataSetChanged();
                            } else if (position == 1) {
                                List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Id.eq(chatAdapter.getData().get(position).getaId())).build().list();
                                if (list != null) {
                                    DaoUtils.instance().messageBeanDao.deleteInTx(list);
                                }
                                //删除单个
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("msgid", chatAdapter.getData().get(posstion).getMsgId());
                                hashMap.put("type", "delete");
                                chatAdapter.getData().remove(posstion);
                                mPresenter.getDelData(hashMap);
                                chatAdapter.notifyDataSetChanged();

                            } else if (position == 2) {
                                isShow = true;
                                chatAdapter.setShow(true);
                                chatDelMsg.setVisibility(View.VISIBLE);
                                chatAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                } else {
                    floatMenu.setOnItemClickListener(new FloatMenu.OnItemClickListener() {
                        @Override
                        public void onClick(View v, int position) {
                            if (position == 0) {
                                List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Id.eq(chatAdapter.getData().get(position).getaId())).build().list();
                                if (list != null) {
                                    DaoUtils.instance().messageBeanDao.deleteInTx(list);
                                }
                                //删除单个
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("msgid", chatAdapter.getData().get(posstion).getMsgId());
                                hashMap.put("type", "delete");
                                chatAdapter.getData().remove(posstion);
                                mPresenter.getDelData(hashMap);
                                chatAdapter.notifyDataSetChanged();
                            } else if (position == 1) {
                                isShow = true;
                                chatAdapter.setShow(true);
                                chatDelMsg.setVisibility(View.VISIBLE);
                                chatAdapter.notifyDataSetChanged();
                            }
                        }
                    });

                }
//                Log.i(TAG, "onItemLongClick: asdas" + posstion);
            }
        });

        mBtnAudio.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //申请权限  判断是否有录音权限
                        if (EasyPermissions.hasPermissions(ChatActivity.this, psermission)) {
                            //打开相机和录像
                            AudioRecordManager.getInstance(ChatActivity.this).startRecord();
                        } else {
                            //去申请
                            EasyPermissions.requestPermissions(ChatActivity.this, "需要获取你的录音权限", 1001, psermission);
                        }

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isCancelled(v, event)) {
                            AudioRecordManager.getInstance(ChatActivity.this).willCancelRecord();
                        } else {
                            AudioRecordManager.getInstance(ChatActivity.this).continueRecord();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        AudioRecordManager.getInstance(ChatActivity.this).stopRecord();
                        AudioRecordManager.getInstance(ChatActivity.this).destroyRecord();
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
                View view = View.inflate(ChatActivity.this, R.layout.popup_audio_wi_vo, null);
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
                    isShop = true;
                    HttpUtils.instance().upLoad(audioPath.getPath());
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
        MyApp.isShowPop = true;
        initChatUi();
        boolean isFirst = (boolean) SPUtils.get(this, "isFirst", false);
        if (isFirst) {

        } else {

        }

        relativeLayoutPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureUtils.startPhoto(ChatActivity.this);
            }

        });
        relativeLayoutphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureUtils.startCarame(ChatActivity.this);
            }
        });
  /*      chatAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Log.i(TAG, "onItemLongClick: asdasdasdasdad");
                isShow = true;
                chatAdapter.setShow(true);
                chatDelMsg.setVisibility(View.VISIBLE);
                chatAdapter.notifyDataSetChanged();
                return true;
            }
        });*/

        chatAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (isShow) {
                    //改变他的状态
                    boolean show = chatAdapter.getData().get(position).isShow();
                    if (show) {
                        chatAdapter.getData().get(position).setShow(false);
                        chatAdapter.notifyDataSetChanged();
                    } else {
                        chatAdapter.getData().get(position).setShow(true);
                        chatAdapter.notifyDataSetChanged();
                    }

                } else {
                    List<Message> data = chatAdapter.getData();
                    Message message = data.get(position);
                    MsgType msgType = message.getMsgType();
                    if (MsgType.AUDIO == msgType) {

                        TextMsgBody body = (TextMsgBody) message.getBody();
                        String extra = body.getExtra();
                        JSONObject jsonObject = JSON.parseObject(extra);
                        //上传录音，播放录音
                        MediaManager.playSound(ChatActivity.this, jsonObject.getString("path"), new MediaPlayer.OnCompletionListener() {
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
                        JumpUtils.gotoActivity(ChatActivity.this, ShopInfoActivity.class, bundle);
                    } else if (msgType == MsgType.Transfer) {
                        TextMsgBody body = (TextMsgBody) message.getBody();
                        String extra = body.getExtra();
                        JSONObject jsonObject = JSON.parseObject(extra);
                        Bundle bundle = new Bundle();
                        bundle.putString("order_no", jsonObject.getString("order_no"));
                        JumpUtils.gotoActivity(ChatActivity.this, BillInfoActivity.class, bundle);
                    } else if (msgType == MsgType.IMAGE) {
                        //点击图片 得到ImageView
                        TextMsgBody textMsgBody = (TextMsgBody) message.getBody();
                        String extra = textMsgBody.getExtra();
                        JSONObject jsonObject = JSON.parseObject(extra);
                        String path = jsonObject.getString("path");
                        Bundle bundle = new Bundle();
                        bundle.putString("img", path);
                        JumpUtils.gotoActivity(ChatActivity.this, ChatImageActivity.class, bundle);
                    }
                }


            }
        });
        chatactRel.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("to_user_id", uerid);
        mPresenter.getMessageList(hashMap);
        smart.finishLoadMore();
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                Toast.makeText(ChatActivity.this, "刷新页面", Toast.LENGTH_SHORT).show();
                if (isDataBase) {
                    page++;
                    List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Room.eq(uerid), MessageBeanDao.Properties.Time.le(timestamp)).orderDesc(MessageBeanDao.Properties.Time).offset(page).limit(20).list();
                    ArrayList<Message> messages = new ArrayList<>();
                    if (list.size() > 0) {
                        if (list.size() == 1) {
                            timestamp = (int) list.get(list.size() - 1).getTime();
                        } else {
                            timestamp = (int) list.get(list.size() - 1).getTime();
                        }
                    }


                    for (int i = list.size() - 1; i >= 0; i--) {
                        MessageBean messageBean = list.get(i);
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
                        SpannableString expressionString = getExpressionString(ChatActivity.this, messageBean.getContent());
                        msgBean.setSpannableString(expressionString);
                        msgBean.setAction(messageBean.getAction());
                        msgBean.setExtra(messageBean.getExtra());
                        msgBean.setMsgid(messageBean.getMsgId());
                        msgBean.setNickname(messageBean.getNickName());
                        msgBean.setTimestamp((int) messageBean.getTime());
                        Message updata = Updata(msgBean);
                        messages.add(updata);
                    }
                    chatAdapter.addData(0, messages);
                    smart.finishRefresh(true);
                } else {
                    //数据库没有

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("to_user_id", uerid);
                    hashMap.put("msgid", chatAdapter.getData().get(0).getMsgId());
                    mPresenter.getMessageList(hashMap);
                    smart.finishRefresh(true);
                }


            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                Toast.makeText(ChatActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
                smart.finishLoadMore(true);
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == ContextMenu.FIRST + 1) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            //删除
            Log.i(TAG, "onContextItemSelected: position=" + menuInfo.position);
            /* cancelFavorite(menuInfo.position);*/
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void initView() {
//        registerForContextMenu(chatactRel);


        relativeLayout.setVisibility(View.GONE);
        smart.setEnableLoadMore(false);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String name = bundle.getString("name");
        uerid = bundle.getString("uid");
        chatTitle.setText(name);
        ArrayList<Message> msgBeans = new ArrayList<>();
        chatactRel.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this, msgBeans);
        chatactRel.setAdapter(chatAdapter);

        //监听接受消息
        MyApp.socket.on("broadcastingListen", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                for (int i = 0; i < 1; i++) {
                    JSONArray jsonArray = (JSONArray) args[0];
                    int finalI = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String s = (String) jsonArray.get(0);

                                JSONObject jsonObject = JSON.parseObject(s);
                                String code = jsonObject.getString("_method_");
                                String toid = jsonObject.getString("uid");
                                String userid = (String) SPUtils.get(ChatActivity.this, "userid", "");
//                                Log.i("wxy", "run: shopid " + toid + "---" + userid);
                                switch (code) {
                                    case "SendMsg":

                                        JSONObject jsonObject1 = JSON.parseObject(s);
                                        String shopid = jsonObject1.getString("shopid");
                                        //shopid 大于0 是 客服  ，小于0 是私信
                                        if (Integer.parseInt(shopid) > 0) {

                                        } else {
                                            MsgBean reciverBean = new Gson().fromJson(s, MsgBean.class);
                                            NotificaBean notificaBean = new NotificaBean();
                                            reciverBean.setLeft(true);
                                            String ct = jsonObject.getString("ct");
                                            SpannableString spannableString = new SpannableString(ct);
                                            reciverBean.setSpannableString(spannableString);
                                            Message updata = Updata(reciverBean);
                                            chatAdapter.getData().add(updata);
                                            chatAdapter.notifyDataSetChanged();
                                        }
                                        break;
                                    case "DelMsg":
                                        String msgid = jsonObject.getString("msgid");
                                        //刪除消息
                                        List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.MsgId.eq(msgid)).build().list();
                                        if (list != null) {
                                            DaoUtils.instance().messageBeanDao.deleteInTx(list);
                                        }
                                        List<Message> data = chatAdapter.getData();
                                        for (int i = 0; i < data.size(); i++) {
                                            Message message = data.get(i);
                                            if (message.getMsgId().equals(msgid)) {
                                                chatAdapter.getData().remove(i);
                                                break;
                                            }
                                        }
                                        chatAdapter.notifyDataSetChanged();
                                        break;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    });


                }
            }
            //https://ajiani.oss-cn-beijing.aliyuncs.com/uploads/image/ee/67370de558834d022370c397612937.jpg
        });

    }

    public static SpannableString getExpressionString(Context context, String text) {

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
            Log.e("FaceUtil", "mact" + key);
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
                mIvEmo.setImageResource(R.mipmap.tab_ico_voice);
                return false;
            }
        });
        //

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_chat;
    }


    @OnClick({R.id.chatact_back, R.id.chat_send, R.id.chat_et, R.id.chat_delmsg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chat_delmsg:
                //删除消息
                List<Message> data = chatAdapter.getData();
                ArrayList<Message> messages = new ArrayList<>();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).isShow()) {
                        messages.add(data.get(i));
                        stringBuilder.append(data.get(i).getMsgId() + ",");
                    }
                }
                for (int i = 0; i < messages.size(); i++) {
                    //删除列表
                    //删除数据库
                    List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Id.eq(messages.get(0).getaId())).build().list();
                    if (list != null) {
                        DaoUtils.instance().messageBeanDao.deleteInTx(list);
                    }

                    chatAdapter.getData().remove(messages.get(i));
                }
                String s1 = stringBuilder.toString();
                String substring = s1.substring(0, s1.length() - 1);
                Log.i(TAG, "onViewClicked: " + substring);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("msgid", substring);
                hashMap.put("type", "delete");
                mPresenter.getDelData(hashMap);
                chatAdapter.notifyDataSetChanged();
                chatDelMsg.setVisibility(View.GONE);
                break;

            case R.id.chatact_back:
                MyApp.isShowPop = false;
                finish();
                break;
            case R.id.chat_send:
                //调用发送信息
                String nickname = (String) SPUtils.get(this, "username", "");
                String headimgurl = (String) SPUtils.get(this, "head", "");
                org.json.JSONObject jsonObject = new org.json.JSONObject();
                String s = chatEt.getText().toString();
                SendMsg sendMsg = new SendMsg(uerid, "", s, "1", jsonObject);
                sendMsg.setAction("1");
                MsgBean msgBean = new MsgBean();
                msgBean.setUid(uerid);
                msgBean.setCt(s);
                msgBean.setAction("1");
                SpannableString expressionString = getExpressionString(this, s);
                msgBean.setSpannableString(expressionString);
                msgBean.setAvatar(headimgurl);
                msgBean.setNickname(nickname);
                msgBean.setLeft(false);
                long l = System.currentTimeMillis();
                msgBean.setTimestamp((int) (l / 1000));
                Message updata = Updata(msgBean);
                chatAdapter.addData(updata);
           /*     chatAdapter.mList.add(msgBean);
                chatAdapter.notifyDataSetChanged();*/
                String userid = (String) SPUtils.get(MyApp.getApp(), "userid", "");
                if (chatAdapter.getItemCount() > 0) {
                    chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
                }

                MyApp.sendLetter(sendMsg);
                chatEt.setText("");
                break;
            case R.id.chat_et:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void messageSuccess(String success) {
        UserMessageListBean userMessageListBean = new Gson().fromJson(success, UserMessageListBean.class);
        List<UserMessageListBean.DataBean> data = userMessageListBean.getData();
        //判断数据库是否有，有查找数据库 没有插入数据库

        if (data.size() > 0) {
            //  List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().build().list();
            List<MessageBean> list = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Id.eq(data.get(0).getAid())).build().list();
            List<MessageBean> list1 = DaoUtils.instance().messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Id.eq(data.get(data.size() - 1).getAid())).build().list();

            if (list.size() > 0) {
                //数据库有，刷新时查找..
                // 数据库
                isDataBase = true;
                timestamp = data.get(data.size() - 1).getTimestamp();
            } else {

                //数据库没有，插入数据库

                if (list1.size() > 0) {
                    //判断插入几个
                    //数据库有
                    isDataBase = true;
                    ArrayList<MessageBean> messageBeans = dataBase(data);
                    DaoUtils.instance().messageBeanDao.insertOrReplaceInTx(messageBeans);
                    timestamp = data.get(data.size() - 1).getTimestamp();

                } else {
                    //数据库没有，下一次刷新调用接口
                    isDataBase = false;
                    ArrayList<MessageBean> messageBeans = dataBase(data);
                    //更新数据库  插入数据库
                    DaoUtils.instance().messageBeanDao.insertInTx(messageBeans);
                }
            }

        }

        ArrayList<MsgBean> msgBeans = new ArrayList<>();
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = data.size() - 1; i >= 0; i--) {
            MsgBean msgBean = new MsgBean();
            UserMessageListBean.DataBean dataBean = data.get(i);
            if (dataBean.getIs_me() == 1) {
                msgBean.setLeft(false);
            } else {
                msgBean.setLeft(true);
            }
            msgBean.setToid(dataBean.getTo_user_id() + "");
            msgBean.setUid(dataBean.getForm_user_id() + "");
            msgBean.setAvatar(dataBean.getAvatar());
            msgBean.setCt(dataBean.getContent());
            SpannableString expressionString = getExpressionString(this, dataBean.getContent());
            msgBean.setSpannableString(expressionString);
            msgBean.setAction(dataBean.getAction());
            msgBean.setExtra(dataBean.getExtra());
            msgBean.setMsgid(dataBean.getMsgid());
            msgBean.setaId(dataBean.getAid());
            msgBean.setNickname(dataBean.getNickname());
            msgBean.setTimestamp(dataBean.getTimestamp());
            msgBeans.add(msgBean);

            Message updata = Updata(msgBean);
            messages.add(updata);

        }
        chatAdapter.addData(0, messages);
        chatactRel.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
    }

    private ArrayList<MessageBean> dataBase(List<UserMessageListBean.DataBean> data) {
        ArrayList<MessageBean> messageBeans = new ArrayList<>();
        for (int i = data.size() - 1; i >= 0; i--) {
            UserMessageListBean.DataBean dataBean = data.get(i);
            MessageBean messageBean = new MessageBean();
            messageBean.setExtra(new Gson().toJson(dataBean.getExtra()));
            messageBean.setAvatar(dataBean.getAvatar());
            messageBean.setAction(dataBean.getAction());
            messageBean.setContent(dataBean.getContent());
            if (dataBean.getIs_me() == 0) {
                messageBean.setIsme(true);
            } else {
                messageBean.setIsme(false);
            }
            String sel = sel(dataBean.getAction());
            messageBean.setUserType(sel);
            messageBean.setMsgId(dataBean.getMsgid());
            messageBean.setRoom(uerid);
            messageBean.setNickName(dataBean.getNickname());
            messageBean.setShopID("0");
            messageBean.setId(dataBean.getAid());
            messageBean.setTime(dataBean.getTimestamp());
            messageBean.setToUserID(dataBean.getTo_user_id() + "");
            messageBean.setUserID(dataBean.getForm_user_id() + "");
            messageBean.setUserType("0");
            messageBeans.add(messageBean);
        }
        return messageBeans;
    }

    public String sel(String action) {
        String text = "";
        switch (action) {
            case "1":
                text = "text";
                break;
            case "4":
                text = "image";
                break;
            case "9":
                text = "audio";
                break;
        }
        return text;
    }

    @Override
    public void serviceCommentSuccess(String success) {

    }

    @Override
    public void serviceMessageSucess(String success) {

    }

    @Override
    public void voteSuccess(String success) {


    }

    @Override
    public void delMessageSuccess(String success) {

    }

    @Override
    public void onBackPressed() {

        if (isShow) {
            chatAdapter.setShow(false);
            isShow = false;
            for (int i = 0; i < chatAdapter.getData().size(); i++) {
                chatAdapter.getData().get(i).setShow(false);
            }

            chatDelMsg.setVisibility(View.GONE);
            chatAdapter.notifyDataSetChanged();

        } else {
            super.onBackPressed();
            MyApp.isShowPop = false;
        }

    }

    @Override
    protected MessageListPresenter preparePresenter() {
        return new MessageListPresenter();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //授权了
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //无权限，且被选择"不再提醒"：提醒客户到APP应用设置中打开权限
        if (!ActivityCompat.shouldShowRequestPermissionRationale(ChatActivity.this, Manifest.permission.CAMERA)) {
            Toast.makeText(ChatActivity.this, "拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限", Toast.LENGTH_SHORT).show();
        }
        //无权限，只是单纯被拒绝
        else {
            Toast.makeText(ChatActivity.this, "拒绝权限，等待下次询问", Toast.LENGTH_SHORT).show();
        }
    }
}
