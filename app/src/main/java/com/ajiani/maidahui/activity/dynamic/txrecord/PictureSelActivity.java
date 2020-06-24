package com.ajiani.maidahui.activity.dynamic.txrecord;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.StatusBarUtil2;
import com.ajiani.maidahui.adapter.dynamic.PictureVpAdapter;
import com.ajiani.maidahui.adapter.dynamic.PopAdapter;
import com.ajiani.maidahui.base.SimpleActivity;

import com.ajiani.maidahui.bean.dynamic.LocalMediaFolder;
import com.ajiani.maidahui.bean.dynamic.PicVideoBean;
import com.ajiani.maidahui.fragment.PicVideoFragment;
import com.ajiani.maidahui.fragment.PictureFragment;
import com.ajiani.maidahui.weight.MyViewPager;
import com.google.android.material.tabs.TabLayout;
import com.luck.picture.lib.config.PictureSelectionConfig;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class PictureSelActivity extends SimpleActivity{

    @BindView(R.id.picturesel_back)
    FrameLayout pictureselBack;
    @BindView(R.id.picture_top)
    ImageView pictureTop;
    @BindView(R.id.picture_sel_vp)
    MyViewPager pictureselVp;
    @BindView(R.id.picture_sel_tab)
    TabLayout pictureselTab;
    @BindView(R.id.picturesel_farm)
    FrameLayout pictureselFarm;
    @BindView(R.id.picturesel_tv)
    TextView pictureselTv;
    @BindView(R.id.picture_video)
    TextView pictureVideo;
    @BindView(R.id.picture_photo)
    TextView picturePhoto;
    @BindView(R.id.picture_lines)
    TextView pictureLines;
    //所选中的图片的公共集合。
    public ArrayList<PicVideoBean> pivcideos=new ArrayList<>();
    private ArrayList<LocalMediaFolder> localMediaFolders;

    ContentResolver contentResolver;
    ArrayList<String> paths = new ArrayList<>();
    List<String> parentDirs = new ArrayList<>();
    List<String> parentImage= new ArrayList<>();
    private PopupWindow popupWindow;
    private PicVideoFragment picVideoFragment;
    private PictureFragment pictureFragment;
    private int left;
    private ObjectAnimator translationX;

    private void getImage() {
        contentResolver = this.getContentResolver();
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        // 获得图片
        Cursor mCursor = contentResolver.query(mImageUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[] { "image/jpeg", "image/png" },MediaStore.Images.Media.DATE_MODIFIED);

        while (mCursor.moveToNext()){
            String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 路径
            paths.add(path);
            //获取到本机中所有的图片后，要对图片进行分类，因此通过路径中的parentDir文件来问分类
            LocalMediaFolder localMediaFolder = new LocalMediaFolder();
            File file = new File(path);
            File parentFile= file.getParentFile();
            String parentFileString = parentFile.getAbsolutePath();
            localMediaFolder.setPath(parentFileString);
            List<String> filesAllName = getFilesAllName(parentFileString);
            String ParentFileName = parentFileString.substring(parentFileString.lastIndexOf("/")+1);
            if (parentDirs.contains(ParentFileName)){
                continue;
            }else {
                parentImage.add(path);
                if(filesAllName!=null){
                    if(filesAllName.size()>0){
                        localMediaFolder.setFirstImagePath(filesAllName.get(filesAllName.size()-1));
                    }else{
                        localMediaFolder.setFirstImagePath(path);
                    }

                    localMediaFolder.setName(ParentFileName);
                    localMediaFolder.setImageNum(filesAllName.size());
                    parentDirs.add(ParentFileName);
                    localMediaFolders.add(localMediaFolder);
                }
            }
        }
        localMediaFolders.get(0).setName("相机胶卷");
        mCursor.close();
    }


    @Override
    protected void initData() {
        pictureselTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==0){
                    if(pivcideos.size()==0){
                        pictureselVp.setCurrentItem(0);
                    }
                }else{
                    if(pivcideos.size()==0){
                        pictureselVp.setCurrentItem(1);
                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }




    protected PictureSelectionConfig config;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initView() {


        getWindow().setExitTransition(new Slide());
        getWindow().setEnterTransition(new Slide());

        StatusBarUtil2.setStatusBarMode(this,true,R.color.black);


        localMediaFolders = new ArrayList<>();
        getImage();

        ArrayList<String> strings = new ArrayList<>();
        strings.add("视频");
        strings.add("图片");
        ArrayList<Fragment> fragments = new ArrayList<>();
        picVideoFragment = new PicVideoFragment(localMediaFolders.get(0).getPath());
        pictureFragment = new PictureFragment(localMediaFolders.get(0).getPath());
        fragments.add(picVideoFragment);
        fragments.add(pictureFragment);
        PictureVpAdapter pictureVpAdapter = new PictureVpAdapter(getSupportFragmentManager(), fragments, strings);
        pictureselVp.setAdapter(pictureVpAdapter);
        pictureselTab.addTab(pictureselTab.newTab().setText("视频"), true);
        pictureselTab.addTab(pictureselTab.newTab().setText("照片"));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) picturePhoto.getLayoutParams();
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) pictureVideo.getLayoutParams();
        left = layoutParams.leftMargin-layoutParams1.leftMargin;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) pictureLines.getLayoutParams();
        layoutParams2.leftMargin=layoutParams1.leftMargin;
        pictureLines.setLayoutParams(layoutParams2);
    }

    boolean isShow=false;

    @Override
    protected int createLayout() {
        return R.layout.activity_pictures;
    }

    @OnClick({R.id.picturesel_back,R.id.picture_photo,R.id.picture_video, R.id.picturesel_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.picturesel_back:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }else{
                    finish();
                }
                break;

            case R.id.picturesel_tv:
                //弹窗
                if(isShow){
                    isShow=false;
                    pictureselBack.setVisibility(View.VISIBLE);
                    popupWindow.dismiss();
                }else{
                    isShow=true;
                    pictureselBack.setVisibility(View.GONE);
                    showPop();
                }

                break;
            case R.id.picture_photo:

                 translationX = ObjectAnimator.ofFloat(pictureLines, "translationX", left);
                translationX.setDuration(250);
                translationX.start();
                if(pivcideos.size()==0){
                    pictureVideo.setTextColor(getResources().getColor(R.color.mine_unselect));
                    picturePhoto.setTextColor(getResources().getColor(R.color.black));
                    pictureselVp.setCurrentItem(1);
                }else{
                    Toast.makeText(this, "暂不支持照片视频混选", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.picture_video:

                translationX = ObjectAnimator.ofFloat(pictureLines, "translationX",-left);
                translationX.setDuration(250);
                translationX.start();
                if(pivcideos.size()==0){
                    pictureVideo.setTextColor(getResources().getColor(R.color.black));
                    picturePhoto.setTextColor(getResources().getColor(R.color.mine_unselect));
                    pictureselVp.setCurrentItem(0);
                }else{
                    Toast.makeText(this, "暂不支持照片视频混选", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void showPop() {
        Animation anim =new RotateAnimation(180f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true); // 设置保持动画最后的状态
        anim.setDuration(250); // 设置动画时间
        anim.setInterpolator(new AccelerateInterpolator()); // 设置插入器


        popupWindow = new PopupWindow();
        View inflate = LayoutInflater.from(this).inflate(R.layout.picture_item, null, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.picture_rel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.PopupWindowStoreStyle);
        popupWindow.setContentView(inflate);
        PopAdapter popAdapter = new PopAdapter(localMediaFolders);
        recyclerView.setAdapter(popAdapter);
        popAdapter.setOnClickLinstener(new PopAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion) {


                //点击传回路径，查询下级的文件
                pictureFragment.setPath(popAdapter.mList.get(posstion).getPath());
                picVideoFragment.setPath(popAdapter.mList.get(posstion).getPath());
                pictureselTv.setText(popAdapter.mList.get(posstion).getName());
                pictureTop.startAnimation(anim);
                popupWindow.dismiss();
            }
        });
        //pop弹出的方法
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int[] location = new int[2];
            pictureselFarm.getLocationOnScreen(location);
            popupWindow.showAtLocation(pictureselFarm, Gravity.NO_GRAVITY,0,location[0]+pictureselFarm.getHeight());
        } else {
            popupWindow.showAsDropDown(pictureselFarm);
        }
     //   popupWindow.showAtLocation(pictureselFarm, Gravity.NO_GRAVITY,0,location[0]+pictureselFarm.getHeight());


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public  List<String> getFilesAllName(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){Log.e("error","空目录");return null;}
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            if(files[i].getName().endsWith(".jpg")||files[i].getName().endsWith(".jpeg")||files[i].getName().endsWith(".png")||files[i].getName().endsWith(".mp4")){
                s.add(files[i].getAbsolutePath());
            }

        }
        return s;
    }


}



