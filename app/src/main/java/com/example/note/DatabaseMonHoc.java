package com.example.note;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseMonHoc extends SQLiteOpenHelper {


    public DatabaseMonHoc(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String str){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(str);
    }

    public Cursor getData(String str){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(str,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
