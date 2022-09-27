package com.rinkusaini.a2_myappteam;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Initialize Database Name and Table Name
    private static final String DATABASE_NAME = "database_leads";
    private static final String TABLE_CHAT = "table_chat";
    private static final String TABLE_LEADS = "table_leads";
    private static final String TABLE_SALESMAN = "table_salesman";
    private static final String TABLE_EMPLOYEE = "table_employee";
    private static final String TABLE_TARGET = "table_target";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table
        String table_chat = "create table " + TABLE_CHAT + "(id INTEGER PRIMARY KEY, txt TEXT)";
        String table_leads = "create table " + TABLE_LEADS+ "(id INTEGER PRIMARY KEY, txt TEXT)";
        String table_salesman = "create table " + TABLE_SALESMAN + "(id INTEGER PRIMARY KEY, txt TEXT)";
        String table_employee = "create table " + TABLE_EMPLOYEE + "(id INTEGER PRIMARY KEY, txt TEXT)";
        String table_target = "create table " + TABLE_TARGET + "(id INTEGER PRIMARY KEY, txt TEXT)";
        db.execSQL(table_chat);
        db.execSQL(table_leads);
        db.execSQL(table_salesman);
        db.execSQL(table_employee);
        db.execSQL(table_target);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALESMAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TARGET);
        onCreate(db);
    }

    public boolean addText(String text, String a) {
        String TABLE_NAME = a;
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

    public boolean deleteText(String text, String a) {
        String TABLE_NAME = a;
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
    public ArrayList getAllText(String a) {
        String TABLE_NAME = a;
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
