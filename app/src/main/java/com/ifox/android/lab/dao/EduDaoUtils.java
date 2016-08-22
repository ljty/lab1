package com.ifox.android.lab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ifox.android.lab.bean.EduBean;

import java.util.ArrayList;

/**
 * 教学资源数据获取
 */
public class EduDaoUtils {

    private OpenHelper eduOpenHelper;

    public EduDaoUtils(Context context){
        //创建一个帮助类对象
        eduOpenHelper = new OpenHelper(context);
    }
    //删除数据库中缓存的旧数据
    public void delete(){
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = eduOpenHelper.getReadableDatabase();
        db.delete("edu", null, null);
        db.close();
    }
    //向数据库中添加新闻数据
    public void saveEdu(ArrayList<EduBean> list){
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = eduOpenHelper.getReadableDatabase();
        for (EduBean eduBean : list) {
            ContentValues values = new ContentValues();
//            values.put("n_id", eduBean.n_id);
//            values.put("n_title", eduBean.n_title);
//            values.put("n_content", eduBean.n_content);
//            values.put("n_visitTimes", eduBean.n_visitTimes);
//            values.put("n_sendDate", eduBean.n_sendDate);
//            values.put("n_attachName", eduBean.n_attachName);
//            values.put("n_attachAddress", eduBean.n_attachAddress);

            db.insert("edu", null, values);
        }
        db.close();
    }
    //从数据库中获取缓存的新闻数据
    public ArrayList<EduBean> getEdu(){
        ArrayList<EduBean> list = new ArrayList<EduBean>();
        //通过帮助类对象获取一个数据库操作对象
        SQLiteDatabase db = eduOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from edu", null);//查询获取数据
        if(cursor != null && cursor.getCount() > 0){
            while(cursor.moveToNext()){
                EduBean eduBean = new EduBean();
//                eduBean.n_id = cursor.getInt(0);
//                eduBean.n_title = cursor.getString(1);
//                eduBean.n_content =	cursor.getString(2);
//                eduBean.n_visitTimes = cursor.getString(3);
//                eduBean.n_sendDate = cursor.getString(4);
//                eduBean.n_attachName =	cursor.getString(5);
//                eduBean.n_attachAddress = cursor.getString(6);

                list.add(eduBean);
            }
        }
        db.close();
        cursor.close();
        return list;
    }
}
