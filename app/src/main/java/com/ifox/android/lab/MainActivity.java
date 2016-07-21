package com.ifox.android.lab;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 主活动，管理fragment
 */
public class MainActivity extends Activity {

    private Button mNews;

    private Button mEdu;

    private Button mVideo;

    private Button mUser;

    private NewsFragment nf;

    private EduFragment ef;

    private VideoFragment vf;

    private UserFragment uf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nf=new NewsFragment();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment,nf);
        ft.commit();

        mNews= (Button) findViewById(R.id.news);
        mNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nf=new NewsFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment,nf);
                ft.commit();
            }
        });

        mEdu= (Button) findViewById(R.id.edu);
        mEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ef=new EduFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment,ef);
                ft.commit();
            }
        });

        mVideo= (Button) findViewById(R.id.video);
        mVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf=new VideoFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment,vf);
                ft.commit();
            }
        });

        mUser= (Button) findViewById(R.id.user);
        mUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uf=new UserFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragment,uf);
                ft.commit();
            }
        });
    }
}
