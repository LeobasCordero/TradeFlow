package com.pedron.tradeflow.tradeflow.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.adapters.AdapterStores;
import com.pedron.tradeflow.tradeflow.entity.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by leocg on 11/03/2016.
 */
public class StoresActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter adapter;
    EditText inputSearch;
//    ArrayList<HashMap<String, String>> storeList;
    List<Store> storeList;


    // Listview Data
    String stores[] = {
            "Ruiz Cortinez","Constitucion","Morones Prieto","Centro Apodaca","Cuahutemoc",
            "Santa Rosa","Azteca","Vicente Guerrero","Churubusco","Los Angeles","San Jose",
            "Plaza Linda Vista","Aeropuerto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);

        lv = (ListView) findViewById(R.id.list_view_stores);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);

        storeList = getStores();
        // Adding items to listview
        adapter = new AdapterStores(this, storeList);
        lv.setAdapter(adapter);
/**
 * TODO El texto descriptivo ya no funciona
 */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


    }

    public List<Store> getStores(){
        List<Store> stores = new ArrayList<>();

        Store s = new Store("Ruiz Cortinez", "31122", "Walmart", "Supercenter", "Circuito", "2214", "Pablo Livas");
        stores.add(s);
        s = new Store("Morones Prieto", "1122", "Cadena", "Formato", "Calle Principal", "1020", "Tres Caminos");
        stores.add(s);
        s = new Store("Centro Apodaca", "1233", "Soriana", "Supercenter", "Miguel Aleman", "S/N", "Zozaya");
        stores.add(s);
        s = new Store("Constitucion", "3005", "Modelorama", "Minisuper", "Lopez Mateos", "2005", "Juan Pablo II");
        stores.add(s);
        s = new Store("Cuahutemoc", "605", "Mi Tiendita", "Supercenter", "Paricutin", "400", "Ave Roma");
        stores.add(s);
        s = new Store("Churubusco", "3500", "Comercial Treviño", "Minicuper", "Garza Sada", "S/N", "Juarez");
        stores.add(s);

        return stores;
    }

}
