package com.ifox.android.lab.Acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.bean.EduBean;
import com.ifox.android.lab.bean.DataHolder;

import static com.ifox.android.lab.R.id.toolbar;

/**
 * 教学资源详情
 */
public class ShowEduActivity extends AppCompatActivity {

    private TextView et_title;

    private TextView et_content;

    private ImageView et_attachAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_edu);

        toolBar();
        init();
        setContent();
    }

    // 设置标题栏
    private void toolBar() {
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

    // 寻找控件
    private void init() {
        et_title = (TextView) findViewById(R.id.et_title);
        et_content = (TextView) findViewById(R.id.et_content);
        et_attachAddress = (ImageView) findViewById(R.id.et_attachAddress);
    }

    // 为控件设置内容
    private void setContent() {
        EduBean eduBean = DataHolder.getInstance().getEduBeanData();
        et_title.setText(eduBean.et_title);
        et_content.setText(eduBean.et_content);
        et_attachAddress.setImageBitmap(eduBean.et_attachAddress);
    }

}