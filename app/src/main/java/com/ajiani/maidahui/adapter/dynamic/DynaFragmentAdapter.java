package com.ajiani.maidahui.adapter.dynamic;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class DynaFragmentAdapter extends FragmentStatePagerAdapter {
    public ArrayList<String> mStrings;
    public ArrayList<Fragment> mList;

    public DynaFragmentAdapter(FragmentManager fm, ArrayList<String> mStrings, ArrayList<Fragment> mList) {
        super(fm);
        this.mStrings = mStrings;
        this.mList = mList;
    }

    public DynaFragmentAdapter(FragmentManager fm) {
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
