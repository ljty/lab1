package com.ifox.android.lab.video;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.ifox.android.lab.R;

import java.util.List;

/**
 * Created by LK on 2016/7/15.
 */
public class DownLoadAdapter extends BaseAdapter {
    private Handler handler = new Handler();
    private Handler mhandler = new Handler();
    ImageButton imageButton ;
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
    public DownLoadAdapter(){

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
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Video video = list.get(position);
        holder.videoDate.setText(video.getVideoDate());
        holder.videoContent.setText(video.getVideoContent());
        holder.videoTime.setText("" + video.getVideoTime());

//        new View2(holder.download,holder.pause);

        new View2(holder,mHandler);
        new ListPic(video.getPicurl(), holder.videoPic, handler).start();
        return convertView;
    }
   public class View2{
       ImageButton download;
       ImageButton pause;
       Handler mHander;
        public View2(Holder holder,Handler handler){
            this.mHander=handler;
                this.download=download;
            this.pause=pause;
            Message msg=new Message();
            msg.obj=holder;
            mHander.sendMessage(msg);
           // download.setVisibility(View.GONE);
        }

       public void getImageButton(ImageButton image1,ImageButton image2){



       }
    }

}

