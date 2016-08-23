package com.ifox.android.lab.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.bean.EduBean;

import java.util.ArrayList;

/**
 * 教学资源适配器
 */
public class EduAdapter extends BaseAdapter{

    private final ArrayList<EduBean> list;
    private final Context context;

    public EduAdapter(Context context,ArrayList<EduBean> list){
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
            view = View.inflate(context, R.layout.item_edu,null);
        }
        TextView et_title = (TextView) view.findViewById(R.id.et_title);

        TextView et_sendDate = (TextView) view.findViewById(R.id.et_sendDate);

        ImageView et_attachAddress = (ImageView) view.findViewById(R.id.et_attachAddress);

        EduBean eduBean = list.get(position);

        et_title.setText(eduBean.et_title);

        et_sendDate.setText(eduBean.et_sendDate);

        et_attachAddress.setImageBitmap(eduBean.et_attachAddress);

        return view;
    }
}