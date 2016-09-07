package com.ifox.android.lab.video.Thread;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.ifox.android.lab.video.Adapter.JsonAdapter;
import com.ifox.android.lab.video.Persondefined.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据搜索类容，向服务器发送请求，并将查询到的结果返回并用列表显示
 */
public class HttpThread1 extends Thread {
    private Context context;
    private String searchContent;
    private String url;
    private ListView listView;
    private Handler handler;
    private JsonAdapter jsonAdapter;

    public void run() {
        dopost();


    }

    public void dopost() {
        try {
            URL httpUrl = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                connection.setRequestMethod("post");
                connection.setReadTimeout(6000);
                OutputStream out = connection.getOutputStream();
                String Content = searchContent;
                out.write(Content.getBytes());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                final List<Video> data = pasreJason(stringBuffer.toString());
                handler.post(new Runnable() {
                    public void run() {
                        jsonAdapter.setData(data);
                        listView.setAdapter(jsonAdapter);

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private List<Video> pasreJason(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<Video> videos = new ArrayList<>();
            int result = jsonObject.getInt("result");
            if (result == 1) {
                JSONArray jsonArray = jsonObject.getJSONArray("video");
                for (int i = 1; i < jsonArray.length(); i++) {
                    Video video = new Video();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String content = jsonObject1.getString("v_content");
                    String url = jsonObject1.getString("v_url");
                    String time = jsonObject1.getString("v_time");
                    String date = jsonObject1.getString("v_date");
                    video.setPicurl(url);
                    video.setVideoContent(content);
                    video.setVideoDate(date);
                    video.setVideoTime(time);
                    videos.add(video);
                }
                return videos;
            } else {
                Toast.makeText(context, "没有相关数据", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
