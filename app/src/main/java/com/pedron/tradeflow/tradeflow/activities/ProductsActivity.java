package com.pedron.tradeflow.tradeflow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;

/**
 * Created by leocg on 17/03/2016.
 */
public class ProductsActivity extends AppCompatActivity {
/*
    ListView listProducts;
    EditText inputSearch;
*/
    TextView textView, candimon, pasta, hipo;
    ArrayAdapter<String> adapter;
    String products[] = {
            "Hinds Clasica","Hinds Mama","Cloralex 250ml","Pinol 1L","Jabon Zest 180g", "Fabuloso 500ml",
            "Head & Shoulders 970ml","Jabon Polvo Foca","Rocainol","Ariel 500g","Alcohol San Jose",
            "Cerillos Talisman","Sardinas El Puerto"};

    public void onClick(View v) {
        Intent intent = new Intent(ProductsActivity.this, ResurtidoActivity.class);
        TextView tv = (TextView)v;
//        Log.i("producto", tv.getText().toString());
//        Log.i("producto", ((TextView) v).getText().toString());
        intent.putExtra("producto", ((TextView) v).getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
/*
        listProducts = (ListView) findViewById(R.id.list_view_products);
        inputSearch = (EditText) findViewById(R.id.search_products);
*/
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);
        textView = (TextView) findViewById(R.id.screen_title);
        textView.setText("Productos");

        candimon = (TextView)findViewById(R.id.candimon);
        pasta = (TextView)findViewById(R.id.pasta);
        hipo = (TextView)findViewById(R.id.hipo);

        candimon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(ProductsActivity.this, ResurtidoActivity.class);
//                TextView tv = (TextView)v;
                act.putExtra("producto", ((TextView) v).getText().toString());
                startActivity(act);
            }
        });

        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(ProductsActivity.this, BodegaActivity.class);
//                TextView tv = (TextView)v;
                act.putExtra("producto", ((TextView) v).getText().toString());
                startActivity(act);
            }
        });

        hipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(ProductsActivity.this, PisoPresentacionActivity.class);
//                TextView tv = (TextView)v;
                act.putExtra("producto", ((TextView) v).getText().toString());
                startActivity(act);
            }
        });

/*
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
        */
    }

    }
