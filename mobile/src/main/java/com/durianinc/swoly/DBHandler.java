package com.durianinc.swoly;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "swolyItems";

    // Contacts table name
    private String TABLE_NAME = "weight";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_VALUE = "value";

//     Constructor
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(Context context, String table) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.TABLE_NAME = table;
        if(!doesTableExist(table)&&!table.equals("weight")) {
            createTable();
        }
    }


    // Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_VALUE + " INTEGER" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    public void createTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_VALUE + " INTEGER" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    // Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void addItem(DatabaseItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VALUE, item.getValue()); // Item value

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<DatabaseItem> getAllItems() {
        ArrayList<DatabaseItem> itemList = new ArrayList<DatabaseItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DatabaseItem item = new DatabaseItem();
                item.setID(Integer.parseInt(cursor.getString(0)));
                item.setValue(Integer.parseInt(cursor.getString(1)));
                // Adding item to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return itemList;
    }

    public ArrayList<String> listAllTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> arrTblNames = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                String table = c.getString( c.getColumnIndex("name"));
                if(!table.equals("weight") && !table.equals("android_metadata")) { //will not return the weight table in the array
                    arrTblNames.add(c.getString(c.getColumnIndex("name")));
                }
                c.moveToNext();
            }
        }
        c.close();
        return arrTblNames;
    }

    public boolean doesTableExist(String tableName) {
        ArrayList<String> tables = listAllTables();

        for (int i=0; i<tables.size();i++) {
            if (tableName.equals(tables.get(i))) {
                return true;
            }
        }
        return false;
    }
}
