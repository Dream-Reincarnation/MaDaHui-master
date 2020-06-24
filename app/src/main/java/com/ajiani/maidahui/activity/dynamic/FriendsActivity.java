package com.ajiani.maidahui.activity.dynamic;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.dynamic.FriendsAdapter;
import com.ajiani.maidahui.adapter.dynamic.links.ContactAdapter;
import com.ajiani.maidahui.adapter.dynamic.links.StickyHeaderDecoration;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.Contact;
import com.ajiani.maidahui.bean.dynamic.FollowListBean;
import com.ajiani.maidahui.bean.dynamic.FriendsListBean;
import com.ajiani.maidahui.cn.CNPinyin;
import com.ajiani.maidahui.cn.CNPinyinFactory;
import com.ajiani.maidahui.mInterface.dynamic.FollowListIn;
import com.ajiani.maidahui.mInterface.dynamic.FriendListIn;
import com.ajiani.maidahui.presenters.FollowListPresenter;
import com.ajiani.maidahui.presenters.dynamic.FriendsPresenter;
import com.ajiani.maidahui.weight.link.CharIndexView;
import com.google.gson.Gson;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FriendsActivity extends BaseActivity<FriendListIn.friendsListView, FriendsPresenter> implements FriendListIn.friendsListView, TencentLocationListener {
    @BindView(R.id.friedns_back)
    ImageView friednsBack;
    @BindView(R.id.friends_del)
    ImageView friednsDel;
    @BindView(R.id.friends_search)
    ImageView friendsSearch;
    @BindView(R.id.friedns_ed)
    EditText friednsEd;
    @BindView(R.id.friends_rel)
    RecyclerView friendsRel;
    @BindView(R.id.friends_searchrel)
    RecyclerView friendsearchRel;
    @BindView(R.id.friends_main)
    CharIndexView friendsMain;
    @BindView(R.id.friends_index)
    TextView friendsIndex;
    private ArrayList<CNPinyin<Contact>> contactList = new ArrayList<>();
    private ContactAdapter mContactAdapter;
    private Subscription subscription;
    private ArrayList<Contact> mContacts;
    private static char[] CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '#'};


    int page;
    int searchPage;
    boolean isSearch;
    private ArrayList<FriendsListBean.DataBean> dataBeans;
    private FriendsAdapter friendsAdapter;
    private String TAG = "wxy";

    @Override
    protected void initData() {





        HashMap<String, String> hashMap = new HashMap<>();
        page+=1;
        hashMap.put("page", page + "");
        mPresenter.getData(hashMap);


       /* for (int i = 0; i < CHARS.length; i++) {
            for (int y = 0; y < 10; y++) {
                mContacts.add(new Contact(CHARS[i] + "名字" + y, "http://thirdwx.qlogo.cn/mmopen/vi_32/IgpNh2icImnWsvicXLEL9qbqn8mEolxBAy5qKSLrIeVxFlrIpIAEXW5y0WSotNcG8kicSHBDWGCPUDEibRhh2oTc1Q/132", "100022"));
            }
        }
        getPinyinList();*/

        //  mPresenter.followListData(hashMap);

        friednsDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friednsEd.setText("");
            }
        });
    }


    @Override
    protected void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        friendsRel.setLayoutManager(manager);
        ArrayList<Integer> integers = new ArrayList<>();
        mContactAdapter = new ContactAdapter(contactList,integers);
        friendsRel.setAdapter(mContactAdapter);
        //搜索显示的页面
        ArrayList<FriendsListBean.DataBean> dataBeans = new ArrayList<>();
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        friendsearchRel.setLayoutManager(manager2);
        friendsAdapter = new FriendsAdapter(dataBeans);
        friendsearchRel.setAdapter(friendsAdapter);

        friendsMain.setOnCharIndexChangedListener(new CharIndexView.OnCharIndexChangedListener() {
            @Override
            public void onCharIndexChanged(char currentIndex) {
                for (int i = 0; i < contactList.size(); i++) {
                    if (contactList.get(i).getFirstChar() == currentIndex) {
                        manager.scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }

            @Override
            public void onCharIndexSelected(String currentIndex) {
                if (currentIndex == null) {
                    friendsIndex.setVisibility(View.INVISIBLE);
                } else {
                    friendsIndex.setVisibility(View.VISIBLE);
                    friendsIndex.setText(currentIndex);
                }
            }
        });
        mContacts = new ArrayList<>();
        mContactAdapter = new ContactAdapter(contactList);

        friednsEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //改变的时候进行网络请求  ，覆盖原来的图层

                friendsearchRel.setVisibility(View.VISIBLE);
                friendsAdapter.mList.clear();
                isSearch = true;
                HashMap<String, String> map = new HashMap<>();
                map.put("page", searchPage++ + "");
                map.put("keyword", friednsEd.getText().toString());
                mPresenter.getData(map);

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (friednsEd.getText().toString().equals("") | friednsEd.getText().toString().length() == 0) {
                    searchPage=0;
                    friendsearchRel.setVisibility(View.GONE);
                    friendsRel.setVisibility(View.VISIBLE);
                    friendsMain.setVisibility(View.VISIBLE);
                    friednsDel.setVisibility(View.GONE);
                } else {
                    friendsRel.setVisibility(View.GONE);
                    friendsMain.setVisibility(View.GONE);
                    friendsearchRel.setVisibility(View.VISIBLE);
                    friednsDel.setVisibility(View.VISIBLE);

                }
            }
        });

        friendsRel.setAdapter(mContactAdapter);
        friendsRel.addItemDecoration(new StickyHeaderDecoration(mContactAdapter));
        mContactAdapter.setOnClickLinsten(new ContactAdapter.onClickLinsten() {
            @Override
            public void onClick(int posstion) {
                Contact contact = mContacts.get(posstion);
                finish();
                EventBus.getDefault().postSticky(contact);
            }
        });

    }

    private void getPinyinList() {
        subscription = Observable.create(new Observable.OnSubscribe<List<CNPinyin<Contact>>>() {
            @Override
            public void call(Subscriber<? super List<CNPinyin<Contact>>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    List<CNPinyin<Contact>> contactList = CNPinyinFactory.createCNPinyinList(mContacts);
                    Collections.sort(contactList);
                    subscriber.onNext(contactList);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CNPinyin<Contact>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<CNPinyin<Contact>> cnPinyins) {
                        contactList.addAll(cnPinyins);

                        mContactAdapter.notifyDataSetChanged();
                        ArrayList<String> integers = new ArrayList<>();
                        for (int i = 0; i <contactList.size(); i++) {
                            Log.i(TAG, "onNext: "+mContactAdapter.getHeaderId(i));
                            integers.add(contactList.get(i).getFirstChar()+"");
                        }
                        //去重
                        removeDuplicateWithOrder(integers);
                        char[] chars=new char[integers.size()];
                        for (int i = 0; i < integers.size(); i++) {
                            chars[i]=integers.get(i).toCharArray()[0];
                        }
                        friendsMain.setChar(chars);
                    }
                });


    }

    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_friends;
    }


    @Override
    protected FriendsPresenter preparePresenter() {
        return new FriendsPresenter();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

   /* @Override
    public void followListSuccess(String success) {
        Toast.makeText(this, "获取成功", Toast.LENGTH_SHORT).show();

    }*/


    @Override
    public void error(String error) {

    }


    @OnClick({R.id.friedns_back, R.id.friends_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.friedns_back:
                finish();
                break;
            case R.id.friends_search:
                break;
        }
    }

    @Override
    public void friendsListSuccess(String success) {
        Log.i("wxy", "friendsListSuccess: " + success);
        FriendsListBean friendsListBean = new Gson().fromJson(success, FriendsListBean.class);
        List<FriendsListBean.DataBean> data = friendsListBean.getData();
        if (isSearch) {

            if (data != null && data.size() > 0) {
                friendsAdapter.mList.addAll(data);
               /* HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page", searchPage+++ "");
                mPresenter.getData(hashMap);*/
                friendsAdapter.notifyDataSetChanged();
            } else {
                friendsAdapter.notifyDataSetChanged();
            }


        } else {
            if (data.size() != 0 && data.size() > 0 && data != null) {
                for (int i = 0; i < data.size(); i++) {
                    FriendsListBean.DataBean dataBean = data.get(i);
                    mContacts.add(new Contact(dataBean.getNickname(), dataBean.getHeadimgurl(), dataBean.getId() + ""));
                }
                HashMap<String, String> hashMap = new HashMap<>();
                page+=1;
                hashMap.put("page", page+ "");
                mPresenter.getData(hashMap);
            } else {
                getPinyinList();


            }
        }


    }

    @Override
    public void friendsListError(String error) {

    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {

    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }
}
