package com.pedron.tradeflow.tradeflow.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedron.tradeflow.tradeflow.R;
import com.pedron.tradeflow.tradeflow.adapters.AdapterClient;
import com.pedron.tradeflow.tradeflow.util.DatabaseHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by leocg on 17/03/2016.
 */
public class ClientsActivity extends AppCompatActivity implements AdapterClient.OnItemClickListener{

    private RecyclerView recycler;
    private List<String> clientList;
    TextView textView;
    String idTienda, idCliente;
    List clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trademark_list);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_imageview, null);
        actionBar.setCustomView(v);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        idTienda = b.getString("idTienda");

        //obtengo la lista de clientes de la BDD
        DatabaseHandler db = new DatabaseHandler(this);
        clientes = db.getClients(idTienda);

        textView = (TextView) findViewById(R.id.screen_title);
        textView.setText("Clientes");

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.hasFixedSize();
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        clientList = getImagesUrls();
        AdapterClient adaptC = new AdapterClient(getApplicationContext(), clientList);
        adaptC.setOnItemClickListener(this);
        recycler.setAdapter(adaptC);
        adaptC.notifyDataSetChanged();
    }

    public List<String> getImagesUrls(){
        List<String> tmList = new ArrayList<>();
        String directory = getDirectory();

        //File root = Environment.getExternalStorageDirectory();
        File dir = new File(directory);
        for (File f : dir.listFiles()) {

            if (f.isAbsolute())
                tmList.add(f.getAbsolutePath());
        }
        Collections.sort(tmList);
        printList(tmList);

        return tmList;
    }

    public String getDirectory(){
        String directory = "";

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "TRADEFLOW");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            File subfolder = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "TRADEFLOW" + File.separator + "CLIENTS");


            if (!subfolder.exists()) {
                subfolder.mkdir();
                directory = subfolder.getAbsolutePath();
            }else{
                directory = subfolder.getAbsolutePath();
            }
        } else {
            new Throwable("No se puede crear el directorio");
        }

        return directory;
    }

    @Override
    public void onItemClick(AdapterClient.ViewHolder item, int position) {
        Intent launchActivity = new Intent(ClientsActivity.this, MenuActivity.class);
        Bundle extras = new Bundle();
        position++;
        extras.putString("idCliente", String.valueOf(position));
        launchActivity.putExtras(extras);
        startActivity(launchActivity);
    }

    /**
     * Basado en una lista de los nombres de las imagenes, podemos obtener que cliente buscar
     *
     * @param clientes
     * @return el idCliente seleccionado
     */
    public String selectCliente(List clientes){
        String cliente = "0";


        return cliente;
    }

    public void printList(List<String> list){
        for (String e : list){
            Log.i("Leobas", "elemento: " + e);
        }
    }
}
