package com.ajiani.maidahui.fragment.dynamic.addshop;

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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.AddShopActivity;
import com.ajiani.maidahui.activity.dynamic.VideoReleaaseActivity;
import com.ajiani.maidahui.adapter.dynamic.AddAdapter;
import com.ajiani.maidahui.adapter.dynamic.StoreAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.dynamic.CommodityBean;
import com.ajiani.maidahui.bean.dynamic.CompleteBean;
import com.ajiani.maidahui.bean.dynamic.StoreBean;
import com.ajiani.maidahui.mInterface.dynamic.AddshopIn;
import com.ajiani.maidahui.presenters.dynamic.AddShopPresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class CollectionStoreFragment extends BaseFragment<AddshopIn.AddshopView, AddShopPresenter> implements AddshopIn.AddshopView {
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
    private AddShopActivity activity;
    private RecyclerView popRel;
    private AddAdapter addAdapter;
    private SmartRefreshLayout smart;

    @Override
    public void error(String error) {


    }

    @Override
    protected void initData() {
        activity = (AddShopActivity) getActivity();
       /* addshopRel2.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<StoreBean.DataBean> dataBeans = new ArrayList<>();
        storeAdapter2 = new StoreAdapter(dataBeans);
        addshopRel2.setAdapter(storeAdapter2);*/


        addshopRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<StoreBean.DataBean> dataBeans2 = new ArrayList<>();
        storeAdapter = new StoreAdapter(dataBeans2);
        addshopRel.setAdapter(storeAdapter);

        HashMap<String, String> hashMap = new HashMap<>();
        page += 1;
        hashMap.put("page", page + "");
        mPresenter.getCollectionStore(hashMap);

        storeAdapter.setOnClickLinstener(new StoreAdapter.onClickLinstener() {


            @Override
            public void onClick(int posstion) {
                showPop(posstion,storeAdapter);
            }
        });

      /*  storeAdapter2.setOnClickLinstener(new StoreAdapter.onClickLinstener() {


            @Override
            public void onClick(int posstion) {
                showPop(posstion,storeAdapter2);
            }
        });*/
        /*activity.friendsDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addshopRel2.setVisibility(View.GONE);
                addshopRel.setVisibility(View.VISIBLE);
                activity.friednsEd.setText("");
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(activity.friednsEd.getWindowToken(), 0);

            }
        });*/
        addshopSamrt.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                HashMap<String, String> hashMap = new HashMap<>();
                page += 1;
                hashMap.put("page", page + "");
                mPresenter.getCollectionStore(hashMap);
            }
        });
        /*addshopSamrt2.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                HashMap<String, String> hashMap = new HashMap<>();
                searchPage += 1;
                hashMap.put("page", searchPage + "");
                mPresenter.getCollectionStore(hashMap);
            }
        });*/

        /*activity.friednsEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //进行网络请求
                searchPage = 0;
                isSearch = true;
                String s1 = activity.friednsEd.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("keyword", s1);
                searchPage += 1;
                hashMap.put("page", searchPage + "");
                mPresenter.getCollectionStore(hashMap);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = activity.friednsEd.getText().toString();
                Log.i("wxy", "afterTextChanged: ===" + s1 + "====" + s1.length());
                if (s1.length() <= 0 || s1.equals("")) {
                    addshopSamrt2.setVisibility(View.GONE);
                    addshopSamrt.setVisibility(View.VISIBLE);
                    activity.friendsDel.setVisibility(View.GONE);

                } else {
                    addshopSamrt2.setVisibility(View.VISIBLE);
                    activity.friendsDel.setVisibility(View.VISIBLE);
                    addshopSamrt.setVisibility(View.GONE);
                }
            }
        });*/

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
        if (activity.dataBeans.size() > 0) {
            addShop.setText("加入商品货架(" + activity.dataBeans.size() + "件)");
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
                if (activity.dataBeans.size() < 10) {
                    if (addAdapter.mList.get(posstion).isSel()) {
                        for (int i = 0; i < activity.dataBeans.size(); i++) {
                            if (activity.dataBeans.get(i).getAid() == addAdapter.mList.get(posstion).getAid()) {
                                activity.dataBeans.remove(i);
                                break;
                            }
                        }
                        addAdapter.mList.get(posstion).setSel(false);
                    } else {
                        addAdapter.mList.get(posstion).setSel(true);
                        activity.dataBeans.add(addAdapter.mList.get(posstion));
                    }
                    addAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(activity, "最多添加10个", Toast.LENGTH_SHORT).show();
                }
                if (activity.dataBeans.size() > 0) {
                    activity.addshop.setText("加入商品货架(" + activity.dataBeans.size() + "件)");
                    activity.addshop.setBackgroundResource(R.drawable.buttonred);
                    addShop.setText("加入商品货架(" + activity.dataBeans.size() + "件)");
                    addShop.setBackgroundResource(R.drawable.buttonred);
                } else {
                    activity.addshop.setText("加入商品货架");
                    activity.addshop.setBackgroundResource(R.drawable.buttonredun);
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
        if (activity.dataBeans.size() > 0) {

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < activity.dataBeans.size(); j++) {

                    if (activity.dataBeans.get(j).getAid() == data.get(i).getAid()) {
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
