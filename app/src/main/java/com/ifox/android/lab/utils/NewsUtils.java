package com.ifox.android.lab.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ifox.android.lab.bean.NewsBean;
import com.ifox.android.lab.dao.NewsDaoUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * 解析公告json
 */
public class NewsUtils {

    public static String newsPath_url="http://222.196.200.105:8080/lab/listAllNew.json";

    public static String newsPicPath_url="http://222.196.200.105:8080/lab/upload/";

    // 从服务器获取数据
    public static ArrayList<NewsBean> getAllNewsForNetWork(Context context){
        ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
        try{
            URL url = new URL(newsPath_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            int code = connection.getResponseCode();
            if(code == 200){
                InputStream inputStream = connection.getInputStream();
                String result = StreamUtils.streamToString(inputStream);
                JSONArray jsonArray=new JSONArray(result);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject new_json=jsonArray.getJSONObject(i);
                    NewsBean newsBean=new NewsBean();
                    newsBean.n_id=new_json.getInt("n_id");
                    newsBean.n_title=new_json.getString("n_title");
                    newsBean.n_content=new_json.getString("n_content");
                    newsBean.n_visitTimes=new_json.getString("n_visitTimes");
                    newsBean.n_sendDate=new_json.getString("n_sendDate");
                    newsBean.n_attachName=new_json.getString("n_attachName");
                    try {
                        URL url_pic = new URL(newsPicPath_url + new_json.getString("n_attachAddress"));
                        HttpURLConnection connection_pic = (HttpURLConnection) url_pic.openConnection();
                        connection_pic.setRequestMethod("GET");
                        connection_pic.setConnectTimeout(10 * 1000);
                        int code_pic = connection_pic.getResponseCode();
                        if (code_pic == 200) {
                            InputStream inputStream_pic = connection_pic.getInputStream();
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream_pic);
                            newsBean.n_attachAddress = bitmap;
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    arrayList.add(newsBean);
                }
                new NewsDaoUtils(context).delete();
                new NewsDaoUtils(context).saveNews(arrayList);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    // 从数据库获取数据
    public  static ArrayList<NewsBean> getAllNewsForDatabase(Context context) {
        return new NewsDaoUtils(context).getNews();
    }
}