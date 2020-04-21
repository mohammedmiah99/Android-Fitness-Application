package com.miah.myfitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    public static final String DB_NAME = "mySQLiteDatabase4";
    public static final String TABLE_NAME = "workout";
    public static final String COLUMN_ID = "id";
    public static final String COL_SQUATS = "squat";
    public static final String COL_SQUATSR = "squatR";
    public static final String COL_DEADLIFT = "deadlift";
    public static final String COL_DEADLIFTR = "deadliftR";
    public static final String COL_BENCH = "bench";
    public static final String COL_BENCHR = "benchR";
    public static final String COL_OHP = "ohp";
    public static final String COL_OHPR = "ohpR";
    public static final String COL_DATE = "date";


    public static final int DB_VERSION = 1;

    public DatabaseHelper2(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sql = "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT , "+COL_SQUATS+" VARCHAR ,"+COL_SQUATSR+" VARCHAR ,"
                +COL_DEADLIFT+" VARCHAR , "+COL_DEADLIFTR+" VARCHAR , "
                +COL_BENCH+" VARCHAR , "+COL_BENCHR+" VARCHAR , "
                +COL_OHP+" VARCHAR , "+ COL_OHPR+" VARCHAR , "+ COL_DATE+" VARCHAR)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public boolean addWorkoutInfo(String squat,String squatR, String deadlift,String deadliftR, String bench,String benchR, String ohp, String ohpR,String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SQUATS, squat);
        contentValues.put(COL_SQUATSR, squatR);
        contentValues.put(COL_DEADLIFT, deadlift);
        contentValues.put(COL_DEADLIFTR, deadliftR);
        contentValues.put(COL_BENCH, bench);
        contentValues.put(COL_BENCHR, benchR);
        contentValues.put(COL_OHP, ohp);
        contentValues.put(COL_OHPR, ohpR);
        contentValues.put(COL_DATE, date);

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != 1;
    }

    public Cursor getWorkoutInfo(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+";";
        return  sqLiteDatabase.rawQuery(sql,null);
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseHelper2.TABLE_NAME, null, null);
    }


}