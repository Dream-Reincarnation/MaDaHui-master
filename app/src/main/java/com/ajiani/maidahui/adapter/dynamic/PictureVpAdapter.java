package com.ajiani.maidahui.adapter.dynamic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PictureVpAdapter extends FragmentStatePagerAdapter {
    public ArrayList<Fragment> mList;
    public ArrayList<String> strings;

    public PictureVpAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public PictureVpAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> mList) {
        super(fm);
        this.mList = mList;
    }

    public PictureVpAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> mList, ArrayList<String> strings) {
        super(fm);
        this.mList = mList;
        this.strings = strings;
    }

    @NonNull
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
        return strings.get(position)
                ;
    }
}
