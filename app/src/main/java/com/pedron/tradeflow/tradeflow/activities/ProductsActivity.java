package com.pedron.tradeflow.tradeflow.activities;

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

import java.util.List;

/**
 * Created by leocg on 17/03/2016.
 */
public class ProductsActivity extends AppCompatActivity {

    ListView listProducts;
    EditText inputSearch;
    ArrayAdapter<String> adapter;
    String products[] = {
            "Hinds Clasica","Hinds Mama","Cloralex 250ml","Pinol 1L","Jabon Zest 180g", "Fabuloso 500ml",
            "Head & Shoulders 970ml","Jabon Polvo Foca","Rocainol","Ariel 500g","Alcohol San Jose",
            "Cerillos Talisman","Sardinas El Puerto"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        listProducts = (ListView) findViewById(R.id.list_view_products);
        inputSearch = (EditText) findViewById(R.id.search_products);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,products);
        listProducts.setAdapter(adapter);

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

    }
