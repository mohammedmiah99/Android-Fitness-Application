package com.miah.myfitapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "mySQLiteDatabase";
    public static final String TABLE_NAME = "food";
    public static final String COLUMN_ID = "id";
    public static final String COL_KCALS = "kcals";
    public static final String COL_NOTES = "notes";
    public static final String COL_CARBS = "carbs";
    public static final String COL_PROTEIN = "protein";
    public static final String COL_FATS = "fats";

    public static final int DB_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sql = "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT , "+COL_NOTES+" VARCHAR ,"
                +COL_KCALS+" VARCHAR , "+COL_CARBS+" VARCHAR , "+COL_PROTEIN+
                " VARCHAR, "+COL_FATS+" VARCHAR)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public boolean addFood(String notes, String kcals, String carbs, String protein, String fats){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOTES, notes);
        contentValues.put(COL_KCALS, kcals);
        contentValues.put(COL_CARBS, carbs);
        contentValues.put(COL_PROTEIN, protein);
        contentValues.put(COL_FATS, fats);

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != 1;
    }

    public Cursor getFood(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+";";
        return  sqLiteDatabase.rawQuery(sql,null);
    }

    public Integer deleteData (String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"notes = ?",new String[]{notes});
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME, null, null);
    }
    public String getcalories(long id) {

        String rv = "not found";
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "ID=?";
        String[] whereargs = new String[]{String.valueOf(id)};
        Cursor csr = db.query(TABLE_NAME,null,whereclause,whereargs,null,null,null);
        if (csr.moveToFirst()) {
            rv = csr.getString(csr.getColumnIndex(COL_KCALS));
        }
        return rv;
    }



}