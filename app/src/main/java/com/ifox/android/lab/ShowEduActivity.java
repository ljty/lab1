package com.ifox.android.lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import static com.ifox.android.lab.R.id.toolbar;

/**
 * 教学资源详情
 */
public class ShowEduActivity extends AppCompatActivity {

    private TextView et_title;

    private TextView et_content;

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

        et_title = (TextView) findViewById(R.id.et_title);
        et_content = (TextView) findViewById(R.id.et_content);

        Intent intent = getIntent();

        et_title.setText(intent.getStringExtra("n_title"));
        et_content.setText(intent.getStringExtra("n_content"));

    }
}