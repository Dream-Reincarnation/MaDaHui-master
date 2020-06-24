package com.ajiani.maidahui.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.activity.dynamic.picture.PictureEditerActivity;
import com.ajiani.maidahui.activity.dynamic.txrecord.PictureSelActivity;
import com.ajiani.maidahui.adapter.dynamic.PicVideoAdapter;
import com.ajiani.maidahui.adapter.dynamic.PictureAdapter;
import com.ajiani.maidahui.base.SimpleFragment;
import com.ajiani.maidahui.bean.dynamic.PicVideoBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PictureFragment extends SimpleFragment {
    @BindView(R.id.picvideo_rela)
    RelativeLayout relativeLayout;
    @BindView(R.id.picvideo_rel)
    RecyclerView picvideoRel;
    private ArrayList<PicVideoBean> picVideoBeans;
    boolean isFist;
    public String path;
    private PicVideoAdapter picVideoAdapter;
    private PictureSelActivity activity;
    private PopupWindow popupWindow;
    private RecyclerView recyclerView;
    private TextView picVideoNext;
    private PictureAdapter pictureAdapter;

    public PictureFragment(String path) {
        this.path = path;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        activity = (PictureSelActivity) getActivity();
        List<String> filesAllName = FileUtils.getFilesAllName(path);
        picVideoBeans = new ArrayList<>();
        for (int i =filesAllName.size()-1; i >=0 ; i--) {
            PicVideoBean picVideoBean = new PicVideoBean();
            picVideoBean.setPath(filesAllName.get(i));
            picVideoBeans.add(picVideoBean);
        }
        picVideoAdapter = new PicVideoAdapter(picVideoBeans,getActivity());
        picvideoRel.setLayoutManager(new GridLayoutManager(getActivity(),4));
        picvideoRel.setAdapter(picVideoAdapter);
        picVideoAdapter.setOnClickLinstener(new PicVideoAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {

                if(!isFist){
                    if(activity.pivcideos.size()==1){
                        showPop();
                        isFist=true;

                    }
                }
                if(activity.pivcideos.size()==2){
                    if(popupWindow.isShowing()){
                        relativeLayout.setVisibility(View.VISIBLE);
                    }
                }

                if(activity.pivcideos.size()==0){
                    isFist=false;
                    relativeLayout.setVisibility(View.GONE);
                   popupWindow.dismiss();

                }else{
                    pictureAdapter = new PictureAdapter(activity.pivcideos);
                    recyclerView.setAdapter(pictureAdapter);
                    recyclerView.scrollToPosition(activity.pivcideos.size());
                    picVideoNext.setText("下一步("+activity.pivcideos.size()+")");
                    //删除底部布局的图片 ，联动上面图片布局
                    pictureAdapter.setOnClickLinstener(new PictureAdapter.onClickLinstener() {
                        @Override
                        public void onClick(int posstion) {
                            for (int i = 0; i <picVideoAdapter.mList.size() ; i++) {
                                if(activity.pivcideos.get(posstion).getPath().equals(picVideoAdapter.mList.get(i).getPath())){
                                    picVideoAdapter.mList.get(i).setSel(false);
                                    break;
                                }
                            }

                            activity.pivcideos.remove(posstion);
                            picVideoNext.setText("下一步("+activity.pivcideos.size()+")");
                            for (int i = 0; i < activity.pivcideos.size(); i++) {
                                activity.pivcideos.get(i).setNum(i+1);
                            }

                            for (int i=0;i<activity.pivcideos.size();i++){
                                for (int j = 0; j < picVideoAdapter.mList.size(); j++) {
                                    String picPath = activity.pivcideos.get(i).getPath();
                                    String s = picVideoAdapter.mList.get(j).getPath();
                                    if(picPath.equals(s)){
                                        picVideoAdapter.mList.get(j).setSel(true);
                                        picVideoAdapter.mList.get(j).setNum(activity.pivcideos.get(i).getNum());
                                    }
                                }
                            }
                            if(activity.pivcideos.size()==0){
                                popupWindow.dismiss();
                            }
                            pictureAdapter.notifyDataSetChanged();
                            picVideoAdapter.notifyDataSetChanged();


                        }
                    });

                }
            }
        });




    }

    //弹出底部布局
    public void showPop(){
        popupWindow = new PopupWindow(getActivity());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.picture_pop, null, false);
        recyclerView = inflate.findViewById(R.id.picvideos_rel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
        picVideoNext = inflate.findViewById(R.id.picvideo_next);
        picVideoNext.setText("下一步(1)");
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(dip2px(getActivity(),135));
        popupWindow.setContentView(inflate);
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimStyle);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(picvideoRel, Gravity.BOTTOM,0,0);
        picVideoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> strings = new ArrayList<>();
                for (int i = 0; i < pictureAdapter.mList.size(); i++) {
                    strings.add(pictureAdapter.mList.get(i).getPath());
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("pictures",strings);
                JumpUtils.gotoActivity(getActivity(), PictureEditerActivity.class,bundle);
            }
        });

    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    public void setPath(String path){
       List<String> filesAllName = FileUtils.getFilesAllName(path);
       picVideoBeans = new ArrayList<>();
       //检查是否有选中的图片
       for (int i =filesAllName.size()-1; i >=0 ; i--) {
           PicVideoBean picVideoBean = new PicVideoBean();
           picVideoBean.setPath(filesAllName.get(i));
           picVideoBeans.add(picVideoBean);
       }
       if(activity.pivcideos.size()>0){
           for (int i=0;i<activity.pivcideos.size();i++){
               for (int j = 0; j < picVideoBeans.size(); j++) {
                   String picPath = activity.pivcideos.get(i).getPath();
                   String s = picVideoBeans.get(j).getPath();
                   if(picPath.equals(s)){
                        picVideoBeans.get(j).setSel(true);
                        picVideoBeans.get(j).setNum(activity.pivcideos.get(i).getNum());
                   }
               }
           }
       }
       picVideoAdapter.mList.clear();
       picVideoAdapter.mList.addAll(picVideoBeans);
       picVideoAdapter.notifyDataSetChanged();

   }


    @Override
    protected int createLayout() {
        return R.layout.fragment_picvideo;
    }

    //得到该目录下的所有图片或视频

}
