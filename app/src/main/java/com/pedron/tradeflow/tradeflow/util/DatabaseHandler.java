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

    // Alerts Table Columns names
    private static final String ALERTA = "alerta";

    // Activities Table Columns names
    private static final String ACTIVIDAD = "actividad";
    private static final String ID_CLIENTE = "id_cliente";

    // Visits Table Column names
    private static final String FECHA = "fecha";
    private static final String UBICACION = "ubicacion";


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

        String CREATE_USUARIOS_TABLE = "CREATE TABLE " + USUARIOS_TRADEFLOW_TABLE + "("
                + USUARIO + " TEXT, " + CONTRASENA + " TEXT, "
                + PERFIL + " TEXT, " + ESTATUS + " TEXT);";
        db.execSQL(CREATE_USUARIOS_TABLE);

        String CREATE_TIENDAS_TABLE = "CREATE TABLE " + TIENDAS_TRADEFLOW_TABLE + "("
                + NUM_TIENDAS + " TEXT, " + NOM_TIENDA + " TEXT, "
                + CADENA + " TEXT, " + FORMATO + " TEXT, " + ID_TIENDA + " TEXT, "
                + DIRECCION + " TEXT);";
        db.execSQL(CREATE_TIENDAS_TABLE);

        String CREATE_ALERTAS_TABLE = "CREATE TABLE " + ALERTAS_TRADEFLOW_TABLE + "("
                + ID_TIENDA + " TEXT, " + ALERTA + " TEXT, " + PERFIL + " TEXT);";
        db.execSQL(CREATE_ALERTAS_TABLE);

        String CREATE_ACTIVIDADES_TABLE = "CREATE TABLE " + ACTIVIDADES_TRADEFLOW_TABLE + "("
                + PERFIL + " TEXT, " + ACTIVIDAD + " TEXT, " + ID_CLIENTE + " TEXT);";
        db.execSQL(CREATE_ACTIVIDADES_TABLE);
        //populateDB(db);
        String CREATE_VISITAS_TABLE = "CREATE TABLE " + VISITAS_TRADEFLOW_TABLE + "("
                + USUARIO + " TEXT, " + FECHA + " TEXT, " + UBICACION + " TEXT, " + ID_TIENDA + " TEXT, "
                + ESTATUS + " TEXT);";
        db.execSQL(CREATE_VISITAS_TABLE);

    }

    public void populateDB(){

        SQLiteDatabase db = this.getReadableDatabase();
        addUserTradeflow("12345", "admin123", "admin", "0");

        addStoreTradeflow("441", "Ruiz Cortinez(1522)", "Walmart", "Supermercado", "1225", "Rodesia del Nte #402, col. San Roque, cp. 65008, San Pedro, Nuevo Leon");
        addStoreTradeflow("610", "Centro(3323)", "Soriana", "Almacen", "855", "Jacarandas #233, col. Linda Vista, cp. 63005, Guadalupe, Nuevo Leon");
        addStoreTradeflow("9405", "Los Angeles(1982)", "HEB", "Supermercado", "322", "Romulo Garza #2988, col. Los Morales, cp. 24452, San Nicolas de los Garza, Nuevo Leon");
        addStoreTradeflow("102", "Escobedo(3004)", "OXXO", "CEDIS", "650", "Lazaro Cardenas #2011, col. Centrito Valle, cp. 66305, San Pedro, Nuevo Leon");
        addStoreTradeflow("3365", "Universidad(1800)", "Famosa", "Almacen", "100", "Cuahutemoc #522, col. Centro, cp.68000, Monterrey, Nuevo Leon");
        addStoreTradeflow("6631", "Juarez(9085)", "Comercial Treviño", "Almacen", "600", "Mariano Escobedo #2223, col. Santa Fe, cp. 69908, Santa Catarina, Nuevo Leon");

        addAlertTradeflow("100", "SQL completo, donde indicamos los campos", "0");
        addAlertTradeflow("100", "La polémica por la manera en la que son tratados los refugiados ", "0");
        addAlertTradeflow("100", "Developing a custom adapter", "0");
        addAlertTradeflow("322", "Como en el caso de los métodos de modificación de datos", "0");
        addAlertTradeflow("650", "Tutorial describes how to use the ListView view together with activities and fragments in A", "0");
        addAlertTradeflow("650", "Above syntax is calling startActivity method", "0");
        addAlertTradeflow("1225", "Intent is an abstract description of an operation", "0");
        addAlertTradeflow("855", "Existen 5 continentes, no 2. solo por que seas un continente con gran cultura", "0");
        addAlertTradeflow("855", "Durante un breve periodo de tiempo la solidaridad con el pueblo", "0");
        addAlertTradeflow("600", "Todo comenzó mal con el nuevo plan de Hoy No Circula, pues en el primer día de operación se registraron nuevamente niveles altos de partículas", "0");
        addAlertTradeflow("600", "Se registró un índice de contaminación superior a los 150 puntos IMECA", "0");
        addAlertTradeflow("600", "Entre las medidas que se podrían aplicar en la Fase 1 de Contingencia, se contempla el Doble No Circula", "0");
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
        // Create tables again
        onCreate(db);
    }

    public int checkTables(String table){
        int x = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT  *  FROM " + table + "; ", null);

        if (cursor.moveToFirst()) {
            x = 1;
        }
Log.i("Leobas", x + " valor de la x");
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
                                  String id, String di) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NUM_TIENDAS, num);
        values.put(NOM_TIENDA, nom);
        values.put(CADENA, cad);
        values.put(FORMATO, fo);
        values.put(ID_TIENDA, id);
//        values.put(CALLE, ca);
        values.put(DIRECCION, di);
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
                ", " + CADENA + ", " + FORMATO + ", " + ID_TIENDA + ", " + DIRECCION +
                " FROM " + TIENDAS_TRADEFLOW_TABLE + "; ", null);

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
                Log.i("Leobas",cursor.getString(0));
                /*Alert alert = new Alert();
                alert.setId_tienda(cursor.getString(0));
                alert.setAlerta(cursor.getString(1));
                alert.setPerfil(cursor.getString(2));
                listAlerts.add(alert);*/
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

}
