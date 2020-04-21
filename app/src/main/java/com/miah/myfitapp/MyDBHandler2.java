package com.miah.myfitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHandler2 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "workout.db";
    private static final String TABLE_WORKOUT = "workout";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SQUAT = "squat";
    private static final String COLUMN_DEADLIFT = "deadlift";
    private static final String COLUMN_BENCHPRESS = "benchpress";
    private static final String COLUMN_OHP = "ohp";

    public MyDBHandler2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_WORKOUT + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SQUAT + " TEXT, " +
                COLUMN_DEADLIFT + " TEXT, " +
                COLUMN_BENCHPRESS + " TEXT, " +
                COLUMN_OHP + " TEXT "
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
        onCreate(db);
    }

    public  void addWorkoutInfo(WorkoutInfo workoutInfo){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SQUAT, workoutInfo.getSquat());
        values.put(COLUMN_DEADLIFT, workoutInfo.getDeadlift());
        values.put(COLUMN_BENCHPRESS, workoutInfo.getBenchpress());
        values.put(COLUMN_OHP, workoutInfo.getOhp());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_WORKOUT, null, values);
        db.close();
    }

    public void deleteSquat(int squat){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_WORKOUT + " WHERE " + COLUMN_SQUAT + "=\"" + squat + "\";" );
    }
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_WORKOUT + " WHERE 1";
        //Curson point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in results
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("squat")) != null) {
                dbString += c.getString(c.getColumnIndex("squat"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}

