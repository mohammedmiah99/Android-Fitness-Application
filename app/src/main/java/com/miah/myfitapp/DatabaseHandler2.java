package com.miah.myfitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler2 extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "myTablenew";


    public DatabaseHandler2(Context context ) {
        super(context, "Newdatabase10", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table myTablenew(xValues INTEGER,yValues INTEGER);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertToData(long valX, int valY){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("xValues", valX);
        contentValues.put("yValues", valY);

        database.insert("myTablenew",null,contentValues);

    }
    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        //db.delete(DatabaseHandler.TABLE_NAME, null, null);
    }

}