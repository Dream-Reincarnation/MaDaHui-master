package com.ajiani.maidahui.activity.dynamic;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.dynamic.TalkAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.Even;
import com.ajiani.maidahui.bean.dynamic.TalkBean;
import com.ajiani.maidahui.mInterface.dynamic.AddTalk;
import com.ajiani.maidahui.presenters.dynamic.TalkPresenter;
import com.ajiani.maidahui.weight.FlowLayout;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectTalk extends BaseActivity<AddTalk.talkView, TalkPresenter> implements AddTalk.talkView {


    @BindView(R.id.sel_rel)
    RecyclerView selRel;
    @BindView(R.id.search_del)
    FrameLayout searchDel;
    @BindView(R.id.sel_flow)
    FlowLayout selFlow;
    int page = 1;
    @BindView(R.id.sel_back)
    FrameLayout selBack;
    @BindView(R.id.search_ed)
    EditText searchEd;
    @BindView(R.id.search_search)
    FrameLayout searchSearch;
    @BindView(R.id.search_cancle)
    FrameLayout searchCancle;
    private TalkAdapter talkAdapter;
    String  regis="^[\\u4e00-\\u9fa5a-zA-Z0-9]{2,50}$";
    @Override
    protected void initData() {
        searchEd.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("wxy", "onKey: "+keyCode);
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                   //进行搜索
                    data();
                    return true;
                }
                return false;
            }
        });
    }

    private void data() {
        String s = searchEd.getText().toString();
       /* if(!s.matches(regis)){
            Toast.makeText(this, "含有非法字符,请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }*/
        searchSearch.setVisibility(View.GONE);
        searchCancle.setVisibility(View.VISIBLE);

        page=0;
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEd.getWindowToken(), 0);
        searchEd.clearFocus();//取消焦点
        //网络请求
        page+=1;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keyword", s);
        hashMap.put("page", "" + page);
        mPresenter.getTalk(hashMap);
    }

    @Override
    protected void initView() {
        searchEd.setFocusable(true);
        searchEd.setFocusableInTouchMode(true);
        searchEd.requestFocus();
        //显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        ArrayList<TalkBean.DataBean> dataBeans = new ArrayList<>();
        talkAdapter = new TalkAdapter(dataBeans);
        selRel.setLayoutManager(new LinearLayoutManager(this));
        selRel.setAdapter(talkAdapter);


        searchEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*page=0;
                page+=1;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("keyword", searchEd.getText().toString());
                hashMap.put("page", "" + page);
                mPresenter.getTalk(hashMap);*/
                //  hashMap.put("keyword",selEd.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = searchEd.getText().toString();
                if(s.length()<0||s.equals("")){
                    searchDel.setVisibility(View.GONE);
                    searchCancle.setVisibility(View.VISIBLE);
                    searchSearch.setVisibility(View.GONE);
                    if(talkAdapter!=null){
                        talkAdapter.mList.clear();
                        talkAdapter.notifyDataSetChanged();
                    }
                }else{
                    searchDel.setVisibility(View.VISIBLE);
                    searchCancle.setVisibility(View.GONE);
                    searchSearch.setVisibility(View.VISIBLE);
                }
            }
        });
        talkAdapter.setOnClickLinstener(new TalkAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {
                String name = talkAdapter.mList.get(posstion).getName();
                if(name.length()<2){
                    Toast.makeText(SelectTalk.this, "至少两个字符", Toast.LENGTH_SHORT).show();
                }else if(name.matches(regis)){
                    //返回到 发布页面
                    Even even = new Even(2, talkAdapter.mList.get(posstion).getName());
                    EventBus.getDefault().postSticky(even);
                    finish();
                }else{
                    Toast.makeText(SelectTalk.this, "话题含有非法字符", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }




    @Override
    protected int createLayout() {
        return R.layout.activity_seltalk;
    }


    @OnClick({R.id.sel_back,R.id.search_search,R.id.search_del, R.id.search_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sel_back:
                finish();
                break;
            case R.id.search_search:
                //搜索按钮
                data();
                break;
            case R.id.search_cancle:
                finish();
                break;
            case R.id.search_del:
                searchEd.setText("");

                break;
        }
    }

    @Override
    protected TalkPresenter preparePresenter() {
        return new TalkPresenter();
    }

    @Override
    public void talkSuccess(String success) {
        Log.i("wxy", "talkSuccess: "+success);
        TalkBean talkBean = new Gson().fromJson(success, TalkBean.class);
        List<TalkBean.DataBean> data = talkBean.getData();
        String s = searchEd.getText().toString();
        if(page==1){
            if(data.size()==0){
                TalkBean.DataBean dataBean = new TalkBean.DataBean();
                dataBean.setCount("-1");
                dataBean.setName(s);
                dataBean.setType(-1);
                dataBean.setThumb("");
                data.add(0,dataBean);
            }else{
                for (int i = 0; i < data.size(); i++) {
                    String name = data.get(i).getName();
                    if(s.equals(name)){
                        break;
                    }else if(i==data.size()-1){
                        TalkBean.DataBean dataBean = new TalkBean.DataBean();
                        dataBean.setCount("-1");
                        dataBean.setName(s);
                        dataBean.setType(-1);
                        dataBean.setThumb("");
                        data.add(0,dataBean);
                        break;
                    }
                }
            }

            talkAdapter.mList.clear();
        }


        talkAdapter.mList.addAll(data);
        talkAdapter.notifyDataSetChanged();
    }

    @Override
    public void error(String error) {

    }


}
