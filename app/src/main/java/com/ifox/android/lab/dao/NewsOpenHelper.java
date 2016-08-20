package com.ifox.android.lab.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 10368 on 2016/8/1.
 */
public class NewsOpenHelper extends SQLiteOpenHelper{

    public NewsOpenHelper(Context context) {
        super(context, "lab", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table news (n_id integer  ," +
                "n_title varchar(200)," +
                "n_content varchar(200)," +
                "n_visitTimes varchar(200)," +
                "n_sendDate varchar(200)," +
                "n_attachName varchar(200)," +
                "n_attachAddress varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
