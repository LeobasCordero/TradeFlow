package com.pedron.tradeflow.tradeflow.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pedron.tradeflow.tradeflow.entity.Alert;
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
    private static final int DATABASE_VERSION = Constant.DBVERSION;

    // Database Name
    private static final String DATABASE_NAME = "tradeflow.db";

    // Tables name
    private static final String USUARIOS_TRADEFLOW_TABLE = "usuarios_tradeflow";
    private static final String TIENDAS_TRADEFLOW_TABLE = "tiendas_tradeflow";
    private static final String ALERTAS_TRADEFLOW_TABLE = "alertas_tradeflow";
    private static final String ACTIVIDADES_TRADEFLOW_TABLE = "actividades_tradeflow";
    private static final String VISITAS_TRADEFLOW_TABLE = "visitas_tradeflow";
    private static final String NOTICIAS_TRADEFLOW_TABLE = "noticias_tradeflow";
    private static final String CLIENTES_TRADEFLOW_TABLE = "clientes_tradeflow";
    private static final String PRODUCTOS_TRADEFLOW_TABLE = "productos_tradeflow";

    // Users Table Columns names
    private static final String USUARIO = "nombre_usuario";
    private static final String CONTRASENA = "contrasena";
    private static final String PERFIL = "perfil";
    private static final String ESTATUS = "estatus";

    // Stores Table Column names
    private static final String NUM_TIENDAS = "numero_tiendas";
    private static final String NOM_TIENDA = "nombre_tienda";
    private static final String CADENA = "cadena";
    private static final String FORMATO = "formato";
//    private static final String CALLE = "calle";
    private static final String ID_TIENDA = "id_tienda";
    private static final String DIRECCION = "direccion";
    private static final String CRUZ = "cruz";

    // Alerts Table Columns names
    private static final String ALERTA = "alerta";

    // Activities Table Columns names
    private static final String ACTIVIDAD = "actividad";
    private static final String ID_CLIENTE = "id_cliente";

    // Visits Table Column names
    private static final String FECHA = "fecha";
    private static final String UBICACION = "ubicacion";

    // News Table Columns names
    private static final String NOTICIA = "noticia";

    // Products Table Columns names
    private static final String PRODUCTO = "producto";
    private static final String ID_PRODUCTO = "id_producto";
    private static final String ID_MARCA = "id_marca";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + USUARIOS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TIENDAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ALERTAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACTIVIDADES_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VISITAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NOTICIAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTES_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTOS_TRADEFLOW_TABLE);

        String CREATE_USUARIOS_TABLE = "CREATE TABLE " + USUARIOS_TRADEFLOW_TABLE + "("
                + USUARIO + " TEXT, " + CONTRASENA + " TEXT, "
                + PERFIL + " TEXT, " + ESTATUS + " TEXT);";
        db.execSQL(CREATE_USUARIOS_TABLE);

        String CREATE_TIENDAS_TABLE = "CREATE TABLE " + TIENDAS_TRADEFLOW_TABLE + "("
                + NUM_TIENDAS + " TEXT, " + NOM_TIENDA + " TEXT, "
                + CADENA + " TEXT, " + FORMATO + " TEXT, " + ID_TIENDA + " TEXT, "
                + DIRECCION + " TEXT, " + USUARIO + " TEXT, " + CRUZ + " TEXT);";
        db.execSQL(CREATE_TIENDAS_TABLE);

        String CREATE_ALERTAS_TABLE = "CREATE TABLE " + ALERTAS_TRADEFLOW_TABLE + "("
                + ID_TIENDA + " TEXT, " + ALERTA + " TEXT, " + PERFIL + " TEXT);";
        db.execSQL(CREATE_ALERTAS_TABLE);

        String CREATE_ACTIVIDADES_TABLE = "CREATE TABLE " + ACTIVIDADES_TRADEFLOW_TABLE + "("
                + PERFIL + " TEXT, " + ACTIVIDAD + " TEXT, " + ID_CLIENTE + " TEXT);";
        db.execSQL(CREATE_ACTIVIDADES_TABLE);

        String CREATE_VISITAS_TABLE = "CREATE TABLE " + VISITAS_TRADEFLOW_TABLE + "("
                + USUARIO + " TEXT, " + FECHA + " TEXT, " + UBICACION + " TEXT, " + ID_TIENDA + " TEXT, "
                + ESTATUS + " TEXT);";
        db.execSQL(CREATE_VISITAS_TABLE);

        String CREATE_NOTICIAS_TABLE = "CREATE TABLE " + NOTICIAS_TRADEFLOW_TABLE + "("
                + ID_CLIENTE + " TEXT, " + NOTICIA + " TEXT);";
        db.execSQL(CREATE_NOTICIAS_TABLE);

        String CREATE_CLIENTES_TABLE = "CREATE TABLE " + CLIENTES_TRADEFLOW_TABLE + "("
                + ID_TIENDA + " TEXT, " + ID_CLIENTE + " TEXT);";
        db.execSQL(CREATE_CLIENTES_TABLE);

        String CREATE_PRODUCTOS_TABLE = "CREATE TABLE " + PRODUCTOS_TRADEFLOW_TABLE + "("
                + ID_PRODUCTO + " TEXT, " + ID_MARCA + " TEXT, " + PRODUCTO + " TEXT);";
        db.execSQL(CREATE_PRODUCTOS_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USUARIOS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TIENDAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ALERTAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ACTIVIDADES_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + VISITAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NOTICIAS_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTES_TRADEFLOW_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCTOS_TRADEFLOW_TABLE);
        // Create tables again
        onCreate(db);
    }

    public int checkTables(String table){
        int x = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT  *  FROM " + table + "; ", null);

        if (cursor.moveToFirst()) {
            x = 2;
        }

        cursor.close();
        db.close();

        return x;
    }

    // Adding new User
    public void addUserTradeflow(String u, String c, String p, String e) {
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
    public void addStoreTradeflow(String num, String nom, String cad, String fo,
                                  String id, String di, String us, String cr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NUM_TIENDAS, num);
        values.put(NOM_TIENDA, nom);
        values.put(CADENA, cad);
        values.put(FORMATO, fo);
        values.put(ID_TIENDA, id);
//        values.put(CALLE, ca);
        values.put(DIRECCION, di);
        values.put(USUARIO, us);
        values.put(CRUZ, cr);
        // Inserting Row
        db.insert(TIENDAS_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new Alerts
    public void addAlertTradeflow(String i, String a, String p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_TIENDA, i);
        values.put(ALERTA, a);
        values.put(PERFIL, p);
        // Inserting Row
        db.insert(ALERTAS_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public void addActivityTradeflow(String p, String a, String i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PERFIL, p);
        values.put(ACTIVIDAD, a);
        values.put(ID_CLIENTE, i);
        // Inserting Row
        db.insert(ACTIVIDADES_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new Visit
    public void addVisitTradeflow(String u, String f, String ub, String t, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USUARIO, u);
        values.put(FECHA, f);
        values.put(UBICACION, ub);
        values.put(ID_TIENDA, t);
        values.put(ESTATUS, s);
        // Inserting Row
        db.insert(VISITAS_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new News
    public void addNewsTradeflow(String i, String n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_CLIENTE, i);
        values.put(NOTICIA, n);

        // Inserting Row
        db.insert(NOTICIAS_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new Clients
    public void addClientTradeflow(String it, String ic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_TIENDA, it);
        values.put(ID_CLIENTE, ic);
        // Inserting Row
        db.insert(CLIENTES_TRADEFLOW_TABLE, null, values);
        db.close(); // Closing database connection
    }

    // Adding new Product
    public void addProductTradeflow(String ip, String im, String p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_PRODUCTO, ip);
        values.put(ID_MARCA, im);
        values.put(PRODUCTO, p);

        // Inserting Row
        db.insert(PRODUCTOS_TRADEFLOW_TABLE, null, values);
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

    // Getting All Stores
    public List<Store> getStores(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Store> listStores = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + NUM_TIENDAS + ", " + NOM_TIENDA +
                ", " + CADENA + ", " + FORMATO + ", " + ID_TIENDA + ", " + DIRECCION + ", " + USUARIO +
                ", " + CRUZ + " FROM " + TIENDAS_TRADEFLOW_TABLE + " WHERE " + USUARIO + " != '" + user + "' ; ", null);

        if (cursor.moveToFirst()) {
            do {
                Store u = new Store();
                u.setNumTiendas(cursor.getString(0));
                u.setNombreTienda(cursor.getString(1));
                u.setCadena(cursor.getString(2));
                u.setFormato(cursor.getString(3));
                u.setIdTienda(cursor.getString(4));
//                u.setCalle(cursor.getString(5));
                u.setDireccion(cursor.getString(5));
                u.setUsuario(cursor.getString(6));
                u.setCruz(cursor.getString(7));
                listStores.add(u);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listStores;
    }

    // Getting Stores per user
    public List<Store> getStoresPerUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Store> listStores = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + NUM_TIENDAS + ", " + NOM_TIENDA +
                ", " + CADENA + ", " + FORMATO + ", " + ID_TIENDA + ", " + DIRECCION + ", " + USUARIO +
                ", " + CRUZ + " FROM " + TIENDAS_TRADEFLOW_TABLE + " WHERE " + USUARIO + " = '" + user + "' ; ", null);

        if (cursor.moveToFirst()) {
            do {
                Store u = new Store();
                u.setNumTiendas(cursor.getString(0));
                u.setNombreTienda(cursor.getString(1));
                u.setCadena(cursor.getString(2));
                u.setFormato(cursor.getString(3));
                u.setIdTienda(cursor.getString(4));
//                u.setCalle(cursor.getString(5));
                u.setDireccion(cursor.getString(5));
                u.setUsuario(cursor.getString(6));
                u.setCruz(cursor.getString(7));
                listStores.add(u);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listStores;
    }

    // Getting Alerts
    public List getAlerts(String idTienda) {
        SQLiteDatabase db = this.getReadableDatabase();
        List listAlerts = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + ALERTA + " FROM " + ALERTAS_TRADEFLOW_TABLE + " WHERE " +
                    ID_TIENDA + " = '" + idTienda + "' ; ", null);


        if (cursor.moveToFirst()) {
            do {
                listAlerts.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAlerts;
    }

    // Getting Activities
    public List<String> getActivities(String perfil, String idCliente) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> listActivities = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + ACTIVIDAD + " FROM " + ACTIVIDADES_TRADEFLOW_TABLE
                + " WHERE " + ID_CLIENTE + "='" + idCliente + "' AND " + PERFIL +
                "='" + perfil + "' ; ", null);

        if (cursor.moveToFirst()) {
            do {
                listActivities.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listActivities;
    }

    // Getting News
    public List getNews(String idCliente) {
        SQLiteDatabase db = this.getReadableDatabase();
        List listNews = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + NOTICIA + " FROM " + NOTICIAS_TRADEFLOW_TABLE + " WHERE " +
                ID_CLIENTE + " = '" + idCliente + "' ; ", null);

        if (cursor.moveToFirst()) {
            do {
                Log.i("Leobas",cursor.getString(0));
                listNews.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }else{
            Log.i("Leobas", "nada");
        }

        cursor.close();
        db.close();

        return listNews;
    }

    // Getting Clients
    public List getClients(String idTienda) {
        SQLiteDatabase db = this.getReadableDatabase();
        List listClients = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + ID_CLIENTE + " FROM " + CLIENTES_TRADEFLOW_TABLE + " WHERE " +
                ID_TIENDA + " = '" + idTienda + "' ; ", null);


        if (cursor.moveToFirst()) {
            do {
                Log.i("Leobas", cursor.getString(0));
                listClients.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listClients;
    }

    // Getting Clients
    public List getProducts(String idMarca) {
        SQLiteDatabase db = this.getReadableDatabase();
        List listProductos = new ArrayList<>();

        Cursor cursor = db.rawQuery(" SELECT " + PRODUCTO + " FROM " + PRODUCTOS_TRADEFLOW_TABLE + " WHERE " +
                ID_MARCA + " = '" + idMarca + "' ; ", null);

        if (cursor.moveToFirst()) {
            do {
                Log.i("Leobas", cursor.getString(0));
                listProductos.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listProductos;
    }
}
