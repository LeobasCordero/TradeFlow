package com.pedron.tradeflow.tradeflow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.util.DatabaseHandler;

import java.util.List;

/**
 * Created by leocg on 17/03/2016.
 */
public class ProductsActivity extends AppCompatActivity {

    ListView listProducts;

    TextView textView;// , candimon, pasta, hipo;
    ArrayAdapter<String> adapter;
    String idMarca, opMenu;
    List productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        listProducts = (ListView) findViewById(R.id.list_view_products);

        //Header
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);
        textView = (TextView) findViewById(R.id.screen_title);
        textView.setText(R.string.products_header);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        idMarca = b.getString("idMarca");
        opMenu = b.getString("opMenu");
//        productText = (TextView) findViewById(R.id.product_center);
//        candimon = (TextView)findViewById(R.id.candimon);
//        pasta = (TextView)findViewById(R.id.pasta);
//        hipo = (TextView)findViewById(R.id.hipo);

        /*candimon.setOnClickListener(new View.OnClickListener() {
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
                startActivity(act);
            }
        });*/
        DatabaseHandler db = new DatabaseHandler(this);
        productList = db.getProducts(idMarca);

        adapter = new ArrayAdapter<String>(this, R.layout.custom_list_element ,R.id.product_center, productList);
        listProducts.setAdapter(adapter);

        listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int op = Integer.valueOf(opMenu);

                switch (op){
                    case 1:
                        Intent act = new Intent(ProductsActivity.this, PisoPresentacionActivity.class);
                        act.putExtra("producto", productList.get(position).toString());
                        act.putExtra("idProducto", position);
                        act.putExtra("idMarca",idMarca);
                        act.putExtra("opMenu",opMenu);
                        startActivity(act);
                        break;
                    case 2:
                        act = new Intent(ProductsActivity.this, BodegaActivity.class);
                        act.putExtra("producto", productList.get(position).toString());
                        act.putExtra("idProducto", position);
                        act.putExtra("idMarca",idMarca);
                        act.putExtra("opMenu",opMenu);
                        startActivity(act);
                        break;
                    case 3:
                        act = new Intent(ProductsActivity.this, ResurtidoActivity.class);
                        act.putExtra("producto", productList.get(position).toString());
                        act.putExtra("idProducto", position);
                        act.putExtra("idMarca",idMarca);
                        act.putExtra("opMenu",opMenu);
                        startActivity(act);
                        break;
                    case 4:
                        act = new Intent(ProductsActivity.this, PrecioActivity.class);
                        act.putExtra("producto", productList.get(position).toString());
                        act.putExtra("idProducto", position);
                        act.putExtra("idMarca",idMarca);
                        act.putExtra("opMenu",opMenu);
                        startActivity(act);
                        break;
                    default:
                        setContentView(R.layout.no_elements_found);
                        break;

                }
            }
        });
    }

    }
