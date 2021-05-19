package com.example.capitalesafrica_java__e1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Data.CapAfri;

public class Conexion extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "capitalesE1";
    private static final String TABLE_Users = "capitalesAfrica";
    private static final String KEY_NAME = "pais";
    private static final String KEY_LOC = "capital";

    public Conexion(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("+ KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
      //  onCreate(db);
    }

    void insertar(HashMap<String,String> afri) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        for (HashMap.Entry<String, String> entry : afri.entrySet()) {
            cValues.put(KEY_NAME, entry.getKey());
            cValues.put(KEY_LOC, entry.getValue());
            db.insert(TABLE_Users, null, cValues);
        }
    }

    public void Conexion(){
        SQLiteDatabase db = this.getWritableDatabase();
    }

    public void DeleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        db.close();
    }

    public HashMap<String, String> GetUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        HashMap<String, String> userList = new HashMap();
        String query = "SELECT pais, capital FROM " + TABLE_Users;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            userList.put( cursor.getString(cursor.getColumnIndex(KEY_NAME)),(cursor.getString(cursor.getColumnIndex(KEY_LOC))));

        }
        return userList;
    }
}
