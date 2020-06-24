package com.ajiani.maidahui.adapter.dynamic;

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
import com.ajiani.maidahui.adapter.mine.VlogAdapter;
import com.ajiani.maidahui.adapter.mine.VlogShopAdapter;
import com.ajiani.maidahui.bean.dynamic.PersonVideoBean;
import com.ajiani.maidahui.bean.mine.MineVideoBean;
import com.ajiani.maidahui.bean.mine.VideoInfoBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrassAdapter extends RecyclerView.Adapter<GrassAdapter.ViewHolder> {
   public ArrayList<PersonVideoBean.DataBean> mList;
    private Context context;


    private int star;


    public GrassAdapter(ArrayList<PersonVideoBean.DataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_vlogitem, parent, false);
        return  new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonVideoBean.DataBean dataBean = mList.get(position);
        String head = (String) SPUtils.get(context, "head", "");
        String name = (String) SPUtils.get(context, "name", "");
        Glide.with(context).load(head).apply(new RequestOptions().circleCrop()).into(holder.vlogItemHead);
        holder.vlogItemCreatetime.setText(dataBean.getFormat_create_time());
        Glide.with(context).load(dataBean.getThumb()).into(holder.vlogItemThumb);
        holder.vlogItemNum.setText(dataBean.getCount() + "");
        holder.vlogItemName.setText(name);
        holder.vlogItemCommment.setText(dataBean.getComment() + "");
        holder.vlogItemLike.setText(dataBean.getStar() + "");
        if (dataBean.getIs_star().equals("1")) {
            holder.vlogLike.setImageResource(R.mipmap.mine_like);
        } else {
            holder.vlogLike.setImageResource(R.mipmap.mine_unlike);
        }
        String content1 = "<font color='red'>" + dataBean.getTopic() + "</font>" + dataBean.getTitle();
        holder.vlogItemTitle.setText(Html.fromHtml(content1));
        holder.vlogItemRel.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        List<PersonVideoBean.DataBean.GoodsBean> goods = dataBean.getGoods();
        int finalPosition = position;
        holder.vlogItemThumb.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        //点击评论跳转到视频
        holder.vlogItemcommlin.setOnClickListener(new View.OnClickListener() {
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
        star = dataBean.getStar();
        //收藏
        holder.vlogLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.getIs_star().equals("1")) {
                    star = dataBean.getStar();
                    holder.vlogLike.setImageResource(R.mipmap.mine_unlike);
                    dataBean.setIs_star("0");
                    star--;
                    dataBean.setStar(star);
                    holder.vlogItemLike.setText(star+"");
                } else {
                    star = dataBean.getStar();
                    dataBean.setIs_star("1");
                    holder.vlogLike.setImageResource(R.mipmap.mine_like);
                    star++;
                    dataBean.setStar(star);
                    holder.vlogItemLike.setText(star+"");
                }
                //收藏
                int aid = dataBean.getAid();
                HttpUtils.instance().videostar(aid+"");

            }
        });

        if (goods != null) {
            ArrayList<VideoInfoBean.DataBean.GoodsBean> goodsBeans = new ArrayList<>();

            for (int i = 0; i <goods.size(); i++) {
                VideoInfoBean.DataBean.GoodsBean goodsBean = new VideoInfoBean.DataBean.GoodsBean();
                goodsBean.setName(goods.get(i).getName());
                goodsBean.setPrice(goods.get(i).getPrice());
                goodsBean.setThumb(goods.get(i).getThumb());
                goodsBeans.add(goodsBean);
            }
            VlogShopAdapter vlogShopAdapter = new VlogShopAdapter(goodsBeans,dataBean.getAid());
            holder.vlogItemRel.setAdapter(vlogShopAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
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
}
