package com.miah.myfitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "myTable";



    public DatabaseHandler(Context context ) {
        super(context, "Newdatabase4", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table myTable(xValues INTEGER,yValues INTEGER,xValues2 INTEGER,yValues2 INTEGER,xValues3 INTEGER,yValues3 INTEGER);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Boolean insertToData(int valX, int valY,int valX2, int valY2,int valX3, int valY3){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("xValues", valX);
        contentValues.put("yValues", valY);
        contentValues.put("xValues2", valX2);
        contentValues.put("yValues2", valY2);
        contentValues.put("xValues3", valX3);
        contentValues.put("yValues3", valY3);

        database.insert("myTable",null,contentValues);
        return true;
    }
    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

    }

}
