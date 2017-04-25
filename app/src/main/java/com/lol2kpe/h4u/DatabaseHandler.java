package com.lol2kpe.h4u;

/**
 * Created by davidfogelberg on 2017-04-20.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHandler extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "userManager";

        // Table name
        private static final String TABLE_PLACES = "places";

        // Columns name for place table
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_LA = "latitude";
        private static final String KEY_LO = "longitude";

        public DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Create table: "places"
        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_PLACES  + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                    + KEY_LA + " TEXT," + KEY_LO + " TEXT" +  ")";
            db.execSQL(CREATE_PLACES_TABLE);
        }

        // Upgrade database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
            onCreate(db);
        }

        // Add a new place
        public void addPlace(DatabaseUserPlaces userPlace) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, userPlace.getName());
            values.put(KEY_LA,   userPlace.getLatitude());
            values.put(KEY_LO,   userPlace.getLongitude());

            db.insert(TABLE_PLACES , null, values);
            db.close();
        }

        // Delete a place
        public void deletePlace(DatabaseUserPlaces userPlace) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_PLACES, KEY_ID + " = ?",
                    new String[] { String.valueOf(userPlace.getId()) });
            db.close();
        }

        // Retrieve a place
        DatabaseUserPlaces getPlace(int id) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_PLACES, new String[] { KEY_ID,
                            KEY_NAME, KEY_LA }, KEY_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            DatabaseUserPlaces place = new DatabaseUserPlaces(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
            return place;
        }

        // Get all places
        public List<DatabaseUserPlaces> getAllPlaces() {
            List<DatabaseUserPlaces> placeList = new ArrayList<DatabaseUserPlaces>();
            String selectQuery = "SELECT  * FROM " + TABLE_PLACES;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    DatabaseUserPlaces userPlace = new DatabaseUserPlaces();
                    userPlace.setId(Integer.parseInt(cursor.getString(0)));
                    userPlace.setName(cursor.getString(1));
                    userPlace.setLatitude(cursor.getDouble(2));
                    userPlace.setLongitude(cursor.getDouble(3));

                    placeList.add(userPlace);
                } while (cursor.moveToNext());
            }

            // return a list of places
            return placeList;
        }

        // Update a place's information
        public int updatePlace(DatabaseUserPlaces userPlace) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, userPlace.getName());
            values.put(KEY_LA, userPlace.getLatitude());
            values.put(KEY_LO, userPlace.getLongitude());

            // updating row
            return db.update(TABLE_PLACES, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(userPlace.getId()) });
        }

        // Count all places
        public int getPlaceCount() {
            String countQuery = "SELECT  * FROM " + TABLE_PLACES;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            return cursor.getCount();
        }
    }




