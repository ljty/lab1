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

        db.execSQL("create table news (n_id integer  ," +
                "n_title varchar(200)," +
                "n_content varchar(200)," +
                "n_visitTimes varchar(200)," +
                "n_sendDate varchar(200)," +
                "n_attachName varchar(200))");

        db.execSQL("create table edu (n_id integer  ," +
                "n_title varchar(200)," +
                "n_content varchar(200)," +
                "n_visitTimes varchar(200)," +
                "n_sendDate varchar(200)," +
                "n_attachName varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
