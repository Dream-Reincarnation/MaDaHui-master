package com.ajiani.maidahui.activity.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.activity.MyApp;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.base.BasePresenterImp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindWechatActivity extends BaseActivity {


    @BindView(R.id.back)
    FrameLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bind_tv)
    TextView bind_tv;
    @BindView(R.id.bind_wechat_copy)
    TextView bindWechatCopy;
    @BindView(R.id.bind_wechat_bt)
    TextView bindWechatBt;
    @BindView(R.id.bind_wechat_tips)
    LinearLayout bindWechatTips;
    private String TAG="wxy";

    @Override
    protected BasePresenterImp preparePresenter() {
        return null;
    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        int wechat = bundle.getInt("wehcat");
        title.setText("绑定公众号");
        Log.i(TAG, "initView: "+wechat);
        if (wechat == 0) {
            //绑定
            bindWechatBt.setText("保存二维码");
            bind_tv.setVisibility(View.GONE);
            bindWechatTips.setVisibility(View.VISIBLE);
        } else {
            //未绑定
            bindWechatBt.setText("保存分享好友");
            bind_tv.setVisibility(View.VISIBLE);
            bindWechatTips.setVisibility(View.VISIBLE );
        }


    }

    @Override
    protected int createLayout() {
        return R.layout.activity_bindwechat;
    }


    public void saveFile(Bitmap bmp) throws IOException {
        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;


        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;
        String fileNames = UUID.randomUUID().toString() + ".jpg";

        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, fileNames);

            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//通知相册更新
        MediaStore.Images.Media.insertImage(getContentResolver(),
                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        sendBroadcast(intent);
    }

    @OnClick({R.id.back, R.id.bind_wechat_copy, R.id.bind_wechat_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bind_wechat_copy:
                ClipboardManager cm = (ClipboardManager) MyApp.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", "麦达汇");
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bind_wechat_bt:
                //保存本地
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.wechat_qrcode);
                try {
                    saveFile(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
