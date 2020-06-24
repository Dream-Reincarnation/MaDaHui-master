package com.ajiani.maidahui.activity.dynamic;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.adapter.dynamic.AddressAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.base.SimpleActivity;
import com.ajiani.maidahui.bean.dynamic.Address;
import com.ajiani.maidahui.bean.dynamic.AddressBean;
import com.ajiani.maidahui.http.DynamicServer;
import com.ajiani.maidahui.http.Globals;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.mInterface.dynamic.AddressIn;
import com.ajiani.maidahui.presenters.dynamic.Addpresenter;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity<AddressIn.AddressView, Addpresenter> implements TencentLocationListener, AddressIn.AddressView {

    @BindView(R.id.friedns_back)
    ImageView friednsBack;
    @BindView(R.id.friends_search)
    ImageView friendsSearch;
    @BindView(R.id.friedns_ed)
    EditText friednsEd;
    @BindView(R.id.friends_del)
    ImageView friendsDel;
    @BindView(R.id.address_rel)
    RecyclerView addressRel;
    @BindView(R.id.address_smart)
    SmartRefreshLayout addressSmart;
    @BindView(R.id.address_search_rel)
    RecyclerView addressSearchRel;
    private double longitude;
    private double latitude;
    private String city;
    private AddressAdapter addressAdapter;
    int page = 0;
    private HashMap<String, String> map;

    @Override
    protected void initData() {


        addressSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page += 1;
                if (map != null) {
                    map.put("page_index", page + "");
                }

                mPresenter.getData(map);
            }
        });

        friednsEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                page = 0;
                //改变字体  进行搜索
                map = new HashMap<>();

                map.put("key", "BE2BZ-I7NKJ-7VRFP-F2MJA-UVTM6-P3B47");
                //设置搜索的的城市
                map.put("region", city);
                //只搜索当前城市
                map.put("region_fix", "1");
                map.put("policy", "1");
                page += 1;
                map.put("page_index", page + "");
                map.put("page_size", "20");
                map.put("location", latitude + "," + longitude);
                //搜索的关键字
                map.put("keyword", friednsEd.getText().toString());
                mPresenter.getData(map);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (friednsEd.getText().toString().equals("") || friednsEd.getText().toString().length() <= 0) {
                    addressSmart.setVisibility(View.GONE);
                    friendsDel.setVisibility(View.GONE);
                    addressRel.setVisibility(View.VISIBLE);
                } else {
                    addressSmart.setVisibility(View.VISIBLE);
                    friendsDel.setVisibility(View.VISIBLE);
                    addressRel.setVisibility(View.GONE);
                }
            }
        });
       addressAdapter.setOnClickLinstener(new AddressAdapter.onClickLinstener() {
           @Override
           public void onClick(int posstion) {
               //点击返回发布页面，传值
               SPUtils.put(AddressActivity.this,"address",addressAdapter.city.get(posstion).toString());
               finish();
           }
       });

    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        addressRel.setLayoutManager(manager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        addressSearchRel.setLayoutManager(linearLayoutManager);
        //需要获取你的位置信息
        ArrayList<Address> addresses = new ArrayList<>();
        addressAdapter = new AddressAdapter(addresses);
        addressSearchRel.setAdapter(addressAdapter);

        TencentLocationRequest tencentLocationRequest = TencentLocationRequest.create();
        tencentLocationRequest.setInterval(30000).setRequestLevel(4).setAllowCache(true);
        TencentLocationManager locationManager = TencentLocationManager.getInstance(this);
        int error = locationManager.requestLocationUpdates(tencentLocationRequest, this);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_address;
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        //得到周边列表
        List<TencentPoi> poiList = tencentLocation.getPoiList();
        //得到经度，维度
        longitude = tencentLocation.getLongitude();
        latitude = tencentLocation.getLatitude();
        //得到在哪个城市
        city = tencentLocation.getCity();

        ArrayList<Address> address = new ArrayList<>();

//        Log.i("wxy", "onLocationChanged: "+poiList.toString());
        for (int j = 0; j < poiList.size(); j++) {
            TencentPoi tencentPoi = poiList.get(j);
            Address address1 = new Address(poiList.get(j).getName(), poiList.get(j).getAddress(), poiList.get(j).getUid(),tencentPoi.getLatitude()+"",tencentPoi.getLongitude()+"");
            address.add(address1);
            /* strings.add(poiList.get(j).getAddress());*/
        }
        AddressAdapter addressAdapter = new AddressAdapter(address);
        addressRel.setAdapter(addressAdapter);

        addressAdapter.setOnClickLinstener(new AddressAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                Log.i("wxy", "onClick: "+addressAdapter.city.get(posstion).toString());
                SPUtils.put(AddressActivity.this,"address",addressAdapter.city.get(posstion).toString());

                finish();

            }
        });

    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }


    @OnClick({R.id.friedns_back, R.id.friends_del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.friedns_back:
                finish();
                break;
            case R.id.friends_del:
                //搜索框清空
                friednsEd.setText("");
                addressSmart.setVisibility(View.GONE);
                friendsDel.setVisibility(View.GONE);
                addressRel.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void error(String error) {

    }

    @Override
    public void addressSuccess(String success) {
        addressSmart.setVisibility(View.VISIBLE);
        JSONObject jsonObject = JSONObject.parseObject(success);
        // 获取到key为shoppingCartItemList的值
        String status = jsonObject.getString("status");
        addressSmart.finishLoadMore();
        if (status.equals("0")) {

//            Log.i("wxy", "addressSuccess: " + status);
            AddressBean addressBean = new Gson().fromJson(success, AddressBean.class);
            List<AddressBean.DataBean> data = addressBean.getData();
            ArrayList<Address> addresses = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                AddressBean.DataBean dataBean = data.get(i);
                addresses.add(new Address(data.get(i).getTitle(), data.get(i).getAddress(), data.get(i).getId(),dataBean.getLocation().getLat()+"",dataBean.getLocation().getLng()+""));
            }

            if (page == 1) {
                addressAdapter.city.clear();
            }
            addressAdapter.city.addAll(addresses);
            addressAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void addressError(String error) {

    }

    @Override
    protected Addpresenter preparePresenter() {
        return new Addpresenter();
    }
}
