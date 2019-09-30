package com.scodeid.kamus.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Created by scode on 30,September,2019
 * Yogi Arif Widodo
 * www.dicoding.com/users/297307
 * www.github.com/yogithesymbian
 * SCODEID company,
 * Indonesia, East Borneo.
 * ==============================================================
 * Android Studio 3.4.2
 * Build #AI-183.6156.11.34.5692245, built on June 27, 2019
 * JRE: 1.8.0_152-release-1343-b16-5323222 amd64
 * JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
 * Linux 5.2.0-kali2-amd64
 * ==============================================================
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbkamus";
    public static final String INGGRIS = "inggris";
    public static final String INDONESIA = "indonesia";
    public static final String BANJAR = "banjar";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public void createTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS kamus");
        db.execSQL("CREATE TABLE if not exists kamus (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " indonesia TEXT, inggris TEXT, banjar TEXT);");
    }

    public void generateData(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(INDONESIA,"Saya");
        cv.put(INGGRIS,"I");
        cv.put(BANJAR,"Ulun");db.insert("kamus", INDONESIA, cv);
        cv.put(INDONESIA,"Beli");
        cv.put(INGGRIS,"Buy");
        cv.put(BANJAR,"Nukar");
        db.insert("kamus", INGGRIS, cv);
        cv.put(INDONESIA,"Ingin");
        cv.put(INGGRIS,"Want");
        cv.put(BANJAR,"Handak");
        db.insert("kamus", BANJAR, cv);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
        generateData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createTable(db);
        generateData(db);
    }
}
