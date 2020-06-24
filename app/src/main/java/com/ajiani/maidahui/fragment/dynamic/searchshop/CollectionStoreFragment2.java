package com.ajiani.maidahui.fragment.dynamic.searchshop;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.AddShopActivity;
import com.ajiani.maidahui.activity.dynamic.SearchShopActivity;
import com.ajiani.maidahui.adapter.dynamic.AddAdapter;
import com.ajiani.maidahui.adapter.dynamic.StoreAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.dynamic.CompleteBean;
import com.ajiani.maidahui.bean.dynamic.StoreBean;
import com.ajiani.maidahui.mInterface.dynamic.AddshopIn;
import com.ajiani.maidahui.presenters.dynamic.AddShopPresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class CollectionStoreFragment2 extends BaseFragment<AddshopIn.AddshopView, AddShopPresenter> implements AddshopIn.AddshopView {
    @BindView(R.id.addshop_rel)
    RecyclerView addshopRel;
    @BindView(R.id.addshop_rel2)
    RecyclerView addshopRel2;
    @BindView(R.id.addshop_samrt)
    SmartRefreshLayout addshopSamrt;
    @BindView(R.id.addshop_samrt2)
    SmartRefreshLayout addshopSamrt2;

    int page;
    int storePage;
    int searchPage;
    boolean isSearch;
    private StoreAdapter storeAdapter;
    private StoreAdapter storeAdapter2;
    private SearchShopActivity activity;
    private RecyclerView popRel;
    private AddAdapter addAdapter;
    private SmartRefreshLayout smart;

    public String keyWord;

    public CollectionStoreFragment2(String keyWord) {
        this.keyWord = keyWord;
    }
    @Override
    public void error(String error) {


    }

    @Override
    protected void initData() {
        activity = (SearchShopActivity) getActivity();
        addshopRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<StoreBean.DataBean> dataBeans2 = new ArrayList<>();
        storeAdapter = new StoreAdapter(dataBeans2);
        addshopRel.setAdapter(storeAdapter);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword",keyWord);
        page += 1;
        hashMap.put("page", page + "");
        mPresenter.getCollectionStore(hashMap);

        storeAdapter.setOnClickLinstener(new StoreAdapter.onClickLinstener() {


            @Override
            public void onClick(int posstion) {
                showPop(posstion,storeAdapter);
            }
        });


        activity.friendsDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.friednsEd.setText("");
                activity.friednsEd.setHint("试试搜 商品/店铺");
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(activity.friednsEd.getWindowToken(), 0);

            }
        });
        addshopSamrt.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });





    }

    private void showPop(int posstion,StoreAdapter storeAdapter) {
        storePage = 0;
        //弹窗 店铺详情
        PopupWindow popupWindow = new PopupWindow(getActivity());
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.store_pop, null, false);
        popupWindow.setContentView(inflate);
        popupWindow.setAnimationStyle(R.style.PopupWindowStoreStyle);
        inflate.findViewById(R.id.store_pop_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popRel = inflate.findViewById(R.id.store_pop_rel);
        TextView storeName = inflate.findViewById(R.id.store_pop_name);
        smart = inflate.findViewById(R.id.store_pop_smart);
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                storePage += 1;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("shop_id", storeAdapter.mList.get(posstion).getAid() + "");
                storePage += 1;
                hashMap.put("page", storePage + "");
                mPresenter.getShopList(hashMap);
            }
        });

        storeName.setText(storeAdapter.mList.get(posstion).getName());
        popRel.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ArrayList<CompleteBean.DataBean> dataBeans1 = new ArrayList<>();
        addAdapter = new AddAdapter(dataBeans1);
        popRel.setAdapter(addAdapter);
        TextView addShop = inflate.findViewById(R.id.addshop_shop);
        if (AddShopActivity.dataBeans.size() > 0) {
            addShop.setText("加入商品货架(" + AddShopActivity.dataBeans.size() + "件)");
            addShop.setBackgroundResource(R.drawable.buttonred);
        } else {
            addShop.setText("加入商品货架");
            addShop.setBackgroundResource(R.drawable.buttonredun);
        }
        popupWindow.setBackgroundDrawable(null);
        //进行网络请求
        popupWindow.showAtLocation(addshopRel2, Gravity.BOTTOM, 0, 50);

        addAdapter.setOnClickLinstener(new AddAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                if (AddShopActivity.dataBeans.size() < 10) {
                    if (addAdapter.mList.get(posstion).isSel()) {
                        for (int i = 0; i < AddShopActivity.dataBeans.size(); i++) {
                            if (AddShopActivity.dataBeans.get(i).getAid() == addAdapter.mList.get(posstion).getAid()) {
                                AddShopActivity.dataBeans.remove(i);
                                break;
                            }
                        }
                        addAdapter.mList.get(posstion).setSel(false);
                    } else {
                        addAdapter.mList.get(posstion).setSel(true);
                        AddShopActivity.dataBeans.add(addAdapter.mList.get(posstion));
                    }
                    addAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(activity, "最多添加10个", Toast.LENGTH_SHORT).show();
                }
                if (AddShopActivity.dataBeans.size() > 0) {
                    addShop.setText("加入商品货架(" + AddShopActivity.dataBeans.size() + "件)");
                    addShop.setBackgroundResource(R.drawable.buttonred);
                    addShop.setText("加入商品货架(" + AddShopActivity.dataBeans.size() + "件)");
                    addShop.setBackgroundResource(R.drawable.buttonred);
                } else {
                    addShop.setText("加入商品货架");
                    addShop.setBackgroundResource(R.drawable.buttonredun);
                    addShop.setText("加入商品货架");
                    addShop.setBackgroundResource(R.drawable.buttonredun);
                }

            }
        });


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("shop_id", storeAdapter.mList.get(posstion).getAid() + "");
        storePage += 1;
        hashMap.put("page", storePage + "");
        mPresenter.getShopList(hashMap);
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_collection;
    }
    public void setData(){
        Log.i("wxy", "setData: 第二个 ");
    }
    @Override
    protected AddShopPresenter preparePresenter() {
        return new AddShopPresenter();
    }

    @Override
    public void collectionStoreSuccess(String success) {

       /* if (isSearch) {
            addshopSamrt2.finishLoadMore();
            StoreBean storeBean = new Gson().fromJson(success, StoreBean.class);
            List<StoreBean.DataBean> data = storeBean.getData();
            if (data != null && data.size() > 0) {
                storeAdapter2.mList.addAll(data);
                storeAdapter2.notifyDataSetChanged();
            }
            if(activity.friednsEd.getText().toString().length()<=0||activity.friednsEd.getText().toString().equals("")){
                isSearch=false;
            }
        } else {*/
            addshopSamrt.finishLoadMore();
            StoreBean storeBean = new Gson().fromJson(success, StoreBean.class);
            List<StoreBean.DataBean> data = storeBean.getData();
            if (data != null && data.size() > 0) {
                storeAdapter.mList.addAll(data);
                storeAdapter.notifyDataSetChanged();
            }
     //   }

    }

    @Override
    public void collectionStoreError(String error) {

    }

    @Override
    public void collectionSuccess(String success) {

    }

    @Override
    public void collectionError(String error) {

    }

    @Override
    public void shopListSuccess(String success) {
        //请求数据
        smart.finishLoadMore();
        CompleteBean completeBean = new Gson().fromJson(success, CompleteBean.class);
        List<CompleteBean.DataBean> data = completeBean.getData();
        if (AddShopActivity.dataBeans.size() > 0) {

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < AddShopActivity.dataBeans.size(); j++) {

                    if (AddShopActivity.dataBeans.get(j).getAid() == data.get(i).getAid()) {
                        data.get(i).setSel(true);
                        break;
                    }
                }
            }
        }
        if (data != null && data.size() > 0) {
            addAdapter.mList.addAll(data);
            addAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public void shopListError(String error) {

    }
}
