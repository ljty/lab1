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
 * 公告适配器
 */
public class NewsAdapter extends BaseAdapter {

    private final ArrayList<NewsBean> list;

    private final Context context;

    public NewsAdapter(Context context,ArrayList<NewsBean> list){
        this.context = context;
        this.list = list;
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
            view = View.inflate(context, R.layout.item_news,null);
        }

        TextView n_title = (TextView) view.findViewById(R.id.n_title);

        NewsBean newsBean = list.get(position);

        n_title.setText(newsBean.n_title);

        return view;
    }
}