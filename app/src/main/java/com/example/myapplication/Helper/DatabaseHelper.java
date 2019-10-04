package com.example.myapplication.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "nearplaces.db";
    public static final String TABLE_NAME = "gasPumps";
    public static final String COL1 = "NAME";
    public static final String COL2 = "RATING";
    public static final String COL3 = "LATTITUDE";
    public static final String COL4 = "LONGITUDE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table " + TABLE_NAME + "(NAME VARCHAR, RATING VARCHAR, LATTITUDE TEXT, LONGITUDE TEXT, PRIMARY KEY(LATTITUDE, LONGITUDE));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String ratings, String lattitude, String longitude) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, ratings);
        contentValues.put(COL3, lattitude);
        contentValues.put(COL4, longitude);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Log.d("insert", "failure");
            return false;
        } else {
            Log.d("insert", "success");
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Integer deleteData(String lat){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"LATTITUDE=?",new String[] {lat});
    }
}
