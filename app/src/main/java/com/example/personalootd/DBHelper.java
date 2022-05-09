package com.example.personalootd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 자가진단 질문지
        String sql = "CREATE TABLE if not exists QUESTION ("
                + "_id integer primary key autoincrement,"
                + "first integer,"
                + "second integer);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists QUESTION";

        db.execSQL(sql);
        onCreate(db);
    }
}