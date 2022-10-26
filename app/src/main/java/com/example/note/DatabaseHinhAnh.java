package com.example.note;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHinhAnh extends SQLiteOpenHelper {
    public DatabaseHinhAnh(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String str){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(str);
    }

    public void insertHinhAnh(String tenmon,String ngaychup,String mota, byte[] hinhanh ){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO HINHANH VALUES(null,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,tenmon);
        statement.bindString(2,ngaychup);
        statement.bindString(3,mota);
        statement.bindBlob(4,hinhanh);

        statement.executeInsert();
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
