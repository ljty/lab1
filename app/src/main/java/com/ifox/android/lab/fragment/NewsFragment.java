package com.ifox.android.lab.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifox.android.lab.R;
import com.ifox.android.lab.ShowNewsActivity;
import com.ifox.android.lab.adapter.NewsRecycleAdapter;
import com.ifox.android.lab.bean.NewsBean;
import com.ifox.android.lab.utils.NewsUtils;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/20.
 * 公告fragment托管
 */
public class NewsFragment extends Fragment {

    private RecyclerView newsReclv;

    private Context mContext;

    private NewsRecycleAdapter newsRecycleAdapter;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            ArrayList<NewsBean> allNews = (ArrayList<NewsBean>) msg.obj;

            if(allNews != null && allNews .size()>0)
            {
                //3.创建一个adapter设置给listview
                newsRecycleAdapter=new NewsRecycleAdapter(mContext,allNews);
                newsReclv.setAdapter(newsRecycleAdapter);
                newsRecycleAdapter.setOnItemClickListener(new NewsRecycleAdapter.OnRecyclerViewItemClickListener(){
                    @Override
                    public void onItemClick(NewsBean newsBean){
                        Intent intent = new Intent();
                        intent.setClass(mContext, ShowNewsActivity.class);
                        intent.putExtra("n_title", newsBean.n_title);
                        intent.putExtra("n_content", newsBean.n_content);
                        startActivity(intent);
                    }
                });
            }
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, null);

        mContext=getActivity();
        newsReclv=(RecyclerView) v.findViewById(R.id.newsReclv);
        newsReclv.setLayoutManager(new LinearLayoutManager(mContext));

        //1.先去数据库中获取缓存的新闻数据展示到listview
        final ArrayList<NewsBean> allnews_database = NewsUtils.getAllNewsForDatabase(mContext);

        if(allnews_database !=null && allnews_database.size()>0){
            //创建一个adapter设置给listview
            newsRecycleAdapter=new NewsRecycleAdapter(mContext,allnews_database);
            newsReclv.setAdapter(newsRecycleAdapter);
            newsRecycleAdapter.setOnItemClickListener(new NewsRecycleAdapter.OnRecyclerViewItemClickListener(){
                @Override
                public void onItemClick(NewsBean newsBean){
                    Intent intent = new Intent();
                    intent.setClass(mContext, ShowNewsActivity.class);
                    intent.putExtra("n_title", newsBean.n_title);
                    intent.putExtra("n_content", newsBean.n_content);
                    startActivity(intent);
                }
            });
        }

        new Thread(new Runnable() {

            @Override
            public void run() {

                ArrayList<NewsBean> allNews= NewsUtils.getAllNewsForNetWork(mContext);
                //请求网络数据
                //通过handler将msg发送到主线程去更新Ui
                Message msg = Message.obtain();
                msg.obj = allNews;
                handler.sendMessage(msg);

            }
        }).start();

        return v;
    }
}
