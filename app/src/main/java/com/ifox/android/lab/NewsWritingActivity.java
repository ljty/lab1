package com.ifox.android.lab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by 10368 on 2016/7/23.
 */
public class NewsWritingActivity extends Activity{

    private TextView mTitle;

    private TextView mDes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_writing);

        mTitle= (TextView) findViewById(R.id.title);
        mDes= (TextView) findViewById(R.id.des);

        Intent intent=getIntent();
        Bundle bundleNews=intent.getBundleExtra("info");

        mTitle.setText(bundleNews.getString("title"));
        mDes.setText(bundleNews.getString("des"));
    }
}
