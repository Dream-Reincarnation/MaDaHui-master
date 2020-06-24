package com.ajiani.maidahui.fragment.mine;

import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;

import butterknife.BindView;

public class AcquisitionFragment extends BaseFragment {
    @BindView(R.id.acq_rel)
    RecyclerView acqRel;

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
        return R.layout.fragment_acquisition;
    }
}
