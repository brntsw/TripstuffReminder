package com.mit.bruno.tripstuffreminder.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by techresult on 22/01/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tripstuffreminder.db";
    private static final int DATABASE_VERSION = 1;

    //Common column names
    public static final String COLUMN_ID = "id";

    //Table TRIP and its Columns
    public static final String TABLE_TRIP = "trip";
    public static final String COLUMN_TRIP_COUNTRY = "country";
    public static final String COLUMN_TRIP_STATE = "state";
    public static final String COLUMN_TRIP_CITY = "city";
    public static final String COLUMN_TRIP_ADDRESS = "address";
    public static final String COLUMN_TRIP_TRANSPORTATION = "transportation";
    public static final String COLUMN_TRIP_DEPARTURE_DATE = "departure_date";
    public static final String COLUMN_TRIP_ARRIVAL_DATE = "arrival_date";
    public static final String COLUMN_TRIP_DEPARTURE_TIME = "departure_time";
    public static final String COLUMN_TRIP_ARRIVAL_TIME = "arrival_time";

    //Creation SQL query
    private static final String TABLE_TRIP_CREATE = "CREATE TABLE " + TABLE_TRIP + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_TRIP_COUNTRY + " VARCHAR(100) NOT NULL, " +
            COLUMN_TRIP_STATE + " VARCHAR(100)," +
            COLUMN_TRIP_CITY + " VARCHAR(100) NOT NULL," +
            COLUMN_TRIP_ADDRESS + "TEXT," +
            COLUMN_TRIP_TRANSPORTATION + "VARCHAR(50)," +
            COLUMN_TRIP_DEPARTURE_DATE + "VARCHAR(10)," +
            COLUMN_TRIP_ARRIVAL_DATE + "VARCHAR(10)," +
            COLUMN_TRIP_DEPARTURE_TIME + "VARCHAR(10)," +
            COLUMN_TRIP_ARRIVAL_TIME + "VARCHAR(10));";

    //Table TRIP_ITEMS and its columns
    public static final String TABLE_TRIP_ITEMS = "trip_items";
    public static final String COLUMN_TRIP_ITEMS_ID_TRIP = "id_trip";
    public static final String COLUMN_TRIP_ITEMS_NAME = "name";

    private static final String TABLE_TRIP_ITEMS_CREATE = "CREATE TABLE " + TABLE_TRIP_ITEMS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_TRIP_ITEMS_ID_TRIP + " INTEGER, " +
            COLUMN_TRIP_ITEMS_NAME + " TEXT);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(TABLE_CREATE);
        Log.i("TABLE CREATE " + TABLE_TRIP, TABLE_TRIP_CREATE);
        Log.i("TABLE CREATE " + TABLE_TRIP_ITEMS, TABLE_TRIP_ITEMS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(), "Database upgrade from version " + oldVersion + " to " + newVersion);
        //db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        //onCreate(db);
    }
}
