package com.ajiani.maidahui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
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
import com.ajiani.maidahui.Utils.DialogUtil;
import com.ajiani.maidahui.Utils.txrecord.ProgressFragmentUtil;
import com.ajiani.maidahui.activity.dynamic.TCVideoCutterActivity;
import com.ajiani.maidahui.activity.dynamic.txrecord.PictureSelActivity;
import com.ajiani.maidahui.adapter.dynamic.PicVideoAdapter;
import com.ajiani.maidahui.adapter.dynamic.PictureAdapter;
import com.ajiani.maidahui.base.SimpleFragment;
import com.ajiani.maidahui.bean.dynamic.PicVideoBean;
import com.ajiani.maidahui.tencent.TCConstants;
import com.tencent.ugc.TXVideoEditConstants;
import com.tencent.ugc.TXVideoJoiner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class PicVideoFragment extends SimpleFragment implements TXVideoJoiner.TXVideoJoinerListener {
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
    private TXVideoJoiner mTXVideoJoiner;
    private ProgressFragmentUtil mProgressFragmentUtil;
    private String mOutputPath;

    public PicVideoFragment(String path) {
        this.path = path;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        activity = (PictureSelActivity) getActivity();
        List<String> filesAllName = getFilesAllName(path);
        picVideoBeans = new ArrayList<>();
        for (int i =filesAllName.size()-1; i >=0 ; i--) {
            PicVideoBean picVideoBean = new PicVideoBean();
            picVideoBean.setPath(filesAllName.get(i));
            picVideoBeans.add(picVideoBean);
        }
        picVideoAdapter = new PicVideoAdapter(picVideoBeans,getActivity(),true);
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
                                relativeLayout.setVisibility(View.GONE);
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

    public void setPath(String path){
        List<String> filesAllName = getFilesAllName(path);
        picVideoBeans = new ArrayList<>();

        for (int i =filesAllName.size()-1; i >=0 ; i--) {
            PicVideoBean picVideoBean = new PicVideoBean();
            picVideoBean.setPath(filesAllName.get(i));
            picVideoBeans.add(picVideoBean);
        }
        picVideoAdapter.mList.clear();
        picVideoAdapter.mList.addAll(picVideoBeans);
        picVideoAdapter.notifyDataSetChanged();

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
                //合成视频跳转页面
                ArrayList<String> strings = new ArrayList<>();
                if(pictureAdapter!=null){
                    for (int i = 0; i < pictureAdapter.mList.size(); i++) {
                        strings.add(pictureAdapter.mList.get(i).getPath());
                    }
                }

                compoundVideo(strings);
            }
        });

    }

    public void compoundVideo(ArrayList<String> paths){
        mTXVideoJoiner = new TXVideoJoiner(getActivity());
        int ret = mTXVideoJoiner.setVideoPathList(paths);
        if (ret == 0) {
        } else if (ret == TXVideoEditConstants.ERR_UNSUPPORT_VIDEO_FORMAT) {
            DialogUtil.showDialog(getActivity(), "视频合成失败", "本机型暂不支持此视频格式", null);
        } else if (ret == TXVideoEditConstants.ERR_UNSUPPORT_AUDIO_FORMAT) {
            DialogUtil.showDialog(getActivity(), "视频合成失败", "暂不支持非单双声道的视频格式", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();

                }
            });
        }
        mTXVideoJoiner.setVideoJoinerListener(this);
        mOutputPath = getCustomVideoOutputPath("ajiani");

        mProgressFragmentUtil = new ProgressFragmentUtil(getActivity(),getResources().getString(R.string.video_joining));
        mProgressFragmentUtil.showLoadingProgress(new ProgressFragmentUtil.IProgressListener() {
            @Override
            public void onStop() {
                mProgressFragmentUtil.dismissLoadingProgress();
                cancelJoin();
            }
        });
        mTXVideoJoiner.joinVideo(TXVideoEditConstants.VIDEO_COMPRESSED_720P, mOutputPath);
    }
    /**
     * 取消视频合成
     */
    private void cancelJoin() {

        if (mTXVideoJoiner != null) {
            mTXVideoJoiner.cancel();
        }

         /*   if (mOnVideoJoinListener != null) {
                mOnVideoJoinListener.onJoinCanceled();
            }*/

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    @Override
    protected int createLayout() {
        return R.layout.fragment_picvideo;
    }
    public List<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){
            Log.e("error","空目录");return null;}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            if(files[i].getName().endsWith(".mp4")){
                s.add(files[i].getAbsolutePath());
            }

        }
        return s;
    }
    //视频的地址
    private String getCustomVideoOutputPath(String fileNamePrefix) {
        //放app目录下   voice
        long currentTime = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        String time = sdf.format(new Date(currentTime));
        File externalFilesDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_MOVIES);

        String outputDir = Environment.getExternalStorageDirectory() + File.separator + TCConstants.OUTPUT_DIR_NAME;
        File outputFolder = new File(outputDir);
        if (!outputFolder.exists()) {
            outputFolder.mkdir();
        }
        String tempOutputPath;
        if (TextUtils.isEmpty(fileNamePrefix)) {
            tempOutputPath = outputDir + File.separator + "TXUGC_" + time + ".mp4";
        } else {
            tempOutputPath = outputDir + File.separator + "TXUGC_" + fileNamePrefix + time + ".mp4";
        }
        //Log.i(TAG, "getCustomVideoOutputPath: " + externalFilesDir.getPath() + "===" + tempOutputPath);
        return tempOutputPath;
    }









    @Override
    public void onJoinProgress(float v) {
        mProgressFragmentUtil.updateGenerateProgress((int) (v * 100));
    }

    @Override
    public void onJoinComplete(TXVideoEditConstants.TXJoinerResult txJoinerResult) {
        mProgressFragmentUtil.dismissLoadingProgress();
        if (txJoinerResult.retCode == TXVideoEditConstants.JOIN_RESULT_OK) {


            Intent intent = new Intent(getActivity(), TCVideoCutterActivity.class);
            intent.putExtra(TCConstants.VIDEO_EDITER_PATH, mOutputPath);


            startActivity(intent);
               /* UGCKitResult ugcKitResult = new UGCKitResult();
                ugcKitResult.errorCode = txJoinerResult.retCode;
                ugcKitResult.descMsg = txJoinerResult.descMsg;
                ugcKitResult.outputPath =mOutputPath ;
                mOnVideoJoinListener.onJoinCompleted(ugcKitResult);*/
        }

    }


}
