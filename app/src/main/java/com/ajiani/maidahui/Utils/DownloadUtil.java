package com.ajiani.maidahui.Utils;

import android.os.Environment;
import android.util.Log;


import androidx.annotation.NonNull;

import com.ajiani.maidahui.Utils.file.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vinsonswang on 2018/7/6.
 */

public class DownloadUtil {
    private static DownloadUtil instance;
    private OkHttpClient okHttpClient;

    public static DownloadUtil get(){
        if(instance == null){
            synchronized (DownloadUtil.class){
                if(instance == null){
                    instance = new DownloadUtil();
                }
            }
        }
        return instance;
    }
    private DownloadUtil(){
        okHttpClient = new OkHttpClient();
    }

    public void download(final String url, final String saveDir, String name,final DownloadListener downloadListener){
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                downloadListener.onDownloadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                //


//                String tempPath = saveFolder + File.separator + "temp_" + getNameFromUrl(url);
                String savePath = saveDir +"/"+name;

                try {
//                    FileUtils.deleteFile(tempPath);
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        downloadListener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
                   // FileUtils.fileRename(tempPath, savePath);
                    Log.i("wxy", "onResponse: "+savePath);
                    downloadListener.onDownloadSuccess(savePath);
                } catch (Exception e) {
                    downloadListener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    @NonNull
    public static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface DownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess(String path);

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }


    /**
     * 根据地址获得数据的字节流并转换成大小
     * @param strUrl 网络连接地址
     * @return
     */
    public  String getFileSizeByUrl(String strUrl){
        InputStream inStream=null;
        ByteArrayOutputStream outStream=null;
        String size="";
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            inStream = conn.getInputStream();

            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while( (len=inStream.read(buffer)) != -1 ){
                outStream.write(buffer, 0, len);
            }
            byte[] bt =  outStream.toByteArray();

            if(null != bt && bt.length > 0){
                DecimalFormat df = new DecimalFormat("#.00");
                if (bt.length < 1024) {
                    size = df.format((double) bt.length) + "BT";
                } else if (bt.length < 1048576) {
                    size = df.format((double) bt.length / 1024) + "KB";
                } else if (bt.length < 1073741824) {
                    size = df.format((double) bt.length / 1048576) + "MB";
                } else {
                    size = df.format((double) bt.length / 1073741824) +"GB";
                }
                System.out.println("文件大小=：" + size);
            }else{
                System.out.println("没有从该连接获得内容");
            }
            inStream.close();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(inStream !=null){
                    inStream.close();
                }
                if(outStream !=null){
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

}
