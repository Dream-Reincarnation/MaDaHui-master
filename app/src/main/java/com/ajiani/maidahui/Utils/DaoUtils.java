package com.ajiani.maidahui.Utils;

import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.dao.ChatList;
import com.ajiani.maidahui.dao.ChatListDao;
import com.ajiani.maidahui.dao.DaoMaster;
import com.ajiani.maidahui.dao.DaoSession;
import com.ajiani.maidahui.dao.MessageBean;

import com.ajiani.maidahui.dao.MessageBeanDao;
import com.ajiani.maidahui.dao.ShopMessageDao;
import com.ajiani.maidahui.http.HttpManager;

import java.util.ArrayList;
import java.util.List;

public class DaoUtils {
   public static DaoUtils daoUtils;
    private static DaoSession daoSession;
    public   ShopMessageDao shopMessageDao;
    public  MessageBeanDao messageBeanDao;
    public DaoMaster daoMaster;
    private final ChatListDao chatListDao;


    public static DaoUtils instance() {
        if (daoUtils == null) {
            synchronized (HttpManager.class) {
                if (daoUtils == null) {
                    daoUtils = new DaoUtils();
                }
            }
        }
        return daoUtils;
    }

    public DaoUtils() {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.getApp(), "agani.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());

        daoSession = daoMaster.newSession();
        chatListDao = daoSession.getChatListDao();
        messageBeanDao = daoSession.getMessageBeanDao();
        shopMessageDao = daoSession.getShopMessageDao();

    }
  //删除一条数据

    public void delData(ChatList chatList){
        chatListDao.delete(chatList);
    }

   //添加数据
    public void addData(ChatList chatList){
        chatListDao.insert(chatList);
    }
    //添加多条数据

    public void addMore(ArrayList<ChatList> chatLists){
        chatListDao.insertInTx(chatLists);
    }

    //更新一条数据

    public void upData(ChatList chatList){
        chatListDao.update(chatList);
    }
    //更新多条数据
    public void upDataMore(ArrayList<ChatList> chatLists){
        chatListDao.updateInTx(chatLists);
    }

    //查询
    public List<ChatList> selAll(){
        List<ChatList> list = chatListDao.queryBuilder().build().list();
        return list;
    }
   //条件查询

    public  List<ChatList>  sel(String sel){
        List<ChatList> list = chatListDao.queryBuilder().whereOr(ChatListDao.Properties.ShopId.eq(sel), ChatListDao.Properties.ShopId.eq(sel)).build().list();
        return list;
    }
    public  List<ChatList>  selUser(String sel){
        List<ChatList> list = chatListDao.queryBuilder().whereOr(ChatListDao.Properties.UserId.eq(sel), ChatListDao.Properties.UserId.eq(sel)).build().list();
        return list;
    }


    //增加数据 、
    /* public void addData(MessageBean messageBean){
        messageBeanDao.insert(messageBean);
    }
    //查询数据
    public List<MessageBean> selData(String id){
        List<MessageBean> list = messageBeanDao.queryBuilder().where(MessageBeanDao.Properties.Uid.eq(id)).build().list();
        return list;
    }

    //修改数据
    public void upData(MessageBean messageBean){
        messageBeanDao.update(messageBean);
    }
    //查询全部
    public  List<MessageBean> selAll(){
        List<MessageBean> messageBeans = messageBeanDao.loadAll();
        return messageBeans;
    }*/
}
