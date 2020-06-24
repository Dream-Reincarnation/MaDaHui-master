package com.ajiani.maidahui.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.photo.TakePictureManager;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.UpLoadBean;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.UploadImg;
import com.ajiani.maidahui.presenters.UpLoadPresnter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;



import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class HeadImgActivity extends BaseActivity<UploadImg.upLoadView, UpLoadPresnter> implements UploadImg.upLoadView, View.OnClickListener,EasyPermissions.PermissionCallbacks, TakePictureManager.takePictureCallBackListener {
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.head_take)
    TextView headTake;
    @BindView(R.id.head_lin)
    LinearLayout headlin;
    private PopupWindow popupWindow;
    private  final int TAKE_PICTURE = 3;
    private  final int CHOOSE_PICTURE = 4;
    private  final int CROP_SMALL_PICTURE = 5;

    //TakePhoto

    private Uri imageUri;  //图片保存路径


    @Override
    protected void initData() {
        //获取TakePhoto实例
        /*takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig=new  .Builder().setMaxSize(50*1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig,true);  //设置为需要压缩*/
    }
    /**
     *  获取TakePhoto实例
     * @return
     */




    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String img = bundle.getString("img");
        String headImg = (String) SPUtils.get(this, "head", "");
        Glide.with(this).load(headImg).into(head);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_headimg;
    }

    String[] psermission = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    @OnClick({R.id.head_take, R.id.head_lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_take:
                //检查权限
                popupWindow = new PopupWindow();
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                View inflate = LayoutInflater.from(this).inflate(R.layout.replacephoto, null, false);
                Button bt_camare = inflate.findViewById(R.id.icon_btn_camera);
                Button bt_picture = inflate.findViewById(R.id.icon_btn_select);
                Button bt_cancle = inflate.findViewById(R.id.icon_btn_cancel);
                bt_camare.setOnClickListener(this);
                bt_cancle.setOnClickListener(this);
                bt_picture.setOnClickListener(this);
                popupWindow.setContentView(inflate);
                if(EasyPermissions.hasPermissions(this,psermission)){
                    //打开相机和录像

                    popupWindow.showAtLocation(headlin, Gravity.BOTTOM, 0, 0);
                }else{
                    //去申请
                    EasyPermissions.requestPermissions(this,"需要获取你的相机权限",1001,psermission);
                }
                //弹窗 更换

                break;
            case R.id.head_lin:
                finish();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_btn_camera:
              /*  Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //系统常量， 启动相机的关键
                startActivityForResult(openCameraIntent, 3); // 参数常量为自定义的request code, 在取返回结果*/
                TakePictureManager      takePictureManager = new TakePictureManager(this);
                //开启裁剪 比例 1:3 宽高 350 350  (默认不裁剪)
                takePictureManager.setTailor(1, 1, 400, 400);
                //拍照方式
                takePictureManager.startTakeWayByCarema();
                //回调
                takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
                    @Override
                    public void successful(boolean isTailor, File outFile, Uri filePath) {
                        Log.e("==w",filePath.getPath());
                    }

                    @Override
                    public void failed(int errorCode, List<String> deniedPermissions) {
                        Log.e("==w",errorCode+"");
                    }
                });

              popupWindow.dismiss();

                break;
            case R.id.icon_btn_select:
                showAlbum();
                popupWindow.dismiss();
                break;
            case R.id.icon_btn_cancel:
                popupWindow.dismiss();
                break;
        }
    }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       List<LocalMedia> images;
       if (resultCode == RESULT_OK) {
           if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调
               images = PictureSelector.obtainMultipleResult(data);
               LocalMedia localMedia = images.get(0);
               String path = localMedia.getCompressPath();

               if(path.contains(".JPEG")){
                   String substring = path.substring(0, path.length() - 4);
                   path=substring+"jpeg";
                   Log.i("wxy", "onActivityResult: "+path);
               }
               File file = new File(path);
                              //上传图片
               mPresenter.setData(file);
               //   sendRelAdapter.setData(mList);
               //selectList = PictureSelector.obtainMultipleResult(data);

               // 例如 LocalMedia 里面返回三种path
               // 1.media.getPath(); 为原图path
               // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
               // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
               // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

           }
       }
   }


    private void showAlbum() {
        //参数很多，根据需要添加
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(0)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .previewImage(true)// \
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .rotateEnabled(true) // 裁剪是否可旋转图片
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //省略switch requestCode
        //授权了
        if(perms.get(0).equals(Manifest.permission.CAMERA)&&perms.get(0).equals(Manifest.permission.READ_EXTERNAL_STORAGE)){
            popupWindow.showAtLocation(headlin, Gravity.BOTTOM, 0, 0);
        }

    }

    @Override
    protected UpLoadPresnter preparePresenter() {
        return new UpLoadPresnter();
    }

    @Override
    public void sucess(String string) {
     //
        UpLoadBean upLoadBean = new Gson().fromJson(string, UpLoadBean.class);
        int id = upLoadBean.getData().getId();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("headimgurl",id+"");
        HashMap<String, String> hashMap1 = Params.setParams2();
        hashMap1.putAll(hashMap);
         HashMap<String, String> sign = Params.getSign(hashMap1);
        HttpManager.instance().getServer(MineServer.class)
                .setInfo(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        Toast.makeText(HeadImgActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        SPUtils.put(HeadImgActivity.this,"head",upLoadBean.getData().getPath());
                        finish();
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });

    }

    @Override
    public void error(String error) {

    }




    @Override
    public void successful(boolean isTailor, File outFile, Uri filePath) {
        Log.e("==w",filePath.getPath());
       /* tvShow.setText(filePath.getPath());
        Picasso.with(Activity_TakePhotoActivity.this).load(outFile).error(R.mipmap.ic_launcher).into(ivShow);*/
        mPresenter.setData(outFile);
    }

    @Override
    public void failed(int errorCode, List<String> deniedPermissions) {
        Log.e("==w",deniedPermissions.toString());
    }
}
