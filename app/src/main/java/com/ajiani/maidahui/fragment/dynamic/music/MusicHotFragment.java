package com.ajiani.maidahui.fragment.dynamic.music;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.DownloadUtil;
import com.ajiani.maidahui.Utils.file.FileUtils;
import com.ajiani.maidahui.Utils.music.VoicePlayer;
import com.ajiani.maidahui.activity.dynamic.MusicActivity;
import com.ajiani.maidahui.adapter.dynamic.music.MusicAdapter;
import com.ajiani.maidahui.base.BaseFragment;
import com.ajiani.maidahui.base.BasePresenterImp;
import com.ajiani.maidahui.bean.dynamic.music.MusicBean;
import com.ajiani.maidahui.mInterface.dynamic.SearMusic;
import com.ajiani.maidahui.presenters.dynamic.SearMusicPresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MusicHotFragment extends BaseFragment<SearMusic.searchView, SearMusicPresenter> implements SearMusic.searchView{
    @BindView(R.id.music_rel)
    RecyclerView musicRel;
    @BindView(R.id.music_smart)
    SmartRefreshLayout musicSmart;
    private String TAG="wxy";
    private MusicAdapter musicAdapter;
    int page;
    public VoicePlayer voicePlayer;
    private MusicActivity activity;
    boolean isSearch;
    private ArrayList<MusicBean.DataBean> dataBeans;
    private ArrayList<MusicBean.DataBean> data;

    @Override
    public void error(String error) {

    }
    public void search(String search) {


        isSearch = true;
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page", page + "");
        hashMap.put("is_recommend", "1");
        hashMap.put("keyword", search);
        mPresenter.getData(hashMap);
    }

    public void cancle(){

        if(data.size()>0){
            musicAdapter.mList.clear();
            musicAdapter.mList.addAll(data);
            musicAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
        page=0;
        voicePlayer=new VoicePlayer();

            if(musicAdapter!=null){
                ArrayList<MusicBean.DataBean> dataBeans = new ArrayList<>();
                //刷新适配器
                for (int i = 0; i < musicAdapter.mList.size(); i++) {
                    musicAdapter.mList.get(i).setPlay(false);
                }
                dataBeans.addAll(musicAdapter.mList);
               /* musicAdapter.mList.clear();
                musicAdapter.mList.addAll(dataBeans);*/
                musicAdapter=new MusicAdapter(dataBeans);
                musicRel.setLayoutManager(new LinearLayoutManager(getActivity()));
                musicRel.setAdapter(musicAdapter);
                syna(musicAdapter);
            }
           /* if(mPresenter!=null){
                HashMap<String, String> hashMap = new HashMap<>();
                page+=1;
                hashMap.put("page",page+"");
                hashMap.put("is_recommend","0");
                mPresenter.getData(hashMap);
            }*/

        }
    }
    public void syna(MusicAdapter musicAdapter){
        musicAdapter.setOnClickLinstener(new MusicAdapter.onClickLinstener() {
            @Override
            public void onClick(int posstion, View view) {


                //进行收藏
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("music_id",musicAdapter.mList.get(posstion).getMusic_id()+"");
                mPresenter.getCollectionList(hashMap);
            }
        });


        //播放音乐
        musicAdapter.setOnItemClickLinstener(new MusicAdapter.onItemClickLinstener() {
            @Override
            public void onClick(int posstion,int pos) {



                if(pos!=posstion){
                    if(voicePlayer.isPlaying()){
                        voicePlayer.pause();
                    }
                    String url = musicAdapter.mList.get(posstion).getUrl();

                    voicePlayer.playUrl(url);
                    voicePlayer.play();
                }else{
                    boolean play = musicAdapter.mList.get(posstion).isPlay();
                    if(play){
                        voicePlayer.play();
                    }else{
                        voicePlayer.pause();
                    }
                }
                //  musicAdapter.notifyDataSetChanged();
            }
        });
        musicAdapter.setOnDownClickLinstener(new MusicAdapter.onDownClickLinstener() {
            @Override
            public void onClick(int posstion) {
                //显示进度条
                activity.musicLinPop.setVisibility(View.VISIBLE);
                DownloadUtil.get().download(musicAdapter.mList.get(posstion).getUrl(), FileUtils.getCache(activity) ,"music_"+musicAdapter.mList.get(posstion).getName()+"_"+musicAdapter.mList.get(posstion).getMusic_id()+".mp3" , new DownloadUtil.DownloadListener() {
                    @Override
                    public void onDownloadSuccess(String path) {

                        Intent intent = activity.getIntent();
                        intent.putExtra("poss", posstion);
                        intent.putExtra("path", path);
                        intent.putExtra("name",musicAdapter.mList.get(posstion).getName()+"-"+musicAdapter.mList.get(posstion).getAuthor());

                        intent.putExtra("thumb",musicAdapter.mList.get(posstion).getThumb());
                        intent.putExtra("musicurl",musicAdapter.mList.get(posstion).getUrl());
                        intent.putExtra("music",musicAdapter.mList.get(posstion).getMusic_id());
                        activity.backpress(intent);
                    }

                    @Override
                    public void onDownloading(int progress) {
                        Log.i(TAG, "onDownloading: "+progress);
                        activity.roundProgress.setProgress(progress);
                    }

                    @Override
                    public void onDownloadFailed() {

                    }
                });

            }
        });
    }
    @Override
    protected void initData() {

        activity= (MusicActivity) getActivity();
        data=new ArrayList<>();
        voicePlayer=new VoicePlayer();
           dataBeans = new ArrayList<>();

        musicAdapter = new MusicAdapter(dataBeans);
        musicRel.setLayoutManager(new LinearLayoutManager(getActivity()));
        musicRel.setAdapter(musicAdapter);

        HashMap<String, String> hashMap = new HashMap<>();
        page+=1;
        hashMap.put("page",page+"");
        hashMap.put("is_recommend","0");
        mPresenter.getData(hashMap);




     syna(musicAdapter);

        //加载
        musicSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page+=1;
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("page",page+"");
                hashMap.put("is_recommend","1");
                mPresenter.getData(hashMap);
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();

        voicePlayer.stop();
    }

    @Override
    protected int createLayout() {
        return R.layout.fragement_musicrecommend;
    }

    @Override
    protected SearMusicPresenter preparePresenter() {
        return new SearMusicPresenter();
    }

    @Override
    public void success(String success) {
       /* musicSmart.finishLoadMore();

        MusicBean musicBean = new Gson().fromJson(success, MusicBean.class);
        List<MusicBean.DataBean> data = musicBean.getData();
        if(page==1){
            musicAdapter.mList.clear();
        }
        musicAdapter.mList.addAll(data);
        musicAdapter.notifyDataSetChanged();*/

        if (isSearch) {
            musicSmart.finishLoadMore();

            MusicBean musicBean = new Gson().fromJson(success, MusicBean.class);
            List<MusicBean.DataBean> data = musicBean.getData();
          /*  if(page==1){
                musicAdapter.mList.clear();
            }*/
            musicAdapter.mList.clear();
            musicAdapter.mList.addAll(data);
            musicAdapter.notifyDataSetChanged();
        } else {
            musicSmart.finishLoadMore();
            MusicBean musicBean = new Gson().fromJson(success, MusicBean.class);
            List<MusicBean.DataBean> data = musicBean.getData();
            if (page == 1) {
                musicAdapter.mList.clear();
            }
            dataBeans.addAll(data);
            this.data.addAll(data);
//            musicAdapter.mList.addAll(data);
            musicAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void successInfo(String string) {

    }

    @Override
    public void colleectioSuccess(String sucess) {

    }

    @Override
    public void getCollectionSuccess(String success) {

    }
}
