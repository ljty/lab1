package com.ifox.android.lab.video;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ifox.android.lab.R;

import java.util.List;

/**
 * 将从httpThread传递进来的数据显示在列表项的相应组件中
 */
public class JasonAdapter extends BaseAdapter {
    private Handler handler = new Handler();
    private List<Video> list;
    private Context context;
    private LayoutInflater inflater;
    public JasonAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public JasonAdapter(){
    }
    public void setData(List<Video> list) {
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
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Video video = list.get(position);
        holder.videoDate.setText(video.getVideoDate());
        holder.videoContent.setText(video.getVideoContent());
        holder.videoTime.setText(""+video.getVideoTime());
        new ListPic(video.getPicurl(), holder.videoPic, handler).start();
        return convertView;
    }
 }

