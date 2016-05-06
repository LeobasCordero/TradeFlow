package com.pedron.tradeflow.tradeflow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;

/**
 * Created by leocg on 17/03/2016.
 */
public class MenuActivity extends AppCompatActivity {

    ImageView piso, bodega, resurtido, precio;
    TextView textView;
    String idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);
        textView = (TextView) findViewById(R.id.screen_title);
        textView.setText(R.string.activities_header);

        piso = (ImageView)findViewById(R.id.piso_clic);
        bodega = (ImageView)findViewById(R.id.bodega_clic);
        resurtido = (ImageView)findViewById(R.id.resurtido_clic);
        precio = (ImageView)findViewById(R.id.precio_clic);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        idCliente = b.getString("idCliente");

        piso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img1 = (ImageView)findViewById(R.id.bodega_clic_gris);
                img1.setVisibility(View.INVISIBLE);
                ImageView img2 = (ImageView)findViewById(R.id.bodega_clic);
                img2.setVisibility(View.VISIBLE);

                Intent launchActivity = new Intent(MenuActivity.this, NewsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("idCliente", idCliente);
                extras.putString("opMenu", "1");
                launchActivity.putExtras(extras);
                startActivity(launchActivity);
            }
        });

        bodega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img3 = (ImageView)findViewById(R.id.resurtido_clic_gris);
                img3.setVisibility(View.INVISIBLE);
                ImageView img4 = (ImageView)findViewById(R.id.resurtido_clic);
                img4.setVisibility(View.VISIBLE);

                Intent launchActivity = new Intent(MenuActivity.this, TrademarkActivity.class);
                Bundle extras = new Bundle();
                extras.putString("opMenu", "2");
                launchActivity.putExtras(extras);
                startActivity(launchActivity);
            }
        });

        resurtido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivity = new Intent(MenuActivity.this, TrademarkActivity.class);
                Bundle extras = new Bundle();
                extras.putString("opMenu", "3");
                launchActivity.putExtras(extras);
                startActivity(launchActivity);
            }
        });

        precio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivity = new Intent(MenuActivity.this, NewsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("idCliente", idCliente);
                extras.putString("opMenu", "4");
                launchActivity.putExtras(extras);
                startActivity(launchActivity);
            }
        });


    }


    }
