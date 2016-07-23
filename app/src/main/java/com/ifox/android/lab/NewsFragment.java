package com.ifox.android.lab;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ifox.android.lab.adapter.NewsAdapter;
import com.ifox.android.lab.bean.NewsBean;
import com.ifox.android.lab.utils.NewsUtils;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/20.
 * 公告fragment托管
 */
public class NewsFragment extends Fragment{

    private ListView mNewslv;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, null);

        mContext=getActivity();
        mNewslv=(ListView)v.findViewById(R.id.newslv);
        ArrayList<NewsBean> allNews= NewsUtils.getAllNews(mContext);
        NewsAdapter newsAdapter=new NewsAdapter(mContext,allNews);
        mNewslv.setAdapter(newsAdapter);
        mNewslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(mContext,NewsWritingActivity.class);
                startActivity(it);
            }
        });

    return v;
    }
}
