package com.example.administrator.mycreater;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private MediaPlayer mediaPlayer;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        videoView = findViewById(R.id.test_video);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {

            // initMediaPlayer(); // 初始化MediaPlayer
            initVideoPath();//初始化videoView
        }
    }

    // 初始化MediaPlayer
/*    private void initMediaPlayer() {
        try {
*//*            File file = new File(Environment.getExternalStorageDirectory(), "fangyuan.mp3");
            if(!file.exists()){

                Toast.makeText(MainActivity.this,"不存在",Toast.LENGTH_SHORT).show();
            }
            mediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径*//*


         mediaPlayer = MediaPlayer.create(this,R.raw.test);
           // mediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/





    private void initVideoPath() {
/*        File file = new File(Environment.getExternalStorageDirectory(), "videotest.mp4");
        if (!file.exists()) {

            Toast.makeText(MainActivity.this, "不存在", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(MainActivity.this, "存在", Toast.LENGTH_SHORT).show();
        }
       videoView.setVideoPath(file.getPath());//设置指定视频文件的路径*/

     videoView.setVideoPath("android.resource://"+getPackageName()+"/raw/videotest");
     videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
         @Override
         public void onPrepared(MediaPlayer mp) {
             mp.start();

         }
     });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // initMediaPlayer();
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
/*            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start(); // 开始播放
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause(); // 暂停播放
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset(); // 停止播放
                    initMediaPlayer();
                }
                break;*/
            case R.id.play:
                if (!videoView.isPlaying()) {
                    videoView.start(); // 开始播放
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause(); // 暂停播放
                }
                break;
            case R.id.stop:
                if (videoView.isPlaying()) {
                    videoView.resume(); // 重新播放

                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
/*        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }*/

        if (videoView != null) {
            videoView.suspend();

        }
    }

}
