package com.ajiani.maidahui.fragment.dynamic.music;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectFragment extends BaseFragment {
    @BindView(R.id.select_more)
    LinearLayout selectMore;
    @BindView(R.id.select_rel)
    RecyclerView selectRel;



    @Override
    protected BasePresenterImp preparePresenter() {
        return null;
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_select;
    }

    @OnClick({R.id.select_more, R.id.select_rel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_more:
                break;
            case R.id.select_rel:
                break;
        }
    }
}
