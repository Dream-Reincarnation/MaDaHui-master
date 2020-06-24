package com.ajiani.maidahui.adapter.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.http.HttpUtils;
import com.ajiani.maidahui.activity.Main3Activity;
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flyco.tablayout.SegmentTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VlogAdapter extends RecyclerView.Adapter {
    public ArrayList<VideoInfoBean.DataBean> mList;
    private String[] mTitles_3 = {"今日", "昨日", "本月", "累计"};
    private Context context;
    private int star;

    public VlogAdapter(ArrayList<VideoInfoBean.DataBean> mList) {
        this.mList = mList;
    }

    //根据条件返回条目的类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        context = parent.getContext();
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.vlog_item2, parent, false);
            holder = new ViewHolder2(inflate);
        } else {

            View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_vlogitem, parent, false);
            holder = new ViewHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        position--;
        if (holder instanceof ViewHolder) {
            ViewHolder holder1 = (ViewHolder) holder;
            VideoInfoBean.DataBean dataBean = mList.get(position);
            String head = (String) SPUtils.get(context, "head", "");
            String name = (String) SPUtils.get(context, "name", "");
            Glide.with(context).load(head).apply(new RequestOptions().circleCrop()).into(holder1.vlogItemHead);
            holder1.vlogItemCreatetime.setText(dataBean.getFormat_create_time());
            Glide.with(context).load(dataBean.getThumb()).into(holder1.vlogItemThumb);
            holder1.vlogItemNum.setText(dataBean.getCount() + "");
            holder1.vlogItemName.setText(name);
            holder1.vlogItemCommment.setText(dataBean.getComment() + "");
            holder1.vlogItemLike.setText(dataBean.getStar() + "");
            if (dataBean.getIs_star().equals("1")) {
                holder1.vlogLike.setImageResource(R.mipmap.mine_like);
            }
            String content1 = "<font color='red'>" + dataBean.getTopic() + "</font>" + dataBean.getTitle();
            holder1.vlogItemTitle.setText(Html.fromHtml(content1));
            holder1.vlogItemRel.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            List<VideoInfoBean.DataBean.GoodsBean> goods = dataBean.getGoods();
            int finalPosition = position;
            holder1.vlogItemThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> strings = new ArrayList<>();
                    for(int i=0;i<mList.size();i++){
                        strings.add(mList.get(i).getAid()+"");
                    }
                    //跳转视频页面
                    Bundle bundle1 = new Bundle();
                    bundle1.putStringArrayList("list", strings);
                    bundle1.putString("posstion", finalPosition +"");
                    JumpUtils.gotoActivity(context, Main3Activity.class,bundle1);
                }{
                    holder1.vlogLike.setImageResource(R.mipmap.mine_unlike);
                }
            });

            //点击评论跳转到视频
            holder1.vlogItemcommlin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<String> strings = new ArrayList<>();
                    for(int i=0;i<mList.size();i++){
                        strings.add(mList.get(i).getAid()+"");
                    }
                    //跳转视频页面
                    Bundle bundle1 = new Bundle();
                    bundle1.putStringArrayList("list", strings);
                    bundle1.putString("posstion", finalPosition +"");
                    bundle1.putString("isshow", "0");
                    JumpUtils.gotoActivity(context, Main3Activity.class,bundle1);
                }

            });
            star = Integer.parseInt(dataBean.getStar());

          //收藏
            holder1.vlogLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dataBean.getIs_star().equals("1")) {
                        star = Integer.parseInt(dataBean.getStar());
                        holder1.vlogLike.setImageResource(R.mipmap.mine_unlike);
                        dataBean.setIs_star("0");
                        star--;
                        dataBean.setStar(String.valueOf(star));
                        holder1.vlogItemLike.setText(star+"");
                    } else {
                        star = Integer.parseInt(dataBean.getStar());
                        dataBean.setIs_star("1");
                        holder1.vlogLike.setImageResource(R.mipmap.mine_like);
                        star++;
                        dataBean.setStar(String.valueOf(star));
                        holder1.vlogItemLike.setText(star+"");
                    }
                    //收藏
                    int aid = dataBean.getAid();
                    HttpUtils.instance().videostar(aid+"");

                }
            });

            if (goods != null) {
                VlogShopAdapter vlogShopAdapter = new VlogShopAdapter((ArrayList<VideoInfoBean.DataBean.GoodsBean>) goods,dataBean.getAid());
                holder1.vlogItemRel.setAdapter(vlogShopAdapter);
            }
        } else if (holder instanceof ViewHolder2) {
           ViewHolder2 holder2= (ViewHolder2) holder;
           holder2.vlogsTab.setTabData(mTitles_3);

        }

    }


    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vlog_item_comment_lin)
        LinearLayout vlogItemcommlin;
        @BindView(R.id.vlog_item_like_lin)
        LinearLayout vlogItemlikelin;
        @BindView(R.id.vlog_item_head)
        ImageView vlogItemHead;
        @BindView(R.id.vlog_item_like)
        ImageView vlogLike;
        @BindView(R.id.vlog_item_name)
        TextView vlogItemName;
        @BindView(R.id.vlog_item_comment)
        TextView vlogItemCommment;
        @BindView(R.id.vlog_item_likenum)
        TextView vlogItemLike;
        @BindView(R.id.vlog_item_title)
        TextView vlogItemTitle;
        @BindView(R.id.vlog_item_createtime)
        TextView vlogItemCreatetime;
        @BindView(R.id.vlog_item_thumb)
        ImageView vlogItemThumb;
        @BindView(R.id.vlog_item_num)
        TextView vlogItemNum;
        @BindView(R.id.vlog_item_rel)
        RecyclerView vlogItemRel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {



        @BindView(R.id.vlogs_tab)
        SegmentTabLayout vlogsTab;
        @BindView(R.id.vlogs_ordernum)
        TextView vlogsOrdernum;
        @BindView(R.id.vlogs_order)
        TextView vlogsOrder;
        @BindView(R.id.vlogs_unyi)
        TextView vlogsUnyi;
        @BindView(R.id.vlogs_unimg)
        ImageView vlogsUnimg;
        @BindView(R.id.vlogs_ordernums)
        TextView vlogsOrdernums;
        @BindView(R.id.vlogs_earningsnum)
        TextView vlogsEarningsnum;
        @BindView(R.id.vlogs_voteimg)
        ImageView vlogsVoteimg;
        @BindView(R.id.vlogs_votes)
        TextView vlogsVotes;
        @BindView(R.id.vlogs_earnings)
        TextView vlogsEarnings;
        @BindView(R.id.vlogs_mai)
        TextView vlogsMai;
        @BindView(R.id.vlogs_maiimg)
        ImageView vlogsMaiimg;
        @BindView(R.id.vlogs_xiao)
        TextView vlogsXiao;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
