package com.pedron.tradeflow.tradeflow.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by leocg on 19/03/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tradeflow.db";

    // Users table name
    private static final String USUARIOS_TRADEFLOW_TABLE = "usuarios_tradeflow";

    // Contacts Table Columns names
    private static final String USUARIO = "nombre_usuario";
    private static final String CONTRASENA = "contrasena";
    private static final String PERFIL = "perfil";
    private static final String ESTATUS = "estatus";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
//        Log.i(Constants.TAG_DATABASE_HANDLER, "Database ");
        String CREATE_USUARIOS_TABLE = "CREATE TABLE " + USUARIOS_TRADEFLOW_TABLE + "("
                + USUARIO + " TEXT," + CONTRASENA + " TEXT,"
                + PERFIL + " TEXT" + ESTATUS + " TEXT);";
        db.execSQL(CREATE_USUARIOS_TABLE);
//        Log.i(Constants.TAG_DATABASE_HANDLER, "Database created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USUARIOS_TRADEFLOW_TABLE);
        // Create tables again
        onCreate(db);
    }

    // Adding new User
    public void AddUserTradeflow(String u, String c, String p, String e) {
//        Log.i(Constants.TAG_DATABASE_HANDLER, "Database Add Location");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USUARIO, u);
        values.put(CONTRASENA, c);
        values.put(PERFIL, p);
        values.put(ESTATUS, e);
        // Inserting Row
        db.insert(USUARIOS_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Getting location
    /*public Boolean getUsers(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOCATION, new String[]{KEY_ID,
                        KEY_LATITUDE, KEY_LONGITUDE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Location location = new Location(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

//        Log.i(Constants.TAG_DATABASE_HANDLER, "lat " + location.getLatitude() + " lng " + location.getLongitude());
        cursor.close();
        db.close();

        return location;*/
//    }

}
