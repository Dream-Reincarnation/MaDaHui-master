package com.ajiani.maidahui.activity.dynamic;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.JumpUtils;
import com.ajiani.maidahui.Utils.Md5Utils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.activity.HomeActivity;
import com.ajiani.maidahui.adapter.dynamic.ReleaseAdapter;
import com.ajiani.maidahui.base.BaseActivity;
import com.ajiani.maidahui.bean.Even;
import com.ajiani.maidahui.bean.ListBean;
import com.ajiani.maidahui.bean.UpLoadBean;
import com.ajiani.maidahui.bean.dynamic.shop.CommodityInfo;
import com.ajiani.maidahui.bean.dynamic.OSSVideobean;
import com.ajiani.maidahui.bean.dynamic.SendSuccBean;
import com.ajiani.maidahui.bean.dynamic.UploadVideoBean;
import com.ajiani.maidahui.bean.dynamic.VideoAleary;
import com.ajiani.maidahui.mInterface.dynamic.ReleaseMin;
import com.ajiani.maidahui.presenters.dynamic.ReleasePresenter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ReleaseActivity extends BaseActivity<ReleaseMin.releaseView, ReleasePresenter> implements ReleaseMin.releaseView {
    @BindView(R.id.release_rele)
    TextView releaseRele;
    @BindView(R.id.release_rel)
    RecyclerView releaseRel;
    @BindView(R.id.release_text)
    EditText releaseText;
    @BindView(R.id.release_title)
    TextView releaseTitle;
    @BindView(R.id.friends)
    TextView friends;
    @BindView(R.id.release_location)
    TextView releaseLocation;
    @BindView(R.id.release_lin1)
    LinearLayout releaseLin1;
    @BindView(R.id.release_mode)
    TextView releaseMode;
    @BindView(R.id.release_lin2)
    LinearLayout releaseLin2;
    @BindView(R.id.release_savelin)
    LinearLayout releaseSavelin;
    @BindView(R.id.release_btsend)
    Button releaseBtsend;
    StringBuffer mText = new StringBuffer();
    private String photo2;
    private String photo3;
    private byte[] bytes;
    private ArrayList<byte[]> string;
    private byte[] bytesByFile;
    private byte[] bytesByFile1;
    private byte[] bytesByFile2;
    private byte[] byte1;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> images = new ArrayList<String>();
    private String musicid;
    private String topic;
    private int duration;
    private File file;
    private String string1;
    private String photo1;
    ArrayList<CommodityInfo> commodityInfos = new ArrayList<>();
    @Override
    public void showprogress() {
        super.showprogress();
    }

    @Override
    public void dissprogress() {
        super.dissprogress();
    }

    @Override
    protected void initData() {

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(Even text) {
        switch (text.getCode()) {
            case 1:
                switch (Integer.parseInt(text.getText())) {
                    case 0:
                        releaseMode.setText("公开");
                        break;
                    case 1:
                        releaseMode.setText("关注");
                        break;
                    case 2:
                        releaseMode.setText("私密");
                        break;
                }

                break;
            case 2:
                mText=new StringBuffer();
                String s = releaseText.getText().toString();
                topic=text.getText();

                mText.append(topic+" ");
                mText.append(s);
                releaseText.setText(mText.toString());
                break;
        }

    }

    @Override
    protected void initView() {

        //得到 存储的东西

        getSp();

        releaseRel.setLayoutManager(new GridLayoutManager(this, 3));
        releaseText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (releaseText.getText().toString().length() == 60) {
                    Toast.makeText(ReleaseActivity.this, "最多只能写60个字", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (releaseText.getText().toString().length() == 60) {
                    Toast.makeText(ReleaseActivity.this, "最多只能写60个字", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ArrayList<String> strings = new ArrayList<>();
        string = new ArrayList<>();
        //string.add(bytes);
        ReleaseAdapter releaseAdapter = new ReleaseAdapter(images);
        releaseRel.setAdapter(releaseAdapter);
    }

    private void getSp() {
        photo1 = (String) SPUtils.get(this, "photo1", "");
        photo2 = (String) SPUtils.get(this, "photop", "");
        //    bytes = (byte[]) SPUtils.get(this, "photo1", "");
        photo3 = (String) SPUtils.get(this, "photo3", "");



        musicid = (String) SPUtils.get(this, "music", "");
        if(photo1.length()>3){
            bytesByFile = getBytesByFile(photo1);
            images.add(photo1);
        }
        if (photo2.length() > 3) {
            bytesByFile = getBytesByFile(photo2);
            images.add(photo2);
        }
        if (photo3.length() > 3) {
            bytesByFile1 = getBytesByFile(photo3);
            images.add(photo3);
        }

    }

    public byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {

                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(ListBean listBean) {
        //上传接口
        int id = listBean.getId();
        switch (id) {
            case 1:
                ArrayList<CommodityInfo> list = listBean.getList();
                commodityInfos.addAll(list);
                break;
            case 2:
                ArrayList<CommodityInfo> list1 = listBean.getList();
                commodityInfos.addAll(list1);
                break;
            case 3:
                ArrayList<CommodityInfo> list2 = listBean.getList();
                commodityInfos.addAll(list2);
                break;
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_release;
    }

    @OnClick({R.id.rellase_back, R.id.release_rel, R.id.release_text, R.id.release_title, R.id.friends, R.id.release_lin1, R.id.release_lin2, R.id.release_savelin, R.id.release_btsend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rellase_back:
                finish();
                break;
            case R.id.release_rel:
                break;
            case R.id.release_text:
                break;
            case R.id.release_title:
                //添加标题
                JumpUtils.gotoActivity(this, SelectTalk.class);
                break;
            case R.id.friends:
                //@好友
                JumpUtils.gotoActivity(this, FriendsActivity.class);
                break;
            case R.id.release_lin1:
                //添加位置
                break;
            case R.id.release_lin2:
                //谁可以看
                Intent intent = new Intent(this, PrivateActivity.class);
                startActivityForResult(intent, 1000);
                break;
            case R.id.release_savelin:
                //保存 图片，标题，地址
                break;
            case R.id.release_btsend:
                //网络请求
                //得到内容，
                //上传图片
              //  if (releaseText.getText().toString().length() > 1) {
                    mPresenter.setData(new File(images.get(0)));
               /* } else {
                    Toast.makeText(this, "请填写内容", Toast.LENGTH_SHORT).show();
                }*/


                break;
        }
    }

    @Override
    protected ReleasePresenter preparePresenter() {
        return new ReleasePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    int img = 0;
    public static String getMimeType(String filePath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        String mime = "text/plain";
        if (filePath != null) {
            try {
                mmr.setDataSource(filePath);
                mime = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
            } catch (IllegalStateException e) {
                return mime;
            } catch (IllegalArgumentException e) {
                return mime;
            } catch (RuntimeException e) {
                return mime;
            }
        }
        return mime;
    }
    @Override
    public void success(String string) {
        UpLoadBean upLoadBean = new Gson().fromJson(string, UpLoadBean.class);
        int id = upLoadBean.getData().getId();
        list.add(id + "");

        if (list.size() == this.images.size()) {
            diss();
            Toast.makeText(this, "图片上传成功===" + list.size(), Toast.LENGTH_SHORT).show();
            StringBuffer stringBuffer = new StringBuffer();
            if (list.size() == 1) {
                stringBuffer.append(list.get(0) + "");

            }else{
                for (int i = 0; i < list.size(); i++) {

                    if (i == list.size() - 1) {
                        stringBuffer.append(list.get(i) + "");
                    }else{
                        stringBuffer.append(list.get(i) + ",");
                    }

                }
            }

            string1 = stringBuffer.toString();
            Log.i("WXY", "succffffffffffffess: "+string1);
            boolean b = (boolean) SPUtils.get(this, "luyin", false);
//            Log.i("WXY", "success: "+b);
            if(b){
                //上传录音
                String   mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                mFilePath += "/AudioRecord";
                ArrayList<String> strings = listFiles(new File(mFilePath));
                file = new File(strings.get(1));
                String md5 = Md5Utils.getMD5(file);
                //得到时间
                long length = file.length();
                String name = file.getName();
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(strings.get(1));
                    mediaPlayer.prepare();
                    duration = mediaPlayer.getDuration()/1000;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("filemd5",md5);
            hashMap.put("filesize",length+"");
            hashMap.put("filename",name);
            hashMap.put("filetype","audio/"+getMimeType(strings.get(1)));
            hashMap.put("filetime",duration+"");
                 mPresenter.upload(hashMap);
            }else{
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("thumb", list.get(0) + "");
                hashMap.put("video_type","image");
                hashMap.put("pictrue", stringBuffer.toString());
                if(musicid.length()>=2){
                    hashMap.put("music", musicid);
                }
                //添加话题
                if(topic!=null){
                    hashMap.put("topic",topic);
                }

                //获取内容
                String s = releaseText.getText().toString();
                String[] s1 = s.split(" ");

                hashMap.put("title", s1[s1.length-1]);
                //获取谁可见
                String mode = releaseMode.getText().toString();
                int mode1 = getMode(mode);
                hashMap.put("role", mode1+"");
                if(commodityInfos.size()>0){
                    mPresenter.getData2(hashMap,commodityInfos);
                }else{
                    mPresenter.getData(hashMap);
                }

                return;
            }

        } else {
            img++;
            mPresenter.setData(new File(images.get(img)));

        }

    }
    public  ArrayList<String> listFiles(File dir) {
        ArrayList<String> strings = new ArrayList<>();
        if (!dir.exists() || !dir.isDirectory())
            return strings;
        String[] files = dir.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(dir, files[i]);
            if (file.isFile()) {
                String absolutePath = file.getAbsolutePath();
                strings.add(absolutePath);

            } else {


                listFiles(file);
            }
        }
        return strings;
    }
    private int getMode(String mode) {
        if(mode.equals("公开")){
            return 0;
        }else if(mode.equals("关注")){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
    public void sendSuccess(String success) {
        SendSuccBean sendSuccBean = new Gson().fromJson(success, SendSuccBean.class);
        Toast.makeText(this, "成功了", Toast.LENGTH_SHORT).show();
        releaseBtsend.setEnabled(false);
        JumpUtils.gotoActivity(this, HomeActivity.class);
    }

    @Override
    public void uploadSuccess(String success) {
        JSONObject jsonObject = JSON.parseObject(success);
        String code = jsonObject.getString("code");
        if(code.equals("304")){
            VideoAleary videoAleary = new Gson().fromJson(success, VideoAleary.class);
            int id = videoAleary.getData().getId();
            String path = videoAleary.getData().getPath();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("thumb", list.get(0) + "");
            hashMap.put("video_type","image");
            hashMap.put("pictrue", string1);
            hashMap.put("audio", id+"");
            if(musicid.length()>=2){
                hashMap.put("music", musicid);
            }
            //添加话题
            if(topic!=null){
                hashMap.put("topic",topic);
            }

            //获取内容
            String s = releaseText.getText().toString();
            hashMap.put("title", s);
            //获取谁可见
            String mode = releaseMode.getText().toString();
            int mode1 = getMode(mode);
            Log.i("WXY", "uploadSuccess: "+mode1);
            hashMap.put("role", mode1+"");
            if(commodityInfos.size()>0){
                mPresenter.getData2(hashMap,commodityInfos);
            }else{
                mPresenter.getData(hashMap);
            }



        }else{
            UploadVideoBean uploadVideoBean = new Gson().fromJson(success, UploadVideoBean.class);
            UploadVideoBean.DataBean data = uploadVideoBean.getData();
            UploadVideoBean.DataBean.AliyunDataBean aliyunData = data.getAliyunData();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("key",aliyunData.getKey());
            hashMap.put("policy",aliyunData.getPolicy());
            hashMap.put("OSSAccessKeyId",aliyunData.getOSSAccessKeyId());
            hashMap.put("success_action_status",aliyunData.getSuccess_action_status()+"");
            hashMap.put("signature",aliyunData.getSignature());
            hashMap.put("callback",aliyunData.getCallback());
            mPresenter.ossVideo(hashMap,file);
        }

    }

    @Override
    public void ossSuccess(String success) {
        OSSVideobean ossVideobean = new Gson().fromJson(success, OSSVideobean.class);
        Toast.makeText(this, "上传录音成功", Toast.LENGTH_SHORT).show();
        String path = ossVideobean.getData().getPath();
//        Log.i("WXY", "ossSuccess: "+path);
        String id = ossVideobean.getData().getId();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("thumb", list.get(0) + "");
        hashMap.put("video_type","image");
        hashMap.put("pictrue", string1);
        hashMap.put("audio", id+"");
        if(musicid.length()>=2){
            hashMap.put("music", musicid);
        }
        //添加话题
        if(topic!=null){
            hashMap.put("topic",topic);
        }

        //获取内容
        String s = releaseText.getText().toString();
        hashMap.put("title", s);
        //获取谁可见
        String mode = releaseMode.getText().toString();
        int mode1 = getMode(mode);
        hashMap.put("role", mode1+"");
        if(commodityInfos.size()>0){
            mPresenter.getData2(hashMap,commodityInfos);
        }else{
            mPresenter.getData(hashMap);
        }


    }

    @Override
    public void error(String error) {

    }
}

