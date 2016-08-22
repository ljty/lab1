package com.ifox.android.lab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by 10368 on 2016/8/20.
 */

public class NewsRecycleAdapter extends RecyclerView.Adapter<NewsRecycleAdapter.NewsViewHolder> implements View.OnClickListener{

    private final ArrayList<NewsBean> list;

    private final Context context;

    private NewsBean newsBean;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(NewsBean newsBean);
    }

    public NewsRecycleAdapter(Context context, ArrayList<NewsBean> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return viewHolder;

//        NewsViewHolder viewHolder = new NewsViewHolder(View.inflate(context, R.layout.item_news, null));
//        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {

        newsBean = list.get(position);

        holder.n_title.setText(newsBean.n_title);

    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(newsBean);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView n_title;

        public NewsViewHolder(View itemView){
            super(itemView);
            n_title = (TextView) itemView.findViewById(R.id.n_title);

        }

    }
}