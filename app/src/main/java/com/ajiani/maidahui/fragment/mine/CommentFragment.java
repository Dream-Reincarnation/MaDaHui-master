package com.ajiani.maidahui.fragment.mine;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Toast;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.Utils.RxUtils;
import com.ajiani.maidahui.Utils.SPUtils;
import com.ajiani.maidahui.Utils.SoftKeyBoardListener;
import com.ajiani.maidahui.adapter.mine.ItemAdapter;
import com.ajiani.maidahui.bean.mine.CommentListBean;
import com.ajiani.maidahui.bean.mine.CommentListBeans;
import com.ajiani.maidahui.bean.mine.CommentSuccess;
import com.ajiani.maidahui.http.BaseObserver;
import com.ajiani.maidahui.http.HttpManager;
import com.ajiani.maidahui.http.MineServer;
import com.ajiani.maidahui.http.Params;
import com.ajiani.maidahui.mInterface.mine.CommentIn;
import com.ajiani.maidahui.presenters.mine.CommentPresenter;
import com.ajiani.maidahui.weight.dialog.CommentBottomSheetDialogFragment;
import com.ajiani.maidahui.weight.dialog.CommentDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//cc9e
public class CommentFragment extends CommentBottomSheetDialogFragment implements CommentIn.commentView {
    public String id;
    public String comment;
    private ItemAdapter itemAdapter;
    int a = 0;
    private CommentDialog commentDialog;
    ArrayList<ArrayList<CommentListBeans.DataBean>> dataBeans = new ArrayList<>();
    private List<CommentListBeans.DataBean> data;

    public CommentFragment(String id, String comment) {
        this.id = id;
        this.comment = comment;
    }


    private SoftKeyBoardListener.OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener = new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
        @Override
        public void keyBoardShow(int height) {

        }

        @Override
        public void keyBoardHide(int height) {
            // inputDialog.dismiss();
        }
    };
    public void initData() {
        a = 0;
        dataBeans = new ArrayList<>();
        TimerTask task = new TimerTask(){
            public void run(){
                //method
                commentDialog = new CommentDialog("请评论");
                commentDialog.show(getFragmentManager(), "comment");
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,500);

        CommentPresenter commentPresenter = new CommentPresenter();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("video_id", id);
        hashMap.put("page", "1");
        HashMap<String, String> hashMap1 = Params.setParams2();
        hashMap1.putAll(hashMap);
        HashMap<String, String> sign = Params.getSign(hashMap1);
        HttpManager.instance().getServer(MineServer.class)
                .CommentList(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onSuccess(String string) {
                        JSONObject jsonObject = JSON.parseObject(string);
                        String code = jsonObject.getString("data");
                        CommentListBeans commentListBean = new Gson().fromJson(string, CommentListBeans.class);
                        data = commentListBean.getData();
                        itemAdapter.mList.addAll(data);

                        if (data.size() > 0) {
                            initdata(data.get(0).getAid(), 0, a);
                        }
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    private void initdata(int aid, int posstion, int a) {
        a += 1;
        int aa = a;
        if (aa > data.size()) {
            return;
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("video_id", id);
            map.put("quote_id", aid + "");
            map.put("page",  "1");
            HashMap<String, String> hashMap = Params.setParams2();
            hashMap.putAll(map);
            HashMap<String, String> sign = Params.getSign(hashMap);
            HttpManager.instance().getServer(MineServer.class)
                    .CommentList(sign)
                    .compose(RxUtils.rxScheduleThread())
                    .subscribe(new BaseObserver() {
                        @Override
                        protected void onSuccess(String string) {
                            //0,1,2,

                            CommentListBeans commentListBeans = new Gson().fromJson(string, CommentListBeans.class);
                            List<CommentListBeans.DataBean> data1 = commentListBeans.getData();
                            dataBeans.add((ArrayList<CommentListBeans.DataBean>) data1);
                            if (aa == data.size()) {

                            } else {
                                initdata(data.get(aa).getAid(), 0, aa);
                            }

                            Log.i("wxy", "onSuccess:==== " + dataBeans.size());
                            if (dataBeans.size() == itemAdapter.mList.size()) {

                                itemAdapter.list.addAll(dataBeans);
                                itemAdapter.notifyDataSetChanged();
                            }

                        }

                        @Override
                        protected void onError(String string) {

                        }
                    });

        }


    }

    @Override
    public void initView() {
        ArrayList<CommentListBeans.DataBean> dataBeans = new ArrayList<>();
        ArrayList<ArrayList<CommentListBeans.DataBean>> arrayLists = new ArrayList<>();
        HashMap<String, ArrayList<CommentListBeans.DataBean>> map = new HashMap<>();


        ArrayList<ArrayList<CommentListBeans.DataBean>> arrayLists1 = new ArrayList<>();
        itemAdapter = new ItemAdapter(dataBeans, arrayLists1);
        recyclerView.setAdapter(itemAdapter);

        commentnum.setText("全部" + comment + "条评论");

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        SoftKeyBoardListener.setListener(getActivity(), onSoftKeyBoardChangeListener);
        //网络请求

        itemAdapter.setOnClickLinstener(new ItemAdapter.onClickLinstener() {

            @Override
            public void onClick(int posstion) {
                String nickname = itemAdapter.mList.get(posstion).getNickname();
                int user_id = itemAdapter.mList.get(posstion).getUser_id();
                //  Log.i("WXY", "onClick: "+itemAdapter.mList.size()+"===="+posstion);
                int aid = itemAdapter.mList.get(posstion).getAid();
                commentDialog = new CommentDialog("回复@" + nickname, new CommentDialog.SendListener() {
                    @Override
                    public void sendComment(String inputText) {
                        //回复评论
                        relay(posstion, aid, inputText);
                    }
                });
                commentDialog.show(getFragmentManager(), "comment");
            }
        });
        //一级评论点赞
        itemAdapter.setOnLikeClickLinstener(new ItemAdapter.onLikeClickLinstener() {
            @Override
            public void onLikeClick(int posstion) {
                Log.i("WXY", "onLikeClick: " + posstion);
                //进行点赞
                String is_star = itemAdapter.mList.get(posstion).getIs_star();
                int aid = itemAdapter.mList.get(posstion).getAid();
                //进行网络请求
                HashMap<String, String> map = new HashMap<>();
                map.put("comment_id", aid + "");
                HashMap<String, String> hashMap = Params.setParams2();
                hashMap.putAll(map);
                HashMap<String, String> sign = Params.getSign(hashMap);
                HttpManager.instance().getServer(MineServer.class).starComment(sign)
                        .compose(RxUtils.rxScheduleThread())
                        .subscribe(new BaseObserver() {
                            @Override
                            protected void onSuccess(String string) {
                                if (is_star.equals("0")) {
                                    itemAdapter.holder1.commentLike.setImageResource(R.mipmap.like);
                                    itemAdapter.mList.get(posstion).setIs_star("1");

                                    int star = itemAdapter.mList.get(posstion).getStar();
                                    star++;
                                    itemAdapter.mList.get(posstion).setStar(star);
                                    itemAdapter.notifyDataSetChanged();

                                } else {
                                    itemAdapter.mList.get(posstion).setIs_star("0");
                                    int star = itemAdapter.mList.get(posstion).getStar();
                                    star--;
                                    itemAdapter.mList.get(posstion).setStar(star);
                                    itemAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            protected void onError(String string) {

                            }
                        });
            }
        });
        //二级评论点赞


       /* if (itemAdapter.commentItemAdapter!= null) {
            itemAdapter.commentItemAdapter.setOnLikesClickLinstener(new CommentItemAdapter.onLikesClickLinstener() {
                @Override
                public void onClick(int posstion) {

                    String is_star = itemAdapter.commentItemAdapter.mList.get(posstion).getIs_star();
                    int aid = itemAdapter.commentItemAdapter.mList.get(posstion).getAid();
                    if (is_star.equals("0")) {
                        itemAdapter.commentItemAdapter.holder1.like.setImageResource(R.mipmap.like);
                        itemAdapter.commentItemAdapter.mList.get(posstion).setIs_star("1");
                        itemAdapter.commentItemAdapter.notifyDataSetChanged();
                    } else {
                        itemAdapter.commentItemAdapter.holder1.like.setImageResource(R.mipmap.aixin);
                        itemAdapter.commentItemAdapter.mList.get(posstion).setIs_star("0");
                        itemAdapter.commentItemAdapter.notifyDataSetChanged();
                    }
                }
            });
        }*/

        initData();
    }
   //评论回复
    private void relay(int user_id, int aid, String inputText) {
        CommentPresenter commentPresenter = new CommentPresenter();
        HashMap<String, String> hashMap = new HashMap<>();
        Log.i("WXY", "relay: " + id + "===" + aid);
        hashMap.put("video_id", id + "");
        hashMap.put("quote_id", aid + "");
        hashMap.put("content", inputText);
        hashMap.put("score", "5");
        HashMap<String, String> hashMap1 = Params.setParams2();
        hashMap1.putAll(hashMap);
        HashMap<String, String> sign = Params.getSign(hashMap1);
        HttpManager.instance().getServer(MineServer.class)
                .addComment(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {

                    private ArrayList<CommentListBeans.DataBean> dataBeans;

                    @Override
                    protected void onSuccess(String string) {
                        Toast.makeText(getActivity(), "成功离开", Toast.LENGTH_SHORT).show();
                        //显示在页面上
                        dataBeans = new ArrayList<>();
                        CommentListBeans.DataBean dataBean = new CommentListBeans.DataBean();
                        String username = (String) SPUtils.get(getActivity(), "username", "");
                        String head = (String) SPUtils.get(getActivity(), "head", "");
                        String userid1 = (String) SPUtils.get(getActivity(), "userid", "");
                        dataBean.setContent(inputText);
                        dataBean.setIs_star("" + 0);
                        dataBean.setAid(aid);
                        dataBean.setUser_id(Integer.parseInt(userid1));
                        dataBean.setHeadimgurl(head);
                        dataBean.setNickname(username);
                        dataBean.setFormat_create_time("刚刚");
                        if (itemAdapter.list.get(user_id).size() > 0) {
                            dataBeans = itemAdapter.list.get(user_id);
                            dataBeans.add(0, dataBean);
                        } else {
                            dataBeans = new ArrayList<>();
                            dataBeans.add(dataBean);
                            itemAdapter.list.add(dataBeans);
                        }


                        itemAdapter.notifyDataSetChanged();
                        commentDialog.dismiss();
                    }

                    @Override
                    protected void onError(String string) {

                    }
                });
    }

    @Override
    protected void sendComment() {
        //   Toast.makeText(getActivity(), "你点击了", Toast.LENGTH_SHORT).show();
        commentDialog = new CommentDialog("优质评论将会被优先展示", new CommentDialog.SendListener() {
            @Override
            public void sendComment(String inputText) {
                //发表评论

                replayComment(inputText);
            }
        });
        commentDialog.show(getFragmentManager(), "comment");
    }
  //回复评论
    private void replayComment(String inputText) {
        CommentPresenter commentPresenter = new CommentPresenter();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("video_id", id);
        hashMap.put("content", inputText);
        hashMap.put("score", "5");
        HashMap<String, String> hashMap1 = Params.setParams2();
        hashMap1.putAll(hashMap);
        HashMap<String, String> sign = Params.getSign(hashMap1);
        HttpManager.instance().getServer(MineServer.class)
                .addComment(sign)
                .compose(RxUtils.rxScheduleThread())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(String string) {
                        commentDialog.dismiss();
                        String username = (String) SPUtils.get(getActivity(), "username", "");
                        String head = (String) SPUtils.get(getActivity(), "head", "");
                        String userid = (String) SPUtils.get(getActivity(), "userid", "");

                        CommentSuccess commentSuccess = new Gson().fromJson(string, CommentSuccess.class);
                        String aid = commentSuccess.getData().getAid();
                        CommentListBeans.DataBean dataBean = new CommentListBeans.DataBean();
                        dataBean.setAid(Integer.parseInt(aid));
                        dataBean.setContent(inputText);
                        dataBean.setCreate_time("0");
                        dataBean.setFormat_create_time("刚刚");
                        dataBean.setUser_id(Integer.parseInt(userid));
                        dataBean.setIs_star("0");
                        dataBean.setIs_star("0");
                        dataBean.setNickname(username);
                        dataBean.setScore(5);
                        dataBean.setHeadimgurl(head);
                        //刷新适配器
                        //
                        ArrayList<CommentListBeans.DataBean> dataBeans = new ArrayList<>();

                            itemAdapter.list.add(0, dataBeans);
                        itemAdapter.mList.add(0, dataBean);
                        itemAdapter.notifyDataSetChanged();


                        itemAdapter.notifyDataSetChanged();

                    }

                    @Override
                    protected void onError(String string) {

                    }
                });

    }

    //   HashMap<String, String> hashMap1 = Params.setParams2();
//               hashMap1.putAll(hashMap);
//               HashMap<String, String> sign = Params.getSign(hashMap1);
//                HttpManager.instance().getServer(MineServer.)
    private ViewTreeObserver.OnGlobalLayoutListener getGlobalLayoutListener(final View decorView, final View contentView) {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                decorView.getWindowVisibleDisplayFrame(r);

                int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
                int diff = height - r.bottom;

                if (diff != 0) {
                    if (contentView.getPaddingBottom() != diff) {
                        contentView.setPadding(0, 0, 0, diff);
                    }
                } else {
                    if (contentView.getPaddingBottom() != 0) {
                        contentView.setPadding(0, 0, 0, 0);
                    }
                }
            }
        };


    }

    @Override
    public void commentListSuccess(String success) {

    }

    @Override
    public void addComment(String success) {

    }

    @Override
    public void error(String error) {

    }

    @Override
    public void showprogress() {

    }

    @Override
    public void dissprogress() {

    }


}
