package com.example.txrecord.baseic;


import com.example.txrecord.TCVideoFileInfo;

import java.util.ArrayList;

public interface IVideoJoinKit {
    /**
     * 设置多个视频路径
     *
     * @param videoList
     */
    void setVideoJoinList(ArrayList<TCVideoFileInfo> videoList);

    /**
     * 设置视频合成监听器
     *
     * @param videoJoinListener
     */
    void setVideoJoinListener(OnVideoJoinListener videoJoinListener);


    interface OnVideoJoinListener {
        /**
         * 视频合成取消
         */
        void onJoinCanceled();

        /**
         * 视频合成完成
         *
         * @param ugcKitResult
         */
        void onJoinCompleted(UGCKitResult ugcKitResult);
    }
}
