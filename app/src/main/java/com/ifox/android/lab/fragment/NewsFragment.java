package com.ifox.android.lab.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.ShowNewsActivity;
import com.ifox.android.lab.adapter.NewsAdapter;
import com.ifox.android.lab.bean.NewsBean;
import com.ifox.android.lab.utils.NewsUtils;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/20.
 * 公告fragment托管
 */
public class NewsFragment extends Fragment {

    private ListView mNewslv;

    private Context mContext;

    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {

            ArrayList<NewsBean> allNews = (ArrayList<NewsBean>) msg.obj;

            if(allNews != null && allNews .size()>0)
            {
                //3.创建一个adapter设置给listview
                NewsAdapter newsAdapter=new NewsAdapter(mContext,allNews);
                mNewslv.setAdapter(newsAdapter);
            }

        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, null);

        mContext=getActivity();
        mNewslv=(ListView)v.findViewById(R.id.newslv);

        //1.先去数据库中获取缓存的新闻数据展示到listview
        ArrayList<NewsBean> allnews_database = NewsUtils.getAllNewsForDatabase(mContext);

        if(allnews_database !=null && allnews_database.size()>0){
            //创建一个adapter设置给listview
            NewsAdapter newsAdapter = new NewsAdapter(mContext, allnews_database);
            mNewslv.setAdapter(newsAdapter);
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
        mNewslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(mContext,ShowNewsActivity.class);
                NewsBean bean=(NewsBean)parent.getItemAtPosition(position);
                String title = bean.title;
                String des = bean.des;
                Bundle bundleNews=new Bundle();
                bundleNews.putString("title", title);
                bundleNews.putString("des",des);

                it.putExtra("info",bundleNews);
                startActivity(it);
            }
        });

    return v;
    }
}
