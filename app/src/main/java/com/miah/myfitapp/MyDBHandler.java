package com.miah.myfitapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.widget.TableLayout;

import androidx.annotation.Nullable;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "food.db";
    private static final String TABLE_FOOD = "food";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOTES = "notes";
    private static final String COLUMN_KCAL = "kcal";
    private static final String COLUMN_PROTEIN = "protein";
    private static final String COLUMN_CARBS = "carbs";
    private static final String COLUMN_FATS = "fats";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_FOOD + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTES + " TEXT, " +
                COLUMN_KCAL + " TEXT, " +
                COLUMN_CARBS + " TEXT, " +
                COLUMN_PROTEIN + " TEXT, " +
                COLUMN_FATS + " TEXT "
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
    onCreate(db);
    }

    public  void addFood(Food food){
        ContentValues values = new ContentValues();
        values.put(COLUMN_KCAL, food.getKcal());
        values.put(COLUMN_NOTES, food.getNotes());
        values.put(COLUMN_CARBS, food.getCarbs());
        values.put(COLUMN_PROTEIN, food.getProtein());
        values.put(COLUMN_FATS, food.getFats());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    public void deleteProduct(String notes){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_FOOD + " WHERE " + COLUMN_NOTES + "=\"" + notes + "\";" );
    }
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE 1";
        //Curson point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in results
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("notes")) != null) {
                dbString += c.getString(c.getColumnIndex("notes"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

}
