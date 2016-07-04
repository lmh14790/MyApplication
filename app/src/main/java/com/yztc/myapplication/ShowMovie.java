package com.yztc.myapplication;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ShowMovie extends AppCompatActivity {
    //@BindView(R.id.myVideo)
    //VideoView myVideo;
    @BindView(R.id.jcVideo)
    JCVideoPlayerStandard jcVideo;
    //@BindView(R.id.my_surfaceView)
    //MySurfaceView mySurfaceView;
    private MediaPlayer player;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmovie);
        ButterKnife.bind(this);
        path = getIntent().getStringExtra("path");
        // play(path);
        jcVideo.setUp(path,"我的视频");
        jcVideo.thumbImageView.setImageURI(Uri.parse(getIntent().getStringExtra("imgpath")));

    }

    /**
     * 播放初始化
     */
    public void play(String path) {
        //点击开始或暂停
        Toast.makeText(this, "开始播放", Toast.LENGTH_SHORT).show();
        player = new MediaPlayer();
        player.reset();
        try {
            player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    return false;
                }
            });
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            player.setDataSource(
                    getApplicationContext(), Uri.parse(path)
            );
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    // player.setDisplay(mySurfaceView.getHolder());
                }
            });
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 停止歌曲
     */
    public void stop() {

        if (player != null) {
            player.stop();
            player.release();
            player = null;
            Toast.makeText(ShowMovie.this, "停止播放", Toast.LENGTH_SHORT).show();
        }
    }

}
