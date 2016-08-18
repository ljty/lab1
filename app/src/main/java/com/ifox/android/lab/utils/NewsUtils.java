package com.ifox.android.lab.utils;

import android.content.Context;

import com.ifox.android.lab.bean.NewsBean;
import com.ifox.android.lab.dao.NewsDaoUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 10368 on 2016/7/23.
 */
public class NewsUtils {

    public static String newsPath_url="http://222.196.200.50:8080/itheima74/servlet/GetNewsServlet";

    public static ArrayList<NewsBean> getAllNewsForNetWork(Context context){
        ArrayList<NewsBean> arrayList = new ArrayList<NewsBean>();
        try{
            //1. 请求服务器获取新闻数据
            // 获取一个 url 对象，通过 url 对象得到一个 urlconnnection 对象
            URL url = new URL(newsPath_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接的方式和超时时间
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            // 获取请求响应码
            int code = connection.getResponseCode();
            if(code == 200){
                // 获取请求到的流信息
                InputStream inputStream = connection.getInputStream();
                String result = StreamUtils.streamToString(inputStream);

                //2. 解析获取的新闻数据到 List 集合中。

                JSONObject root_json = new JSONObject(result);// 将一个字符串封装成一个 json 对象。
                JSONArray jsonArray = root_json.getJSONArray("newss");// 获取 root_json 中的 newss 作为 jsonArray 对象

                for (int i = 0 ;i < jsonArray.length();i++){// 循环遍历 jsonArray
                    JSONObject news_json = jsonArray.getJSONObject(i);// 获取一条新闻的 json

                    NewsBean newsBean = new NewsBean();

                    newsBean. id = news_json.getInt("id");
                    newsBean. des = news_json.getString("des");
                    newsBean. title = news_json.getString("title");

                    arrayList.add(newsBean);

                }
//                JSONArray jsonArray=new JSONArray(result);
//                for (int i=0;i<jsonArray.length();i++){
//                    JSONObject new_json=jsonArray.getJSONObject(i);
//                    NewsBean newsBean=new NewsBean();
//                    newsBean.id=new_json.getInt("id");
//                    newsBean.des=new_json.getString("name");

//                    arrayList.add(newsBean);
//                }

                //3. 清楚数据库中旧的数据，将新的数据缓存到数据库中
                new NewsDaoUtils(context).delete();
                new NewsDaoUtils(context).saveNews(arrayList);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public  static ArrayList<NewsBean> getAllNewsForDatabase(Context context) {

        return new NewsDaoUtils(context).getNews();

    }
}