package com.ifox.android.lab.video;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.ifox.android.lab.R;

/**
 * 用于播放本地视频（全屏播放）
 */
public class LocalVideoActivity extends Activity {
    private VideoPlay videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_video);
        videoView = (VideoPlay) findViewById(R.id.common_videoView);
        String videoUri = String.valueOf(Uri.parse(Environment.getExternalStorageDirectory()
                .getPath() + "/test.mp4"));
        videoView.start(videoUri, false);
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            videoView.setFullScreen();
        }


    }


}