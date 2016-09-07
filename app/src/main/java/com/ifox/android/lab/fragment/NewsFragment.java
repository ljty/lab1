package com.ifox.android.lab.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.Acitivity.ShowNewsActivity;
import com.ifox.android.lab.adapter.NewsAdapter;
import com.ifox.android.lab.bean.DataHolder;
import com.ifox.android.lab.bean.NewsBean;
import com.ifox.android.lab.utils.NewsUtils;

import java.util.ArrayList;

/**
 * 公告托管
 */
public class NewsFragment extends Fragment {

    private ListView mNewslv;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, null);

        // 为适配器设置内容
        mContext=getActivity();
        mNewslv=(ListView)v.findViewById(R.id.newslv);
        final ArrayList<NewsBean> allNews_database = NewsUtils.getAllNewsForDatabase(mContext);
        if(allNews_database != null && allNews_database.size()>0){
            NewsAdapter newsAdapter = new NewsAdapter(mContext, allNews_database);
            mNewslv.setAdapter(newsAdapter);
        }

        onClick();
        return v;
    }

    // 为适配器Item设置点击事件
    private void onClick() {
        mNewslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,ShowNewsActivity.class);

                NewsBean bean = (NewsBean)parent.getItemAtPosition(position);
                DataHolder.getInstance().setNewsBeanData(bean);
                startActivity(intent);
            }
        });
    }
}
