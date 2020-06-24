package com.ajiani.maidahui.fragment.dynamic.picture;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.SimpleFragment;
import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class
AtlasFragment extends SimpleFragment {
    @BindView(R.id.picture_scroll)
    HorizontalScrollView pictureScroll;
    @BindView(R.id.picture_bauty)
    LinearLayout pictureBauty;
    @BindView(R.id.picture_lin)
    LinearLayout pictureLin;
    @BindView(R.id.picture_music)
    LinearLayout pictureMusic;
    @BindView(R.id.picture_tag)
    LinearLayout pictureTag;
    @BindView(R.id.picture_size)
    LinearLayout pictureSize;
    @BindView(R.id.picture_next)
    TextView pictureNext;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        ArrayList<String> pictures = bundle.getStringArrayList("pictures");

        for (int i = 0; i <pictures.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            /*ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width=ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.height=ViewGroup.LayoutParams.WRAP_CONTENT;*/

           imageView.setImageBitmap(BitmapFactory.decodeFile(pictures.get(i)));
            pictureLin.addView(imageView);
        }


    }

    @Override
    protected int createLayout() {
        return R.layout.fragment_atals;
    }

    @OnClick({R.id.picture_bauty, R.id.picture_music, R.id.picture_tag, R.id.picture_size, R.id.picture_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.picture_bauty:
                break;
            case R.id.picture_music:
                break;
            case R.id.picture_tag:
                break;
            case R.id.picture_size:
                break;
            case R.id.picture_next:
                break;
        }
    }
}
