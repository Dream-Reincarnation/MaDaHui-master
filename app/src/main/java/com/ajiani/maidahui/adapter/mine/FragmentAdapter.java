package com.ajiani.maidahui.adapter.mine;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
    public ArrayList<String> mString;
    public ArrayList<Fragment> mList;
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(FragmentManager fm, ArrayList<String> mString, ArrayList<Fragment> mList) {
        super(fm);
        this.mString = mString;
        this.mList = mList;
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
        return mString.get(position);
    }
}
