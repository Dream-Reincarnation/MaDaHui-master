package com.ajiani.maidahui.adapter.dynamic;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class AddshopVpAdapter extends FragmentStatePagerAdapter {
    public ArrayList<Fragment> mList;
     public ArrayList<String> mStrings;

    public AddshopVpAdapter(FragmentManager fm, ArrayList<Fragment> mList, ArrayList<String> mStrings) {
        super(fm);
        this.mList = mList;
        this.mStrings = mStrings;
    }

    public AddshopVpAdapter(FragmentManager fm, ArrayList<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    public AddshopVpAdapter(FragmentManager fm) {
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
