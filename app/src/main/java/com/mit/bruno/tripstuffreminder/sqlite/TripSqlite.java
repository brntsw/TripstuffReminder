package com.mit.bruno.tripstuffreminder.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by techresult on 24/03/2015.
 */
public class TripSqlite {
    private SQLiteDatabase database;
    private SQLiteHelper helper;
    private String[] colunas = {SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_TRIP_COUNTRY,
            SQLiteHelper.COLUMN_TRIP_STATE,
            SQLiteHelper.COLUMN_TRIP_CITY,
            SQLiteHelper.COLUMN_TRIP_ADDRESS,
            SQLiteHelper.COLUMN_TRIP_TRANSPORTATION,
            SQLiteHelper.COLUMN_TRIP_DEPARTURE_DATETIME,
            SQLiteHelper.COLUMN_TRIP_ARRIVAL_DATETIME};

    public TripSqlite(Context context){
        helper = new SQLiteHelper(context);
    }

    public void open(){
        database = helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    public long insertTrip(){
        return 0;
    }
}
