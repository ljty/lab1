package com.ifox.android.lab.video.Adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
 * 下载类适配器，将要下载的视频用列表的形式经行下载
 */
public class DownLoadAdapter extends BaseAdapter {
    private Handler handler = new Handler();//创建一个handler对象，用于向DownloadingActivity传入视频信息
    private List<Video> list;
    private Context context;
    private LayoutInflater inflater;
    private View.OnClickListener click;
    Handler mHandler;
    public DownLoadAdapter(Context context,Handler handler) {
        this.context = context;
        this.mHandler=handler;
        inflater = LayoutInflater.from(context);
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
    public void setOnclick(View.OnClickListener click) {
        this.click=click;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Holder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);//将列表项的布局文件转换成View
            holder = new Holder(view);//创建一个holder对象
            view.setTag(holder);//设置关联
        } else {
            holder = (Holder) view.getTag();
        }
        Video video = list.get(position);
        holder.videoDate.setText(video.getVideoDate());//设置列表项的日期
        holder.videoContent.setText(video.getVideoContent());//设置列表项的内容
        holder.videoTime.setText("" + video.getVideoTime());//设置列表项的播放时长
        Message msg=new Message();//创建一个信息对象，并用handler发送
        msg.obj=holder;
        mHandler.sendMessage(msg);
        new PicThread(video.getPicurl(), holder.videoPic, handler).start();//调用加载图片类
        return view;
    }
}

