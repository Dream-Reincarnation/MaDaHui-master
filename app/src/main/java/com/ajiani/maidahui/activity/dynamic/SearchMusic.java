package com.ajiani.maidahui.activity.dynamic;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.mInterface.dynamic.SearMusic;
import com.ajiani.maidahui.presenters.dynamic.SearMusicPresenter;

public class SearchMusic extends BaseActivity<SearMusic.searchView, SearMusicPresenter> implements SearMusic.searchView {


    @Override
    protected SearMusicPresenter preparePresenter() {
        return new SearMusicPresenter();
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_music;
    }

    @Override
    public void success(String success) {

    }

    @Override
    public void successInfo(String string) {

    }

    @Override
    public void colleectioSuccess(String sucess) {

    }

    @Override
    public void getCollectionSuccess(String success) {

    }
}
