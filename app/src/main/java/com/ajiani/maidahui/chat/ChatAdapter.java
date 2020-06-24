package com.ajiani.maidahui.chat;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.PopupMenu;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.chat.GlideUtils;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.activity.chat.ChatActivity;
import com.ajiani.maidahui.adapter.MyViewHolder;
import com.ajiani.maidahui.bean.dynamic.PersonVideoBean;
import com.ajiani.maidahui.bean.sockets.OfferBean;
import com.ajiani.maidahui.bean.sockets.ShopInfo;
import com.ajiani.maidahui.chat.bean.AudioMsgBody;
import com.ajiani.maidahui.chat.bean.FileMsgBody;
import com.ajiani.maidahui.chat.bean.ImageMsgBody;
import com.ajiani.maidahui.chat.bean.Message;
import com.ajiani.maidahui.chat.bean.MsgBody;
import com.ajiani.maidahui.chat.bean.MsgSendStatus;
import com.ajiani.maidahui.chat.bean.MsgType;
import com.ajiani.maidahui.chat.bean.TextMsgBody;
import com.ajiani.maidahui.chat.bean.VideoMsgBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.google.gson.Gson;


import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

public class ChatAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {


    private static final int TYPE_SEND_TEXT = 1;
    private static final int TYPE_RECEIVE_TEXT = 2;
    private static final int TYPE_SEND_IMAGE = 3;
    private static final int TYPE_RECEIVE_IMAGE = 4;
    private static final int TYPE_SEND_Transfer = 5;
    private static final int TYPE_RECEIVE_Transfer = 6;
    private static final int TYPE_SEND_Offer = 7;
    private static final int TYPE_RECEIVE_Offer = 8;
    private static final int TYPE_SEND_AUDIO = 9;
    private static final int TYPE_RECEIVE_AUDIO = 10;
    private static final int TYPE_SEND_SHOP = 11;
    private static final int TYPE_RECEIVE_COMMENT = 12;


    private static final int SEND_TEXT = R.layout.item_text_send;
    private static final int RECEIVE_TEXT = R.layout.item_text_receive;
    private static final int SEND_IMAGE = R.layout.item_image_send;
    private static final int RECEIVE_IMAGE = R.layout.item_image_receive;
    private static final int SEND_Transfer = R.layout.item_transfer_send;
    private static final int RECEIVE_Transfer = R.layout.item_transfer_receive;
    private static final int SEND_Offer = R.layout.item_offer_send;
    private static final int RECEIVE_Offer = R.layout.item_offer_receive;
    private static final int RECEIVE_AUDIO = R.layout.item_audio_receive;
    private static final int SEND_AUDIO = R.layout.item_audio_send;
    private static final int SEND_Shop = R.layout.item_shop_send;
    //点评
    private static final int RECEIVE_COMMENT = R.layout.item_comment_receive;
    public boolean isShow;
    private onClickLinstener onClick;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    /*
    private static final int SEND_LOCATION = R.layout.item_location_send;
    private static final int RECEIVE_LOCATION = R.layout.item_location_receive;*/


    public ChatAdapter(Context context, List<Message> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<Message>() {
            @Override
            protected int getItemType(Message entity) {
                boolean isSend = entity.getSenderId().equals(Chat2Activity.mSenderId);
                if (MsgType.TEXT == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_TEXT : TYPE_RECEIVE_TEXT;
                } else if (MsgType.IMAGE == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_IMAGE : TYPE_RECEIVE_IMAGE;
                } else if (MsgType.Transfer == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_Transfer : TYPE_RECEIVE_Transfer;
                } else if (MsgType.Offer == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_Offer : TYPE_RECEIVE_Offer;
                } else if (MsgType.AUDIO == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_AUDIO : TYPE_RECEIVE_AUDIO;
                } else if (MsgType.Shop == entity.getMsgType()) {
                    return isSend ? TYPE_SEND_SHOP : TYPE_RECEIVE_AUDIO;
                } else if (MsgType.COMMNET == entity.getMsgType()) {
                    return isSend ? TYPE_RECEIVE_COMMENT : TYPE_RECEIVE_COMMENT;
                }

                return 0;
            }
        });
        getMultiTypeDelegate().registerItemType(TYPE_SEND_TEXT, SEND_TEXT)
                .registerItemType(TYPE_RECEIVE_TEXT, RECEIVE_TEXT)
                .registerItemType(TYPE_SEND_IMAGE, SEND_IMAGE)
                .registerItemType(TYPE_RECEIVE_IMAGE, RECEIVE_IMAGE)
                .registerItemType(TYPE_SEND_Transfer, SEND_Transfer)
                .registerItemType(TYPE_RECEIVE_Transfer, RECEIVE_Transfer)
                .registerItemType(TYPE_SEND_Offer, SEND_Offer)
                .registerItemType(TYPE_RECEIVE_Offer, RECEIVE_Offer)
                .registerItemType(TYPE_SEND_AUDIO, SEND_AUDIO)
                .registerItemType(TYPE_RECEIVE_AUDIO, RECEIVE_AUDIO)
                .registerItemType(TYPE_SEND_SHOP, SEND_Shop)
                .registerItemType(TYPE_RECEIVE_COMMENT, RECEIVE_COMMENT);
    }

    /* @Override
     protected void convert(BaseViewHolder helper, Message item) {


     }
 */
    //是已发送 ，进度条的显示
    private void setStatus(BaseViewHolder helper, Message item) {
        MsgBody msgContent = item.getBody();
        if (msgContent instanceof TextMsgBody
                || msgContent instanceof AudioMsgBody || msgContent instanceof VideoMsgBody || msgContent instanceof FileMsgBody) {
            //只需要设置自己发送的状态
            MsgSendStatus sentStatus = item.getSentStatus();
            boolean isSend = item.getSenderId().equals(ChatActivity.mSenderId);
            if (isSend) {
                if (sentStatus == MsgSendStatus.SENDING) {
                    helper.setVisible(R.id.chat_item_progress, true).setVisible(R.id.chat_item_fail, false);
                } else if (sentStatus == MsgSendStatus.FAILED) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, true);
                } else if (sentStatus == MsgSendStatus.SENT) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, false);
                }
            }
        } else if (msgContent instanceof ImageMsgBody) {
            boolean isSend = item.getSenderId().equals(ChatActivity.mSenderId);
            if (isSend) {
                MsgSendStatus sentStatus = item.getSentStatus();
                if (sentStatus == MsgSendStatus.SENDING) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, false);
                } else if (sentStatus == MsgSendStatus.FAILED) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, true);
                } else if (sentStatus == MsgSendStatus.SENT) {
                    helper.setVisible(R.id.chat_item_progress, false).setVisible(R.id.chat_item_fail, false);
                }
            } else {
            }
        }
    }

    private void showPopupMenu(Context context, View view) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(context, view);
        // 获取布局文件
        popupMenu.getMenuInflater().inflate(R.menu.chat_item, popupMenu.getMenu());
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 控件每一个item的点击事件
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                // 控件消失时的事件
            }
        });
        switch (view.getId()) {
            case R.id.del:
                popupMenu.getMenu().findItem(R.id.del).setVisible(false);
                break;
            default:
                break;
        }
        popupMenu.show();
    }

    //设置内容
    private void setContent(BaseViewHolder helper, Message item) {
        Glide.with(mContext).load(item.getAvatar()).apply(new RequestOptions().circleCrop()).into((ImageView) helper.getView(R.id.chat_item_header));
        if (isShow) {
            helper.getView(R.id.chat_fram_check).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.chat_fram_check).setVisibility(View.GONE);
        }
        if (item.isShow()) {
            ImageView view = helper.getView(R.id.chat_img);
            view.setImageResource(R.mipmap.alerdysel);
        } else {
            ImageView view = helper.getView(R.id.chat_img);
            view.setImageResource(R.mipmap.unsel);
        }
        Log.i("wxy", "setContent: " + item.getNickName());

        if (item.getMsgType().equals(MsgType.TEXT)) {
            TextMsgBody msgBody = (TextMsgBody) item.getBody();

            helper.setText(R.id.chat_receiver_name, item.getNickName());
            helper.setText(R.id.chat_item_content_text, msgBody.getSpannableString());
            helper.getView(R.id.chat_item_layout_content).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onClick != null) {
                        onClick.onClick(helper.getPosition());
                    }
                    return true;
                }
            });
        } else if (item.getMsgType().equals(MsgType.IMAGE)) {
            helper.setText(R.id.chat_receiver_name, item.getNickName());
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
            GlideUtils.loadChatImage(mContext, getJSon(msgBody.getExtra(), "path"), (ImageView) helper.getView(R.id.bivPic));
            helper.getView(R.id.bivPic).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onClick != null) {

                        onClick.onClick(helper.getPosition());
                    }
                    return true;
                }
            });
            //转账
        } else if (item.getMsgType().equals(MsgType.Transfer)) {
            helper.setText(R.id.chat_receiver_name, item.getNickName());
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
            //进行赋值 转账金额和转账原因
            helper.setText(R.id.red_moneyleft, getJSon(msgBody.getExtra(), "money"));
            helper.setText(R.id.red_titleleft, msgBody.getMessage());
            //定制商品付款
            helper.getView(R.id.red_titleleft).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onClick != null) {
                        onClick.onClick(helper.getPosition());
                    }
                    return true;
                }
            });
        } else if (item.getMsgType().equals(MsgType.Offer)) {
            helper.setText(R.id.chat_receiver_name, item.getNickName());
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
            //图片
            OfferBean.ExtraBean extraBean = new Gson().fromJson(msgBody.getExtra(), OfferBean.ExtraBean.class);
            helper.setText(R.id.shops_title, extraBean.getTitle());
            Glide.with(mContext).load(extraBean.getThumb()).into((ImageView) helper.getView(R.id.chatitem_shopimg));
//                    GlideUtils.loadChatImage(mContext,extraBean.getThumb(),helper.getView(R.id.chatitem_shopimg));
            helper.setText(R.id.shops_money, extraBean.getPrice());
            helper.getView(R.id.chat_item_layout_content).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onClick != null) {
                        onClick.onClick(helper.getPosition());
                    }
                    return true;
                }
            });
        } else if (item.getMsgType().equals(MsgType.AUDIO)) {
            helper.setText(R.id.chat_receiver_name, item.getNickName());
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
            if (getJSon(msgBody.getExtra(), "time") != null) {
                helper.setText(R.id.tvDuration, (int) Math.ceil(Float.parseFloat(getJSon(msgBody.getExtra(), "time"))) + "");
            }
            helper.getView(R.id.rlAudio).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onClick != null) {
                        onClick.onClick(helper.getPosition());
                    }
                    return true;
                }
            });
        } else if (item.getMsgType().equals(MsgType.Shop)) {
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
            ShopInfo shopInfo = new Gson().fromJson(msgBody.getExtra(), ShopInfo.class);
            Glide.with(mContext).load(shopInfo.getThumb()).into((ImageView) helper.getView(R.id.shopimg));
//                    GlideUtils.loadChatImage(mContext,shopInfo.getShopthumb(),helper.getView(R.id.shopimg));
            helper.setText(R.id.shoptitle, shopInfo.getTitle());
            helper.setText(R.id.shopprice, shopInfo.getGoods_price());
            helper.getView(R.id.chat_item_layout_content).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onClick != null) {
                        onClick.onClick(helper.getPosition());
                    }
                    return true;
                }
            });
            //点评
        } else if (item.getMsgType() == MsgType.COMMNET) {
            helper.setText(R.id.chat_receiver_name, item.getNickName());
            TextMsgBody msgBody = (TextMsgBody) item.getBody();
        }
    }

    public interface onClickLinstener {
        void onClick(int posstion);
    }

    public void setOnClickLinstener(onClickLinstener onClickLinstener) {
        this.onClick = onClickLinstener;
    }

    private void setOnClick(BaseViewHolder helper, Message item) {
        MsgBody msgContent = item.getBody();
        MsgType msgType = item.getMsgType();
        if (MsgType.AUDIO == msgType) {
            helper.addOnClickListener(R.id.rlAudio);
//            helper.addOnLongClickListener(R.id.rlAudio);
        } else if (msgType == MsgType.Offer) {
            helper.addOnClickListener(R.id.shops_tx);
//            helper.addOnLongClickListener(R.id.shops_tx);
        } else if (msgType == MsgType.Transfer) {
            helper.addOnClickListener(R.id.chat_item_layout_content);
//            helper.addOnLongClickListener(R.id.chat_item_layout_content);
        } else if (msgType == MsgType.COMMNET) {
            helper.addOnClickListener(R.id.shop_comment);
//            helper.addOnLongClickListener(R.id.shop_comment);
        } else if (msgType == MsgType.IMAGE) {
            helper.addOnClickListener(R.id.bivPic);
//            helper.addOnLongClickListener(R.id.bivPic);
        } else if (msgType == MsgType.TEXT) {
            helper.addOnClickListener(R.id.chat_item_layout_content);
//            helper.addOnLongClickListener(R.id.chat_item_layout_content);
        }
    }

    //拿到json中某一字段的值
    public String getJSon(String json, String type) {

        JSONObject jsonObject = JSON.parseObject(json);
        String string = jsonObject.getString(type);
        if (string != null) {
            return string;
        } else {
            return "";
        }


    }


    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        setContent(helper, item);
        //  setStatus(helper, item);
        setOnClick(helper, item);
    }
}



