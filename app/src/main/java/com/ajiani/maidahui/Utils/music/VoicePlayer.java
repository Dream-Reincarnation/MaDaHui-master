package com.ajiani.maidahui.Utils.music;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

public class VoicePlayer implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    public MediaPlayer mediaPlayer;
    private boolean isCompletion = false;
    private boolean isPrepared = false;

    public VoicePlayer() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {

        }
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    /**
     * 播放
     */
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void playUrl(String videoUrl) {
        try {
            isCompletion = false;
            mediaPlayer.reset();
            mediaPlayer.setDataSource(videoUrl);
            mediaPlayer.prepare();
        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停
     */
    public void pause() {
        mediaPlayer.pause();
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 重播
     */
    public void replay() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
        }
    }

    @Override
    public void onPrepared(MediaPlayer arg0) {
        isPrepared = true;
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        isCompletion = true;
    }

    /**
     * 是否准备完毕
     */
    public boolean isPrepared() {
        return isPrepared;
    }

    /**
     * 是否播放完毕
     */
    public boolean isCompletion() {
        return isCompletion;
    }
}
