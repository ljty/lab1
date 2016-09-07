package com.ifox.android.lab.video.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.Persondefined.Holder;
import com.ifox.android.lab.video.Thread.PicThread;
import com.ifox.android.lab.video.Persondefined.Video;

import java.util.List;

/**
 * 用于从httpThread传递进来的数据显示在列表项的相应组件中
 */
public class JsonAdapter extends BaseAdapter {
    private Handler handler = new Handler();//传递到ListPic用于更新图片显示
    private List<Video> list;//用于接收httpThread传递过来的video集合
    private Context context;//用于实例化过滤器
    private LayoutInflater inflater;//声明过滤器
    public JsonAdapter(Context context) {
        this.context = context;//接收到从Fragment传递过来的上下文
        inflater = LayoutInflater.from(context);//实例化过滤器
    }

    public void setData(List<Video> list) {
        this.list = list;
    }//接收由httpThread传递来的video集合数据

    public int getCount() {
        return list.size();
    }//取到集合的大小

    public Object getItem(int position) {
        return list.get(position);
    }//取到video视频集合具体项

    public long getItemId(int position) {//取到视频的id
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;//定义一个组件容器
        if (convertView == null) {//第一次加载
            convertView = inflater.inflate(R.layout.listview, null);//将布局文件转换成View
            holder = new Holder(convertView);//实例化holder对象
            convertView.setTag(holder);//convertView于holder对象设置Tag
        } else {
            holder = (Holder) convertView.getTag();//直接由convertView取到holder对象
        }
        Video video = list.get(position);//取到集合中的具体视频项
        holder.videoDate.setText(video.getVideoDate());//给日期组件设置创建日期
        holder.videoContent.setText(video.getVideoContent());//给视频描述组件设置具体内容
        holder.videoTime.setText(""+video.getVideoTime());//给播放时长组件设置播放时长
        new PicThread(video.getPicurl(), holder.videoPic, handler).start();//启动线程传递图片地址到PicThread
        return convertView;
    }
 }

