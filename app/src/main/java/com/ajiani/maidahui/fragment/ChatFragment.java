package com.ajiani.maidahui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DaoUtils;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.activity.chat.Chat2Activity;
import com.ajiani.maidahui.activity.chat.ChatActivity;
import com.ajiani.maidahui.activity.chat.ChatCommentActivity;
import com.ajiani.maidahui.activity.chat.ChatStarActivity;
import com.ajiani.maidahui.activity.chat.FansActivity;
import com.ajiani.maidahui.activity.chat.InteractActivity;
import com.ajiani.maidahui.activity.chat.LogisticsActivity;
import com.ajiani.maidahui.activity.chat.NewsAssistant;
import com.ajiani.maidahui.activity.login.LoginActivity;
import com.ajiani.maidahui.adapter.mine.ChatAdapter2;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.chat.MessageListBean;
import com.ajiani.maidahui.bean.chat.ServiceBean;
import com.ajiani.maidahui.bean.chat.SystemNumBean;
import com.ajiani.maidahui.bean.sockets.MsgBean;
import com.ajiani.maidahui.dao.ChatList;
import com.ajiani.maidahui.mInterface.chat.MessageList;
import com.ajiani.maidahui.presenters.chat.MessagePresenter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatFragment extends BaseFragment<MessageList.MessageView, MessagePresenter> implements MessageList.MessageView {

    @BindView(R.id.sendChat)
    TextView sendChat;
    @BindView(R.id.chat_lins)
    LinearLayout chatLin;
    /*@BindView(R.id.logistics_smart)
    SmartRefreshLayout smart;*/
    @BindView(R.id.chat_logistics)
    LinearLayout chatLogistics;
    @BindView(R.id.chat_fans)
    LinearLayout chatFans;
    @BindView(R.id.chat_star)
    LinearLayout chatStar;
    @BindView(R.id.chat_comment)
    LinearLayout chatComment;
    @BindView(R.id.chat_interact)
    LinearLayout chatInteract;
    @BindView(R.id.chat_top)
    TextView chatTop;
    @BindView(R.id.chat_nomsg)
    TextView chatNomsg;
    @BindView(R.id.chat_notifation)
    LinearLayout chatNotifation;
    @BindView(R.id.chat_rel)
    RecyclerView chatRel;
    private ItemTouchHelper mItemTouchHelper;
    private int page = 1;
    private ChatAdapter2 chatAdapter;
    int nored;
    private String TAG = "wxy";
    private MessageListBean.DataBean dataBean;
    private HashMap<String, String> hashMap;
    private String token;
    //是否进行网络请求
   boolean isPost;

    @Override
    protected void initData() {
//    smart.setRefreshHeader(new ClassicsHeader(getActivity()));
        HomeActivity.context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int statusBarHeight = StatusBarUtil2.getStatusBarHeight(getActivity());
        chatLin.setPadding(0,statusBarHeight,0,0);
        boolean dao = (boolean) SPUtils.get(getActivity(), "dao", false);
        if (true) {

            SPUtils.put(getActivity(), "dao", false);
        } else {
            //查数据库
            Toast.makeText(mActivity, "走的是数据库", Toast.LENGTH_SHORT).show();
        }
        if (EventBus.getDefault().isRegistered(this)) {

        } else {
            EventBus.getDefault().register(this);
        }


        chatAdapter.setOnLongClickLinstener(new ChatAdapter2.onLongClickLinstener() {
            @Override
            public void onLongClick(int posstion) {

                PopupWindow popupWindow = new PopupWindow(getActivity());
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.del_item, null, false);
                inflate.findViewById(R.id.del_lin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.del_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int user_id = chatAdapter.mList.get(posstion).getUser_id();
                        List<ChatList> sel = DaoUtils.instance().sel(user_id + "");
                        if (Integer.parseInt(sel.get(posstion).getShopId()) > 0) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("shop_id", chatAdapter.mList.get(posstion).getUser_id() + "");
                            mPresenter.getData(hashMap);
                        } else {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("to_user_id", chatAdapter.mList.get(posstion).getUser_id() + "");
                            mPresenter.getDelMessage(hashMap);
                        }


                        MessageListBean.DataBean dataBean = chatAdapter.mList.get(posstion);

                        List<ChatList> chatLists = DaoUtils.instance().selUser(dataBean.getUser_id() + "");
                        Log.i(TAG, "onClick: " + chatLists.size());
                        if (chatLists.size() == 0) {
                            chatLists = DaoUtils.instance().sel(dataBean.getUser_id() + "");
                        }
                        Log.i(TAG, "onClick: " + chatLists.size());
                        //删除数据库
                        if (chatLists != null && chatLists.size() > 0) {
                            DaoUtils.instance().delData(chatLists.get(0));
                        }

                        chatAdapter.mList.remove(posstion);
                        chatAdapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.setContentView(inflate);
                popupWindow.showAtLocation(chatRel, Gravity.BOTTOM, 0, 0);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(MsgBean msgBean) {
        String avatar = msgBean.getAvatar();
        String ct = msgBean.getCt();
        String nickname = msgBean.getNickname();
        String uid = msgBean.getUid();
        if (chatAdapter.mList.size() == 0) {
            MessageListBean.DataBean dataBean = new MessageListBean.DataBean();
            dataBean.setUser_id(Integer.parseInt(uid));
            dataBean.setContent(ct);
            dataBean.setHeadimgurl(avatar);
            dataBean.setNickname(nickname);
            dataBean.setNoread(1);
            chatAdapter.mList.add(dataBean);
            chatAdapter.notifyDataSetChanged();
        } else {
            for (int i = 0; i < chatAdapter.mList.size(); i++) {
                if (Integer.parseInt(uid) == chatAdapter.mList.get(i).getUser_id()) {
                    //添加消息
                    MessageListBean.DataBean dataBean = chatAdapter.mList.get(i);
                    int noread = dataBean.getNoread();
                    noread++;
                    dataBean.setUser_id(Integer.parseInt(uid));
                    dataBean.setContent(ct);
                    dataBean.setHeadimgurl(avatar);
                    dataBean.setNickname(nickname);
                    dataBean.setNoread(noread);
                    chatAdapter.mList.set(i, dataBean);
                    chatAdapter.notifyDataSetChanged();
                    break;
                } else {
                    if (i == chatAdapter.mList.size() - 1) {
                        //
                        //就添加 代表没有相同的
                        //     Log.i("wxy", "getMessage: "+uid);
                        MessageListBean.DataBean dataBean = new MessageListBean.DataBean();
                        dataBean.setUser_id(Integer.parseInt(uid));
                        dataBean.setContent(ct);
                        dataBean.setHeadimgurl(avatar);
                        dataBean.setNickname(nickname);
                        dataBean.setNoread(1);
                        chatAdapter.mList.add(dataBean);
                        chatAdapter.notifyDataSetChanged();
                        break;
                    }
                }


            }
        }
    }

   /*@Override
    public boolean onContextItemSelected(MenuItem item) {
        if (getUserVisibleHint()) {
            int position;
            position =chatAdapter.getPosstion();
            switch (item.getItemId()) {
                case 0:
                    Toast.makeText(getContext(), item.getTitle() + "" + position, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(getContext(), item.getTitle()+""+ position, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(), item.getTitle()+""+ position, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        return super.onContextItemSelected(item);
    }*/


    @Override

    protected void initView() {
        //    this.registerForContextMenu(chatRel);
        isPost=false;
        ArrayList<MessageListBean.DataBean> messageLists = new ArrayList<>();

        chatAdapter = new ChatAdapter2(messageLists);
        chatRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatRel.setAdapter(chatAdapter);
       /* chatRel.addItemDecoration(new ItemTouchHelper(this,
                RecyclerViewListDecoration.VERTICAL_LIST));*/
        chatAdapter.setOnClickLinstener(new ChatAdapter2.onClickLinstener() {
            @Override
            public void onClick(int posstion) {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("to_user_id", chatAdapter.mList.get(posstion).getUser_id() + "");
                mPresenter.getDelMessage(hashMap);
                MessageListBean.DataBean dataBean = chatAdapter.mList.get(posstion);

                List<ChatList> chatLists = DaoUtils.instance().selUser(dataBean.getUser_id() + "");
                if (chatLists.size() == 0) {
                    chatLists = DaoUtils.instance().sel(dataBean.getUser_id() + "");
                }
                //产出数据库
                if (chatLists != null && chatLists.size() > 0) {
                    DaoUtils.instance().delData(chatLists.get(0));
                }

                chatAdapter.mList.remove(posstion);
                chatAdapter.notifyDataSetChanged();


            }
        });
        chatAdapter.setOnItemClickLinstener(new ChatAdapter2.onItemClickLinstener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(int posstion) {

                int user_id = chatAdapter.mList.get(posstion).getUser_id();
                if (user_id < 10000) {
                    //跳转到客服聊天
                    Bundle bundle = new Bundle();
                    bundle.putString("extra", "");
                    bundle.putString("avart", chatAdapter.mList.get(posstion).getHeadimgurl());
                    bundle.putString("name", chatAdapter.mList.get(posstion).getNickname());
                    bundle.putString("time", chatAdapter.mList.get(posstion).getTimestamp());
                    bundle.putString("uid", chatAdapter.mList.get(posstion).getUser_id() + "");
                    JumpUtils.gotoActivity(getActivity(), Chat2Activity.class, bundle);
                } else {
                    //跳转到聊天页面
                    Bundle bundle = new Bundle();
                    bundle.putString("name", chatAdapter.mList.get(posstion).getNickname());
                    bundle.putString("uid", chatAdapter.mList.get(posstion).getUser_id() + "");
                    JumpUtils.gotoActivity(getActivity(), ChatActivity.class, bundle);
                }

            }
        });
        /*HashMap<String, String> hashMap = new HashMap<>();
        mPresenter.getSystemList(hashMap);*/
    }

    @Override
    public void isToken() {
        super.isToken();

    }

    @Override
    public void onResume() {
        super.onResume();
        token = (String) SPUtils.get(getActivity(), "token", "");
        if (token.length() > 7&&!isPost) {
            hashMap = new HashMap<>();
            mPresenter.getSystemList(hashMap);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("page", page + "");
            mPresenter.getUserList(hashMap);
            mPresenter.getServiceList(hashMap);
            isPost=true;
        } else {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData(false);
        if (getUserVisibleHint()) {
            if (HomeActivity.context != null) {
                StatusBarUtil2.setStatusBarMode(HomeActivity.context, true, R.color.white);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData(false);
    }


    protected boolean isViewInitiated; //控件是否初始化完成
    protected boolean isVisibleToUser; //页面是否可见
    protected boolean isDataInitiated; //数据是否加载

    protected void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            StatusBarUtil2.setStatusBarMode(HomeActivity.context, true, R.color.white);
            isDataInitiated = true;
        }
    }

    @Override
    protected MessagePresenter preparePresenter() {
        return new MessagePresenter();
    }


    @Override
    protected int createLayout() {
        return R.layout.fragment_chat;
    }


    @OnClick({R.id.sendChat, R.id.chat_logistics, R.id.chat_fans, R.id.chat_star, R.id.chat_comment, R.id.chat_interact, R.id.chat_notifation})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("posstion", 3);
        switch (view.getId()) {
            case R.id.sendChat:
                break;
            case R.id.chat_logistics:
                if (token.length() < 7) {
                    JumpUtils.gotoActivity(getActivity(), LoginActivity.class, bundle, 11);
                    return;
                }
                //物流、
                JumpUtils.gotoActivity(getActivity(), LogisticsActivity.class);
                break;
            case R.id.chat_fans:
                if (token.length() < 7) {
                    JumpUtils.gotoActivity(getActivity(), LoginActivity.class, bundle, 11);
                    return;
                }
                JumpUtils.gotoActivity(getActivity(), FansActivity.class);
                break;
            case R.id.chat_star:
                if (token.length() < 7) {
                    JumpUtils.gotoActivity(getActivity(), LoginActivity.class, bundle, 11);
                    return;
                }
                //跳转
                JumpUtils.gotoActivity(getActivity(), ChatStarActivity.class);
                break;
            case R.id.chat_comment:
                if (token.length() < 7) {
                    JumpUtils.gotoActivity(getActivity(), LoginActivity.class, bundle, 11);
                    return;
                }
                //跳转评论
            /*    SendMsg sendMsg = new SendMsg("100015", "", "hello,你好呀", "");
              //  ShopMsg shopMsg = new ShopMsg("5", "", "hello,你好呀,衣服多少钱", "");
                MyApp.sendLetter(sendMsg);*/
                JumpUtils.gotoActivity(getActivity(), ChatCommentActivity.class);
                break;
            case R.id.chat_interact:
                if (token.length() < 7) {
                    JumpUtils.gotoActivity(getActivity(), LoginActivity.class, bundle, 11);
                    return;
                }
                //广播
                JumpUtils.gotoActivity(getActivity(), InteractActivity.class);
                break;
            case R.id.chat_notifation:
                if (token.length() < 7) {
                    JumpUtils.gotoActivity(getActivity(), LoginActivity.class, bundle, 11);
                    return;
                }
                //消息助手
                JumpUtils.gotoActivity(getActivity(), NewsAssistant.class);
                break;
        }
    }

    @Override
    public void UserListSuccess(String sucess) {
        MessageListBean messageList = new Gson().fromJson(sucess, MessageListBean.class);
        //插入收据库 列表
        List<MessageListBean.DataBean> data = messageList.getData();
        if (data != null) {
            chatAdapter.mList.addAll(data);
        }


        for (int i = 0; i < data.size(); i++) {
            int noread = data.get(i).getNoread();
            nored += noread;
        }
        if (nored > 0) {
            HomeActivity homeActivity = (HomeActivity) getActivity();
        } else {

        }
//        List<ChatList> chatLists1 = DaoUtils.instance().selAll();
        //插入数据库
        ArrayList<ChatList> chatLists = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            MessageListBean.DataBean dataBean = data.get(i);
            ChatList chatList = new ChatList(null, "0", dataBean.getUser_id() + "", dataBean.getContent(), dataBean.getHeadimgurl(), dataBean.getTimestamp(), false, dataBean.getNickname(), dataBean.getNoread() + "",
                    dataBean.getFormat_create_time());
            chatLists.add(chatList);
        }
        boolean ishasdao = (boolean) SPUtils.get(getActivity(), "ishas", false);
        //判斷第一次
        if (ishasdao) {
            for (int i = 0; i < chatLists.size(); i++) {
                String id = chatLists.get(i).getUserId();
                ChatList chatList = chatLists.get(i);

                List<ChatList> sel = DaoUtils.instance().sel(id);
                //判断列表是否存在
                if (sel != null & sel.size() > 0) {
                    chatList.setId(sel.get(0).getId());
                    DaoUtils.instance().upData(chatList);
                } else {
                    //不存在
                    DaoUtils.instance().addData(chatList);
                }

            }
        } else {
            DaoUtils.instance().addMore(chatLists);
            SPUtils.put(getActivity(), "ishas", true);
        }


        //   DaoUtils.instance().addMore(chatLists);


        if (chatAdapter.mList.size() > 0) {
            Collections.sort(chatAdapter.mList, sort());
            if (dataBean != null && chatAdapter.mList.size() > 0) {
                if (chatAdapter.mList.get(0).getUser_id() == 00) {

                } else {
                    chatAdapter.mList.add(0, dataBean);
                }

            }
        }

        chatAdapter.notifyDataSetChanged();
    }

    @Override
    public void serviceListSuccess(String success) {
        if (success != null && success.length() > 3) {
            Log.i("WXY", "serviceListSuccess: " + success);
            ServiceBean serviceBean = new Gson().fromJson(success, ServiceBean.class);
            List<ServiceBean.DataBean> data = serviceBean.getData();
            for (int i = 0; i < data.size(); i++) {
                MessageListBean.DataBean dataBean = new MessageListBean.DataBean();
                dataBean.setNoread(Integer.parseInt(data.get(i).getNoread2()));
                dataBean.setNickname(data.get(i).getName());
                dataBean.setHeadimgurl(data.get(i).getThumb());
                dataBean.setContent(data.get(i).getContent());
                dataBean.setUser_id(data.get(i).getShop_id());
                dataBean.setFormat_create_time(data.get(i).getFormat_create_time());
                if (data.get(i).getTimestamp().equals("")) {
                    dataBean.setTimestamp(0 + "");
                } else {
                    dataBean.setTimestamp(data.get(i).getTimestamp());
                }
                chatAdapter.mList.add(dataBean);

            }
            //插入数据库
            ArrayList<ChatList> chatLists = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                ServiceBean.DataBean dataBean = data.get(i);
                ChatList chatList = new ChatList(null, dataBean.getShop_id() + "", "0", dataBean.getThumb(), dataBean.getContent(), dataBean.getTimestamp(), false, dataBean.getName(), dataBean.getNoread2(),
                        dataBean.getFormat_create_time());
                chatLists.add(chatList);
            }
            boolean ishasdao = (boolean) SPUtils.get(getActivity(), "ishas1", false);
            if (ishasdao) {
                for (int i = 0; i < chatLists.size(); i++) {
                    String id = chatLists.get(i).getShopId();
                    ChatList chatList = chatLists.get(i);

                    List<ChatList> sel = DaoUtils.instance().sel(id);

                    if (sel != null & sel.size() > 0) {
                        chatList.setId(sel.get(0).getId());
                        DaoUtils.instance().upData(chatList);
                    } else {
                        DaoUtils.instance().addData(chatList);
                    }

                }
            } else {
                DaoUtils.instance().addMore(chatLists);
                SPUtils.put(getActivity(), "ishas1", true);
            }
            //  DaoUtils.instance().addMore(chatLists);

            if (chatAdapter.mList.size() > 0) {
                Collections.sort(chatAdapter.mList, sort());
                if (dataBean != null) {

                    if (chatAdapter.mList.get(0).getUser_id() == 00) {

                    } else {
                        chatAdapter.mList.add(0, dataBean);
                    }
                }
            }
            chatAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void systemSuccess(String success) {
        Log.i(TAG, "systemSuccess: " + success);

        SystemNumBean systemNumBean = new Gson().fromJson(success, SystemNumBean.class);

        if (systemNumBean.getData().getLast_message() == null || systemNumBean.getData().getLast_message().equals("")) {

        } else {
            dataBean = new MessageListBean.DataBean();
            dataBean.setUser_id(00);
            dataBean.setFormat_create_time(systemNumBean.getData().getLast_message_time());
            dataBean.setContent(systemNumBean.getData().getLast_message());
            dataBean.setHeadimgurl("asd");
            dataBean.setNoread(systemNumBean.getData().getMessage());
            dataBean.setTimestamp(systemNumBean.getTimestamp() + "");
            dataBean.setNickname("佳妮猫小助手");
            /*chatAdapter.mList.add(dataBean);
            chatAdapter.notifyDataSetChanged();*/
        }

    }

    @Override
    public void delMessageSuccess(String success) {

    }

    @Override
    public void delShopMessageSuccess(String success) {

    }

    public Comparator sort() {
        //排序规则，这里是以年龄先排序，如果年龄相同
        Comparator<MessageListBean.DataBean> comparator = new Comparator<MessageListBean.DataBean>() {
            public int compare(MessageListBean.DataBean s1, MessageListBean.DataBean s2) {

                // 时间 -》名字
                if (s2.getTimestamp() != null) {
                    if (s1.getTimestamp().equals("") || s2.getTimestamp().equals("")) {
                        return 0;
                    } else {
                        if (Integer.parseInt(s1.getTimestamp()) != Integer.parseInt(s2.getTimestamp())) {
                            return Integer.parseInt(s2.getTimestamp()) - Integer.parseInt(s1.getTimestamp());
                        } else {
                            return s1.getNickname().compareTo(s2.getNickname());
                        }
                    }
                }
                return 0;

            }


        };
        return comparator;

    }

    @Override
    public void error(String error) {

    }
}