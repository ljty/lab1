package com.ifox.android.lab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ifox.android.lab.bean.NewsBean;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * 公告数据获取
 */
public class NewsDaoUtils {

    private OpenHelper newsOpenHelper;

    public NewsDaoUtils(Context context){
        //创建一个帮助类对象
        newsOpenHelper = new OpenHelper(context);
    }
    //删除数据库中缓存的旧数据
    public void delete(){
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        db.delete("news", null, null);
        db.close();
    }
    //向数据库中添加新闻数据
    public void saveNews(ArrayList<NewsBean> list){
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        for (NewsBean newsBean : list) {
            ContentValues values = new ContentValues();
            values.put("n_id", newsBean.n_id);
            values.put("n_title", newsBean.n_title);
            values.put("n_content", newsBean.n_content);
            values.put("n_visitTimes", newsBean.n_visitTimes);
            values.put("n_sendDate", newsBean.n_sendDate);
            values.put("n_attachName", newsBean.n_attachName);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Bitmap bitmap = newsBean.n_attachAddress;
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            values.put("n_attachAddress", os.toByteArray());

            db.insert("news", null, values);
        }
        db.close();
    }
    //从数据库中获取缓存的新闻数据
    public ArrayList<NewsBean> getNews(){
        ArrayList<NewsBean> list = new ArrayList<NewsBean>();
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = newsOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from news", null);//查询获取数据
        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                NewsBean newsBean = new NewsBean();
                newsBean.n_id = cursor.getInt(0);
                newsBean.n_title = cursor.getString(1);
                newsBean.n_content = cursor.getString(2);
                newsBean.n_visitTimes = cursor.getString(3);
                newsBean.n_sendDate = cursor.getString(4);
                newsBean.n_attachName =	cursor.getString(5);

                byte[] in = cursor.getBlob(6);
                Bitmap bitmap = BitmapFactory.decodeByteArray(in, 0, in.length);
                newsBean.n_attachAddress=bitmap;

                list.add(newsBean);
            }
        }
        db.close();
        cursor.close();
        return list;
    }
}
