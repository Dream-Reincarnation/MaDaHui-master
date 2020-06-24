package com.ajiani.maidahui.adapter.dynamic;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class VPAdapter  extends FragmentStatePagerAdapter {
    public ArrayList<Fragment> mList;
    public ArrayList<String> mStrings;

    public VPAdapter(FragmentManager fm, ArrayList<Fragment> mList, ArrayList<String> mStrings) {
        super(fm);
        this.mList = mList;
        this.mStrings = mStrings;
    }

    public VPAdapter(FragmentManager fm, ArrayList<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    public VPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings.get(position);
    }
}