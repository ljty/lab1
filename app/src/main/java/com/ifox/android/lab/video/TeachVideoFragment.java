package com.ifox.android.lab.video;

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

public class TeachVideoFragment extends Fragment
{
        private JasonAdapter jasonAdapter;
        private Handler handler = new Handler();
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {       View view= inflater.inflate(R.layout.tab01, container, false);
                final ListView listview=(ListView)view.findViewById(R.id.videolist);
                jasonAdapter = new JasonAdapter(view.getContext());
                String url = "http://222.196.200.45:8080/video/Jsondata";
                //String url = "http://222.196.200.28:8080/lab/listAllVideo.json";

                new HttpThread(url, listview, handler, jasonAdapter).start();
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent it = new Intent(view.getContext(), Main3Activity.class);
                                Video video=(Video)listview.getItemAtPosition(position);
                                //Video video = new Video("单片机教学视频", "http://download.haozip.com/haozip_v2.8_tiny.exe", "http://222.196.200.34:8080/video/test.jpg", "t2.mp4", "t2.mp4","t2.mp4");
                              // String content="浪迹天涯";
                                it.putExtra("content", video);
                                view.getContext().startActivity(it);
                        }
                });
                return view;

        }

}
