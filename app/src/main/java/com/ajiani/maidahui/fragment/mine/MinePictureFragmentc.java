package com.ajiani.maidahui.fragment.mine;

import android.widget.ImageView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleFragment;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class MinePictureFragmentc extends SimpleFragment {

    String url;
    @BindView(R.id.minepic_imga)
    ImageView minepicImga;

    public MinePictureFragmentc(String url) {
        this.url = url;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Glide.with(getActivity()).load(url).into(minepicImga);
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_minpica;
    }
}
