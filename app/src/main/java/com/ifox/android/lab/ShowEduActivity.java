package com.ifox.android.lab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.ifox.android.lab.R.id.toolbar;

/**
 * Created by 10368 on 2016/7/21.
 * 教学资源详情
 */
public class ShowEduActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_edu);

        //设置返回
        Toolbar mToolbar = (Toolbar) findViewById(toolbar);
        mToolbar.setTitle("lab");
        mToolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}
