package com.ajiani.maidahui.adapter.dynamic.links;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.Contact;
import com.ajiani.maidahui.cn.CNPinyin;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by you on 2017/9/11.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> implements StickyHeaderAdapter<HeaderHolder> {

    public final List<CNPinyin<Contact>> cnPinyinList;
    private onClickLinsten onClick;
    private Context context;
    public ArrayList<Integer> mList;
    public ContactAdapter(List<CNPinyin<Contact>> cnPinyinList) {
        this.cnPinyinList = cnPinyinList;
    }

    public ContactAdapter(List<CNPinyin<Contact>> cnPinyinList, ArrayList<Integer> mList) {
        this.cnPinyinList = cnPinyinList;
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return cnPinyinList.size();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ContactHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, parent, false));
    }


    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        Contact contact = cnPinyinList.get(position).data;
        Glide.with(context).load(contact.imgUrl).apply(new RequestOptions().circleCrop()).into(holder.iv_header);
        holder.tv_name.setText(contact.name);
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClick!=null){
                    onClick.onClick(position);
                }
            }
        });
    }

    @Override
    public long getHeaderId(int childAdapterPosition) {
        return cnPinyinList.get(childAdapterPosition).getFirstChar();
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder holder, int childAdapterPosition) {

        holder.tv_header.setText(String.valueOf(cnPinyinList.get(childAdapterPosition).getFirstChar()));

    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header, parent, false));
    }
    public interface onClickLinsten{
        void onClick(int posstion);
    }
    public void setOnClickLinsten(onClickLinsten onClickLinsten){
        this.onClick=onClickLinsten;
    }

}
