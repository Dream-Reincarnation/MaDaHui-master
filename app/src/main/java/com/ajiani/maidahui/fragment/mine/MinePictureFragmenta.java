package com.ajiani.maidahui.fragment.mine;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleFragment;
import com.ajiani.maidahui.bean.ListBean;
import com.ajiani.maidahui.bean.mine.PictureInfoBean;
import com.ajiani.maidahui.weight.RandomDragTagLayout;
import com.ajiani.maidahui.weight.RandomDragTagView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;

public class MinePictureFragmenta extends SimpleFragment {

    String url;
    @BindView(R.id.minepic_imga)
    ImageView minepicImga;
    @BindView(R.id.photos2_rla)
    RelativeLayout photos2Rla;
    @BindView(R.id.photos2_rand)
    RandomDragTagLayout photos2Rand;
   ArrayList<PictureInfoBean.DataBean.GoodsBean.ListsBean> mList;
    private String TAG="wxy";

    public MinePictureFragmenta(String url, ArrayList<PictureInfoBean.DataBean.GoodsBean.ListsBean> mList) {

        this.url = url;
        this.mList = mList;
    }

    public MinePictureFragmenta(String url) {
        this.url = url;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

     /*   RandomDragTagView tagView = new RandomDragTagView(getContext(),even.getShopId(),even.getShopTheml());
        relativeLayout.addView(tagView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tagView.initTagView(even.getShopPrice(),500 ,600 , false);*/
        Glide.with(getActivity()).load(url).into(minepicImga);
        if(mList!=null&&mList.size()>0){
            int left = mList.get(0).getLeft();
            int top = mList.get(0).getTop();
            int shop_id = mList.get(0).getShop_id();
            String price = mList.get(0).getPrice();
            String thumb = mList.get(0).getThumb();
            RandomDragTagView tagView = new RandomDragTagView(getContext(),shop_id,thumb);
            photos2Rla.addView(tagView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tagView.initTagView(price,500 ,600 , false);

        }
    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_minpica;
    }
}
