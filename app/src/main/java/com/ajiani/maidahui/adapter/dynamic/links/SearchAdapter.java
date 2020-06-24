package com.ajiani.maidahui.adapter.dynamic.links;

import android.graphics.Color;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.Contact;
import com.ajiani.maidahui.cn.CNPinyinIndex;

import java.util.List;


/**
 * Created by you on 2017/9/12.
 */

public class SearchAdapter extends RecyclerView.Adapter<ContactHolder> {

    private final List<CNPinyinIndex<Contact>> contactList;

    public SearchAdapter(List<CNPinyinIndex<Contact>> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        CNPinyinIndex<Contact> index = contactList.get(position);
        Contact contact = index.cnPinyin.data;


        SpannableStringBuilder ssb = new SpannableStringBuilder(contact.chinese());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        ssb.setSpan(span, index.start, index.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tv_name.setText(ssb);
    }

    public void setNewDatas(List<CNPinyinIndex<Contact>> newDatas) {
        this.contactList.clear();
        if (newDatas != null && !newDatas.isEmpty()) {
            this.contactList.addAll(newDatas);
        }
        notifyDataSetChanged();
    }

}
