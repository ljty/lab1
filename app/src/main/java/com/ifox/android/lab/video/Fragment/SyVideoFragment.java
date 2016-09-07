package com.ifox.android.lab.video.Fragment;
/**
 * 仪器使用教学视频页，用ListView显示视频列表
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ifox.android.lab.R;
import com.ifox.android.lab.video.Activity.VideoShowActivity;
import com.ifox.android.lab.video.Thread.HttpThread;
import com.ifox.android.lab.video.Adapter.JsonAdapter;
import com.ifox.android.lab.video.Persondefined.Video;

public class SyVideoFragment extends Fragment {
    private com.ifox.android.lab.video.Adapter.JsonAdapter JsonAdapter;
private Handler handler = new Handler();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab02, container, false);
        final ListView listview = (ListView) view.findViewById(R.id.videolist);
        JsonAdapter = new JsonAdapter(view.getContext());//实例化适配器
        String url = "http://222.196.200.252:8080/video/Jsondata";//服务器Json数据的URL地址
        new HttpThread(url, listview, handler, JsonAdapter).start();//创建并启动线程
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {//给列表项设置点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到Main3Activity（视频详情页）并把视频信息传递过去
                Intent it = new Intent(view.getContext(), VideoShowActivity.class);
                Video video = (Video) listview.getItemAtPosition(position);
                it.putExtra("content", video);
                view.getContext().startActivity(it);
            }
        });
        return view;

    }

}
