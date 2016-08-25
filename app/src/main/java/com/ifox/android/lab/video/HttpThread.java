package com.ifox.android.lab.video;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *1:根据从MainActivity传递过来的URL，获取其Json数据，并解析出来之后传递给适配器类JsonAdapter
 *2:给listView设置adpter
 */
public class HttpThread extends Thread {
    Context context;
    private String url;
    private ListView listView;
    private Handler handler;
    private JasonAdapter jasonAdapter;

    public HttpThread(String url, ListView listView, Handler handler, JasonAdapter jasonAdapter) {
        this.url = url;
        this.handler = handler;
        this.listView = listView;
        this.jasonAdapter = jasonAdapter;
    }

    public void run() {
        URL httpUrl;
        try {
            httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }
            final List<Video> data = p1asr1eJason(stringBuffer.toString());
            handler.post(new Runnable() {
                public void run() {
                    jasonAdapter.setData(data);
                    listView.setAdapter(jasonAdapter);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Video> p1asr1eJason(String json) throws Exception {
        List<Video> videos = new ArrayList<Video>();
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            Video video = new Video();
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String content = jsonObject1.getString("videoContent");
            String time = jsonObject1.getString("videoTime");
            String date = jsonObject1.getString("videoDate");
            String picurl = jsonObject1.getString("url");
            String videourl = jsonObject1.getString("videourl");
            String videoname = jsonObject1.getString("videoname");

            //String content=jsonObject1.getString("v_content");
            //String time=jsonObject1.getString("v_time");
            //String date=jsonObject1.getString("v_sendDate");
            //String picurl=jsonObject1.getString("p_url");
            // String picurl="http://v1.qzone.cc/avatar/201303/18/14/36/5146b6067d679395.jpg%21200x200.jpg";
            // String url=jsonObject1.getString("v_url");
            //String videoname=jsonObject1.getString("v_name");
            // video.setVideoname(videoname);

            video.setPicurl(picurl);
            video.setVideoContent(content);
            video.setVideoDate(date);
            video.setVideoTime(time);
            video.setVideoname(videoname);
            String url = "http://222.196.200.45:8080/" + videourl;
            video.setVideourl(url);
            videos.add(video);
        }
        return videos;
    }
}


