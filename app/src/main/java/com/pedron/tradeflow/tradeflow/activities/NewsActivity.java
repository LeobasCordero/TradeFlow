package com.pedron.tradeflow.tradeflow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.util.DatabaseHandler;

import java.util.List;

/**
 * Created by leocg on 16/03/2016.
 */
public class NewsActivity extends AppCompatActivity {

    Button b;
    TextView textView, noElements;
    String idCliente;
    List noticias;
    ListView listViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);
        textView = (TextView) findViewById(R.id.screen_title);
        textView.setText("Noticias");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        idCliente = bundle.getString("idCliente");
        Log.i("Leobas", "idCliente " + idCliente);
        DatabaseHandler db = new DatabaseHandler(this);

        noticias = db.getNews(idCliente);
        listViewNews = (ListView)findViewById(R.id.list_view_news);

        if(null != noticias && 0 < noticias.size()) {
            Log.i("Leobas", "entro");
            listViewNews.setAdapter(new ArrayAdapter<String>(
                    this, R.layout.list_item_new, R.id.text_news, noticias));
        }else{
            setContentView(R.layout.no_elements_found);
            noElements = (TextView) findViewById(R.id.legend_not_found);
            noElements.setText(R.string.new_not_found);
        }

        b = (Button)findViewById(R.id.next_news);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, TrademarkActivity.class));
            }
        });
    }



}
