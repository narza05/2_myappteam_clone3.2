package com.rinkusaini.a2_myappteam;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class LeadsDatabaseHelper extends SQLiteOpenHelper {

    //Initialize Database Name and Table Name
    private static final String DATABASE_NAME = "database_leads";
    private static final String TABLE_NAME = "table_leads";

    LeadsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table
        String createTable = "create table " + TABLE_NAME + "(id INTEGER PRIMARY KEY, txt TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addText(String text) {
        //Get Writeable Database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //Create ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put("txt",text);   //"txt" should match with onCreate column

        //Add values into Database
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteText(String text) {
        //Get Writeable Database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" Select * from " + TABLE_NAME + " where txt = ?",new String[]{text});
        if (cursor.getCount()>0) {
            //Add values into Database
            long result = sqLiteDatabase.delete(TABLE_NAME, "txt = ?", new String[]{text});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    @SuppressLint("Range")
    public ArrayList getAllText() {
        //Get readable Database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();

        //Create Cursor to select All Values
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME
                  , null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("txt")));
            cursor.moveToNext();
        }
        return arrayList;
    }

}
