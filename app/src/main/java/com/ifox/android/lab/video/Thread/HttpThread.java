package com.ifox.android.lab.video.Thread;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

import com.ifox.android.lab.video.Adapter.JsonAdapter;
import com.ifox.android.lab.video.Persondefined.Video;

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
 *1:根据从TeachVideoFragment传递过来的URL，获取其Json数据，并解析出来之后传递给适配器类JsonAdapter
 *2:给listView设置adpter
 */
public class HttpThread extends Thread {
    Context context;
    private String url;
    private ListView listView;
    private Handler handler;
    private JsonAdapter jsonAdapter;

    public HttpThread(String url, ListView listView, Handler handler, JsonAdapter jsonAdapter) {
        this.url = url;//取到从TeachVideoFragment传来的视频地址
        this.handler = handler;//取到从TeachVideoFragment传来的handler
        this.listView = listView;//取到从TeachVideoFragment传来的视频地址listView
        this.jsonAdapter = jsonAdapter;//取到从TeachVideoFragment传来的视频地址JsonAdapter
    }

    public void run() {
        //进行网络访问请求，拿到Json数据
        URL httpUrl;
        try {
            httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();//用于存储Json数据
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }
            final List<Video> data = p1asr1eJason(stringBuffer.toString());//取得返回的数据
            handler.post(new Runnable() {//设置数据在列表中显示
                public void run() {
                    jsonAdapter.setData(data);//给适配器传递给list集合
                    listView.setAdapter(jsonAdapter);//给listView设置适配器
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Video> p1asr1eJason(String json) throws Exception {//解析从服务器取到的Json数据
        List<Video> videos = new ArrayList<Video>();//创建一个List集合对象，用于存储所有解析到的video视频信息
        JSONArray jsonArray = new JSONArray(json);//将Json数据先存储到Json数组中
        for (int i = 0; i < jsonArray.length(); i++) {//遍历Json数组，取出数据
            Video video = new Video();//创建一个video对象，用于存储视频信息
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String content = jsonObject1.getString("videoContent");//取到视频描述信息
            String time = jsonObject1.getString("videoTime");//取到视频播放时长
            String date = jsonObject1.getString("videoDate");//取到视频创建日期
            String picurl = jsonObject1.getString("url");//取到图片地址
            String videourl = jsonObject1.getString("videourl");//取到视频地址
            String videoname = jsonObject1.getString("videoname");//取到视频名字

            //String videoshow=jsonObject1.getString("v_content");
            //String time=jsonObject1.getString("v_time");
            //String date=jsonObject1.getString("v_sendDate");
            //String picurl=jsonObject1.getString("p_url");
            // String picurl="http://v1.qzone.cc/avatar/201303/18/14/36/5146b6067d679395.jpg%21200x200.jpg";
            // String url=jsonObject1.getString("v_url");
            //String videoname=jsonObject1.getString("v_name");
            // video.setVideoname(videoname);
            //将视频信息添加到Video类中
            video.setPicurl(picurl);
            video.setVideoContent(content);
            video.setVideoDate(date);
            video.setVideoTime(time);
            video.setVideoname(videoname);
            String url = "http://222.196.200.252:8080/video/t2.mp4";
            video.setVideourl(url);
            videos.add(video);
        }
        return videos;//返回视频集合
    }
}


