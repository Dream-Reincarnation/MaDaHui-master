package com.ajiani.maidahui.fragment.dynamic.searchshop;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.AddShopActivity;
import com.ajiani.maidahui.activity.dynamic.SearchShopActivity;
import com.ajiani.maidahui.adapter.dynamic.AddAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.bean.dynamic.CompleteBean;
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

public class CollectionFragment2 extends BaseFragment<AddshopIn.AddshopView, AddShopPresenter> implements AddshopIn.AddshopView {


    @BindView(R.id.addshop_rel)
    RecyclerView addshopRel;
    @BindView(R.id.addshop_rel2)
    RecyclerView addshopRel2;
    @BindView(R.id.addshop_samrt)
    SmartRefreshLayout addshopSamrt;
    @BindView(R.id.addshop_samrt2)
    SmartRefreshLayout addshopSamrt2;
    private AddAdapter addAdapter;
    private AddAdapter addAdapter2;
    private SearchShopActivity activity;
    int page;
    int searchPage;
    boolean isSearch;
    private String TAG="wxy";

    public String keyWord;

    public CollectionFragment2(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){

            if(addAdapter!=null){
                for(int i=0;i<addAdapter.mList.size();i++){
                    if(addAdapter.mList.get(i).isSel()){
                        addAdapter.mList.get(i).setSel(false);
                    }
                }
                for (int i = 0; i < AddShopActivity.dataBeans.size(); i++) {
                    for(int j=0;j<addAdapter.mList.size();j++){
                        if(addAdapter.mList.get(j).isSel()){
                            continue;
                        }
                        if (AddShopActivity.dataBeans.get(i).getAid() == addAdapter.mList.get(j).getAid()) {
                            addAdapter.mList.get(j).setSel(true);
                            break;
                        }
                    }
                }
                addAdapter.notifyDataSetChanged();
            }


        }
    }


    @Override
    public void error(String error) {

    }
    public void setData(){
        Log.i("wxy", "setData: 第3个 ");
    }
    public void synchronizationData(){
        for(int i=0;i<addAdapter.mList.size();i++){
            if(addAdapter.mList.get(i).isSel()){
                addAdapter.mList.get(i).setSel(false);
            }
        }

        for (int i = 0; i < AddShopActivity.dataBeans.size(); i++) {
            for(int j=0;j<addAdapter.mList.size();j++){
                if(addAdapter.mList.get(j).isSel()){
                    continue;
                }
                if (AddShopActivity.dataBeans.get(i).getAid() == addAdapter.mList.get(j).getAid()) {
                    addAdapter.mList.get(j).setSel(true);
                    break;
                }
            }
        }
        addAdapter.notifyDataSetChanged();
    }


    public void setData(String keyWord){
         //进行网络请求

    }
    @Override
    protected void initData() {

        activity = (SearchShopActivity) getActivity();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword",keyWord);
        searchPage+=1;
        hashMap.put("page",searchPage+"");
        mPresenter.getCollection(hashMap);

        activity.friendsDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.friednsEd.setText("");
                activity.friednsEd.setHint("试试搜 商品/店铺");
                synchronizationData();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(activity.friednsEd.getWindowToken(), 0);
            }
        });
        addshopRel.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ArrayList<CompleteBean.DataBean> dataBeans = new ArrayList<>();
        addAdapter = new AddAdapter(dataBeans);
        addshopRel.setAdapter(addAdapter);




        addAdapter.setOnClickLinstener(new AddAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                if (AddShopActivity.dataBeans.size() < 10) {
                    if (addAdapter.mList.get(posstion).isSel()) {
                        for(int i=0;i<AddShopActivity.dataBeans.size();i++){
                            if(AddShopActivity.dataBeans.get(i).getAid()==addAdapter.mList.get(posstion).getAid()){
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
                    activity.addshop.setText("加入商品货架("+AddShopActivity.dataBeans.size()+"件)");
                    activity.addshop.setBackgroundResource(R.drawable.buttonred);
                }else{
                    activity.addshop.setText("加入商品货架");
                    activity.addshop.setBackgroundResource(R.drawable.buttonredun);
                }
            }
        });


        addshopSamrt.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("keyword",keyWord);
                searchPage+=1;
                hashMap.put("page",searchPage+"");
                mPresenter.getCollection(hashMap);
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_complete;
    }

    @Override
    protected AddShopPresenter preparePresenter() {
        return new AddShopPresenter();
    }

    @Override
    public void collectionStoreSuccess(String success) {

    }

    @Override
    public void collectionStoreError(String error) {

    }

    @Override
    public void collectionSuccess(String success) {
        addshopSamrt.finishLoadMore();

            CompleteBean completeBean = new Gson().fromJson(success, CompleteBean.class);
            List<CompleteBean.DataBean> data = completeBean.getData();
            if (AddShopActivity.dataBeans.size() > 0) {

                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < AddShopActivity.dataBeans.size(); j++) {
                        if(data.get(i).isSel()){
                            continue;
                        }
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
    public void collectionError(String error) {

    }

    @Override
    public void shopListSuccess(String success) {

    }

    @Override
    public void shopListError(String error) {

    }
}
