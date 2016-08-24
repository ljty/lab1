package com.ifox.android.lab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageDB extends SQLiteOpenHelper {

    /** 数据库版本号 **/
    private final static int DATABASE_VERSION = 1;
    /** 数据库名 **/
    private final static String DB_NAME = "image_db";

    /** 表名 **/
    private String TABLE_IMAGE = "image_data";
    public String T_ID = "_id";// 字段名
    public String T_BLOB = "T_BLOB";// 字段名
    /** 创建表 SQL 字符串 **/
    private String TABLE_IMAGE_CREATE = "Create table" + TABLE_IMAGE + "(" + T_ID
            + "INTEGER DEFAULT'1'NOT NULL PRIMARY KEY AUTOINCREMENT ,"
            + T_BLOB + "BLOB );";
    /** 表列数据集合 **/
    private String[] col = { T_ID, T_BLOB};

    /**ImageDB 对象构造方法 **/
    public ImageDB(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    public ImageDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /** 创建数据 **/
    public Long createData(Bitmap bmp) {
        ContentValues initValues = new ContentValues();
        Long id = null;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
/**
 * Bitmap.CompressFormat.JPEG 和 Bitmap.CompressFormat.PNG
 * JPEG 与 PNG 的是区别在于 JPEG 是有损数据图像，PNG 使用从 LZ77 派生的无损数据压缩算法。
 * 这里建议使用 PNG 格式保存
 * 100 表示的是质量为 100%。当然，也可以改变成你所需要的百分比质量。
 * os 是定义的字节输出流
 *
 * .compress() 方法是将 Bitmap 压缩成指定格式和质量的输出流
 */
        bmp.compress(Bitmap.CompressFormat.PNG, 100, os);

        initValues.put(T_BLOB, os.toByteArray());// 以字节形式保存

        SQLiteDatabase db = getDatabaseWrit();
        id = db.insert(TABLE_IMAGE, null, initValues);// 保存数据
        db.close();

        Log.i("Image", "create done.");
        return id;
    }

    public List<Map<String, Object>> getData() {

        List<Map<String, Object>> list = null;

        SQLiteDatabase db = getDatabaseRead();
        Cursor cursor = db.query(TABLE_IMAGE, col, null, null, null, null, null);// 数据的查询
        HashMap<String, Object> bindData = null;
        list = new ArrayList<Map<String, Object>>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            bindData = new HashMap<String, Object>();
            bindData.put(T_ID, cursor.getLong(0));
/** 得到 Bitmap 字节数据 **/
            byte[] in = cursor.getBlob(1);
/**
 * 根据 Bitmap 字节数据转换成 Bitmap 对象
 * BitmapFactory.decodeByteArray() 方法对字节数据，从 0 到字节的长进行解码，生成 Bitmap 对像。
 **/
            Bitmap bmpout = BitmapFactory.decodeByteArray(in, 0, in.length);
            bindData.put(T_BLOB, bmpout);

            list.add(bindData);
        }
        cursor.close();
        db.close();
        Log.i("Image", "get a Bitmap.");
        return list;
    }

    public void delete() {
        SQLiteDatabase db = getDatabaseWrit();
        db.delete(TABLE_IMAGE, null, null);// 数据的删除
        db.close();
        Log.i("Image", "delete all data.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_IMAGE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table" + TABLE_IMAGE;
        db.execSQL(sql);
        onCreate(db);
    }

    private SQLiteDatabase getDatabaseRead() {
        return this.getReadableDatabase();
    }

    private SQLiteDatabase getDatabaseWrit() {
        return this.getWritableDatabase();
    }

}