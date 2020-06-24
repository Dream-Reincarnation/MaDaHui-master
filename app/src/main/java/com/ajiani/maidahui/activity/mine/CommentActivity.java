package com.ajiani.maidahui.activity.mine;

import android.app.Activity;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.mInterface.mine.VideoInfoIn;
import com.ajiani.maidahui.presenters.mine.VideoPresenter;

public class CommentActivity extends BaseActivity<VideoInfoIn.videoInfoView, VideoPresenter> implements VideoInfoIn.videoInfoView {


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
        return R.layout.comment_item;
    }

    @Override
    public void getVideoData(String success) {

    }

    @Override
    public void getCommentSuccess(String success) {

    }

    @Override
    public void videoStarSuccess(String success) {

    }

    @Override
    public void FollowUserSuccess(String success) {

    }

    @Override
    protected VideoPresenter preparePresenter() {
        return new VideoPresenter();
    }
}
