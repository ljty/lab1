package com.ifox.android.lab.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 公告数据库
 */
public class OpenHelper extends SQLiteOpenHelper{

    // 新建数据库
    public OpenHelper(Context context) {
        super(context, "lab", null, 1);
    }

    // 新建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        newsSql(db);
        eduSql(db);
    }

    // 更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 公告表
    private void newsSql(SQLiteDatabase db) {
        db.execSQL("create table news (n_id integer ," +
                "n_title text," +
                "n_content text," +
                "n_visitTimes text," +
                "n_sendDate text," +
                "n_attachName text," +
                "n_attachAddress blob)");
    }

    // 教学资源表
    private void eduSql(SQLiteDatabase db) {
        db.execSQL("create table edu (et_id integer ," +
                "et_title text," +
                "et_content text," +
                "et_visitTimes text," +
                "et_sendDate text," +
                "et_attachName text," +
                "et_attachAddress blob)");
    }
}
