package com.ifox.android.lab.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ifox.android.lab.bean.EduBean;
import com.ifox.android.lab.dao.EduDaoUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * 解析教学资源json
 */
public class EduUtils {

    public static String eduPath_url="http://222.196.200.55:8080/lab/listAllTheory.json";

    public static String eduPicPath_url="http://222.196.200.55:8080/lab/upload/";

    public static ArrayList<EduBean> getAllEduForNetWork(Context context){
        ArrayList<EduBean> arrayList = new ArrayList<EduBean>();
        try{
            //1. 请求服务器获取新闻数据
            // 获取一个 url 对象，通过 url 对象得到一个 urlconnnection 对象
            URL url = new URL(eduPath_url);
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

                JSONArray jsonArray = new JSONArray(result);
                for (int i=0;i<jsonArray.length();i++){

                    JSONObject edu_json = jsonArray.getJSONObject(i);
                    EduBean eduBean = new EduBean();
                    eduBean.et_id=edu_json.getInt("et_id");
                    eduBean.et_title=edu_json.getString("et_title");
                    eduBean.et_content=edu_json.getString("et_content");
                    eduBean.et_visitTimes=edu_json.getString("et_visitTimes");
                    eduBean.et_sendDate=edu_json.getString("et_sendDate");
                    eduBean.et_attachName=edu_json.getString("et_attachName");

                    URL url_pic = new URL(eduPicPath_url+edu_json.getString("et_attachAddress"));
                    HttpURLConnection connection_pic = (HttpURLConnection) url_pic.openConnection();
                    connection_pic.setRequestMethod("GET");
                    connection_pic.setConnectTimeout(10*1000);
                    int code_pic = connection_pic.getResponseCode();
                    if(code_pic == 200) {
                        InputStream inputStream_pic = connection_pic.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream_pic);

                        eduBean.et_attachAddress=bitmap;
                    }

                    arrayList.add(eduBean);
                }

                new EduDaoUtils(context).delete();
                new EduDaoUtils(context).saveEdu(arrayList);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public  static ArrayList<EduBean> getAllEduForDatabase(Context context) {

        return new EduDaoUtils(context).getEdu();

    }
}