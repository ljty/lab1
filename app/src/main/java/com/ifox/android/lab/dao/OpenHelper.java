package com.ifox.android.lab.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 公告数据库
 */
public class OpenHelper extends SQLiteOpenHelper{

    public OpenHelper(Context context) {
        super(context, "lab", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table news (n_id integer primary key autoincrement ," +
                "n_title varchar(200),n_content varchar(200)," +
                "n_visitTimes varchar(200)," +
                "n_sendDate varchar(200)," +
                "n_attachName varchar(200)," +
                "n_attachAddress blob)");

        db.execSQL("create table edu (et_id integer primary key autoincrement ," +
                "et_title varchar(200)," +
                "et_content varchar(200)," +
                "et_visitTimes varchar(200)," +
                "et_sendDate varchar(200)," +
                "et_attachName varchar(200)," +
                "et_attachAddress blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
