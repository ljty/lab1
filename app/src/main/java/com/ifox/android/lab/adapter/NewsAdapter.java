package com.ifox.android.lab.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/23.
 */
public class NewsAdapter extends BaseAdapter{

    private final ArrayList<NewsBean> list;

    private final Context context;

    public NewsAdapter(Context context,ArrayList<NewsBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        if(convertView != null){
            view = convertView;
        }else {
            view=View.inflate(context, R.layout.item_news,null);
        }
        TextView news= (TextView) view.findViewById(R.id.news);

        NewsBean newsBean=list.get(position);

        news.setText(newsBean.title);
        return view;
    }
}