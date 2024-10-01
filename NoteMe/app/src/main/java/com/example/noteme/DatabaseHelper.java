package com.example.noteme;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.concurrent.Callable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NOTEME";
    private static final String TABLE_NAME = "NOTES";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TITLE";
    private static final String COL_3 = "SUBTITLE";
    private static final String COL_4 = "BODY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
