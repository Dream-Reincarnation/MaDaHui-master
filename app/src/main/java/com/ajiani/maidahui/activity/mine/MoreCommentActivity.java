package com.ajiani.maidahui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.adapter.mine.MoreCommAdapter;
import com.ajiani.maidahui.base.SimpleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreCommentActivity extends SimpleActivity {
    @BindView(R.id.morecomm_back)
    ImageView morecommBack;
    @BindView(R.id.morecomm_img)
    ImageView morecommImg;
    @BindView(R.id.morecomm_name)
    TextView morecommName;
    @BindView(R.id.morecomm_time)
    TextView morecommTime;
    @BindView(R.id.morecomm_like)
    ImageView morecommLike;
    @BindView(R.id.morecomm_likenum)
    TextView morecommLikenum;
    @BindView(R.id.morecomm_context)
    TextView morecommContext;
    @BindView(R.id.moercomm_rel)
    RecyclerView moercommRel;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
         moercommRel.setLayoutManager(new LinearLayoutManager(this));
        MoreCommAdapter moreCommAdapter = new MoreCommAdapter();
        moercommRel.setAdapter(moreCommAdapter);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_morecomment;
    }

    @OnClick({R.id.morecomm_back, R.id.morecomm_like})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.morecomm_back:
                finish();
                break;
            case R.id.morecomm_like:
                break;
        }
    }
}
