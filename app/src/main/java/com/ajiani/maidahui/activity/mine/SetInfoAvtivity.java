package com.ajiani.maidahui.activity.mine;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.draw.DrawUtils;
import com.ajiani.maidahui.Utils.photo.PictureUtils;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.UpLoadBean;
import com.ajiani.maidahui.mInterface.mine.UpdataSetin;
import com.ajiani.maidahui.presenters.mine.SetInfoPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fule.com.mydatapicker.DataPickerDialog;
import fule.com.mydatapicker.DatePickerDialog;
import fule.com.mydatapicker.DateUtil;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.http.POST;

public class SetInfoAvtivity extends BaseActivity<UpdataSetin.upSetView, SetInfoPresenter> implements UpdataSetin.upSetView, EasyPermissions.PermissionCallbacks, View.OnClickListener {

    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.setinfo_nickname)
    EditText setinfoNickname;
    @BindView(R.id.setinfo_height)
    TextView setinfoHeight;
    @BindView(R.id.setinfo_weight)
    TextView setinfoWeight;
    @BindView(R.id.setinfo_birther)
    TextView setinfoBirther;
    @BindView(R.id.setinfo_sign)
    EditText setinfoSign;
    @BindView(R.id.setinfo_save)
    TextView setinfoSave;
    @BindView(R.id.setinfo_head)
    ImageView setinfohead;
    @BindView(R.id.setinfo_boy)
    ImageView setinfoboy;
    @BindView(R.id.setinfo_girl)
    ImageView setinfogirl;

    private ArrayList<String> strings;
    private ArrayList<String> list;
    private String TAG = "wxy";
    private int isSel;

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {
        strings = new ArrayList<>();
        for (int i = 20; i < 240; i++) {
            strings.add(i + " cm");
        }
        list = new ArrayList<>();
        for (int i = 20; i < 240; i++) {
            list.add(i + " kg");
        }

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
    }

    @Override
    protected void initView() {
        String weight = getSp("weight");
        String height = getSp("height");
        String name = getSp("name");
        String birthday = getSp("birthday");
        String sign = getSp("sign");
        String head = getSp("head");
        String sex = getSp("sex");
        Glide.with(this).load(head).apply(new RequestOptions().circleCrop()).into(setinfohead);
        setinfoNickname.setText(name);
        setinfoBirther.setText(birthday);
        if (height.equals("0")) {
            setinfoHeight.setText("请设置");
        } else {
            setinfoHeight.setText(height + " cm");
        }
        if (weight.equals("0")) {
            setinfoWeight.setText("请设置");
        } else {
            setinfoWeight.setText(weight + " kg");
        }
        if (sex.equals("1")) {
            isSel=1;
            setinfoboy.setImageResource(R.mipmap.boysel);
            setinfogirl.setImageResource(R.mipmap.girlunsel);
        } else {
            isSel=2;
            setinfoboy.setImageResource(R.mipmap.boyunsel);
            setinfogirl.setImageResource(R.mipmap.girlsel);
        }
        setinfoSign.setText(sign);
    }


    @Override
    protected int createLayout() {
        return R.layout.activity_setinfo;
    }
    String[] psermission = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};

    @OnClick({R.id.title, R.id.back,R.id.setinfo_height,R.id.setinfo_girl,R.id.setinfo_boy,R.id.setinfo_head  ,R.id.setinfo_weight, R.id.setinfo_birther, R.id.setinfo_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setinfo_head:
                //弹窗去选择照片
                if(EasyPermissions.hasPermissions(this,psermission)){
                    //打开相机和录像

                    popupWindow.showAtLocation(setinfoBirther, Gravity.BOTTOM, 0, 0);
                }else{
                    //去申请
                    EasyPermissions.requestPermissions(this,"需要获取你的相机权限",1001,psermission);
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.title:
                title.setText("个人信息");
                break;
            case R.id.setinfo_height:
                //弹出选择框
                showChooseDialog(strings, false);
                break;
            case R.id.setinfo_weight:
                showChooseDialog(list, true);
                break;
            case R.id.setinfo_birther:
                showDateDialog(DateUtil.getDateForString("2000-02-14"));
                break;
            case R.id.setinfo_save:
                //进行网络请求\
                HashMap<String, String> hashMap = new HashMap<>();
                if (setinfoNickname.getText().toString().length() == 0) {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!setinfoNickname.getText().toString().matches("^[\\u2e80-\\u9fffa-zA-Z0-9]{2,7}$")){
                    Toast.makeText(this, "昵称里不可使用标点符号", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    hashMap.put("nickname", setinfoNickname.getText().toString());
                }

                if (setinfoBirther.getText().toString() != null) {
                    hashMap.put("birthday", setinfoBirther.getText().toString());
                }
                if (setinfoWeight.getText().toString() != null) {
                    String weight = setinfoWeight.getText().toString();
                    hashMap.put("weight", weight.substring(0, weight.length() - 3));
                }
                if (setinfoHeight.getText().toString() != null) {
                    String height = setinfoHeight.getText().toString();
                    hashMap.put("height", height.substring(0, height.length() - 3));
                }
                if (setinfoSign.getText().toString().length() ==1) {

                } else {

                }
                hashMap.put("sign", setinfoSign.getText().toString());
                hashMap.put("sex", isSel+"");
                mPresenter.getData(hashMap);
                break;
            case R.id.setinfo_boy:
                isSel=1;
                setinfoboy.setImageResource(R.mipmap.boysel);
                setinfogirl.setImageResource(R.mipmap.girlunsel);
                break;
            case R.id.setinfo_girl:
                isSel=2;
                setinfoboy.setImageResource(R.mipmap.boyunsel);
                setinfogirl.setImageResource(R.mipmap.girlsel);
                break;
        }
    }

    //身高选择框、
    private void showChooseDialog(List<String> mlist, boolean isweight) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        DataPickerDialog.Builder chooseDialog = builder.setData(mlist).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        if (isweight) {
                            setinfoWeight.setText(itemValue);
                        } else {
                            setinfoHeight.setText(itemValue);
                        }
                    }
                    @Override
                    public void onCancel() {

                    }
                });
        if(isweight){
            chooseDialog.setSelection(49);
        }else{
            chooseDialog.setSelection(149);
        }

        chooseDialog.create().show();
    }


    //日期选择框
    private DatePickerDialog dateDialog;

    private void showDateDialog(List<Integer> date) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                setinfoBirther.setText(String.format("%d-%s-%s", dates[0], dates[1] > 9 ? dates[1] : ("0" + dates[1]), dates[2] > 9 ? dates[2] : ("0" + dates[2])));
            }

            @Override
            public void onCancel() {

            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }

    @Override
    protected SetInfoPresenter preparePresenter() {
        return new SetInfoPresenter();
    }

    @Override
    public void success(String success) {

        SPUtils.put(this, "name",setinfoNickname.getText().toString());
     //   SPUtils.put(this, "head", );
        SPUtils.put(this, "sex", isSel+"");
        SPUtils.put(this, "sign", setinfoSign.getText().toString());
        SPUtils.put(this, "height", setinfoHeight.getText().toString().substring(0,setinfoHeight.getText().toString().length()-3));
        SPUtils.put(this, "weight", setinfoWeight.getText().toString().substring(0,setinfoWeight.getText().toString().length()-3));
        SPUtils.put(this, "birthday",setinfoBirther.getText().toString());
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void upLoadSuccess(String success) {
        UpLoadBean upLoadBean = new Gson().fromJson(success, UpLoadBean.class);
        String path = upLoadBean.getData().getPath();
        SPUtils.put(this, "head", path);
        Glide.with(this).load(path).apply(new RequestOptions().circleCrop()).into(setinfohead);
    }

    public String getSp(String type) {
        return (String) SPUtils.get(this, type, "");
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_btn_camera:
                PictureUtils.startCarame(this);
                popupWindow.dismiss();

                break;
            case R.id.icon_btn_select:
                PictureUtils.startPhoto(this,1,false);
                popupWindow.dismiss();
                break;
            case R.id.icon_btn_cancel:
                popupWindow.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> selectList;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        String androidQToPath = selectList.get(0).getAndroidQToPath();
                        File file = new File(androidQToPath);
                        mPresenter.upLoadData(file);
                    } else {
                        String compressPath = selectList.get(0).getCompressPath();
                        //上传图片\
                        File file = new File(compressPath);
                        mPresenter.upLoadData(file);
                    }


                    break;
                case PictureConfig.CAMERA:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        String androidQToPath = selectList.get(0).getAndroidQToPath();
                        File file = new File(androidQToPath);
                        mPresenter.upLoadData(file);
                    } else {
                        String compressPath = selectList.get(0).getCompressPath();
                        //上传图片
                        File file = new File(compressPath);
                        mPresenter.upLoadData(file);
                    }
                    break;
            }
        }
    }
}
