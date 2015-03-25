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
    public static final String COLUMN_ID_TRIP = "id_trip";

    /* ---------------------------- Table TRIP and its Columns ---------------------------- */

    public static final String TABLE_TRIP = "trip";
    public static final String COLUMN_TRIP_COUNTRY = "country";
    public static final String COLUMN_TRIP_STATE = "state";
    public static final String COLUMN_TRIP_CITY = "city";
    public static final String COLUMN_TRIP_ADDRESS = "address";
    public static final String COLUMN_TRIP_TRANSPORTATION = "transportation";
    public static final String COLUMN_TRIP_DEPARTURE_DATETIME = "departure_date";
    public static final String COLUMN_TRIP_ARRIVAL_DATETIME = "arrival_date";

    //Creation SQL query
    private static final String TABLE_TRIP_CREATE = "CREATE TABLE " + TABLE_TRIP + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
            COLUMN_TRIP_COUNTRY + " VARCHAR(100) NOT NULL, " +
            COLUMN_TRIP_STATE + " VARCHAR(100)," +
            COLUMN_TRIP_CITY + " VARCHAR(100) NOT NULL," +
            COLUMN_TRIP_ADDRESS + " TEXT," +
            COLUMN_TRIP_TRANSPORTATION + " VARCHAR(50) NOT NULL," +
            COLUMN_TRIP_DEPARTURE_DATETIME + " VARCHAR(25)," +
            COLUMN_TRIP_ARRIVAL_DATETIME + " VARCHAR(25));";

    /* ----------------------------------------------------------------------------------- */

    /* -------------------------- Table TRIP_THINGS_TO_BRING and its columns ----------------------- */

    public static final String TABLE_TRIP_ITEMS = "trip_things_to_bring";
    public static final String COLUMN_TRIP_ITEMS_NAME = "name";

    //Creation SQL query
    private static final String TABLE_TRIP_ITEMS_CREATE = "CREATE TABLE " + TABLE_TRIP_ITEMS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_ID_TRIP + " INTEGER NOT NULL, " +
            COLUMN_TRIP_ITEMS_NAME + " TEXT, " +
            "FOREIGN KEY(" + COLUMN_ID_TRIP + ") REFERENCES " + TABLE_TRIP + "(" + COLUMN_ID + "));";

    /* --------------------------------------------------------------------------------------------- */

    /* --------------------------- Table TRIP_THINGS_TO_DO and its columns --------------------------- */

    public static final String TABLE_TRIP_THINGS_TO_DO = "trip_things_to_do";
    public static final String COLUMN_TRIP_THINGS_TO_DO_THING = "thing";
    public static final String COLUMN_TRIP_THINGS_TO_DO_DESCRIPTION = "description";
    public static final String COLUMN_TRIP_THINGS_TO_DO_DATETIME = "date_to_do";

    //Creation SQL query
    private static final String TABLE_TRIP_THINGS_TO_DO_CREATE = "CREATE TABLE " + TABLE_TRIP_THINGS_TO_DO + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
            COLUMN_ID_TRIP + " INTEGER, " +
            COLUMN_TRIP_THINGS_TO_DO_THING + " TEXT, " +
            COLUMN_TRIP_THINGS_TO_DO_DESCRIPTION + " TEXT, " +
            COLUMN_TRIP_THINGS_TO_DO_DATETIME + " VARCHAR(25), " +
            "FOREIGN KEY(" + COLUMN_ID_TRIP + ") REFERENCES " + TABLE_TRIP + "(" + COLUMN_ID + "));";

    /* ----------------------------------------------------------------------------------------------- */

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(TABLE_CREATE);
        Log.i("TABLE CREATE", "TABLE: " + TABLE_TRIP + " / Query: " + TABLE_TRIP_CREATE);
        Log.i("TABLE CREATE", "TABLE: " + TABLE_TRIP_ITEMS + " / Query: " + TABLE_TRIP_ITEMS_CREATE);
        Log.i("TABLE CREATE", "TABLE: " + TABLE_TRIP_THINGS_TO_DO + " / Query: " + TABLE_TRIP_THINGS_TO_DO_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelper.class.getName(), "Database upgrade from version " + oldVersion + " to " + newVersion);
        //db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
