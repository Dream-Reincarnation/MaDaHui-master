package com.ajiani.maidahui.fragment.dynamic.addshop;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.dynamic.AddShopActivity;
import com.ajiani.maidahui.activity.dynamic.VideoReleaaseActivity;
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

public class CollectionFragment extends BaseFragment<AddshopIn.AddshopView, AddShopPresenter> implements AddshopIn.AddshopView {


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
    private AddShopActivity activity;
    int page;
    int searchPage;
    boolean isSearch;
    private String TAG="wxy";

    public String keyWord;


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
                for (int i = 0; i < activity.dataBeans.size(); i++) {
                    for(int j=0;j<addAdapter.mList.size();j++){
                        if(addAdapter.mList.get(j).isSel()){
                            continue;
                        }
                        if (activity.dataBeans.get(i).getAid() == addAdapter.mList.get(j).getAid()) {
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

    }
    public void synchronizationData(){
        for(int i=0;i<addAdapter.mList.size();i++){
            if(addAdapter.mList.get(i).isSel()){
                addAdapter.mList.get(i).setSel(false);
            }
        }

        for (int i = 0; i < activity.dataBeans.size(); i++) {
            for(int j=0;j<addAdapter.mList.size();j++){
                if(addAdapter.mList.get(j).isSel()){
                    continue;
                }
                if (activity.dataBeans.get(i).getAid() == addAdapter.mList.get(j).getAid()) {
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

        activity = (AddShopActivity) getActivity();

      /*  activity.friendsDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addshopRel2.setVisibility(View.GONE);
                addshopRel.setVisibility(View.VISIBLE);
                activity.friednsEd.setText("");
              synchronizationData();
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(activity.friednsEd.getWindowToken(), 0);
            }
        });*/
        addshopRel.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        ArrayList<CompleteBean.DataBean> dataBeans = new ArrayList<>();
        addAdapter = new AddAdapter(dataBeans);
        addshopRel.setAdapter(addAdapter);

        /*ArrayList<CompleteBean.DataBean> dataBeans2 = new ArrayList<>();
        addshopRel2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        addAdapter2 = new AddAdapter(dataBeans2);
        addshopRel2.setAdapter(addAdapter2);*/

        //得到全部的商品
        HashMap<String, String> hashMap = new HashMap<>();
        page += 1;
        hashMap.put("page", page + "");
        mPresenter.getCollection(hashMap);


        addAdapter.setOnClickLinstener(new AddAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                if (activity.dataBeans.size() < 10) {
                    if (addAdapter.mList.get(posstion).isSel()) {
                        for(int i=0;i<activity.dataBeans.size();i++){
                            if(activity.dataBeans.get(i).getAid()==addAdapter.mList.get(posstion).getAid()){
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
                    activity.addshop.setText("加入商品货架("+activity.dataBeans.size()+"件)");
                    activity.addshop.setBackgroundResource(R.drawable.buttonred);
                }else{
                    activity.addshop.setText("加入商品货架");
                    activity.addshop.setBackgroundResource(R.drawable.buttonredun);
                }
            }
        });
        /*addAdapter2.setOnClickLinstener(new AddAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                if (activity.dataBeans.size() < 10) {
                    if (addAdapter2.mList.get(posstion).isSel()) {
                        for (int i = 0; i < activity.dataBeans.size(); i++) {
                            if (activity.dataBeans.get(i).getAid() == addAdapter2.mList.get(posstion).getAid()) {
                                activity.dataBeans.remove(i);
                                break;
                            }


                        }
                        addAdapter2.mList.get(posstion).setSel(false);
                    } else {

                        addAdapter2.mList.get(posstion).setSel(true);


                        activity.dataBeans.add(addAdapter2.mList.get(posstion));
                    }
                    addAdapter2.notifyDataSetChanged();

                } else {
                    Toast.makeText(activity, "最多添加10个", Toast.LENGTH_SHORT).show();
                }
                Log.i("wxy", "onClick: " + activity.dataBeans.size());
                if (activity.dataBeans.size() > 0) {
                    activity.addshop.setText("加入商品货架(" + activity.dataBeans.size() + "件)");
                    activity.addshop.setBackgroundResource(R.drawable.buttonred);
                } else {
                    activity.addshop.setText("加入商品货架");
                    activity.addshop.setBackgroundResource(R.drawable.buttonredun);
                }
                if(activity.isSecond&&activity.dataBeans.size()==0){
                    activity.addshop.setText("取消商品货架商品");
                    activity.addshop.setBackgroundResource(R.drawable.buttonred);
                }else if(activity.isSecond){
                    activity.addshop.setText("更新商品货架(" + activity.dataBeans.size() + "件)");
                    activity.addshop.setBackgroundResource(R.drawable.buttonred);
                }


            }
        });*/

        addshopSamrt.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                HashMap<String, String> hashMap = new HashMap<>();
                page += 1;
                hashMap.put("page", page + "");
                mPresenter.getCollection(hashMap);
            }
        });
       /* activity.friednsEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //进行网络请求
                searchPage=0;
                isSearch=true;
                String s1 = activity.friednsEd.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("keyword",s1);
                searchPage+=1;
                hashMap.put("page",searchPage+"");
                mPresenter.getCollection(hashMap);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = activity.friednsEd.getText().toString();
                Log.i("wxy", "afterTextChanged: ==="+s1+"===="+s1.length());
                if(s1.length()<=0||s1.equals("")){
                    addshopSamrt2.setVisibility(View.GONE);
                    addshopSamrt.setVisibility(View.VISIBLE);
                    activity.friendsDel.setVisibility(View.GONE);
                    synchronizationData();
                }else{
                    addshopSamrt2.setVisibility(View.VISIBLE);
                    addshopSamrt.setVisibility(View.GONE);
                    activity.friendsDel.setVisibility(View.VISIBLE);
                }
            }
        });*/
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


       /* if(isSearch){

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
            if(searchPage==1){
                addAdapter2.mList.clear();
            }
            if (data != null && data.size() > 0) {
                addAdapter2.mList.addAll(data);
                addAdapter2.notifyDataSetChanged();

            }else{
                addAdapter2.notifyDataSetChanged();
            }
            if(activity.friednsEd.getText().toString().length()<=0||activity.friednsEd.getText().toString().equals("")){
                isSearch=false;
            }
        }else {*/
            CompleteBean completeBean = new Gson().fromJson(success, CompleteBean.class);
            List<CompleteBean.DataBean> data = completeBean.getData();
            if (activity.dataBeans.size() > 0) {

                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < activity.dataBeans.size(); j++) {
//                    Log.i(TAG, "collectionSuccess: 包含了");
                         
                        if(data.get(i).isSel()){
                            continue;
                        }
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
       // }
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
