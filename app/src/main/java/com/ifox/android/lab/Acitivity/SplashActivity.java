package com.ifox.android.lab.Acitivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ifox.android.lab.R;

/**
 * 启动应用，设置停留时间，使mainactivity有时间获取服务器数据
 */
public class SplashActivity extends Activity{

    private final int SPLASH_DISPLAY_LENGHT = 1000*3; // 延迟1秒

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}