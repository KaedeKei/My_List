package com.example.my_list;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "itemsDb.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "itemss";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + " (" + COLUMN_ID + " integer primary key  autoincrement, " + COLUMN_NAME + " text)");
        db.execSQL("insert into " + TABLE + " (" + COLUMN_NAME + ") values('vase'), ('carpet')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE);
        onCreate(db);
    }
}
