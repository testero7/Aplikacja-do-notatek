package com.example.apki_projekt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.TEMAT, name);
        contentValue.put(DBHelper.NOTATKA, desc);
        database.insert(DBHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns =
                new String[] {
                        DBHelper._ID,
                        DBHelper.TEMAT,
                        DBHelper.NOTATKA };


        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                columns, null, null,
                null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TEMAT, name);
        contentValues.put(DBHelper.NOTATKA, desc);
        int i = database.update(DBHelper.TABLE_NAME,
                contentValues,
                DBHelper._ID + " = " + _id,
                null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME,
                DBHelper._ID + "=" + _id,
                null);
    }

}