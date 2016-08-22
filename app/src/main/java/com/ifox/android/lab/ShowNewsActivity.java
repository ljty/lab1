package com.ifox.android.lab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import static com.ifox.android.lab.R.id.toolbar;

/**
 * Created by 10368 on 2016/7/23.
 */
public class ShowNewsActivity extends AppCompatActivity {

    private TextView n_title;

    private TextView n_content;

    private Context context=this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        //设置返回
        Toolbar mToolbar = (Toolbar) findViewById(toolbar);

        mToolbar.setTitle("lab");
        mToolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//返回箭头
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//      ((TextView) findViewById(R.id.title)).setText(Html.fromHtml(descString(),getImageGetterInstance(),null));

        n_title= (TextView) findViewById(R.id.n_title);
        n_content= (TextView) findViewById(R.id.n_content);

        Intent intent=getIntent();

        n_title.setText(intent.getStringExtra("n_title"));
        n_content.setText(intent.getStringExtra("n_content"));

    }

//    private String descString() {
//        return "您消耗的总热量约等于4杯" + "<img src='" + R.drawable.ic_book_white_24dp
//                + "'/>" + "+5只" + "<img src='" + R.drawable.ic_book_white_24dp
//                + "'/>" + "+10个" + "<img src='"
//                + R.drawable.ic_book_white_24dp + "'/>" + "";
//    }
//
//    public Html.ImageGetter getImageGetterInstance() {
//        Html.ImageGetter imgGetter=new Html.ImageGetter(){
//
//            @Override
//            public Drawable getDrawable(String source) {
//                int fontH=(int)(getResources().getDimension(R.dimen.activity_horizontal_margin)*1.5);
//                int id=Integer.parseInt(source);
//                Drawable d=getResources().getDrawable(id);
//                int height=fontH;
//                int width=(int)((float)d.getIntrinsicWidth()/(float)d.getIntrinsicHeight()*fontH);
//                if (width==0){
//                    width=d.getIntrinsicWidth();
//                }
//                d.setBounds(0,0,width,height);
//                return d;
//            }
//        };
//        return imgGetter;
//    }

}
