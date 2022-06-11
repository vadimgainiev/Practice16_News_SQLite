package com.example.practice16_news_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "NewsDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table UserInfo(id INTEGER NOT NULL primary key AUTOINCREMENT UNIQUE, " +
                "login TEXT, password TEXT, role TEXT)");
        db.execSQL("create Table NewsInfo(id INTEGER NOT NULL primary key AUTOINCREMENT UNIQUE, " +
                "title TEXT, text TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists UserInfo");
        db.execSQL("drop Table if exists NewsInfo");
    }

    public Boolean insertUser(String login, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result = db.insert("UserInfo",null,contentValues);
        return result != -1;
    }

    public Boolean insertNews(String title, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("text",text);
        long result = db.insert("NewsInfo",null,contentValues);
        return result != -1;
    }

    public Boolean editNews(String title, String changed_title, String changed_text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", changed_title);
        contentValues.put("text", changed_text);
        int result = db.update("NewsInfo", contentValues,
                "title = " + "'" + title + "'",null);
        db.close();
        return result > 0;
    }

    public Boolean deleteNews(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("NewsInfo",
                "title = " + "'" + title + "'", null);
        db.close();
        return result > 0;
    }

    public Cursor getDataOnUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from UserInfo",null);
    }

    public Cursor getDataOnNews() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("Select * from NewsInfo",null);
    }
}
