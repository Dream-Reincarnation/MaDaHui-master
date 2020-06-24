package com.ajiani.maidahui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DownloadHandlerThread extends HandlerThread {

    private Handler mDownloadHandler;//下载文件的Handler
    private Handler mUiHandler; //处理UI刷新的Handler
    private List<String> listUrls = new ArrayList();
    private Context context;

    public DownloadHandlerThread(String name, Context context) {
        super(name);
        this.context = context;

    }

    public DownloadHandlerThread(String name, int priority) {
        super(name, priority);
    }

    public void setUiHandler(Handler mUiHandler, List listUrls) {
        this.mUiHandler = mUiHandler;
        this.listUrls = listUrls;
    }


    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        //初始化
        mDownloadHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                //在子线程中进行网络请求
                Bitmap bitmap = downloadUrlBitmap(listUrls.get(msg.what));
                //保存相册
                try {
                    saveFile(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
               /* ImageModel imageModel = new ImageModel();
                imageModel.setBitmap(bitmap);
                imageModel.setUrl(listUrls.get(msg.what));
                Message msg1 = new Message();
                msg1.what = msg.what;
                msg1.obj = imageModel;*/
                if (msg.what == listUrls.size() - 1) {
                    //通知主线程去更新UI
                    Message obtain = Message.obtain();
                    obtain.what = 5;
                    mUiHandler.sendMessage(obtain);
                }
            }
        };
        if (mUiHandler == null) {
            throw new NullPointerException("uiHandler is not null");
        }
        for (int i = 0; i < listUrls.size(); i++) {
            //每个1秒去更新图片
            mDownloadHandler.sendEmptyMessage(i);
        }
    }

    /**
     * 保存图片
     *
     * @param
     * @throws IOException
     */
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
        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                bmp, fileName, null);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    private Bitmap downloadUrlBitmap(String urlString) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

}
