//package com.rinkusaini.a2_myappteam;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//public class ChatDatabaseHelper extends SQLiteOpenHelper {
//
//    public static final String DATABASE_NAME = "database chats";
//    public static final String TABLE_NAME = "table chats";
//
//    public ChatDatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, 1);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        //Create Table
//        String createTable = "create table " + TABLE_NAME + "(id INTEGER PRIMARY KEY, txt TEXT)";
//        db.execSQL(createTable);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
