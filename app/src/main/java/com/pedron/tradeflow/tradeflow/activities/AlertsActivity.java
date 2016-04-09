package com.pedron.tradeflow.tradeflow.activities;

import android.app.ActionBar;
import android.app.ListActivity;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leocg on 15/03/2016.
 */
public class AlertsActivity extends AppCompatActivity {

    Button next;
    List alertas;
    ListView listView;
    TextView textView;
    String idTienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);
        textView = (TextView) findViewById(R.id.screen_title);
        textView.setText("Alertas");

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        idTienda = b.getString("idTienda");

        DatabaseHandler db = new DatabaseHandler(this);
        alertas = db.getAlerts(idTienda);
        listView = (ListView)findViewById(R.id.list_view_alerts);

        listView.setAdapter(new ArrayAdapter<String>(
                this, R.layout.list_item_alert, R.id.text_alert, alertas));

        next = (Button)findViewById(R.id.next_alert);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivity = new Intent(AlertsActivity.this, ClientsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("idTienda", idTienda);
                launchActivity.putExtras(extras);
                startActivity(launchActivity);
            }
        });

    }

}
