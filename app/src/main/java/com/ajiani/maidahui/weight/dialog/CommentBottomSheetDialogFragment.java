package com.ajiani.maidahui.weight.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajiani.maidahui.R;
import com.ajiani.maidahui.bean.mine.CommentListBean;
import com.ajiani.maidahui.presenters.mine.CommentPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;


/**
 * @Author：淘跑
 * @Date: 2018/8/28  17:04
 * @Use：
 */
public abstract class CommentBottomSheetDialogFragment extends BottomSheetDialogFragment {

//    public InputDialog inputDialog;
    private int keyboardHeight;
    public RecyclerView recyclerView;
    public TextView commentnum;
    public LinearLayout lin;
    private onClickLinstener OnClick;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //给dialog设置主题为透明背景 不然会有默认的白色背景
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 在这里将view的高度设置为精确高度，即可屏蔽向上滑动不占全屏的手势。
        //如果不设置高度的话 会默认向上滑动时dialog覆盖全屏
        View view = inflater.inflate(R.layout.dialog_comment, container, false);

        commentnum = view.findViewById(R.id.commentnum);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getScreenHeight(getActivity()) / 3*2));

        return view;
    }

    public abstract void initView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      //  recyclerView.setAdapter(new ItemAdapter());
        view.findViewById(R.id.comment_ed);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendComment();
            }
        });

        initView();
    }

    protected abstract void sendComment();

  /*  public class CommentItemAdapter extends RecyclerView.Adapter<CommentItemAdapter.ViewHolder>{

       public int a=5;
        private Context context;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            View inflate = LayoutInflater.from(context).inflate(R.layout.comment_item2, parent, false);
            return new ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context).load(R.mipmap.photo).apply(new RequestOptions().circleCrop()).into(holder.head);
        }

        @Override
        public int getItemCount() {
            return a;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private final ImageView head;
            private final TextView name;
            private final TextView context;
            private final TextView likeNum;
            private final ImageView like;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                head = itemView.findViewById(R.id.commitem_head);
                name = itemView.findViewById(R.id.commitem_name);
                context = itemView.findViewById(R.id.commitem_text);
                likeNum = itemView.findViewById(R.id.comment_likenum);
                like = itemView.findViewById(R.id.comment_like);
            }
        }

    }
    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

        private Context context;
        public ArrayList<CommentListBean.DataBean> mList;
        private onClickLinstener OnClick;


        public ItemAdapter(ArrayList<CommentListBean.DataBean> mList) {
            this.mList = mList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            context = parent.getContext();
            return new MyViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }



        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            Glide.with(context).load(mList.get(position).getHeadimgurl()).apply(new RequestOptions().circleCrop()).into(holder.commentImg);
             holder.commentRell.setLayoutManager(new LinearLayoutManager(context));
            holder.commentName.setText(mList.get(position).getNickname());
            holder.commentTime.setText(mList.get(position).getCreate_time());
            holder.commentContext.setText(mList.get(position).getContent());
            holder.commentLikenum.setText(mList.get(position).getStar()+"");
            CommentItemAdapter commentItemAdapter = new CommentItemAdapter();
            holder.lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(OnClick!=null){
                        OnClick.onClick(1);
                    }
                }
            });

            holder.commentRell.setAdapter(commentItemAdapter);
            holder.unFold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      //展开十条回复
                    commentItemAdapter.a+=10;
                    commentItemAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
//            @BindView(R.id.comment_img)
            public ImageView
        commentImg;
//            @BindView(R.id.comment_name)
            public TextView commentName;
//            @BindView(R.id.comment_time)
            public TextView commentTime;
//            @BindView(R.id.comment_like)
            public ImageView commentLike;
//            @BindView(R.id.comment_likenum)
            public TextView commentLikenum;
//            @BindView(R.id.comment_context)

            public TextView commentContext;
//            @BindView(R.id.comment_rell)
            public RecyclerView commentRell;
            private final TextView unFold;
            private final LinearLayout lin;

            //            final TextView text;
            MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.recycle_item_list_dialog, parent, false));
          //      text = (TextView) itemView.findViewById(R.id.text);
                commentContext=itemView.findViewById(R.id.comment_context);
                commentRell=itemView.findViewById(R.id.comment_rell);
                commentLikenum=itemView.findViewById(R.id.comment_likenum);
                commentLike=itemView.findViewById(R.id.comment_like);
                commentTime=itemView.findViewById(R.id.comment_time);
                commentName=itemView.findViewById(R.id.comment_name);
                commentImg=itemView.findViewById(R.id.comment_img);
                lin = itemView.findViewById(R.id.comment_item);

                unFold = itemView.findViewById(R.id.comment_unfold);
            }
        }



    }*/

     public interface onClickLinstener{
             void onClick(int posstion);
         }
         public void setOnClickLinstener(onClickLinstener onClickLinstener){
             this.OnClick=onClickLinstener;
         }

    /**
     * 如果想要点击外部消失的话 重写此方法
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //设置点击外部可消失
        dialog.setCanceledOnTouchOutside(true);
        //设置使软键盘弹出的时候dialog不会被顶起
        Window win = dialog.getWindow();
        WindowManager.LayoutParams params = win.getAttributes();
        win.setSoftInputMode(params.SOFT_INPUT_ADJUST_NOTHING);

        //这里设置dialog的进出动画
        win.setWindowAnimations(R.style.DialogBottomAnim);
        return dialog;
    }

    /**
     * 得到屏幕的高
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
}