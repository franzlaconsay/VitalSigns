package com.f11.vitalsigns;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static String database_name="Users";
    private static int database_version =1;

    public DBHandler(Context context){
        super(context, database_name, null, database_version);
    }

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE User" +
                "(" +
                "FirstName varchar(50)," +
                "LastName varchar(50)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS User";
        db.execSQL(sql);
        this.onCreate(db);
    }

    public void addUser(String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO User VALUES(" +
                "'" + firstName + "'," +
                "'" + lastName + "')";
        db.execSQL(sql);
        db.close();
    }

    public List getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        List users = new ArrayList();
        Cursor cursor = db.rawQuery("select * from User",null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String fname = cursor.getString(cursor.getColumnIndex("FirstName"));
                String lname = cursor.getString(cursor.getColumnIndex("LastName"));
                users.add(lname+", "+fname);
                cursor.moveToNext();
            }
        }
        else {
            users.add("");
        }
        return users;
    }
}