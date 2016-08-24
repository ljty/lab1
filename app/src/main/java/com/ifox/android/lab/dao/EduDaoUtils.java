package com.ifox.android.lab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ifox.android.lab.bean.EduBean;

import java.io.ByteArrayOutputStream;
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
            values.put("et_id", eduBean.et_id);
            values.put("et_title", eduBean.et_title);
            values.put("et_content", eduBean.et_content);
            values.put("et_visitTimes", eduBean.et_visitTimes);
            values.put("et_sendDate", eduBean.et_sendDate);
            values.put("et_attachName", eduBean.et_attachName);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Bitmap bitmap = eduBean.et_attachAddress;
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            values.put("et_attachAddress", os.toByteArray());

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
                eduBean.et_id = cursor.getInt(0);
                eduBean.et_title = cursor.getString(1);
                eduBean.et_content =	cursor.getString(2);
                eduBean.et_visitTimes = cursor.getString(3);
                eduBean.et_sendDate = cursor.getString(4);
                eduBean.et_attachName =	cursor.getString(5);
                byte[] in = cursor.getBlob(6);
                Bitmap bitmap = BitmapFactory.decodeByteArray(in, 0, in.length);
                eduBean.et_attachAddress=bitmap;

                list.add(eduBean);
            }
        }
        db.close();
        cursor.close();
        return list;
    }
}
