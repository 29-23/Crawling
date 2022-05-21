package com.example.personalootd.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "OOTD.db";
    private Context mContext;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
        dataBaseCheck();
    }

    private void dataBaseCheck()
    {
        File dbFile = new File(DB_PATH + DB_NAME);

        if (!dbFile.exists())
        {
            dbCopy();
        }
    }

    private void dbCopy()
    {
        try
        {
            File folder = new File(DB_PATH);
            if (!folder.exists())
            {
                folder.mkdir();
            }

            InputStream inputStream = mContext.getAssets().open(DB_NAME);
            String out_filename = DB_PATH + DB_NAME;
            OutputStream outputStream = new FileOutputStream(out_filename);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = inputStream.read(mBuffer)) > 0)
            {
                outputStream.write(mBuffer, 0, mLength);
            }
            outputStream.flush();
            ;
            outputStream.close();
            inputStream.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 자가진단 질문지
        String sql = "CREATE TABLE if not exists QUESTION ("
                + "_id integer primary key autoincrement,"
                + "color integer,"  // color code
                + "tem integer,"    // 색 온도 (temperature)
                + "bri integer,"    // 색 명도 (brightness)
                + "sat integer)";   // 색 채도 (saturation)

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists QUESTION";

        db.execSQL(sql);
        onCreate(db);
    }
}