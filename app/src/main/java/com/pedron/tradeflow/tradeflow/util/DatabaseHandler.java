package com.pedron.tradeflow.tradeflow.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pedron.tradeflow.tradeflow.entity.Store;
import com.pedron.tradeflow.tradeflow.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leocg on 19/03/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "tradeflow.db";

    // Tables name
    private static final String USUARIOS_TRADEFLOW_TABLE = "usuarios_tradeflow";
    private static final String TIENDAS_TRADEFLOW_TABLE = "tiendas_tradeflow";

    // Users Table Columns names
    private static final String USUARIO = "nombre_usuario";
    private static final String CONTRASENA = "contrasena";
    private static final String PERFIL = "perfil";
    private static final String ESTATUS = "estatus";

    // Sstores Table Column names
    private static final String NUM_TIENDAS = "numero_tiendas";
    private static final String NOM_TIENDA = "nombre_tienda";
    private static final String CADENA = "cadena";
    private static final String FORMATO = "formato";
    private static final String CALLE = "calle";
    private static final String ID_TIENDA = "id_tienda";
    private static final String COLONIA = "colonia";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIOS_TABLE = "CREATE TABLE " + USUARIOS_TRADEFLOW_TABLE + "("
                + USUARIO + " TEXT, " + CONTRASENA + " TEXT, "
                + PERFIL + " TEXT, " + ESTATUS + " TEXT);";
        db.execSQL(CREATE_USUARIOS_TABLE);

        String CREATE_TIENDAS_TABLE = "CREATE TABLE " + TIENDAS_TRADEFLOW_TABLE + "("
                + NUM_TIENDAS + " TEXT, " + NOM_TIENDA + " TEXT, "
                + CADENA + " TEXT, " + FORMATO + " TEXT, " + ID_TIENDA + " TEXT, "
                + CALLE + " TEXT, " + COLONIA + " TEXT);";
        db.execSQL(CREATE_TIENDAS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USUARIOS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TIENDAS_TRADEFLOW_TABLE);
        // Create tables again
        onCreate(db);
    }

    // Adding new User
    public void AddUserTradeflow(String u, String c, String p, String e) {
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

    // Adding new Store
    public void AddStoreTradeflow(String num, String nom, String cad, String fo,
                                  String id, String ca, String co) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NUM_TIENDAS, num);
        values.put(NOM_TIENDA, nom);
        values.put(CADENA, cad);
        values.put(FORMATO, fo);
        values.put(ID_TIENDA, id);
        values.put(CALLE, ca);
        values.put(COLONIA, co);
        // Inserting Row
        db.insert(TIENDAS_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Getting Users
    public List<User> getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> listUsers = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + USUARIO + ", " + CONTRASENA +
                ", " + PERFIL + ", " + ESTATUS + " FROM " + USUARIOS_TRADEFLOW_TABLE + "; ", null);

        if (cursor.moveToFirst()) {
            do {
                User u = new User();
                u.setUsuario(cursor.getString(0));
                u.setContrasena(cursor.getString(1));
                u.setPerfil(cursor.getString(2));
                u.setEstatus(cursor.getString(3));
                listUsers.add(u);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listUsers;
    }

    // Getting Stores
    public List<Store> getStores() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Store> listStores = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + NUM_TIENDAS + ", " + NOM_TIENDA +
                ", " + CADENA + ", " + FORMATO + ", " + ID_TIENDA + ", " + CALLE +
                ", " + COLONIA + " FROM " + TIENDAS_TRADEFLOW_TABLE + "; ", null);

        if (cursor.moveToFirst()) {
            do {
                Store u = new Store();
                u.setNumTiendas(cursor.getString(0));
                u.setNombreTienda(cursor.getString(1));
                u.setCadena(cursor.getString(2));
                u.setFormato(cursor.getString(3));
                u.setIdTienda(cursor.getString(4));
                u.setCalle(cursor.getString(5));
                u.setColonia(cursor.getString(6));
                listStores.add(u);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listStores;
    }

}
